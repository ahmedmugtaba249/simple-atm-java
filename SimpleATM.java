import java.util.Scanner;

public class SimpleATM {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 1. PIN login (3 attempts max)
        int pin = 1234, attempts = 0;
        boolean loggedIn = false;

        while (attempts < 3 && !loggedIn) {
            System.out.print("Enter PIN: ");
            if (input.nextInt() == pin) {
                loggedIn = true;
            } else {
                attempts++;
                System.out.println("Wrong PIN. Tries left: " + (3 - attempts));
            }
        }

        // 2. Main ATM menu (only runs if login worked)
        if (loggedIn) {
            double balance = 500.0; // Starting balance
            String[] history = new String[50]; // Stores transactions
            int count = 0, choice = 0;

            while (choice != 5) {
                System.out.print("\n1.Balance  2.Deposit  3.Withdraw  4.Transactions  5.Exit\nChoose (1-5): ");
                choice = input.nextInt();

                if (choice == 1) {
                    System.out.println("Your balance is: $" + balance);

                } else if (choice == 2) {
                    System.out.print("Deposit amount: $");
                    double amt = input.nextDouble();
                    if (amt > 0) {
                        balance += amt;
                        if (count < history.length) history[count++] = "Deposited $" + amt;
                        System.out.println("Deposited! New balance: $" + balance);
                    } else {
                        System.out.println("Error: Amount must be over $0.");
                    }

                } else if (choice == 3) {
                    System.out.print("Withdraw amount: $");
                    double amt = input.nextDouble();
                    if (amt <= 0) {
                        System.out.println("Error: Amount must be over $0.");
                    } else if (amt > balance) {
                        System.out.println("Error: Insufficient funds! Your balance is $" + balance);
                    } else {
                        balance -= amt;
                        if (count < history.length) history[count++] = "Withdrew $" + amt;
                        System.out.println("Withdrawn! New balance: $" + balance);
                    }

                } else if (choice == 4) {
                    if (count == 0) {
                        System.out.println("No transactions yet.");
                    } else {
                        for (int i = 0; i < count; i++) {
                            System.out.println((i + 1) + ". " + history[i]);
                        }
                    }

                } else if (choice == 5) {
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("Invalid option.");
                }
            }
        } else {
            System.out.println("Account locked. Too many wrong attempts.");
        }

        input.close();
    }
}