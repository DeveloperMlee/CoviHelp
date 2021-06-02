package com.example.covihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.location.GnssMeasurementsEvent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Registration extends AppCompatActivity {
    WebView regWeb;
    private AdView mAdView;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_registration );
        regWeb=findViewById ( R.id.regWeb );
        WebSettings webSettings=regWeb.getSettings ();
        webSettings.setJavaScriptEnabled ( true );
        regWeb.setWebViewClient ( new Callback() );
        regWeb.loadUrl ( "https://selfregistration.cowin.gov.in/" );



        MobileAds.initialize(this, new OnInitializationCompleteListener () {
            @Override
            public void onInitializationComplete( InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adViewRegister);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
    private class Callback extends WebViewClient
    {
        @Override
        public boolean shouldOverrideKeyEvent ( WebView view , KeyEvent event ) {
            return false;
        }
    }
}