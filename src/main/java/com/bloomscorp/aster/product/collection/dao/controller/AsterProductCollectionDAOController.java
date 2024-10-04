package com.bloomscorp.aster.product.collection.dao.controller;


import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.product.collection.dao.repository.AsterProductCollectionJpaRepository;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;

public abstract class AsterProductCollectionDAOController<
        CO extends AsterProductCollection,
        J extends AsterProductCollectionJpaRepository<CO>
        > extends AsterCRUDDAOController<CO, J> {

    public AsterProductCollectionDAOController(J repository) {
        super(repository);
    }
}
