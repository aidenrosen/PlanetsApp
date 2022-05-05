package edu.quinnipiac.ser210.planetsapp;
/*
	Project by Aiden Rosen and Joseph Noga
	For: PlanetsApp
	Class Name: PlanetsSQLiteHelper.java
	Date: 05/04/2022
	Description: Used as a helper to the database used to store Favorites.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;

public class PlanetsSQLiteHelper extends SQLiteOpenHelper
{
	public static final String NAME = "Planets_database";
	public static final String PLANET = "planet";
	public static final String TABLE = "planet_table";
	public static final String KEY_ID = "_id";
	public static final String IS_FAVORITE= "is_fav";
	private static final int DATABASE_VERSION = 21;

	public PlanetsSQLiteHelper(@Nullable Context context)
	{
		super(context, NAME, null, DATABASE_VERSION);
	}

	//if the table doesnt exist, create it
	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase)
	{
			String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PLANET + " TEXT NOT NULL, " + IS_FAVORITE + " BOOLEAN);";
			sqLiteDatabase.execSQL(CREATE_TABLE);
	}

	//if the version updates, drop the table if it exists
	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
	{
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(sqLiteDatabase);
	}

}
