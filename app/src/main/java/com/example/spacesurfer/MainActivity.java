package com.example.spacesurfer;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    public static boolean isLeftPressed = false; 
    public static boolean isRightPressed = false;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, MyService.class));

        SpaceView gameView = new SpaceView(this); 
        getWindow().setBackgroundDrawableResource(R.drawable.space);
        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout); 
        gameLayout.addView(gameView); 
        Button leftButton = (Button) findViewById(R.id.leftButton);
        Button rightButton = (Button) findViewById(R.id.rightButton);
        leftButton.setOnTouchListener(this);
        rightButton.setOnTouchListener(this);
    }




        public boolean onTouch(View button, MotionEvent motion) {
        switch(button.getId()) { 
            case R.id.leftButton:
                switch (motion.getAction()) { 
                    case MotionEvent.ACTION_DOWN:
                        isLeftPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isLeftPressed = false;
                        break;
                }
                break;
            case R.id.rightButton:
                switch (motion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isRightPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isRightPressed = false;
                        break;
                }
                break;
        }
        return true;
    }


        public void onBackPressed() {
        stopService(new Intent(this, MyService.class));
    }
}
