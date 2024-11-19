package org.example.productservice.product.vo.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class AddProductRequestVo {

	private String sellerUuid;
	private int price;
	private String productName;
	private String prompt;
	private String description;
	private Long llmId;
	private String topCategoryUuid;
	private String subCategoryUuid;
}
