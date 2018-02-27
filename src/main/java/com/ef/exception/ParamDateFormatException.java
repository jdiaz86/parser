package com.ef.exception;

import static com.ef.util.Const.START_DATE_PARAM;

/**
 * Exception to handle date param format
 * @author jdiaz86
 */
public class ParamDateFormatException extends ParserException {
    
    final static String EXCEPTION_MESSAGE = "The format for " + START_DATE_PARAM + " is incorrect, should be: yyyy-MM-dd.HH:mm:ss";
    
    public ParamDateFormatException() {
        super(EXCEPTION_MESSAGE);
    }
    
    public ParamDateFormatException(String message) {
        super(message);
    }
}
