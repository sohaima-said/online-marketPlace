public class CashPayment implements Payment {
    public boolean pay(double amt){
        System.out.println("Cash paid: "+amt);
        return  true;
    }
}


