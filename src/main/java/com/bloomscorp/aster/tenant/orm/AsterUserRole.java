package com.bloomscorp.aster.tenant.orm;

import com.bloomscorp.aster.support.AsterBehemothORM;
import com.bloomscorp.nverse.pojo.NVerseRole;

public abstract class AsterUserRole<E extends Enum<E>> extends AsterBehemothORM implements NVerseRole<E> {
	public abstract E getRole();
}
