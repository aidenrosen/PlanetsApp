package edu.quinnipiac.ser210.planetsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainFragment.ViewChangeListener
{
	public DrawerLayout drawerLayout;
	public NavController navController;
	public NavigationView navigationView;
	public NavHostFragment navHostFragment;
	public Toolbar toolbar;
	private SQLiteDatabase db;
	private PlanetsSQLiteHelper dbHelper;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		//Navigation code
		drawerLayout = findViewById(R.id.drawer_layout);
		navigationView = findViewById(R.id.navigationView);
		navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
		navController = navHostFragment.getNavController();
		NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
		NavigationUI.setupWithNavController(navigationView, navController);
		navigationView.setNavigationItemSelectedListener(this);

		//SQL Code
		dbHelper = new PlanetsSQLiteHelper(this);
		db = dbHelper.getWritableDatabase();

	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item)
	{
		int id = item.getItemId();

		switch(id)
		{
			case R.id.main_frag:
				navController.navigate(R.id.mainFragment);
				break;
			case R.id.comp_frag:
				navController.navigate(R.id.compareFragment);
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

	@Override
	public void onBackPressed()
	{
		if (drawerLayout.isDrawerOpen(GravityCompat.START))
		{
			drawerLayout.closeDrawer(GravityCompat.START);
		}
		else super.onBackPressed();
	}

	@Override
	public void onClick(int planetKey) {
//		DescriptionFragment fragment = new DescriptionFragment();
//		Bundle bundle = new Bundle();
//		bundle.putInt("planet", planetKey);
//		fragment.setArguments(bundle);
		Bundle bundle = new Bundle();
		bundle.putInt("planet", planetKey);
		navController.navigate(R.id.descriptionFragment, bundle);
	}
}