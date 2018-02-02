package com.bignerdranch.android.criminalintent;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String mSuspect;
    private ArrayList<String> mPhotos;
    private String mPath;

    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
        mPhotos = new ArrayList<String>();
    }
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public void setPath(String path){
        this.mPath = path;
    }

    public void addPath(String path){
        this.mPath += (path + ", ");
    }

    public String getPath(){
        return mPath;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
    
    public boolean addPhoto(String photoURI)
	{
		if (mPhotos.contains(photoURI))
		{
			return false;
		}
		mPhotos.add(photoURI);
		
		return true;
	}
	
	public ArrayList<String> getPhotos()
	{
		return mPhotos;
	}
}
