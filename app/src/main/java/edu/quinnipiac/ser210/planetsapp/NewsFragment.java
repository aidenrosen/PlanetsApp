package edu.quinnipiac.ser210.planetsapp;
/*
	Project by Aiden Rosen and Joseph Noga
	For: PlanetsApp
 */
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.Locale;

public class NewsFragment extends Fragment
{
	private int imageId;
	private String[] news;
	TextView t1, t2, t3;
	View mView;
	ImageView imageView;

	public NewsFragment()
	{
		// Required empty public constructor
	}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		int defaultValue = 0;
		news = new String[]{"", "", ""}; //Default array of 3 things to initialize it
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		mView = inflater.inflate(R.layout.fragment_news, container, false);
		setHasOptionsMenu(true);

		t1 = (TextView) mView.findViewById(R.id.news1);
		t2 = (TextView) mView.findViewById(R.id.news2);
		t3 = (TextView) mView.findViewById(R.id.news3);
		imageView = (ImageView) mView.findViewById(R.id.newsImage);

		String[] startNews = getResources().getStringArray(R.array.mercury_news);

		t1.setText(startNews[0]);
		t2.setText(startNews[1]);
		t3.setText(startNews[2]);
		imageView.setImageResource(R.drawable.mercury); //Initialize the fragment to contain mercury's info, since that's the first planet

		((AppCompatActivity) this.getContext()).getSupportActionBar().setTitle(""); //Remove the title since the action bar is cluttered
		return mView;
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
	{
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.news_menu, menu);
		MenuItem item = menu.findItem(R.id.spinner);
		Spinner spinner = (Spinner) item.getActionView();

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.planets, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
			{
				switch(PlanetHandler.planets[i].toLowerCase()) //Logic for updating fragment once the user selects an item from the spinner
				{
					case "mercury":
						news = getResources().getStringArray(R.array.mercury_news);
						imageId = R.drawable.mercury;
						break;
					case "venus":
						news = getResources().getStringArray(R.array.venus_news);
						imageId = R.drawable.venus;
						break;
					case "earth":
						news = getResources().getStringArray(R.array.earth_news);
						imageId = R.drawable.earth;
						break;
					case "mars":
						news = getResources().getStringArray(R.array.mars_news);
						imageId = R.drawable.mars;
						break;
					case "jupiter":
						news = getResources().getStringArray(R.array.jupiter_news);
						imageId = R.drawable.jupiter;
						break;
					case "saturn":
						news = getResources().getStringArray(R.array.saturn_news);
						imageId = R.drawable.saturn;
						break;
					case "uranus":
						news = getResources().getStringArray(R.array.uranus_news);
						imageId = R.drawable.uranus;
						break;
					case "neptune":
						news = getResources().getStringArray(R.array.neptune_news);
						imageId = R.drawable.neptune;
						break;
				}

				t1.setText(news[0]);
				t2.setText(news[1]);
				t3.setText(news[2]);

				imageView = (ImageView) mView.findViewById(R.id.newsImage);
				imageView.setImageResource(imageId);
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView)
			{

			}
		});
	}

}