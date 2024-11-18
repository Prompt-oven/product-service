package org.example.productservice.llm.dto.in;

import org.example.productservice.llm.vo.in.AddLLMVersionRequestVo;
import org.example.productservice.llm.domain.LLMVersion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddLLMVersionRequestDto {

	private String llmVersionName;
	private Long llmId;

	public static AddLLMVersionRequestDto toDto(AddLLMVersionRequestVo addLLMVersionRequestVo) {
		return AddLLMVersionRequestDto.builder()
				.llmId(addLLMVersionRequestVo.getLlmId())
				.llmVersionName(addLLMVersionRequestVo.getLlmVersionName())
				.build();
	}

	public LLMVersion createEntity() {
		return LLMVersion.builder()
				.llmVersionName(llmVersionName)
				.llmId(llmId)
				.deleted(false)
				.build();
	}

	public LLMVersion updateEntity(Long llmVersionId) {
		return LLMVersion.builder()
				.llmVersionId(llmVersionId)
				.llmId(llmId)
				.llmVersionName(llmVersionName)
				.deleted(false)
				.build();
	}
}