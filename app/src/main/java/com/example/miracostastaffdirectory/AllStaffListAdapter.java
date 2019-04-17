package com.example.miracostastaffdirectory;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import com.example.miracostastaffdirectory.Model.StaffMember;


public class AllStaffListAdapter extends ArrayAdapter<StaffMember> {


    private Context ctx;
    private int resID;
    private List<StaffMember> allStaff;




    public AllStaffListAdapter(Context context, int resource, List<StaffMember> staffList) {
        super(context, resource, staffList);

        ctx = context;
        resID = resource;
        allStaff = staffList;
    }



    // We'll use this to use a custom layout instead of the standard one
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // For each task in the list, inflate its view
        StaffMember focusedStaffMember = allStaff.get(position);





        // build a new inflater, and set it to this wonky function call that works with the context ( also cast it )
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View v = inflater.inflate(resID, null);

        if (position%2==0)
            v.setBackgroundColor(Color.CYAN);
        else
            v.setBackgroundColor(Color.LTGRAY);

        TextView staffName = v.findViewById(R.id.listNameTextView);
        staffName.setText(focusedStaffMember.getName());

        return v;
    }
}



