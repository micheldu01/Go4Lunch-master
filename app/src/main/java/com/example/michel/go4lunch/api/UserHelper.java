package com.example.michel.go4lunch.api;

import com.example.michel.go4lunch.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserHelper {


    private static String COLLECTION_NAME = "users";


    // COLLECTION REFERENCE
    public static CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // CREATE
    public static Task<Void> createUser(String uid, String username, String email, String urlPicture){

        User userToCreate = new User(uid, username, email, urlPicture);

        return UserHelper.getUsersCollection().document(uid).set(userToCreate);
    }

    // GET
    public static Task<DocumentSnapshot> getUser(String uid){
        return UserHelper.getUsersCollection().document(uid).get();
    }

    // UPDATE USERNAME
    public static Task<Void> updateUsername(String username, String uid){
        return UserHelper.getUsersCollection().document(uid).update("username", username);
    }

    // UPDATE EMAIL
    public static Task<Void> updateEmail(String email, String uid){
        return UserHelper.getUsersCollection().document(uid).update("email", email);
    }

    // UPDATE PICTURE
    public static Task<Void> updatePicture(String urlPicture, String uid){
        return UserHelper.getUsersCollection().document(uid).update("urlPicture", urlPicture);
    }

    // DELETE
    public static Task<Void> deleteUser(String uid){
        return UserHelper.getUsersCollection().document(uid).delete();
    }

}























