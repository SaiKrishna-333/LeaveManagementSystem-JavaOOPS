# Refactoring Notes

1. Centralized leave validation in `Employee.validateRequest()`.
2. Extracted request state into `LeaveRequest`.
3. Added checked exception hierarchy for invalid operations.
4. Removed constructor-time polymorphism by supplying entitlement explicitly.
5. Return a defensive copy from `getRequests()`.
6. Added executable regression tests in the final commit.
