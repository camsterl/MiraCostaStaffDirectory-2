package com.example.miracostastaffdirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.miracostastaffdirectory.Model.Location;
import com.example.miracostastaffdirectory.Model.LocationListAdapter;
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

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

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

        mapImage = findViewById(R.id.map);

        mapImage.setImageResource(R.drawable.oc_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);



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

        //add position to map
        map.addMarker(new MarkerOptions().position(myPostion).title("Current Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker)));


        //lets move camera to our position
        CameraPosition cameraPosition = new CameraPosition.Builder().target(myPostion).zoom(15.0f).build();

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
