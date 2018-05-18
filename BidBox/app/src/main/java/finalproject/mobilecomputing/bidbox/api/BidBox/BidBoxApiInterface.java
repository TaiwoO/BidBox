package finalproject.mobilecomputing.bidbox.api.BidBox;

import java.util.List;

import finalproject.mobilecomputing.bidbox.models.Auction;
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

/**
 * Created by Taiwo on 5/17/2018.
 */

public interface BidBoxApiInterface {

    @GET("auction/all")
    Call<List<Auction>> getAllAuctions();

//    @FormUrlEncoded
//    @POST("user/auction")
//    Call<Void> addNewAuction(@Field("name") String name,
//                       @Field("isbn") String isbn,
//                       @Field("version") String version,
//                       @Field("condition") String condition,
//                       @Field("askingPrice") String askingPrice,
//                       @Header("Authorization") String token);

    @Multipart
    @POST("user/auction")
    Call<Void> addNewAuction(@Part MultipartBody.Part image,
                             @Part("name") RequestBody name,
                             @Part("isbn") RequestBody isbn,
                             @Part("version") RequestBody version,
                             @Part("condition") RequestBody condition,
                             @Part("askingPrice") RequestBody askingPrice,
                             @Header("Authorization") String token);

}
