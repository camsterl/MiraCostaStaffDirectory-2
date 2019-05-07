package com.example.miracostastaffdirectory;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.miracostastaffdirectory.Model.StaffMember;

import java.util.ArrayList;

public class SingleDeptStaff extends AppCompatActivity {

    private ArrayList<StaffMember> staffInDept;
    private ArrayList<StaffMember> allStaff;
    public static Activity singleDeptStaffAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_dept_staff);

        singleDeptStaffAct = this;

        staffInDept = new ArrayList<>();

        Intent fromDepartmentsAct = getIntent();
        String deptToLoad = fromDepartmentsAct.getStringExtra("department");
        allStaff = fromDepartmentsAct.getParcelableArrayListExtra("allStaff");


        // we will assign this string based on what department we're passed from Departments.java
        //      then we'll use it in a for loop to only pull out the staff members that have this in their TITLE
        //          just have to make sure that every staff member in the JSON has this word in their title first
        //          before I go blindly assigning this (the word must also be unique to that department's staff)
        String searchKey="";

        // if there is no one common word we need to use two
        boolean thereAreTwoWordsToSearchFor = false;
        String secondSearchKey="";

        // okey dokey so there are some that require 3............
        boolean thereAreThreeFuckingWords = false;
        String thirdSearchKey="";


        switch (deptToLoad) {

            case ("Administration Of Justice Department"):
                searchKey = "Justice";
                break;
            case ("Art Department"):
                searchKey = "Art";
                break;
            case ("Automotive Technology Department"):
                searchKey = "Automotive";
                break;
            case ("Biological Science Department"):
                searchKey = "Biology";
                break;
            case ("Biotechnology Department"):
                thereAreTwoWordsToSearchFor = true;
                searchKey = "Biotechnology";
                secondSearchKey = "Biomanufacturing";
                break;
            case ("Business Department"):
                thereAreThreeFuckingWords = true;
                searchKey = "Business";
                secondSearchKey = "Accounting";
                thirdSearchKey = "Hospitality";
                break;
            case ("Career Department"):
                searchKey = "Career";
                break;
            case ("Chemistry Department"):
                searchKey = "Chemistry";
                break;
            case ("Child Development Department"):
                searchKey = "Child";
                break;
            case ("Communication Studies Department"):
                searchKey = "Communication";
                break;
            case ("Counseling Department"):
                searchKey = "Counsel";
                break;
            case ("Computer Science Department"):
                searchKey = "Computer";
                break;
            case ("CSIT Department"):
                searchKey = "CSIT";
                break;
            case ("Dance Department"):
                searchKey = "Dance";
                break;
            case ("Design Department"):
                searchKey = "Design";
                break;
            case ("English Department"):
                searchKey = "English";
                break;
            case ("ESLD Department"):
                searchKey = "ESL";
                break;
            case ("History Department"):
                searchKey = "History";
                break;
            case ("Horticulture Department"):
                searchKey = "Horticulture";
                break;
            case ("IMTD Department"):
                searchKey = "Media Arts";
                break;
            case ("International Languages Department"):
                searchKey = "International Languages";
                break;
            case ("Kinesiology Department"):
                thereAreThreeFuckingWords = true;
                searchKey = "Kinesiology";
                secondSearchKey = "Massage";
                thirdSearchKey = "Nutrition";
                break;
            case ("Library Science Department"):
                searchKey = "Librar";
                break;
            case ("Math Department"):
                searchKey = "Math";
                break;
            case ("Music Department"):
                searchKey = "Music";
                break;
            case ("Nursing Department"):
                thereAreThreeFuckingWords = true;
                searchKey = "Nursing";
                secondSearchKey = "Surgical";
                thirdSearchKey = "Pharmacology";
                break;
            case ("Philosophy And Religious Department"):
                thereAreTwoWordsToSearchFor = true;
                searchKey = "Philosophy";
                secondSearchKey = "Religious";
                break;
            case ("Physical Science Department"):
                thereAreThreeFuckingWords = true;
                searchKey = "Physics";
                secondSearchKey = "Oceanography";
                thirdSearchKey = "Astronomy";
                break;
            case ("Psychology Department"):
                searchKey = "Psychology";
                break;
            case ("Sociology Department"):
                searchKey = "Sociology";
                break;
            case ("Theatre And Film Department"):
                thereAreThreeFuckingWords = true;
                searchKey = "Film";
                secondSearchKey = "Theatre";
                thirdSearchKey = "Drama";
                break;
            default:
                System.out.println("ERROR CODE: 000000x5556732222x90");
                System.out.println("Something went terribly wrong, we didn't get a matching string " +
                        "\nfrom the Departments Activity in the Intent");
                System.exit(0);
        }

        // if we are searching for three words
        if (thereAreThreeFuckingWords) {

            // loop through all staff
            for (int i=0; i < allStaff.size(); i++) {

                // if it contains one of the three search keys
                if (allStaff.get(i).getTitle().contains(searchKey)
                        || allStaff.get(i).getTitle().contains(secondSearchKey)
                        || allStaff.get(i).getTitle().contains(thirdSearchKey)) {

                    // add it to the list
                    staffInDept.add(allStaff.get(i));
                }
            }
        }

        // if we are searching for two words
        if (thereAreTwoWordsToSearchFor) {

            // loop through all staff
            for (int i =0; i < allStaff.size(); i++) {

                //if the title contains one of the two search words
                if (allStaff.get(i).getTitle().contains(searchKey)
                    ||  allStaff.get(i).getTitle().contains(secondSearchKey)) {

                    // add it to the list
                    staffInDept.add(allStaff.get(i));
                }

            }

        }

        // else we are only searching for one search keyword
        else {
            for (int i =0; i < allStaff.size(); i++) {
                if (allStaff.get(i).getTitle().contains(searchKey)) {
                    staffInDept.add(allStaff.get(i));
                }
            }
        }

    }
}
