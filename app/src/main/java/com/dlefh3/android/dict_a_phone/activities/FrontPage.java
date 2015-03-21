package com.dlefh3.android.dict_a_phone.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dlefh3.android.dict_a_phone.R;

import static com.dlefh3.android.dict_a_phone.R.string.welcome_toast;


public class FrontPage extends Activity
{
    Button searchButton, optionsButton, aboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        if (savedInstanceState == null)
        {
            Toast toast = Toast.makeText(getApplicationContext(), (CharSequence) getString(welcome_toast), Toast.LENGTH_LONG);
            toast.show();
        }
        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(FrontPage.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);

            }
        });

        optionsButton = (Button) findViewById(R.id.optionsButton);
        optionsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), OptionsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        SharedPreferences savedColor = getSharedPreferences(getString(R.string.pref_file), 0);
        int bgColor = savedColor.getInt("bg_color", 0);
        if(bgColor != 0)
            ((LinearLayout)findViewById(R.id.front_page_layout)).setBackgroundColor(bgColor);
    }



}
