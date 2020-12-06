package com.example.jamsecure;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class User_Select_Jam_Studio extends AppCompatActivity {

    List<FetchData> fetchData;
    RecyclerView recyclerView;
    HelperAdapter helperAdapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__select__jam__studio);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        fetchData=new ArrayList<>();
        helperAdapter=new HelperAdapter(fetchData);
        recyclerView.setAdapter(helperAdapter);

        databaseReference= FirebaseDatabase.getInstance().getReference("Owners");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    FetchData data=ds.getValue(FetchData.class);
                    fetchData.add(data);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(User_Select_Jam_Studio.this,"Data base problem",Toast.LENGTH_LONG).show();
            }
        });

    }
}