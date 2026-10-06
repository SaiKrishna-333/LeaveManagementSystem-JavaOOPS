package leave;

public class Main {
    public static void main(String[] args) throws Exception {
        Employee[] staff = {
            new FullTimeEmployee("E001", "Asha", "Engineering"),
            new PartTimeEmployee("E002", "Ben", "Design"),
            new Intern("E003", "Chloe", "Marketing")
        };
        for (Employee e : staff) e.displayDetails();
    }
}
