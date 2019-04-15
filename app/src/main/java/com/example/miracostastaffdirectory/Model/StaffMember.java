package com.example.miracostastaffdirectory.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class StaffMember implements Parcelable {

    String name;
    String title;
    String phoneExt;
    String Location;
    String email;

    public StaffMember(String name, String title, String phoneExt, String location, String email) {
        this.name = name;
        this.title = title;
        this.phoneExt = phoneExt;
        Location = location;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneExt() {
        return phoneExt;
    }

    public void setPhoneExt(String phoneExt) {
        this.phoneExt = phoneExt;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "StaffMember{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", phoneExt='" + phoneExt + '\'' +
                ", Location='" + Location + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.title);
        dest.writeString(this.phoneExt);
        dest.writeString(this.Location);
        dest.writeString(this.email);
    }

    protected StaffMember(Parcel in) {
        this.name = in.readString();
        this.title = in.readString();
        this.phoneExt = in.readString();
        this.Location = in.readString();
        this.email = in.readString();
    }

    public static final Parcelable.Creator<StaffMember> CREATOR = new Parcelable.Creator<StaffMember>() {
        @Override
        public StaffMember createFromParcel(Parcel source) {
            return new StaffMember(source);
        }

        @Override
        public StaffMember[] newArray(int size) {
            return new StaffMember[size];
        }
    };
}
