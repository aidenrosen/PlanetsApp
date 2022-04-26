package edu.quinnipiac.ser210.planetsapp;

import org.json.JSONArray;
import org.json.JSONException;

public class PlanetHandler {
    public static String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn",
            "Uranus", "Neptune"};
    public static String[] stats = new String[]{"Mass", "Radius", "Orbital Period", "Semi-Major Axis",
            "Temperature", "Light Years From Earth"};

    public PlanetHandler() {
        //required empty constructor
    }

    public String getPlanetMass(String planetMassJsonStr) throws JSONException {
        JSONArray countryArray = new JSONArray(planetMassJsonStr);
        return countryArray.getJSONObject(0).getString("mass");
    }

    public String getPlanetRadius(String planetRadiusJsonStr) throws JSONException {
        JSONArray countryArray = new JSONArray(planetRadiusJsonStr);
        return countryArray.getJSONObject(0).getString("radius");
    }

    public String getPlanetOrbit(String planetOrbitJsonStr) throws JSONException {
        JSONArray countryArray = new JSONArray(planetOrbitJsonStr);
        return countryArray.getJSONObject(0).getString("period");
    }

    public String getPlanetAxis(String planetAxisJsonStr) throws JSONException {
        JSONArray countryArray = new JSONArray(planetAxisJsonStr);
        return countryArray.getJSONObject(0).getString("semi_major_axis");
    }

    public String getPlanetTemperature(String planetTempJsonStr) throws JSONException {
        JSONArray countryArray = new JSONArray(planetTempJsonStr);
        return countryArray.getJSONObject(0).getString("temperature");
    }

    public String getPlanetDistance(String planetDistanceJsonStr) throws JSONException {
        JSONArray countryArray = new JSONArray(planetDistanceJsonStr);
        return countryArray.getJSONObject(0).getString("distance_light_year");
    }

}
