package finalproject.mobilecomputing.bidbox.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Taiwo on 5/15/2018.
 */

public class Auction implements Serializable {

    @SerializedName("_id")
    private String id;
    private Book book;
    private List<Bid> bids;
    private Double askingPrice;
    private String startDate;
    private String endDate;
    private String auctioneerUserId;

    public Auction() {

    }

    public Auction(String id, Book book, List<Bid> bids, Double askingPrice, String startDate, String endDate, String auctioneerUserId) {
        this.id = id;
        this.book = book;
        this.bids = bids;
        this.askingPrice = askingPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.auctioneerUserId = auctioneerUserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public Double getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(Double askingPrice) {
        this.askingPrice = askingPrice;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAuctioneerUserId() {
        return auctioneerUserId;
    }

    public void setAuctioneerUserId(String auctioneerUserId) {
        this.auctioneerUserId = auctioneerUserId;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id='" + id + '\'' +
                ", book=" + book +
                ", bids=" + bids +
                ", askingPrice=" + askingPrice +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", auctioneerUserId='" + auctioneerUserId + '\'' +
                '}';
    }
}
