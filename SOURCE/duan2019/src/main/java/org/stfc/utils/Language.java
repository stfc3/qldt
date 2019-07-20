/**
 * 
 */
package org.stfc.utils;

/**
 * @author viettx
 *
 */
public enum Language {
	VI("vi"), EN("en");
	private String value;

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param value
	 */
	private Language(String value) {
		this.value = value;
	}
	
}
