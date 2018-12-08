package com.thalasoft.butik.data.jpa.domain;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.Assert;

@Embeddable
public class EmailAddress {

	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

	@Column(name = "email")
	private String value;

	public EmailAddress(String email) {
		Assert.isTrue(isValid(email), "Invalid email address !");
		this.value = email;
	}

	protected EmailAddress() {
	}

	public static boolean isValid(String candidate) {
		return candidate == null ? false : PATTERN.matcher(candidate).matches();
	}

	public String getEmailAddress() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (this.value == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
			return false;
		}

		EmailAddress that = (EmailAddress) obj;

		return this.value.equals(that.value);
	}

	@Override
	public int hashCode() {
		return value == null ? 0 : value.hashCode();
	}

}
