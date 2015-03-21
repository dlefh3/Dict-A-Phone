package com.dlefh3.android.dict_a_phone.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dlefh3.android.dict_a_phone.R;


public class FrontPage extends Activity
{
    Button searchButton, optionsButton, aboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        Toast toast = Toast.makeText(getApplicationContext(), (CharSequence)"Welcome to Dict-A-Phone!", Toast.LENGTH_LONG);
        toast.show();

        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
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
                startActivity(i);
            }
        });
    }




}
