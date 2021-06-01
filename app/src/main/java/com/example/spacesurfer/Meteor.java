package com.example.spacesurfer;

import android.content.Context;

import java.util.Random;

public class Meteor extends SpaceObj {
    private int radius = 2; 
    private float minSpeed = (float) 0.1;
    private float maxSpeed = (float) 0.4; 
    public Meteor(Context context) {
        Random random = new Random();

        bitmapId = R.drawable.meteor;
        y=0;
        x = random.nextInt(SpaceView.maxX) - radius;
        size = radius*2;
        speed = minSpeed + (maxSpeed - minSpeed) * random.nextFloat();

        init(context);
    }
    @Override
    public void update() {
        y += speed;
    }
    public boolean isCollision(float shipX, float shipY, float shipSize) {
        return !(( ( x + size ) < shipX) || ( x > ( shipX + shipSize )) || (( y + size ) < shipY ) || ( y > ( shipY + shipSize )));
    }

}
