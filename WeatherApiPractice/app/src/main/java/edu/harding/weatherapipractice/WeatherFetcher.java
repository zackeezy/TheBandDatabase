package edu.harding.weatherapipractice;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import edu.harding.weatherapipractice.MainActivity;

public class WeatherFetcher {
    private Context mContext;

    public WeatherFetcher(Context context){
        mContext = context;
    }

    public interface onWeatherReceivedListener{
        void onWeatherReceived(Weather weather);
        void onErrorResponse(VolleyError error);
    }

    public void getWeather(String zip, final onWeatherReceivedListener listener){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = String.format("http://api.openweathermap.org/data/2.5/weather?zip=%s&units=imperial&appid=6e8cf9556f936f601ea3e66daa899c68", zip);

        Log.d("test", "Url: " + url);

        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("test", "Response: " + response.toString());
                        try {
                            Weather weather = new Weather();
                            JSONObject main = response.getJSONObject("main");
                            weather.setCurrentTemp(main.getInt("temp"));
                            weather.setCity(response.getString("name"));
                            weather.setMaxTemp(((int) main.getDouble("temp_max")));
                            weather.setMinTemp(((int) main.getDouble("temp_min")));

                            listener.onWeatherReceived(weather);
                        }
                        catch(Exception ex){
                            Log.d("temp", "Error: " + ex.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onErrorResponse(error);
                    }
                }
        );
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
