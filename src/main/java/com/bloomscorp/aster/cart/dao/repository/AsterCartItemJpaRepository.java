package com.bloomscorp.aster.cart.dao.repository;

import com.bloomscorp.aster.cart.orm.AsterCartItem;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.support.AsterExclude;
import org.springframework.data.jpa.repository.JpaRepository;

@AsterExclude
public interface AsterCartItemJpaRepository<
    CA extends AsterProductCategory,
    SCA extends AsterProductSubCategory<CA>,
    CO extends AsterProductCollection,
    P extends AsterProduct<CA, SCA, CO>,
    CI extends AsterCartItem<CA, SCA, CO, P>> extends JpaRepository<CI, Long> {
}
