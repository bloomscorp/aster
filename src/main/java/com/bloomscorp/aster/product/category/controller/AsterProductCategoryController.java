package com.bloomscorp.aster.product.category.controller;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.behemoth.AsterCRUDController;
import com.bloomscorp.aster.product.category.dao.controller.AsterProductCategoryDAOController;
import com.bloomscorp.aster.product.category.dao.repository.AsterProductCategoryJpaRepository;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.restful.AsterProductCategoryResponse;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.NVerseAuthorityResolver;
import com.bloomscorp.nverse.NVerseGatekeeper;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;

public abstract class AsterProductCategoryController<
        B extends LogBook<AsterLog, A, T, E, R>,
        A extends AuthenticationLog,
        T extends NVerseTenant<E, R>,
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        C extends CronManager<B, AsterLog, A, T, E, R>,
        CA extends AsterProductCategory,
        J extends AsterProductCategoryJpaRepository<CA>
        > extends AsterCRUDController<B, A, T, E, R, C> {

    public final AsterProductCategoryResponse<CA> response;
    public final AsterProductCategoryDAOController<CA, J> daoController;

    public AsterProductCategoryController(
            RainTree rainTree,
            B logBook,
            C cron,
            NVerseGatekeeper<T, E, R> gatekeeper,
            NVerseAuthorityResolver<T, E, R> authorityResolver,
            HttpRequestDumpSanitizer httpRequestDumpSanitizer,
            AsterProductCategoryDAOController<CA, J> daoController,
            AsterProductCategoryResponse<CA> response
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

    public String getCategoryList() {
        return this.response.buildList(
                this.daoController.retrieveEntityList()
        );
    }

}
