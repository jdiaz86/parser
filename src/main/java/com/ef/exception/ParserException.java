package com.ef.exception;

/**
 * Exception to handle Parser's general exceptions
 * @author jdiaz86
 */
public class ParserException extends RuntimeException {
    
    final static String DEFAULT_MESSAGE = "An error has occur within Parser application\n";
    
    public ParserException() {
        super(DEFAULT_MESSAGE);
    }
    
    public ParserException(String message) {
        super(DEFAULT_MESSAGE + message);
    }
    
}
