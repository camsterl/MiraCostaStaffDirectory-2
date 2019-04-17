package com.example.miracostastaffdirectory;

import android.content.Context;
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

        TextView staffName = v.findViewById(R.id.listNameTextView);
        staffName.setText(focusedStaffMember.getName());

        return v;
    }
}




/**
public class AllStaffListAdapter extends ArrayAdapter<StaffMember> {

    //declare member variables to store the params
    private Context mContext;
    private int mResourceId;
    private List<StaffMember> mAllStaff;

    //this constructor is being called  by MainActivity



    public AllStaffListAdapter(@NonNull Context context, int resource, @NonNull List<StaffMember> staffList) {
        super(context, resource, staffList);
        mContext = context;
        mResourceId = resource;
        mAllStaff = staffList;

    }


    //in order to bridge the view (music_event_list_item) with model (MusicEvent) we override;
    //crtl + o = override


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // inflate custom layout with data from List<MusicEvents>

        StaffMember focusedStaffMember = mAllStaff.get(position);

        // manually inflate custom layout
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //tell inflater to inflate music_event_list_item

        View customView = inflater.inflate(mResourceId, null);

        //fill parts of custom view

        TextView listNameTextView = customView.findViewById(R.id.listNameTextView);

        //put info in text views and image views
        listNameTextView.setText(focusedStaffMember.getName());

        return customView;
}
}
*/



