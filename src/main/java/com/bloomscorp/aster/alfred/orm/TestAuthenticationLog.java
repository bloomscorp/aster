package com.bloomscorp.aster.alfred.orm;

import com.bloomscorp.alfred.contract.AuthenticationLogContract;
import com.bloomscorp.alfred.orm.AUTH_ACTION_ENUM;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.support.Constant;
import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.aster.tenant.orm.TenantFacade;
import com.bloomscorp.behemoth.orm.BehemothORM;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
@Builder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class TestAuthenticationLog<
	T extends AsterTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>
> extends BehemothORM implements AuthenticationLog {

	@Enumerated(EnumType.STRING)
	@Column(
		name = AuthenticationLogContract.ACTION,
		columnDefinition = "auth_action_enum",
		nullable = false
	)
	@Type(PostgreSQLEnumType.class)
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