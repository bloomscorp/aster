package com.bloomscorp.aster.cart.orm;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.orm.AsterProductCollectionMapping;
import com.bloomscorp.aster.product.orm.AsterProductSubCategoryMapping;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.behemoth.orm.BehemothORM;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

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

    public abstract T getTenant();

    @JdbcTypeCode(SqlTypes.JSON)
    public abstract List<CI> getItems();
}
