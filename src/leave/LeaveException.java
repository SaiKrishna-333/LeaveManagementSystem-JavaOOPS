package leave;

/** Base exception for all leave-related errors. */
public class LeaveException extends Exception {
    private static final long serialVersionUID = 1L;
    public LeaveException(String message) {
        super(message);
    }
}
