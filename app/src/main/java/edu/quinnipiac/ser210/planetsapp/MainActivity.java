package edu.quinnipiac.ser210.planetsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Turns the initial FrameLayout into the MainFragment
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.container, new MainFragment());
		ft.commit();
	}
}