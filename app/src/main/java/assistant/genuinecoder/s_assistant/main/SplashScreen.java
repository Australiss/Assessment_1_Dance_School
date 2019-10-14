package assistant.genuinecoder.s_assistant.main;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import assistant.genuinecoder.s_assistant.R;
import assistant.genuinecoder.s_assistant.main.Login.Login;

public class SplashScreen extends AppCompatActivity {

    private ImageView logo;
    private static int splashTimeOut=5000;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.splash_screen);

         logo = (ImageView) findViewById(R.id.logo);

         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Intent i = new Intent (SplashScreen.this, Login.class);
                 startActivity(i);
                 finish();
             }
         },splashTimeOut);

         Animation myanim = AnimationUtils.loadAnimation(this,R.anim.splashanimation);
         logo.startAnimation(myanim);
     }

}
