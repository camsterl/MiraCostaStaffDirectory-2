package com.example.miracostastaffdirectory.Model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.miracostastaffdirectory.R;

import java.util.ArrayList;
import java.util.List;

public class LocationListAdapter extends ArrayAdapter<Location> {

    private Context mContext;
    private List<Location> mLocationsList;
    private int mResourceId;



    /**
     * Creates a new <code>LocationsListAdapter</code> given a mContext, resource id and list of locations.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param locations The list of locations to display
     */
    public LocationListAdapter(Context c, int rId, List<Location> locations) {
        super(c, rId, locations);
        mContext = c;
        mResourceId = rId;
        mLocationsList = locations;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the Location selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final Location focusedLocation = mLocationsList.get(pos);


        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);



        TextView locationListNameTextView =
                view.findViewById(R.id.SingleItemTextView);

        locationListNameTextView.setText(focusedLocation.getName());


        view.setTag(focusedLocation);


        return view;
    }
}
