package leave;

public class Intern extends Employee {
    public Intern(String id, String name, String dept) {
        super(id, name, dept, 6);
    }

    @Override public int getMaxDaysPerRequest() { return 2; }
    @Override public String getRole() { return "Intern"; }
}
