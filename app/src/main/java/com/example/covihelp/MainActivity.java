package com.example.covihelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private  Animation animation;
    private CardView enterInfo,searchInfo,coRe,remDis,updates,abUs,impLinks,vacBeds;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        init();

        enterInfo.setOnClickListener ( this );
        searchInfo.setOnClickListener ( this );
        coRe.setOnClickListener ( this );
        remDis.setOnClickListener ( this );
        updates.setOnClickListener ( this );
        abUs.setOnClickListener ( this );
        animation= AnimationUtils.loadAnimation(this,R.anim.fadein);
        vacBeds.setOnClickListener(this);
        impLinks.setOnClickListener(this);



        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("MyNotifications","MyNotifications",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);


        }

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg ="Failed";
                        }
                    }
                });

    }

    private void init() {
        enterInfo=findViewById ( R.id.enterInfo );
        searchInfo=findViewById ( R.id.searchInfo );
        coRe=findViewById ( R.id.covidResorces );
        remDis=findViewById ( R.id.remDis );
        updates=findViewById ( R.id.registration );
        abUs=findViewById ( R.id.aboutUs );
        impLinks=findViewById(R.id.impLink);
        vacBeds=findViewById(R.id.delhiVacant);
    }

    @Override
    public void onClick ( View v ) {
        switch (v.getId ()){

            case R.id.enterInfo:
                v.startAnimation(animation);
                startActivity ( new Intent ( getApplicationContext (),EnterActivity.class ) );
                break;
            case R.id.searchInfo:
                v.startAnimation(animation);
                startActivity ( new Intent ( getApplicationContext (),DetailsActivity.class ) );
                break;
            case R.id.covidResorces:
                v.startAnimation(animation);
                startActivity ( new Intent ( getApplicationContext (),CovidResources.class ) );
                break;
            case  R.id.remDis:
                v.startAnimation(animation);
                startActivity ( new Intent ( getApplicationContext (),SearchResources.class ) );
                break;
            case R.id.registration:
                v.startAnimation(animation);
                startActivity ( new Intent ( getApplicationContext (),Registration.class ) );
                break;
            case R.id.aboutUs:
                v.startAnimation(animation);
                startActivity ( new Intent ( getApplicationContext (),AboutUs.class ) );
                break;
            case R.id.delhiVacant:
                v.startAnimation(animation);
                startActivity ( new Intent ( getApplicationContext (),DelhiVecant.class ) );
                break;
            case R.id.impLink:
                v.startAnimation(animation);
                startActivity ( new Intent ( getApplicationContext (), HomeRemedy.class ) );
                break;
        }
    }
}