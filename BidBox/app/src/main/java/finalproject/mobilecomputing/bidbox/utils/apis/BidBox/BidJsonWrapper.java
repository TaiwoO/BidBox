package finalproject.mobilecomputing.bidbox.utils.apis.BidBox;

import org.json.JSONException;
import org.json.JSONObject;

import finalproject.mobilecomputing.bidbox.models.Bid;

/**
 * Created by Taiwo on 5/15/2018.
 */

public final class BidJsonWrapper {

    private BidJsonWrapper() {
        throw new UnsupportedOperationException();
    }

    public static Bid createFromJson(JSONObject bidJson) {
        Bid bid = new Bid();
        if (bidJson == null) {
            return null;
        }

        try {
            String id = bidJson.getString("_id");
            Double price = bidJson.getDouble("price");
            String bidderId = bidJson.getString("user");

            bid.setId(id);
            bid.setPrice(price);
            bid.setBidderId(bidderId);

            return bid;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
