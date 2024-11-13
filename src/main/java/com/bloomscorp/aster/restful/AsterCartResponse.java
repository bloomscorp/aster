package com.bloomscorp.aster.restful;

import com.bloomscorp.aster.cart.orm.AsterCart;
import com.bloomscorp.aster.cart.orm.AsterCartItem;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.orm.AsterProductCollectionMapping;
import com.bloomscorp.aster.product.orm.AsterProductSubCategoryMapping;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.support.ResponseParameter;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.raintree.RainTree;
import com.bloomscorp.raintree.restful.RainResponse;

import java.util.List;

public class AsterCartResponse<
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
    CT extends AsterCart<E, R, T, CA, SCA, CO, P, CI>
    > extends RainResponse<CT> {

    public AsterCartResponse(RainTree rainTree) {
        super(rainTree);
    }

    @Override
    public String buildEntity(CT c) {
        return this.prepareEntity(c, ResponseParameter.CART);
    }

    @Override
    public String buildList(List<CT> list) {
        return this.prepareList(list, ResponseParameter.CART_LIST);
    }
}
