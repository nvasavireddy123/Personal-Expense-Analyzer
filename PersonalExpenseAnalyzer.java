import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Expense {
    String description;
    double amount;
    String category;
    String date;

    Expense(String description, double amount, String category, String date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
}

public class PersonalExpenseAnalyzer {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Expense> expenses = new ArrayList<>();
    static double budget = 0;

    // Automatically identify expense category
    static String findCategory(String description) {

        String text = description.toLowerCase();

        if (text.contains("food") ||
            text.contains("lunch") ||
            text.contains("dinner") ||
            text.contains("breakfast") ||
            text.contains("restaurant")) {
            return "Food";
        }

        if (text.contains("bus") ||
            text.contains("train") ||
            text.contains("uber") ||
            text.contains("cab") ||
            text.contains("petrol") ||
            text.contains("fuel")) {
            return "Transport";
        }

        if (text.contains("movie") ||
            text.contains("game") ||
            text.contains("netflix")) {
            return "Entertainment";
        }

        if (text.contains("book") ||
            text.contains("college") ||
            text.contains("course")) {
            return "Education";
        }

        if (text.contains("medicine") ||
            text.contains("hospital") ||
            text.contains("doctor")) {
            return "Medical";
        }

        if (text.contains("shopping") ||
            text.contains("clothes") ||
            text.contains("dress") ||
            text.contains("shoes")) {
            return "Shopping";
        }

        if (text.contains("rent") ||
            text.contains("electricity") ||
            text.contains("water") ||
            text.contains("bill")) {
            return "Bills";
        }

        return "Others";
    }

    // Add an expense
    static void addExpense() {

        scanner.nextLine();

        System.out.print("Enter expense description: ");
        String description = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        scanner.nextLine();

        System.out.print("Enter date (DD-MM-YYYY): ");
        String date = scanner.nextLine();

        String category = findCategory(description);

        Expense expense =
                new Expense(description, amount, category, date);

        expenses.add(expense);

        System.out.println();
        System.out.println("Expense added successfully!");
        System.out.println("Detected Category : " + category);
    }

    // Display all expenses
    static void viewExpenses() {

        if (expenses.size() == 0) {
            System.out.println("\nNo expenses found.");
            return;
        }

        System.out.println("\n================ EXPENSES ================");

        System.out.printf(
                "%-20s %-12s %-18s %-15s%n",
                "Description",
                "Amount",
                "Category",
                "Date"
        );

        System.out.println(
                "-------------------------------------------------------------"
        );

        for (Expense e : expenses) {

            System.out.printf(
                    "%-20s Rs.%-9.2f %-18s %-15s%n",
                    e.description,
                    e.amount,
                    e.category,
                    e.date
            );
        }
    }

    // Calculate total
    static double calculateTotal() {

        double total = 0;

        for (Expense e : expenses) {
            total = total + e.amount;
        }

        return total;
    }

    // Category analysis
    static void categoryAnalysis() {

        if (expenses.size() == 0) {
            System.out.println("\nNo expenses found.");
            return;
        }

        HashMap<String, Double> categoryAmount =
                new HashMap<>();

        for (Expense e : expenses) {

            if (categoryAmount.containsKey(e.category)) {

                double oldAmount =
                        categoryAmount.get(e.category);

                categoryAmount.put(
                        e.category,
                        oldAmount + e.amount
                );

            } else {

                categoryAmount.put(
                        e.category,
                        e.amount
                );
            }
        }

        System.out.println("\n========== CATEGORY ANALYSIS ==========");

        for (String category : categoryAmount.keySet()) {

            System.out.printf(
                    "%-20s Rs.%.2f%n",
                    category,
                    categoryAmount.get(category)
            );
        }
    }

    // Set budget
    static void setBudget() {

        System.out.print("\nEnter your monthly budget: Rs.");

        budget = scanner.nextDouble();

        if (budget <= 0) {

            System.out.println(
                    "Budget must be greater than 0."
            );

            budget = 0;
            return;
        }

        System.out.println(
                "Monthly budget set successfully!"
        );
    }

    // Budget status
    static void budgetStatus() {

        if (budget == 0) {

            System.out.println(
                    "\nPlease set your budget first."
            );

            return;
        }

        double total = calculateTotal();

        double remaining = budget - total;

        System.out.println("\n========== BUDGET STATUS ==========");

        System.out.printf(
                "Monthly Budget : Rs.%.2f%n",
                budget
        );

        System.out.printf(
                "Total Spent    : Rs.%.2f%n",
                total
        );

        if (remaining >= 0) {

            System.out.printf(
                    "Remaining      : Rs.%.2f%n",
                    remaining
            );

            double percentage =
                    (total / budget) * 100;

            System.out.printf(
                    "Budget Used    : %.2f%%%n",
                    percentage
            );

            if (percentage >= 90) {

                System.out.println(
                        "WARNING: You are very close to your budget!"
                );

            } else if (percentage >= 75) {

                System.out.println(
                        "CAUTION: Monitor your spending."
                );

            } else {

                System.out.println(
                        "GOOD: Your spending is under control."
                );
            }

        } else {

            System.out.printf(
                    "Exceeded By    : Rs.%.2f%n",
                    -remaining
            );

            System.out.println(
                    "ALERT: You have exceeded your budget!"
            );
        }
    }

    // Spending summary
    static void summary() {

        if (expenses.size() == 0) {

            System.out.println(
                    "\nNo expenses found."
            );

            return;
        }

        double total = calculateTotal();

        double average =
                total / expenses.size();

        System.out.println("\n========== SPENDING SUMMARY ==========");

        System.out.println(
                "Number of Expenses : " + expenses.size()
        );

        System.out.printf(
                "Total Spending     : Rs.%.2f%n",
                total
        );

        System.out.printf(
                "Average Expense    : Rs.%.2f%n",
                average
        );

        if (budget > 0) {

            double percentage =
                    (total / budget) * 100;

            System.out.printf(
                    "Budget Used        : %.2f%%%n",
                    percentage
            );
        }
    }

    // Main menu
    public static void main(String[] args) {

        while (true) {

            System.out.println();
            System.out.println("==========================================");
            System.out.println("       PERSONAL EXPENSE ANALYZER");
            System.out.println("==========================================");

            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Category Analysis");
            System.out.println("4. Set Monthly Budget");
            System.out.println("5. Check Budget");
            System.out.println("6. Spending Summary");
            System.out.println("7. Exit");

            System.out.print("\nEnter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addExpense();
                    break;

                case 2:
                    viewExpenses();
                    break;

                case 3:
                    categoryAnalysis();
                    break;

                case 4:
                    setBudget();
                    break;

                case 5:
                    budgetStatus();
                    break;

                case 6:
                    summary();
                    break;

                case 7:
                    System.out.println(
                            "\nThank you for using Personal Expense Analyzer!"
                    );

                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "\nInvalid choice. Please enter 1 to 7."
                    );
            }
        }
    }
}

