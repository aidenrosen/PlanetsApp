package edu.quinnipiac.ser210.planetsapp;
/*
	Project by Aiden Rosen and Joseph Noga
	For: PlanetsApp
	Date: 05/04/2022
	Class Name: MainActivity.java
	Description: main frame for the entire app; hosts MainFragment and connects to all other app fragments.
 */
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainFragment.ViewChangeListener
{
	//instance variables
	public DrawerLayout drawerLayout;
	public NavController navController;
	public NavigationView navigationView;
	public NavHostFragment navHostFragment;
	public Toolbar toolbar;
	private SQLiteDatabase db;
	private PlanetsSQLiteHelper dbHelper;
	private ListView lv;
	private PlanetsDataSource planetsDataSource;
	private ArrayList<String> favs = new ArrayList<String>();
	private ShareActionProvider provider;
	private boolean blueMode = false;
	private int currFrag;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//refer to toolbar
		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		//Navigation code
		drawerLayout = findViewById(R.id.drawer_layout);
		navigationView = findViewById(R.id.navigationView);
		navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
		navController = navHostFragment.getNavController();
		NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
		planetsDataSource = new PlanetsDataSource(this);
		planetsDataSource.open();
		NavigationUI.setupWithNavController(navigationView, navController);
		navigationView.setNavigationItemSelectedListener(this);
		lv = (ListView) findViewById(R.id.list);

		//open SQLite database where favorites will be stored
		dbHelper = new PlanetsSQLiteHelper(this);
		db = dbHelper.getWritableDatabase();

		Cursor cursor = db.query(PlanetsSQLiteHelper.TABLE, new String[] {PlanetsSQLiteHelper.PLANET},null,null,null,null,null);
		cursor.moveToFirst();

		while(!cursor.isAfterLast())
		{
			favs.add(cursor.getString(0));
			cursor.moveToNext();
		}

		for(int i = 0; i < PlanetArrayAdapter.fav.length; i++)
		{
			for(int j = 0; j < favs.size(); j++)
			{
				if(PlanetHandler.planets[i].equals(favs.get(j)))
				{
					PlanetArrayAdapter.flipIndex(i);
				}
			}
		}

	}

	//sets up an Options widget in the Toolbar
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_main, menu);
		MenuItem item = menu.findItem(R.id.share);
		provider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
		setShareActionIntent();
		return super.onCreateOptionsMenu(menu);
	}

	//sets up the Share widget in Toolbar
	private void setShareActionIntent()
	{
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "Look at this information about planets that I found!");
		provider.setShareIntent(intent);
	}

	//background color changes when pressing Options widget
	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item)
	{
		View activity = (View) findViewById(R.id.drawer_layout);

		if(item.getItemId() == R.id.change_background)
		{
			if(blueMode)
			{
				activity.setBackgroundColor(Color.parseColor("#FFFFFF"));
			} else activity.setBackgroundColor(Color.parseColor("#00FFFF"));
			blueMode = !blueMode; //Change background color, then save if it's blue or not and invert it
		}

		return super.onOptionsItemSelected(item);
	}

	//Logic for nav drawer
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item)
	{
		int id = item.getItemId();

		switch(id)
		{
			case R.id.main_frag:
				navController.navigate(R.id.mainFragment);
				currFrag = R.id.main_frag;
				break;
			case R.id.comp_frag:
				navController.navigate(R.id.compareFragment);
				currFrag = R.id.comp_frag;
				break;
			case R.id.fav_frag:
				navController.navigate(R.id.favoritesFragment);
				currFrag = R.id.fav_frag;
				break;
			case R.id.news_frag:
				navController.navigate(R.id.newsFragment);
				currFrag = R.id.news_frag;
				break;
		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@Override
	public boolean onSupportNavigateUp()
	{
		return NavigationUI.navigateUp(navController, drawerLayout);
	}

	//close Drawer when back button is pressed
	@Override
	public void onBackPressed()
	{
		if (drawerLayout.isDrawerOpen(GravityCompat.START))
		{
			drawerLayout.closeDrawer(GravityCompat.START);
		}
		else super.onBackPressed();
	}

	//upon clicking any Planet in the MainFragment ListView
	@Override
	public void onClick(int planetKey) {
		Bundle bundle = new Bundle();
		bundle.putInt("planet", planetKey);
		navController.navigate(R.id.descriptionFragment, bundle); //Send which planet the user clicks on to the description fragment
	}

	//code for saving a favorite and updating its image to reflect whether a Planet is a favorite or not
	public void onSelectFavorite(View view)
	{
		ImageButton button = (ImageButton) view;
		String tag = (String) button.getTag(R.id.positionOnList);
		int position = Integer.parseInt(tag);
//		Drawable currDrawable = button.getDrawable();
//		Drawable favSelected = getResources().getDrawable(R.drawable.fav_selected_foreground);
//		if(currDrawable.getConstantState().equals(favSelected.getConstantState()))
		if(button.getDrawable().equals(R.drawable.fav_selected_foreground) || button.getTag(R.id.drawable).equals(R.drawable.fav_selected_foreground))
		{
			//Set the image and tag of the button
			button.setImageResource(R.drawable.fav_not_selected_foreground);
			button.setTag(R.id.drawable, R.drawable.fav_not_selected_foreground);

			//Remove from database
			planetsDataSource.deletePlanet(PlanetHandler.planets[position]);
			MainFragment.removeFav(PlanetHandler.planets[position]);
		}
		else
		{
			button.setImageResource(R.drawable.fav_selected_foreground);
			button.setTag(R.id.drawable, R.drawable.fav_selected_foreground);

			//Add to database
			planetsDataSource.addPlanet(PlanetHandler.planets[position]);
			MainFragment.addFav(PlanetHandler.planets[position]);

		}
		PlanetArrayAdapter.flipIndex(position);


	}
}