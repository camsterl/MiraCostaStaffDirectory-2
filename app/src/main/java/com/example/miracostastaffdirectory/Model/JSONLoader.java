package com.example.miracostastaffdirectory.Model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class loads Country data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (Country) with data.
 */
public class JSONLoader {

    /**
     * Loads JSON data from a file in the assets directory.
     *
     * @param context The activity from which the data is loaded.
     * @throws IOException If there is an error reading from the JSON file.
     */
    public static List<StaffMember> loadJSONFromAsset(Context context) throws IOException {
        List<StaffMember> allStaffList = new ArrayList<>();
        String json = null;


        InputStream is = context.getAssets().open("AdimOfJustice.json");


        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");

        try {
            JSONObject jsonRootObject = new JSONObject(json);
            JSONArray allCountriesJSON = jsonRootObject.getJSONArray("");

            // Loop through all the countries in the JSON data, create a Country
            int numCountries = allCountriesJSON.length();

            // To be used in loop
            JSONObject countryJSON;

            StaffMember sMem = null;

            for (int i = 0; i < numCountries; i++) {
                countryJSON = allCountriesJSON.getJSONObject(i);

              sMem = new StaffMember();
                // Extract the name and region
                sMem.setName(countryJSON.getString("Name"));
                sMem.setTitle(countryJSON.getString("Title"));
                sMem.setPhoneExt(countryJSON.getString("Phone"));
                sMem.setLocation(countryJSON.getString("Location"));
                sMem.setEmail(countryJSON.getString("Email"));

                // Add object for each and add the object to the allCountriesList

                allStaffList.add(sMem);
            }



        } catch (JSONException e) {
            Log.e("Flag Quiz", e.getMessage());
        }

        return allStaffList;
    }
}
