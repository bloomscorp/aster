package com.bloomscorp.aster.product.orm;

import com.bloomscorp.aster.product.category.orm.AsterProductCategory;
import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.product.subcategory.orm.AsterProductSubCategory;
import com.bloomscorp.behemoth.orm.BehemothORM;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AsterProductCollectionMapping<
	CA extends AsterProductCategory,
	SCA extends AsterProductSubCategory,
	CO extends AsterProductCollection,
	P extends AsterProduct<CA, SCA, CO>
	> extends BehemothORM {
	
	public abstract CO getCollection();
	
	public abstract void setCollection(CO collection);
	
	public abstract P getProduct();
	
	public abstract void setProduct(P product);
	
}
