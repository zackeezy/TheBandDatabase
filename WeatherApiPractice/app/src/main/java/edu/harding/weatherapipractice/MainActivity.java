package edu.harding.weatherapipractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mCurrentTemp;
    private TextView mLowTemp;
    private TextView mHighTemp;
    private TextView mCity;
    private EditText mZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCurrentTemp = (TextView) findViewById(R.id.temp);
        mLowTemp = (TextView) findViewById(R.id.lowTemp);
        mHighTemp = (TextView) findViewById(R.id.highTemp);
        mCity = (TextView) findViewById(R.id.city);
        mZip = (EditText) findViewById(R.id.input);

        mZip.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String zip = mZip.getText().toString();
                searchForWeather(zip);

                return true;
            }
        });
    }

    private void searchForWeather(String zip){
        WeatherFetcher fetcher = new WeatherFetcher(this);

        fetcher.getWeather(zip, new WeatherFetcher.onWeatherReceivedListener() {
            @Override
            public void onWeatherReceived(Weather weather) {
                mCurrentTemp.setText("F " + weather.getCurrentTemp());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                mCurrentTemp.setText(error.toString());
            }
        });
    }
}
