package com.bloomscorp.aster.cart.orm;

import com.bloomscorp.aster.cart.contract.AsterCartItemContract;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.orm.AsterProductCollectionMapping;
import com.bloomscorp.aster.product.orm.AsterProductImage;
import com.bloomscorp.aster.product.orm.AsterProductSubCategoryMapping;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.support.AsterBehemothORM;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterCartItem<
    CA extends AsterProductCategory,
    SCA extends AsterProductSubCategory,
    CO extends AsterProductCollection,
    P extends AsterProduct<
        CA,
        SCA,
        CO,
        ? extends AsterProductSubCategoryMapping<CA, SCA, CO, ?>,
        ? extends AsterProductCollectionMapping<CA, SCA, CO, ?>,
        ? extends AsterProductImage<CA, SCA, CO, P>
        >
    > extends AsterBehemothORM {

    public abstract P getProduct();
    public abstract void setProduct(P product);

    @Column(
        name = AsterCartItemContract.QUANTITY,
        nullable = false,
        columnDefinition = "NUMERIC"
    )
    @ColumnDefault("0.00")
    private Double quantity = 0.00;

    @Column(
        name = AsterCartItemContract.CREATED_AT,
        nullable = false
    )
    private Long createdAt;

    @Column(
        name = AsterCartItemContract.UPDATED_AT
    )
    private Long updatedAt;

}
