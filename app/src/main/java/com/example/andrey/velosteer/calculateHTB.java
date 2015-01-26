package com.android.bikeSteer;

public class calculateHTB 
{

	public static void main(String[] args) 
	{
    	double headAngle= 66;
    	double rake= 37;
    	double aToC= 527;
    	double x = 1/Math.tan(Math.toRadians(headAngle))* rake;
    	double headtubeB = Math.sin(Math.toRadians((headAngle))*(aToC - x));
        System.out.println(x);
        System.out.println(headtubeB);

	}

}
