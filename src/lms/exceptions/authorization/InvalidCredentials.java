package lms.exceptions.authorization;

public class InvalidCredentials extends AuthorizationError{
    public InvalidCredentials(String message) {
        super(message);
    }
}
