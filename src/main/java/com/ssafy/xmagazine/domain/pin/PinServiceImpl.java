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
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.script.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.xmagazine.mapper.PinMapper;

@Service
@Transactional
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

	@Override
	public List<PinDto> selectPinByMultiTagAndPage(List<String> tagNames, int offset, int limit) {
		return pinMapper.selectPinByMultiTagAndPage(tagNames, offset, limit);
	}

	@Override
	public List<PinDto> searchPinsByMultiTagAndPage(List<String> tagNames, int offset, int limit) {
		SearchRequest searchRequest = buildSearchRequest("search_index", tagNames, offset, limit);
		return executeSearch(searchRequest);
	}

	private SearchRequest buildSearchRequest(String indexName, List<String> tagNames, int offset, int limit) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(buildBoolQuery(tagNames));
		configureSort(searchSourceBuilder);
		searchSourceBuilder.from(offset);
		searchSourceBuilder.size(limit);

		SearchRequest searchRequest = new SearchRequest(indexName);
		searchRequest.source(searchSourceBuilder);
		return searchRequest;
	}

	private BoolQueryBuilder buildBoolQuery(List<String> tagNames) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		BoolQueryBuilder tagMatchQuery = QueryBuilders.boolQuery();

		tagMatchQuery.should(QueryBuilders.termsQuery("tags.keyword", tagNames));
		tagNames.forEach(tagName -> {
			tagMatchQuery.should(QueryBuilders.matchQuery("title", tagName));
			tagMatchQuery.should(QueryBuilders.matchQuery("description", tagName));
			tagMatchQuery.should(QueryBuilders.matchQuery("address", tagName));
		});

		boolQueryBuilder.must(tagMatchQuery);
		boolQueryBuilder.must(QueryBuilders.termQuery("isDeleted", false));
		return boolQueryBuilder;
	}

	private void configureSort(SearchSourceBuilder sourceBuilder) {
		sourceBuilder.sort(SortBuilders.fieldSort("likeCount").order(SortOrder.DESC));
		sourceBuilder.sort(SortBuilders
				.scriptSort(new Script("doc['tags.keyword'].length"), ScriptSortBuilder.ScriptSortType.NUMBER)
				.order(SortOrder.DESC));
	}

	private List<PinDto> executeSearch(SearchRequest searchRequest) {
		try {
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			return Arrays.stream(searchResponse.getHits().getHits())
					.map(hit -> convertToPinDto(hit.getSourceAsString()))
					.collect(Collectors.toList());
		} catch (IOException e) {
			System.err.println("Failed to execute search query: " + e.getMessage());
			return Collections.emptyList();
		}
	}

	private PinDto convertToPinDto(String source) {
		try {
			return objectMapper.readValue(source, PinDto.class);
		} catch (IOException e) {
			System.err.println("Error parsing JSON: " + e.getMessage());
			return null;
		}
	}
}
