package com.bloomscorp.aster.product.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.dao.repository.AsterProductJpaRepository;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.orm.AsterProductCollectionMapping;
import com.bloomscorp.aster.product.orm.AsterProductImage;
import com.bloomscorp.aster.product.orm.AsterProductSubCategoryMapping;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AsterProductDAOController<
        CA extends AsterProductCategory,
        SCA extends AsterProductSubCategory,
        CO extends AsterProductCollection,
        P extends AsterProduct<
            CA,
            SCA,
            CO,
            ? extends AsterProductSubCategoryMapping<CA, SCA, CO, ?>,
            ? extends AsterProductCollectionMapping<CA, SCA, CO, ?>,
            ? extends AsterProductImage<CA, SCA, CO, P>
            >,
        R extends AsterProductJpaRepository<CA, SCA, CO, P>
        > extends AsterCRUDDAOController<P, R> {

    public AsterProductDAOController(R repository) {
        super(repository);
    }

    public List<P> retrieveProducts() {
        List<P> products = this.getRepository().findAll();
        return products;
    }

    public P retrieveProductBySlug(String slug) {
        return this.getRepository().findProductBySlug(slug);
    }

    public abstract void prepareProduct(P product);

    public List<P> prepareProducts(@NotNull List<P> products) {
        products.forEach(this::prepareProduct);
        return products;
    };
}
