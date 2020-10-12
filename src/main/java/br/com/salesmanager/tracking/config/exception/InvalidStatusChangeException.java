package br.com.salesmanager.tracking.config.exception;

public class InvalidStatusChangeException extends RuntimeException {

    public InvalidStatusChangeException() {
        super("Invalid status change");
    }
}
