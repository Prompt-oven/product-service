package org.example.productservice.admin.category.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteCategoryRequestVo {

	private String categoryUuid;

	public DeleteCategoryRequestVo(String categoryUuid) {
		this.categoryUuid = categoryUuid;
	}
}
