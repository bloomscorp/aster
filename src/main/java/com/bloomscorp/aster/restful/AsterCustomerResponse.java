package com.bloomscorp.aster.restful;

import com.bloomscorp.aster.support.ResponseParameter;
import com.bloomscorp.aster.tenant.orm.AsterCustomer;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.raintree.RainTree;
import com.bloomscorp.raintree.restful.RainResponse;

import java.util.List;

public abstract class AsterCustomerResponse<
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	T extends NVerseTenant<E, R>,
	C extends AsterCustomer<E, R, T>
	> extends RainResponse<C> {
	
	public AsterCustomerResponse(RainTree rainTree) {
		super(rainTree);
	}
	
	@Override
	public String buildEntity(C c) {
		return this.prepareEntity(c, ResponseParameter.CUSTOMER);
	}
	
	@Override
	public String buildList(List<C> list) {
		return this.prepareList(list, ResponseParameter.CUSTOMER_LIST);
	}
}
