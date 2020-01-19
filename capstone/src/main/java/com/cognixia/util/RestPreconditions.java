package com.cognixia.util;

import org.springframework.web.client.ResourceAccessException;

public class RestPreconditions {

	public static <T> T checkNotNull(T resource) {
		if (resource == null) {
			throw new ResourceAccessException("Can't be null!");
		}
		return resource;
	}
}
