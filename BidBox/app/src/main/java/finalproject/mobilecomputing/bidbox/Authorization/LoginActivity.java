package finalproject.mobilecomputing.bidbox.Authorization;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import finalproject.mobilecomputing.bidbox.R;
import finalproject.mobilecomputing.bidbox.CurrentBidActivity;

public class LoginActivity extends AppCompatActivity {

    private Button login_Button, goToRegister_Button;
    private EditText textField_Email, textField_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_Button = findViewById(R.id.button_login);
        goToRegister_Button = findViewById(R.id.button_goToRegister);
        textField_Email = findViewById(R.id.text_loginEmail);
        textField_Password = findViewById(R.id.text_loginPassword);

        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url ="https://dd8a6638.ngrok.io/auth/login";

        // This listener sends an HTTP Requester when clicking the "Login" button.
        login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // HTTP Request to Sign In.
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println(response);
                                Context context = getApplicationContext();
                                Toast toast = Toast.makeText(context, response, Toast.LENGTH_LONG);
                                toast.show();
                                gotoHomeActivity();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println(error);
                                Context context = getApplicationContext();
                                Toast toast = Toast.makeText(context, String.valueOf(error), Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        // Get user's input from the TextFields for email and password.
                        String email = String.valueOf(textField_Email.getText());
                        String password = String.valueOf(textField_Password.getText());
                        params.put("email", email);
                        params.put("password", password);
                        return params;
                    }
                };
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

    private void gotoHomeActivity() {
        Intent intent = new Intent(this, CurrentBidActivity.class);
        startActivity(intent);
    }
}
