package com.bloomscorp.aster.tenant.dao.repository.accessor;

import com.bloomscorp.nverse.pojo.NVerseRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;

import java.util.Optional;

public interface AsterTenantJpaAccessor<
    T extends NVerseTenant<E, R>,
    E extends Enum<E>,
    R extends NVerseRole<E>
> {
    Optional<T> findByEmail(String email);
    Optional<T> findByContactNumber(String contactNumber);
}