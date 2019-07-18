/**
 * 
 */
package org.stfc.utils;

/**
 * @author viett
 *
 */
public enum Language {
	VI("vi"), EN("en");
	private String lang;

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @param lang
	 */
	private Language(String lang) {
		this.lang = lang;
	}
	
}
