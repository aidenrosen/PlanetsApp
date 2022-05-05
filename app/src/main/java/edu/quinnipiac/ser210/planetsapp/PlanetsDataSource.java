package edu.quinnipiac.ser210.planetsapp;
/*
	Project by Aiden Rosen and Joseph Noga
	For: PlanetsApp
	Class Name: PlanetsDataSource.java
	Date: 05/04/2022
	Description: Creates a database where the Favorite planets are stored for use in the Favorites tab.

 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PlanetsDataSource {

    //instance variables
    private SQLiteDatabase database;
    private PlanetsSQLiteHelper pHelper;
    private String[] allColumns = { PlanetsSQLiteHelper.KEY_ID, PlanetsSQLiteHelper.PLANET, PlanetsSQLiteHelper.IS_FAVORITE};

    public PlanetsDataSource(Context context)
    {
        pHelper = new PlanetsSQLiteHelper(context);
    }

    public void open() throws SQLException
    {
        database = pHelper.getWritableDatabase();
    }

    public SQLiteDatabase getDatabase() { return database; }
    public void close() { pHelper.close();}

    //code for adding a planet; sets Favorite to true, adds planet name
    public String addPlanet(String planet)
    {
        ContentValues values = new ContentValues();
        values.put(PlanetsSQLiteHelper.PLANET, planet);
        values.put(PlanetsSQLiteHelper.IS_FAVORITE, true);

        long insertId = database.insert(PlanetsSQLiteHelper.TABLE, null, values);

        Cursor cursor = database.query(PlanetsSQLiteHelper.TABLE, allColumns, PlanetsSQLiteHelper.KEY_ID + " = " + insertId, null, null, null,null);

        cursor.moveToFirst();

        String newPlanet = cursorToPlanet(cursor);
        cursor.close();
        return newPlanet;
    }

    private String cursorToPlanet(Cursor cursor)
    {
        return cursor.getString(0);
    }

    //code for deleting a favorite from the database
    public void deletePlanet(String planet)
    {
        database.delete(PlanetsSQLiteHelper.TABLE, PlanetsSQLiteHelper.PLANET + "= '" + planet + "'", null);
    }

    //retrieve all favorites
    public List<String> getAllPlanets()
    {
        List<String> planets = new ArrayList<String>();

        Cursor cursor = database.query(PlanetsSQLiteHelper.TABLE, allColumns, null,null,null,null,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast())
        {
            String planet = cursor.getString(1);
            planets.add(planet);
            cursor.moveToNext();
        }

        cursor.close();
        return planets;
    }


}
