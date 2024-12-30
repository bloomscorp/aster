package com.bloomscorp.aster.tenant.dao.controller;

import com.bloomscorp.aster.support.AsterBehemothORM;
import com.bloomscorp.behemoth.dao.controller.BehemothCRUDDAOController;
import com.bloomscorp.nverse.dao.NVerseTenantDAO;
import com.bloomscorp.nverse.pojo.NVerseRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AsterTenantDAOController<
	B extends AsterBehemothORM,
	J extends JpaRepository<B, Long>,
	T extends NVerseTenant<E, R>,
	E extends Enum<E>,
	R extends NVerseRole<E>
> extends BehemothCRUDDAOController<B, J> implements NVerseTenantDAO<T, E, R> {
	public AsterTenantDAOController(J repository) {
		super(repository);
	}
}
