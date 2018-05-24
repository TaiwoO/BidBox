package finalproject.mobilecomputing.bidbox;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import finalproject.mobilecomputing.bidbox.R;
import finalproject.mobilecomputing.bidbox.adapters.CheckoutItemAdapter;
import finalproject.mobilecomputing.bidbox.adapters.CurrentBidItemAdapter;
import finalproject.mobilecomputing.bidbox.api.BidBox.BidBoxApiInterface;
import finalproject.mobilecomputing.bidbox.models.Book;
import finalproject.mobilecomputing.bidbox.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_SHORT;

public class CurrentBidActivity extends AppCompatActivity {


    private ActionBar actionBar;
    private List<Book> books;
    private ListView bidItemListView;
    private static CurrentBidItemAdapter currentBidItemAdapter;

    private static final String TAG = "currentbid";

    private Button checkout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_bid);

        final Context mcontext = this;

        checkout = (Button) findViewById(R.id.current_bid_checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( mcontext, "Should go to checkout pg now", Toast.LENGTH_SHORT).show();
            }
        });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        bidItemListView = (ListView)findViewById(R.id.current_bid_item_listview);

        getUserInfo();
    }

    private void getUserInfo() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String token = pref.getString("token", null);
        //bidItemListView = (ListView) findViewById(R.id.checkout_item_listview);
        final Context mcontext = this;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Log.d(TAG, "The User: ");

        BidBoxApiInterface bidBoxService = retrofit.create(BidBoxApiInterface.class);
        Call<User> getUserInfoCall = bidBoxService.getUserInfo(token);
        getUserInfoCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                double sum = 0;
                int statusCode = response.code();
                Log.d(TAG, "on response called: ");

                User user = response.body();

                if (statusCode == 200) {
                    //Toast.makeText(mcontext, "GOT USER INFO", LENGTH_SHORT).show();

                    books = user.getShoppingChart();


                    currentBidItemAdapter = new CurrentBidItemAdapter(mcontext, books);
                    bidItemListView.setAdapter(currentBidItemAdapter);









                    Log.d(TAG, "good status: " + user.toString());
                } else {
                    Toast.makeText(mcontext, "Failed to get user info", LENGTH_SHORT).show();
                    Log.d(TAG, "failure");

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "failure");
                Log.d(TAG, t.getMessage());

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


}
