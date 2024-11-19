package org.example.productservice.product.dto.in;

import org.example.productservice.product.domain.ProductPolicy;
import org.example.productservice.common.utils.UuidGenerator;
import org.example.productservice.product.vo.in.AddProductPolicyRequestVo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AddProductPolicyRequestDto {

	private String productUuid;
	private float discountRate;
	private boolean enabled;
	private boolean deleted;
	private boolean approved;
	private String seed;
	private Long llmVersionId;

	public static AddProductPolicyRequestDto toDto(
		AddProductPolicyRequestVo addProductPolicyRequestVo) {
		return AddProductPolicyRequestDto.builder()
			.productUuid(addProductPolicyRequestVo.getProductUuid())
			.discountRate(addProductPolicyRequestVo.getDiscountRate())
			.enabled(addProductPolicyRequestVo.isEnabled())
			.deleted(addProductPolicyRequestVo.isDeleted())
			.approved(addProductPolicyRequestVo.isApproved())
			.seed(addProductPolicyRequestVo.getSeed())
			.llmVersionId(addProductPolicyRequestVo.getLlmVersionId())
			.build();
	}


	public ProductPolicy createEntity(String productUuid) {
		return ProductPolicy.builder()
			.productUuid(productUuid)
			.productPolicyUuid(UuidGenerator.generateProductPolicyUuid())
			.discountRate(discountRate)
			.enabled(enabled)
			.deleted(deleted)
			.approved(approved)
			.seed(seed)
			.llmVersionId(llmVersionId)
			.build();
	}
}
