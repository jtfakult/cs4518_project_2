package com.bignerdranch.android.criminalintent

import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_gallery_view.*


class GalleryView : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_view)

        val recyclerView = imageGallery as RecyclerView
        recyclerView.setHasFixedSize(true)

        val layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.layoutManager = layoutManager
        val createLists = prepareData()
        val adapter = GalleryAdapter(applicationContext, createLists)
        recyclerView.adapter = adapter

        loadPhotos()
    }

    fun loadPhotos()
    {

    }

    private fun prepareData(): ArrayList<ImageTile>
    {
        val theimage = ArrayList<ImageTile>()
        for (i in 0..10) {
            val createList = ImageTile()
            createList.setImage_title("hi")
            createList.setImage_ID(i + 10000)
            theimage.add(createList)
        }
        return theimage
    }

}
