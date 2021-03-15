package com.example.jamsecure;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class NewJam extends AppCompatActivity {
    ProgressBar progressBar;
    EditText etDate,tim;
    TextView tjn,tjrr,tjl;
    Spinner dur;
    int t1hr,t1min, iq=0;
    String jdate, jtime;
    FirebaseUser us;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference fmbase;
    //DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_jam);
        progressBar= findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);
        tim=findViewById(R.id.tim);
        tjn=findViewById(R.id.tjn);
        tjl=findViewById(R.id.tjl);
        tjrr=findViewById(R.id.tjrr);
        etDate=findViewById(R.id.et_date);
        Button book=findViewById(R.id.book);
        dur=findViewById(R.id.dur) ;
        Intent intent = getIntent();
        String location = intent.getStringExtra("loc");
        String jname = intent.getStringExtra("name");
        final String jemail = intent.getStringExtra("email");
        String jrate = intent.getStringExtra("rate");
        Calendar calendar = Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        etDate.setFocusable(false);
        etDate.setKeyListener(null);
        tim.setKeyListener(null);

       /* setListener = new DatePickerDialog.OnDateSetListener(){
            public void onDateSet(DatePicker view, int year, int month, int dayofMonth)
                month =month+1;
            String date=day+"/"+month+"/"+day;

        }*/
        ArrayAdapter<String> adapter1= new ArrayAdapter<>(
                this, R.layout.spin_style,
                getResources().getStringArray(R.array.sp_list1));
        adapter1.setDropDownViewResource(R.layout.drop_down);
        dur.setAdapter(adapter1);
        tjn.setText(jname);
        tjl.setText(location);
        tjrr.setText(jrate);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        NewJam.this, new DatePickerDialog.OnDateSetListener(){
                            public void onDateSet(DatePicker view, int year, int month,int day){
                                month=month+1;
                                String date=day+"/"+month+"/"+year;
                                jdate=year+""+month+""+day;
                                etDate.setText(date);
                            }
                },year,month,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        tim.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        NewJam.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hr, int min) {
                        t1hr = hr;
                        t1min = min;
                        jtime=hr+""+min;
                        String aiq = String.valueOf(t1min);
                        if(aiq.length()>1){iq = 1;}
                        Log.d("Timeval", String.valueOf(t1min));
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0, 0, 0, t1hr, t1min);
                        tim.setText(DateFormat.format("hh:mm aa", calendar));
                    }
                },12,0, false);
                    timePickerDialog.updateTime(t1hr,t1min);
                    timePickerDialog.show();

            }
        });
    book.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int[] flag = {5};
            us = auth.getCurrentUser();
            final String[] uemail = {us.getEmail()};
            String date = etDate.getText().toString();
            String time = tim.getText().toString();
            final String start = jdate+jtime;
            final int istart = Integer.parseInt(start);
            final String duration=dur.getSelectedItem().toString().trim();
            int a = Integer.parseInt(duration);
            final int e;
            if(iq==1)
            { e= Integer.parseInt(start)+(a*100);}
            else{ e= Integer.parseInt(start)+(a*10);}
            String end = Integer.toString(e);

            int pos=dur.getSelectedItemPosition();
            if(pos==0){
                Toast toast = Toast.makeText(NewJam.this, "Please Select a valid duration", Toast.LENGTH_LONG);
                toast.show();
                return;
            }
            if(date.isEmpty()){
                Toast toast = Toast.makeText(NewJam.this, "Please Select a valid date", Toast.LENGTH_LONG);
                toast.show();
                return;
            }
            if(time.isEmpty()){
                Toast toast = Toast.makeText(NewJam.this, "Please Select a valid time", Toast.LENGTH_LONG);
                toast.show();
                return;
            }
            final Slots slot = new Slots(jemail, uemail[0],date,time,duration,start,end);
            fmbase = FirebaseDatabase.getInstance().getReference().child("Slots"); //orderByChild("JamRoom_Email").equalTo(jemail);
            fmbase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot datas: snapshot.getChildren()){
                        String tsstart=datas.child("Start_at").getValue().toString();
                        String tsend=datas.child("End_at").getValue().toString();
                        String tjamemail= datas.child("JamRoom_Email").getValue().toString();
                        int tstart = Integer.parseInt(tsstart);
                        int tend = Integer.parseInt(tsend);
                        Log.d("IMHERE","INSIDE FOR");
                        if(tjamemail.equals(jemail)){
                        if((istart >= tstart) && (istart<tend)){

                            Toast.makeText(NewJam.this, "Selected Slot Not Available, Please Select A Different Slot!", Toast.LENGTH_SHORT).show();

                            flag[0] =1;
                            break;
                        }
                        if(e> tstart && e<=tend){
                            Toast.makeText(NewJam.this, "Selected Slot Not Available, Please Select A Different Slot!", Toast.LENGTH_SHORT).show();
                            flag[0] =1;
                            break;
                        }

                    }
                    }
                    if(flag[0]!=1)
                    { //Toast.makeText(NewJam.this,"flag value is "+flag[0],Toast.LENGTH_SHORT).show();
                        bookjamroom(slot);}
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Log.d("OUTLOOP","OUTDSIDE FOR");
            Log.d("flagcheck", String.valueOf(flag[0]));

        }


    });
    }
    private void bookjamroom(Slots slot) {
        progressBar.setVisibility(View.VISIBLE);
        FirebaseDatabase.getInstance().getReference("Slots")
                .child(UUID.randomUUID().toString())
                .setValue(slot).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(NewJam.this,"Booking Successful" ,Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    Intent i = new Intent(NewJam.this,Bookjamroom.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //so that you don't come back to login page again
                    startActivity(i);
                }
                else{
                    Toast.makeText(NewJam.this,"Unable To Book",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}