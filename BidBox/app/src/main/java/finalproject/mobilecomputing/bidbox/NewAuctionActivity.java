package finalproject.mobilecomputing.bidbox;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;

import finalproject.mobilecomputing.bidbox.api.BidBox.BidBoxApiInterface;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewAuctionActivity extends AppCompatActivity {

    public static final String TAG = NewAuctionActivity.class.getSimpleName();
    private static int IMG_RESULT = 1;
    private EditText bookName;
    private EditText bookIsbn;
    private EditText bookVersion;
    private EditText bookCondition;
    private EditText bookAskingPrice;
    private ImageButton bookImageBtn;
    private Button submitBtn;
    private ActionBar actionBar;
    private String bookImageFilePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_auction);
        setTitle("Add New Auction");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);


        bookName = (EditText) findViewById(R.id.new_auction_book_name);
        bookIsbn = (EditText) findViewById(R.id.new_auction_book_isbn);
        bookVersion = (EditText) findViewById(R.id.new_auction_book_version);
        bookCondition = (EditText) findViewById(R.id.new_auction_book_condition);
        bookAskingPrice = (EditText) findViewById(R.id.new_auction_book_askingPrice);
        bookImageBtn = (ImageButton) findViewById(R.id.new_auction_book_img_btn);
        submitBtn = (Button) findViewById(R.id.new_auction_submit_btn);

        bookImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMG_RESULT);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitNewAuctionForm();
            }
        });
    }

    private void submitNewAuctionForm() {

        // Need the user Json Web Token in order to add a new auction.
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String token = pref.getString("token", null);

        // TODO: Validtation.

        // Convert the book file into a Multipart request body
        File bookImageFile = new File(bookImageFilePath);
        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), bookImageFile);
        MultipartBody.Part bookImageBody = MultipartBody.Part.createFormData("image", bookImageFile.getName(), reqFile);

        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), bookName.getText().toString());
        RequestBody isbn = RequestBody.create(MediaType.parse("text/plain"), bookIsbn.getText().toString());
        RequestBody version = RequestBody.create(MediaType.parse("text/plain"), bookVersion.getText().toString());
        RequestBody condition = RequestBody.create(MediaType.parse("text/plain"), bookCondition.getText().toString());
        RequestBody askingPrice = RequestBody.create(MediaType.parse("text/plain"), bookAskingPrice.getText().toString());

        Log.d(TAG, token);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BidBoxApiInterface bidBoxService = retrofit.create(BidBoxApiInterface.class);
        // TODO: check if fields are non-empty

        Call<Void> addNewAuctionCall = bidBoxService.addNewAuction(
                bookImageBody,
                name,
                isbn,
                version,
                condition,
                askingPrice,
                token
        );

        addNewAuctionCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    Toast.makeText(NewAuctionActivity.this, "Auction was added!", Toast.LENGTH_SHORT).show();
                    gotoHomeActivity();

                } else {
                    Toast.makeText(NewAuctionActivity.this, "Failed to add auction. Make sure you filled in all fields", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "@@@@ OPPS");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "@@@@ AN ERROR OCCURED: mabey unAuthorized ");
            }
        });
    }

    private void gotoHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            // Get an image from an external image gallery
            if (requestCode == IMG_RESULT && resultCode == RESULT_OK
                    && data != null) {

                Uri URI = data.getData();
                String[] FILE = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(URI,
                        FILE, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(FILE[0]);
                bookImageFilePath = cursor.getString(columnIndex);
                cursor.close();
                Bitmap bookImageBitmap = BitmapFactory.decodeFile(bookImageFilePath);
                bookImageBtn.setImageBitmap(bookImageBitmap);
            }

        } catch (Exception e) {
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
