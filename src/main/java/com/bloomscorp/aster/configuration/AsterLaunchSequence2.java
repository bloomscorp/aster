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

public class AsterLaunchSequence2 {

	public NVerseEmailEncoder emailEncoder(
		NVerseEmailValidator emailValidator,
		String encoderKey
	) {
		return new NVerseEmailEncoder(
			encoderKey,
			emailValidator,
			NVerseAES.SHA512
		);
	}
}
