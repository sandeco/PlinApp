package plin.net.br.plin.activities;

import android.content.Context;

import plin.net.br.plin.R;
import plin.net.br.plin.util.App;

/**
 * Created by sandeco on 17/06/16.
 */
public class StringLoad {

    private static int min = 1;
    private static int max = 5;

    public static String getString(){

        Context c = App.getContext();
        int randomNum = min + (int)(Math.random() * max);
        String load = "";


        if(randomNum==1){
            load=c.getString(R.string.load01);
        }else if(randomNum==2){
            load=c.getString(R.string.load02);
        }else if(randomNum==3){
            load=c.getString(R.string.load03);
        }else if(randomNum==4){
            load=c.getString(R.string.load04);
        }else if(randomNum==5) {
            load=c.getString(R.string.load05);
        }

        return load;
    }
}
