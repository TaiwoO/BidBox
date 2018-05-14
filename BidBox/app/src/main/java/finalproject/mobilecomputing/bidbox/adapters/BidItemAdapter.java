package finalproject.mobilecomputing.bidbox.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import finalproject.mobilecomputing.bidbox.HomeActivity;
import finalproject.mobilecomputing.bidbox.R;
import finalproject.mobilecomputing.bidbox.models.Book;

public class BidItemAdapter extends ArrayAdapter<Book> implements View.OnClickListener {

    public static final String TAG = BidItemAdapter.class.getSimpleName();
    private List<Book> mBooks;
    Context mContext;

    public BidItemAdapter(@NonNull Context context, @NonNull List<Book> books) {
        super(context, R.layout.bid_item_row, books);

        this.mBooks = books;
        this.mContext = context;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView bookName;
        TextView bookIsbn;
        ImageView bookImg;
        Button bidBtn;
        Button addToChartBtn;
    }

    @Override
    public void onClick(View view) {
        int position = (Integer) view.getTag();
        Book book = getItem(position);

        switch (view.getId()) {
            case R.id.bid_item_bid_btn:
                Log.d(TAG, "OKAYY");
                Toast.makeText(mContext, "Should go to bid pg now", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bid_item_addToChart_btn:
                Toast.makeText(mContext, "Should add the item to the chart now", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Book book = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        //
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.bid_item_row, parent, false);

            viewHolder.bookName = (TextView) convertView.findViewById(R.id.bid_item_name);
            viewHolder.bookIsbn = (TextView) convertView.findViewById(R.id.bid_item_isbn);
            viewHolder.bookImg = (ImageView) convertView.findViewById(R.id.bid_item_img);
            viewHolder.bidBtn = (Button) convertView.findViewById(R.id.bid_item_bid_btn);
            viewHolder.addToChartBtn = (Button) convertView.findViewById(R.id.bid_item_addToChart_btn);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.bookName.setText(book.getName());
        viewHolder.bookIsbn.setText(book.getIsbn());
        viewHolder.bidBtn.setOnClickListener(this);
        viewHolder.bidBtn.setTag(position);
        viewHolder.addToChartBtn.setOnClickListener(this);
        viewHolder.addToChartBtn.setTag(position);

        return convertView;
    }
}
