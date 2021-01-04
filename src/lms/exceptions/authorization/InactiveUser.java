package lms.exceptions.authorization;

public class InactiveUser extends AuthorizationError{
    public InactiveUser(String message) {
        super(message);
    }
}
