package edu.quinnipiac.ser210.planetsapp;
/*
	Project by Aiden Rosen and Joseph Noga
	For: PlanetsApp
	Class Name: FavoritesFragment.java
	Date: 05/04/2022
	Description: Displays the favorites selected in the MainFragment in another ListView.
 */
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment {

    //instance variables
    private ArrayList<String> favs;
    private MainFragment.ViewChangeListener listener;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        //retrieve whatever is a Favorite from the MainFragment
        this.favs = MainFragment.getFavs();

        String[] newFavs = new String[favs.size()];
        newFavs = favs.toArray(newFavs);

        FavsArrayAdapter favsArrayAdapter = new FavsArrayAdapter(
                getActivity(),
                newFavs);

        //ListView contains all Favorites selected
        ListView lv = (ListView) view.findViewById(R.id.favList);
        lv.setAdapter(favsArrayAdapter);

        //determines the position of each favorite in the ListView for easier reference to DescriptionFragment
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] planetNames = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter",
                        "Saturn", "Uranus", "Neptune"};
                String selected = (String) parent.getItemAtPosition(position);
                for (int i = 0; i < 8; i++)
                {
                    String planet = planetNames[i];
                    if (selected.equals(planet))
                    {
                        position = i;
                    }
                }
                listener.onClick(position);
                favsArrayAdapter.notifyDataSetChanged();
            }
        });

        ((AppCompatActivity) this.getContext()).getSupportActionBar().setTitle("Favorites");

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //refers to MainActivity as a listener in order to obtain context
        this.listener = (MainFragment.ViewChangeListener) context;
    }
}