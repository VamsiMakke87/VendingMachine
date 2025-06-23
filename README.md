# 🥤 Vending Machine System

This project is a modular Java-based simulation of a vending machine system. It is built using object-oriented principles and leverages multiple software design patterns to ensure clean code structure, concurrency safety, scalability, and extensibility.

---

## 🔧 Features

* Product selection with quantity
* Payment via coins, notes, or card
* State-driven transitions (Idle → Payment → Dispense → Return Change)
* Refunds for overpayments or cancellations
* Strategy-based payment handling
* Inventory management

---

## 🧠 Design Patterns Used

* **State Pattern:** To model machine behavior based on state.
* **Strategy Pattern:** To support flexible payment mechanisms.
* **Singleton Pattern:** To ensure single instances of machine and inventory.

---

## 🧵 Concurrency Handling

* `synchronized` methods ensure thread safety.
* Prevents race conditions during concurrent modifications (e.g., payment processing).
* `Inventory` and `VendingMachine` use thread-safe patterns for safe access.

---

## 🚀 Scalability

* Stateless state classes make the machine horizontally scalable.
* Clean interface separation supports API integration and modular growth.
* Easily deployable as a REST microservice.

---

## 🧩 Extensibility

* Add new payment types, products, or states easily.
* Wrap logic in APIs for frontend or mobile integration.
* Replace in-memory data with persistent storage without impacting business logic.

---

## 📦 Class Responsibilities Overview

| Class                                        | Responsibility                              |
| -------------------------------------------- | ------------------------------------------- |
| `VendingMachine`                             | Core logic controller                       |
| `Inventory`                                  | Singleton-based product store               |
| `VendingMachineState`                        | State behavior abstraction                  |
| `IdleState`, `PaymentState`, etc.            | Handle business rules of each machine state |
| `PaymentStrategy`                            | Abstracts the payment mechanism             |
| `CashPaymentStrategy`, `CardPaymentStrategy` | Payment implementations                     |
| `Product`                                    | Represents an item                          |
| `Coin`, `Note`                               | Enum constants for payment types            |

---

## 📁 Project Structure

```
src/
└── org.example/
    ├── Main.java                          # Demo execution
    ├── VendingMachine.java                # Singleton controller
    ├── currency/
    │   ├── Coin.java                      # Enum for coin types
    │   └── Note.java                      # Enum for note types
    ├── model/
    │   ├── Inventory.java                 # Manages product stock
    │   └── Product.java                   # Product definition
    ├── state/
    │   ├── VendingMachineState.java       # State interface
    │   └── impl/
    │       ├── IdleState.java
    │       ├── PaymentState.java
    │       ├── DispenseState.java
    │       └── ReturnChangeState.java     # Concrete machine states
    └── strategy/
        └── payment/
            ├── PaymentStrategy.java       # Strategy interface
            ├── CashPaymentStrategy.java
            └── CardPaymentStrategy.java   # Payment strategies
```

---

## ✅ Example Flow

```java
vendingMachine.selectProduct("coke", 3);
vendingMachine.payWithCard(10);
vendingMachine.insertNote(Note.ONE);
vendingMachine.cancelRequest();
```

---

## 📈 Future Improvements

* Add unit and integration testing
* Convert to RESTful microservice (Spring Boot)
* Integrate front-end UI
* Externalize inventory and pricing
* Support concurrent sessions/machines

---

## 👤 Author

**Vamsi Makke**