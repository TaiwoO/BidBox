package finalproject.mobilecomputing.bidbox.api.BidBox;

import java.util.List;

import finalproject.mobilecomputing.bidbox.models.Auction;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Taiwo on 5/17/2018.
 */

public interface BidBoxApiInterface {

    @GET("auction/all")
    Call<List<Auction>> getAllAuctions();
}
