package edu.quinnipiac.ser210.planetsapp;
/*
	Project by Aiden Rosen and Joseph Noga
	For: PlanetsApp
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


	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase)
	{
			String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PLANET + " TEXT NOT NULL, " + IS_FAVORITE + " BOOLEAN);";
			sqLiteDatabase.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
	{
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(sqLiteDatabase);
	}

}
