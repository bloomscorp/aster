package com.bloomscorp.aster.product.category.orm;

import com.bloomscorp.aster.product.category.contract.AsterProductCategoryContract;
import com.bloomscorp.behemoth.orm.BehemothORM;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterProductCategory extends BehemothORM {

    @Column(
        name = AsterProductCategoryContract.NAME,
        nullable = false
    )
    private String name;

    @Column(
        name = AsterProductCategoryContract.ICON
    )
    private String icon;

    @Column(
        name = AsterProductCategoryContract.CREATED_AT,
        nullable = false
    )
    private Long createdAt;
}
