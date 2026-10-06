package leave;

/** Abstraction: every employee type must define its leave rules. */
public interface LeavePolicy {
    int getAnnualEntitlement();
    int getMaxDaysPerRequest();
}
