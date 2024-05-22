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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DistanceIndexing implements CommandLineRunner {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    public static void main(String[] args) {
        log.debug("DistanceIndexing is running");
        SpringApplication.run(DistanceIndexing.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String indexName = "distance_index";

        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("34.207.79.51", 9200, "http")));
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

    private boolean createIndex(RestHighLevelClient client, String indexName) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 1));
        request.mapping(
                "{\n" +
                        "  \"properties\": {\n" +
                        "    \"userId\": {\"type\": \"integer\"},\n" +
                        "    \"title\": {\"type\": \"text\"},\n" +
                        "    \"imageUrl\": {\"type\": \"text\", \"fields\": {\"keyword\": {\"type\": \"keyword\", \"ignore_above\": 256}}},\n"
                        +
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

    private void indexData(RestHighLevelClient client, String indexName, ResultSet rs)
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
            jsonMap.put("isDeleted", rs.getBoolean("is_deleted"));

            IndexRequest indexRequest = new IndexRequest(indexName)
                    .id(String.valueOf(rs.getInt("pin_id")))
                    .source(jsonMap);
            client.index(indexRequest, RequestOptions.DEFAULT);
        }
    }
}