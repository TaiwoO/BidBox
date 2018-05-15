package finalproject.mobilecomputing.bidbox;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import finalproject.mobilecomputing.bidbox.adapters.BidItemAdapter;
import finalproject.mobilecomputing.bidbox.models.Book;

public class HomeActivity extends AppCompatActivity {

    private List<Book> books;
    private ListView bidItemListView;
    private FloatingActionButton addAuctionBtn;
    private static BidItemAdapter bidItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bidItemListView = (ListView)findViewById(R.id.bid_item_listview);
        addAuctionBtn = (FloatingActionButton) findViewById(R.id.home_add_auction_fab);
        addAuctionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gotoNewAuctionActivity();

            }
        });
        books = new ArrayList<>();
        Book sampleBook = new Book();
        sampleBook.setIsbn("11112222223333333");
        sampleBook.setName("Chemistry one");
        Book sampleBook2 = new Book();
        sampleBook2.setIsbn("11112222223333333");
        sampleBook2.setName("Chemistry two");
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


        bidItemAdapter = new BidItemAdapter(this, books);
        bidItemListView.setAdapter(bidItemAdapter);
    }

    private void gotoNewAuctionActivity() {
        Intent intent = new Intent(this, NewAuctionActivity.class);
        startActivity(intent);
    }
}
