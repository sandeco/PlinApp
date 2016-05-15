package plin.net.br.plin.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Random;

import plin.net.br.plin.R;
import plin.net.br.plin.service.FindNewPostTask;
import plin.net.br.plin.service.SyncData;

public class Splash extends Activity implements Runnable {

    private RelativeLayout screen;
    private LinearLayout middle;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        // setar background dinamicamente
        choiseBackground();

        //esperar 3seg e ir para main
        //handler.postDelayed(this,3000);

        FindNewPostTask task = new FindNewPostTask();
        task.start();


    }

    private void choiseBackground() {


    }


    @Override
    public void run() {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }


    public static int Randomic(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

}
