package com.bloomscorp.aster.support;

import com.bloomscorp.behemoth.orm.BehemothORM;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AsterBehemothORM extends BehemothORM {
	
	public void setId(Long id) {
		super.id = id;
	}
}
