package com.bloomscorp.aster.product.orm;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.behemoth.orm.BehemothORM;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterProductSubCategoryMapping<
    CA extends AsterProductCategory,
    SCA extends AsterProductSubCategory,
    CO extends AsterProductCollection,
    P extends AsterProduct<
        CA,
        SCA,
        CO,
        ? extends AsterProductSubCategoryMapping<CA, SCA, CO, ?>,
        ? extends AsterProductCollectionMapping<CA, SCA, CO, ?>
        >
    > extends BehemothORM {

    public abstract SCA getSubCategory();

    public abstract void setSubCategory(SCA subCategory);

    public abstract P getProduct();

    public abstract void setProduct(P product);

}
