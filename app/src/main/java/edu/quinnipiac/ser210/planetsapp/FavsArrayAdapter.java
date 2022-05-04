package edu.quinnipiac.ser210.planetsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FavsArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;
    private static ArrayList<String> favs;

    public FavsArrayAdapter(@NonNull Context context, @NonNull String[] values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
        favs = MainFragment.getFavs();

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //creates a View representing a Java object out of the layer
        View rowView = inflater.inflate(R.layout.favlayout,parent,false); //reference for TextView
        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);

        //Logic for row view icons
//        switch(position)
//        {
//            case 0:
//                icon.setImageResource(R.drawable.mercury);
//                break;
//            case 1:
//                icon.setImageResource(R.drawable.venus);
//                break;
//            case 2:
//                icon.setImageResource(R.drawable.earth);
//                break;
//            case 3:
//                icon.setImageResource(R.drawable.mars);
//                break;
//            case 4:
//                icon.setImageResource(R.drawable.jupiter);
//                break;
//            case 5:
//                icon.setImageResource(R.drawable.saturn);
//                break;
//            case 6:
//                icon.setImageResource(R.drawable.uranus);
//                break;
//            case 7:
//                icon.setImageResource(R.drawable.neptune);
//                break;
//        }

        return rowView;

    }



}
