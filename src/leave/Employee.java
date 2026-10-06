package leave;

import java.util.ArrayList;
import java.util.List;

/** Abstract base class. Encapsulation: all fields are private. */
public abstract class Employee implements LeavePolicy {
    private final String employeeId;
    private final String name;
    private final String department;
    private final int annualEntitlement;
    private int leaveBalance;
    private final List<LeaveRequest> requests = new ArrayList<>();

    /**
     * The entitlement is supplied by the concrete class instead of calling an
     * overridable method from the constructor.
     */
    protected Employee(String employeeId, String name, String department, int annualEntitlement) {
        if (employeeId == null || employeeId.isBlank()) {
            throw new IllegalArgumentException("Employee ID cannot be blank.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Employee name cannot be blank.");
        }
        if (department == null || department.isBlank()) {
            throw new IllegalArgumentException("Department cannot be blank.");
        }
        if (annualEntitlement <= 0) {
            throw new IllegalArgumentException("Annual entitlement must be positive.");
        }
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

    /** Overloading: apply with a default reason. */
    public LeaveRequest applyLeave(int days) throws LeaveException {
        return applyLeave(days, "Personal");
    }

    /** Overloading: apply with a reason. */
    public LeaveRequest applyLeave(int days, String reason) throws LeaveException {
        validateRequest(days);
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

    /** Single place for all leave-request validation. */
    protected void validateRequest(int days) throws LeaveException {
        if (days <= 0) {
            throw new InvalidLeaveRequestException("Days must be positive.");
        }
        if (days > getMaxDaysPerRequest()) {
            throw new InvalidLeaveRequestException(
                    getRole() + " can take at most " + getMaxDaysPerRequest() + " day(s) per request.");
        }
        if (days > leaveBalance) {
            throw new InsufficientLeaveBalanceException(days, leaveBalance);
        }
    }

    /** Subclasses supply their role name. */
    public abstract String getRole();

    /** Display common employee details; Manager extends this behaviour. */
    public void displayDetails() {
        System.out.println("[" + getRole() + "] " + employeeId + " | " + name + " | " + department
                + " | Balance: " + leaveBalance + "/" + getAnnualEntitlement()
                + " | Max/request: " + getMaxDaysPerRequest());
    }

    /** Defensive copy prevents callers from modifying the internal list. */
    public List<LeaveRequest> getRequests() {
        return new ArrayList<>(requests);
    }
}
