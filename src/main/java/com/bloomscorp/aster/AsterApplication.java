package com.bloomscorp.aster;

import com.bloomscorp.aster.configuration.AsterConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Controller
@Import({
	AsterConfiguration.class
})
public @interface AsterApplication {
}
