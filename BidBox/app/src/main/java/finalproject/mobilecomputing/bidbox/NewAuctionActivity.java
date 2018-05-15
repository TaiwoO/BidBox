package finalproject.mobilecomputing.bidbox;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import finalproject.mobilecomputing.bidbox.utils.BitMapUtils;

public class NewAuctionActivity extends AppCompatActivity {

    public static final String TAG = NewAuctionActivity.class.getSimpleName();
    private static int IMG_RESULT = 1;
    private EditText bookName;
    private EditText bookIsbn;
    private EditText bookVersion;
    private EditText bookCondition;
    private ImageButton bookImageBtn;
    private Button submitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_auction);
        setTitle("Add New Auction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookName = (EditText) findViewById(R.id.new_auction_book_name);
        bookIsbn = (EditText) findViewById(R.id.new_auction_book_isbn);
        bookVersion = (EditText) findViewById(R.id.new_auction_book_version);
        bookCondition = (EditText) findViewById(R.id.new_auction_book_condition);
        bookImageBtn = (ImageButton) findViewById(R.id.new_auction_book_img_btn);
        submitBtn = (Button) findViewById(R.id.new_auction_submit_btn);

        bookImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMG_RESULT);
            }
        });
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
                String bookImageFileLocation = cursor.getString(columnIndex);
                cursor.close();
                Bitmap bookImageBitmap = BitmapFactory.decodeFile(bookImageFileLocation);
                bookImageBtn.setImageBitmap(bookImageBitmap);
            }

        } catch (Exception e) {
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
