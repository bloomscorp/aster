package com.bloomscorp.aster.product.subcategory.orm;

import com.bloomscorp.aster.product.subcategory.contract.AsterProductSubCategoryContract;
import com.bloomscorp.behemoth.orm.BehemothORM;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterProductSubCategory extends BehemothORM {
	
	@Column(
		name = AsterProductSubCategoryContract.NAME,
		nullable = false
	)
	private String name;
	
	@Column(
		name = AsterProductSubCategoryContract.ICON
	)
	private String icon;
	
	@Column(
		name = AsterProductSubCategoryContract.CREATED_AT,
		nullable = false
	)
	private Long createdAt;
}
