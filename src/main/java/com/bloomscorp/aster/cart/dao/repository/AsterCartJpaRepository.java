package com.bloomscorp.aster.cart.dao.repository;

import com.bloomscorp.aster.cart.orm.AsterCart;
import com.bloomscorp.aster.cart.orm.AsterCartItem;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.orm.AsterProductCollectionMapping;
import com.bloomscorp.aster.product.orm.AsterProductSubCategoryMapping;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.support.AsterExclude;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import org.springframework.data.jpa.repository.JpaRepository;

@AsterExclude
public interface AsterCartJpaRepository<
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
        ? extends AsterProductCollectionMapping<CA, SCA, CO, ?>
        >,
    CI extends AsterCartItem<CA, SCA, CO, P>,
    CT extends AsterCart<E, R, T, CA, SCA, CO, P, CI>> extends JpaRepository<CT, Long> {

    public CT findByTenant(T tenant);
}
