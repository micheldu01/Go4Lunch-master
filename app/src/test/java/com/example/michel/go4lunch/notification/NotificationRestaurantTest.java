package com.example.michel.go4lunch.notification;

import com.example.michel.go4lunch.models.User;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NotificationRestaurantTest {


    // ARRAY WORKMATES
    String workmates[][] = {{"id restaurant 1", "id workmate 1", "name 1"}, {"id restaurant 1", "id workmate 2", "name 2"}, {"id restaurant 2", "id workmate 3", "name 3"}};

    // ARRAY RESTARUANT
    String restaurant[][] = {{"restaurant 1", "address 1", "id restaurant 1"}, {"restaurant 2", "address 2", "id restaurant 2"}, {"restaurant 3", "address 3", "id restaurant 3"}};

    // ID RESTAURANT
    String id_restaurant;

    // NUM RESTAURANT
    int num;

    // NUM WORKMATES
    int num_workmates;

    // ARRAY LIST WORKMATES CHOICE RESTAURANT
    ArrayList<String> workmates_choice = new ArrayList<>();


    @Test
    public void test() {

        int a = 2 + 2;

        assertEquals(4, a);
    }


    // GET MY CHOICE OF RESTAURANT
    @Test
    public void test1() {

        // I M WORKMATE 1

        // GET ID RESTAURANT OF MY CHOICE
        id_restaurant = workmates[0][0];

        assertEquals("id restaurant 1", id_restaurant);

        // GET NAME

        // GET SIZE RESTAURANT
        int l = restaurant.length;

        // IMPLEMENT I
        int i = 0;

        // USE WHILE FOR GET ID RESTAURANT
        while (i < l){

            // IF ID RESTAURANT EQUAL RESTAURANT ID GET POSITION INTO NUM
            if (id_restaurant == restaurant[i][2]) {

                num = i;
            }

            i++;
        }

        assertEquals(0,num);
    }


    // GET ADDRESS AND NAME RESTAURANT
    @Test
    public void getNameAdresse() {

        // GET NAME AND ADDRESS
        num = 0;

        // NAME RESTAURANT
        String name_restaurant = restaurant[num][0];

        // ADDRESS
        String address = restaurant[num][1];

        // TEST NAME RESTAURANT
        assertEquals("restaurant 1",name_restaurant);

        // TEST ADDRESS
        assertEquals("address 1",address);
    }


    // GET NAME WORKMATES
    @Test
    public void getNameWorkmates() {

        // GET ID RESTAURANT
        id_restaurant = restaurant[0][2];

        // GET SIZE WORKMATES
        num_workmates = workmates.length;

        // IMPLEMENT INT I FOR WHILE
        int i = 0;

        // USE WHILE
        while (i<num_workmates){

            // IF ID RESTAURANT EQUAL CHOICE WORKMATES IMPLEMENT ARRAY LIST CHOICE OF WORKMATES
            if (id_restaurant == workmates[i][0]){

                // IMPLEMENT ARRAY LIST WITH NAME
                workmates_choice.add(workmates[i][2]);

            }

            i++;
        }

        // TEST IF NAME EXIST
        assertEquals("name 1",workmates_choice.get(0));

    }
}











































