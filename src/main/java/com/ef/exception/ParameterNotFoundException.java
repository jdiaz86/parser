package com.ef.exception;

import java.util.NoSuchElementException;

/**
 * Exception to handle application's parameters
 * @author jdiaz86
 */
public class ParameterNotFoundException extends NoSuchElementException {
     
    final static String DEFAULT_MESSAGE = String.join("\n", 
                                    ""
                                    ,"One of more of these parameters are missing, please try again with all parameters set"
                                    ,"--accessLog"
                                    ,"--startDate"
                                    ,"--duration"
                                    ,"--threshold" );

    
    public ParameterNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    
    public ParameterNotFoundException(String message) {
        super(message != null ? message : DEFAULT_MESSAGE);
    }
    
    
}
