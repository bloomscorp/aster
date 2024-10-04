package com.bloomscorp.aster.product.collection.controller;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.behemoth.AsterCRUDController;
import com.bloomscorp.aster.product.collection.dao.controller.AsterProductCollectionDAOController;
import com.bloomscorp.aster.product.collection.dao.repository.AsterProductCollectionJpaRepository;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.restful.AsterProductCollectionResponse;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.NVerseAuthorityResolver;
import com.bloomscorp.nverse.NVerseGatekeeper;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;

public abstract class AsterProductCollectionController<
        B extends LogBook<AsterLog, A, T, E, R>,
        A extends AuthenticationLog,
        T extends NVerseTenant<E, R>,
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        C extends CronManager<B, AsterLog, A, T, E, R>,
        CO extends AsterProductCollection,
        J extends AsterProductCollectionJpaRepository<CO>
        > extends AsterCRUDController<B, A, T, E, R, C> {

    private final AsterProductCollectionDAOController<CO, J> daoController;
    private final AsterProductCollectionResponse<CO> response;

    public AsterProductCollectionController(
            RainTree rainTree,
            B logBook,
            C cron,
            NVerseGatekeeper<T, E, R> gatekeeper,
            NVerseAuthorityResolver<T, E, R> authorityResolver,
            HttpRequestDumpSanitizer httpRequestDumpSanitizer,
            AsterProductCollectionDAOController<CO, J> daoController,
            AsterProductCollectionResponse<CO> response
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

    public String getCollectionList() {
        return this.response.buildList(
                this.daoController.retrieveEntityList()
        );
    }
}
