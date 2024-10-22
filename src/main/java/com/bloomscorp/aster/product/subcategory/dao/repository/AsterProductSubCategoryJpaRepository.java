package com.bloomscorp.aster.product.subcategory.dao.repository;

import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.support.AsterExclude;
import org.springframework.data.jpa.repository.JpaRepository;

@AsterExclude
public interface AsterProductSubCategoryJpaRepository<
	SCA extends AsterProductSubCategory
	> extends JpaRepository<SCA, Long> {
}
