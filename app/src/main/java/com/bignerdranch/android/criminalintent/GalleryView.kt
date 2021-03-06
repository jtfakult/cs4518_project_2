package com.bignerdranch.android.criminalintent

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import android.os.Environment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.face.FaceDetector
import kotlinx.android.synthetic.main.activity_gallery_view.*
import java.io.File


class GalleryView : ActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_view)
        val extras = this.intent.extras

        val recyclerView = imageGallery as RecyclerView
        recyclerView.setHasFixedSize(true)

        val layoutManager = GridLayoutManager(applicationContext, 4)
        recyclerView.layoutManager = layoutManager
        val createLists = prepareData(extras.getBoolean("face_dect_on"), extras.getStringArrayList("images"))

        val adapter = GalleryAdapter(applicationContext, createLists)
        recyclerView.adapter = adapter

        prepareData(extras.getBoolean("face_dect_on"), extras.getStringArrayList("images"))
    }

    private fun prepareData(face_dect_on: Boolean, images: ArrayList<String>): ArrayList<ImageTile>
    {
        var externalFilesDir : File = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        val theimage = ArrayList<ImageTile>()
        for (i in images.size-1 downTo 0) {
        //for (i in 0..images.size-1) {
            var filePath: String = File(externalFilesDir, images[i]).getPath()
            if (!(File(filePath)).exists())
            {
                continue;
            }
            val createList = ImageTile()
            createList.setImage_title(filePath)
            createList.setImage_ID(i+10000)

            val bitmap = PictureUtils.getScaledBitmap(
                    filePath, this)
            if (face_dect_on) {
                var bd: BitmapDrawable? = faceDect(bitmap, createList)
                if (bd == null)
                {
                    Log.e("Google Vision", "Google vision not working")
                }
                else {
                    createList.setImage_draw(bd)
                }
            } else {
                createList.setImage_draw(BitmapDrawable(resources, bitmap))
            }

            theimage.add(createList)
        }
        return theimage
    }

    fun faceDect(myBitmap: Bitmap, thisImage: ImageTile): BitmapDrawable? {
        val myRectPaint = Paint()
        myRectPaint.strokeWidth = 5f
        myRectPaint.color = Color.RED
        myRectPaint.style = Paint.Style.STROKE

        val tempBitmap = Bitmap.createBitmap(myBitmap.width, myBitmap.height, Bitmap.Config.RGB_565)
        val tempCanvas = Canvas(tempBitmap)
        tempCanvas.drawBitmap(myBitmap, 0f, 0f, null)

        val faceDetector = FaceDetector.Builder(this).setTrackingEnabled(false)
                .build()
        if (!faceDetector.isOperational) {
            //            new AlertDialog.Builder(v.getContext()).setMessage("Could not set up the face detector!").show();
            return null;
        }

        val frame = Frame.Builder().setBitmap(myBitmap).build()
        val faces = faceDetector.detect(frame)

        for (i in 0 until faces.size()) {
            val thisFace = faces.valueAt(i)
            val x1 = thisFace.position.x
            val y1 = thisFace.position.y
            val x2 = x1 + thisFace.width
            val y2 = y1 + thisFace.height
            tempCanvas.drawRoundRect(RectF(x1, y1, x2, y2), 2f, 2f, myRectPaint)
        }

        //thisImage.setImage_draw(BitmapDrawable(resources, tempBitmap))
        return BitmapDrawable(resources, tempBitmap);
    }
}
