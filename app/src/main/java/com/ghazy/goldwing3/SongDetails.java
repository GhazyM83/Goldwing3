package com.ghazy.goldwing3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class SongDetails extends AppCompatActivity {
    private TextView tvSongName, tvArtistName, tvAlbumName;
    private String source;
    private ImageView ivCover;
    private Button playBtn;
    private SeekBar positionBar;
    private TextView elapsedTimeLabel, remainingTimeLabel;
    private MediaPlayer mp;
    private int totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);
        getSupportActionBar().hide();
        Intent i = this.getIntent();
        Song song = (Song) i.getSerializableExtra("song");

        connectComponents();

        tvSongName.setText(song.getSongName());
        tvArtistName.setText(song.getArtistName());
        tvAlbumName.setText(song.getAlbumName());
        //source = getResources().getDrawable(R.raw.tun, );
        //Picasso.get().load(String.valueOf(storageRef.child(song.getSongCover()).getDownloadUrl())).into(ivCover);
        playBtn = findViewById(R.id.btPlaySongDetails);
        elapsedTimeLabel = findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = findViewById(R.id.remainingTimeLabel);

        mp = MediaPlayer.create(this, R.raw.lostcause);
        mp.setLooping(true);
        mp.seekTo(0);
        totalTime = mp.getDuration();

        positionBar = findViewById(R.id.positionBar);
        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            mp.seekTo(progress);
                            positionBar.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mp.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {}
                }
            }
        }).start();
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update positionBar.
            positionBar.setProgress(currentPosition);

            // Update Labels.
            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            String remainingTime = "- " + createTimeLabel(totalTime - currentPosition);
            remainingTimeLabel.setText(remainingTime);

            return true;
        }
    });

    public String createTimeLabel(int time) {
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    public void playBtnClick(View view) {

        if (!mp.isPlaying()) {
            // Stopping
            mp.start();

        } else {
            // Playing
            mp.pause();
        }
    }

    private void connectComponents() {
        tvSongName = findViewById(R.id.tvSongNameSongDetails);
        tvArtistName = findViewById(R.id.tvArtistNameSongDetails);
        tvAlbumName = findViewById(R.id.tvAlbumNameSongDetails);
        ivCover = findViewById(R.id.ivPhotoSongDetails);
    }
}