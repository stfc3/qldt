/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.excel.exception;

/**
 *
 * @author dong.dv
 */
public class InvalidCellValueException extends ExcelReadingException {

    public InvalidCellValueException(String message) {
        super(message);
    }

    public InvalidCellValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCellValueException(Throwable cause) {
        super(cause);
    }

    public InvalidCellValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
