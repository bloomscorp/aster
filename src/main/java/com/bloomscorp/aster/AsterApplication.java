package com.bloomscorp.aster;

import com.bloomscorp.aster.configuration.AsterConfiguration;
import com.bloomscorp.bsb.BmxApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@BmxApplication(scanBasePackages = {
//	"com.bloomscorp.aster"
//})
@EnableJpaRepositories(basePackages = {
	"com.bloomscorp.aster"
})
@EntityScan(basePackages = {
	"com.bloomscorp.aster"
})
@Controller
//@SpringBootApplication(scanBasePackages = {
//	"com.bloomscorp.aster"
//})
@SpringBootApplication
@Import({
	AsterConfiguration.class
})
public @interface AsterApplication {
}
