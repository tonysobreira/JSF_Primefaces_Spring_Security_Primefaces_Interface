package com.jsf.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return CryptoUtil.encryptAES(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {

		if (!StringUtils.isBlank(rawPassword) && encode(rawPassword).equals(encodedPassword)) {
			return true;
		}

		return false;
	}

}
