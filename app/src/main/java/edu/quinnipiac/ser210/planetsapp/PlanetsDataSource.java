package edu.quinnipiac.ser210.planetsapp;
/*
	Project by Aiden Rosen and Joseph Noga
	For: PlanetsApp
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

    public void deletePlanet(String planet)
    {
        database.delete(PlanetsSQLiteHelper.TABLE, PlanetsSQLiteHelper.PLANET + "= '" + planet + "'", null);
    }

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
