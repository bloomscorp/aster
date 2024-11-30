package com.bloomscorp.aster.cart.orm;

import com.bloomscorp.aster.cart.contract.AsterCartContract;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.orm.AsterProductCollectionMapping;
import com.bloomscorp.aster.product.orm.AsterProductSubCategoryMapping;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.behemoth.orm.BehemothORM;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterCart<
    E extends Enum<E>,
    R extends AsterUserRole<E>,
    T extends NVerseTenant<E, R>,
    CA extends AsterProductCategory,
    SCA extends AsterProductSubCategory,
    CO extends AsterProductCollection,
    P extends AsterProduct<
        CA,
        SCA,
        CO,
        ? extends AsterProductSubCategoryMapping<CA, SCA, CO, ?>,
        ? extends AsterProductCollectionMapping<CA, SCA, CO, ?>
        >,
    CI extends AsterCartItem<CA, SCA, CO, P>
    > extends BehemothORM {

    @Column(
        name = AsterCartContract.CREATED_AT,
        nullable = false
    )
    private Long createdAt;
    @Column(
        name = AsterCartContract.UPDATED_AT
    )
    private Long updatedAt;

    public abstract T getTenant();
    public abstract void setTenant(T tenant);

    public abstract List<CI> getItems();
    public abstract void setItems(List<CI> items);

}
