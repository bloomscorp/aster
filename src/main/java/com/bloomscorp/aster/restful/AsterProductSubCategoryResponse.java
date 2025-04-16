package com.bloomscorp.aster.restful;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.support.ResponseParameter;
import com.bloomscorp.raintree.RainTree;
import com.bloomscorp.raintree.restful.RainResponse;

import java.util.List;

public abstract class AsterProductSubCategoryResponse<
        CA extends AsterProductCategory,
        SCA extends AsterProductSubCategory
        > extends RainResponse<SCA> {

    public AsterProductSubCategoryResponse(RainTree rainTree) {
        super(rainTree);
    }

    @Override
    public String buildEntity(SCA sca) {
        return this.prepareEntity(sca, ResponseParameter.SUB_CATEGORY);
    }

    @Override
    public String buildList(List<SCA> list) {
        return this.prepareList(list, ResponseParameter.SUB_CATEGORY_LIST);
    }
}
