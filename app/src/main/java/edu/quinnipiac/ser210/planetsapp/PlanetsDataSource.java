package edu.quinnipiac.ser210.planetsapp;

import android.database.sqlite.SQLiteDatabase;

public class PlanetsDataSource {
    private SQLiteDatabase database;
    private PlanetsSQLiteHelper pHelper;
    private String[] allColumns = { PlanetsSQLiteHelper.KEY_ID, PlanetsSQLiteHelper.IS_FAVORITE};
}
