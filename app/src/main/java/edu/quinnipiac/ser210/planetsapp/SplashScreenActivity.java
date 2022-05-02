package edu.quinnipiac.ser210.planetsapp;

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
	}

	public void onClick(View view)
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);

	}
}