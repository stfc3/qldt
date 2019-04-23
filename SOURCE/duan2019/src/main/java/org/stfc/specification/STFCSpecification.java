package org.stfc.specification;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class STFCSpecification<T> {
	public Specification<T> getSpecification(String search) {
		if (StringUtils.isBlank(search)) {
			return null;
		}
		SpecificationsBuilder<T> builder = new SpecificationsBuilder<T>();
		String operationSetExper = StringUtils.join(SearchOperation.SIMPLE_OPERATION_SET, "|");
		Pattern pattern = Pattern
				.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)([\\p{Print}\\p{Space}]+?)(\\p{Punct}?),");
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {
			String s1 = matcher.group(1);
			String s2 = matcher.group(2);
			String s3 = matcher.group(3);
			String s4 = matcher.group(4);
			String s5 = matcher.group(5);
			builder.with(s1, s2, s4, s3, s5);
		}
		return builder.build();
	}
	
	public String getFieldNames(Class<?> clazz, String queryValue) throws Exception {
		String builderQuery = "";

		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getType().getName().equals(String.class.getName())) {
				builderQuery += fields[i].getName() + ":" + queryValue + ",";
			}
		}
		return builderQuery;
	}
}
