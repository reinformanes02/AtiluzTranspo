package com.example.atiluztranspo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.LoginButton);
        registerButton = findViewById(R.id.RegisterButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Check if email and password are not empty
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Perform login operation
                new LoginUserTask().execute(email, password);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the registration activity
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });
    }

    private class LoginUserTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String email = params[0];
            String password = params[1];

            try {
                // Replace this placeholder with actual HTTP request to your login PHP script
                // You need to make a POST request with the email and password to your server
                // and handle the login process there
                // For example:
                // String postData = "email=" + email + "&password=" + password;
                // String response = YourHttpHelperClass.makePostRequest("https://yourserver.com/login.php", postData);
                // return response;

                // For now, return a dummy response indicating success
                return "success";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.contains("success")) {
                // Login successful, start dashboard activity
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
            } else {
                // Login failed, display an error message
                Toast.makeText(MainActivity.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
