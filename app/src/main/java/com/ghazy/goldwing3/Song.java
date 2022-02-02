package com.ghazy.goldwing3;

import androidx.annotation.NonNull;

enum songCategory{
    pop, rnb, rock, country, jazz, soul
}

public class Song {
    private String songName;
    private String songLength;
    private String artistName;
    private String albumName;
    private songCategory category;
    private String credits;
    private String releaseDate;
    private String lyrics;
    private int streams;
    private String songCover;

    public Song() {
    }

    public Song(String songName,String artistName, String albumName, songCategory category, String releaseDate, String songCover)
    {
        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.category = category;
        this.releaseDate = releaseDate;
        this.songCover = songCover;
    }

    public Song(String songName,String artistName, String albumName, songCategory category, String releaseDate) {
        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.category = category;
        this.releaseDate = releaseDate;
    }

    public Song(String songName, String songLength, String artistName, String albumName, songCategory category, String credits, String releaseDate, String lyrics, int streams, String songCover) {
        this.songName = songName;
        this.songLength = songLength;
        this.artistName = artistName;
        this.albumName = albumName;
        this.category = category;
        this.credits = credits;
        this.releaseDate = releaseDate;
        this.lyrics = lyrics;
        this.streams = streams;
        this.songCover = songCover;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongLength() {
        return songLength;
    }

    public void setSongLength(String songLength) {
        this.songLength = songLength;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public songCategory getCategory() {
        return category;
    }

    public void setCategory(songCategory category) {
        this.category = category;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public int getStreams() {
        return streams;
    }

    public void setStreams(int streams) {
        this.streams = streams;
    }

    public String getSongCover() {
        return songCover;
    }

    public void setSongCover(String songCover) {
        this.songCover = songCover;
    }

    @NonNull
    @Override
    public String toString() {
        return "Song{" +
                "songName='" + songName + '\'' +
                ", songLength='" + songLength + '\'' +
                ", artistName='" + artistName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", category=" + category +
                ", credits='" + credits + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", streams=" + streams +
                ", songCover='" + songCover + '\'' +
                '}';
    }
}
