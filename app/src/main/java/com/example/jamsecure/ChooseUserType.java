package com.example.jamsecure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseUserType extends AppCompatActivity {
    RadioButton rb1, rb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user_type);
         rb1 =(RadioButton) findViewById(R.id.radioButton);
         rb2 = (RadioButton) findViewById(R.id.radioButton2);
        Button button = findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(rb1.isChecked()){
                Intent i = new Intent(ChooseUserType.this,Register.class);
                startActivity(i);
                 }
                else if(rb2.isChecked()){
                    Intent i = new Intent(ChooseUserType.this,owner_register.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(ChooseUserType.this,"Please Select one type" ,Toast.LENGTH_LONG).show();

        }});
    }
}
