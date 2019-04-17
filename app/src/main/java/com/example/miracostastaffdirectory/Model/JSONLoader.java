package com.example.miracostastaffdirectory.Model;

import android.content.Context;
import android.text.InputType;
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
        int it=0;
        InputStream is = null;

        ReadJSONLoop:
        while (true) {

            it++;
            switch (it) {
                case(1):
                    is = context.getAssets().open("AdimOfJustice.json");
                    break;
                case(2):
                    is = context.getAssets().open("ArtDepartment.json");
                    break;
                case(3):
                    is = context.getAssets().open("AutomotiveTechnologyDepartment.json");
                    break;
                case(4):
                    is = context.getAssets().open("BiologicalScienceDepartment.json");
                    break;
                case(5):
                    is = context.getAssets().open("BiotechnologyDepartment.json");
                    break;
                case(6):
                    is = context.getAssets().open("BusinessDepartment.json");
                    break;
                case(7):
                    is = context.getAssets().open("CareerDepartment.json");
                    break;
                case(8):
                    is = context.getAssets().open("ChemistryDepartment.json");
                    break;
                case(9):
                    is = context.getAssets().open("ChildDevelopmentDepartment.json");
                    break;
                case(10):
                    is = context.getAssets().open("CommunicationStudiesDepartment.json");
                    break;
                case(11):
                    is = context.getAssets().open("CounselingDepartment.json");
                    break;
                case(12):
                    is = context.getAssets().open("CSDepartment.json");
                    break;
                case(13):
                    is = context.getAssets().open("CSITDepartment.json");
                    break;
                case(14):
                    is = context.getAssets().open("DanceDepartment.json");
                    break;
                case(15):
                    is = context.getAssets().open("DesignDepartment.json");
                    break;
                case(16):
                    is = context.getAssets().open("EnglishDepartment.json");
                    break;
                case(17):
                    is = context.getAssets().open("ESLDepartment.json");
                    break;
                case(18):
                    is = context.getAssets().open("HistoryDepartment.json");
                    break;
                case(19):
                    is = context.getAssets().open("HorticultureDepartment.json");
                    break;
                case(20):
                    is = context.getAssets().open("IMTDDepartment.json");
                    break;
                case(21):
                    is = context.getAssets().open("InternationalLanguagesDepartment.json");
                    break;
                case(22):
                    is = context.getAssets().open("KinesiologyDepartment.json");
                    break;
                case(23):
                    is = context.getAssets().open("LibraryScienceDepartment.json");
                    break;
                case(24):
                    is = context.getAssets().open("MathDepartment.json");
                    break;
                case(25):
                    is = context.getAssets().open("MusicDepartment.json");
                    break;
                case(26):
                    is = context.getAssets().open("NursingDepartment.json");
                    break;
                case(27):
                    is = context.getAssets().open("PhilosophyAndReligiousDepartment.json");
                    break;
                case(28):
                    is = context.getAssets().open("PhysicalScienceDepartment.json");
                    break;
                case(29):
                    is = context.getAssets().open("PsychologyDepartment.json");
                    break;
                case(30):
                    is = context.getAssets().open("SociologyDepartment.json");
                    break;
                case(31):
                    is = context.getAssets().open("TheatreAndFilmDepartment.json");
                    break;
                default:
                    break ReadJSONLoop;
            }


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
                String name, title, phoneExt, location, email;
                StaffMember sm = null;

                for (int i = 0; i < numCountries; i++) {
                    countryJSON = allCountriesJSON.getJSONObject(i);

                    // Extract the name and region
                    name = countryJSON.getString("Name");
                    title = countryJSON.getString("Title");
                    phoneExt = countryJSON.getString("Phone");
                    location = countryJSON.getString("Location");
                    email = countryJSON.getString("Email");

                    // Add object for each and add the object to the allCountriesList
                    sm = new StaffMember(name, title, phoneExt, location, email);
                    allStaffList.add(sm);
                }


            } catch (JSONException e) {
                Log.e("Flag Quiz", e.getMessage());
            }
        }

        return allStaffList;
    }
}
