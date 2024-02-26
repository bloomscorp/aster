package com.bloomscorp.aster.alfred.orm;

import com.bloomscorp.alfred.contract.AuthenticationLogContract;
import com.bloomscorp.alfred.orm.AUTH_ACTION_ENUM;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.support.Constant;
import com.bloomscorp.aster.tenant.orm.TenantFacade;
import com.bloomscorp.behemoth.orm.BehemothORM;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = AuthenticationLogContract.TABLE)
@Table(name = AuthenticationLogContract.TABLE)
public class AsterAuthenticationLog extends BehemothORM implements AuthenticationLog {

	@Enumerated(EnumType.STRING)
	@Column(
		name = AuthenticationLogContract.ACTION,
		columnDefinition = "auth_action_enum",
		nullable = false
	)
	@Type(PostgreSQLEnumType.class)
	private AUTH_ACTION_ENUM action;

	@ToString.Exclude
	@ManyToOne(
		targetEntity = TenantFacade.class,
		fetch = FetchType.EAGER
	)
	@JoinColumn(
		name = AuthenticationLogContract.USER_ID,
		columnDefinition = "BIGSERIAL",
		nullable = false,
		foreignKey = @ForeignKey(
			name = "fk_user_id"
		)
	)
	private TenantFacade tenant;

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
	@Builder.Default
	private short attempt = 1;

	@Column(
		name = AuthenticationLogContract.INFORMATION,
		columnDefinition = "TEXT",
		nullable = false
	)
	@Builder.Default
	@ColumnDefault(Constant.NO_INFORMATION)
	private String information = Constant.NO_INFORMATION;
}
