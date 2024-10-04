package com.bloomscorp.aster.product.collection.orm;

import com.bloomscorp.aster.product.collection.contract.AsterProductCollectionContract;
import com.bloomscorp.behemoth.orm.BehemothORM;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterProductCollection extends BehemothORM {

    @Column(
            name = AsterProductCollectionContract.NAME,
            nullable = false
    )
    private String name;

    @Column(
            name = AsterProductCollectionContract.ICON
    )
    private String icon;

    @Column(
            name = AsterProductCollectionContract.CREATED_AT,
            nullable = false
    )
    private Long createdAt;
}
