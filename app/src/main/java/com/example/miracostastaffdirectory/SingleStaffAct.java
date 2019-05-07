package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.miracostastaffdirectory.Model.StaffMember;

public class SingleStaffAct extends AppCompatActivity {

    private StaffMember sm;
    private int prevScroll;
    private String sourceAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_staff);

        Intent from = getIntent();
        sm = from.getParcelableExtra("sm");
        prevScroll = from.getIntExtra("prevScroll", 0);
        sourceAct = from.getStringExtra("sourceAct");


        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView titleTextView= findViewById(R.id.titleTextView);
        TextView phoneTextView= findViewById(R.id.phoneTextView);
        TextView roomTextView= findViewById(R.id.roomTextView);
        TextView emailTextView= findViewById(R.id.emailTextView);

        nameTextView.setText(sm.getName());
        titleTextView.setText(sm.getTitle());
        phoneTextView.setText(sm.getFullPhoneNumber());
        roomTextView.setText(sm.getLocation());
        emailTextView.setText(sm.getEmail());

        TextView homeTV = findViewById(R.id.HomeTextView);
        TextView deptsTV = findViewById(R.id.DepartmentsTextView);
        TextView allStaffTV = findViewById(R.id.AllStaffTextView);

    }





    public void allStaffClick(View v)
    {
        Intent toAllStaff = new Intent(this, AllStaff.class);
        toAllStaff.putExtra("prevScroll", prevScroll);
        startActivity(toAllStaff);
        finish();
    }

    public void back(View v) {
        if (sourceAct.equals("allStaff"))
            allStaffClick(v);
        else if (sourceAct.equals("singleStaffDept")) {
            Intent goBack = new Intent(this, Departments.class);
            // TODO: finish this up. Go back to Departments.java to get the list again, then call on the function that goes to SingleDeptStaff.java using a String extra passed here
        }
    }

    public void departmentClick(View v)
    {
        Intent goToDeptsAct = new Intent (this, Departments.class);
        startActivity(goToDeptsAct);
        this.finish();
    }

    public void goHomeClick(View v)
    {
        Intent goHome=  new Intent (this, MainActivity.class);
        startActivity(goHome);
        this.finish();
    }
}
