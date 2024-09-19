package com.bloomscorp.aster.order.orm;

import com.bloomscorp.aster.order.contract.AsterOrderItemContract;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.behemoth.orm.BehemothORM;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import java.util.LinkedHashMap;

public abstract class AsterOrderItem<
        E extends Enum<E>,
        R extends AsterUserRole<E>,
        T extends NVerseTenant<E, R>,
        O extends AsterOrder<E, R, T>
        > extends BehemothORM {

    public abstract O getOrder();

    @Column(
            name = AsterOrderItemContract.QUANTITY,
            columnDefinition = "NUMERIC",
            nullable = false
    )
    private double quantity;

    @Column(
            name = AsterOrderItemContract.AMOUNT,
            columnDefinition = "NUMERIC",
            nullable = false
    )
    private double amount;

    @Column(
            name = AsterOrderItemContract.UNIT,
            columnDefinition = "VARCHAR",
            nullable = true
    )
    private String unit;

    @Column(
            name = AsterOrderItemContract.PRODUCT,
            columnDefinition = "JSONB",
            nullable = false
    )
    @Type(JsonBinaryType.class)
    @ColumnDefault("'{}'")
    private final Object productDetails = new LinkedHashMap<>();

    @Column(
            name = AsterOrderItemContract.SHIPPING_CODE,
            columnDefinition = "VARCHAR",
            nullable = false
    )
    @ColumnDefault("''")
    private final String shippingCode = "";

    @Column(
            name = AsterOrderItemContract.TRACKING_URL,
            columnDefinition = "VARCHAR",
            nullable = true
    )
    @ColumnDefault("''")
    private String trackingUrl;

    @Column(
            name = AsterOrderItemContract.STATUS,
            columnDefinition = "VARCHAR",
            nullable = false
    )
    private String status;

    @Column(
            name = AsterOrderItemContract.DISPATCHED_ON,
            columnDefinition = "BIGINT",
            nullable = false
    )
    @ColumnDefault("0")
    private final Long dispatchedOn = 0L;

    @Column(
            name = AsterOrderItemContract.ESTIMATED_DELIVERY_FROM,
            columnDefinition = "BIGINT",
            nullable = false
    )
    @ColumnDefault("0")
    private final Long estimatedDeliveryFrom = 0L;

    @Column(
            name = AsterOrderItemContract.ESTIMATED_DELIVERY_TO,
            columnDefinition = "BIGINT",
            nullable = false
    )
    @ColumnDefault("0")
    private final Long estimatedDeliveryTo = 0L;

    @Column(
            name = AsterOrderItemContract.PAYMENT_STATUS,
            columnDefinition = "payment_status_enum",
            nullable = false
    )
    private String paymentStatus;

    @Column(
            name = AsterOrderItemContract.CREATED_AT,
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Long createdAt;

    @Column(
            name = AsterOrderItemContract.UPDATED_AT,
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Long updatedAt;

}
