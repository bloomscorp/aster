package com.bloomscorp.aster.tenant.address.orm;

import com.bloomscorp.aster.tenant.address.contract.AsterAddressContract;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.behemoth.orm.BehemothORM;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

@Getter
@MappedSuperclass
public abstract class AsterAddress<
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	T extends NVerseTenant<E, R>
	> extends BehemothORM {

	public abstract T getTenant();

	@Column(
		name = AsterAddressContract.NAME,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 150
	)
	public String name;

	@Column(
		name = AsterAddressContract.PRIMARY_PHONE,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 20
	)
	public String primaryPhone;

	@Column(
		name = AsterAddressContract.ALTERNATE_PHONE,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 20
	)
	@ColumnDefault("''")
	public String alternatePhone = "";

	@Column(
		name = AsterAddressContract.ADDRESS_LINE_ONE,
		columnDefinition = "TEXT",
		nullable = false
	)
	public String addressLine1;

	@Column(
		name = AsterAddressContract.ADDRESS_LINE_TWO,
		columnDefinition = "TEXT",
		nullable = false
	)
	@ColumnDefault("''")
	public String addressLine2 = "";

	@Column(
		name = AsterAddressContract.LANDMARK,
		columnDefinition = "VARCHAR",
		nullable = false
	)
	@ColumnDefault("''")
	public String landmark = "";

	@Column(
		name = AsterAddressContract.POSTAL_CODE,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 10
	)
	public String postalCode;

	@Column(
		name = AsterAddressContract.CITY,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 100
	)
	public String city;

	@Column(
		name = AsterAddressContract.STATE,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 100
	)
	public String state;

	@Column(
		name = AsterAddressContract.COUNTRY,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 100
	)
	public String country;

	// TODO: create a "address_type_enum" at the database level
	@Enumerated(EnumType.STRING)
	@Column(
		name = AsterAddressContract.ADDRESS_TYPE,
		columnDefinition = "address_type_enum",
		nullable = false
	)
	@Type(PostgreSQLEnumType.class)
	@ColumnDefault("'HOME'")
	public ADDRESS_TYPE addressType = ADDRESS_TYPE.PERMANENT;

	@Column(
		name = AsterAddressContract.DEFAULT_ADDRESS,
		columnDefinition = "BOOLEAN",
		nullable = false
	)
	public Boolean defaultAddress;
}
