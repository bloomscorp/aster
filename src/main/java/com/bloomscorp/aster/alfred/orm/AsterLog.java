package com.bloomscorp.aster.alfred.orm;

import com.bloomscorp.alfred.contract.LogContract;
import com.bloomscorp.alfred.orm.LOG_TYPE;
import com.bloomscorp.alfred.orm.Log;
import com.bloomscorp.aster.support.Constant;
import com.bloomscorp.behemoth.orm.BehemothORM;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = LogContract.TABLE)
@Table(name = LogContract.TABLE)
public class AsterLog extends BehemothORM implements Log {

	@Column(
		name = LogContract.LOGGER,
		columnDefinition = "TEXT",
		nullable = false
	)
	private String logger;

	@Enumerated(EnumType.STRING)
	@Column(
		name = LogContract.LOG_TYPE,
		columnDefinition = "log_enum",
		nullable = false
	)
	@Type(PostgreSQLEnumType.class)
	private LOG_TYPE logType;

	@Column(
		name = LogContract.MESSAGE,
		columnDefinition = "TEXT",
		nullable = false
	)
	@ColumnDefault(Constant.NO_INFORMATION)
	private String message;

	@Column(
		name = LogContract.DATA_DUMP,
		columnDefinition = "TEXT",
		nullable = false
	)
	@ColumnDefault(Constant.NO_INFORMATION)
	private String dataDump;

	@Column(
		name = LogContract.TIME,
		columnDefinition = "BIGINT",
		nullable = false
	)
	private long time;
}
