package edu.quinnipiac.ser210.planetsapp;
/*
	Project by Aiden Rosen and Joseph Noga
	For: PlanetsApp
	Class Name: PlanetHandler.java
	Date: 05/04/2022
	Description: Used for the AsyncTask in DescriptionFragment. Actually performs the retrieval of
	specific planet information based on the required stat/planet selected.
 */
import org.json.JSONArray;
import org.json.JSONException;

public class PlanetHandler {

    //instance variables
    public static String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn",
            "Uranus", "Neptune"};
    public static String[] stats = new String[]{"Mass", "Radius", "Orbital Period", "Semi-Major Axis",
            "Temperature", "Light Years From Earth"};

    public PlanetHandler() {
        //required empty constructor
    }

    //retrieves the planet's mass
    public String getPlanetMass(String planetMassJsonStr) throws JSONException {
        JSONArray countryArray = new JSONArray(planetMassJsonStr);
        return countryArray.getJSONObject(0).getString("mass");
    }

    //retrieves the planet's radius
    public String getPlanetRadius(String planetRadiusJsonStr) throws JSONException {
        JSONArray countryArray = new JSONArray(planetRadiusJsonStr);
        return countryArray.getJSONObject(0).getString("radius");
    }

    //retrieves the planet's orbital period
    public String getPlanetOrbit(String planetOrbitJsonStr) throws JSONException {
        JSONArray countryArray = new JSONArray(planetOrbitJsonStr);
        return countryArray.getJSONObject(0).getString("period");
    }

    //retrieve's the planets semi-major axis
    public String getPlanetAxis(String planetAxisJsonStr) throws JSONException {
        JSONArray countryArray = new JSONArray(planetAxisJsonStr);
        return countryArray.getJSONObject(0).getString("semi_major_axis");
    }

    //retrieves the planet's atmospheric temperature
    public String getPlanetTemperature(String planetTempJsonStr) throws JSONException {
        JSONArray countryArray = new JSONArray(planetTempJsonStr);
        return countryArray.getJSONObject(0).getString("temperature");
    }

    //retrieves the planet's distance from Earth
    public String getPlanetDistance(String planetDistanceJsonStr) throws JSONException {
        JSONArray countryArray = new JSONArray(planetDistanceJsonStr);
        return countryArray.getJSONObject(0).getString("distance_light_year");
    }

}
