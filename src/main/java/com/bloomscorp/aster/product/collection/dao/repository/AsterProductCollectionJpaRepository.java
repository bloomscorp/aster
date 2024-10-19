package com.bloomscorp.aster.product.collection.dao.repository;

import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import com.bloomscorp.aster.support.AsterExclude;
import org.springframework.data.jpa.repository.JpaRepository;

@AsterExclude
public interface AsterProductCollectionJpaRepository<CO extends AsterProductCollection> extends JpaRepository<CO, Long> {
}
