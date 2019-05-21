package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.miracostastaffdirectory.Model.AllDepartmentListAdapter;
import com.example.miracostastaffdirectory.Model.JSONLoader;
import com.example.miracostastaffdirectory.Model.StaffMember;

import java.io.IOException;
import java.util.ArrayList;

public class DepartmentsActivity extends AppCompatActivity {

    private ListView deptsListView;
    private ArrayList<StaffMember> allStaff;
    private ArrayList<String> departments;
    private ArrayList<String> filteredDepts;
    private ArrayAdapter<String> adapter;
    private EditText searchET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);

        Intent fromMain = getIntent();

        allStaff = fromMain.getParcelableArrayListExtra("allStaff");

        // This will happen if we don't go to this activity from the Main Page
        //      So we have to load the JSON again (not sure about how to get around this)
        if (allStaff == null) {
            try {
                allStaff = (ArrayList<StaffMember>) JSONLoader.loadJSONFromAsset(this);
            } catch (IOException e) {
                Log.e("MCC Staff Dir", "Error Loading JSON" + e.getMessage());
            }
        }

        deptsListView = findViewById(R.id.GeneralListView);
        departments = new ArrayList<>();

        // I'm hoping this was necessary
        departments.add("Administration Of Justice Department");
        departments.add("Art Department");
        departments.add("Automotive Technology Department");
        departments.add("Biological Science Department");
        departments.add("Biotechnology Department");
        departments.add("Business Department");
        departments.add("Career Department");
        departments.add("Chemistry Department");
        departments.add("Child Development Department");
        departments.add("Communication Studies Department");
        departments.add("Counseling Department");
        departments.add("Computer Science Department");
        departments.add("CSIT Department");
        departments.add("Dance Department");
        departments.add("Design Department");
        departments.add("English Department");
        departments.add("ESLD Department");
        departments.add("History Department");
        departments.add("Horticulture Department");
        departments.add("IMTD Department");
        departments.add("International Languages Department");
        departments.add("Kinesiology Department");
        departments.add("Library Science Department");
        departments.add("Math Department");
        departments.add("Music Department");
        departments.add("Nursing Department");
        departments.add("Philosophy And Religious Department");
        departments.add("Physical Science Department");
        departments.add("Psychology Department");
        departments.add("Sociology Department");
        departments.add("Theatre And Film Department");

        filteredDepts = new ArrayList<>();
        filteredDepts.addAll(departments);

        adapter = (new AllDepartmentListAdapter(this, R.layout.simple_one_text_line_item, filteredDepts));
        deptsListView.setAdapter(adapter);

        deptsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToSingleDeptStaff(view, position);
            }
        });

        searchET = findViewById(R.id.searchEditText);
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterForSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }

    public void filterForSearch(String S) {
        String searchKey = S.toLowerCase();
        adapter.clear();

        if (!searchKey.isEmpty()) {

            for (String s: departments) {
                if (s.toLowerCase().contains(searchKey)) {
                    adapter.add(s);
                }
            }
        }
        else {
            for (String s: departments) {
                adapter.add(s);
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
        this.recreate();
    }

    public void allStaffClick(View v) {
        Intent intent = new Intent(this, AllStaffActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void goToSingleDeptStaff(View v, int pos) {

        Intent intent = new Intent(this, SingleDeptStaffActivity.class);

        String dept = filteredDepts.get(pos);

        intent.putExtra("deptToLoad", dept);
        intent.putParcelableArrayListExtra("allStaff", allStaff);

        startActivity(intent);
        this.finish();

    }


}
