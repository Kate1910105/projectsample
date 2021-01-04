package lms.exceptions.authorization;

public class UserNotFound extends AuthorizationError{
    public UserNotFound(String message) {
        super(message);
    }
}
