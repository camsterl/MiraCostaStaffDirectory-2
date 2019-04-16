package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void allStaffClick(View v)
    {
        Intent allStaffIntent = new Intent(this, AllStaff.class);


        startActivity(allStaffIntent);
    }
}
