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
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;

public class DistanceIndexing {

    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/pintravel";
        String dbUser = "ssafy";
        String dbPassword = "ssafy";
        String indexName = "distance_index";

        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
             Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {

            if (!createIndex(client, indexName)) {
                System.out.println("Index creation failed.");
                return;
            }

            try (PreparedStatement pst = con.prepareStatement(
                    "SELECT pin_id, user_id, title, image_url, description, address, latitude, longitude, created_at, is_deleted FROM pins")) {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    indexData(client, indexName, rs);
                }
            }

            System.out.println("Data indexed successfully.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean createIndex(RestHighLevelClient client, String indexName) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 1));
        request.mapping(
                "{\n" +
                        "  \"properties\": {\n" +
                        "    \"userId\": {\"type\": \"integer\"},\n" +
                        "    \"title\": {\"type\": \"text\"},\n" +
                        "    \"imageUrl\": {\"type\": \"text\", \"fields\": {\"keyword\": {\"type\": \"keyword\", \"ignore_above\": 256}}},\n" +
                        "    \"description\": {\"type\": \"text\"},\n" +
                        "    \"address\": {\"type\": \"text\"},\n" +
                        "    \"location\": {\"type\": \"geo_point\"},\n" +
                        "    \"createdAt\": {\"type\": \"date\"},\n" +
                        "    \"isDeleted\": {\"type\": \"boolean\"}\n" +
                        "  }\n" +
                        "}",
                XContentType.JSON);

        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        return createIndexResponse.isAcknowledged();
    }

    private static void indexData(RestHighLevelClient client, String indexName, ResultSet rs)
            throws SQLException, IOException {
        while (rs.next()) {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("userId", rs.getInt("user_id"));
            jsonMap.put("title", rs.getString("title"));
            jsonMap.put("imageUrl", rs.getString("image_url"));
            jsonMap.put("description", rs.getString("description"));
            jsonMap.put("address", rs.getString("address"));
            jsonMap.put("location", Map.of("lat", rs.getDouble("latitude"), "lon", rs.getDouble("longitude")));
            jsonMap.put("createdAt", rs.getTimestamp("created_at").toInstant().toString());
            jsonMap.put("isDeleted", rs.getBoolean("is_deleted"));

            IndexRequest indexRequest = new IndexRequest(indexName)
                    .id(String.valueOf(rs.getInt("pin_id")))
                    .source(jsonMap);
            client.index(indexRequest, RequestOptions.DEFAULT);
        }
    }
}
