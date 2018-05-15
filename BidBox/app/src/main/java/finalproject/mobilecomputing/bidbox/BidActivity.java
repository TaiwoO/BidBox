package finalproject.mobilecomputing.bidbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import finalproject.mobilecomputing.bidbox.adapters.BidItemAdapter;
import finalproject.mobilecomputing.bidbox.models.Book;

/**
 * Created by sam m on 5/12/18.
 */



public class BidActivity extends AppCompatActivity implements OnClickListener {

    private TextView bookName, isbnNUm, sellerInfo, sellerEmail;
    private static BidItemAdapter bidItemAdapter;
    private List<Book> books;
    private Button bidBtn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bidding);


        //get instance from layout
        bookName = (TextView) findViewById(R.id.bid_item_name);
        isbnNUm = (TextView) findViewById(R.id.bid_item_isbn);
        sellerInfo = (TextView) findViewById(R.id.bid_item_sellerInfo);
        sellerEmail = (TextView) findViewById(R.id.bid_item_sellerEmail);
        bidBtn = (Button) findViewById(R.id.bidding_addBid);
        bidBtn.setOnClickListener(this);



        //linking with book and adapter - code goes here
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

                //code to mage buttons goes here

                break;
        }
    }
}