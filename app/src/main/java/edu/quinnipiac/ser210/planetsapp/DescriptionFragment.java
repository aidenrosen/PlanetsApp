package edu.quinnipiac.ser210.planetsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DescriptionFragment extends Fragment
{
	private int planetKey;
	private String planetDescription;

	public DescriptionFragment()
	{
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		String key = "planet";
		int defaultValue = 0;

		Bundle bundle = this.getArguments();
		if (bundle != null) {
			int myInt = bundle.getInt(key, defaultValue);
			planetKey = myInt;
			switch (planetKey)
			{
				case 0:
					planetDescription = "You selected Mercury.";
					break;
				case 1:
					planetDescription = "You selected Venus.";
					break;
				case 2:
					planetDescription = "You selected Earth.";
					break;
				case 3:
					planetDescription = "You selected Mars.";
					break;
				case 4:
					planetDescription = "You selected Jupiter.";
					break;
				case 5:
					planetDescription = "You selected Saturn.";
					break;
				case 6:
					planetDescription = "You selected Uranus.";
					break;
				case 7:
					planetDescription = "You selected Neptune.";
					break;
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_description, container, false);
		TextView description = (TextView) view.findViewById(R.id.planetDesc);
		description.setText(planetDescription);
		return view;
	}
}