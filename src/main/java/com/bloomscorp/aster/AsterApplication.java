package com.bloomscorp.aster;

import com.bloomscorp.aster.alfred.AsterCronManager;
import com.bloomscorp.aster.alfred.AsterLogBook;
import com.bloomscorp.aster.alfred.dao.repository.AsterLogBookRepository;
import com.bloomscorp.aster.alfred.dao.repository.AuthenticationLogJpaRepository;
import com.bloomscorp.aster.alfred.dao.repository.LogJpaRepository;
import com.bloomscorp.aster.configuration.AsterConfiguration;
import com.bloomscorp.aster.configuration.AsterLaunchSequence1;
import com.bloomscorp.bsb.BmxApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@BmxApplication
@Controller
@SpringBootApplication(
	scanBasePackages = {
		"com.bloomscorp.aster.alfred"
	}
)
@Import({
	AsterConfiguration.class,
	AsterLaunchSequence1.class,
	AsterLogBook.class,
	AsterCronManager.class,
//	AsterLogBookRepository.class,
//	AuthenticationLogJpaRepository.class,
//	LogJpaRepository.class
})
public @interface AsterApplication {
}
