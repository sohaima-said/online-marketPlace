import java.io.Serializable;

public abstract class Product implements Serializable {
    protected int id;
    protected String name;
    protected double price;
    protected int stock;

    public Product(int id,String name,double price,int stock){
        this.id=id; this.name=name; this.price=price; this.stock=stock;
    }

    public int getId(){ return id; }
    public String getName(){ return name; }
    public double getPrice(){ return price; }
    public int getStock(){ return stock; }

    public boolean reduceStock(int q){
        if(q>0 && stock>=q){ stock-=q; return true; }
        return false;
    }

    public void addStock(int q){ if(q>0) this.stock+=q; }

    @Override
    public String toString(){
        return name+" ($"+price+") Stock:"+stock;
    }
}


