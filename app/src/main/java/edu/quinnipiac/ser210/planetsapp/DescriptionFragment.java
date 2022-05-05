package edu.quinnipiac.ser210.planetsapp;
/*
	Project by Aiden Rosen and Joseph Noga
	For: PlanetsApp
	Class Name: DescriptionFragment.java
	Date: 05/04/2022
	Description: Displays the general description and picture of a selected planet from the MainFragment.
 */
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionFragment extends Fragment
{
	//instance variables
	private int planetKey, imageId;
	private String news, planet;

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

		//determines which planet data to display depending on what was selected from the ListView
		if (bundle != null)
		{
			int myInt = bundle.getInt(key, defaultValue);
			planetKey = myInt;
			switch(planetKey)
			{
				case 0:
					news = getString(R.string.mercury_desc);
					imageId = R.drawable.mercury;
					planet = "Mercury";
					break;
				case 1:
					news = getString(R.string.venus_desc);
					imageId = R.drawable.venus;
					planet = "Venus";
					break;
				case 2:
					news = getString(R.string.earth_desc);
					imageId = R.drawable.earth;
					planet = "Earth";
					break;
				case 3:
					news = getString(R.string.mars_desc);
					imageId = R.drawable.mars;
					planet = "Mars";
					break;
				case 4:
					news = getString(R.string.jupiter_desc);
					imageId = R.drawable.jupiter;
					planet = "Jupiter";
					break;
				case 5:
					news = getString(R.string.saturn_desc);
					imageId = R.drawable.saturn;
					planet = "Saturn";
					break;
				case 6:
					news = getString(R.string.uranus_desc);
					imageId = R.drawable.uranus;
					planet = "Uranus";
					break;
				case 7:
					news = getString(R.string.neptune_desc);
					imageId = R.drawable.neptune;
					planet = "Neptune";
					break;

			}
		}
	}

	//UI for the fragment; only contains TextView and ImageView, along with Toolbar to share info
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_description, container, false);

		//Set the components of the fragment based on what the user selects
		TextView description = (TextView) view.findViewById(R.id.news);
		description.setText(news);

		ImageView image = (ImageView) view.findViewById(R.id.newsImage);
		image.setImageResource(imageId);

		((AppCompatActivity) this.getContext()).getSupportActionBar().setTitle(planet);

		return view;
	}


}