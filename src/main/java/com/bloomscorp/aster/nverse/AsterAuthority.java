package com.bloomscorp.aster.nverse;

public record AsterAuthority(
	boolean superUser,
	boolean administrator,
	boolean customer,
	boolean accountant,
	boolean deliveryManager
) {
}
