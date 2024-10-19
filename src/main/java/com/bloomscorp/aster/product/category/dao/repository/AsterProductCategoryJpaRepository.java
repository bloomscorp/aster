package com.bloomscorp.aster.product.category.dao.repository;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.support.AsterExclude;
import org.springframework.data.jpa.repository.JpaRepository;

@AsterExclude
public interface AsterProductCategoryJpaRepository<CA extends AsterProductCategory> extends JpaRepository<CA, Long> {
}
