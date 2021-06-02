package com.example.covihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class AboutUs extends AppCompatActivity {
    ImageView gmail,facebook,twitter;
    private Animation animation;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_about_us );
        gmail=findViewById ( R.id.imgGmail);
        facebook=findViewById ( R.id.imgFace );
        twitter=findViewById ( R.id.imgTwi );
        animation= AnimationUtils.loadAnimation(this,R.anim.fadein);


        twitter.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                v.startAnimation(animation);
                Uri uri = Uri.parse("https://twitter.com/Manojlee2"); // missing 'http://' will cause crashed
                Intent intent = new Intent( Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        } );

        facebook.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                v.startAnimation(animation);
                Uri uri = Uri.parse("https://www.facebook.com/profile.php?id=100007339819389"); // missing 'http://' will cause crashed
                Intent intent = new Intent( Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        } );

        gmail.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                v.startAnimation(animation);
                Toast.makeText ( AboutUs.this , "m.lee00888@gmail.com" , Toast.LENGTH_LONG ).show ( );
            }
        } );

    }
}