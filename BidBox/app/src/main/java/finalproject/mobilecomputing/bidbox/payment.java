package finalproject.mobilecomputing.bidbox;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class payment extends AppCompatActivity {

    public TextView payment, saved_cards,cardNumber, payment_method, total,price;
    Button button_credit_debit ,  button_campus_card,button_paypal,button_pay;
    String total_checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payment = (TextView)findViewById(R.id.text_payment);
        saved_cards = (TextView)findViewById(R.id.text_savedCard);
        cardNumber = (TextView)findViewById(R.id.text_savedCard);
        payment_method=(TextView)findViewById(R.id.text_paymentMethods);
        total = (TextView)findViewById(R.id.text_total);
        price = (TextView)findViewById(R.id.text_price);

        button_campus_card = (Button)findViewById(R.id.button_campusCard);
        button_credit_debit = (Button)findViewById(R.id.button_credit_debit);
        button_paypal = (Button)findViewById(R.id.button_Paypal);
        button_pay = (Button)findViewById(R.id.button_pay);


        Typeface customFont_bold = Typeface.createFromAsset(getAssets(),"fonts/AvenirNextLTPro-Demi.otf");
        Typeface customFont_regular = Typeface.createFromAsset(getAssets(),"fonts/AvenirNextLTPro-Regular.otf");

        payment.setTypeface(customFont_bold);
        saved_cards.setTypeface(customFont_regular);
        cardNumber.setTypeface(customFont_regular);
        payment_method.setTypeface(customFont_regular);
        button_campus_card.setTypeface(customFont_regular);
        button_credit_debit.setTypeface(customFont_regular);
        button_paypal.setTypeface(customFont_regular);
        total.setTypeface(customFont_bold);
        price.setTypeface(customFont_bold);
        button_pay.setTypeface(customFont_bold);

        //To DO : get total price of books from previous activity.
        //To Do : Set the price to be displayed in "price" field.
        //price.setText("$"+" ------price variable -----");
        total_checkout = getIntent().getStringExtra("total");
        if(total_checkout!=null) {
            price.setText("$" + total_checkout);
        }
        else {
            total_checkout = "55.00";
            price.setText("$" + total_checkout);
        }


        button_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent receipt = new Intent(view.getContext(), finalproject.mobilecomputing.bidbox.receipt.class);
                receipt.putExtra("total",total_checkout);
                startActivity(receipt);

                //To Do : send all book details to next activity

            }
        });







    }
}
