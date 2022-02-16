package com.ghazy.goldwing3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SongDetails extends AppCompatActivity {
    private TextView tvSongName, tvArtistName, tvSongLength, tvAlbumName;
    private ImageView ivCover;

    /*
        private String address;
    private RestCat category;
    private String photo;
    private String phone;
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);

        connectComponents();
        Intent i = this.getIntent();

        Song rest = (Song) i.getSerializableExtra("song");

        tvSongName.setText(rest.getSongName());
        tvArtistName.setText(rest.getArtistName());
        tvSongLength.setText(rest.getSongLength());
        tvAlbumName.setText(rest.getAlbumName());
        Picasso.get().load(rest.getSongCover()).into(ivCover);
    }

    private void connectComponents() {
        tvSongName = findViewById(R.id.tvSongNameSongDetails);
        tvArtistName = findViewById(R.id.tvArtistNameSongDetails);
        tvSongLength = findViewById(R.id.tvSongLengthSongDetails);
        tvAlbumName = findViewById(R.id.tvAlbumNameSongDetails);
        ivCover = findViewById(R.id.ivPhotoSongDetails);
    }
}