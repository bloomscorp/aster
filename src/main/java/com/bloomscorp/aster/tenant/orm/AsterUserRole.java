package com.bloomscorp.aster.tenant.orm;

import com.bloomscorp.behemoth.orm.BehemothORM;
import com.bloomscorp.nverse.pojo.NVerseRole;

public abstract class AsterUserRole<E extends Enum<E>> extends BehemothORM implements NVerseRole<E> {
	public abstract E getRole();
}
