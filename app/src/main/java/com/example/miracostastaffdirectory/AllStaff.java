package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.miracostastaffdirectory.Model.StaffMember;

import java.util.ArrayList;


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

        adapter = new AllStaffListAdapter(this, R.layout.simple_one_text_line_item, allStaff);
        allStaffListView.setAdapter(adapter);


    }
}
