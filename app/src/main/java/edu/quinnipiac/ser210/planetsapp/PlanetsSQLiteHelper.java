package edu.quinnipiac.ser210.planetsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PlanetsSQLiteHelper extends SQLiteOpenHelper
{
	private static final String NAME = "Planets_database";
	private static final String TABLE = "planet_table";
	public static final String KEY_ID = "_id";
	public static final String IS_FAVORITE= "is_fav";

	public PlanetsSQLiteHelper(@Nullable Context context)
	{
		super(context, NAME, null, 1);
	}


	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase)
	{
		String CREATE_TABLE = "CREATE TABLE " + TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IS_FAVORITE + " BOOLEAN);";
		sqLiteDatabase.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
	{
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
		onCreate(sqLiteDatabase);
	}
}
