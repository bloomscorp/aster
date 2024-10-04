package com.bloomscorp.aster.cart.orm;

import com.bloomscorp.aster.cart.contract.AsterCartItemContract;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.behemoth.orm.BehemothORM;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterCartItem<
    E extends Enum<E>,
    R extends AsterUserRole<E>,
    T extends NVerseTenant<E, R>,
	P extends AsterProduct
   > extends BehemothORM {
	
	public abstract T getTenant();

	public abstract P getProduct();
	
	@Column(
		name = AsterCartItemContract.QUANTITY,
		nullable = false,
		columnDefinition = "DECIMAL",
		precision = 8,
		scale = 2
	)
	@ColumnDefault("0.00")
	private Double quantity = 0.00;
	
	@Column(
		name = AsterCartItemContract.CREATED_AT,
		nullable = false
	)
	private Long createdAt;
	
	@Column(
		name = AsterCartItemContract.UPDATED_AT
	)
	private Long updatedAt;
	
}
