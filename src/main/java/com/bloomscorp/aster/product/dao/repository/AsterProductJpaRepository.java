package com.bloomscorp.aster.product.dao.repository;

import com.bloomscorp.aster.product.orm.AsterProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsterProductJpaRepository<P extends AsterProduct> extends JpaRepository<P, Long> {
	P findProductBySlug(String slug);
}
