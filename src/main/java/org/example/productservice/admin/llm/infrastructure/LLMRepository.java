package org.example.productservice.admin.llm.infrastructure;

import java.util.Optional;

import org.example.productservice.common.domain.LLM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LLMRepository extends JpaRepository<LLM, Long> {
	Optional<LLM> findByName(String name);
}
