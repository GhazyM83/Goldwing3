package com.ghazy.goldwing3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    TextView tvName, tvEmail, tvPassword, tvBirthday, tvBio;
    ImageView ivPhoto;
    MyCallback myCallback;
    FirebaseServices fbs = FirebaseServices.getInstance();
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        tvName = findViewById(R.id.tvNameProfile);
        tvEmail = findViewById(R.id.tvEmailProfile);
        tvPassword = findViewById(R.id.tvPasswordProfile);
        tvBirthday = findViewById(R.id.tvBirthdayProfile);
        tvBio = findViewById(R.id.tvBioProfile);
        ivPhoto = findViewById(R.id.ivPhotoProfile);
        readData();
        myCallback = new MyCallback() {
            @Override
            public void onCallback(List<Song> attractionsList) {
            }
            @Override
            public void onCallBackUser(User user) {
                tvName.setText(user.getFullName());
                tvEmail.setText(user.getEmail());
                tvPassword.setText(user.getPassword());
                tvBirthday.setText(user.getBirthday());
                tvBio.setText(user.getBio());
            }
        };
    }

    private void readData() {
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
                                Log.e("ProfileActivity: readData()", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "error reading!" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void goToHome(View view) {
        Intent i = new Intent(getApplicationContext(), Home.class);
        startActivity(i);
    }
}