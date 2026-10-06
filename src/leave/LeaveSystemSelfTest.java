package leave;

/** Lightweight executable regression tests for the assignment scenarios. */
public class LeaveSystemSelfTest {
    private static int passed = 0;
    private static int total = 0;

    private static void check(String name, boolean condition) {
        total++;
        if (!condition) throw new AssertionError("FAILED: " + name);
        passed++;
        System.out.println("PASS: " + name);
    }

    private static void expectLeaveException(String name, RunnableLeave operation, String expectedPart) {
        total++;
        try {
            operation.run();
            throw new AssertionError("FAILED: " + name + " (no exception)");
        } catch (LeaveException ex) {
            if (!ex.getMessage().contains(expectedPart)) {
                throw new AssertionError("FAILED: " + name + " -> " + ex.getMessage());
            }
            passed++;
            System.out.println("PASS: " + name);
        }
    }

    @FunctionalInterface
    private interface RunnableLeave {
        void run() throws LeaveException;
    }

    public static void main(String[] args) throws Exception {
        FullTimeEmployee ft = new FullTimeEmployee("T001", "FT", "Eng");
        PartTimeEmployee pt = new PartTimeEmployee("T002", "PT", "Design");
        Intern intern = new Intern("T003", "IN", "Marketing");
        Manager manager = new Manager("T004", "MG", "Eng", 8);

        check("T1 initial balances", ft.checkLeaveBalance() == 24 && pt.checkLeaveBalance() == 12
                && intern.checkLeaveBalance() == 6 && manager.checkLeaveBalance() == 30);

        LeaveRequest ft10 = ft.applyLeave(10, "Vacation");
        check("T2 full-time 10 days approved", ft10.getStatus() == LeaveRequest.Status.APPROVED
                && ft.checkLeaveBalance() == 14);
        expectLeaveException("T3 full-time 11 days rejected", () -> ft.applyLeave(11), "at most 10");

        pt.applyLeave(5, "Vacation");
        check("T4 part-time 5 days approved", pt.checkLeaveBalance() == 7);
        expectLeaveException("T5 part-time 6 days rejected", () -> pt.applyLeave(6), "at most 5");

        intern.applyLeave(2, "Training");
        check("T6 intern 2 days approved", intern.checkLeaveBalance() == 4);
        expectLeaveException("T7 intern 3 days rejected", () -> intern.applyLeave(3), "at most 2");

        manager.applyLeave(15, "Vacation");
        check("T8 manager 15 days approved", manager.checkLeaveBalance() == 15);

        expectLeaveException("T9 zero days rejected", () -> ft.applyLeave(0), "positive");
        expectLeaveException("T10 negative days rejected", () -> ft.applyLeave(-3), "positive");

        FullTimeEmployee balanceCase = new FullTimeEmployee("T005", "Balance", "Eng");
        balanceCase.applyLeave(10);
        check("T11 first 10 days approved", balanceCase.checkLeaveBalance() == 14);
        balanceCase.applyLeave(10);
        check("T11 second 10 days approved", balanceCase.checkLeaveBalance() == 4);
        expectLeaveException("T11 third 10 days rejected by balance", () -> balanceCase.applyLeave(10), "only 4");

        Intern cancelCase = new Intern("T006", "Cancel", "Marketing");
        LeaveRequest cancelRequest = cancelCase.applyLeave(1);
        cancelCase.cancelLeave(cancelRequest.getRequestId());
        check("T12 cancellation restores balance", cancelCase.checkLeaveBalance() == 6);

        expectLeaveException("T13 invalid request id rejected", () -> cancelCase.cancelLeave(999), "No active request");

        System.out.println("\n" + passed + "/" + total + " regression checks passed.");
    }
}
