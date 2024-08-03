package com.bloomscorp.aster.restful;

import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.support.ResponseParameter;
import com.bloomscorp.raintree.RainTree;
import com.bloomscorp.raintree.restful.RainResponse;

import java.util.List;

public abstract class AsterProductResponse<P extends AsterProduct> extends RainResponse<P> {

	public AsterProductResponse(RainTree rainTree) {
		super(rainTree);
	}

	@Override
	public String buildEntity(P product) {
		return this.prepareEntity(product, ResponseParameter.PRODUCT);
	}

	@Override
	public String buildList(List<P> list) {
		return this.prepareList(list, ResponseParameter.PRODUCT_LIST);
	}
}
