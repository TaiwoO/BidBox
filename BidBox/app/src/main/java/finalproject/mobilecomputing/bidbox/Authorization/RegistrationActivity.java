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

import finalproject.mobilecomputing.bidbox.HomeActivity;
import finalproject.mobilecomputing.bidbox.R;

public class RegistrationActivity extends AppCompatActivity {

    private Button register_Button, goToLogin_Button;
    private EditText textField_registerEmail, textField_registerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        register_Button = findViewById(R.id.button_register);
        goToLogin_Button = findViewById(R.id.button_goToLogin);
        textField_registerEmail = findViewById(R.id.text_registerEmail);
        textField_registerPassword = findViewById(R.id.text_registerPassword);

        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url ="https://dd8a6638.ngrok.io/auth/register";

        register_Button.setOnClickListener(new View.OnClickListener() {
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
                        }){
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        // Get user's input from the TextFields for email and password.
                        String email = String.valueOf(textField_registerEmail.getText());
                        String password = String.valueOf(textField_registerPassword.getText());
                        params.put("email", email);
                        params.put("password", password);
                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

        goToLogin_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent loginIntent = new Intent(getBaseContext(), LoginActivity.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginIntent);
            }
        });
    }

    private void gotoHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
