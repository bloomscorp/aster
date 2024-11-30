package com.bloomscorp.aster.alfred.orm;

import com.bloomscorp.alfred.contract.LogContract;
import com.bloomscorp.alfred.orm.LOG_TYPE;
import com.bloomscorp.alfred.orm.Log;
import com.bloomscorp.aster.support.Constant;
import com.bloomscorp.behemoth.orm.BehemothORM;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

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
	@JdbcTypeCode(SqlTypes.NAMED_ENUM)
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
