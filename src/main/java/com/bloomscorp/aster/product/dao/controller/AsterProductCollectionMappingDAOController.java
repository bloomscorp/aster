package com.bloomscorp.aster.product.dao.controller;

import com.bloomscorp.aster.behemoth.AsterCRUDDAOController;
import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.dao.repository.AsterProductCollectionMappingJpaRepository;
import com.bloomscorp.aster.product.orm.AsterProduct;
import com.bloomscorp.aster.product.orm.AsterProductCollectionMapping;
import com.bloomscorp.aster.product.orm.AsterProductSubCategoryMapping;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;

public class AsterProductCollectionMappingDAOController<
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
	COM extends AsterProductCollectionMapping<CA, SCA, CO, P>,
	R extends AsterProductCollectionMappingJpaRepository<CA, SCA, CO, P, COM>
	> extends AsterCRUDDAOController<COM, R> {
	
	public AsterProductCollectionMappingDAOController(R repository) {
		super(repository);
	}
}
