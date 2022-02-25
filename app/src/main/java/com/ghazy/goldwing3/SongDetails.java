package com.ghazy.goldwing3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class SongDetails extends AppCompatActivity {
    private TextView tvSongName, tvArtistName, tvSongLength, tvAlbumName;
    private ImageView ivCover;
    private FirebaseServices fbs = FirebaseServices.getInstance();
    private StorageReference storageRef = fbs.getStorage().getReference();
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

        Song song = (Song) i.getSerializableExtra("song");

        tvSongName.setText(song.getSongName());
        tvArtistName.setText(song.getArtistName());
        tvSongLength.setText(song.getSongLength());
        tvAlbumName.setText(song.getAlbumName());
        Picasso.get().load(String.valueOf(storageRef.child(song.getSongCover()).getDownloadUrl())).into(ivCover);
    }

    private void connectComponents() {
        tvSongName = findViewById(R.id.tvSongNameSongDetails);
        tvArtistName = findViewById(R.id.tvArtistNameSongDetails);
        tvSongLength = findViewById(R.id.tvSongLengthSongDetails);
        tvAlbumName = findViewById(R.id.tvAlbumNameSongDetails);
        ivCover = findViewById(R.id.ivPhotoSongDetails);
    }
}