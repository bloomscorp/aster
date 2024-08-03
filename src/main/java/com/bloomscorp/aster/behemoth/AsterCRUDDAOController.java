package com.bloomscorp.aster.behemoth;

import com.bloomscorp.behemoth.dao.controller.BehemothCRUDDAOController;
import com.bloomscorp.behemoth.orm.BehemothORM;
import org.springframework.data.jpa.repository.JpaRepository;

public class AsterCRUDDAOController<
	E extends BehemothORM,
	R extends JpaRepository<E, Long>
> extends BehemothCRUDDAOController<E, R> {
	public AsterCRUDDAOController(R repository) {
		super(repository);
	}
}
