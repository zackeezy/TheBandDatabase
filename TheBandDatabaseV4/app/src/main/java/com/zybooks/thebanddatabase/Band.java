package com.zybooks.thebanddatabase;

public class Band {
    private int mId;
    private String mName;
    private String mDescription;
    private String mGenre;
    private float mRating;

    public Band() {}

    public Band(int id, String name, String description, String genre) {
        mId = id;
        mName = name;
        mDescription = description;
        mGenre = genre;
        mRating = -1;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float mRating) {
        this.mRating = mRating;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getGenre(){
        return mGenre;
    }

    public void setGenre(String genre){
        this.mGenre = genre;
    }
}
