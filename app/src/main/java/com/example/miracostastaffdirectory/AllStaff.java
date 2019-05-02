package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.miracostastaffdirectory.Model.JSONLoader;
import com.example.miracostastaffdirectory.Model.StaffMember;

import java.io.IOException;
import java.util.ArrayList;


public class AllStaff extends AppCompatActivity {

    private ArrayList<StaffMember> allStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_staff);



        Intent fromMain = getIntent();

        allStaff = fromMain.getParcelableArrayListExtra("allStaff");

        // This will happen if we don't go to this activity from the Main Page
        //      So we have to load the JSON again (not sure about how to get around this)
        if (allStaff.isEmpty()) {
            try {
                allStaff = (ArrayList<StaffMember>) JSONLoader.loadJSONFromAsset(this);
            } catch (IOException e) {
                Log.e("MC Staff Dir", "Error Loading JSON" + e.getMessage());
            }
        }

        ListView allStaffListView = findViewById(R.id.AllStaffListView);


        if (allStaffListView ==null)
            System.out.println("This shit is null");

        ArrayAdapter<StaffMember> adapter = new AllStaffListAdapter(this, R.layout.simple_one_text_line_item, allStaff);
        allStaffListView.setAdapter(adapter);


    }


    public void goHome(View v) {

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        this.finish();

    }

    public void allStaffClick(View v)
    {
        this.recreate();
    }

    public void departmentClick(View v)
    {
        Intent departmentIntent = new Intent(this, Departments.class);


        startActivity(departmentIntent);
        this.finish();
    }
    public void singleStaffAct(View v)
    {
        Intent singleStaff = new Intent(this, SingleStaffAct.class);


        startActivity(singleStaff);
        this.finish();
    }
}
