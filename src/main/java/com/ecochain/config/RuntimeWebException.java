package com.ecochain.config;

@SuppressWarnings("serial")
public class RuntimeWebException extends RuntimeException {

    public RuntimeWebException() {
        super();
    }

    public RuntimeWebException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeWebException(String message) {
        super(message);
    }

    public RuntimeWebException(Throwable cause) {
        super(cause);
    }

}
