package com.bloomscorp.aster.tenant.orm;

import com.bloomscorp.aster.support.AsterBehemothORM;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class AsterCustomer<
    E extends Enum<E>,
    R extends AsterUserRole<E>,
    T extends NVerseTenant<E, R>
> extends AsterBehemothORM {
    public abstract T getTenant();
}
