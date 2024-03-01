package com.bloomscorp.aster.nverse.service;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

@Getter
public abstract class AsterAuth0Service {

	private final String issuer;
	private final String jwkProviderUrl;

	public AsterAuth0Service(String issuer, String jwkProviderUrl) {
		this.issuer = issuer;
		this.jwkProviderUrl = jwkProviderUrl;
	}

	public JwkProvider defaultJWKProvider() throws MalformedURLException {
		return new JwkProviderBuilder(new URL(this.jwkProviderUrl))
			.cached(10, 24, TimeUnit.HOURS)
			.rateLimited(10, 1, TimeUnit.MINUTES)
			.build();
	}

	public boolean validateToken(
		String token,
		String email
	) {

		JwkProvider provider = null;
		try {
			provider = this.getJWKProvider();
		} catch (MalformedURLException e) {
			return false;
		}

		try {
			DecodedJWT jwt = JWT.decode(token);
			Jwk jwk = provider.get(jwt.getKeyId());

			Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);

			JWTVerifier verifier = JWT.require(algorithm)
				.withIssuer(this.issuer)
				.withClaim("email", email)
				.build();

			return verifier.verify(token) != null;

		} catch (JWTVerificationException | JwkException e) {
			return false;
		}
	}

	@Contract("_ -> new")
	private @NotNull DecodedJWT decodeJWT(String jwt) {
		return JWT.decode(jwt);
	}

	public String getEmailFromToken(String token) {
		return this.decodeJWT(token).getClaim("email").asString();
	}

	public String getUserFromToken(String token) {
		return this.decodeJWT(token).getSubject();
	}

	public String getNameFromToken(String token) {
		return this.decodeJWT(token).getClaim("name").asString();
	}

	public abstract JwkProvider getJWKProvider() throws MalformedURLException;
}
