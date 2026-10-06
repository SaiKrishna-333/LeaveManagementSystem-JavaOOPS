package leave;

public class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String id, String name, String dept) {
        this(id, name, dept, 24);
    }

    protected FullTimeEmployee(String id, String name, String dept, int annualEntitlement) {
        super(id, name, dept, annualEntitlement);
    }

    @Override public int getMaxDaysPerRequest() { return 10; }
    @Override public String getRole() { return "Full-Time"; }
}
