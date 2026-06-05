public class Customer extends User {
    private Cart cart = new Cart();

    public Customer(String name, String email) { super(name, email); }
    public Cart getCart() { return cart; }
}

