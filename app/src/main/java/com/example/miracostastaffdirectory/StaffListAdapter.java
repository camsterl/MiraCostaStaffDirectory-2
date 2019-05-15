package com.example.miracostastaffdirectory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import com.example.miracostastaffdirectory.Model.StaffMember;


public class StaffListAdapter extends ArrayAdapter<StaffMember> {


    private Context ctx;
    private int resID;
    private List<StaffMember> allStaff;
    private int highlight=-1;




    public StaffListAdapter(Context context, int resource, List<StaffMember> staffList) {
        super(context, resource, staffList);

        ctx = context;
        resID = resource;
        allStaff = staffList;
    }

    public StaffListAdapter(Context context, int resource, List<StaffMember> staffList, int viewToHighlight) {
        super(context, resource, staffList);

        ctx = context;
        resID = resource;
        allStaff = staffList;
        highlight = viewToHighlight;
    }



    // We'll use this to use a custom layout instead of the standard one
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // For each staff member in the list, inflate its view
        StaffMember focusedStaffMember = allStaff.get(position);

        // build a new inflater, and set it to this wonky function call that works with the context ( also cast it )
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        @SuppressLint("ViewHolder")
        View v = inflater.inflate(resID, null);
       // String focusedStaffMember2 = allStaff.get(position).getName();

        if (position%2==0)
            v.setBackgroundColor(Color.CYAN);
        else
            v.setBackgroundColor(Color.WHITE);

        if (position == highlight) {

         //   Log.i("MCC Course test", "Here: " + allStaff.get(position).getName());
            v.setBackgroundColor(Color.MAGENTA);
        }

        TextView staffName = v.findViewById(R.id.SingleItemTextView);
        staffName.setText(focusedStaffMember.getName());

        return v;
    }
}


