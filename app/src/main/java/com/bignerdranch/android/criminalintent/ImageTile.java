package com.bignerdranch.android.criminalintent;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by jfakult on 1/30/18.
 */

public class ImageTile {
	
	private String image_title;
	private Integer image_id;
	private Drawable image_draw;

	public String getImage_title() {
		return image_title;
	}
	
	public void setImage_title(String android_version_name) {
		this.image_title = android_version_name;
	}
	
	public Integer getImage_ID() {
		return image_id;
	}
	
	public void setImage_ID(Integer android_image_url) {
		this.image_id = android_image_url;
	}


	public Drawable getImage_draw() {
		return image_draw;
	}

	public void setImage_draw(Drawable image_draw) {
		this.image_draw = image_draw;
	}
}
