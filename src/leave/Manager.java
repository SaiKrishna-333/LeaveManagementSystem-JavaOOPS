package leave;

public class Manager extends FullTimeEmployee {
    private static final int MANAGER_ENTITLEMENT = 30;
    private final int teamSize;

    public Manager(String id, String name, String dept, int teamSize) {
        super(id, name, dept, MANAGER_ENTITLEMENT);
        if (teamSize < 0) throw new IllegalArgumentException("Team size cannot be negative.");
        this.teamSize = teamSize;
    }

    @Override public int getMaxDaysPerRequest() { return 15; }
    @Override public String getRole() { return "Manager"; }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println(" Team size: " + teamSize);
    }
}
