package com.example.atiluztranspo;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Admin extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button adminButton; // Corrected variable name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        adminButton = findViewById(R.id.adminButton); // Corrected variable assignment

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Check if email and password are not empty
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Admin.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Perform login operation
                new LoginAdminTask().execute(email, password);
            }
        });
    }

    private class LoginAdminTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String email = params[0];
            String password = params[1];
            String result = "";

            try {
                URL url = new URL("https://alofgamequiz.tech/adminlogin.php"); // Replace with your server's URL
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                // Build the POST data
                String postData = "email=" + email + "&password=" + password;

                // Write POST data to output stream
                OutputStream os = conn.getOutputStream();
                os.write(postData.getBytes());
                os.flush();
                os.close();

                // Check the response code
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Read and process the response
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    result = response.toString();
                } else {
                    result = "Error: Server returned HTTP response code: " + responseCode;
                }
            } catch (Exception e) {
                result = "Error: " + e.getMessage();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.contains("success")) {
                 //Login successful, start AdminDashboard activity
                Intent intent = new Intent(Admin.this, AdminDashboard.class);
                startActivity(intent);
                Toast.makeText(Admin.this, "Login successful!", Toast.LENGTH_SHORT).show(); // Placeholder for success
            } else {
                // Login failed, display an error message
                Toast.makeText(Admin.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
