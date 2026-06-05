public class InventoryManager {
    private static final int LOW_STOCK = 5;

    public static boolean reduce(Product p, int q) {
        boolean ok = p.reduceStock(q);
        if (ok && p.getStock() <= LOW_STOCK)
            System.out.println("LOW STOCK: " + p.getName());
        return ok;
    }

    public static void add(Product p, int q) {
        p.addStock(q);
        if (p.getStock() <= LOW_STOCK)
            System.out.println("LOW STOCK: " + p.getName());
    }
}
