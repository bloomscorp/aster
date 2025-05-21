package com.bloomscorp.aster.tenant.dao.controller;

import com.bloomscorp.aster.support.AsterBehemothORM;
import com.bloomscorp.behemoth.dao.controller.BehemothCRUDDAOController;
import com.bloomscorp.hastar.code.ActionCode;
import com.bloomscorp.nverse.dao.NVerseUserRoleDAO;
import com.bloomscorp.nverse.pojo.NVerseRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.drew.lang.annotations.NotNull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class AsterUserRoleDAOController<
    R extends AsterBehemothORM & NVerseRole<E>,
    J extends JpaRepository<R, Long>,
    E extends Enum<E>
> extends BehemothCRUDDAOController<R, J> implements NVerseUserRoleDAO<E, R> {

    public AsterUserRoleDAOController(J repository) {
        super(repository);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int addNewRole(R role) {
        role = this.getRepository().saveAndFlush(role);
        return (role.getId() > 0) ? ActionCode.INSERT_SUCCESS : ActionCode.INSERT_FAILURE;
    }
}
