package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.miracostastaffdirectory.Model.Location;
import com.example.miracostastaffdirectory.Model.LocationListAdapter;
import com.example.miracostastaffdirectory.Model.LocationsJSONLoader;
import com.example.miracostastaffdirectory.Model.StaffMember;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
//  d
    ArrayList<StaffMember> allStaff;
    ImageView mapImage;
    private GoogleMap map;
    private List<Location> allLocationsList;
    private ListView locationsListView;
    private LocationListAdapter locationListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // map stuff
        mapImage = findViewById(R.id.map);
        mapImage.setImageResource(R.drawable.oc_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // set list view
        locationsListView = findViewById(R.id.locationsListView);
        //TODO: idk where we get the values for allLocationsList  ----- Didn't we make a locations.json??
        try {
            allLocationsList = LocationsJSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            Log.e("Miracosta Staff Dir", "Couldn't find JSONFile with list of locations (locations.json)");
            e.printStackTrace();
        }


        // set adapter
        locationListAdapter = new LocationListAdapter(this, R.layout.simple_one_text_line_item, allLocationsList);
        locationsListView.setAdapter(locationListAdapter);

        locationsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location l = (Location)view.getTag();
                focusOn(l);
            }
        });


    }


    public void seChange(View v)
    {
        mapImage.setImageResource(R.drawable.se_map);
    }

    public void clcChange(View v)
    {
        mapImage.setImageResource(R.drawable.clc_map);
    }

    public void ocChange(View v)
    {
        mapImage.setVisibility(View.INVISIBLE);
    }

    public void focusOn(Location l) {
        LatLng pos = new LatLng(l.getLatitude(), l.getLongitude());

        //lets move camera to our position
        CameraPosition cameraPosition = new CameraPosition.Builder().target(pos).zoom(19f).build();

        //update postition of camera
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        //instruct map to move to this position
        map.moveCamera(cameraUpdate);
    }

    public void allStaffClick(View v)
    {
        Intent allStaffIntent = new Intent(this, AllStaff.class);

        allStaffIntent.putParcelableArrayListExtra("allStaff", allStaff);

        startActivity(allStaffIntent);
        this.finish();
    }
    public void departmentClick(View v)
    {
        Intent departmentIntent = new Intent(this, Departments.class);


        departmentIntent.putParcelableArrayListExtra("allStaff", allStaff);


        startActivity(departmentIntent);
        this.finish();
    }
    public void goHomeClick(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        LatLng myPostion = new LatLng(33.190802, -117.301805);

        //lets move camera to our position
        CameraPosition cameraPosition = new CameraPosition.Builder().target(myPostion).zoom(16.5f).build();

        //update postition of camera
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        //instruct map to move to this position
        map.moveCamera(cameraUpdate);

        //add all cofffee shops to map
        //loop through list and each location
        LatLng postion;
        for(Location location : allLocationsList)
        {
            postion = new LatLng(location.getLatitude(), location.getLongitude());
            map.addMarker(new MarkerOptions().position(postion).title(location.getName()));
        }
    }
}
