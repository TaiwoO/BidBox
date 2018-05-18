package finalproject.mobilecomputing.bidbox.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Taiwo on 5/17/2018.
 */

public class User {

    @SerializedName("_id")
    String id;
    String name;
    String email;
    List<Auction> auctions;
    List<Bid> bids;
    List<Book> shoppingChart;

    public User() {

    }

    public User(String id, String name, String email, List<Auction> auctions, List<Bid> bids, List<Book> shoppingChart) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.auctions = auctions;
        this.bids = bids;
        this.shoppingChart = shoppingChart;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public List<Book> getShoppingChart() {
        return shoppingChart;
    }

    public void setShoppingChart(List<Book> shoppingChart) {
        this.shoppingChart = shoppingChart;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", auctions=" + auctions +
                ", bids=" + bids +
                ", shoppingChart=" + shoppingChart +
                '}';
    }
}
