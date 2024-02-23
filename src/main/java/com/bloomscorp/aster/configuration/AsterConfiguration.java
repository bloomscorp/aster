package com.bloomscorp.aster.configuration;

import com.bloomscorp.nverse.sanitizer.HttpRequestDumpSanitizer;
import com.bloomscorp.raintree.RainTree;
import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsterConfiguration {

	@Bean
	public RainTree rainTree() {
		return new RainTree();
	}

	@Bean
	public HttpRequestDumpSanitizer httpRequestDumpSanitizer() {
		return new HttpRequestDumpSanitizer();
	}

	@Bean
	public Tika tika() {
		return new Tika();
	}
}
