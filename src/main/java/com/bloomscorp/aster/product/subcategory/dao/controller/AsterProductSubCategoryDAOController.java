package com.bloomscorp.aster.product.subcategory.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.subcategory.dao.repository.AsterProductSubCategoryJpaRepository;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;

import java.util.List;

public abstract class AsterProductSubCategoryDAOController<
        CA extends AsterProductCategory,
        SCA extends AsterProductSubCategory<CA>,
        J extends AsterProductSubCategoryJpaRepository<CA, SCA>
        > extends AsterCRUDDAOController<SCA, J> {

    public AsterProductSubCategoryDAOController(J repository) {
        super(repository);
    }

    public List<SCA> findByCategory(CA category) {
        return this.getRepository().findByCategory(category);
    }
}
