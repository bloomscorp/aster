package com.bloomscorp.aster.cart.orm;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.behemoth.orm.BehemothORM;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterCart<
    E extends Enum<E>,
    R extends AsterUserRole<E>,
    T extends NVerseTenant<E, R>,
    CA extends AsterProductCategory,
    SCA extends AsterProductSubCategory<CA>,
    CO extends AsterProductCollection,
    P extends AsterProduct<CA, SCA, CO>,
    CI extends AsterCartItem<CA, SCA, CO, P>
    > extends BehemothORM {

    public abstract T getTenant();

    @Type(JsonBinaryType.class)
    public abstract List<CI> getItems();
}
