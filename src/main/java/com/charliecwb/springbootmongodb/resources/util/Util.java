package com.charliecwb.springbootmongodb.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Util {
	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
