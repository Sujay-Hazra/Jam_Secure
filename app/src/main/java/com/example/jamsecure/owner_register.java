package com.example.jamsecure;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class owner_register extends AppCompatActivity implements View.OnClickListener{
    EditText email, pw, mob, pwr, sname, jr;
    Spinner loc;
    ProgressBar progressBar;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_register);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        email=findViewById(R.id.temail);
        pw=findViewById(R.id.tpw);
        mob=findViewById(R.id.tcn);
        loc=findViewById(R.id.tl);
        pwr=findViewById(R.id.tpwr);
        sname=findViewById(R.id.tfn);
        jr=findViewById(R.id.tjr);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.br).setOnClickListener((View.OnClickListener) this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            //handle it
        }
    }

    private void registerOwner(){
        final String em=email.getText().toString().trim();
        final String name=sname.getText().toString().trim();
        String password=pw.getText().toString().trim();
        String password_re=pwr.getText().toString().trim();
        final String phone=mob.getText().toString().trim();
        final String jamrate=jr.getText().toString().trim();
        final String location=loc.getSelectedItem().toString().trim();
        if(name.isEmpty()){
            sname.setError("Please Enter Full Name");
            sname.requestFocus();
            return;
        }
        if(em.isEmpty()){
            email.setError("Email Required");
            email.requestFocus();
            return;
        }
        if(jamrate.isEmpty()){
            jr.setError("Email Required");
            jr.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(em).matches()){
            email.setError("Enter Valid Email");
            email.requestFocus();
            return;
        }
        if(password.isEmpty()){
            pw.setError("Enter Password");
            pw.requestFocus();
            return;
        }
        if(password.length()<6){
            pw.setError("Min password length should be 6");
            pw.requestFocus();
            return;
        }
        if(password_re.isEmpty()){
            pwr.setError("Enter Password Again");
            pwr.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            mob.setError("Please Enter Contact Info");
            mob.requestFocus();
            return;
        }
        if(phone.length()!=10){
            mob.setError("Please Enter Valid Contact Info");
            mob.requestFocus();
            return;
        }
        if(!password.equals(password_re)){
            pwr.setError("Please re enter same password");
            pwr.requestFocus();
            return;
        }
        if(location.isEmpty()){
            Toast toast = Toast.makeText(this, "Please Select Location", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(em,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Owner owner = new Owner(name,em,phone,location,jamrate);
                    FirebaseDatabase.getInstance().getReference("Owners")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(owner).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                Toast.makeText(owner_register.this,"Registration Successful" ,Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                Intent i = new Intent(owner_register.this,RoomOwner.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //so that you don't come back to login page again
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(owner_register.this,"Unable To Register",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"You are already registered", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                    else{
                        Toast.makeText(owner_register.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.br: registerOwner();
                break;
        }
    }
}

