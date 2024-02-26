package com.bloomscorp.aster;

import com.bloomscorp.aster.alfred.AsterCronManager;
import com.bloomscorp.aster.alfred.AsterLogBook;
import com.bloomscorp.aster.configuration.AsterConfiguration;
import com.bloomscorp.aster.configuration.AsterLaunchSequence1;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({
	AsterConfiguration.class,
	AsterLaunchSequence1.class,
	AsterLogBook.class,
	AsterCronManager.class
})
public @interface EnableAster {
}
