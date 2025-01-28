package com.bloomscorp.aster.order.orm;

import com.bloomscorp.aster.order.contract.AsterOrderContract;
import com.bloomscorp.aster.support.AsterBehemothORM;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterOrder<
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        T extends NVerseTenant<E, R>,
        OI extends AsterOrderItem<E, R, T, ?>
        > extends AsterBehemothORM {

    public abstract T getTenant();
    public abstract void setTenant(T tenant);

    public abstract List<OI> getOrderItemList();
    public abstract void setOrderItemList(List<OI> orderItemList);

    @Column(
            name = AsterOrderContract.ADDRESS,
            nullable = false,
            columnDefinition = "JSONB"
    )
    @JdbcTypeCode(SqlTypes.JSON)
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
    @JdbcTypeCode(SqlTypes.JSON)
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
