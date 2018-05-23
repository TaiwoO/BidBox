package finalproject.mobilecomputing.bidbox.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Taiwo on 5/15/2018.
 */

public class Bid implements Serializable{

    @SerializedName("_id")
    private String id;
    private Double price;
    @SerializedName("user")
    private String bidderUserId;

    public Bid() {

    }

    public Bid(String id, Double price, String bidderUserId) {
        this.id = id;
        this.price = price;
        this.bidderUserId = bidderUserId;
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

    public String getBidderUserId() {
        return bidderUserId;
    }

    public void setBidderUserId(String bidderUserId) {
        this.bidderUserId = bidderUserId;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", bidderUserId='" + bidderUserId + '\'' +
                '}';
    }
}
