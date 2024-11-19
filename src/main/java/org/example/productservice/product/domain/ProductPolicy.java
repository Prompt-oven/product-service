package org.example.productservice.product.domain;

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
@AllArgsConstructor
@Builder
public class ProductPolicy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productPolicyId;

	@Column(nullable = false)
	private String productPolicyUuid;

	@Column(nullable = false)
	private String productUuid;

	@Column(nullable = false)
	private float discountRate;

	@Column(nullable = false)
	private boolean enabled;

	@Column(nullable = false)
	private boolean deleted;

	@Column(nullable = false)
	private boolean approved;

	@Column(length = 20)
	private String seed;

	@Column(nullable = false)
	private Long llmVersionId;

	@Column(nullable = false)
	private String llmVersionName;
}
