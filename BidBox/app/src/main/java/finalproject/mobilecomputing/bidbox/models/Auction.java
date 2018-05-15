package finalproject.mobilecomputing.bidbox.models;

import java.util.Date;
import java.util.List;

/**
 * Created by Taiwo on 5/15/2018.
 */

public class Auction {

    private Book book;
    private List<Bid> bids;
    private Double askingPrice;
    private Date startDate;
    private Date endDate;
    private String auctionerId;

    public Auction() {

    }

    public Auction(Book book, List<Bid> bids, Double askingPrice, Date startDate, Date endDate, String auctionerId) {
        this.book = book;
        this.bids = bids;
        this.askingPrice = askingPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.auctionerId = auctionerId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAuctionerId() {
        return auctionerId;
    }

    public void setAuctionerId(String auctionerId) {
        this.auctionerId = auctionerId;
    }
}
