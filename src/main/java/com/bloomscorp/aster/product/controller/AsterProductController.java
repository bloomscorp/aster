package com.bloomscorp.aster.product.controller;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.behemoth.AsterCRUDController;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.dao.controller.AsterProductDAOController;
import com.bloomscorp.aster.product.dao.repository.AsterProductJpaRepository;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.orm.AsterProductCollectionMapping;
import com.bloomscorp.aster.product.orm.AsterProductSubCategoryMapping;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.restful.AsterProductResponse;
import com.bloomscorp.nverse.NVerseAuthorityResolver;
import com.bloomscorp.nverse.NVerseGatekeeper;
import com.bloomscorp.nverse.NVerseHttpRequestWrapper;
import com.bloomscorp.nverse.pojo.NVerseRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;

public abstract class AsterProductController<
        B extends LogBook<AsterLog, A, T, E, R>,
        A extends AuthenticationLog,
        T extends NVerseTenant<E, R>,
        E extends Enum<E>,
        R extends NVerseRole<E>,
        C extends CronManager<B, AsterLog, A, T, E, R>,
        CA extends AsterProductCategory,
        SCA extends AsterProductSubCategory,
        CO extends AsterProductCollection,
        P extends AsterProduct<
            CA,
            SCA,
            CO,
            ? extends AsterProductSubCategoryMapping<CA, SCA, CO, ?>,
            ? extends AsterProductCollectionMapping<CA, SCA, CO, ?>
            >,
        J extends AsterProductJpaRepository<CA, SCA, CO, P>
        > extends AsterCRUDController<B, A, T, E, R, C> {

    private final AsterProductDAOController<CA, SCA, CO, P, J> daoController;
    public final AsterProductResponse<CA, SCA, CO, P> response;

    public AsterProductController(
            RainTree rainTree,
            B logBook,
            C cron,
            NVerseGatekeeper<T, E, R> gatekeeper,
            NVerseAuthorityResolver<T, E, R> authorityResolver,
            HttpRequestDumpSanitizer httpRequestDumpSanitizer,
            AsterProductDAOController<CA, SCA, CO, P, J> daoController,
            AsterProductResponse<CA, SCA, CO, P> response
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

    public String getProductList(NVerseHttpRequestWrapper request) {
        return this.response.buildList(
                this.daoController.retrieveProducts()
        );
    }
}
