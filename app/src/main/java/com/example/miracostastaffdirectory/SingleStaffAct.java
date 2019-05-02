package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.miracostastaffdirectory.Model.StaffMember;

public class SingleStaffAct extends AppCompatActivity {

    private StaffMember sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_staff);


        Intent fromMain = getIntent();
        sm = fromMain.getParcelableExtra("sm");



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



    }
}
