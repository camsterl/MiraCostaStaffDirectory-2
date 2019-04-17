package com.example.miracostastaffdirectory;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.miracostastaffdirectory.Model.JSONLoader;
import com.example.miracostastaffdirectory.Model.StaffMember;

import java.io.IOException;
import java.util.List;

public class AllStaff extends ListActivity {

    private ListView allStaffListView;
    List<StaffMember> allStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //  setContentView(R.layout.activity_all_staff);

        try {
            allStaff = JSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            Log.e("SD Music Events", "Error Loading JSON" + e.getMessage());
        }

        allStaffListView = findViewById(R.id.AllStaff);
        ArrayAdapter<StaffMember> staffAdapter = (new AllStaffListAdapter(this, R.layout.activity_all_staff_list_item, allStaff));
        allStaffListView.setAdapter(staffAdapter);


    }
}
