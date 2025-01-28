package com.bloomscorp.aster.restful;

import com.bloomscorp.aster.support.ResponseParameter;
import com.bloomscorp.aster.tenant.address.orm.AsterAddress;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.raintree.RainTree;
import com.bloomscorp.raintree.restful.RainResponse;

import java.util.List;

public abstract class AsterAddressResponse<
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        T extends NVerseTenant<E, R>,
        A extends AsterAddress<E, R, T>
        > extends RainResponse<A> {

    public AsterAddressResponse(RainTree rainTree) {
        super(rainTree);
    }

    @Override
    public String buildEntity(A a) {
        return this.prepareEntity(a, ResponseParameter.ADDRESS);
    }

    @Override
    public String buildList(List<A> list) {
        return this.prepareList(list, ResponseParameter.ADDRESS_LIST);
    }
}
