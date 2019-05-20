package com.example.miracostastaffdirectory.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    private Location l = new Location(111, "name", 1.111111, 1.111111 );
    private double delta = .000000001;

    @Test
    public void getId() {
        assertEquals(111, l.getId());
    }

    @Test
    public void setId() {
        l.setId(222);
        assertEquals(222, l.getId());
    }

    @Test
    public void getName() {
        assertEquals("name", l.getName());
    }

    @Test
    public void setName() {
        l.setName("name2");
        assertEquals("name2", l.getName());
    }

    @Test
    public void getLatitude() {
        assertEquals(1.111111, l.getLatitude(), delta);
    }

    @Test
    public void setLatitude() {
        double newLat = l.getLatitude()+.01*4.11;
        l.setLatitude(newLat);
        assertEquals(newLat, l.getLatitude(), delta);
    }

    @Test
    public void getLongitude() {
        assertEquals(1.111111, l.getLongitude(), delta);
    }

    @Test
    public void setLongitude() {
        double newLong = l.getLatitude()+.0001*4.11;
        l.setLongitude(newLong);
        assertEquals(newLong, l.getLongitude(), delta);
    }

    @Test
    public void getFormattedLatLng() {
        String latLng = String.valueOf(Math.abs(l.getLatitude()));
        latLng += ((l.getLatitude() < 0.0) ? " S  " : " N  ");
        latLng += String.valueOf(Math.abs(l.getLongitude()));
        latLng += ((l.getLongitude() < 0.0) ? " W" : "E");
        assertEquals(latLng, l.getFormattedLatLng());

    }

    @Test
    public void toStringTest() {
        String test = "Location{" +
                "Id=" + l.getId() +
                ", Name='" + l.getName() + '\'' +
                ", Latitude=" + l.getLatitude() +
                ", Longitude=" + l.getLongitude() +
                '}';
        assertEquals(test, l.toString());
    }

    @Test
    public void describeContents() {
    }

    @Test
    public void writeToParcel() {
    }
}