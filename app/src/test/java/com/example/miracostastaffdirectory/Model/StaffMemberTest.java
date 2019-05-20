package com.example.miracostastaffdirectory.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class StaffMemberTest {

    private StaffMember sm = new StaffMember("name", "title", "phone", "location", "email");

    @Test
    public void getName() {
        assertEquals("name", sm.getName());
    }

    @Test
    public void setName() {
        sm.setName("name2");
        assertEquals("name2", sm.getName());
    }

    @Test
    public void getTitle() {
        assertEquals("title", sm.getTitle());
    }

    @Test
    public void setTitle() {
        sm.setTitle("title2");
        assertEquals("title2", sm.getTitle());
    }

    @Test
    public void getPhoneExt() {
        assertEquals("phone", sm.getPhoneExt());
    }

    @Test
    public void getFullPhoneNumber() {
        assertEquals("+1(760)757-2121 ext." + sm.getPhoneExt(), sm.getFullPhoneNumber());
    }

    @Test
    public void setPhoneExt() {
        sm.setPhoneExt("111");
        assertEquals("111", sm.getPhoneExt());
    }

    @Test
    public void getLocation() {
        assertEquals("location", sm.getLocation());
    }

    @Test
    public void setLocation() {
        sm.setLocation("location2");
        assertEquals("location2", sm.getLocation());
    }

    @Test
    public void getEmail() {
        assertEquals("email", sm.getEmail());
    }

    @Test
    public void setEmail() {
        sm.setEmail("email2");
        assertEquals("email2", sm.getEmail());
    }

    @Test
    public void toStringTest() {
        String compare = "StaffMember{" +
                "name='" + sm.getName() + '\'' +
                ", title='" + sm.getTitle() + '\'' +
                ", phoneExt='" + sm.getPhoneExt() + '\'' +
                ", location='" + sm.getLocation() + '\'' +
                ", email='" + sm.getEmail() + '\'' +
                '}';
        assertEquals(compare, sm.toString());
    }

    @Test
    public void contentsString() {
        String compare = sm.getName() + " " + sm.getTitle() + " " + sm.getPhoneExt() + " " + sm.getLocation() + " " + sm.getEmail();
        assertEquals(compare, sm.contentsString());
    }

    @Test
    public void describeContents() {
    }

    @Test
    public void writeToParcel() {
    }
}