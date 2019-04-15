package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Departments extends AppCompatActivity {

    ListView deptsListView;
    ArrayList<String> departments;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);

        deptsListView = findViewById(R.id.departments);
        departments = new ArrayList<>();





    }

    public void goHome(View v) {
        this.finish();
    }

    public void goToDepartments(View v) {
        Intent intent= new Intent (this, Departments.class);
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    public void goToAllStaff(View v) {
        Intent intent = new Intent(this, AllStaff.class);
        startActivity(intent);
        this.finish();
    }


}
