package lms.exceptions.authorization;

public class AuthorizationError extends Exception{
    public AuthorizationError(String message) {
        super(message);
    }
}
