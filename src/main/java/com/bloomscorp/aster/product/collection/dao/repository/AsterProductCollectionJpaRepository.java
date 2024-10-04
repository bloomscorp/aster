package com.bloomscorp.aster.product.collection.dao.repository;

import com.bloomscorp.aster.product.collection.orm.AsterProductCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsterProductCollectionJpaRepository<CO extends AsterProductCollection> extends JpaRepository<CO, Long> {
}
