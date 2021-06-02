package com.example.covihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class TwitterResources extends AppCompatActivity {
    WebView tResources;
    ImageView twiBack;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        String m1="delhi(plasma%20OR%20tifin%20OR%20food%20OR%20oxygen)";
        String url="https://twitter.com/search?q=";
        String end="&src=typed_query&f=live";
        setContentView ( R.layout.activity_twitter_resources );
        twiBack=findViewById(R.id.twitterResBack);
        twiBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CovidResources.class));
                finish();
            }
        });
        tResources=findViewById ( R.id.tResWeb);
        String URL = getIntent().getExtras().getString("url");
        String WholeUrl=url+URL+end;
        WebSettings webSettings=tResources.getSettings ();
        webSettings.setJavaScriptEnabled ( true );
        tResources.setWebViewClient ( new Callback () );
        tResources.loadUrl ( WholeUrl);
    }
    private class Callback extends WebViewClient
    {
        @Override
        public boolean shouldOverrideKeyEvent ( WebView view , KeyEvent event ) {
            return false;
        }
    }

    @Override
    public void onBackPressed () {
        super.onBackPressed ( );
        startActivity ( new Intent ( getApplicationContext (),CovidResources.class ) );
        finish ();
    }
}