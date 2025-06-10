package com.bloomscorp.aster.order.orm;

import com.bloomscorp.aster.order.contract.AsterOrderContract;
import com.bloomscorp.aster.support.AsterBehemothORM;
import com.bloomscorp.aster.tenant.address.orm.ADDRESS_TYPE;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
        OI extends AsterOrderItem<E, R, T>
        > extends AsterBehemothORM {

    public abstract T getTenant();
    public abstract void setTenant(T tenant);

    public abstract List<OI> getOrderItemList();
    public abstract void setOrderItemList(List<OI> orderItemList);

    @Column(
            name = AsterOrderContract.SHIPPING_ADDRESS,
            nullable = false,
            columnDefinition = "JSONB"
    )
    @JdbcTypeCode(SqlTypes.JSON)
    private Object shippingAddress;
    
    @Column(
        name = AsterOrderContract.DELIVERY_ADDRESS,
        nullable = false,
        columnDefinition = "JSONB"
    )
    @JdbcTypeCode(SqlTypes.JSON)
    private Object deliveryAddress;
    
    @Column(
        name = AsterOrderContract.SUB_TOTAL,
        columnDefinition = "NUMERIC",
        nullable = false
    )
    private double subTotal;

    @Column(
            name = AsterOrderContract.TOTAL,
            columnDefinition = "NUMERIC",
            nullable = false
    )
    private double total;

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
    
    @Column(
            name = AsterOrderContract.STATUS,
            nullable = false,
            columnDefinition = "VARCHAR"
    )
    private String status;
    
    // TODO: create a "payment_status_enum" at the database level
    @Enumerated(EnumType.STRING)
    @Column(
        name = AsterOrderContract.PAYMENT_STATUS,
        nullable = false,
        columnDefinition = "payment_status_enum"
    )
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @ColumnDefault("'PENDING'")
    public PAYMENT_STATUS paymentStatus = PAYMENT_STATUS.PENDING;
    
    @Column(
            name = AsterOrderContract.FAILED_ERROR_CODE,
            columnDefinition = "VARCHAR"
    )
    private String failedErrorCode;
    
    @Column(
            name = AsterOrderContract.FAILED_ERROR_MESSAGE,
            columnDefinition = "VARCHAR"
    )
    private String failedErrorMessage;
    
    @Column(
            name = AsterOrderContract.CANCELLED_AT,
            columnDefinition = "BIGINT"
    )
    private Long cancelledAt;
    
    @Column(
            name = AsterOrderContract.CANCELLED_REASON,
            columnDefinition = "VARCHAR"
    )
    private String cancelledReason;

}
