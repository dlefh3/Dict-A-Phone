package com.dlefh3.android.dict_a_phone.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dlefh3.android.dict_a_phone.R;

public class OptionsActivity extends ActionBarActivity
{
    int red, blue, green, alpha;
    SeekBar redSeekBar, blueSeekBar, greenSeekBar, alphaSeekBar;
    EditText redEditText, blueEditText, greenEditText, alphaEditText;
    TextView previewText;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        relativeLayout = (RelativeLayout)findViewById(R.id.optionsRelativeLayout);

        redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);
        greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);

        redEditText = (EditText) findViewById(R.id.redEditText);
        blueEditText = (EditText) findViewById(R.id.blueEditText);
        greenEditText = (EditText) findViewById(R.id.greenEditText);

        previewText = (TextView) findViewById(R.id.previewTextView);

        previewText.setBackgroundColor(Color.rgb(2, greenSeekBar.getProgress(), blueSeekBar.getProgress()));

        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                redEditText.setText(String.valueOf(progress));
                previewText.setBackgroundColor(Color.rgb(redSeekBar.getProgress(),
                        greenSeekBar.getProgress(), blueSeekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}
          
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}
           
        });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                blueEditText.setText(String.valueOf(progress));
                previewText.setBackgroundColor(Color.rgb(redSeekBar.getProgress(),
                        greenSeekBar.getProgress(), blueSeekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}

        });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                greenEditText.setText(String.valueOf(progress));
                previewText.setBackgroundColor(Color.rgb(redSeekBar.getProgress(),
                        greenSeekBar.getProgress(), blueSeekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}

        });

        redEditText.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                redSeekBar.setProgress(getIntForSeekBars(v));
                return false;
            }
        });

        blueEditText.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                blueSeekBar.setProgress(getIntForSeekBars(v));
                return false;
            }
        });

        greenEditText.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                greenSeekBar.setProgress(getIntForSeekBars(v));
                return true;
            }
        });
        
    }
    public static int getIntForSeekBars(TextView v)
    {
        int textField = Integer.valueOf(v.getText().toString());
        if (textField < 0)
            textField = 0;
        else if (textField > 255)
            textField = 255;

        return textField;
    }


}
