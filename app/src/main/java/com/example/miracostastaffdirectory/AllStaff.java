package com.example.miracostastaffdirectory;

import android.app.ListActivity;
import android.content.Intent;
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

    private ArrayList<StaffMember> allStaff;
    private ListView allStaffListView;
    private ArrayAdapter<StaffMember> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_staff);



        Intent fromMain = getIntent();
        allStaff = fromMain.getParcelableArrayListExtra("allStaff");
        allStaffListView = findViewById(R.id.AllStaffListView);


        if (allStaffListView==null)
            System.out.println("This shit is null");

        adapter = new AllStaffListAdapter(this, R.layout.activity_all_staff_list_item, allStaff);
        allStaffListView.setAdapter(adapter);


    }
}
