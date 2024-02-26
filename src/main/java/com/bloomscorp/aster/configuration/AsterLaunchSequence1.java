package com.bloomscorp.aster.configuration;

import com.bloomscorp.nverse.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsterLaunchSequence1 {

	@Bean
	@ConditionalOnMissingBean
	public NVerseAuthenticationEntryPoint authenticationEntryPoint() {
		return new NVerseAuthenticationEntryPoint();
	}

	@Bean
	@ConditionalOnMissingBean
	public NVerseHttpRequestFilter requestFilter() {
		return new NVerseHttpRequestFilter();
	}

	@Bean
	@ConditionalOnMissingBean
	public NVerseCORSConfigurationSource nVerseCORSConfigurationSource() {
		return new NVerseCORSConfigurationSource();
	}

	@Bean
	@ConditionalOnMissingBean
	public NVerseEmailValidator nVerseEmailValidator() {
		return new NVerseEmailValidator();
	}

	@Bean
	@ConditionalOnMissingBean
	public NVerseAuthenticationService nVerseAuthenticationService() {
		return new NVerseAuthenticationService();
	}
}
