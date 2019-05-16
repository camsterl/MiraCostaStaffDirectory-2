package com.example.miracostastaffdirectory.Model;

import android.content.Context;
import android.text.InputType;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Class loads Country data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (Country) with data.
 */
public class LocationsJSONLoader {

    /**
     * Loads JSON data from a file in the assets directory.
     *
     * @param context The activity from which the data is loaded.
     * @throws IOException If there is an error reading from the JSON file.
     */
    public static List<Location> loadJSONFromAsset(Context context) throws IOException {

        List<Location> allLocList = new ArrayList<>();
        String json = null;

        InputStream is = null;

        is = context.getAssets().open("locations.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, StandardCharsets.UTF_8);

        try {
            JSONObject jsonRootObject = new JSONObject(json);
            JSONArray allLocJSON = jsonRootObject.getJSONArray("locations");

            // Loop through all the countries in the JSON data, create a Country
            int numLocations = allLocJSON.length();

            // To be used in loop
            JSONObject LocJSON;
            String name;
            double lattd, longtd;
            Location loc = null;

            for (int i = 0; i < numLocations; i++) {
                LocJSON = allLocJSON.getJSONObject(i);

                // Extract the name and region
                name = LocJSON.getString("name");
                lattd = LocJSON.getDouble("lat");
                longtd = LocJSON.getDouble("long");

                // Add object for each and add the object to the allCountriesList
                loc = new Location(name, lattd, longtd);
                allLocList.add(loc);
            }



        } catch (JSONException e) {
            Log.e("MC Staff Dir", e.getMessage());
        }

        System.out.println("allStaffList From JSON below");
        System.out.println(allLocList.toString());
        System.out.println("allStaffList From JSON above");
        return allLocList;
    }
}


