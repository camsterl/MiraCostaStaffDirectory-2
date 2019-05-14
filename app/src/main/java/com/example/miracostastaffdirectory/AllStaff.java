package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.miracostastaffdirectory.Model.JSONLoader;
import com.example.miracostastaffdirectory.Model.StaffMember;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class AllStaff extends AppCompatActivity {

    private static ArrayList<StaffMember> allStaff;
    private static ArrayList<StaffMember> filteredStaff;
    private ListView allStaffListView;
    private static ArrayAdapter<StaffMember> adapter;
    private EditText searchET;
    int prevScroll=-1;
    String prevSearch="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_staff);

        Intent from = getIntent();
        allStaff = from.getParcelableArrayListExtra("allStaff");
        prevScroll = from.getIntExtra("prevScroll", -1);
        prevSearch = from.getStringExtra("prevSearch");
        if (prevSearch==null || prevSearch.equals(""))
            prevSearch="";

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
        filteredStaff = new ArrayList<>();

        for (StaffMember sm: allStaff) {
            filteredStaff.add(sm);
        }

        Log.i("MCC Staff Dir", "Size = " + allStaff.size());

        adapter = new AllStaffListAdapter(this, R.layout.simple_one_text_line_item, filteredStaff, prevScroll);
        allStaffListView.setAdapter(adapter);

        allStaffListView.setSelection(prevScroll-6);

        AdapterView.OnItemClickListener onItemClickListener = (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                singleStaffAct(position);
            }
        });

        allStaffListView.setOnItemClickListener(onItemClickListener);

        // the list is taken care of, now lets do the search edit text
        searchET = findViewById(R.id.searchEditText);
        //searchET.setText(prevSearch);
        Log.i("MCC Staff Dir", "Size2 = " + allStaff.size());
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterForSearch(searchET.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }


    public void filterForSearch(String s) {
        prevSearch = s;
        String searchKey = s.toLowerCase();
        adapter.clear();
        Log.i("MCC Staff Dir", "Entered filterForSearch");
        if (!searchKey.isEmpty()) {
            Log.i("MCC Staff Dir", "Search key is NOT empty");
            Log.i("MCC Staff Dir", "Size6 = " + allStaff.size());
            Log.i("MCC Staff Dir", "Size of allStaff is = " + allStaff.size());
            for (Object o : allStaff) {
                StaffMember sm = (StaffMember) o;
                if (sm.contentsString().toLowerCase().contains(searchKey)) {
                    Log.i("MCC Staff Dir", sm.contentsString().toLowerCase());
                    adapter.add(sm);
                }
            }
        }
        else {
            for (StaffMember sm: allStaff) {
                adapter.add(sm);
            }
        }


        adapter.notifyDataSetChanged();


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
        StaffMember sm = filteredStaff.get(pos);

        Intent singleStaff = new Intent(this, SingleStaffAct.class);

        singleStaff.putExtra("sm", sm);
        singleStaff.putExtra("prevScroll", pos);
        singleStaff.putExtra("prevSearch", prevSearch);
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
