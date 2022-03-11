package com.ghazy.goldwing3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private RecyclerView rvAllSongs;
    private TextView tvWelcome;
    RecyclerViewAdapter adapter;
    FirebaseServices fbs;
    ArrayList<Song> songs;
    User user;
    MyCallback myCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        tvWelcome = findViewById(R.id.tvWelcomeHome);
        fbs = FirebaseServices.getInstance();
        songs = new ArrayList<Song>();
        readData();
        myCallback = new MyCallback() {
            @Override
            public void onCallback(List<Song> songsList) {
                RecyclerView recyclerView = findViewById(R.id.rvFirstSongsHome);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new RecyclerViewAdapter(getApplicationContext(), songs);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCallBackUser(User user) {
                tvWelcome.setText("Welcome, " + user.getFullName() + "!");
            }
        };
    }

    private void readData() {
        //for song details
        try {
            fbs.getFire().collection("songs")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    songs.add(document.toObject(Song.class));
                                }
                                myCallback.onCallback(songs);

                            } else {
                                Log.e("HomeActivity: readData()", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "error reading!" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //for user details
        try {
            fbs.getFire().collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if (document.toObject(User.class).getEmail().equals(fbs.getAuth().getCurrentUser().getEmail()))
                                user = document.toObject(User.class);
                        }
                        myCallback.onCallBackUser(user);
                    } else {
                        Log.e("HomeActivity: readData()", "Error getting documents.", task.getException());
                    }
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "error reading!" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void goToProfile(View view) {
        Intent i = new Intent(getApplicationContext(), Profile.class);
        startActivity(i);
    }
}
