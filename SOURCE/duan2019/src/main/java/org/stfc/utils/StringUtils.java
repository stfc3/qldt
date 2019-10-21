/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

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
        /*
     * Hàm convert tieng viet khong dau
     */
    public static String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replace("đ", "d");
    }

	
}
