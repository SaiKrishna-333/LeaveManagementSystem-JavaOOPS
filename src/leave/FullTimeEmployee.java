package leave;

public class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String id, String name, String dept) {
        super(id, name, dept, 24);
    }

    @Override public int getMaxDaysPerRequest() { return 10; }
    @Override public String getRole() { return "Full-Time"; }
}
