package com.bloomscorp.aster.cart.controller;

import com.bloomscorp.alfred.LogBook;
import com.bloomscorp.alfred.cron.CronManager;
import com.bloomscorp.alfred.orm.AuthenticationLog;
import com.bloomscorp.aster.alfred.orm.AsterLog;
import com.bloomscorp.aster.behemoth.AsterCRUDController;
import com.bloomscorp.aster.cart.dao.controller.AsterCartDAOController;
import com.bloomscorp.aster.cart.dao.repository.AsterCartJpaRepository;
import com.bloomscorp.aster.cart.orm.AsterCart;
import com.bloomscorp.aster.cart.orm.AsterCartItem;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.restful.AsterCartResponse;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.NVerseAuthorityResolver;
import com.bloomscorp.nverse.NVerseGatekeeper;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;

public abstract class AsterCartController<
    B extends LogBook<AsterLog, A, T, E, R>,
    A extends AuthenticationLog,
    T extends NVerseTenant<E, R>,
    E extends Enum<E>,
    R extends AsterUserRole<E>,
    C extends CronManager<B, AsterLog, A, T, E, R>,
    CA extends AsterProductCategory,
    SCA extends AsterProductSubCategory,
    CO extends AsterProductCollection,
    P extends AsterProduct<CA, SCA, CO>,
    CI extends AsterCartItem<CA, SCA, CO, P>,
    CT extends AsterCart<E, R, T, CA, SCA, CO, P, CI>,
    J extends AsterCartJpaRepository<E, R, T, CA, SCA, CO, P, CI, CT>
    > extends AsterCRUDController<B, A, T, E, R, C> {

    public final AsterCartResponse<E, R, T, CA, SCA, CO, P, CI, CT> response;
    public final AsterCartDAOController<E, R, T, CA, SCA, CO, P, CI, CT, J> daoController;

    public AsterCartController(
        RainTree rainTree,
        B logBook,
        C cron,
        NVerseGatekeeper<T, E, R> gatekeeper,
        NVerseAuthorityResolver<T, E, R> authorityResolver,
        HttpRequestDumpSanitizer httpRequestDumpSanitizer,
        AsterCartResponse<E, R, T, CA, SCA, CO, P, CI, CT> response,
        AsterCartDAOController<E, R, T, CA, SCA, CO, P, CI, CT, J> daoController
    ) {
        super(
            rainTree,
            logBook,
            cron,
            gatekeeper,
            authorityResolver,
            httpRequestDumpSanitizer
        );
        this.response = response;
        this.daoController = daoController;
    }

    public String getCartItemList(T tenant) {
        return this.response.buildEntity(
            this.daoController.getCartByTenant(tenant)
        );
    }
}
