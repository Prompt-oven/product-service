package org.example.productservice.member.category.infrastructure;

import java.util.List;
import java.util.Optional;

import org.example.productservice.common.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByParentCategoryUuid(String parentCategoryUuid);

	List<Category> findByParentCategoryUuidIsNull();

	@Query("SELECT c FROM Category c WHERE c.categoryUuid = :categoryUuid")
	Optional<Category> findByCategoryUuid(String categoryUuid);

}
