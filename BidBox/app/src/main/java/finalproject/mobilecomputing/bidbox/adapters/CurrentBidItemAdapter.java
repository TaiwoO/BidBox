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


import java.util.List;

import finalproject.mobilecomputing.bidbox.R;
import finalproject.mobilecomputing.bidbox.models.Book;

/**
 * Created by Joe on 5/8/2018.
 * Based on the AuctionItemAdapter.java but for the current bid page
 */

public class CurrentBidItemAdapter extends ArrayAdapter<Book> implements View.OnClickListener{

    public static final String TAG = CurrentBidItemAdapter.class.getSimpleName();
    private List<Book> mBooks;
    Context mContext;

    public CurrentBidItemAdapter(@NonNull Context context, @NonNull List<Book> books) {
        super(context, R.layout.current_bid_item_row, books);

        this.mBooks = books;
        this.mContext = context;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView bookName;
        TextView bookIsbn;
        ImageView bookImg;
        TextView timeRemaining;
        TextView currentBid;
        TextView yourBid;
        Button checkout;
    }

    @Override
    public void onClick(View view) {
        int position = (Integer) view.getTag();
        Book book = getItem(position);

        /*switch (view.getId()) {
            case R.id.bid_item_bid_btn:
                Log.d(TAG, "OKAYY");
                Toast.makeText(mContext, "Should go to bid pg now", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bid_item_addToChart_btn:
                Toast.makeText(mContext, "Should add the item to the chart now", Toast.LENGTH_SHORT).show();
                break;
        }*/
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Book book = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        //
        CurrentBidItemAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new CurrentBidItemAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.current_bid_item_row, parent, false);

            viewHolder.bookName = (TextView) convertView.findViewById(R.id.current_bid_name);
            viewHolder.bookIsbn = (TextView) convertView.findViewById(R.id.current_bid_isbn);
            viewHolder.bookImg = (ImageView) convertView.findViewById(R.id.current_bid_item_img);

            viewHolder.timeRemaining = (TextView) convertView.findViewById(R.id.time_remaining_value);

            viewHolder.yourBid = (TextView) convertView.findViewById(R.id.your_bid_value);
            viewHolder.currentBid = (TextView) convertView.findViewById(R.id.current_bid_value);

            //viewHolder.bidBtn = (Button) convertView.findViewById(R.id.bid_item_bid_btn);
            //viewHolder.addToChartBtn = (Button) convertView.findViewById(R.id.bid_item_addToChart_btn);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CurrentBidItemAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.bookName.setText(book.getName());
        viewHolder.bookIsbn.setText(book.getIsbn());



        viewHolder.timeRemaining.setText("1:57:39"); //book.getTimeFormat();
        viewHolder.yourBid.setText("$25.50"); //book.getBid(userid?);
        viewHolder.currentBid.setText("$25.50"); //book.getCurrentBid();


        //viewHolder.bidBtn.setOnClickListener(this);
        //viewHolder.bidBtn.setTag(position);
        //viewHolder.addToChartBtn.setOnClickListener(this);
        //viewHolder.addToChartBtn.setTag(position);

        return convertView;
    }
}
