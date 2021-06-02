package com.example.covihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    private ImageView iconImage;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash );
        iconImage=findViewById(R.id.icon_image);

        // linearLayout.animate ().alpha ( 0 ).setDuration ( 1 );
        TranslateAnimation animation=new TranslateAnimation (0,0,0,-1000  );
        animation.setDuration ( 4000 );
        animation.setFillAfter ( false );
        animation.setAnimationListener ( new MyAnimatioinListener () );
        iconImage.setAnimation ( animation );
    }
    

    private class MyAnimatioinListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

            iconImage.clearAnimation ();
            iconImage.setVisibility ( View.INVISIBLE );
            startActivity(new Intent (getApplicationContext(),MainActivity.class));
            finish ();

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}