package leave;

public class Main {
    public static void main(String[] args) {
        // Polymorphism: one array type, many behaviours.
        Employee[] staff = {
            new FullTimeEmployee("E001", "Asha", "Engineering"),
            new PartTimeEmployee("E002", "Ben", "Design"),
            new Intern("E003", "Chloe", "Marketing"),
            new Manager("E004", "Dev", "Engineering", 8)
        };

        System.out.println("=== Initial details ===");
        for (Employee e : staff) e.displayDetails();

        System.out.println("\n=== Applying leave ===");
        for (Employee e : staff) {
            try {
                LeaveRequest r = e.applyLeave(4, "Vacation");
                System.out.println(e.getName() + " -> " + r + " | balance " + e.checkLeaveBalance());
            } catch (LeaveException ex) {
                System.out.println(e.getName() + " -> DENIED: " + ex.getMessage());
            }
        }

        System.out.println("\n=== Overloaded applyLeave(days) ===");
        try {
            System.out.println(staff[0].applyLeave(2));
        } catch (LeaveException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\n=== Cancel leave ===");
        try {
            staff[0].cancelLeave(1);
            System.out.println("Asha cancelled request 1, balance " + staff[0].checkLeaveBalance());
            staff[0].cancelLeave(99);
        } catch (LeaveException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        System.out.println("\n=== Exceeding balance ===");
        try {
            staff[3].applyLeave(15);
            staff[3].applyLeave(15);
        } catch (LeaveException ex) {
            System.out.println("Dev -> DENIED: " + ex.getMessage());
        }

        System.out.println("\n=== Final details ===");
        for (Employee e : staff) e.displayDetails();
    }
}
