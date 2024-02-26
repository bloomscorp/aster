package com.bloomscorp.aster.alfred.dao.repository;

import com.bloomscorp.alfred.adapter.ILogBookDAO;
import com.bloomscorp.aster.alfred.orm.AsterAuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.support.Constant;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(Constant.SCOPE_SINGLETON)
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class AsterLogBookRepository implements ILogBookDAO<AsterAuthenticationLog, AsterLog> {

	private final AuthenticationLogJpaRepository authenticationLogJpaRepository;
	private final LogJpaRepository logJpaRepository;

	@Override
	public long insertAuthenticationLog(AsterAuthenticationLog log) {
		return this.authenticationLogJpaRepository.save(log).getId();
	}

	@Override
	public long insertLog(AsterLog log) {
		return this.logJpaRepository.save(log).getId();
	}
}
