package com.bloomscorp.aster.alfred.orm;

import com.bloomscorp.alfred.contract.AuthenticationLogContract;
import com.bloomscorp.alfred.orm.AUTH_ACTION_ENUM;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.support.AsterBehemothORM;
import com.bloomscorp.aster.support.Constant;
import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterAuthenticationLog<
	T extends AsterTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>
> extends AsterBehemothORM implements AuthenticationLog {

	@Enumerated(EnumType.STRING)
	@Column(
		name = AuthenticationLogContract.ACTION,
		columnDefinition = "auth_action_enum",
		nullable = false
	)
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
	private AUTH_ACTION_ENUM action;

	@Column(
		name = AuthenticationLogContract.TIME,
		columnDefinition = "BIGINT",
		nullable = false
	)
	private long time;

	@Column(
		name = AuthenticationLogContract.ATTEMPT,
		columnDefinition = "SMALLINT",
		length = 1
	)
	@ColumnDefault("1")
	private short attempt = 1;

	@Column(
		name = AuthenticationLogContract.INFORMATION,
		columnDefinition = "TEXT",
		nullable = false
	)
	@ColumnDefault(Constant.NO_INFORMATION)
	private String information = Constant.NO_INFORMATION;

	public abstract T getTenant();
}
