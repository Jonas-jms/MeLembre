package com.meLembre.util;

import java.util.Collection;

import org.springframework.stereotype.Component;

@Component
public final class TSUtil {

	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object value) {

		if (value == null) {
			return true;
		} else if (value instanceof Collection) {
			return ((Collection) value).isEmpty();
		}

		return false;
	}
}
