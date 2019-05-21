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
 * Class loads StaffMember
 *data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (StaffMember
 *) with data.
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
        String json;
        int it=0;

        assignStringLoop:
        while (true) {

            InputStream is = null;
            String JSONCollectionName;

            it++;
            switch (it) {
                case(1):
                    JSONCollectionName = ("AdimOfJusticeDepartment");
                    break;
                case(2):
                    JSONCollectionName = ("ArtDepartment");
                    break;
                case(3):
                    JSONCollectionName = ("AutomotiveTechnologyDepartment");
                    break;
                case(4):
                    JSONCollectionName = ("BiologicalScienceDepartment");
                    break;
                case(5):
                    JSONCollectionName = ("BiotechnologyDepartment");
                    break;
                case(6):
                    JSONCollectionName = ("BusinessDepartment");
                    break;
                case(7):
                    JSONCollectionName = ("CareerDepartment");
                    break;
                case(8):
                    JSONCollectionName = ("ChemistryDepartment");
                    break;
                case(9):
                    JSONCollectionName = ("ChildDevelopmentDepartment");
                    break;
                case(10):
                    JSONCollectionName = ("CommunicationStudiesDepartment");
                    break;
                case(11):
                    JSONCollectionName = ("CounselingDepartment");
                    break;
                case(12):
                    JSONCollectionName = ("CSDepartment");
                    break;
                case(13):
                    JSONCollectionName = ("CSITDepartment");
                    break;
                case(14):
                    JSONCollectionName = ("DanceDepartment");
                    break;
                case(15):
                    JSONCollectionName = ("DesignDepartment");
                    break;
                case(16):
                    JSONCollectionName = ("EnglishDepartment");
                    break;
                case(17):
                    JSONCollectionName = ("ESLDepartment");
                    break;
                case(18):
                    JSONCollectionName = ("HistoryDepartment");
                    break;
                case(19):
                    JSONCollectionName = ("HorticultureDepartment");
                    break;
                case(20):
                    JSONCollectionName = ("IMTDepartment");
                    break;
                case(21):
                    JSONCollectionName = ("InternationalLanguagesDepartment");
                    break;
                case(22):
                    JSONCollectionName = ("KinesiologyDepartment");
                    break;
                case(23):
                    JSONCollectionName = ("LibraryScienceDepartment");
                    break;
                case(24):
                    JSONCollectionName = ("MathDepartment");
                    break;
                case(25):
                    JSONCollectionName = ("MusicDepartment");
                    break;
                case(26):
                    JSONCollectionName = ("NursingDepartment");
                    break;
                case(27):
                    JSONCollectionName = ("PhilosophyAndReligiousDepartment");
                    break;
                case(28):
                    JSONCollectionName = ("PhysicalScienceDepartment");
                    break;
                case(29):
                    JSONCollectionName = ("PsychologyDepartment");
                    break;
                case(30):
                    JSONCollectionName = ("SociologyDepartment");
                    break;
                case(31):
                    JSONCollectionName = ("TheatreAndFilmDepartment");
                    break;
                default:
                    break assignStringLoop;
            }


            is = context.getAssets().open(JSONCollectionName + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);

            try {
                JSONObject jsonRootObject = new JSONObject(json);
                JSONArray allStaffJSON = jsonRootObject.getJSONArray(JSONCollectionName);

                // Loop through all the countries in the JSON data, create a StaffMember
                int numStaff = allStaffJSON.length();

                // To be used in loop
                JSONObject staffJSON;
                String name, title, phoneExt, location, email;
                StaffMember sm = null;

                for (int i = 0; i < numStaff; i++) {
                    staffJSON = allStaffJSON.getJSONObject(i);

                    // Extract the name and region
                    name = staffJSON.getString("A");
                    title = staffJSON.getString("B");
                    phoneExt = staffJSON.getString("D");
                    location = staffJSON.getString("F");
                    email = staffJSON.getString("H");

                    // Add object for each and add the object to the allCountriesList
                    sm = new StaffMember(name, title, phoneExt, location, email);
                    allStaffList.add(sm);
                }



            } catch (JSONException e) {
                Log.e("MC Staff Dir", e.getMessage());
            }
        }

        System.out.println("allStaffList From JSON below");
        System.out.println(allStaffList.toString());
        System.out.println("allStaffList From JSON above");
        return allStaffList;
    }
}
