package com.example.vijaykumar.trm_login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by vijay.kumar on 11/8/2017.
 */
public class Splash_screen extends Activity {
TextView txt_trm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        txt_trm=(TextView)findViewById(R.id.txt_trm);
        Animation translatebu= AnimationUtils.loadAnimation(this, R.anim.animation);

        txt_trm.startAnimation(translatebu);
        Thread thread= new Thread()
        {
            public void run()
            {
                try{
                    sleep(3*1000);
                    Intent i=new Intent(Splash_screen.this,Login_Activity.class);
                    startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
