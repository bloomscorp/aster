package com.bloomscorp.aster.order.controller;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.behemoth.AsterCRUDController;
import com.bloomscorp.aster.order.dao.controller.AsterOrderDAOController;
import com.bloomscorp.aster.order.dao.repository.AsterOrderJpaRepository;
import com.bloomscorp.aster.order.orm.AsterOrder;
import com.bloomscorp.aster.restful.AsterOrderResponse;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.NVerseAuthorityResolver;
import com.bloomscorp.nverse.NVerseGatekeeper;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;

public abstract class AsterOrderController<
        B extends LogBook<AsterLog, A, T, E, R>,
        A extends AuthenticationLog,
        T extends NVerseTenant<E, R>,
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        C extends CronManager<B, AsterLog, A, T, E, R>,
        O extends AsterOrder<E, R, T>,
        J extends AsterOrderJpaRepository<E, R, T, O>
        > extends AsterCRUDController<B, A, T, E, R, C> {

    public final AsterOrderDAOController<E, R, T, O, J> daoController;
    public final AsterOrderResponse<E, R, T, O> response;

    public AsterOrderController(
            RainTree rainTree,
            B logBook,
            C cron,
            NVerseGatekeeper<T, E, R> gatekeeper,
            NVerseAuthorityResolver<T, E, R> authorityResolver,
            HttpRequestDumpSanitizer httpRequestDumpSanitizer,
            AsterOrderDAOController<E, R, T, O, J> daoController,
            AsterOrderResponse<E, R, T, O> response
    ) {
        super(
                rainTree,
                logBook,
                cron,
                gatekeeper,
                authorityResolver,
                httpRequestDumpSanitizer
        );

        this.daoController = daoController;
        this.response = response;
    }

    public String getOrderListByTenant(T tenant) {
        return this.response.buildList(
                this.daoController.getOrdersByTenant(tenant)
        );
    }
}
