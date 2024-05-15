package com.bloomscorp.aster.configuration;

import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;
import org.apache.tika.Tika;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsterConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public RainTree rainTree() {
		return new RainTree();
	}

	@Bean
	@ConditionalOnMissingBean
	public HttpRequestDumpSanitizer httpRequestDumpSanitizer() {
		return new HttpRequestDumpSanitizer();
	}

	@Bean
	@ConditionalOnMissingBean
	public Tika tika() {
		return new Tika();
	}
}
