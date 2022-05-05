package edu.quinnipiac.ser210.planetsapp;
/*
	Project by Aiden Rosen and Joseph Noga
	For: PlanetsApp
 */
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

        this.favs = MainFragment.getFavs();

        String[] newFavs = new String[favs.size()];
        newFavs = favs.toArray(newFavs);

        FavsArrayAdapter favsArrayAdapter = new FavsArrayAdapter(
                getActivity(),
                newFavs);

        ListView lv = (ListView) view.findViewById(R.id.favList);
        lv.setAdapter(favsArrayAdapter);

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


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (MainFragment.ViewChangeListener) context;
    }
}