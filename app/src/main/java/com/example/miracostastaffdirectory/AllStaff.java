package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.miracostastaffdirectory.Model.JSONLoader;
import com.example.miracostastaffdirectory.Model.StaffMember;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class AllStaff extends AppCompatActivity {

    private ArrayList<StaffMember> allStaff;
    private ListView allStaffListView;
    private ArrayAdapter<StaffMember> adapter;
    int prevScroll=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_staff);

        Intent from = getIntent();

        allStaff = from.getParcelableArrayListExtra("allStaff");
        prevScroll = from.getIntExtra("prevScroll", -1);

        // This will happen if we don't go to this activity from the Main Page
        //      So we have to load the JSON again (not sure about how to get around this)
        if (allStaff == null) {
            try {
                allStaff = (ArrayList<StaffMember>) JSONLoader.loadJSONFromAsset(this);
            } catch (IOException e) {
                Log.e("MC Staff Dir", "Error Loading JSON" + e.getMessage());
            }
        }

        Collections.sort(allStaff, new Comparator<StaffMember>() {
            public int compare(StaffMember s1, StaffMember s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });

        allStaffListView = findViewById(R.id.AllStaffListView);

        if (allStaffListView ==null)
            System.out.println("This shit is null");



        adapter = new AllStaffListAdapter(this, R.layout.simple_one_text_line_item, allStaff, prevScroll);
        allStaffListView.setAdapter(adapter);

        allStaffListView.setSelection(prevScroll-6);

        AdapterView.OnItemClickListener onItemClickListener = (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                singleStaffAct(position);
            }
        });

        allStaffListView.setOnItemClickListener(onItemClickListener);

    }


    public void goHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        this.finish();
    }

    public void allStaffClick(View v)
    {
        Intent intent = getIntent();
        intent.putExtra("prevScroll", -1);
        finish();
        startActivity(intent);
    }

    public void departmentClick(View v)
    {
        Intent departmentIntent = new Intent(this, Departments.class);

        startActivity(departmentIntent);
        this.finish();
    }

    public void singleStaffAct(int pos)
    {
        StaffMember sm = allStaff.get(pos);

        Intent singleStaff = new Intent(this, SingleStaffAct.class);

        singleStaff.putExtra("sm", sm);
        singleStaff.putExtra("prevScroll", pos);
        singleStaff.putExtra("sourceAct", "allStaff");

        startActivity(singleStaff);

        finish();
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }
}
