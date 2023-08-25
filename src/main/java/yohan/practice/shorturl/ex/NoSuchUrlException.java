package yohan.practice.shorturl.ex;

public class NoSuchUrlException extends RuntimeException {
    public NoSuchUrlException() {
        super();
    }

    public NoSuchUrlException(String message) {
        super(message);
    }

    public NoSuchUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchUrlException(Throwable cause) {
        super(cause);
    }
}
