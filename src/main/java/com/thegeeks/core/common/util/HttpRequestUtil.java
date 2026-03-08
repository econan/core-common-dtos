package com.thegeeks.core.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author kwangbum.ha@gmail.com
 * @date 2023. 5. 6.
 *
 */
public class HttpRequestUtil {

	/**
	 *
	 * @return
	 */
	public static final HttpServletRequest getRequest() {

		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}

	private HttpRequestUtil() {}
}
