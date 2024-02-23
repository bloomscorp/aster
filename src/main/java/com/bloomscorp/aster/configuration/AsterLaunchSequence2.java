package com.bloomscorp.aster.configuration;

import com.bloomscorp.nverse.NVerseAES;
import com.bloomscorp.nverse.NVerseEmailEncoder;
import com.bloomscorp.nverse.NVerseEmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class AsterLaunchSequence2 {

	private final NVerseEmailValidator emailValidator;

	public NVerseEmailEncoder emailEncoder(String encoderKey) {
		return new NVerseEmailEncoder(
			encoderKey,
			this.emailValidator,
			NVerseAES.SHA512
		);
	}
}
