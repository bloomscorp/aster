package com.bloomscorp.aster.nverse;

import com.bloomscorp.aster.tenant.orm.AsterTenant;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.aster.tenant.orm.USER_ROLE;
import com.bloomscorp.nverse.NVerseGatekeeper;

public abstract class AsterGatekeeper<
	T extends AsterTenant<E, R>,
	E extends Enum<E>,
	R extends AsterUserRole<E>,
	S extends AsterSecureLayer<T, E, R>
> extends NVerseGatekeeper<T, E, R> {

	public static final int CODE_SU = 1;
	public static final int CODE_AD = 2;
	public static final int CODE_CU = 3;
	public static final int CODE_AC = 4;
	public static final int CODE_DM = 5;
	public static final int CODE_SUADCUACDM = 6;

	public AsterGatekeeper(S secureLayer) {
		super(secureLayer);
	}

	private boolean roleAD(T tenant) {
		return this.roleGOD(tenant) ||
			tenant.getRoles()
				.stream()
				.parallel()
				.map(R::getRole)
				.anyMatch(e -> e == USER_ROLE.ROLE_ADMINISTRATOR);
	}

	private boolean roleCU(T tenant) {
		return this.roleGOD(tenant) ||
			tenant.getRoles()
				.stream()
				.parallel()
				.map(R::getRole)
				.anyMatch(e -> e == USER_ROLE.ROLE_CUSTOMER);
	}

	private boolean roleAC(T tenant) {
		return this.roleGOD(tenant) ||
			tenant.getRoles()
				.stream()
				.parallel()
				.map(R::getRole)
				.anyMatch(e -> e == USER_ROLE.ROLE_ACCOUNTANT);
	}

	private boolean roleDM(T tenant) {
		return this.roleGOD(tenant) ||
			tenant.getRoles()
				.stream()
				.parallel()
				.map(R::getRole)
				.anyMatch(e -> e == USER_ROLE.ROLE_DELIVERY_MANAGER);
	}

	private boolean roleSUADCUACDM(T tenant) {
		return this.roleGOD(tenant) ||
			this.roleSU(tenant)		||
			this.roleAD(tenant)		||
			this.roleCU(tenant)		||
			this.roleAC(tenant)		||
			this.roleDM(tenant);
	}

	@Override
	public boolean userHasAppropriateAuthority(T user, int code) {
		return switch (code) {
			case AsterGatekeeper.CODE_SU -> this.roleSU(user);
			case AsterGatekeeper.CODE_AD -> this.roleAD(user);
			case AsterGatekeeper.CODE_CU -> this.roleCU(user);
			case AsterGatekeeper.CODE_AC -> this.roleAC(user);
			case AsterGatekeeper.CODE_DM -> this.roleDM(user);
			case AsterGatekeeper.CODE_SUADCUACDM -> this.roleSUADCUACDM(user);
			default -> false;
		};
	}
}
