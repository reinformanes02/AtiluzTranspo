package com.example.atiluztranspo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Registration extends AppCompatActivity {

    private EditText nameEditText, lastNameEditText, contactEditText, emailEditText, passwordEditText;
    private ToggleButton toggleTermsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameEditText = findViewById(R.id.Name);
        lastNameEditText = findViewById(R.id.LastName);
        contactEditText = findViewById(R.id.Contact);
        emailEditText = findViewById(R.id.Email);
        passwordEditText = findViewById(R.id.Password);
        toggleTermsButton = findViewById(R.id.toggleTerms);

        Button createButton = findViewById(R.id.buttoncreate);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!toggleTermsButton.isChecked()) {
                    Toast.makeText(Registration.this, "Please accept the terms and conditions", Toast.LENGTH_SHORT).show();
                    return;
                }

                String name = nameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String contact = contactEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (name.isEmpty() || lastName.isEmpty() || contact.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Registration.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                new RegisterUserTask().execute(name, lastName, contact, email, password);
            }
        });

        toggleTermsButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Terms accepted
                Toast.makeText(Registration.this, "Terms accepted", Toast.LENGTH_SHORT).show();
            } else {
                // Terms not accepted
                Toast.makeText(Registration.this, "Terms not accepted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class RegisterUserTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String lastName = params[1];
            String contact = params[2];
            String email = params[3];
            String password = params[4];

            try {
                String data = URLEncoder.encode("fname", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                data += "&" + URLEncoder.encode("lname", "UTF-8") + "=" + URLEncoder.encode(lastName, "UTF-8");
                data += "&" + URLEncoder.encode("contact_no", "UTF-8") + "=" + URLEncoder.encode(contact, "UTF-8");
                data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                URL url = new URL("https://alofgamequiz.tech/register.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
                outputStreamWriter.write(data);
                outputStreamWriter.flush();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } catch (Exception e) {
                Log.e("RegisterUserTask", "Error: " + e.getMessage(), e);
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(Registration.this, result, Toast.LENGTH_SHORT).show();
        }
    }
}
