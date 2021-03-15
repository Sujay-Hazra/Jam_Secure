package com.example.jamsecure;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText etEmail, etPassword;
    ProgressBar progressBar;
    private FirebaseUser mUser;
    private FirebaseDatabase mDatabase;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.reg);
        etEmail = findViewById(R.id.em);
        etPassword = findViewById(R.id.tpw);
        Button lbtn = findViewById(R.id.lb);
        mAuth = FirebaseAuth.getInstance();


        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
       lbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userlogin();

            }
        });
        SpannableString spannableString = new SpannableString("New user? tap here to register!");
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(@NotNull View v) {
                Intent intent = new Intent(MainActivity.this, ChooseUserType.class);
                startActivity(intent);
            }
        };
        spannableString.setSpan(span, 0, 31, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private void userlogin(){
        final String email=etEmail.getText().toString().trim();
        String password=etPassword.getText().toString().trim();
        progressBar.setVisibility(View.VISIBLE);
        if(email.isEmpty()){
            etEmail.setError("Please Enter Email");
            etEmail.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if(password.isEmpty()){
            etPassword.setError("Email Required");
            etPassword.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if(password.length()<6){
            etPassword.setError("Min password length should be 6");
            etPassword.requestFocus();
            progressBar.setVisibility(View.GONE);
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                    mUser = mAuth.getCurrentUser();
                    mDatabase = FirebaseDatabase.getInstance();
                    mDatabase.getReference("Owners").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Map<String,Object> owners = (Map)snapshot.getValue();
                            if(owners.containsKey(mUser.getUid())){
                                Intent i = new Intent(MainActivity.this,RoomOwner.class);
                                Log.d("nun","1");
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //so that you don't come back to login page again
                                startActivity(i);
                                //Owner code
                            }
                            else{
                                Intent i = new Intent(MainActivity.this,Bookjamroom.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                //so that you don't come back to login page again
                                Log.d("nun","0");
                                startActivity(i);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


