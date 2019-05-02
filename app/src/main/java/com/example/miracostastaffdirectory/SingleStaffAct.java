package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.miracostastaffdirectory.Model.JSONLoader;
import com.example.miracostastaffdirectory.Model.StaffMember;

import java.io.IOException;
import java.util.ArrayList;

public class SingleStaffAct extends AppCompatActivity {

    private ArrayList<StaffMember> allStaff;
    private StaffMember staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_staff);


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

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView departmentsTextView= findViewById(R.id.departmentsTextView);
        TextView phoneTextView= findViewById(R.id.phoneTextView);
        TextView roomTextView= findViewById(R.id.roomTextView);
        TextView emailTextView= findViewById(R.id.emailTextView);



    }
}
