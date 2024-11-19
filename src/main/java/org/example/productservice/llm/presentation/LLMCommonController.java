package org.example.productservice.llm.presentation;

import java.util.List;

import org.example.productservice.common.response.BaseResponse;
import org.example.productservice.llm.application.LLMService;
import org.example.productservice.llm.dto.out.GetLLMListByTypeResponseDto;
import org.example.productservice.llm.vo.out.GetLLMListByTypeResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/llm")
@Tag(name = "LLM 관리 API", description = "LLM 관리 관련 API endpoints")
public class LLMCommonController {

	private final LLMService llmService;

	@Operation(summary = "LLM 리스트 조회", description = """
	llmType = text, image, 미작성 시 전체 리스트 조회
	""")
	@GetMapping("/list")
	public BaseResponse<List<GetLLMListByTypeResponseVo>> getLLMListByType(
		@RequestParam(required = false) String llmType) {

		return new BaseResponse<>(
			llmService.getLLMListByType(llmType).stream()
				.map(GetLLMListByTypeResponseDto::toVo)
				.toList()
		);
	}
}
