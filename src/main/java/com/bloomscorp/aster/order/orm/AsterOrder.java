package com.bloomscorp.aster.order.orm;

import com.bloomscorp.aster.order.contract.AsterOrderContract;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.behemoth.orm.BehemothORM;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import java.util.List;

public abstract class AsterOrder<
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        T extends NVerseTenant<E, R>
        > extends BehemothORM {

    public abstract T getTenant();

    public abstract List<AsterOrderItem<E, R, T, AsterOrder<E, R, T>>> getOrderItemList();
    public abstract void setOrderItemList(List<AsterOrderItem<E, R, T, AsterOrder<E, R, T>>> orderItemList);

    @Column(
            name = AsterOrderContract.ADDRESS,
            nullable = false,
            columnDefinition = "JSONB"
    )
    @Type(JsonBinaryType.class)
    private Object address;

    @Column(
            name = AsterOrderContract.TOTAL,
            columnDefinition = "NUMERIC",
            nullable = false
    )
    private double total;

    @Column(
            name = AsterOrderContract.TRANSACTION,
            nullable = false,
            columnDefinition = "JSONB"
    )
    @Type(JsonBinaryType.class)
    private Object transaction;

    @Column(
            name = AsterOrderContract.DELETED,
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    @ColumnDefault("false")
    private Boolean deleted = false;

    @Column(
            name = AsterOrderContract.CREATED_AT,
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Long createdAt;

}
