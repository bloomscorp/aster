package com.bloomscorp.aster.product.category.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.product.category.dao.repository.AsterProductCategoryJpaRepository;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;

public abstract class AsterProductCategoryDAOController<
        CA extends AsterProductCategory,
        J extends AsterProductCategoryJpaRepository<CA>
        > extends AsterCRUDDAOController<CA, J> {

    public AsterProductCategoryDAOController(J repository) {
        super(repository);
    }
}
