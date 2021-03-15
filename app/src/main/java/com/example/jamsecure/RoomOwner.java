package com.example.jamsecure;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RoomOwner extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    FloatingActionButton b1;
    private RecyclerView recyclerView;
    HelperAdapterSlotOwner adapter1; // Create Object of the Adapter class
    DatabaseReference fmbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_owner);
        b1= findViewById(R.id.bfa);
        fmbase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        Query mbase = fmbase.child("Slots").orderByChild("JamRoom_Email").equalTo(email);
        Log.d("Querries", String.valueOf(mbase));
        Toast.makeText(this,"Owner is "+email,Toast.LENGTH_SHORT).show();
        recyclerView = findViewById(R.id.rc2);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<FetchSlot> options = new FirebaseRecyclerOptions.Builder<FetchSlot>()
                .setQuery(mbase, FetchSlot.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter1 = new HelperAdapterSlotOwner(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter1);




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://web-chat.global.assistant.watson.cloud.ibm.com/preview.html?region=eu-gb&integrationID=117b6864-801a-4669-b502-070a64091220&serviceInstanceID=16d64f6a-0b0e-4253-b1bd-0bb0e6c38514";
                Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                RoomOwner.this.startActivity(i1);
            }
        });

    }
    @Override protected void onStart()
    {
        super.onStart();
        adapter1.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter1.stopListening();
    }
}