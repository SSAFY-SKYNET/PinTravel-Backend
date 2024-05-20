package com.ssafy.xmagazine;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;

public class ElasticsearchExample {
    public static void main(String[] args) throws IOException {
        // RestHighLevelClient 객체를 생성하여 Elasticsearch 서버에 연결합니다.
        // 이 클라이언트는 Elasticsearch의 RESTful API와 통신을 담당합니다.
        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")))) { // localhost에 있는 Elasticsearch 인스턴스에 연결

            // 검색 쿼리를 구성하기 위한 SearchSourceBuilder 객체를 생성합니다.
            // SearchSourceBuilder는 검색 쿼리를 구성하는 데 사용되는 도구입니다.
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery()); // 모든 문서를 검색하는 쿼리를 생성합니다.

            // 검색 요청을 정의하는 SearchRequest 객체를 생성합니다.
            // SearchRequest 객체는 검색을 실행하기 위해 필요한 정보를 포함합니다.
            SearchRequest searchRequest = new SearchRequest();
            searchRequest.source(searchSourceBuilder); // 생성한 검색 쿼리를 검색 요청에 설정합니다.

            // 클라이언트를 사용하여 검색 요청을 실행하고 결과를 SearchResponse 객체로 받습니다.
            // RequestOptions.DEFAULT는 기본 요청 옵션을 사용합니다.
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

            // SearchResponse에서 검색 결과를 포함하고 있는 SearchHits 객체를 추출합니다.
            SearchHits hits = searchResponse.getHits();

            // 검색된 문서의 총 개수를 콘솔에 출력합니다.
            System.out.println("검색 결과: " + hits.getTotalHits());
        }
    }
}
