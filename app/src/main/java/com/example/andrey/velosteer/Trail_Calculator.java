package com.example.andrey.velosteer;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.text.DecimalFormat;


public class Trail_Calculator extends Activity implements View.OnKeyListener, ToggleButton.OnCheckedChangeListener
{
    private static double headAngle, rake, trail, headtubeB, wheel, aToC;
    private static EditText etHA, etFO, etATC, etW, etHBH, etT;
    private ToggleButton tbFO, tbATC, tbW, tbHBH, tbT;
    boolean inFO, inATC, inW, inHBH, inT;
    DecimalFormat df;


    @Override
    protected void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_bike_steer);
        tbFO = (ToggleButton)findViewById(R.id.rakeButton);
        tbFO.setOnCheckedChangeListener(this);
        tbATC = (ToggleButton)findViewById(R.id.aToCButton);
        tbATC.setOnCheckedChangeListener(this);
        tbW = (ToggleButton)findViewById(R.id.wheelButton);
        tbW.setOnCheckedChangeListener(this);
        tbHBH = (ToggleButton)findViewById(R.id.headtubeButton);
        tbHBH.setOnCheckedChangeListener(this);
        tbT = (ToggleButton)findViewById(R.id.trailButton);
        tbT.setOnCheckedChangeListener(this);
        //Instantiate EditText fields
        etHA = (EditText)findViewById(R.id.headAngle);
        etFO = (EditText)findViewById(R.id.rake);
        etATC = (EditText)findViewById(R.id.aToC);
        etW = (EditText)findViewById(R.id.wheel);
        etHBH = (EditText)findViewById(R.id.headTube);
        etHBH.setKeyListener(null);
        etT = (EditText)findViewById(R.id.trail);
        etT.setKeyListener(null);
        df= new DecimalFormat("#.##");

    }


    //  ************************* onCheckedChanged() **************************
//  Author: Andrey Kobzar
    public void onCheckedChanged(CompoundButton cb, boolean checked)
    {
        int id = cb.getId();
        switch(id)
        {
            case R.id.aToCButton:
                if(checked)
                {
                    inATC = true;
                }
                else
                {
                    inATC = false;
                }
                break;
            case R.id.rakeButton:
                if(checked)
                {
                    inFO = true;
                }
                else
                {
                    inFO = false;
                }
                break;
            case R.id.wheelButton:
                if(checked)
                {
                    inW = true;
                }
                else
                {
                    inW = false;
                }
                break;
            case R.id.headtubeButton:
                if(checked)
                {
                    inHBH = true;
                    if(!etHBH.getText().toString().isEmpty())
                    {
                        headtubeB = Double.parseDouble(etHBH.getText().toString())/25.4;
                        etHBH.setText(""+headtubeB);
                    }
                }
                else
                {
                    inHBH = false;
                    if(!etHBH.getText().toString().isEmpty())
                    {
                        headtubeB = Double.parseDouble(etHBH.getText().toString())*25.4;
                        etHBH.setText(""+headtubeB);
                    }
                }
                break;
            case R.id.trailButton:
                if(checked)
                {
                    inT = true;
                    if(!etT.getText().toString().isEmpty())
                    {
                        trail = Double.parseDouble(etT.getText().toString())/25.4;
                        etT.setText(""+trail);
                    }
                }
                else
                {
                    inT = false;
                    if(!etT.getText().toString().isEmpty())
                    {
                        trail = Double.parseDouble(etT.getText().toString())*25.4;
                        etT.setText(""+trail);
                    }
                }
                break;
        }
    }
    //  ***************************** onKey() ********************************
//  Author: Andrey Kobzar
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent ke)
    {
        return false;
    }

    //  **************************** calculate() ************************
    public void calculate(View v)
    {
        if(!etHA.getText().toString().isEmpty()&& !etFO.getText().toString().isEmpty()
                && !etATC.getText().toString().isEmpty())
        {
            headAngle= Double.parseDouble(etHA.getText().toString());
            rake= Double.parseDouble(etFO.getText().toString());
            if(inFO) rake=rake*25.4;
            aToC= Double.parseDouble(etATC.getText().toString());
            if(inATC) aToC=aToC*25.4;
            double x = 1/Math.tan(Math.toRadians(headAngle))* rake;
            headtubeB = Math.sin(Math.toRadians(headAngle))*(aToC - x);
            if(inHBH) headtubeB=headtubeB/25.4;
            etHBH.setText(""+headtubeB);
        }
        if(!etHA.getText().toString().isEmpty()&& !etFO.getText().toString().isEmpty()
                && !etW.getText().toString().isEmpty())
        {
            headAngle= Double.parseDouble(etHA.getText().toString());
            rake= Double.parseDouble(etFO.getText().toString());
            if(inFO) rake=rake*25.4;
            wheel= (Double.parseDouble(etW.getText().toString()))/2;
            if(inW) wheel=wheel*25.4;
            double y = 1/Math.cos(Math.toRadians(headAngle)) * rake;
            trail = 1/Math.tan(Math.toRadians(headAngle))*(wheel-y);
            if(inT) trail=trail/25.4;
            etT.setText(""+trail);
        }

    }

}

