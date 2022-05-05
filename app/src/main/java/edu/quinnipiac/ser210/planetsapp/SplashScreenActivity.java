package edu.quinnipiac.ser210.planetsapp;
/*
	Project by Aiden Rosen and Joseph Noga
	For: PlanetsApp
	Class Name: SplashScreenActivity.java
	Date: 05/04/2022
	Description: Used at the beginning when you boot up the app at first. Displays image and button that lets you access the rest of the app.
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SplashScreenActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		//content view contains ImageView and Button
	}

	//When you click the button, it brings you to the MainActivity class
	public void onClick(View view)
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);

	}
}