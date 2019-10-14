package assistant.genuinecoder.s_assistant.main.components;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import assistant.genuinecoder.s_assistant.R;

public class About extends AppCompatActivity {

    Animation anim1, anim2;
    TextView fblink;
    TextView website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        moveViewToScreenCenter(findViewById(R.id.names));
        moveIcon(findViewById(R.id.imageViewAbout));

        fblink = (TextView) findViewById(R.id.fblink);
        website = (TextView) findViewById(R.id.websitelink);

        fblink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/nrithyopasana/";
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://banjah.it5m.com.au";
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }

        });

    }

    private void moveIcon(View view) {
        int originalPos[] = new int[2];
        view.getLocationOnScreen(originalPos);

        anim2 = new TranslateAnimation(0, 0, 0, originalPos[1] + 250);
        anim2.setDuration(2000);
        anim2.setFillAfter(true);
        view.startAnimation(anim2);
    }

    private void moveViewToScreenCenter(View view) {
        RelativeLayout root = findViewById(R.id.ctr);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int statusBarOffset = dm.heightPixels - root.getMeasuredHeight();

        int originalPos[] = new int[2];
        view.getLocationOnScreen(originalPos);

        int yDest = dm.heightPixels / 2 - (view.getMeasuredHeight() / 2) - statusBarOffset;

        anim1 = new TranslateAnimation(0, 0, 0, yDest - originalPos[1] + 500);
        anim1.setDuration(1500);
        anim1.setFillAfter(true);
        view.startAnimation(anim1);
    }
}
