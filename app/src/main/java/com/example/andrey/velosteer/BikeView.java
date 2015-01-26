package com.android.bikeSteer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BikeView extends View
{
	int viewH, viewW;
	public BikeView(Context context) 
	{
		super(context);
	}
	
	public BikeView(Context context, AttributeSet attr)
	{
		super(context, attr);
	}
	
	public void orient()
	{
	    viewW = this.getWidth();
    	viewH = this.getHeight();
	}
	

	public void onDraw(Canvas c)
	{
    	Paint p = new Paint();
    	p.setColor(Color.BLACK);
    	c.drawCircle((viewW/2), (viewH/2), 100, p);
	}

}
