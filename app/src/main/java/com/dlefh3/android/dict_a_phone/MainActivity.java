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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wordsapi.www.client.JsonUtil;
import com.wordsapi.www.wordsapi.api.WordsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    public static ObjectMapper mapper;
    private TextView txtView;
    private ImageButton imgButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        txtView = (TextView) findViewById(R.id.textView);
        imgButton = (ImageButton) findViewById(R.id.searchImageButton);
        imgButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                WordsApi wordsApi = new WordsApi();
                final String accessToken = "PecbHqRppTmshQ2O36b1FWa0OdWRp1UngatjsnKtJJbznLjveV";
                final String word = String.valueOf(((EditText) findViewById(R.id.searchEditText)).getText());
                String detail = "definitions";
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ((TextView) findViewById(R.id.textView)).setText(
                                new StringBuilder().append("No Results for \"").append(word).append("\"").toString());
                    }
                };
                    //DetailsResponse response = wordsApi.details(accessToken, word, detail);

                Request request = new JsonObjectRequest(
                    "https://wordsapiv1.p.mashape.com/words/"+word+"/definitions", null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //((TextView) findViewById(R.id.textView)).setText(response.toString());
                            try
                            {
                                List details = JsonUtil.getJsonMapper().readValue(response.getString("definitions"), List.class);

                                ((TextView) findViewById(R.id.textView)).setText(details.toString());
                            }
                            catch (Error e) {
                            } catch (JsonMappingException e) {

                                e.printStackTrace();
                            } catch (JsonParseException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

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
