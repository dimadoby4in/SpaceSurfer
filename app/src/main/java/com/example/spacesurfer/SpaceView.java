package com.example.spacesurfer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class SpaceView extends SurfaceView implements Runnable{
    public static int maxX = 20; // размер по горизонтали
    public static int maxY = 28; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали

    private SoundPool sounds;
    private int sExplosion;

    private List<Meteor> meteors = new ArrayList<>(); // тут будут харанится астероиды
    private final int Meteors_INTERVAL = 50; // время через которое появляются астероиды (в итерациях)
    private int currentTime = 0;

    private boolean firstTime = true;
    public boolean gameRunning = true;
    private Ship ship;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;


    public SpaceView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        sounds = new SoundPool(10,AudioManager.STREAM_MUSIC,0);
        sExplosion = sounds.load(context, R.raw.explosion, 1);

        gameThread = new Thread(this);
        gameThread.start();

    }


    @Override
    public void run() {
        while (gameRunning) {
            canvas = null;
            
            update();
            draw();
            checkCollision();
            checkIfNewAsteroid();
            control();
        }
    }

    private void update() {
        if(!firstTime) {
            ship.update();
            for (Meteor meteor : meteors) {
                meteor.update();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {

            if(firstTime){ // инициализация при первом запуске
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;
                ship = new Ship(getContext());
            }

            canvas = surfaceHolder.lockCanvas(); // закрываем canvas

            canvas.drawColor(Color.BLACK);

            ship.drow(paint, canvas);

            for(Meteor meteor: meteors){
                meteor.drow(paint, canvas);
            }

            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    private void control() { // пауза на 20 миллисекунд
        try {
            gameThread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void checkCollision(){ //проверяем есть ли столкновения
        for (Meteor meteor : meteors) {
            if(meteor.isCollision(ship.x, ship.y, ship.size)){ // игрок проиграл
                sounds.play(sExplosion, 1.0f, 1.0f, 0, 0, 1.5f);//проигрыш звука
                gameRunning = false;


            }
        }
    }

    private void checkIfNewAsteroid(){ // каждые 50 операций добавляем новый астероид
        if(currentTime >= Meteors_INTERVAL){
            Meteor meteor = new Meteor(getContext());
            meteors.add(meteor);
            currentTime = 0;
        }else{
            currentTime ++;
        }
    }


}
