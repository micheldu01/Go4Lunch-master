package com.example.michel.go4lunch.fragments;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListViewFragmentTest {


    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    // TEST SPLIT
    @Test
    public void testSplit() {

        // TEST SPLIT
        String currentString = "michel=laurent";
        String[] separated = currentString.split("=");

        // SEPARATED
        final String username = separated[0];
        final String name = separated[1];

        // MAKE TEST
        assertEquals("michel",separated[0]);
        assertEquals("laurent",separated[1]);

    }


    // TEST TRY CATCH
    @Test
    public void testTryCatch() {

        // TRY TO ADD 2 INTO STRING
        String string = "michel";

        try {
            // ADD 2
            string = string+2;


        }catch (Exception e){
            // ELSE IMPLEMENT STRING
            string = "l'addition ne fonctionne pas!";
        }
        // ADD 2 INTO STRING IS POSSIBLE
        assertEquals("michel2", string);
    }

}