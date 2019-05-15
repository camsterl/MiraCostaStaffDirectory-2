package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.miracostastaffdirectory.Model.StaffMember;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {

    ArrayList<StaffMember> allStaff;
    ImageView mapImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

       mapImage.findViewById(R.id.map);

      mapImage.setImageResource(R.drawable.oc_map);



    }


    public void seChange(View v)
    {
        mapImage.setImageResource(R.drawable.se_map);
    }

    public void clcChange(View v)
    {
        mapImage.setImageResource(R.drawable.clc_map);
    }

    public void ocChange(View v)
    {
        mapImage.setImageResource(R.drawable.oc_map);
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
}
