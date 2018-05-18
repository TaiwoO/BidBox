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

import finalproject.mobilecomputing.bidbox.adapters.BidItemAdapter;
import finalproject.mobilecomputing.bidbox.api.BidBox.BidBoxApiInterface;
import finalproject.mobilecomputing.bidbox.models.Auction;
import finalproject.mobilecomputing.bidbox.models.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = HomeActivity.class.getSimpleName();
    private ActionBar actionBar;
    private List<Book> books;
    private ListView bidItemListView;
    private FloatingActionButton addAuctionBtn;
    private static BidItemAdapter bidItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        bidItemListView = (ListView) findViewById(R.id.bid_item_listview);
        addAuctionBtn = (FloatingActionButton) findViewById(R.id.home_add_auction_fab);
        addAuctionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNewAuctionActivity();
            }
        });

        books = new ArrayList<>();
        bidItemAdapter = new BidItemAdapter(this, books);
        bidItemListView.setAdapter(bidItemAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://a0bb7b7e.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BidBoxApiInterface bidBoxService = retrofit.create(BidBoxApiInterface.class);
        Call<List<Auction>> getAllAuctionsCall = bidBoxService.getAllAuctions();
        getAllAuctionsCall.enqueue(new Callback<List<Auction>>() {
            @Override
            public void onResponse(Call<List<Auction>> call, Response<List<Auction>> response) {
                int statusCode = response.code();
                List<Auction> allAuctions = response.body();
                for (Auction auction: allAuctions) {
                    books.add(auction.getBook());
                }
                bidItemAdapter.notifyDataSetChanged();
//                Log.d(TAG, "@@@@: " + allAuctions.get(0).toString());
            }

            @Override
            public void onFailure(Call<List<Auction>> call, Throwable t) {
                Log.d(TAG, "@@@@ AN ERROR OCCURED: ");
            }
        });


//        books = new ArrayList<>();
//        Book sampleBook = new Book();
//        sampleBook.setIsbn("11112222223333333");
//        sampleBook.setName("Chemistry one");
//        Book sampleBook2 = new Book();
//        sampleBook2.setIsbn("11112222223333333");
//        sampleBook2.setName("Chemistry two");
//        books.add(sampleBook);
//        books.add(sampleBook2);
//        books.add(sampleBook);
//        books.add(sampleBook2);
//        books.add(sampleBook);
//        books.add(sampleBook2);
//        books.add(sampleBook);
//        books.add(sampleBook2);
//        books.add(sampleBook);
//        books.add(sampleBook2);
//
//
//        bidItemAdapter = new BidItemAdapter(this, books);
//        bidItemListView.setAdapter(bidItemAdapter);

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
