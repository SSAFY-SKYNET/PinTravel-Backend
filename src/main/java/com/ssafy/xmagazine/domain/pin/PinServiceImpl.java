package com.ssafy.xmagazine.domain.pin;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.xmagazine.mapper.PinMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PinServiceImpl implements PinService {

	private final PinMapper pinMapper;
	private final RestHighLevelClient client;
	private final ObjectMapper objectMapper;

	@Autowired
	public PinServiceImpl(PinMapper pinMapper, RestHighLevelClient client, ObjectMapper objectMapper) {
		this.pinMapper = pinMapper;
		this.client = client;
		this.objectMapper = objectMapper;
	}

	@Override
	public PinDto selectPinById(int pinId) {
		return pinMapper.selectPinById(pinId);
	}

	@Override
	public List<PinDto> selectPinByLikeCount() {
		return pinMapper.selectPinByLikeCount();
	}

	@Override
	public List<PinDto> selectPinByLikeCountAndPage(int offset, int limit) {
		return pinMapper.selectPinByLikeCountAndPage(offset, limit);
	}

	@Override
	public List<PinDto> selectPinByUserId(int userId) {
		return pinMapper.selectPinByUserId(userId);
	}

	@Override
	public List<PinDto> selectPinByUserIdAndPage(int userId, int offset, int limit) {
		return pinMapper.selectPinByUserIdAndPage(userId, offset, limit);
	}

	@Override
	public List<PinDto> selectPinByTag(int tagId) {
		return pinMapper.selectPinByTag(tagId);
	}

	@Override
	public List<PinDto> selectPinByTagAndPage(int tagId, int offset, int limit) {
		return pinMapper.selectPinByTagAndPage(tagId, offset, limit);
	}

	@Override
	public List<PinDto> selectPinByBoard(int boardId) {
		return pinMapper.selectPinByBoard(boardId);
	}

	@Override
	public List<PinDto> selectPinByBoardAndPage(int boardId, int offset, int limit) {
		return pinMapper.selectPinByBoardAndPage(boardId, offset, limit);
	}

	@Override
	public List<PinDto> selectPinByPinIdAndPage(double longitude, double latitude, int offset, int limit) {
		return pinMapper.selectPinByPinIdAndPage(longitude, latitude, offset, limit);
	}

	@Override
	public void insertPin(PinDto pin) {
		pinMapper.insertPin(pin);
	}

	@Override
	public void updatePin(PinDto pin) {
		pinMapper.updatePin(pin);
	}

	@Override
	public void deletePin(int pinId) {
		pinMapper.deletePin(pinId);
	}

	// Mapper를 활용한 검색
	@Override
	public List<PinDto> selectPinByMultiTagAndPage(List<String> tagNames, int
	offset, int limit) {
	return pinMapper.selectPinByMultiTagAndPage(tagNames, offset, limit);
	}

	// Elasticsearch 를 활용한 검색
	// @Override
	// public List<PinDto> selectPinByMultiTagAndPage(List<String> tagNames, int offset, int limit) {
	// 	log.debug("selectPinByMultiTagAndPage is running");
	// 	SearchRequest searchRequest = buildSearchRequest("search_index", tagNames, offset, limit);
	// 	return executeSearch(searchRequest);
	// }

	private SearchRequest buildSearchRequest(String indexName, List<String> keywords, int offset, int limit) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(buildBoolQuery(keywords)); // 키워드 기반으로 불리언 쿼리 생성
		configureSort(searchSourceBuilder); // 정렬 구성
		searchSourceBuilder.from(offset); // 검색 시작 위치 설정
		searchSourceBuilder.size(limit); // 검색 결과의 최대 크기 설정

		SearchRequest searchRequest = new SearchRequest(indexName); // 새 검색 요청 생성
		searchRequest.source(searchSourceBuilder); // 검색 요청에 검색 소스 설정
		return searchRequest;
	}

	private BoolQueryBuilder buildBoolQuery(List<String> keywords) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery(); // 불리언 쿼리 빌더 생성
		MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(String.join(" ", keywords))
				.field("title", 5.0f)
				.field("address", 4.0f)
				.field("tags.name", 3.0f)
				.field("description", 2.0f)
				.fuzziness("AUTO")
				.prefixLength(2)
				.maxExpansions(10)
				.type(MultiMatchQueryBuilder.Type.BEST_FIELDS);

		boolQueryBuilder.must(multiMatchQueryBuilder);
		boolQueryBuilder.must(QueryBuilders.termQuery("isDeleted", false)); // 삭제되지 않은 항목만 검색
		return boolQueryBuilder;
	}

	private void configureSort(SearchSourceBuilder sourceBuilder) {
		sourceBuilder.sort(SortBuilders.scoreSort().order(SortOrder.DESC)); // 점수 기반 정렬
		sourceBuilder.sort(SortBuilders.fieldSort("likeCount").order(SortOrder.DESC)); // 좋아요 수에 따라 내림차순 정렬
	}

	private List<PinDto> executeSearch(SearchRequest searchRequest) {
		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT); // 검색 요청 실행
			return Arrays.stream(searchResponse.getHits().getHits())
					.map(hit -> convertToPinDto(hit.getSourceAsString())) // 검색 결과를 PinDto 객체로 변환
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.err.println("Failed to execute search query: " + e.getMessage()); // 검색 실패 시 에러 로그 출력
			return Collections.emptyList(); // 예외 발생 시 빈 리스트 반환
		}
	}

	private PinDto convertToPinDto(String source) {
		try {
			return objectMapper.readValue(source, PinDto.class); // JSON 문자열을 PinDto 객체로 변환
		} catch (IOException e) {
			System.err.println("Error parsing JSON: " + e.getMessage()); // JSON 파싱 실패 시 에러 로그 출력
			return null; // 예외 발생 시 null 반환
		}
	}

	public List<PinDto> selectPinByNearest(PinDto pin, int offset, int limit) {
		SearchRequest searchRequest = buildProximitySearchRequest("distance_index", pin, offset, limit);
		return executeProximitySearch(searchRequest);
	}

	private SearchRequest buildProximitySearchRequest(String indexName, PinDto pin, int offset, int limit) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(buildGeoQuery(pin)); // 위치 기반 쿼리 생성
		searchSourceBuilder.sort(SortBuilders.geoDistanceSort("location", pin.getLatitude(), pin.getLongitude())
				.order(SortOrder.ASC)); // 거리 기준 오름차순 정렬
		searchSourceBuilder.from(offset); // 검색 시작 위치 설정
		searchSourceBuilder.size(limit); // 검색 결과의 최대 크기 설정

		// 필요한 필드만 반환
		searchSourceBuilder.fetchSource(new String[] { "pinId", "title", "latitude", "longitude" }, null);

		SearchRequest searchRequest = new SearchRequest(indexName); // 새 검색 요청 생성
		searchRequest.source(searchSourceBuilder); // 검색 요청에 검색 소스 설정
		return searchRequest;
	}

	private BoolQueryBuilder buildGeoQuery(PinDto pin) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(QueryBuilders.geoDistanceQuery("location")
				.point(pin.getLatitude(), pin.getLongitude())
				.distance("50km")); // 원하는 검색 반경 설정
		boolQueryBuilder.must(QueryBuilders.termQuery("isDeleted", false)); // 삭제되지 않은 항목만 검색
		return boolQueryBuilder;
	}

	private List<PinDto> executeProximitySearch(SearchRequest searchRequest) {
		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT); // 검색 요청 실행
			return Arrays.stream(searchResponse.getHits().getHits())
					.map(hit -> convertToPinDto(hit.getSourceAsString())) // 검색 결과를 PinDto 객체로 변환
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.err.println("Failed to execute search query: " + e.getMessage()); // 검색 실패 시 에러 로그 출력
			return Collections.emptyList(); // 예외 발생 시 빈 리스트 반환
		}
	}
}
