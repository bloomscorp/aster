package com.bloomscorp.aster.cart.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.cart.dao.repository.AsterCartJpaRepository;
import com.bloomscorp.aster.cart.orm.AsterCart;
import com.bloomscorp.aster.cart.orm.AsterCartItem;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.orm.AsterProductCollectionMapping;
import com.bloomscorp.aster.product.orm.AsterProductImage;
import com.bloomscorp.aster.product.orm.AsterProductSubCategoryMapping;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;

public class AsterCartDAOController<
    E extends Enum<E>,
    R extends AsterUserRole<E>,
    T extends NVerseTenant<E, R>,
    CA extends AsterProductCategory,
    SCA extends AsterProductSubCategory,
    CO extends AsterProductCollection,
    P extends AsterProduct<
        CA,
        SCA,
        CO,
        ? extends AsterProductSubCategoryMapping<CA, SCA, CO, ?>,
        ? extends AsterProductCollectionMapping<CA, SCA, CO, ?>,
        ? extends AsterProductImage<CA, SCA, CO, P>
        >,
    CI extends AsterCartItem<CA, SCA, CO, P>,
    CT extends AsterCart<E, R, T, CA, SCA, CO, P, CI>,
    J extends AsterCartJpaRepository<E, R, T, CA, SCA, CO, P, CI, CT>
    > extends AsterCRUDDAOController<CT, J> {

    public AsterCartDAOController(J repository) {
        super(repository);
    }

    public CT getCartByTenant(T tenant) {
        return this.getRepository().findByTenant(tenant);
    }
}
