package com.example.jamsecure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class User_Select_Location extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__select__location);
        Button bn = findViewById(R.id.b);
         final  Spinner loc=findViewById(R.id.loc2);

        bn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                int pos=loc.getSelectedItemPosition();
                String location=loc.getSelectedItem().toString().trim();
                if(location.isEmpty()){
                    Toast toast = Toast.makeText(User_Select_Location.this, "Please Select Location", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
               else if(pos==0){
                    Toast toast = Toast.makeText(User_Select_Location.this, "Please Select Location, not this one", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                else {

                    Intent intent = new Intent(User_Select_Location.this, User_Select_Jam_Studio.class);
                    intent.putExtra("LOC",location);
                    User_Select_Location.this.startActivity(intent);


                }
            }
        });

    }
}