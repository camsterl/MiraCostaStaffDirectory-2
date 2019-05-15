package com.example.miracostastaffdirectory;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.miracostastaffdirectory.Model.JSONLoader;
import com.example.miracostastaffdirectory.Model.StaffMember;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Departments extends AppCompatActivity {

    private ListView deptsListView;
    private ArrayList<StaffMember> allStaff;
    private ArrayList<String> departments;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);

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

        deptsListView = findViewById(R.id.departments);
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
        departments.add("ESL Department");
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


        adapter = (new AllDepartmentListAdapter(this, R.layout.simple_one_text_line_item, departments));
        deptsListView.setAdapter(adapter);

        deptsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToSingleDeptStaff(view, position);
            }
        });



    }

    public void goHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void goToDepartments(View v) {
        this.recreate();
    }

    public void goToAllStaff(View v) {
        Intent intent = new Intent(this, AllStaff.class);
        startActivity(intent);
        this.finish();
    }

    public void goToSingleDeptStaff(View v, int pos) {

        Intent intent = new Intent(this, SingleDeptStaff.class);

        String dept = departments.get(pos);

        intent.putExtra("department", dept);
        intent.putParcelableArrayListExtra("allStaff", allStaff);

        startActivity(intent);
        this.finish();

    }


}
