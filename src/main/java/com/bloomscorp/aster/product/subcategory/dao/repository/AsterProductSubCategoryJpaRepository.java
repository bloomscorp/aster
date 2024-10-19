package com.bloomscorp.aster.product.subcategory.dao.repository;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.aster.support.AsterExclude;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@AsterExclude
public interface AsterProductSubCategoryJpaRepository<
        CA extends AsterProductCategory,
        SCA extends AsterProductSubCategory<CA>
        > extends JpaRepository<SCA, Long> {

    List<SCA> findByCategory(CA category);
}
