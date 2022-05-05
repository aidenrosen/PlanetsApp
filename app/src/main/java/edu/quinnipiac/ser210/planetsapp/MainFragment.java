package edu.quinnipiac.ser210.planetsapp;
/*
	Project by Aiden Rosen and Joseph Noga
	For: PlanetsApp
 */
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFragment extends Fragment
{

	private static ArrayList<String> favs = new ArrayList<>();

	public interface ViewChangeListener{
		public void onClick(int planetKey);
	}
	private ViewChangeListener listener;

	public MainFragment()
	{
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_main, container, false);

		String[] planetNames = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter",
			"Saturn", "Uranus", "Neptune"};
		PlanetArrayAdapter planetAdapter = new PlanetArrayAdapter(
				getActivity(),
				planetNames);

		ListView lv = (ListView) view.findViewById(R.id.list);
		lv.setAdapter(planetAdapter);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				listener.onClick(position);
				planetAdapter.notifyDataSetChanged();
			}
		});

		PlanetsSQLiteHelper dbHelper = new PlanetsSQLiteHelper(this.getContext());
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		favs.clear();

		Cursor cursor = db.query(PlanetsSQLiteHelper.TABLE, new String[]{PlanetsSQLiteHelper.PLANET, PlanetsSQLiteHelper.IS_FAVORITE}, null, null, null, null,null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			favs.add(cursor.getString(0));
			cursor.moveToNext();
		}

		planetAdapter.notifyDataSetChanged();
		return view;
	}

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
		this.listener = (ViewChangeListener) context;
	}

	public static ArrayList<String> getFavs() {
		return favs;
	}

	public static void addFav(String fav) {favs.add(fav);}

	public static void removeFav(String fav)
	{
		if(favs.contains(fav)) favs.remove(fav);
	}
}