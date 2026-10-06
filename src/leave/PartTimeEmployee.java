package leave;

public class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String id, String name, String dept) {
        super(id, name, dept, 12);
    }

    @Override public int getMaxDaysPerRequest() { return 5; }
    @Override public String getRole() { return "Part-Time"; }
}
