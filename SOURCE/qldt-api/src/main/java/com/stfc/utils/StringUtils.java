/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.utils;

/**
 *
 * @author viettx
 */
public class StringUtils {

	public static String escapeCharacter(String param) {
		if (!Comparator.isEqualNullOrEmpty(param)) {
			return param.trim().toLowerCase().replace("/", "//").replace("%", "/%").replace("_", "/_");
		} else {
			return "";
		}
	}

	
}
