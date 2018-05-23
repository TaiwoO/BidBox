package finalproject.mobilecomputing.bidbox.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import finalproject.mobilecomputing.bidbox.BidActivity;
import finalproject.mobilecomputing.bidbox.R;
import finalproject.mobilecomputing.bidbox.api.BidBox.BidBoxApiInterface;
import finalproject.mobilecomputing.bidbox.models.Auction;
import finalproject.mobilecomputing.bidbox.models.Book;
import finalproject.mobilecomputing.bidbox.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuctionItemAdapter extends ArrayAdapter<Auction> implements View.OnClickListener {

    public static final String TAG = AuctionItemAdapter.class.getSimpleName();
    Context mContext;

    public AuctionItemAdapter(@NonNull Context context) {
        super(context, R.layout.auction_item_row);

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
        Auction auction = getItem(position);

        switch (view.getId()) {
            case R.id.bid_item_bid_btn:
                Intent intent = new Intent(mContext, BidActivity.class);

                intent.putExtra("auction", auction);
                mContext.startActivity(intent);
                Log.d(TAG, "OKAYY");
                break;
            case R.id.bid_item_addToChart_btn:
                addToChart(auction.getBook());
                break;
        }
    }

    private void addToChart(Book book) {
        SharedPreferences pref = mContext.getSharedPreferences(mContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String token = pref.getString("token", null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mContext.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BidBoxApiInterface bidBoxService = retrofit.create(BidBoxApiInterface.class);
        Call<Void> addToChartCall = bidBoxService.addToShoppingChart(book.getId(), token);
        addToChartCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                int statusCode = response.code();

                if (statusCode == 200) {
                    Toast.makeText(mContext, "Added  item to shopping chart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Failed to add to chart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Auction auction = getItem(position);

        Log.d(TAG, auction.toString());

        // Check if an existing view is being reused, otherwise inflate the view
        //
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.auction_item_row, parent, false);

            viewHolder.bookName = (TextView) convertView.findViewById(R.id.bid_item_name);
            viewHolder.bookIsbn = (TextView) convertView.findViewById(R.id.bid_item_isbn);
            viewHolder.bookImg = (ImageView) convertView.findViewById(R.id.bid_item_img);
            viewHolder.bidBtn = (Button) convertView.findViewById(R.id.bid_item_bid_btn);
            viewHolder.addToChartBtn = (Button) convertView.findViewById(R.id.bid_item_addToChart_btn);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.bookName.setText(auction.getBook().getName());
        viewHolder.bookIsbn.setText(auction.getBook().getIsbn());
        viewHolder.bidBtn.setOnClickListener(this);
        viewHolder.bidBtn.setTag(position);
        viewHolder.addToChartBtn.setOnClickListener(this);
        viewHolder.addToChartBtn.setTag(position);
        Log.d(TAG, auction.toString());
        Glide.with(mContext)
                .load(auction.getBook().getImgUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.no_image_available_placeholder)
                        .fitCenter())
                .into(viewHolder.bookImg);

        return convertView;
    }
}
