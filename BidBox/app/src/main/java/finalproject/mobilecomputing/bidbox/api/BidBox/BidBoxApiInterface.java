package finalproject.mobilecomputing.bidbox.api.BidBox;

import java.util.List;

import finalproject.mobilecomputing.bidbox.models.Auction;
import finalproject.mobilecomputing.bidbox.models.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Taiwo on 5/17/2018.
 */

public interface BidBoxApiInterface {

    @GET("auction/all")
    Call<List<Auction>> getAllAuctions();

    // Returns a User object of the currently logged-in user
    @GET("user")
    Call<User> getUserInfo(@Header("Authorization") String token);

    @GET("user/{userId}")
    Call<User> getUserInfoById(@Path("userId") String userId);

    @Multipart
    @POST("user/auction")
    Call<Void> addNewAuction(@Part MultipartBody.Part image,
                             @Part("name") RequestBody name,
                             @Part("isbn") RequestBody isbn,
                             @Part("version") RequestBody version,
                             @Part("condition") RequestBody condition,
                             @Part("askingPrice") RequestBody askingPrice,
                             @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("user/auction/{auctionId}/bid")
    Call<Void> addNewBid(@Path("auctionId") String auctionId,
                         @Field("price") String price,
                         @Header("Authorization") String token);


    @FormUrlEncoded
    @POST("user/shoppingchart")
    Call<Void> addToShoppingChart(@Field("bookid") String bookId,
                                  @Header("Authorization") String token);


}
