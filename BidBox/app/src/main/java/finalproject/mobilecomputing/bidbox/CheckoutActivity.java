package finalproject.mobilecomputing.bidbox;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

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

public class CheckoutActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private List<Book> books;
    private ListView checkoutItemListView;
    private TextView total;
    private static CheckoutItemAdapter checkoutItemAdapter;
    final Context mcontext = this;
    private static final String TAG = "assignment2";
    private Button proceed;

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

    private void getUserInfo() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String token = pref.getString("token", null);
        checkoutItemListView = (ListView) findViewById(R.id.checkout_item_listview);
        total = (TextView) findViewById(R.id.total_cost);


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
                    Toast.makeText(mcontext, "GOT USER INFO", LENGTH_SHORT).show();

                    books = user.getShoppingChart();


                    checkoutItemAdapter = new CheckoutItemAdapter(mcontext, books);
                    checkoutItemListView.setAdapter(checkoutItemAdapter);

                    for (int i = 0; i < books.size(); i++) {
                        sum += books.get(i).getPrice();
                    }

                    total.setText("$" + (int) sum);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_checkout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        total = (TextView) findViewById(R.id.total_cost);

        setSupportActionBar(toolbar);

        final Context mcontext = this;

        List<Book> shoppingCart;

        proceed = (Button) findViewById(R.id.button_proceed_toPayment);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mcontext, "Should go to payment pg now", LENGTH_SHORT).show();

                String temp = total.getText().toString();
                temp = temp.substring(1);
                double sum = Double.parseDouble(temp);
                Log.d(TAG, "SUM: " + sum);
                startPaymentActivity(sum);

            }
        });

        getUserInfo();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    void startPaymentActivity(double total){
        Intent intent = new Intent(this, payment.class);
        intent.putExtra("total", total);
        startActivity(intent);

    }


}
        //checkoutItemListView = (ListView)findViewById(R.id.checkout_item_listview);
       // books = new ArrayList<>();
        /*
        Book sampleBook = new Book();
        sampleBook.setIsbn("11112222223333333");
        sampleBook.setName("Organic Chemistry 10th Edition");
        Book sampleBook2 = new Book();
        sampleBook2.setIsbn("999998888877777666");
        sampleBook2.setName("Advanced Level Computer Science");
        books.add(sampleBook);
        books.add(sampleBook2);
        books.add(sampleBook);
        books.add(sampleBook2);
        books.add(sampleBook);
        books.add(sampleBook2);
        books.add(sampleBook);
        books.add(sampleBook2);
        books.add(sampleBook);
        books.add(sampleBook2);


        checkoutItemAdapter = new CheckoutItemAdapter(this, books);
        checkoutItemListView.setAdapter(checkoutItemAdapter);
        */
   // }

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    /*
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
                Intent intent = new Intent(this, CheckoutActivity.class);
                startActivity(intent);
                return super.onOptionsItemSelected(item);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    */
//}


