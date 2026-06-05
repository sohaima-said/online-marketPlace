public class Clothing extends Product {
    private String size,fabric;
    public Clothing(int id,String n,double p,int s,String sz,String f){
        super(id,n,p,s); size=sz; fabric=f;
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
    public int getStock() {
        return super.getStock();
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    public String getSize() {
        return size;
    }

    public String getFabric() {
        return fabric;
    }
}


