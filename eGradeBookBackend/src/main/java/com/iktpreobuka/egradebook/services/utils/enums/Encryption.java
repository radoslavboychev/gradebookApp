package com.iktpreobuka.egradebook.services.utils.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encryption {

	private final static Logger logger = LoggerFactory.getLogger(Encryption.class);

	public static String getPasswordEncoded(String password) {
		logger.info("~~PASSWORD ENCRIPTION~~ Accessing service for password encription.");

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		logger.info("~~PASSWORD ENCRIPTION~~ Password encripted.");

		return encoder.encode(password);

	}

	// use for setting admin encoded password for db
	public static void main(String[] args) {
		System.out.println(getPasswordEncoded("angel"));
	}

	public static Boolean validatePassword(String password, String encodedPassword) {
		logger.info("~~PASSWORD ENCRIPTION~~ Accessing service for password validation.");

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		logger.info("~~PASSWORD ENCRIPTION~~ Password validated.");

		return encoder.matches(password, encodedPassword);
	}

}
