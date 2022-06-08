package com.ghazy.goldwing3;
enum collectionType{
    single, album, playlist
}

public class Collection {

    private String collectionName;
    private String collectionArtist;
    private int numOfSongs;
    private collectionType collectionType;
    private String date;
    private String collectionCover;

    public Collection() {
    }

    public Collection(String collectionName, String collectionArtist, int numOfSongs, collectionType collectionType, String date, String collectionCover) {
        this.collectionName = collectionName;
        this.collectionArtist = collectionArtist;
        this.numOfSongs = numOfSongs;
        this.collectionType = collectionType;
        this.date = date;
        this.collectionCover = collectionCover;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionArtist() {
        return collectionArtist;
    }

    public void setCollectionArtist(String collectionArtist) {
        this.collectionArtist = collectionArtist;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public collectionType getType() {
        return collectionType;
    }

    public void setType(collectionType type) {
        collectionType = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCollectionCover() {
        return collectionCover;
    }

    public void setCollectionCover(String collectionCover) {
        this.collectionCover = collectionCover;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "collectionName='" + collectionName + '\'' +
                ", collectionArtist='" + collectionArtist + '\'' +
                ", numOfSongs=" + numOfSongs +
                ", Type=" + collectionType +
                ", date='" + date + '\'' +
                ", collectionCover='" + collectionCover + '\'' +
                '}';
    }
}
