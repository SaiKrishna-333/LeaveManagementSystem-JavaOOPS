package leave;

public class LeaveRequest {
    public enum Status { APPROVED, CANCELLED }

    private static int counter = 1;
    private final int requestId;
    private final int days;
    private final String reason;
    private Status status;

    public LeaveRequest(int days, String reason) {
        this.requestId = counter++;
        this.days = days;
        this.reason = reason;
        this.status = Status.APPROVED;
    }

    public int getRequestId() { return requestId; }
    public int getDays() { return days; }
    public String getReason() { return reason; }
    public Status getStatus() { return status; }
    public void cancel() { this.status = Status.CANCELLED; }

    @Override
    public String toString() {
        return "Request#" + requestId + " [" + days + " day(s), " + reason + ", " + status + "]";
    }
}
