package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Bundle;

public class Splash extends AppCompatActivity {
    private static int SPLASH_SCREEN=3000;
    //variables
    Animation topAnim;
    Animation bottomAnim;
    ImageView image;
    TextView growchat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //Animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        image=findViewById(R.id.image);
        growchat=findViewById(R.id.quantumhunt);


        image.setAnimation(topAnim);
        image.setAnimation(bottomAnim);

        growchat.setAnimation(topAnim);
        growchat.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this,SignupActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
}