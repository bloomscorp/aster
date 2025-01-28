package com.bloomscorp.aster.tenant.address.controller;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.behemoth.AsterCRUDController;
import com.bloomscorp.aster.restful.AsterAddressResponse;
import com.bloomscorp.aster.tenant.address.dao.controller.AsterAddressDAOController;
import com.bloomscorp.aster.tenant.address.dao.repository.AsterAddressJpaRepository;
import com.bloomscorp.aster.tenant.address.orm.AsterAddress;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.NVerseAuthorityResolver;
import com.bloomscorp.nverse.NVerseGatekeeper;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;

public abstract class AsterAddressController<
        B extends LogBook<AsterLog, A, T, E, R>,
        A extends AuthenticationLog,
        T extends NVerseTenant<E, R>,
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        C extends CronManager<B, AsterLog, A, T, E, R>,
        AD extends AsterAddress<E, R, T>,
        J extends AsterAddressJpaRepository<E, R, T, AD>
        > extends AsterCRUDController<B, A, T, E, R, C> {

    public final AsterAddressResponse<E, R, T, AD> response;
    public final AsterAddressDAOController<E, R, T, AD, J> daoController;

    public AsterAddressController(
            RainTree rainTree,
            B logBook,
            C cron,
            NVerseGatekeeper<T, E, R> gatekeeper,
            NVerseAuthorityResolver<T, E, R> authorityResolver,
            HttpRequestDumpSanitizer httpRequestDumpSanitizer,
            AsterAddressDAOController<E, R, T, AD, J> daoController,
            AsterAddressResponse<E, R, T, AD> response
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

    public String getAddressList(T tenant) {
        return this.response.buildList(
                this.daoController.getAddressesByTenant(tenant)
        );
    }
}
