package com.ghazy.goldwing3;


import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Song> mData;
    private LayoutInflater mInflater;
    private Context context;
    private FirebaseServices fbs = FirebaseServices.getInstance();
    private StorageReference storageRef = fbs.getStorage().getReference();

    private final RecyclerViewAdapter.ItemClickListener mClickListener = new ItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Song song = mData.get(position);
            Intent i = new Intent(context, SongDetails.class);
            i.putExtra("song", song);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    };

    // data is passed into the constructor
    RecyclerViewAdapter(Context context, List<Song> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row1, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = mData.get(position);
        holder.tvName.setText(song.getSongName());
        holder.tvArtist.setText(song.getArtistName());
        Picasso.get().load(String.valueOf(storageRef.child(song.getSongCover()).getDownloadUrl())).into(holder.ivCover);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName, tvArtist;
        ImageView ivCover;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvSongNameRow);
            tvArtist = itemView.findViewById(R.id.tvArtistNameRow);
            ivCover = itemView.findViewById(R.id.ivCoverRow);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Song getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    /*
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    */


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
