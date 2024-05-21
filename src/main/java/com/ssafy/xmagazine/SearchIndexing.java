package com.ssafy.xmagazine;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import com.nimbusds.jose.shaded.gson.Gson;

public class SearchIndexing {
    public static void main(String[] args) {
        // Gson 객체 초기화
        Gson gson = new Gson();

        // 데이터베이스 연결 및 데이터 추출
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pintravel", "ssafy", "ssafy");
                PreparedStatement pst = con.prepareStatement(
                        "SELECT p.*, GROUP_CONCAT(t.name SEPARATOR ', ') AS tags, COUNT(DISTINCT l.like_id) AS like_count "
                                +
                                "FROM pins p " +
                                "LEFT JOIN pintags pt ON p.pin_id = pt.pin_id " +
                                "LEFT JOIN tags t ON pt.tag_id = t.tag_id " +
                                "LEFT JOIN likes l ON p.pin_id = l.pin_id " +
                                "GROUP BY p.pin_id");
                ResultSet rs = pst.executeQuery()) {

            // Elasticsearch 클라이언트 설정
            try (RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http")))) {

                // 데이터 인덱싱
                while (rs.next()) {
                    Map<String, Object> jsonMap = new HashMap<>();
                    jsonMap.put("pinId", rs.getInt("pin_id")); // "pin_id" -> "pinId"
                    jsonMap.put("userId", rs.getInt("user_id")); // "user_id" -> "userId"
                    jsonMap.put("title", rs.getString("title"));
                    jsonMap.put("imageUrl", rs.getString("image_url")); // "image_url" -> "imageUrl"
                    jsonMap.put("description", rs.getString("description"));
                    jsonMap.put("address", rs.getString("address"));
                    jsonMap.put("latitude", rs.getDouble("latitude"));
                    jsonMap.put("longitude", rs.getDouble("longitude"));

                    // createdAt을 LocalDateTime으로 변환
                    String createdAtStr = rs.getString("created_at");
                    jsonMap.put("createdAt", createdAtStr);

                    jsonMap.put("isDeleted", rs.getBoolean("is_deleted")); // "is_deleted" -> "isDeleted"
                    jsonMap.put("likeCount", rs.getInt("like_count")); // "like_count" -> "likeCount"

                    // tags 처리: null 체크 추가
                    String tags = rs.getString("tags");
                    if (tags != null) {
                        jsonMap.put("tags", tags.split(", "));
                    } else {
                        jsonMap.put("tags", new String[0]); // 빈 배열 할당
                    }

                    String jsonString = gson.toJson(jsonMap);
                    IndexRequest indexRequest = new IndexRequest("search_index")
                            .id(String.valueOf(rs.getInt("pin_id")))
                            .source(jsonString, XContentType.JSON);
                    client.index(indexRequest, RequestOptions.DEFAULT);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
