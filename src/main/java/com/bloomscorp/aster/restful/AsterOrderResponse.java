package com.bloomscorp.aster.restful;

import com.bloomscorp.aster.order.orm.AsterOrder;
import com.bloomscorp.aster.support.ResponseParameter;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.raintree.RainTree;
import com.bloomscorp.raintree.restful.RainResponse;

import java.util.List;

public class AsterOrderResponse<
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        T extends NVerseTenant<E, R>,
        O extends AsterOrder<E, R, T>
        > extends RainResponse<O> {

    public AsterOrderResponse(RainTree rainTree) {
        super(rainTree);
    }

    @Override
    public String buildEntity(O o) {
        return this.prepareEntity(o, ResponseParameter.ORDER);
    }

    @Override
    public String buildList(List<O> list) {
        return this.prepareList(list, ResponseParameter.ORDER_LIST);
    }
}
