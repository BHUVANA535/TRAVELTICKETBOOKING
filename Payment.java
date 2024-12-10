import java.util.ArrayList;
import java.util.List;

public class Payment {
    //we're setting these attributes as private as they contain sensitive info and shouldn't be easily accessible
    private String cardNumber;
    private String expiryDate;
    private String cvv;


    // constructor to initialize payment details
    public Payment(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }


    // getter for cardNumber
    public String getCardNumber() {
        return cardNumber;
    }

    // setter for cardNumber
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // getter for expiryDate
    public String getExpiryDate() {
        return expiryDate;
    }

    // setter for expiryDate
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    // getter for cvv
    public String getCvv() {
        return cvv;
    }

    // setter for cvv
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    // overriding toString method to return payment details
    public String toString() {
        return "Payment [cardNumber=" + cardNumber + ", expiryDate=" + expiryDate + ", cvv=" + cvv + "]";
    }

   
    // declaring the list to store payment history
    private static List<Payment> paymentHistory = new ArrayList<>();
}
