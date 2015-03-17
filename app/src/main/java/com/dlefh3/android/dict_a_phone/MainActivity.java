package com.dlefh3.android.dict_a_phone;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wordsapi.www.wordsapi.api.WordsApi;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {


    private TextView txtView;
    private ImageButton imgButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.textView);
        imgButton = (ImageButton) findViewById(R.id.searchImageButton);
        imgButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                WordsApi wordsApi = new WordsApi();
                final String accessToken = "PecbHqRppTmshQ2O36b1FWa0OdWRp1UngatjsnKtJJbznLjveV";
                String word = String.valueOf(((EditText) findViewById(R.id.searchEditText)).getText());
                String detail = "definitions";
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ((TextView) findViewById(R.id.textView)).setText(error.getMessage().toString());
                    }
                };
                    //DetailsResponse response = wordsApi.details(accessToken, word, detail);
/*
                MyJSONObjectRequest jsonObjectRequest = new MyJSONObjectRequest(

                        "https://wordsapiv1.p.mashape.com/words/"+word+"/definitions", null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                //((TextView) findViewById(R.id.textView)).setText(response);
                                try
                                {
                                    DetailsResponse dResponse = (DetailsResponse) JsonUtil.getJsonMapper().readValue(response, DetailsResponse.class);
                                    txtView.setText((CharSequence) dResponse.getDefinitions().toString());
                                }
                                catch (Exception e)
                                {
                                    txtView.setText(e.getMessage().toString());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                ((TextView) findViewById(R.id.textView)).setText(error.getMessage().toString());
                            }
                        }
                ){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError
                    {
                        Map<String, String> pHeaders = super.getHeaders();
                        Map<String, String> headers = new HashMap<String, String>();
                        if (headers == null
                                || headers.equals(Collections.emptyMap())) {
                            headers = new HashMap<String, String>();
                        }
                        headers.putAll(pHeaders);
                        headers.put("X-Mashape-Key", accessToken);
                        headers.put("Accept", "application/json");


                        return headers;
                    }
                }; */

                Request request = new JsonObjectRequest(
                    "https://wordsapiv1.p.mashape.com/words/"+word+"/definitions", null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            ((TextView) findViewById(R.id.textView)).setText(response.toString());

                        }
                    }, errorListener){

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> pHeaders = super.getHeaders();
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.putAll(pHeaders);
                    headers.put("api-key", "API_KEY_GOES_HERE");
                    headers.put("X-Mashape-Key", accessToken);
                    headers.put("Accept", "application/json");
                    return headers;
                }
            };
                //AppController.addRequestToQueue(jsonObjectRequest);
                AppController.addRequestToQueue(request);


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
