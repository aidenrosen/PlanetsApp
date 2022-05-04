package edu.quinnipiac.ser210.planetsapp;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CompareFragment extends Fragment
{
	private PlanetHandler planetHandler = new PlanetHandler();
	Spinner planetOne, planetTwo, statSelected;
	String p1, p2, sStat, s1, s2;
	private AppCompatActivity context;
	private String urlAccess = "https://planets-by-api-ninjas.p.rapidapi.com/v1/planets?name=";

	public CompareFragment() {}

	public CompareFragment(AppCompatActivity context)
	{
		this.context = context;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	class FetchPlanetStat extends AsyncTask<String, Void, String>
	{

		@Override
		protected String doInBackground(String... strings) {
			HttpURLConnection urlConnection = null;
			HttpURLConnection urlConnection2 = null;
			BufferedReader reader = null;
			BufferedReader reader2 = null;
			String stat1 = "";
			String stat2 = "";
			try
			{
				URL url = new URL(urlAccess + strings[0]);
				urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setRequestProperty("X-RapidAPI-Key", "d35f5d26e1mshafc3e3ad636a793p1f9ef0jsnb85767fe0bf6");
				urlConnection.connect();

				URL url2 = new URL(urlAccess + strings[1]);
				urlConnection2 = (HttpURLConnection) url2.openConnection();
				urlConnection2.setRequestMethod("GET");
				urlConnection2.setRequestProperty("X-RapidAPI-Key", "d35f5d26e1mshafc3e3ad636a793p1f9ef0jsnb85767fe0bf6");
				urlConnection2.connect();

				InputStream in = urlConnection.getInputStream();
				if(in == null) return null;

				InputStream in2 = urlConnection2.getInputStream();
				if(in2 == null) return null;

				reader = new BufferedReader(new InputStreamReader(in));
				stat1 = getStringFromBuffer(reader);

				reader2 = new BufferedReader(new InputStreamReader(in2));
				stat2 = getStringFromBuffer(reader2);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
			finally
			{
				if(urlConnection != null) urlConnection.disconnect();
				if(urlConnection2 != null) urlConnection2.disconnect();
				if(reader != null && reader2 != null)
				{
					try
					{
						reader.close();
						reader2.close();
					}
					catch(Exception e)
					{
						return null;
					}
				}
			}
			s1 = stat1;
			s2 = stat2;
			return stat1;
		}

		private String getStringFromBuffer(BufferedReader reader) throws Exception
		{
			StringBuffer buffer = new StringBuffer();
			String line;

			while((line = reader.readLine()) != null)
			{
				buffer.append(line + "\n");
			}

			if(reader != null)
			{
				try
				{
					reader.close();

				}
				catch(Exception e)
				{
					return null;
				}
			}


			//call a specific retrieval function based on the selected stat
			String result = "";
			if (sStat.equals("Mass"))
			{
				result = planetHandler.getPlanetMass(buffer.toString());
			}
			else if (sStat.equals("Radius"))
			{
				result = planetHandler.getPlanetRadius(buffer.toString());
			}
			else if (sStat.equals("Orbital Period"))
			{
				result = planetHandler.getPlanetOrbit(buffer.toString());
			}
			else if (sStat.equals("Semi-Major Axis"))
			{
				result = planetHandler.getPlanetAxis(buffer.toString());
			}
			else if (sStat.equals("Temperature"))
			{
				result = planetHandler.getPlanetTemperature(buffer.toString());
			}
			else if (sStat.equals("Light Years From Earth"))
			{
				result = planetHandler.getPlanetDistance(buffer.toString());
			}

			return result;
		}

		@Override
		protected void onPostExecute(String s)
		{
			TextView planetName1 = (TextView) getActivity().findViewById(R.id.planetText1);
			TextView planetName2 = (TextView) getActivity().findViewById(R.id.planetText2);
			TextView planetStat1 = (TextView) getActivity().findViewById(R.id.statView1);
			TextView planetStat2 = (TextView) getActivity().findViewById(R.id.statView2);

			planetName1.setText(p2);
			planetName2.setText(p1);
			planetStat1.setText(s2);
			planetStat2.setText(s1);


		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_compare, container, false);
		planetOne = (Spinner) view.findViewById(R.id.spinner1);
		planetTwo = (Spinner) view.findViewById(R.id.spinner2);
		statSelected = (Spinner) view.findViewById(R.id.spinnerStat);

		//add country names to country spinners
		ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, planetHandler.planets);
		planetOne.setAdapter(countryAdapter);
		planetTwo.setAdapter(countryAdapter);

		//add stat options to stat spinner
		ArrayAdapter<String> statAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, planetHandler.stats);
		statSelected.setAdapter(statAdapter);

		Button button = (Button) view.findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				p1 = (String) planetOne.getSelectedItem();
				p2 = (String) planetTwo.getSelectedItem();
				sStat = (String) statSelected.getSelectedItem();
				new FetchPlanetStat().execute(p1, p2, sStat);
			}
		});

		((AppCompatActivity) this.getContext()).getSupportActionBar().setTitle("Compare");

		return view;
	}
}