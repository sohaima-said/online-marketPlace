public class Grocery extends Product {
    private String expiry;
    public Grocery(int id,String n,double p,int s,String e){
        super(id,n,p,s); expiry=e;
    }

    @Override
    public int getId() {
        return super.getId();
    }
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public int getStock() {
        return super.getStock();
    }

    public String getExpiry() {
        return expiry;
    }
}

