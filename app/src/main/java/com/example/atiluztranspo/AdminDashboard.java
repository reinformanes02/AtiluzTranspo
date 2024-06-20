package com.example.atiluztranspo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdminDashboard extends AppCompatActivity {

    private LinearLayout linearLayout;
    private static final String TAG = "AdminDashboard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard); // Ensure this matches your XML layout file name

        linearLayout = findViewById(R.id.linearLayout); // Ensure this matches the ID in your XML

        new GetDataTask().execute("https://alofgamequiz.tech/fetch_drivers.php"); // Ensure the URL is correct
    }

    private class GetDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
            } catch (Exception e) {
                Log.e(TAG, "Error fetching data", e);
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "Fetched data: " + result); // Log the fetched data
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String fullName = jsonObject.getString("FullName");
                    String plateNumber = jsonObject.getString("PlateNumber");
                    String model = jsonObject.getString("Model");
                    String color = jsonObject.getString("Color");

                    // Create a new TextView for each driver
                    TextView textView = new TextView(AdminDashboard.this);
                    textView.setText(fullName + " - " + plateNumber + " - " + model + " - " + color);
                    linearLayout.addView(textView);
                }
            } catch (JSONException e) {
                Log.e(TAG, "Error parsing JSON", e);
            }
        }
    }
}
