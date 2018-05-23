package finalproject.mobilecomputing.bidbox.utils.apis.BidBox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import finalproject.mobilecomputing.bidbox.models.Auction;
import finalproject.mobilecomputing.bidbox.models.Bid;
import finalproject.mobilecomputing.bidbox.models.Book;

/**
 * Created by Taiwo on 5/15/2018.
 */

public final class AuctionJsonWrapper {

    private AuctionJsonWrapper() {
        throw new UnsupportedOperationException();
    }

    public static Auction createFromJson(JSONObject auctionJson) {
        Auction auction = new Auction();
        if (auctionJson == null) {
            return null;
        }

        try {
            String id = auctionJson.getString("_id");
            String auctioneerId = auctionJson.getString("user");
            String startDate = auctionJson.optString("startDate");
            String endDate = auctionJson.optString("endDate");
            Double askingPrice = auctionJson.optDouble("askingPrice");
            Book book = BookJsonWrapper.createFromJson(auctionJson.getJSONObject("book"));

            List<Bid> bids = new ArrayList<>();
            JSONArray bidJsons = auctionJson.getJSONArray("bids");
            for (int i = 0; i < bidJsons.length(); i++) {
                JSONObject bidJson = bidJsons.getJSONObject(i);
                Bid bid = BidJsonWrapper.createFromJson(bidJson);
                bids.add(bid);
            }

            auction.setId(id);
            auction.setBook(book);
            auction.setBids(bids);
            auction.setAskingPrice(askingPrice);
            auction.setStartDate(startDate);
            auction.setEndDate(endDate);
            auction.setAuctioneerUserId(auctioneerId);

            return auction;

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
