package finalproject.mobilecomputing.bidbox.Authorization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import finalproject.mobilecomputing.bidbox.R;

public class LoginActivity extends AppCompatActivity {

    private Button login_Button, goToRegister_Button;
    private EditText textField_Username, textField_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_Button = findViewById(R.id.button_login);
        goToRegister_Button = findViewById(R.id.button_goToRegister);
        textField_Username = findViewById(R.id.text_loginUsername);
        textField_Password = findViewById(R.id.text_loginPassword);

        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url ="http://www.google.com";

        // This listener sends an HTTP Requester when clicking the "Login" button.
        login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // HTTP Request to Sign In.
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
//                                mTextView.setText("Response is: "+ response.substring(0,500));
                                System.out.println(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        mTextView.setText("That didn't work!");
                        System.out.println(error);
                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

        // This listener channels us to the RegisterActivity
        goToRegister_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent registrationIntent = new Intent(getBaseContext(), RegistrationActivity.class);
                registrationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(registrationIntent);
            }
        });
    }
}
