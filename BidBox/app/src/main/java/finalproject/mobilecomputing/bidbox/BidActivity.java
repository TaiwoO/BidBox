package finalproject.mobilecomputing.bidbox;

import android.content.Intent;
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
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import finalproject.mobilecomputing.bidbox.adapters.BidItemAdapter;
import finalproject.mobilecomputing.bidbox.models.Book;

/**
 * Created by sam m on 5/12/18.
 */



public class BidActivity extends AppCompatActivity implements OnClickListener {

    private ActionBar actionBar;
    private TextView bookName, isbnNUm, sellerInfo, sellerEmail;
    private static BidItemAdapter bidItemAdapter;
    private List<Book> books;
    private Button bidBtn, purchaseBtn;
    private Book passBook;


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
        sellerEmail = (TextView) findViewById(R.id.bid_item_sellerEmail);
        bidBtn = (Button) findViewById(R.id.bidding_addBid);
        bidBtn.setOnClickListener(this);
        purchaseBtn = (Button) findViewById(R.id.bidding_purchaseNow);
        purchaseBtn.setOnClickListener(this);



        //getting Book object
        Intent i = getIntent();
        passBook=(Book)i.getSerializableExtra("book");
        Log.d("BidAcvtivity", passBook.toString());
       // bookName.setText(new String(passBook.getName() + passBook.getVersion()));
        //setting object on bid page, based on data that is pass

        bookName.setText(passBook.getName());
        isbnNUm.setText(passBook.getIsbn());


        //setting book
//        this.id="000";
//        this.name="TestBook";
//        this.version="v9";
//        this.ownerID="sss1";
//        this.condition="New";
//        this.isbn="000000";

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

                break;
            case R.id.bidding_purchaseNow:

                //code for purchase now goes here
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
}