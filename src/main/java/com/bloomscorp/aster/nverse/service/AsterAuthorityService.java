package com.bloomscorp.aster.nverse.service;

import com.bloomscorp.aster.nverse.AsterAuthority;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.bloomscorp.aster.tenant.orm.USER_ROLE.*;

public class AsterAuthorityService {

	@NotNull
	@Contract("_ -> new")
	public static <
		R extends AsterUserRole<?>
	> AsterAuthority prepareAuthority(
		@NotNull List<R> roles
	) {
		return new AsterAuthority(
			roles.stream().parallel().map(R::getRole).anyMatch(e -> e == ROLE_SUPER_USER),
			roles.stream().parallel().map(R::getRole).anyMatch(e -> e == ROLE_ADMINISTRATOR),
			roles.stream().parallel().map(R::getRole).anyMatch(e -> e == ROLE_CUSTOMER),
			roles.stream().parallel().map(R::getRole).anyMatch(e -> e == ROLE_ACCOUNTANT),
			roles.stream().parallel().map(R::getRole).anyMatch(e -> e == ROLE_DELIVERY_MANAGER)
		);
	}
}
