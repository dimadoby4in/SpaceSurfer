package com.example.spacesurfer;

import android.content.Context;

public class Ship extends SpaceObj {
    public Ship(Context context) {
        bitmapId = R.drawable.spaceship;
        size = 5;
        x = 7;
        y = SpaceView.maxY - size - 1;
        speed = (float) 0.3;
        init(context);
    }

    @Override
    public void update() { 
        if(MainActivity.isLeftPressed && x >= 0){
            x -= speed;
        }
        if(MainActivity.isRightPressed && x <= SpaceView.maxX - 5){
            x += speed;
        }
    }

}
