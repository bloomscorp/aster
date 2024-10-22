package com.bloomscorp.aster.product.subcategory.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.subcategory.dao.repository.AsterProductSubCategoryJpaRepository;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;

import java.util.List;

public abstract class AsterProductSubCategoryDAOController<
        SCA extends AsterProductSubCategory,
        J extends AsterProductSubCategoryJpaRepository<SCA>
        > extends AsterCRUDDAOController<SCA, J> {

    public AsterProductSubCategoryDAOController(J repository) {
        super(repository);
    }
}
