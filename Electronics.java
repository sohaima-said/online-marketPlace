public class Electronics extends Product {
    private String warranty;
    private String energy;

    public Electronics(int id, String n, double p, int s, String w, String e) {
        super(id, n, p, s);
        warranty = w;
        energy = e;
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

    public String getEnergy() {
        return energy;
    }

    public String getWarranty() {
        return warranty;
    }
}