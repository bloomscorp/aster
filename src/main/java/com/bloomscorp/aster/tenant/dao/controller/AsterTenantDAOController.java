package com.bloomscorp.aster.tenant.dao.controller;

import com.bloomscorp.aster.support.AsterBehemothORM;
import com.bloomscorp.aster.support.AsterUtility;
import com.bloomscorp.aster.tenant.dao.repository.accessor.AsterTenantJpaAccessor;
import com.bloomscorp.aster.tenant.orm.AsterUserRole;
import com.bloomscorp.aster.tenant.orm.USER_ROLE;
import com.bloomscorp.behemoth.dao.controller.BehemothCRUDDAOController;
import com.bloomscorp.hastar.code.ActionCode;
import com.bloomscorp.nverse.NVerseEmailEncoder;
import com.bloomscorp.nverse.NVersePasswordEncoder;
import com.bloomscorp.nverse.dao.NVerseTenantDAO;
import com.bloomscorp.nverse.pojo.NVerseRole;
import com.bloomscorp.nverse.pojo.NVerseTenant;
import com.bloomscorp.pastebox.Pastebox;
import com.drew.lang.annotations.NotNull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public abstract class AsterTenantDAOController<
	T extends AsterBehemothORM & NVerseTenant<E, R>,
	J extends JpaRepository<T, Long> & AsterTenantJpaAccessor<T, E, R>,
	E extends Enum<E>,
	R extends AsterBehemothORM & NVerseRole<E>,
	U extends AsterUserRoleDAOController<R, ?, E>
> extends BehemothCRUDDAOController<T, J> implements NVerseTenantDAO<T, E, R> {

	private final NVerseEmailEncoder emailEncoder;
	private final NVersePasswordEncoder passwordEncoder;
	private final U userRoleDAOController;

	public AsterTenantDAOController(
		J repository,
		NVerseEmailEncoder emailEncoder,
		NVersePasswordEncoder passwordEncoder,
		U userRoleDAOController
	) {
		super(repository);
		this.emailEncoder = emailEncoder;
		this.passwordEncoder = passwordEncoder;
		this.userRoleDAOController = userRoleDAOController;
	}

	public boolean verifyUniqueEmail(@NonNull String email, boolean encoded) {
		try {
			return this
				.getRepository()
				.findByEmail(encoded ? email : this.emailEncoder.encode(email))
				.orElse(null) == null;
		} catch (Exception ignored) {
			return false;
		}
	}

	public boolean verifyUniqueContactNumber(@NonNull String contactNumber) {
		try {
			return this
				.getRepository()
				.findByContactNumber(contactNumber)
				.orElse(null) == null;
		} catch (Exception ignored) {
			return false;
		}
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addNewTenant(@NonNull T user, @NotNull R userRole) {
		return this.addNewTenant(user, userRole, false, false);
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addNewTenant(
		@NonNull T user,
		@NotNull R userRole,
		boolean verifyUniqueEmail,
		boolean verifyUniqueContactNumber
	) {

		try {
			user.setEmail(
				this.emailEncoder.encode(
					user.getEmail()
				)
			);
		} catch (
			NoSuchAlgorithmException 	|
			 NoSuchPaddingException 	|
			 InvalidKeyException 		|
			 IllegalBlockSizeException 	|
			 BadPaddingException exception
		) {
			return ActionCode.INSERT_FAILURE;
		}

		if (verifyUniqueEmail && !this.verifyUniqueEmail(user.getEmail(), true))
			return ActionCode.INCORRECT_INFORMATION;

		if (verifyUniqueContactNumber && !this.verifyUniqueContactNumber(user.getContactNumber()))
			return ActionCode.INCORRECT_INFORMATION;

		user.setUid(AsterUtility.buildUID());
		user.setCreationTime(Pastebox.getCurrentTimeInMillis());
		user.setActive(true);
		user.setProfileImageUrl("default-display-picture.svg");
		user.setPassword(
			this.passwordEncoder.encode(
				user.getPassword()
			)
		);

		user = this.getRepository().saveAndFlush(user);

		if (
			(user.getId() > 0) && (
				this.userRoleDAOController.addNewRole(userRole) == ActionCode.INSERT_SUCCESS
			)
		) return ActionCode.INSERT_SUCCESS;

		return ActionCode.INSERT_FAILURE;
	}
}
