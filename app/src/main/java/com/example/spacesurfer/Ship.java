package com.example.spacesurfer;

import android.content.Context;

public class Ship extends SpaceObj {
    public Ship(Context context) {
        bitmapId = R.drawable.spaceship; // определяем начальные параметры
        size = 5;
        x = 7;
        y = SpaceView.maxY - size - 1;
        speed = (float) 0.24;

        init(context); // инициализируем корабль
    }

    @Override
    public void update() { // перемещаем корабль в зависимости от нажатой кнопки
        if(MainActivity.isLeftPressed && x >= 0){
            x -= speed;
        }
        if(MainActivity.isRightPressed && x <= SpaceView.maxX - 5){
            x += speed;
        }
    }

}
