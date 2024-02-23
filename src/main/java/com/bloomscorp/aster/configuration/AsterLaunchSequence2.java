package com.bloomscorp.aster.configuration;

import com.bloomscorp.nverse.NVerseAES;
import com.bloomscorp.nverse.NVerseEmailEncoder;
import com.bloomscorp.nverse.NVerseEmailValidator;

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
