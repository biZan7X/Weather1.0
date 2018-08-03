package com.bizantechx.weather1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button mybutton ;
    EditText city ;
    TextView result;
    String baseurl = "http://api.openweathermap.org/data/2.5/weather?q=";
    String api="&appid=058fe2e4ac4b92fda6c357e50b6e5b97";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mybutton   = (Button) findViewById(R.id.button);
        city  = (EditText) findViewById(R.id.city);
        result=(TextView) findViewById(R.id.results);
        if(city==null)
            result.setText("Invalid city");
        else
            mybutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String myUrl = baseurl + city.getText().toString() +api;
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myUrl, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    String info = null;
                                    try {
                                        info = response.getString("weather");
                                        JSONArray ar = new JSONArray(info);
                                        for (int i = 0; i < ar.length(); i++) {
                                            JSONObject parob = ar.getJSONObject(i);
                                            String id = parob.getString("description");
                                            result.setText(id);

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.i("error","error");
                                }
                            }
                    );
                    mySingleTon.getmInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);

                }
            });
    }
}
