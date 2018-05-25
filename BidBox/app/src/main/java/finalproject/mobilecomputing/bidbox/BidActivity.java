package finalproject.mobilecomputing.bidbox;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import finalproject.mobilecomputing.bidbox.adapters.AuctionItemAdapter;
import finalproject.mobilecomputing.bidbox.api.BidBox.BidBoxApiInterface;
import finalproject.mobilecomputing.bidbox.models.Auction;
import finalproject.mobilecomputing.bidbox.models.Bid;
import finalproject.mobilecomputing.bidbox.models.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sam m on 5/12/18.
 */



public class BidActivity extends AppCompatActivity implements OnClickListener {

    private ActionBar actionBar;
    private TextView bookName, isbnNUm, sellerInfo, sellerEmail, bidRemainingTime, currentMaxBid, askingBid;
    private EditText insertedBid;
    private static AuctionItemAdapter auctionItemAdapter;
    private List<Book> books;
    private Button bidBtn, purchaseBtn;
    private Auction passedAuction;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bidding);
        setTitle("Bid On this Book");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);


        //get instance from layout
        bookName = (TextView) findViewById(R.id.bid_item_name);
        isbnNUm = (TextView) findViewById(R.id.bid_item_isbn);
        sellerInfo = (TextView) findViewById(R.id.bid_item_sellerInfo);
//        sellerEmail = (TextView) findViewById(R.id.bid_item_sellerEmail);
        bidBtn = (Button) findViewById(R.id.bidding_addBid);
        bidRemainingTime = (TextView) findViewById(R.id.bid_item_timeRemaining);
        currentMaxBid= (TextView) findViewById(R.id.bid_item_currentBid);
        askingBid = (TextView) findViewById(R.id.bidding_purchaseNowText);
        insertedBid = (EditText) findViewById(R.id.bid_insertBidEditText);

        bidBtn.setOnClickListener(this);
        purchaseBtn = (Button) findViewById(R.id.bidding_purchaseNow);
        purchaseBtn.setOnClickListener(this);



        //getting Book object
        Intent i = getIntent();
        passedAuction=(Auction) i.getSerializableExtra("auction");
        Log.d("BidAcvtivity", passedAuction.toString());
       // bookName.setText(new String(passBook.getName() + passBook.getVersion()));
        //setting object on bid page, based on data that is pass

        bookName.setText(passedAuction.getBook().getName());
        isbnNUm.setText(passedAuction.getBook().getIsbn());
        sellerInfo.setText("Sold By UserID: "+passedAuction.getAuctioneerUserId());
        askingBid.setText("Buy now for: $"+ Double.toString(passedAuction.getAskingPrice()));
        bidRemainingTime.setText(passedAuction.getEndDate());
        //error happens here
        currentMaxBid.setText(getCurrentMaxBid(passedAuction));
        //bidding_purchaseNowText

        //setting the image
        // show The Image in a ImageView
        String imageUrl = passedAuction.getBook().getImgUrl();
        new DownloadImageTask((ImageView) findViewById(R.id.bid_item_img))
                .execute(imageUrl);


        //template for sending object to checkout
//        Deneme dene = new Deneme(4,"Mustafa");
//        Intent i = new Intent(this, Y.class);
//        i.putExtra("sampleObject", dene);
//        startActivity(i);

    }

    //getters and settings for views on bidding.xml
    public TextView getBookName() {
        return bookName;
    }

    public void setBookName(TextView bookName) {
        this.bookName = bookName;
    }

    public TextView getIsbnNUm() {
        return isbnNUm;
    }

    public void setIsbnNUm(TextView isbnNUm) {
        this.isbnNUm = isbnNUm;
    }

    public TextView getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(TextView sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public TextView getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(TextView sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    @Override
    //managing buttons and on click here
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.bidding_addBid:

                //code to add bid here
                String newBid = (insertedBid.getText().toString());
                Log.d("BidAcvtivity-New-Bid", newBid);
                sumbitNewBidForm(passedAuction, newBid); //add the new bid
                currentMaxBid.setText(getCurrentMaxBid(passedAuction)); //update currentBid based on highest Bid


                break;
            case R.id.bidding_purchaseNow:

                //code for purchase now goes here -> should either add to cart or go to checkout page.
                //should make call to send to prastusha here
                String requestedBid = new Double(passedAuction.getAskingPrice()).toString();
                sumbitNewBidForm(passedAuction, requestedBid); //add the new bid
                gotoPaymentActivity();
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_search:
                return super.onOptionsItemSelected(item);

            case R.id.profile:
                Intent intent = new Intent(this, CurrentBidActivity.class);
                startActivity(intent);
                return super.onOptionsItemSelected(item);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // adding a new bid to an auction

    private void sumbitNewBidForm(Auction auction, String newBid) {

        // User's Json Web Token is needed

        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String token = pref.getString("token", null);



        String price = newBid;

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl(getString(R.string.base_url))

                .addConverterFactory(GsonConverterFactory.create())

                .build();



        BidBoxApiInterface bidBoxService = retrofit.create(BidBoxApiInterface.class);

        Call<Void> addNewBidCall = bidBoxService.addNewBid(auction.getId(), price, token);

        addNewBidCall.enqueue(new Callback<Void>() {

            @Override

            public void onResponse(Call<Void> call, Response<Void> response) {

                int statusCode = response.code();



                if (statusCode == 200) {

                    Toast.makeText(getApplicationContext(), "Bid was added!", Toast.LENGTH_SHORT).show();



                } else {

                    Toast.makeText(getApplicationContext(), "Failed to add bid. Make sure you filled in all fields", Toast.LENGTH_SHORT).show();

                }

            }

            @Override

            public void onFailure(Call<Void> call, Throwable t) {


            }

        });

    }

    //get the current max Bid to be display in current Bid
    private String getCurrentMaxBid(Auction currentAuction){

        ArrayList<Double> Bids= new ArrayList<>();

        if(currentAuction.getBids().size()>0) {

            for (Bid myBids : currentAuction.getBids()) {
                Bids.add(myBids.getPrice());
            }

            double myMax = Collections.max(Bids);
            String maxBid = new Double(myMax).toString();
            Log.d("BidAcvtivity", maxBid);
            return maxBid;
        }
        else{
            return "0";
        }
    }

    private void gotoPaymentActivity() {
        Intent intent = new Intent(this, payment.class);
        startActivity(intent);
    }

    //class for adding image
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

