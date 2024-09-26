package com.bloomscorp.aster.product.orm;

import com.bloomscorp.aster.product.contract.AsterProductContract;
import com.bloomscorp.behemoth.orm.BehemothORM;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterProduct extends BehemothORM {

	@Column(
		name = AsterProductContract.NAME,
		nullable = false,
		columnDefinition = "TEXT"
	)
	private String name;

	@Column(
		name = AsterProductContract.SLUG,
		nullable = false,
		columnDefinition = "TEXT"
	)
	private String slug;

	@Column(
		name = AsterProductContract.SKU,
		nullable = false,
		columnDefinition = "VARCHAR",
		length = 25
	)
	private String sku;

	@Column(
		name = AsterProductContract.PRICE,
		nullable = false,
		columnDefinition = "DECIMAL",
		precision = 8,
		scale = 2
	)
	@ColumnDefault("0.00")
	private double price = 0.00;

	@Column(
		name = AsterProductContract.QUANTITY,
		nullable = false,
		columnDefinition = "DECIMAL",
		precision = 8,
		scale = 2
	)
	@ColumnDefault("0.00")
	private Double quantity = 0.00;

	@Column(
		name = AsterProductContract.DESCRIPTION,
		nullable = false,
		columnDefinition = "TEXT"
	)
	@FullTextField
	private String description;

	@Column(
		name = AsterProductContract.CARE,
		nullable = false,
		columnDefinition = "TEXT"
	)
	@FullTextField
	private String care;

	@Column(
		name = AsterProductContract.SALE,
		columnDefinition = "BOOLEAN"
	)
	@ColumnDefault("FALSE")
	private boolean sale = false;

	@Column(
		name = AsterProductContract.DISCOUNT,
		columnDefinition = "NUMERIC"
	)
	@ColumnDefault("0.00")
	private double discount = 0.00;

	@Column(
		name = AsterProductContract.DISABLED,
		nullable = false,
		columnDefinition = "BOOLEAN"
	)
	@ColumnDefault("false")
	private boolean disabled = false;
}
