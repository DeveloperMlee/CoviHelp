package com.example.covihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

public class CovidResources extends AppCompatActivity {

    Switch oxy,icu,beds,ventilator,tests,fFlue,remdesivir,Favipiramir,tocilizumab,plasma,food;
    private Button btnResources;
    ImageView backSe;
    private EditText edtCovRes;
     String sOxy="";
     String sICu="";
     String sBeds="";
     String sVentilator="";
     String sTests="";
     String sFfLue="";
     String sRemdesivir="";
     String sFavipiramir="";
     String sTocilizu="";
     String sPlasma="";
     String sFood="";
     String tiffin="";

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_covid_resources );
        init();

        backSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        btnResources.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                checkCred();
            }
        } );


    }

    private void init() {
        oxy=findViewById ( R.id.tOxySwitch );
        icu=findViewById ( R.id.tIcuSwitch );
        beds=findViewById ( R.id.tBedsSwitch );
        ventilator=findViewById ( R.id.tVenSwitch );
        tests=findViewById ( R.id.tTestSwitch );
        fFlue=findViewById ( R.id.tFabiSwitch );
        remdesivir=findViewById ( R.id.tRemSwitch );
        Favipiramir=findViewById ( R.id.tFaPiraSwitch );
        tocilizumab=findViewById ( R.id.tTociSwitch );
        plasma=findViewById ( R.id.tPlasmaSwitch );
        food=findViewById ( R.id.tFoodSwitch );
        btnResources=findViewById ( R.id.btnResource );
        edtCovRes=findViewById ( R.id.edtCoviRes );
        backSe=findViewById(R.id.backSeTwi);
    }

    private void checkCred () {
        String reString=edtCovRes.getText ().toString ().trim ();
        if(TextUtils.isEmpty ( reString )){
            edtCovRes.setError ( "Please Enter City" );
        }
        else {
//            startActivity ( new Intent ( getApplicationContext (),TwitterResources.class ) );
            insertData();
        }
    }

    private void insertData () {
        if(oxy.isChecked ()){
            sOxy="OR%20oxygen%20";
        }
        if(icu.isChecked ()){
            sICu="OR%20icu%20";
        }
       if(beds.isChecked ()){
           sBeds="OR%20beds%20";
        }
       if(ventilator.isChecked ()){
          sVentilator="OR%20ventilator%20";
        }
       if(tests.isChecked ()){
            sTests="OR%20tests%20";
        }
       if(fFlue.isChecked ()){
            sFfLue="OR%20fabiflu%20";
        }
        if(remdesivir.isChecked ()){
            sRemdesivir="OR%20remdesivir%20";
        }
        if(Favipiramir.isChecked ()){
            sFavipiramir="%20favipiravir%20";
        }
        if(tocilizumab.isChecked ()){
            sTocilizu="OR%20tocilizumab%20";
        }
        if(plasma.isChecked ()){
            sPlasma="OR%20plasma%20";
        }
        if(food.isChecked ()){
            sFood="OR%20food%20";
            tiffin="OR%20tiffin%20";
        }
        if(sOxy.isEmpty ()&&sICu.isEmpty ()&&sBeds.isEmpty ()&&sVentilator.isEmpty ()&&sTests.isEmpty ()&&sFfLue.isEmpty ()&&sRemdesivir.isEmpty ()&&sFavipiramir.isEmpty ()
                &&sTocilizu.isEmpty ()&&sPlasma.isEmpty ()&&sFood.isEmpty ()&&tiffin.isEmpty ()){
            sOxy="covid help";
        }
        sendData();



    }

    private void sendData () {
//        String m1="delhi(plasma%20OR%20tifin%20OR%20food%20OR%20oxygen)";
        String city=edtCovRes.getText ().toString ().trim ()+" "+"verified";
        String exceptCity=sOxy+sICu+sBeds+sVentilator+sTests+sFfLue+sRemdesivir+sFavipiramir+sTocilizu+sPlasma+sFood+tiffin;
        String leftBr="(";
        String rightBr=")";
        String afterCity=leftBr+exceptCity+rightBr;
        String WholeString=city+afterCity;

        Intent intent = new Intent(this, TwitterResources.class);
        intent.putExtra("url", WholeString); // getText() SHOULD NOT be static!!!
        startActivity(intent);
        finish ();
    }
}
