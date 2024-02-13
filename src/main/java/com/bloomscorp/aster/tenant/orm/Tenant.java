package com.bloomscorp.aster.tenant.orm;

import com.bloomscorp.aster.tenant.contract.TenantContract;
import com.bloomscorp.behemoth.orm.BehemothORM;
import com.bloomscorp.nverse.pojo.NVERSE_AUTH_PROVIDER;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@MappedSuperclass
public abstract class Tenant<
	E extends Enum<E>,
	R extends UserRole<E>
> extends BehemothORM implements NVerseTenant<E, R> {

	@Column(
		name = TenantContract.ASTER_ID,
		columnDefinition = "VARCHAR",
		length = 22,
		nullable = false
	)
	private String uid;

	@Column(
		name = TenantContract.EMAIL,
		columnDefinition = "VARCHAR",
		length = 150,
		nullable = false
	)
	private String email;

	@Column(
		name = TenantContract.EMAIL_VERIFIED,
		columnDefinition = "BOOLEAN",
		nullable = false
	)
	@ColumnDefault("false")
	private boolean emailVerified = false;

	@Column(
		name = TenantContract.CONTACT,
		columnDefinition = "VARCHAR",
		length = 20,
		nullable = false
	)
	@ColumnDefault("''")
	private String contactNumber = "";

	@Column(
		name = TenantContract.CONTACT_VERIFIED,
		columnDefinition = "BOOLEAN",
		nullable = false
	)
	@ColumnDefault("false")
	private boolean contactNumberVerified = false;

	@Column(
		name = TenantContract.PASSWORD,
		columnDefinition = "VARCHAR",
		length = 120,
		nullable = false
	)
	private String password;

	@Column(
		name = TenantContract.CREATION_TIME,
		columnDefinition = "BIGINT",
		nullable = false
	)
	private long creationTime;

	@Column(
		name = TenantContract.NAME,
		columnDefinition = "VARCHAR",
		length = 150,
		nullable = false
	)
	private String name;

	@Column(
		name = TenantContract.DOB,
		columnDefinition = "BIGINT"
	)
	@ColumnDefault("0")
	private Long dob = 0L;

	// create an "gender_enum" at the database level
	@Enumerated(EnumType.STRING)
	@Column(
		name = TenantContract.GENDER,
		columnDefinition = "gender_enum",
		nullable = false
	)
	@Type(PostgreSQLEnumType.class)
	private GENDER gender;

	@Column(
		name = TenantContract.LAST_ACCESS_TIME,
		columnDefinition = "BIGINT"
	)
	@ColumnDefault("0")
	@Min(
		value = 0,
		message = "Last access time cannot be less than 0"
	)
	private Long lastAccessTime;

	@Column(
		name = TenantContract.PROFILE_IMAGE_URL,
		columnDefinition = "VARCHAR",
		nullable = false
	)
	@ColumnDefault("default-profile-picture.svg")
	private String profileImageUrl = "default-profile-picture.svg";

	@Column(
		name = TenantContract.ACTIVE,
		columnDefinition = "BOOLEAN",
		nullable = false
	)
	@ColumnDefault("true")
	private boolean active = true;

	@Column(
		name = TenantContract.SUSPENDED,
		columnDefinition = "BOOLEAN",
		nullable = false
	)
	@ColumnDefault("false")
	private boolean suspended = false;

	@Column(
		name = TenantContract.BANNED,
		columnDefinition = "BOOLEAN",
		nullable = false
	)
	@ColumnDefault("false")
	private boolean banned = false;

	@Column(
		name = TenantContract.BAN_DATE,
		columnDefinition = "BIGINT"
	)
	@ColumnDefault("0")
	private Long banDate = 0L;

	@Column(
		name = TenantContract.BAN_UPLIFT_DATE,
		columnDefinition = "BIGINT"
	)
	@ColumnDefault("0")
	private Long banUpliftDate = 0L;

	@Column(
		name = TenantContract.DELETED,
		columnDefinition = "BOOLEAN",
		nullable = false
	)
	@ColumnDefault("false")
	private boolean deleted = false;

	// create "auth_provider_enum" at the database level
	@Enumerated(EnumType.STRING)
	@Column(
		name = TenantContract.PROVIDER,
		columnDefinition = "auth_provider_enum",
		nullable = false
	)
	@Type(PostgreSQLEnumType.class)
	@ColumnDefault("UNKNOWN")
	private NVERSE_AUTH_PROVIDER provider = NVERSE_AUTH_PROVIDER.UNKNOWN;

	@Transient
	private String decryptedEmail;

	public abstract List<R> getRoles();
}
