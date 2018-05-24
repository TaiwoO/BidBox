package finalproject.mobilecomputing.bidbox.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import finalproject.mobilecomputing.bidbox.R;
import finalproject.mobilecomputing.bidbox.models.Book;

/**
 * Created by rltan on 5/16/2018.
 */

public class CheckoutItemAdapter extends ArrayAdapter<Book> implements View.OnClickListener{

    public static final String TAG = CheckoutItemAdapter.class.getSimpleName();
    private List<Book> mBooks;
    Context mContext;



    public CheckoutItemAdapter(@NonNull Context context, @NonNull List<Book> books) {
        super(context, R.layout.checkout_item_row, books);

        this.mBooks = books;
        this.mContext = context;
    }
    // View lookup cache
    private static class ViewHolder {
        TextView bookName;
        TextView bookIsbn;
        ImageView bookImg;
        TextView price;
        TextView quantity;
        //TextView yourBid;
        //Button checkout;
    }

    @Override
    public void onClick(View view) {
        int position = (Integer) view.getTag();
        Book book = getItem(position);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Book book = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        //
        CheckoutItemAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new CheckoutItemAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.checkout_item_row, parent, false);

            viewHolder.bookName = (TextView) convertView.findViewById(R.id.checkout_item_name);
            viewHolder.bookIsbn = (TextView) convertView.findViewById(R.id.checkout_item_isbn);
            viewHolder.bookImg = (ImageView) convertView.findViewById(R.id.checkout_item_img);

            viewHolder.price = (TextView) convertView.findViewById(R.id.checkout_price_value);

            viewHolder.quantity = (TextView) convertView.findViewById(R.id.checkout_quantity_value);
           // viewHolder.currentBid = (TextView) convertView.findViewById(R.id.current_bid_value);

            //viewHolder.bidBtn = (Button) convertView.findViewById(R.id.bid_item_bid_btn);
            //viewHolder.addToChartBtn = (Button) convertView.findViewById(R.id.bid_item_addToChart_btn);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CheckoutItemAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.bookName.setText(book.getName());
        viewHolder.bookIsbn.setText(book.getIsbn());



        viewHolder.price.setText(book.getPrice().toString()); //book.getTimeFormat();
        viewHolder.quantity.setText("1"); //book.getBid(userid?);
       // viewHolder.currentBid.setText("$25.50"); //book.getCurrentBid();

        Glide.with(mContext)
                .load(book.getImgUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.no_image_available_placeholder)
                        .fitCenter())
                .into(viewHolder.bookImg);
        //viewHolder.bidBtn.setOnClickListener(this);
        //viewHolder.bidBtn.setTag(position);
        //viewHolder.addToChartBtn.setOnClickListener(this);
        //viewHolder.addToChartBtn.setTag(position);

        return convertView;
    }


}
