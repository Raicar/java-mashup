package es.udc.mashup.ui.client.virtualstore;

public class InvalidSearchException extends Exception {

    public InvalidSearchException() {
        super();
    }

    public InvalidSearchException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSearchException(Throwable cause) {
        super(cause);
    }

    public InvalidSearchException(String message) {
        super(message);
    }
}
