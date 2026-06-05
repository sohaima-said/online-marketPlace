public class CreditCardPayment implements Payment {
    private String card;
    private double balance;
    public CreditCardPayment(String c,double b) {
        card=c;
        balance=b;
    }
    @Override
    public boolean pay(double amt){
        if(card != null && card.matches("\\d{16}")){
            balance-=amt;
            System.out.println("Card payment successful");
            return true;
        }
        else System.out.println("Payment failed");
        return false;
    }
}


