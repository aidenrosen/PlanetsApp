package edu.quinnipiac.ser210.planetsapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Comment;

import androidx.test.core.app.ApplicationProvider;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class PlanetsDataSourceTest {
    private PlanetsDataSource planetsDataSource;
    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        context.deleteDatabase(PlanetsSQLiteHelper.NAME);
        planetsDataSource = new PlanetsDataSource(context);
        planetsDataSource.open();
    }

    @After
    public void tearDown() throws Exception {
        planetsDataSource.close();
    }

    @Test
    public void createPlanet() throws Exception {
        planetsDataSource.addPlanet("Mercury");
        List<String> planets = planetsDataSource.getAllPlanets();
        assertEquals(planets.size(),1);
        assertTrue(planets.get(0).equals("Mercury"));
    }

    @Test
    public void deletePlanet() throws Exception {
        planetsDataSource.deletePlanet("Mercury");
        List<String> planets = planetsDataSource.getAllPlanets();
        assertEquals(planets.size(),0);
    }

}
