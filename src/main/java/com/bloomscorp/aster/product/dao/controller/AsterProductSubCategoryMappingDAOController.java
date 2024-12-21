package com.bloomscorp.aster.product.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.dao.repository.AsterProductSubCategoryMappingJpaRepository;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.orm.AsterProductCollectionMapping;
import com.bloomscorp.aster.product.orm.AsterProductSubCategoryMapping;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;

public class AsterProductSubCategoryMappingDAOController<
	CA extends AsterProductCategory,
	SCA extends AsterProductSubCategory,
	CO extends AsterProductCollection,
	P extends AsterProduct<
		CA,
		SCA,
		CO,
		? extends AsterProductSubCategoryMapping<CA, SCA, CO, ?>,
		? extends AsterProductCollectionMapping<CA, SCA, CO, ?>
		>,
	SCAM extends AsterProductSubCategoryMapping<CA, SCA, CO, P>,
	R extends AsterProductSubCategoryMappingJpaRepository<CA, SCA, CO, P, SCAM>
	> extends AsterCRUDDAOController<SCAM, R> {
	
	public AsterProductSubCategoryMappingDAOController(R repository) {
		super(repository);
	}
}
