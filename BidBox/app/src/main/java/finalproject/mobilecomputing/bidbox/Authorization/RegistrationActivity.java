package finalproject.mobilecomputing.bidbox.Authorization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import finalproject.mobilecomputing.bidbox.R;

public class RegistrationActivity extends AppCompatActivity {

    private Button register_Button, goToLogin_Button;
    private EditText textField_registerDisplayName, textField_registerUsername, textField_registerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        register_Button = findViewById(R.id.button_register);
        goToLogin_Button = findViewById(R.id.button_goToLogin);
        textField_registerDisplayName = findViewById(R.id.text_registerDisplayName);
        textField_registerUsername = findViewById(R.id.text_registerUsername);
        textField_registerPassword = findViewById(R.id.text_registerPassword);

        register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
}
