package com.bloomscorp.aster.product.orm;

import com.bloomscorp.aster.product.contract.AsterProductVariantContract;
import com.bloomscorp.aster.support.AsterBehemothORM;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterProductVariant extends AsterBehemothORM {
	
	@Column(
		name = AsterProductVariantContract.VARIANT_NAME,
		nullable = false,
		columnDefinition = "VARCHAR"
	)
	private String variantName;
	
	@Column(
		name = AsterProductVariantContract.VARIANT_VALUE,
		nullable = false,
		columnDefinition = "VARCHAR"
	)
	private String variantValue;
}
