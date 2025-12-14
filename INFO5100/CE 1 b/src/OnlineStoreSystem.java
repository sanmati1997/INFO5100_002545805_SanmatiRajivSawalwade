import java.util.*;

public class OnlineStoreSystem {

    static String[] orderData = {
            "John,Laptop,1,899.99", "Sarah,Mouse,2,25.50", "Mike,Keyboard,1,75.00",
            "John,Monitor,1,299.99", "Sarah,Laptop,1,899.99", "Lisa,Mouse,3,25.50",
            "Mike,Headphones,1,150.00", "John,Mouse,1,25.50", "Lisa,Keyboard,2,75.00",
            "Sarah,Monitor,2,299.99", "Mike,Monitor,1,299.99", "Lisa,Headphones,1,150.00"
    };

    public static void main(String[] args) {

        System.out.println("=== ONLINE STORE ORDER PROCESSING SYSTEM ===\n");

        step1_ArrayList();
        step2_HashSet();
        step3_TreeSet();
        step4_HashMap();
        step5_Queue();
        step6_Stack();
    }

    // ------------------------------------------------------------
    // STEP 1: ArrayList - Store all orders
    // ------------------------------------------------------------
    static void step1_ArrayList() {
        System.out.println("STEP 1: Managing orders with ArrayList");

        ArrayList<String> orders = new ArrayList<>();
        Collections.addAll(orders, orderData);

        System.out.println("Total orders: " + orders.size());

        System.out.println("First 3 orders:");
        for (int i = 0; i < 3; i++) {
            System.out.println("  " + orders.get(i));
        }

        System.out.println();
    }

    // ------------------------------------------------------------
    // STEP 2: HashSet - Unique customers
    // ------------------------------------------------------------
    static void step2_HashSet() {
        System.out.println("STEP 2: Finding customers with HashSet");

        HashSet<String> customers = new HashSet<>();

        for (String order : orderData) {
            String[] parts = order.split(",");
            customers.add(parts[0]); // customer name
        }

        System.out.println("Unique customers: " + customers);
        System.out.println("Total customers: " + customers.size());
        System.out.println();
    }

    // ------------------------------------------------------------
    // STEP 3: TreeSet - Sorted products
    // ------------------------------------------------------------
    static void step3_TreeSet() {
        System.out.println("STEP 3: Sorting products with TreeSet");

        TreeSet<String> products = new TreeSet<>();

        for (String order : orderData) {
            String[] parts = order.split(",");
            products.add(parts[1]); // product name
        }

        System.out.println("Products (sorted): " + products);
        System.out.println("Total products: " + products.size());
        System.out.println();
    }

    // ------------------------------------------------------------
    // STEP 4: HashMap - Totals
    // ------------------------------------------------------------
    static void step4_HashMap() {
        System.out.println("STEP 4: Calculating totals with HashMap");

        HashMap<String, Double> customerTotal = new HashMap<>();
        HashMap<String, Integer> productQuantity = new HashMap<>();

        for (String order : orderData) {
            String[] parts = order.split(",");
            String customer = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);
            double price = Double.parseDouble(parts[3]);

            double totalPrice = quantity * price;

            // Customer total spent
            customerTotal.put(customer, customerTotal.getOrDefault(customer, 0.0) + totalPrice);

            // Product quantity sold
            productQuantity.put(product, productQuantity.getOrDefault(product, 0) + quantity);
        }

        System.out.println("Total spent by each customer:");
        for (String c : customerTotal.keySet()) {
            System.out.println("  " + c + ": $" + customerTotal.get(c));
        }

        System.out.println("\nTotal quantity sold per product:");
        for (String p : productQuantity.keySet()) {
            System.out.println("  " + p + ": " + productQuantity.get(p));
        }

        System.out.println();
    }

    // ------------------------------------------------------------
    // STEP 5: Queue - Big Orders ($200+)
    // ------------------------------------------------------------
    static void step5_Queue() {
        System.out.println("STEP 5: Processing big orders with Queue");

        Queue<String> bigOrders = new LinkedList<>();

        for (String order : orderData) {
            String[] parts = order.split(",");
            int quantity = Integer.parseInt(parts[2]);
            double price = Double.parseDouble(parts[3]);

            if (quantity * price >= 200) {
                bigOrders.add(order);
            }
        }

        System.out.println("Processing big orders (FIFO):");
        while (!bigOrders.isEmpty()) {
            System.out.println("  Processing: " + bigOrders.poll());
        }

        System.out.println();
    }

    // ------------------------------------------------------------
    // STEP 6: Stack - Returns
    // ------------------------------------------------------------
    static void step6_Stack() {
        System.out.println("STEP 6: Handling returns with Stack");

        Stack<String> returns = new Stack<>();

        // Sample returns
        returns.push("Sarah,Mouse,1");
        returns.push("John,Keyboard,1");
        returns.push("Lisa,Monitor,1");

        System.out.println("Processing returns (LIFO):");
        while (!returns.isEmpty()) {
            System.out.println("  Processing return: " + returns.pop());
        }

        System.out.println();
    }
}
1