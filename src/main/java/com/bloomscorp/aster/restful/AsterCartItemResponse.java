package com.bloomscorp.aster.restful;

import com.bloomscorp.aster.cart.orm.AsterCartItem;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.support.ResponseParameter;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.raintree.RainTree;
import com.bloomscorp.raintree.restful.RainResponse;

import java.util.List;

public class AsterCartItemResponse<
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        T extends NVerseTenant<E, R>,
        P extends AsterProduct,
        C extends AsterCartItem<E, R, T, P>
        > extends RainResponse<C> {

    public AsterCartItemResponse(RainTree rainTree) {
        super(rainTree);
    }

    @Override
    public String buildEntity(C c) {
        return this.prepareEntity(c, ResponseParameter.CART);
    }

    @Override
    public String buildList(List<C> list) {
        return this.prepareList(list, ResponseParameter.CART_LIST);
    }
}
