package com.example.jamsecure;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class NewJam extends AppCompatActivity {
    EditText etDate,tim;
    Spinner loc,dur;
    int t1hr,t1min;
    String jdate, jtime;
    //DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_jam);
        tim=findViewById(R.id.tim);
        etDate=findViewById(R.id.et_date);
        loc= findViewById(R.id.loc);
        dur=findViewById(R.id.dur) ;
        Calendar calendar = Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        etDate.setFocusable(false);
        etDate.setKeyListener(null);
        tim.setKeyListener(null);
        final String location=loc.getSelectedItem().toString().trim();
        final String duration=dur.getSelectedItem().toString().trim();
       /* setListener = new DatePickerDialog.OnDateSetListener(){
            public void onDateSet(DatePicker view, int year, int month, int dayofMonth)
                month =month+1;
            String date=day+"/"+month+"/"+day;

        }*/
        ArrayAdapter<String> adapter= new ArrayAdapter<>(
                this, R.layout.spin_style,
                getResources().getStringArray(R.array.sp_list));

        adapter.setDropDownViewResource(R.layout.drop_down);
        loc.setAdapter(adapter);
        ArrayAdapter<String> adapter1= new ArrayAdapter<>(
                this, R.layout.spin_style,
                getResources().getStringArray(R.array.sp_list1));
        adapter1.setDropDownViewResource(R.layout.drop_down);
        dur.setAdapter(adapter1);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        NewJam.this, new DatePickerDialog.OnDateSetListener(){
                            public void onDateSet(DatePicker view, int year, int month,int day){
                                month=month+1;
                                String date=day+"/"+month+"/"+year;
                                jdate=day+""+month+year;
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
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0, 0, 0, t1hr, t1min);
                        tim.setText(DateFormat.format("hh:mm aa", calendar));
                    }
                },12,0, false);
                    timePickerDialog.updateTime(t1hr,t1min);
                    timePickerDialog.show();

            }
        });
    }
}