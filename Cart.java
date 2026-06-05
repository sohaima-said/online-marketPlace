import java.io.Serializable;
import java.util.*;

public class Cart implements Serializable {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product p,int q){ items.add(new CartItem(p,q)); }
    public List<CartItem> getItems(){ return items; }
    public double total(){ return items.stream().mapToDouble(CartItem::getSubtotal).sum(); }
    public void clear(){ items.clear(); }
}
