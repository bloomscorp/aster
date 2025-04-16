package com.bloomscorp.aster.restful;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.support.ResponseParameter;
import com.bloomscorp.raintree.RainTree;
import com.bloomscorp.raintree.restful.RainResponse;

import java.util.List;

public abstract class AsterProductCategoryResponse<
        CA extends AsterProductCategory
        > extends RainResponse<CA> {

    public AsterProductCategoryResponse(RainTree rainTree) {
        super(rainTree);
    }

    @Override
    public String buildEntity(CA ca) {
        return this.prepareEntity(ca, ResponseParameter.CATEGORY);
    }

    @Override
    public String buildList(List<CA> list) {
        return this.prepareList(list, ResponseParameter.CATEGORY_LIST);
    }
}
