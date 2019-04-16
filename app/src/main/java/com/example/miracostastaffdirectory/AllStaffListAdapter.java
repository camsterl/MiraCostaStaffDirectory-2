package com.example.miracostastaffdirectory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import com.example.miracostastaffdirectory.Model.StaffMember;


public class AllStaffListAdapter extends ArrayAdapter<StaffMember> {

    //declare member variables to store the params
    private Context mContext;
    private int mResourceId;
    private List<StaffMember> mAllStaff;

    //this constructor is being called  by MainActivity
    public AllStaffListAdapter(@NonNull Context context, int resource, @NonNull List<StaffMember> objects) {
        super(context, resource, objects);
        mContext = context;
        mResourceId = resource;
        mAllStaff = objects;

    }


    //in order to bridge the view (music_event_list_item) with model (MusicEvent) we override;
    //crtl 0 = override


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // inflate custom layout with data from List<MusicEvents>

        StaffMember focusedEvent = mAllStaff.get(position);

        // manually inflate custom layout
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //tell inflater to inflate music_event_list_item

        View customView = inflater.inflate(mResourceId, null);

        //fill parts of custom view

        TextView listNameTextView = customView.findViewById(R.id.listNameTextView);

        //put info in text views and image views
        listNameTextView.setText(focusedEvent.getName());

        return customView;
}
}
