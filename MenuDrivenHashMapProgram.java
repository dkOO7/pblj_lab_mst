import java.util.*;

class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String msg) { super(msg); }
}

class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) { super(msg); }
}

class EmployeeManagement {
    private HashMap<Integer, String> employeeMap = new HashMap<>();

    public void addEmployee(int id, String name) { employeeMap.put(id, name); }

    public String getEmployeeName(int id) throws EmployeeNotFoundException {
        if (!employeeMap.containsKey(id)) throw new EmployeeNotFoundException("Error: Employee ID not found!");
        return employeeMap.get(id);
    }

    public void displayEmployees() { System.out.println("Employee Map: " + employeeMap); }
}

class ProductManagement {
    private HashMap<String, Double> productMap = new HashMap<>();

    public void addProduct(String id, double price) { productMap.put(id, price); }

    public void applyDiscount(String id, double percent) throws ProductNotFoundException {
        if (!productMap.containsKey(id)) throw new ProductNotFoundException("Error: Product ID not found");
        if (percent < 0 || percent > 100) throw new IllegalArgumentException("Error: Discount must be 0–100!");
        double newPrice = productMap.get(id) * (1 - percent / 100);
        productMap.put(id, newPrice);
        System.out.println("New price for " + id + ": $" + newPrice);
    }

    public void displayProducts() { System.out.println("Products: " + productMap); }
}

public class MenuDrivenHashMapProgram {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        EmployeeManagement empManager = new EmployeeManagement();
        ProductManagement prodManager = new ProductManagement();

        while (true) {
            System.out.println("\nMain Menu:\n1. Employee Management\n2. Product Management\n3. Exit");
            switch (sc.nextInt()) {
                case 1 -> employeeMenu(empManager);
                case 2 -> productMenu(prodManager);
                case 3 -> { System.out.println("Exiting..."); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void employeeMenu(EmployeeManagement empManager) {
        while (true) {
            System.out.println("\nEmployee Menu:\n1. Add\n2. Retrieve\n3. Display\n4. Back");
            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.print("Enter ID: "); int id = sc.nextInt();
                    System.out.print("Enter Name: "); sc.nextLine();
                    empManager.addEmployee(id, sc.nextLine());
                }
                case 2 -> {
                    System.out.print("Enter ID: "); int id = sc.nextInt();
                    try { System.out.println("Name: " + empManager.getEmployeeName(id)); }
                    catch (EmployeeNotFoundException e) { System.out.println(e.getMessage()); }
                }
                case 3 -> empManager.displayEmployees();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void productMenu(ProductManagement prodManager) {
        while (true) {
            System.out.println("\nProduct Menu:\n1. Add\n2. Discount\n3. Display\n4. Back");
            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.print("Enter Product ID: "); String id = sc.next();
                    System.out.print("Enter Price: "); double price = sc.nextDouble();
                    prodManager.addProduct(id, price);
                }
                case 2 -> {
                    System.out.print("Enter Product ID: "); String id = sc.next();
                    System.out.print("Enter Discount %: "); double percent = sc.nextDouble();
                    try { prodManager.applyDiscount(id, percent); }
                    catch (ProductNotFoundException | IllegalArgumentException e) { System.out.println(e.getMessage()); }
                }
                case 3 -> prodManager.displayProducts();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
