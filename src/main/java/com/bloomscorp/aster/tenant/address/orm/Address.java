package com.bloomscorp.aster.tenant.address.orm;

import com.bloomscorp.aster.tenant.orm.UserRole;
import com.bloomscorp.aster.tenant.address.contract.AddressContract;
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
public abstract class Address<
	E extends Enum<E>,
	R extends UserRole<E>,
	T extends NVerseTenant<E, R>
	> extends BehemothORM {

	public abstract T getTenant();

	@Column(
		name = AddressContract.NAME,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 150
	)
	public String name;

	@Column(
		name = AddressContract.PRIMARY_PHONE,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 20
	)
	public String primaryPhone;

	@Column(
		name = AddressContract.ALTERNATE_PHONE,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 20
	)
	@ColumnDefault("''")
	public String alternatePhone = "";

	@Column(
		name = AddressContract.ADDRESS_LINE_ONE,
		columnDefinition = "TEXT",
		nullable = false
	)
	public String addressLine1;

	@Column(
		name = AddressContract.ADDRESS_LINE_TWO,
		columnDefinition = "TEXT",
		nullable = false
	)
	@ColumnDefault("''")
	public String addressLine2 = "";

	@Column(
		name = AddressContract.LANDMARK,
		columnDefinition = "VARCHAR",
		nullable = false
	)
	@ColumnDefault("''")
	public String landmark = "";

	@Column(
		name = AddressContract.POSTAL_CODE,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 10
	)
	public String postalCode;

	@Column(
		name = AddressContract.CITY,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 100
	)
	public String city;

	@Column(
		name = AddressContract.STATE,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 100
	)
	public String state;

	@Column(
		name = AddressContract.COUNTRY,
		columnDefinition = "VARCHAR",
		nullable = false,
		length = 100
	)
	public String country;

	// TODO: create a "address_type_enum" at the database level
	@Enumerated(EnumType.STRING)
	@Column(
		name = AddressContract.ADDRESS_TYPE,
		columnDefinition = "address_type_enum",
		nullable = false
	)
	@Type(PostgreSQLEnumType.class)
	@ColumnDefault("'HOME'")
	public ADDRESS_TYPE addressType = ADDRESS_TYPE.PERMANENT;

	@Column(
		name = AddressContract.DEFAULT_ADDRESS,
		columnDefinition = "BOOLEAN",
		nullable = false
	)
	public Boolean defaultAddress;
}
