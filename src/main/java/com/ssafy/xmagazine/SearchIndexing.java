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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.shaded.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SearchIndexing implements CommandLineRunner {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    public static void main(String[] args) {
        log.debug("SearchIndexing is running");
        SpringApplication.run(SearchIndexing.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Gson gson = new Gson();
        String indexName = "distance_index";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                PreparedStatement pst = con.prepareStatement(
                        "SELECT p.*, GROUP_CONCAT(t.name SEPARATOR ', ') AS tags, COUNT(DISTINCT l.like_id) AS like_count "
                                +
                                "FROM pins p " +
                                "LEFT JOIN pintags pt ON p.pin_id = pt.pin_id " +
                                "LEFT JOIN tags t ON pt.tag_id = t.tag_id " +
                                "LEFT JOIN likes l ON p.pin_id = l.pin_id " +
                                "GROUP BY p.pin_id");
                ResultSet rs = pst.executeQuery()) {

            try (RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("34.207.79.51", 9200, "http")))) {
                while (rs.next()) {
                    Map<String, Object> jsonMap = new HashMap<>();
                    jsonMap.put("pinId", rs.getInt("pin_id"));
                    jsonMap.put("userId", rs.getInt("user_id"));
                    jsonMap.put("title", rs.getString("title"));
                    jsonMap.put("imageUrl", rs.getString("image_url"));
                    jsonMap.put("description", rs.getString("description"));
                    jsonMap.put("address", rs.getString("address"));
                    jsonMap.put("latitude", rs.getDouble("latitude"));
                    jsonMap.put("longitude", rs.getDouble("longitude"));

                    // createdAt을 문자열로 변환하여 jsonMap에 추가
                    String createdAtStr = rs.getString("created_at");
                    jsonMap.put("createdAt", createdAtStr);

                    jsonMap.put("isDeleted", rs.getBoolean("is_deleted"));
                    jsonMap.put("likeCount", rs.getInt("like_count"));

                    String tags = rs.getString("tags");
                    jsonMap.put("tags", tags != null ? tags.split(", ") : new String[0]);

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