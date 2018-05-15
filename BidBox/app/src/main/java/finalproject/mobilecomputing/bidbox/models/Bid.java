package finalproject.mobilecomputing.bidbox.models;

/**
 * Created by Taiwo on 5/15/2018.
 */

public class Bid {

    private String id; // The bid itself
    private Double price;
    private String bidderId; // User that made the bid

    public Bid() {

    }

    public Bid(Double price, String bidderId) {
        this.price = price;
        this.bidderId = bidderId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "id='" + id + '\'' +
                ", price=" + price +
                ", bidderId='" + bidderId + '\'' +
                '}';
    }
}
