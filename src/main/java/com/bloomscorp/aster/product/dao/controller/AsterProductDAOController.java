package com.bloomscorp.aster.product.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.dao.repository.AsterProductJpaRepository;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;

import java.util.List;

public abstract class AsterProductDAOController<
        CA extends AsterProductCategory,
        SCA extends AsterProductSubCategory<CA>,
        CO extends AsterProductCollection,
        P extends AsterProduct<CA, SCA, CO>,
        R extends AsterProductJpaRepository<CA, SCA, CO, P>
        > extends AsterCRUDDAOController<P, R> {

    public AsterProductDAOController(R repository) {
        super(repository);
    }

    public List<P> retrieveProducts() {
        List<P> products = this.getRepository().findAll();
        return products;
    }
}
