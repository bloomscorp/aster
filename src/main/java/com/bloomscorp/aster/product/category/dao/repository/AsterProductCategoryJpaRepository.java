package com.bloomscorp.aster.product.category.dao.repository;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsterProductCategoryJpaRepository<CA extends AsterProductCategory> extends JpaRepository<CA, Long> {
}
