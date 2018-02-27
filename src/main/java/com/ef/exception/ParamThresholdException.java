package com.ef.exception;

import static com.ef.util.Const.DURATION_PARAM;

/**
 * Excpetion to handle threshold param
 * @author jdiaz86
 */
public class ParamThresholdException extends NumberFormatException {
    
    final static String DEFAULT_MESSAGE = "The parameter " + DURATION_PARAM + " can only accept number values";
    
    public ParamThresholdException() {
        super(DEFAULT_MESSAGE);
    }
    
    public ParamThresholdException(String message) {
        super(message);
    }
    
}
