package finalproject.mobilecomputing.bidbox.models;

/**
 * Created by Taiwo on 5/15/2018.
 */

public class Bid {

    private Double price;
    private String bidderId;

    public Bid() {

    }

    public Bid(Double price, String bidderId) {
        this.price = price;
        this.bidderId = bidderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBidderId() {
        return bidderId;
    }

    public void setBidderId(String bidderId) {
        this.bidderId = bidderId;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "price=" + price +
                ", bidderId='" + bidderId + '\'' +
                '}';
    }
}
