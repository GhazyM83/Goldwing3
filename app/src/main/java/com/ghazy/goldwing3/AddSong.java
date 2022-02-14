package com.ghazy.goldwing3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.UUID;

public class AddSong extends AppCompatActivity {

    private static final String TAG = "AddSong";
    private EditText etName, etArtist, etAlbum, etDate;
    private Spinner spCategory;
    private ImageView ivCover;
    private FirebaseServices fbs;
    private Uri filePath;
    private StorageReference storageReference;
    private String StorageCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        getSupportActionBar().hide();
        etName = findViewById(R.id.etNameAddSong);
        etArtist = findViewById(R.id.etArtistAddSong);
        etAlbum = findViewById(R.id.etAlbumAddSong);
        etDate = findViewById(R.id.etDateAddSong);
        spCategory = findViewById(R.id.spCategoryAddSong);
        ivCover = findViewById(R.id.ivCoverAddSong);
        fbs = FirebaseServices.getInstance();
        StorageCode = "images/" + UUID.randomUUID().toString();
        spCategory.setAdapter(new ArrayAdapter<songCategory>(this, android.R.layout.simple_list_item_1, songCategory.values()));
        storageReference = fbs.getStorage().getReference();
    }

    public void add(View view) {
        String name, artist, album, date, category, photo;
        name = etName.getText().toString();
        artist = etArtist.getText().toString();
        album = etAlbum.getText().toString();
        date = etDate.getText().toString();
        category = spCategory.getSelectedItem().toString();
        if (ivCover.getDrawable() == null)
            photo = "no_image";
        else photo = StorageCode;


        if (name.trim().isEmpty() || artist.trim().isEmpty() || album.trim().isEmpty() ||
                date.trim().isEmpty() || category.trim().isEmpty() || photo.trim().isEmpty()) {
            Toast.makeText(this, "Some fields are empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        Song song = new Song(name, artist, album, songCategory.valueOf(category), date, photo);
        fbs.getFire().collection("songs")
                .add(song)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Document added" + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref = storageReference.child(StorageCode);

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath).addOnSuccessListener(
                    new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(
                                UploadTask.TaskSnapshot taskSnapshot)
                        {

                            // Image uploaded successfully
                            // Dismiss dialog
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage("Uploaded " + (int)progress + "%");
                                }
                            });
        }
    }

    public void selectPhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),40);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 40) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                        filePath = data.getData();
                        Picasso.get().load(filePath).resize(50,0).into(ivCover);
                        uploadImage();
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}