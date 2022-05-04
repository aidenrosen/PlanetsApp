package edu.quinnipiac.ser210.planetsapp;

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
	private int planetKey, imageId;
	private String planetDescription, planet;

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
					planetDescription = getString(R.string.mercury_desc);
					imageId = R.drawable.mercury;
					planet = "Mercury";
					break;
				case 1:
					planetDescription = getString(R.string.venus_desc);
					imageId = R.drawable.venus;
					planet = "Venus";
					break;
				case 2:
					planetDescription = getString(R.string.earth_desc);
					imageId = R.drawable.earth;
					planet = "Earth";
					break;
				case 3:
					planetDescription = getString(R.string.mars_desc);
					imageId = R.drawable.mars;
					planet = "Mars";
					break;
				case 4:
					planetDescription = getString(R.string.jupiter_desc);
					imageId = R.drawable.jupiter;
					planet = "Jupiter";
					break;
				case 5:
					planetDescription = getString(R.string.saturn_desc);
					imageId = R.drawable.saturn;
					planet = "Saturn";
					break;
				case 6:
					planetDescription = getString(R.string.uranus_desc);
					imageId = R.drawable.uranus;
					planet = "Uranus";
					break;
				case 7:
					planetDescription = getString(R.string.neptune_desc);
					imageId = R.drawable.neptune;
					planet = "Neptune";
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

		//Set the components of the fragment based on what the user selects
		TextView description = (TextView) view.findViewById(R.id.news);
		description.setText(planetDescription);

		ImageView image = (ImageView) view.findViewById(R.id.planetImage);
		image.setImageResource(imageId);

		((AppCompatActivity) this.getContext()).getSupportActionBar().setTitle(planet);

		return view;
	}
}