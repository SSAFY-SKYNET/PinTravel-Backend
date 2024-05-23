// GPTController.java
package com.ssafy.xmagazine.gpt;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GPTController {

	@Value("${gpt.model}")
	private String model;

	@Value("${gpt.api.url}")
	private String apiUrl;
	private final RestTemplate restTemplate;

	@PostMapping("/generate-travel-plan")
	public ResponseEntity<Map<String, String>> generateTravelPlan(@RequestBody Map<String, Object> request) {
		List<Map<String, Object>> items = (List<Map<String, Object>>)request.get("items");
		String prompt = buildPrompt(items);

		GPTRequest gptRequest = new GPTRequest(
			model, prompt, 0.25, 256, 0.1, 0, 0);

		GPTResponse gptResponse = restTemplate.postForObject(
			apiUrl, gptRequest, GPTResponse.class);

		String plan = gptResponse.getChoices().get(0).getMessage().getContent();

		Map<String, String> response = new HashMap<>();
		response.put("plan", plan);
		return ResponseEntity.ok(response);
	}

	private String buildPrompt(List<Map<String, Object>> items) {
		// items를 분석하여 프롬프트 생성
		// 예시: JSON 형식으로 변환 후 프롬프트에 포함
		String itemsJson = new Gson().toJson(items);
		return
			"너는 국내 최고의 관광 가이드야. "
				+ "JSON 형태로 관광지 데이터를 줄거야. "
				+ "너는 해당 데이터를 분석하고 관광 일정을 작성해줘. "
				+ "추천할 때는 데이터를 위도와 경도를 활용해서 K-means clustering 알고리즘을 사용하여 군집화를 실시하고 군집에서 떨어진 부분은 추천 관광지 목록에서 제외해줘."
				+ "군집 1개에 대해서 답변을 작성해줘."
				+ "답변할 때는 좌표 정보와 이미지는 제외하고 관광테마와 관광 지역 위치, 관광 목록, 해당 관광지 요약 정보만 출력해줘. "
				+ "관광 지역 위치의 경우는 해당 군집에 대해서 출력해. 요약 정보는 1줄 이내로 작성해. "
				+ "답변은 HTML과 Tailwind CSS를 사용하여 포맷팅해줘. 이때 \"```html\"을 사용하여 감쌀 필요는 없어."
				+ "전체 응답을 <div class='bg-white p-6 rounded-lg shadow-md'>로 감싸고, "
				+ "관광 테마는 <h2 class='text-2xl font-bold mb-4'>로 감싸서 출력하고, 음식점이라면 \"맛집 탐방\" 같이 표현해,"
				+ "음식점이라면 맛집 탐방 같이 표현해, "
				+ "관광 지역 위치는 <p class='text-gray-600 mb-4'>로 3글자 이내로 출력해, "
				+ "관광 목록은 <ul class='list-disc pl-6 mb-4'>와 <li class='mb-2'>를 사용하고, "
				+ "관광지 요약 정보는 <p class='text-gray-800'>를 사용해. "
				+ "전체 응답은 1500자를 초과하지 않도록 해줘.\n"
				+ itemsJson;
	}
}