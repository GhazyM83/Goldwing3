package com.ghazy.goldwing3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class AddSong extends AppCompatActivity {

    private static final String TAG = "AddSong";
    private EditText etName, etArtist, etAlbum, etDate, etFile;
    private Spinner spCategory;
    private ImageView ivCover;
    private FirebaseServices fbs;
    private Uri filePath;
    private StorageReference storageReference;
    private StorageReference ref;
    private String imageUrl;

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
        etFile = findViewById(R.id.etFileAddSong);
        fbs = FirebaseServices.getInstance();
        spCategory.setAdapter(new ArrayAdapter<songCategory>(this, android.R.layout.simple_list_item_1, songCategory.values()));
        ref = fbs.getStorage().getReference();
        storageReference = fbs.getStorage().getReference();

    }

    public void add(View view) {
        String name, artist, album, filename, date, category, photo;
        name = etName.getText().toString();
        artist = etArtist.getText().toString();
        album = etAlbum.getText().toString();
        date = etDate.getText().toString();
        filename = etFile.getText().toString();
        category = spCategory.getSelectedItem().toString();
        if (ivCover.getDrawable() == null)
            photo = "no_image";
        else photo = ref.getPath();


        if (name.trim().isEmpty() || artist.trim().isEmpty() || album.trim().isEmpty() ||
                date.trim().isEmpty() || category.trim().isEmpty() || filename.trim().isEmpty()) {
            Toast.makeText(this, "Some fields are empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        Song song = new Song(name, artist, album, filename, songCategory.valueOf(category), date, photo);
        fbs.getFire().collection("songs")
                .add(song)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Song added successfully!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding song", e);
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

            ref = storageReference.child(UUID.randomUUID().toString() + "." + getFileExtension(filePath));

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath).addOnSuccessListener(
                    new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            // Image uploaded successfully
                            // Dismiss dialog
                            progressDialog.dismiss();
                            //imageUrl = ref.getDownloadUrl().getResult().getEncodedPath();
                            Toast.makeText(getApplicationContext(), "Image Uploaded!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Log.i("ggg", e.getMessage());
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
                        Picasso.get().load(filePath).into(ivCover);
                        uploadImage();
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
}