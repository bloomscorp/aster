package com.bloomscorp.aster.product.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.dao.repository.AsterProductImageJpaRepository;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.orm.AsterProductCollectionMapping;
import com.bloomscorp.aster.product.orm.AsterProductImage;
import com.bloomscorp.aster.product.orm.AsterProductSubCategoryMapping;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;

public class AsterProductImageDAOController<
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
	PIMG extends AsterProductImage<CA, SCA, CO, P>,
	J extends AsterProductImageJpaRepository<CA, SCA, CO, P, PIMG>
	> extends AsterCRUDDAOController<PIMG, J> {
	
	public AsterProductImageDAOController(J repository) {
		super(repository);
	}
}
