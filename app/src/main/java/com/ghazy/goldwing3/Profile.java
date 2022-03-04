package com.ghazy.goldwing3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    TextView tvName, tvEmail, tvBirthday, tvBio;
    ImageView ivPhoto;
    FirebaseServices fbs;
    ArrayList<User> users;
    MyCallback myCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.tvNameProfile);
        tvEmail = findViewById(R.id.tvEmailProfile);
        tvBirthday = findViewById(R.id.tvBirthdayProfile);
        tvBio = findViewById(R.id.tvBioProfile);
        ivPhoto = findViewById(R.id.ivPhotoProfile);
        users = new ArrayList<User>();
        myCallback = new MyCallback() {
            @Override
            public void onCallback(List<Song> restsList) {
                
            }
        };


        /*tvName.setText(user.getFullName());
        tvEmail.setText(user.getEmail());
        tvBirthday.setText(user.getBirthday());
        tvBio.setText(user.getBio());
        fbs = FirebaseServices.getInstance();
        String email = fbs.getAuth().getCurrentUser().getEmail();
        fbs.getFire().collection("users").get().*/
    }

    private void readData() {
        try {
            fbs.getFire().collection("users")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    users.add(document.toObject(User.class));
                                }
                                MyCallback.onCallBackUsers(users);

                            } else {
                                Log.e("AllRestActivity: readData()", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "error reading!" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}