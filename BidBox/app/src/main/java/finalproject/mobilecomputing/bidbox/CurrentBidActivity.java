package finalproject.mobilecomputing.bidbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import finalproject.mobilecomputing.bidbox.R;
import finalproject.mobilecomputing.bidbox.adapters.CurrentBidItemAdapter;
import finalproject.mobilecomputing.bidbox.models.Book;

public class CurrentBidActivity extends AppCompatActivity {


    private List<Book> books;
    private ListView bidItemListView;
    private static CurrentBidItemAdapter currentBidItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_bid);

        bidItemListView = (ListView)findViewById(R.id.current_bid_item_listview);
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


        currentBidItemAdapter = new CurrentBidItemAdapter(this, books);
        bidItemListView.setAdapter(currentBidItemAdapter);
    }


}
