package com.example.covihelp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.myViewHolder> {
    Context context;
    ArrayList <InfoModel> data;
    ArrayList <InfoModel> backup;

    public MyAdaptor ( Context context , ArrayList < InfoModel > data ) {
        this.context = context;
        this.data = data;
        backup=new ArrayList <> ( data );
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder ( @NonNull ViewGroup parent , int viewType ) {
        return new myViewHolder ( LayoutInflater.from(parent.getContext()).inflate(R.layout.details_information,parent,false));
    }

    @Override
    public void onBindViewHolder ( @NonNull myViewHolder holder , int position ) {
      holder.hName.setText ( data.get ( position ).getAddress () );
      holder.number.setText ( data.get ( position ).getAttendee ());
      holder.pinCode.setText ( data.get ( position ).getPin ());
        holder.state.setText ( data.get ( position ).getState());
        holder.des.setText ( data.get ( position ).getDes());
      holder.date.setText ( data.get ( position ).getDate ());
      holder.time.setText ( data.get ( position ).getTime());

      holder.btnCall.setOnClickListener ( new View.OnClickListener ( ) {
          @Override
          public void onClick ( View v ) {
              final Animation animation= AnimationUtils.loadAnimation(context,R.anim.blink);
              v.startAnimation(animation);
              String url = "https://api.whatsapp.com/send?phone="+91+" "+data.get(position).getAttendee();
              Intent i = new Intent(Intent.ACTION_VIEW);
              i.setData(Uri.parse(url));
              context.startActivity(i);


          }
      } );

    }

    @Override
    public int getItemCount () {
        return data.size ();
    }

    public void filterList(ArrayList<InfoModel> filteredList){
        data=filteredList;
        notifyDataSetChanged ();
    }



    class myViewHolder extends RecyclerView.ViewHolder {
    Button btnCall;
    TextView hName,number,date,state,pinCode,des,time;
    public myViewHolder ( @NonNull View itemView )
    {
        super ( itemView );
        btnCall=itemView.findViewById ( R.id.btnCall );
        hName=itemView.findViewById ( R.id.txtHospitalName );
        number=itemView.findViewById ( R.id.txtNumber );
        date=itemView.findViewById ( R.id.txtDate );
        state=itemView.findViewById ( R.id.txtState );
        pinCode=itemView.findViewById ( R.id.txtPincode );
        des=itemView.findViewById ( R.id.txtDes );
        time=itemView.findViewById ( R.id.txtTime );

    }
}



}
