package plin.net.br.plin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.Random;

public class splash extends Activity implements Runnable {

    private LinearLayout layout;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        // setar background dinamicamente
        choiseBackground();

        //esperar 3seg e ir para main
        handler.postDelayed(this,3000);

    }

    private void choiseBackground() {
        layout = (LinearLayout)findViewById(R.id.splash);

        if(Randomic(1,2)==1)
            layout.setBackgroundResource(R.drawable.splash01);
        else
            layout.setBackgroundResource(R.drawable.splash02);

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
