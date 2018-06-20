package com.example.michel.go4lunch.chat;


import android.app.DownloadManager;

import com.google.firebase.firestore.Query;

public class MessageHelper {

    // DECLARE STRING CONNECTION NAME
    private static final String COLLECTION_NAME = "message";


    // METHOD QUERY
    public static Query getAllMessageForChat(String chat){

        return ChatHelper.getChatCollection()
                .document(chat)
                .collection(COLLECTION_NAME)
                .orderBy("dateCreated")
                .limit(50);
    }

}
