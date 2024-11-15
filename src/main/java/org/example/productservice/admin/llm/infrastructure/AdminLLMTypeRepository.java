package org.example.productservice.admin.llm.infrastructure;

import java.util.Optional;

import org.example.productservice.common.llm.domain.LLMType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLLMTypeRepository extends JpaRepository<LLMType, Long> {
	boolean existsByLlmTypeName(String llmTypeName);

	Optional<LLMType> findByLlmTypeName(String llmTypeName);
}