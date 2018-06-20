package com.example.michel.go4lunch.chat;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChatHelper {

    private static final String COLLECTION_NAME = "chats";


    // METHOD COLLECTION REFERENCE
    public static CollectionReference getChatCollection(){

        // FIRESTORE GET COLLECTION NAME
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);

    }

}
