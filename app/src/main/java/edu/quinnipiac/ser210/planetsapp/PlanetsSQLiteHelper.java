package edu.quinnipiac.ser210.planetsapp;

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

	public static final String DATABASE_NAME = "planet_table.db";
	private static final int DATABASE_VERSION = 1;

	public PlanetsSQLiteHelper(@Nullable Context context)
	{
		super(context, NAME, null, 9);
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
