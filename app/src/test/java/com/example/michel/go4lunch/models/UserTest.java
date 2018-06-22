package com.example.michel.go4lunch.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.Assert.*;

public class UserTest {

    ArrayList<User> userArrayList = new ArrayList<>();

    @Test
    public void testCompareTo() {

        userArrayList.add(new User("michel","url_picture1"));
        userArrayList.add(new User("laurent","url_picture2"));

        assertEquals("laurent",userArrayList.get(1).getUsername());

    }

    @Test
    public void testCompareTo2() {

        userArrayList.add(new User("michel","url_picture1"));
        userArrayList.add(new User("laurent","url_picture2"));

        assertEquals("michel",userArrayList.get(0).getUsername());

    }


}