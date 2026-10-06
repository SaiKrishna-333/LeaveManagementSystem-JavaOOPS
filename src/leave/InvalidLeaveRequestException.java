package leave;

public class InvalidLeaveRequestException extends LeaveException {
    private static final long serialVersionUID = 1L;

    public InvalidLeaveRequestException(String message) {
        super(message);
    }
}
