package com.dlefh3.android.dict_a_phone.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dlefh3.android.dict_a_phone.R;

public class OptionsActivity extends Activity
{
    public final String SAVED_COLOR = getString(R.string.saved_preferences_file);
    int red, blue, green, alpha;
    SeekBar redSeekBar, blueSeekBar, greenSeekBar, alphaSeekBar;
    EditText redEditText, blueEditText, greenEditText, alphaEditText;
    TextView previewText;
    RelativeLayout relativeLayout;
    Button backButton, setBGColorButton;
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

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(OptionsActivity.this, FrontPage.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
            }
        });
        setBGColorButton = (Button) findViewById(R.id.setBGColorButton);
        setBGColorButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {



                AlertDialog.Builder builder = new AlertDialog.Builder(OptionsActivity.this);
                builder.setTitle("Confirm").setMessage("Are you sure you want to change the " +
                        "background color?").setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        int newBG = Color.rgb(redSeekBar.getProgress(),
                                greenSeekBar.getProgress(), blueSeekBar.getProgress());

                        SharedPreferences savedColor = getSharedPreferences(SAVED_COLOR, 0);
                        SharedPreferences.Editor editor = savedColor.edit();

                        editor.putInt("bg_color", newBG);
                        editor.commit();
                        relativeLayout.setBackgroundColor(newBG);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
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
