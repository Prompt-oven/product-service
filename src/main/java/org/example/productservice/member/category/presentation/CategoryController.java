package org.example.productservice.member.category.presentation;

import java.util.List;

import org.example.productservice.member.category.application.CategoryService;
import org.example.productservice.member.category.dto.out.GetSubCategoriesResponseDto;
import org.example.productservice.member.category.vo.out.GetSubCategoriesResponseVo;
import org.example.productservice.global.common.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "카테고리 관리 API", description = "카테고리 관련 API endpoints")
@RequestMapping("/api/category")
public class CategoryController {

	private final CategoryService categoryService;

	@Operation(summary = "하위 카테고리 리스트 조회", description = "parentCategoryCode =\"top\"입력시 최상위 카테고리 리스트 조회",
		tags = "공통")
	@GetMapping("/sub-categories")
	public BaseResponse<List<GetSubCategoriesResponseVo>> getSubCategories(
		@RequestParam(value = "parentCategoryUuid", required = false) String parentCategoryUuid) {
		return new BaseResponse<>(
			categoryService.getSubCategories(parentCategoryUuid)
				.stream()
				.map(GetSubCategoriesResponseDto::toVo)
				.toList()
		);
	}
}