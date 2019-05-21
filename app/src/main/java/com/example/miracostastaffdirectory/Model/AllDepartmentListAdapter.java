package com.example.miracostastaffdirectory.Model;



import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.miracostastaffdirectory.R;

import java.util.List;


public class AllDepartmentListAdapter extends ArrayAdapter<String> {


        private Context ctx;
        private int resID;
        private List<String> departments;
        private int highlight=-1;


//test

    public AllDepartmentListAdapter(Context context, int resource, List<String> departmentList) {
            super(context, resource, departmentList);

            ctx = context;
            resID = resource;

            departments = departmentList;
        }

    public AllDepartmentListAdapter(Context context, int resource, List<String> departmentList, int viewToHighlight) {
            super(context, resource, departmentList);

            ctx = context;
            resID = resource;
            departments = departmentList;
            highlight = viewToHighlight;
        }



        // We'll use this to use a custom layout instead of the standard one
        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            // For each staff member in the list, inflate its view
            String focusedDepartment = departments.get(position);

            // build a new inflater, and set it to this wonky function call that works with the context ( also cast it )
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            @SuppressLint("ViewHolder")
            View v = inflater.inflate(resID, null);

            if (position%2==0)
                v.setBackgroundColor(Color.CYAN);
            else
                v.setBackgroundColor(Color.WHITE);

            if (position == highlight)
                v.setBackgroundColor(Color.MAGENTA);

            TextView departmentName = v.findViewById(R.id.SingleItemTextView);
            departmentName.setText(focusedDepartment);

            return v;
        }
    }



