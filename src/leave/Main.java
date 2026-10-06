package leave;

public class Main {
    public static void main(String[] args) {
        Employee[] staff = {
            new FullTimeEmployee("E001", "Asha", "Engineering"),
            new PartTimeEmployee("E002", "Ben", "Design"),
            new Intern("E003", "Chloe", "Marketing"),
            new Manager("E004", "Dev", "Engineering", 8)
        };

        for (Employee e : staff) {
            try {
                LeaveRequest r = e.applyLeave(2, "Vacation");
                System.out.println(e.getName() + " -> " + r);
            } catch (LeaveException ex) {
                System.out.println(e.getName() + " -> DENIED: " + ex.getMessage());
            }
        }

        try {
            System.out.println("Overloaded call -> " + staff[0].applyLeave(1));
        } catch (LeaveException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
