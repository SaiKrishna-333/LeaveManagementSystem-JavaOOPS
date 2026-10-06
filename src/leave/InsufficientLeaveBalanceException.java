package leave;

public class InsufficientLeaveBalanceException extends LeaveException {
    private static final long serialVersionUID = 1L;

    public InsufficientLeaveBalanceException(int requested, int available) {
        super("Requested " + requested + " day(s) but only " + available + " available.");
    }
}
