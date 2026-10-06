# Leave Management System (Java OOP)

Monthly Assignment – Module 2: Object-Oriented Programming

## Objective
A console-based Java application that manages employee leave while demonstrating core Object-Oriented Programming concepts.

## Features
- Employee ID, name, department and leave balance
- `applyLeave()` with method overloading
- `cancelLeave()` with safe refund behaviour
- `displayDetails()` and `checkLeaveBalance()`
- Full-Time, Part-Time, Intern and Manager employee types
- Different annual entitlements and per-request limits
- Custom checked exceptions for invalid and insufficient-balance requests
- Separate `LeaveRequest` class for request state
- Defensive copy when exposing stored requests

## Leave Rules
| Type | Annual days | Max per request |
|---|---:|---:|
| Full-Time | 24 | 10 |
| Part-Time | 12 | 5 |
| Intern | 6 | 2 |
| Manager | 30 | 15 |

These are sample values for the assignment and are not a real company policy.

## OOP Concepts Demonstrated
- **Encapsulation:** private state and controlled operations
- **Abstraction:** abstract `Employee` + `LeavePolicy` interface
- **Inheritance:** employee subclasses reuse common behaviour
- **Polymorphism:** `Employee[]` stores different concrete employee types
- **Method overriding:** role and per-request rules are specialized
- **Method overloading:** `applyLeave(int)` and `applyLeave(int, String)`
- **Access modifiers:** private/protected/public used according to responsibility

## Project Structure
```text
Leave-Management-System-Assignment/
├── src/leave/
│   ├── Employee.java
│   ├── FullTimeEmployee.java
│   ├── PartTimeEmployee.java
│   ├── Intern.java
│   ├── Manager.java
│   ├── LeavePolicy.java
│   ├── LeaveRequest.java
│   ├── LeaveException.java
│   ├── InvalidLeaveRequestException.java
│   ├── InsufficientLeaveBalanceException.java
│   └── Main.java
├── images/
├── docs/
├── report.tex
├── report.pdf
├── README.md
└── .gitignore
```

## Compile and Run
From the project root:

```bash
mkdir -p out
javac -d out src/leave/*.java
java -cp out leave.Main
```

## Expected Demonstration
The program demonstrates successful applications, a role-specific rejection, method overloading, cancellation/refund, an invalid request ID, and an insufficient-balance request.

## Git Practice
Suggested meaningful commits:
1. Initial employee design and class diagram
2. Added Employee class and exceptions
3. Implemented leave management operations
4. Added inheritance for employee types
5. Implemented polymorphism and method overloading
6. Added Manager change request
7. Refactored validation and request responsibilities
8. Added tests, README, diagrams and final report

## Author
**Name:** Sai Krishna S.

**Roll No / ID:** `1SI23IS086`

**GitHub:** `<YOUR GITHUB REPOSITORY URL>`
