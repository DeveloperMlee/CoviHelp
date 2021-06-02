package com.example.covihelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EnterActivity extends AppCompatActivity {
    ImageView backEnterRe;
    private EditText edtAddress,edtAttendee,edtPin,edtState,edtDes;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private DatePickerDialog datePickerDialog;
    private Button datePicker,timePicker;
    private Button btnSubmit;
    Animation animation;
    String date;
    String time;
    FirebaseFirestore db;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_enter );
        db=FirebaseFirestore.getInstance ();
        initEditText();


        backEnterRe.setOnClickListener(v -> {
            animation   = AnimationUtils.loadAnimation(this,R.anim.blink);
            v.startAnimation(animation);
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        });

        btnSubmit.setOnClickListener ( v -> {
            animation= AnimationUtils.loadAnimation(this,R.anim.blink);
            v.startAnimation(animation);
           checkCredential();
      } );

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

    }

    private void checkCredential () {

        String address=edtAddress.getText ().toString ().trim ();
        String attendee=edtAttendee.getText ().toString ().trim ();
        String pin=edtPin.getText ().toString ().trim ();
        String state=edtState.getText ().toString ().trim ();
        String des=edtDes.getText().toString().trim();


        if(TextUtils.isEmpty ( address )){
           edtAddress.setError ( "Enter address" );
        }
        else if(TextUtils.isEmpty ( attendee )){
            edtAttendee.setError ( "Enter Attendee Number" );
        }
        else if(attendee.length ()<10){
            edtAttendee.setError ( "Enter Valid Number" );
        }
       else if(TextUtils.isEmpty ( pin )){
            edtPin.setError ( "Enter Pin code" );
        }
       else if(TextUtils.isEmpty ( state )){
            edtState.setError ( "Enter State" );
        }
        else if(TextUtils.isEmpty ( des )){
            edtState.setError ( "Enter Description" );
        }
        else {
            insertData ();
        }
    }

    private void insertData () {

        Map<String,String> items=new HashMap <> (  );
        items.put ( "address",edtAddress.getText ().toString ().toUpperCase ().trim () );
        items.put ( "attendee",edtAttendee.getText ().toString ().toUpperCase ().trim () );
        items.put ( "pin",edtPin.getText ().toString ().toUpperCase ().trim () );
        items.put ( "state",edtState.getText ().toString ().toUpperCase ().trim () );
        items.put("des",edtDes.getText().toString().toUpperCase().trim());
        items.put("date",date);
        items.put("time",time);
        db.collection ("information" ).add ( items )
                .addOnCompleteListener ( new OnCompleteListener < DocumentReference > ( ) {
                    @Override
                    public void onComplete ( @NonNull Task < DocumentReference > task ) {
                        Toast.makeText ( EnterActivity.this , "Inserted Successfully" , Toast.LENGTH_LONG ).show ( );
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                } );

    }

    private void initEditText () {
        btnSubmit=findViewById ( R.id.btnSubmit );
        edtAddress=findViewById ( R.id.edtAddress );
        edtAttendee=findViewById ( R.id.edtAttendee );
        edtPin=findViewById ( R.id.edtPin );
        edtState=findViewById ( R.id.edtState );
        edtDes=findViewById(R.id.edtDes);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        backEnterRe=findViewById(R.id.backEnterResource);

    }






    public void openDatePicker(View view)
    {


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        date=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        datePicker.setText(date);


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void openTimePicker(View view) {

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute ) {

                        time=hourOfDay + ":" + minute;
                        timePicker.setText(time);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();

    }
}