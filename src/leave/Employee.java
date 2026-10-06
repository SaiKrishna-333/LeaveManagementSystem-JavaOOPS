package leave;

public abstract class Employee implements LeavePolicy {
    private final String employeeId;
    private final String name;
    private final String department;
    private final int annualEntitlement;
    private int leaveBalance;

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

    public abstract String getRole();

    public void displayDetails() {
        System.out.println("[" + getRole() + "] " + employeeId + " | " + name + " | "
                + department + " | Balance: " + leaveBalance + "/" + getAnnualEntitlement());
    }
}
