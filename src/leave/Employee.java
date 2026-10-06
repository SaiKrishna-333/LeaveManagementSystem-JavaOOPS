package leave;

public abstract class Employee implements LeavePolicy {
    private final String employeeId;
    private final String name;
    private final String department;
    private final int annualEntitlement;
    private int leaveBalance;
    private final java.util.List<LeaveRequest> requests = new java.util.ArrayList<>();

    protected Employee(String employeeId, String name, String department, int annualEntitlement) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.annualEntitlement = annualEntitlement;
        this.leaveBalance = annualEntitlement;
    }

    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    @Override public int getAnnualEntitlement() { return annualEntitlement; }
    public int checkLeaveBalance() { return leaveBalance; }

    public LeaveRequest applyLeave(int days, String reason) throws LeaveException {
        if (days <= 0) throw new InvalidLeaveRequestException("Days must be positive.");
        if (days > getMaxDaysPerRequest()) throw new InvalidLeaveRequestException("Too many days requested.");
        if (days > leaveBalance) throw new InsufficientLeaveBalanceException(days, leaveBalance);
        leaveBalance -= days;
        LeaveRequest request = new LeaveRequest(days, reason);
        requests.add(request);
        return request;
    }

    public void cancelLeave(int requestId) throws LeaveException {
        for (LeaveRequest request : requests) {
            if (request.getRequestId() == requestId && request.getStatus() == LeaveRequest.Status.APPROVED) {
                request.cancel();
                leaveBalance += request.getDays();
                return;
            }
        }
        throw new InvalidLeaveRequestException("No active request with id " + requestId);
    }

    public abstract String getRole();

    public void displayDetails() {
        System.out.println("[" + getRole() + "] " + employeeId + " | " + name + " | "
                + department + " | Balance: " + leaveBalance + "/" + getAnnualEntitlement());
    }
}
