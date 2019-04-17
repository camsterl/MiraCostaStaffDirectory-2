package com.example.miracostastaffdirectory;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.miracostastaffdirectory.Model.JSONLoader;
import com.example.miracostastaffdirectory.Model.StaffMember;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AllStaff extends AppCompatActivity {

    private ListView allStaffListView;
    private ArrayList<StaffMember> allStaff;
    private ArrayAdapter<StaffMember> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_staff);

        try {
            allStaff = (ArrayList<StaffMember>)JSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            Log.e("SD Music Events", "Error Loading JSON" + e.getMessage());
        }

        allStaffListView = findViewById(R.id.AllStaffListView);


        if (allStaffListView==null)
            System.out.println("This shit is null");

        adapter = new AllStaffListAdapter(this, R.layout.activity_all_staff_list_item, allStaff);
        allStaffListView.setAdapter(adapter);


    }
}
