package com.ghazy.goldwing3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView tvName, tvEmail, tvBirthday, tvBio;
    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent i = this.getIntent();
        User user = (User) i.getSerializableExtra("user");

        tvName = findViewById(R.id.tvNameProfile);
        tvEmail = findViewById(R.id.tvEmailProfile);
        tvBirthday = findViewById(R.id.tvBirthdayProfile);
        tvBio = findViewById(R.id.tvBioProfile);
        ivPhoto = findViewById(R.id.ivPhotoProfile);

        tvName.setText(user.getFullName());
        tvEmail.setText(user.getEmail());
        tvBirthday.setText(user.getBirthday());
        //tvBio.setText(user.getBio());

    }


}