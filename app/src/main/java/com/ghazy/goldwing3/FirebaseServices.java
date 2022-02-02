package com.ghazy.goldwing3;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class FirebaseServices {
    private static FirebaseServices instance;
    private final FirebaseAuth auth;
    private final FirebaseFirestore fire;
    private final FirebaseStorage storage;

    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseFirestore getFire() {
        return fire;
    }

    public FirebaseStorage getStorage() {
        return storage;
    }

    public FirebaseServices()
    {
        auth = FirebaseAuth.getInstance();
        fire = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    public static FirebaseServices getInstance()
    {
        if (instance == null)
            instance = new FirebaseServices();
        return instance;
    }
}
