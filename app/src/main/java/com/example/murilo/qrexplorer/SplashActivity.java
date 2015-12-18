package com.example.murilo.qrexplorer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    /** Splash screen timer **/
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initTimer();
    }

    /**
     * Function to init the timer and define the function to call when it's done.
     */
    private void initTimer(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onTimerComplete();
            }
        }, SPLASH_TIME_OUT);
    }

    /**
     *   This method will be executed once the timer is over/completed
     */
    private void onTimerComplete(){
        openMainActivity();
        finish();
    }

    /**
     * Function to open the fragment activity.
     */
    private void openMainActivity(){
        //Switch to the Main Activity
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        // close this activity and make sure we can't go back to it:
        finish();
    }
}
