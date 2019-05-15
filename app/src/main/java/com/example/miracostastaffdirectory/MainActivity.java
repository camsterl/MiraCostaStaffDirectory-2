package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.miracostastaffdirectory.Model.JSONLoader;
import com.example.miracostastaffdirectory.Model.StaffMember;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class MainActivity extends AppCompatActivity {


    ArrayList<StaffMember> allStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            allStaff = (ArrayList<StaffMember>) JSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            Log.e("MC Staff Dir", "Error Loading JSON" + e.getMessage());
        }

    }
    public void allStaffClick(View v)
    {
        Intent allStaffIntent = new Intent(this, AllStaff.class);

        allStaffIntent.putParcelableArrayListExtra("allStaff", allStaff);

        startActivity(allStaffIntent);
        this.finish();
    }
    public void departmentClick(View v)
    {
        Intent departmentIntent = new Intent(this, Departments.class);


        departmentIntent.putParcelableArrayListExtra("allStaff", allStaff);


        startActivity(departmentIntent);
        this.finish();
    }
    public void goHomeClick(View v)
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void mapChange(View v) {
        Intent mapIntent = new Intent(this, MapActivity.class);


        startActivity(mapIntent);
        this.finish();
    }
}
