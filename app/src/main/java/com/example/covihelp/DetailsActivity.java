package com.example.covihelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    RecyclerView detailRecycler;
    FirebaseFirestore db;
    ImageView backSearch;
    ArrayList<InfoModel>data;
    MyAdaptor adaptor;
    EditText search;
    private AdView mAdView;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_details );
        search=findViewById ( R.id.edtSearch );
        backSearch=findViewById(R.id.backFromSearchInfo);
        detailRecycler=findViewById ( R.id.detailRecycler );
        detailRecycler.setLayoutManager ( new LinearLayoutManager ( this ) );
        db=FirebaseFirestore.getInstance ();
        backSearch.setOnClickListener(v -> {
      Animation animation   = AnimationUtils.loadAnimation(this,R.anim.blink);
            v.startAnimation(animation);
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        });

        data=new ArrayList <> (  );
        adaptor=new MyAdaptor ( this,data );
        detailRecycler.setAdapter ( adaptor );

        search.addTextChangedListener ( new TextWatcher ( ) {
            @Override
            public void beforeTextChanged ( CharSequence s , int start , int count , int after ) {

            }

            @Override
            public void onTextChanged ( CharSequence s , int start , int before , int count ) {

            }

            @Override
            public void afterTextChanged ( Editable s ) {

                filter(s.toString ());
            }
        } );


       db.collection ( "information" ).get ()
               .addOnSuccessListener ( new OnSuccessListener < QuerySnapshot > ( ) {
                   @Override
                   public void onSuccess ( QuerySnapshot queryDocumentSnapshots ) {

                       List< DocumentSnapshot > list=queryDocumentSnapshots.getDocuments ();
                       for(DocumentSnapshot d:list){
                           InfoModel obj=d.toObject ( InfoModel.class );
                           data.add ( obj );
                       }
                       adaptor.notifyDataSetChanged ();
                   }
               } );


        MobileAds.initialize(this, new OnInitializationCompleteListener () {
            @Override
            public void onInitializationComplete( InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    private void filter(String text)
    {
        ArrayList<InfoModel> filteredList=new ArrayList <> (  );
        for(InfoModel infoModel:data)
        {
            if(infoModel.getPin ().toUpperCase ().contains ( text.toUpperCase () ))
            {
                filteredList.add ( infoModel );
            }
        }
        adaptor.filterList(filteredList);
    }

}