package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jfakult on 1/30/18.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
	private ArrayList<ImageTile> galleryList;
	private Context context;
	
	public GalleryAdapter(Context context, ArrayList<ImageTile> galleryList) {
		this.galleryList = galleryList;
		this.context = context;
	}
	
	@Override
	public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gallery_tile, viewGroup, false);
		return new ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(GalleryAdapter.ViewHolder viewHolder, int i) {
		((TextView) viewHolder.title).setText(galleryList.get(i).getImage_title());
		viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
		viewHolder.img.setImageDrawable(galleryList.get(i).getImage_draw());
	}
	
	@Override
	public int getItemCount() {
		return galleryList.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder{
		private TextView title;
		private ImageView img;
		public ViewHolder(View view) {
			super(view);
			
			title = (TextView)view.findViewById(R.id.title);
			img = (ImageView) view.findViewById(R.id.img);
		}
	}
}
