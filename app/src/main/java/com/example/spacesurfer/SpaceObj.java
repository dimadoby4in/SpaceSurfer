package com.example.spacesurfer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class SpaceObj {
    protected float x; 
    protected float y;
    protected float size; 
    protected float speed; 
    protected int bitmapId;
    protected Bitmap bitmap; 



    void init(Context context) { 
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int)(size * SpaceView.unitW), (int)(size * SpaceView.unitH), false);
        cBitmap.recycle();
    }

    void update(){ 
    }

    void drow(Paint paint, Canvas canvas){ 
        canvas.drawBitmap(bitmap, x*SpaceView.unitW, y*SpaceView.unitH, paint);
    }
}
