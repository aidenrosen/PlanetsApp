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

public class PlanetArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;

    public PlanetArrayAdapter(@NonNull Context context, @NonNull String[] values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //creates a View representing a Java object out of the layer
        View rowView = inflater.inflate(R.layout.rowlayout,parent,false); //reference for TextView
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageButton button = (ImageButton) rowView.findViewById(R.id.favorite);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);
        //button.setTag(R.id.drawable, R.drawable.fav_not_selected_foreground);
        button.setTag(R.id.positionOnList ,position + "");
        //String s = values[position];

        return rowView;

    }


}
