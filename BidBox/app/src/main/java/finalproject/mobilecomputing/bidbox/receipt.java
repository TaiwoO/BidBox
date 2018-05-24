package finalproject.mobilecomputing.bidbox;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class receipt extends AppCompatActivity {

    TextView thank_you, ordernumber,orderdetails,totalReceipt, totalPrice;
    Button button_continue;
    String total_checkout;
    Random rand = new Random();
    int order_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        thank_you = (TextView)findViewById(R.id.text_thankYou);
        ordernumber = (TextView)findViewById(R.id.text_orderNumber);
        orderdetails = (TextView)findViewById(R.id.text_orderdetails);
        totalPrice = (TextView)findViewById(R.id.text_receiptPrice);
        totalReceipt = (TextView)findViewById(R.id.text_receiptTotal);
        button_continue = (Button) findViewById(R.id.button_continue);


        Typeface customFont_bold = Typeface.createFromAsset(getAssets(),"fonts/AvenirNextLTPro-Demi.otf");
        Typeface customFont_regular = Typeface.createFromAsset(getAssets(),"fonts/AvenirNextLTPro-Regular.otf");

        thank_you.setTypeface(customFont_bold);
        ordernumber.setTypeface(customFont_regular);
        orderdetails.setTypeface(customFont_regular);
        totalReceipt.setTypeface(customFont_bold);
        totalPrice.setTypeface(customFont_bold);
        button_continue.setTypeface(customFont_bold);


        //To Do : OrderDetails

        order_num = rand.nextInt(9000000) + 1000000;
        ordernumber.setText("Order#:"+ order_num);
        total_checkout=getIntent().getStringExtra("total");
        totalPrice.setText("$"+total_checkout);

        orderdetails.setText("Organic Chemistry 10th Edition \n" +
                "ISBN# 9812003821903 \n Quantity:1 ----- Price:$25.00 \n \n" +
                " Advanced Level Computer Science \n ISBN# 532398054190 \n Quantity:1 ----- Price:$30.00");

        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goto_homepage = new Intent(getBaseContext(), HomeActivity.class);
               goto_homepage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(goto_homepage);
            }
        });




    }
}
