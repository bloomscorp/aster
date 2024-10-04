package com.bloomscorp.aster.restful;

import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.support.ResponseParameter;
import com.bloomscorp.raintree.RainTree;
import com.bloomscorp.raintree.restful.RainResponse;

import java.util.List;

public class AsterProductCollectionResponse<
        CO extends AsterProductCollection
        > extends RainResponse<CO> {

    public AsterProductCollectionResponse(RainTree rainTree) {
        super(rainTree);
    }

    @Override
    public String buildEntity(CO co) {
        return this.prepareEntity(co, ResponseParameter.PRODUCT_COLLECTION);
    }

    @Override
    public String buildList(List<CO> list) {
        return this.prepareList(list, ResponseParameter.PRODUCT_COLLECTION_LIST);
    }
}
