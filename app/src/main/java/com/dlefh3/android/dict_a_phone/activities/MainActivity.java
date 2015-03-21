package com.dlefh3.android.dict_a_phone.activities;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dlefh3.android.dict_a_phone.AppController;
import com.dlefh3.android.dict_a_phone.R;
import com.dlefh3.android.dict_a_phone.adapter.CustomArrayAdapter;
import com.dlefh3.android.dict_a_phone.adapter.DefinitionItemObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.wordsapi.www.client.JsonUtil;
import com.wordsapi.www.wordsapi.model.Definition;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class MainActivity extends ListActivity {

    public static ObjectMapper mapper;
    private TextView txtView, resultCountTextView, resultLabelTextView;
    private EditText searchEditText;
    private ImageButton imgButton;
    final String accessToken = "PecbHqRppTmshQ2O36b1FWa0OdWRp1UngatjsnKtJJbznLjveV";
    String word;
    String detail = "definitions";
    final String urlBase = "https://wordsapiv1.p.mashape.com/words/";
    private CustomArrayAdapter customArrayAdapter = null;
    List<Definition> definitions = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        customArrayAdapter = new CustomArrayAdapter(MainActivity.this);
        setListAdapter(customArrayAdapter);

        txtView = (TextView) findViewById(R.id.textView);
        resultCountTextView = (TextView) findViewById(R.id.resultCountTextView);
        resultLabelTextView = (TextView) findViewById(R.id.resultLabelTextView);
        searchEditText = (EditText) findViewById(R.id.searchEditText);
        imgButton = (ImageButton) findViewById(R.id.searchImageButton);
        View.OnClickListener searchOnClickListener = new SearchOnClickListener();

        imgButton.setOnClickListener(searchOnClickListener);


        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    imgButton.callOnClick();
                    handled = true;
                }
                return handled;
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
    private void generateData()
    {
        if (definitions != null)
        {
            customArrayAdapter.clear();
            for(Iterator<Definition> d = definitions.iterator(); d.hasNext();)
            {
                Definition definition = d.next();
                DefinitionItemObject definitionItemObject = new DefinitionItemObject(
                        definition.getDefinition(),definition.getPartOfSpeech() );
                customArrayAdapter.add(definitionItemObject);
            }
            customArrayAdapter.notifyDataSetChanged();
        }
    }

    public class SearchOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
            txtView.setText("");
            //resultCountTextView.setVisibility(View.INVISIBLE);
            //resultLabelTextView.setVisibility(View.INVISIBLE);
            word = String.valueOf(((EditText) findViewById(R.id.searchEditText)).getText());
            String requestUrl = urlBase+word+"/" + detail;
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    txtView.setText("No Results for \"" + word + "\"");
                    resultCountTextView.setText(getString(R.string.results_none));
                    Log.e("Response Error", error.networkResponse.toString());

                    customArrayAdapter.clear();
                }
            };
            Request request = new JsonObjectRequest(requestUrl, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //((TextView) findViewById(R.id.textView)).setText(response.toString());
                            try
                            {
                                definitions = JsonUtil.getJsonMapper().readValue(response.getString("definitions"),
                                        TypeFactory.defaultInstance().constructCollectionType(List.class, Definition.class));
                                generateData();
                                if (definitions != null)
                                {
                                    resultCountTextView.setText(String.valueOf(definitions.size()));
                                }
                                //((TextView) findViewById(R.id.textView)).setText(definitions.toString());
                            }
                            catch (JsonMappingException e) {
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
                public Map<String, String> getHeaders() throws AuthFailureError
                {
                    Map<String, String> pHeaders = super.getHeaders();
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.putAll(pHeaders);
                    headers.put("X-Mashape-Key", accessToken);
                    headers.put("Accept", "application/json");
                    return headers;
                }
            };
            //AppController.addRequestToQueue(jsonObjectRequest);
            AppController.addRequestToQueue(request);
        }
    }


}
