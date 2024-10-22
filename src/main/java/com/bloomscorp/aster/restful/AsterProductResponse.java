package com.bloomscorp.aster.restful;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.support.ResponseParameter;
import com.bloomscorp.raintree.RainTree;
import com.bloomscorp.raintree.restful.RainResponse;

import java.util.List;

public abstract class AsterProductResponse<
        CA extends AsterProductCategory,
        SCA extends AsterProductSubCategory,
        CO extends AsterProductCollection,
        P extends AsterProduct<CA, SCA, CO>
        > extends RainResponse<P> {

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
