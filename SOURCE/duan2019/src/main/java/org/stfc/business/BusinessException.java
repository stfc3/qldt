/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.business;

/**
 *
 * @author mcb
 */
public class BusinessException extends Exception {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -5332953043836351031L;

	public BusinessException(String msg) {
		super(msg);
	}
}
