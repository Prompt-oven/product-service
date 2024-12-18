package org.example.productservice.product.presentation;

import java.util.List;

import org.example.productservice.common.response.BaseResponse;
import org.example.productservice.product.application.ProductService;
import org.example.productservice.product.dto.in.GetProductListRequestDto;
import org.example.productservice.product.dto.in.GetSellersProductListRequestDto;
import org.example.productservice.product.dto.in.SellerProductDto;
import org.example.productservice.product.dto.out.TemporaryAddProductResponseDto;
import org.example.productservice.product.mapper.ProductMapper;
import org.example.productservice.product.vo.in.AddProductRequestVo;
import org.example.productservice.product.vo.in.UpdateProductRequestVo;
import org.example.productservice.product.vo.out.GetProductDetailResponseVo;
import org.example.productservice.product.vo.out.GetProductListResponseVo;
import org.example.productservice.product.vo.out.GetSellerUuidByProductUuidResponseVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
@Tag(name = "판매자 상품 API", description = "판매자 상품 관련 API endpoints")
public class ProductController {

	private final ProductService productService;
	private final ProductMapper productMapper;

	@Operation(summary = "상품 임시 등록", description = """
	상품 등록
	
	- llmId: (Dall-E, 1), (GPT, 2)
	
	contents는 리스트 형태로 여러 개 등록 가능.
	""")
	@PostMapping("/seller/product/temporary")
	public BaseResponse<TemporaryAddProductResponseVo> temporaryAddProduct(@RequestBody AddProductRequestVo addProductRequestVo) {

		TemporaryAddProductResponseDto temporaryAddProductResponseDto =
			productService.temporaryAddProduct(productMapper.toDto(addProductRequestVo));

		return new BaseResponse<>(
			productMapper.toVo(temporaryAddProductResponseDto)
		);
	}

	// @Operation(summary = "상품 임시 등록 목록 보기", description = "상품 임시 등록 목록 보기")
	// @GetMapping("/seller/product/temporary/list")
	// public BaseResponse<List<GetTemporaryProductListResponseVo>> getTemporaryProductList(@RequestParam String memberUuid) {
	//
	// 	return new BaseResponse<>(
	// 		productService.getTemporaryProductList(memberUuid).stream()
	// 			.map(productMapper::toVo)
	// 			.toList()
	// 	);
	// }

	@Operation(summary = "상품 등록", description = """
	상품 등록
	
	- llmId: (Dall-E, 1), (GPT, 2)
	
	contents는 리스트 형태로 여러 개 등록 가능.
	""")
	@PostMapping("/seller/product")
	public BaseResponse<Void> addProduct(@RequestBody AddProductRequestVo addProductRequestVo) {

		productService.addProduct(productMapper.toDto(addProductRequestVo));
		return new BaseResponse<>();
	}

	@Operation(summary = "상품 수정", description = "상품 수정")
	@PutMapping("/seller/product")
	public BaseResponse<Void> updateProduct(@RequestBody UpdateProductRequestVo updateProductRequestVo) {

		productService.updateProduct(productMapper.toDto(updateProductRequestVo));
		return new BaseResponse<>();
	}

	@Operation(summary = "상품 삭제", description = "상품 삭제")
	@DeleteMapping("/seller/product/{productUuid}")
	public BaseResponse<Void> deleteProduct(@PathVariable String productUuid) {
		productService.deleteProduct(productUuid);
		return new BaseResponse<>();
	}

	@Operation(summary = "상품 상세 조회", description = "상품 상세 조회")
	@GetMapping("/product/{productUuid}")
	public BaseResponse<GetProductDetailResponseVo> getProductDetail(@PathVariable String productUuid) {

		return new BaseResponse<>(
			productMapper.toVo(productService.getProductDetail(productUuid))
		);
	}

	@Operation(summary = "상품 판매자 조회", description = "상품 판매자 조회")
	@GetMapping("/product/{productUuid}/seller")
	public BaseResponse<GetSellerUuidByProductUuidResponseVo> getSellerUuidByProductUuid(@PathVariable String productUuid) {

		return new BaseResponse<>(
			productMapper.toVo(productService.getSellerUuidByProductUuid(productUuid))
		);
	}

	@Operation(summary = "상품 목록 보기", description = "상품 목록 보기")
	@GetMapping("/product/list")
	public BaseResponse<GetProductListResponseVo> searchProducts(
		@RequestParam(required = false) String searchBar,
		@RequestParam(required = false) String topCategoryUuid,
		@RequestParam(required = false) String subCategoryUuid,
		@RequestParam(required = false) Boolean enable,
		@RequestParam(required = false, defaultValue = "0") String minPrice,
		@RequestParam(required = false) String maxPrice,

		@Parameter(
			description = "정렬 기준 likeCount, avgStar, sells, createdAt, reviewCount"
		)
		@RequestParam(required = false, defaultValue = "createdAt") String sortOption,

		@Parameter(
			description = "정렬 방향 ASC, DESC"
		)
		@RequestParam(required = false, defaultValue = "DESC") String sortBy,
		@RequestParam(required = false) String cursorId,
		@RequestParam(required = false, defaultValue = "20") Integer pageSize,
		@RequestParam(required = false) List<Long> llmIdList
	) {

		GetProductListRequestDto getProductListRequestDto = GetProductListRequestDto.builder()
			.searchBar(searchBar)
			.topCategoryUuid(topCategoryUuid)
			.subCategoryUuid(subCategoryUuid)
			.enable(enable)
			.minPrice(minPrice)
			.maxPrice(maxPrice)
			.sortOption(sortOption)
			.sortBy(sortBy)
			.cursorId(cursorId)
			.pageSize(pageSize)
			.llmIdList(llmIdList)
			.build();

		log.info("sortOption: {}", sortOption);

		return new BaseResponse<>(
			productMapper.toVo(productService.searchProducts(getProductListRequestDto))
		);
	}

	@Operation(summary = "판매자 상품 목록 보기", description = "판매자의 상품 목록을 조회합니다. number: 현재페이지, size: 페이지 크기, totalElements: 전체 상품 수, totalPages: 전체 페이지 수, numberOfElements: 현재 페이지의 상품 수, pageSize: 페이지당 최대 항목 수")
	@GetMapping("/product/{sellerUuid}/list")
	public BaseResponse<Page<SellerProductDto>> getSellerProducts(

		@Parameter(description = "판매자 UUID", required = true)
		@PathVariable String sellerUuid,

		@RequestParam(required = false) String searchBar,

		@Parameter(description = "정렬 기준 price, sells, createdAt(default)")
		@RequestParam(required = false, defaultValue = "createdAt") String sortOption,

		@Parameter(description = "정렬 방향 ASC, DESC(default)")
		@RequestParam(required = false, defaultValue = "DESC") String sortBy,

		@Parameter(description = "true(default), false")
		@RequestParam(required = false, defaultValue = "true") boolean enable,

		@Parameter(description = "true, false(default)")
		@RequestParam(required = false, defaultValue = "false") boolean temporary,

		@Parameter(description = "페이지 번호 (0부터 시작)")
		@RequestParam(required = false, defaultValue = "0") int page,

		@Parameter(description = "페이지 크기 (default: 16)")
		@RequestParam(required = false, defaultValue = "16") int size
	) {
		GetSellersProductListRequestDto requestDto = GetSellersProductListRequestDto.builder()
			.sellerUuid(sellerUuid)
			.searchBar(searchBar)
			.sortOption(sortOption)
			.sortBy(sortBy)
			.enable(enable)
			.temporary(temporary)
			.build();

		Sort sort = Sort.by("DESC".equals(sortBy) ? Sort.Direction.DESC : Sort.Direction.ASC, sortOption);
		Pageable pageable = PageRequest.of(page, size, sort);

		log.info("Seller Products List Request - sellerUuid: {}, sortOption: {}, enable: {}, temporary: {}, page: {}, size: {}",
			sellerUuid, sortOption, enable, temporary, page, size);

		return new BaseResponse<>(productService.getSellersProductList(requestDto, pageable));
	}


}
