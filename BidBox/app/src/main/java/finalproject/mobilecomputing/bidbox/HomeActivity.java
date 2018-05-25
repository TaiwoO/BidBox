package finalproject.mobilecomputing.bidbox;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import finalproject.mobilecomputing.bidbox.adapters.AuctionItemAdapter;
import finalproject.mobilecomputing.bidbox.api.BidBox.BidBoxApiInterface;
import finalproject.mobilecomputing.bidbox.models.Auction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = HomeActivity.class.getSimpleName();
    private ActionBar actionBar;
    private List<Auction> auctions;
    private ListView auctionItemListView;
    private FloatingActionButton addAuctionBtn;
    private static AuctionItemAdapter auctionItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayHomeAsUpEnabled(true);

        auctionItemListView = (ListView) findViewById(R.id.bid_item_listview);
        addAuctionBtn = (FloatingActionButton) findViewById(R.id.home_add_auction_fab);
        addAuctionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNewAuctionActivity();
            }
        });

        auctions = new ArrayList<>();
        auctionItemAdapter = new AuctionItemAdapter(this);
        auctionItemListView.setAdapter(auctionItemAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BidBoxApiInterface bidBoxService = retrofit.create(BidBoxApiInterface.class);
        Call<List<Auction>> getAllAuctionsCall = bidBoxService.getAllAuctions();
        getAllAuctionsCall.enqueue(new Callback<List<Auction>>() {
            @Override
            public void onResponse(Call<List<Auction>> call, Response<List<Auction>> response) {
                int statusCode = response.code();
                auctions = response.body();
                auctionItemAdapter.addAll(auctions);
                auctionItemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Auction>> call, Throwable t) {
                Log.d(TAG, "@@@@ AN ERROR OCCURED: ");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                System.out.println("...");
                return super.onOptionsItemSelected(item);

            case R.id.profile:
                System.out.println("...");
                Intent intent = new Intent(this, CurrentBidActivity.class);
                startActivity(intent);
                return super.onOptionsItemSelected(item);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void gotoNewAuctionActivity() {
        Intent intent = new Intent(this, NewAuctionActivity.class);
        startActivity(intent);
    }
}
