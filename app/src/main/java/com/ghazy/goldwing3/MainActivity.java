package com.ghazy.goldwing3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
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

    public void goToPlay(View view) {
        Intent i = new Intent(getApplicationContext(), SongDetails.class);
        startActivity(i);
    }
}