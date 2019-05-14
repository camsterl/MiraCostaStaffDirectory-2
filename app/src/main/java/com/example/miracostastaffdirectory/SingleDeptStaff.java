package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.miracostastaffdirectory.Model.StaffMember;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SingleDeptStaff extends AppCompatActivity {

    private ArrayList<StaffMember> staffInDept;
    private ArrayList<StaffMember> allStaff;
    private ArrayList<StaffMember> filteredStaff;
    private EditText searchET;
    private ListView staffListView;
    private StaffListAdapter adapter;
    int prevScroll = -1;
    String prevSearch = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_staff);

        // og arrayList
        staffInDept = new ArrayList<>();

        Intent from = getIntent();
        String deptToLoad = from.getStringExtra("department");
        // this will get an arrayList regardless of which activity it comes from since they have the same key
        allStaff = from.getParcelableArrayListExtra("allStaff");
        prevScroll = from.getIntExtra("prevScroll", -1);
        prevSearch = from.getStringExtra("prevSearch");
        if (prevSearch==null)
            prevSearch="";


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

        {
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
                for (StaffMember sm : allStaff) {

                    // if it contains one of the three search keys
                    if (sm.getTitle().contains(searchKey)
                            || sm.getTitle().contains(secondSearchKey)
                            || sm.getTitle().contains(thirdSearchKey)) {

                        // add it to the list
                        staffInDept.add(sm);
                    }
                }
            }

            // if we are searching for two words
            if (thereAreTwoWordsToSearchFor) {

                // loop through all staff
                for (StaffMember sm: allStaff) {

                    //if the title contains one of the two search words
                    if (sm.getTitle().contains(searchKey)
                            || sm.getTitle().contains(secondSearchKey)) {

                        // add it to the list
                        staffInDept.add(sm);
                    }

                }

            }

            // else we are only searching for one search keyword
            else {
                for (StaffMember sm: allStaff) {
                    if (sm.getTitle().contains(searchKey)) {
                        staffInDept.add(sm);
                    }
                }
            }
        }

        // allStaff should be populated
        // make a filtered list for searching
        Log.i("MCC Staff Dir", "allStaff size = " + allStaff.size());
        Collections.sort(allStaff, new Comparator<StaffMember>() {
            public int compare(StaffMember s1, StaffMember s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });

        filteredStaff = new ArrayList<>();
        filteredStaff.addAll(staffInDept);

        // create adapter, define list view, and set them
        adapter = new StaffListAdapter(this, R.layout.simple_one_text_line_item, filteredStaff, prevScroll);
        staffListView = findViewById(R.id.AllStaffListView);
        staffListView.setAdapter(adapter);

        // highlight previous thing
        staffListView.setSelection(prevScroll-6);

        AdapterView.OnItemClickListener onItemClickListener = (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                singleStaffAct(position);
            }
        });

        staffListView.setOnItemClickListener(onItemClickListener);

        // the list is taken care of, now lets do the search edit text
        searchET = findViewById(R.id.searchEditText);
        //searchET.setText(prevSearch);
        Log.i("MCC Staff Dir", "Size2 = " + allStaff.size());
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterForSearch(searchET.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }


    public void filterForSearch(String s) {
        prevSearch = s;
        String searchKey = s.toLowerCase();
        adapter.clear();
        Log.i("MCC Staff Dir", "Entered filterForSearch");
        if (!searchKey.isEmpty()) {
            Log.i("MCC Staff Dir", "Search key is NOT empty");
            Log.i("MCC Staff Dir", "Size6 = " + staffInDept.size());
            Log.i("MCC Staff Dir", "Size of allStaff is = " + staffInDept.size());
            for (StaffMember sm : staffInDept) {
                if (sm.contentsString().toLowerCase().contains(searchKey)) {
                    Log.i("MCC Staff Dir", sm.contentsString().toLowerCase());
                    adapter.add(sm);
                }
            }
        }
        else {
            for (StaffMember sm: allStaff) {
                adapter.add(sm);
            }
        }


        adapter.notifyDataSetChanged();


    }

    public void goHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        this.finish();
    }

    public void departmentClick(View v) {
        Intent departmentIntent = new Intent(this, Departments.class);

        startActivity(departmentIntent);
        this.finish();
    }

    public void allStaffClick(View v)
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    public void singleStaffAct(int pos)
    {
        StaffMember sm = filteredStaff.get(pos);

        Intent singleStaff = new Intent(this, SingleStaffAct.class);

        singleStaff.putExtra("sm", sm);
        singleStaff.putExtra("prevScroll", pos);
        singleStaff.putExtra("prevSearch", prevSearch);
        singleStaff.putExtra("sourceAct", "deptStaff");

        startActivity(singleStaff);

        finish();
    }







}
