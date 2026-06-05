public class ECommerceApp {
    public static void main(String[] args) {
        ProductStore.loadProducts();
        Authentication auth = new Authentication();
        AdminAuthentication adminAuth = new AdminAuthentication();

        if (adminAuth.getAllAdmins().isEmpty()) {
            adminAuth.register("Admin", "admin@email.com", 1, "1234");
        }

        if (ProductStore.getAllProducts().isEmpty()) {
            ProductStore.addProduct(new Electronics(1, "Laptop", 1200, 0, "2 Years", "A++"));
            ProductStore.addProduct(new Grocery(2, "Milk", 2, 0, "2025-12-20"));
            ProductStore.addProduct(new Clothing(3, "T-Shirt", 10, 0, "L", "Cotton"));
            ProductStore.saveProducts();
        }

        new LoginFrame(auth, adminAuth);

    }
}