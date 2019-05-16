package com.example.miracostastaffdirectory.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {
    private long mId;
    private String mName;


    private double mLatitude;
    private double mLongitude;

    public Location(long id, String name, double latitude, double longitude) {
        mId = id;
        mName = name;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public Location(String name, double latitude, double longitude) {
        this(-1, name, latitude, longitude);
    }

    protected Location(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mLatitude = in.readDouble();
        mLongitude = in.readDouble();
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }



    public String getFormattedLatLng()
    {
        String latLng = String.valueOf(Math.abs(mLatitude));
        latLng += ((mLatitude < 0.0) ? " S  " : " N  ");
        latLng += String.valueOf(Math.abs(mLongitude));
        latLng += ((mLongitude < 0.0) ? " W" : "E");
        return latLng;
    }

    @Override
    public String toString() {
        return "Location{" +
                "Id=" + mId +
                ", Name='" + mName + '\'' +
                ", Latitude=" + mLatitude +
                ", Longitude=" + mLongitude +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(mId);
        parcel.writeString(mName);
        parcel.writeDouble(mLatitude);
        parcel.writeDouble(mLongitude);
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
