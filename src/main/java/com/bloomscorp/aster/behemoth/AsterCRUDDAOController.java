package com.bloomscorp.aster.behemoth;

import com.bloomscorp.aster.support.AsterBehemothORM;
import com.bloomscorp.behemoth.dao.controller.BehemothCRUDDAOController;
import org.springframework.data.jpa.repository.JpaRepository;

public class AsterCRUDDAOController<
	E extends AsterBehemothORM,
	R extends JpaRepository<E, Long>
> extends BehemothCRUDDAOController<E, R> {
	public AsterCRUDDAOController(R repository) {
		super(repository);
	}
}
