package com.bloomscorp.aster.product.orm;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.contract.AsterProductImageContract;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.support.AsterBehemothORM;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterProductImage<
	CA extends AsterProductCategory,
	SCA extends AsterProductSubCategory,
	CO extends AsterProductCollection,
	P extends AsterProduct<
		CA,
		SCA,
		CO,
		? extends AsterProductSubCategoryMapping<CA, SCA, CO, ?>,
		? extends AsterProductCollectionMapping<CA, SCA, CO, ?>,
		? extends AsterProductImage<CA, SCA, CO, P>
		>
	> extends AsterBehemothORM {
	
	@Column(
		name = AsterProductImageContract.IMAGE_URL,
		nullable = false,
		columnDefinition = "VARCHAR"
	)
	private String imageUrl;
	
	@Column(
		name = AsterProductImageContract.ALT_TEXT,
		nullable = false,
		columnDefinition = "TEXT"
	)
	private String altText;
	
	@Column(
		name = AsterProductImageContract.ORDER,
		nullable = false,
		columnDefinition = "INT"
	)
	@ColumnDefault("0")
	private int order = 0;
	
	@Column(
		name = AsterProductImageContract.CREATED_AT,
		nullable = false,
		columnDefinition = "BIGINT"
	)
	private Long createdAt;
	
	@Transient
	private MultipartFile imageFile;
	
	@Transient
	private boolean deleted = false;
}
