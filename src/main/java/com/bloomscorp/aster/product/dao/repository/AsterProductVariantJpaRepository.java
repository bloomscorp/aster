package com.bloomscorp.aster.product.dao.repository;

import com.bloomscorp.aster.product.orm.AsterProductVariant;
import com.bloomscorp.aster.support.AsterExclude;
import org.springframework.data.jpa.repository.JpaRepository;

@AsterExclude
public interface AsterProductVariantJpaRepository<
	V extends AsterProductVariant
	> extends JpaRepository<V, Long> {
}
