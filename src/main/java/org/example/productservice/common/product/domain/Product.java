package org.example.productservice.common.product.domain;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
@DynamicUpdate
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(nullable = false, length = 50)
	private String productUuid;

	@Column(nullable = false, length = 50)
	private String sellerUuid;

	@Column(nullable = false, length = 50)
	private String sellerName;

	@Column(nullable = false, length = 50)
	private String productName;

	@Column(nullable = false)
	private int price;

	@Column(nullable = false, length = 500)
	private String prompt;

	@Column(nullable = false, length = 100)
	private String description;

	@Column(nullable = false)
	private Long llmId;

	@Column(nullable = false)
	private String llmName;

	@Column(nullable = false)
	private String topCategoryUuid;

	@Column(nullable = false)
	private String topCategoryName;

	@Column(nullable = false)
	private String subCategoryUuid;

	@Column(nullable = false)
	private String subCategoryName;

	@Column(nullable = false)
	private boolean deleted;
}
