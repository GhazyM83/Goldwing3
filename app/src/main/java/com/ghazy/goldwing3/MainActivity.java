package com.ghazy.goldwing3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView ivTest;
    private FirebaseServices fbs = FirebaseServices.getInstance();
    private StorageReference storageRef = fbs.getStorage().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ivTest = findViewById(R.id.ivTestMain);
        //Picasso.get().load(String.valueOf(storageRef.child("images/1d3faa13-6450-4b69-aa25-54c5bf07777a").getDownloadUrl())).into(ivTest);
    }

    public void goToLogin(View view) {
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }

    public void goToSignup(View view) {
        Intent i = new Intent(getApplicationContext(), Signup.class);
        startActivity(i);
    }

    public void goToAddSong(View view) {
        Intent i = new Intent(getApplicationContext(), AddSong.class);
        startActivity(i);
    }

    public void goToHome(View view) {
        Intent i = new Intent(getApplicationContext(), Home.class);
        startActivity(i);
    }
}