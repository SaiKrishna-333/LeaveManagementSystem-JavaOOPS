# Initial Employee Design

## Core Objects
- Employee
- LeaveRequest
- FullTimeEmployee
- PartTimeEmployee
- Intern
- Manager

## Core Behaviours
- applyLeave()
- cancelLeave()
- displayDetails()
- checkLeaveBalance()

## Planned Relationships
- FullTimeEmployee, PartTimeEmployee and Intern extend Employee.
- Manager extends FullTimeEmployee.
- Employee owns LeaveRequest objects.
- LeavePolicy defines employee-specific leave rules.
- LeaveException is the base exception for invalid leave operations.
