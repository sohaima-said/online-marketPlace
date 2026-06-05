import java.io.Serializable;
import java.util.*;


public class CartItem implements Serializable {
    private Product product;
    private int quantity;

    public CartItem(Product p,int q){ product=p; quantity=q; }
    public Product getProduct(){ return product; }
    public int getQuantity(){ return quantity; }
    public double getSubtotal(){ return product.getPrice()*quantity; }
}
