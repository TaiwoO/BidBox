package finalproject.mobilecomputing.bidbox;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import finalproject.mobilecomputing.bidbox.R;
import finalproject.mobilecomputing.bidbox.adapters.CurrentBidItemAdapter;
import finalproject.mobilecomputing.bidbox.models.Book;

public class CurrentBidActivity extends AppCompatActivity {


    private ActionBar actionBar;
    private List<Book> books;
    private ListView bidItemListView;
    private static CurrentBidItemAdapter currentBidItemAdapter;

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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        bidItemListView = (ListView)findViewById(R.id.current_bid_item_listview);
        books = new ArrayList<>();
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


        currentBidItemAdapter = new CurrentBidItemAdapter(this, books);
        bidItemListView.setAdapter(currentBidItemAdapter);
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