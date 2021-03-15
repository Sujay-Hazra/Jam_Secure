 package com.example.jamsecure;

 import android.content.Intent;
 import android.os.Bundle;
 import android.util.Log;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.firebase.ui.database.FirebaseRecyclerOptions;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.Query;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

public class User_Select_Jam_Studio extends AppCompatActivity {

        private RecyclerView recyclerView;
        HelperAdapter adapter; // Create Object of the Adapter class
        DatabaseReference fmbase; // Create object of the
        // Firebase Realtime Database
        TextView l;
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user__select__jam__studio);
            Intent intent = getIntent();
            String location = intent.getStringExtra("LOC");
            Log.d("THELOC",location);
           // l = findViewById(R.id.tffl);
           // l.setText(location);
            // Create a instance of the database and get
            // its reference
            String msg = "These are the Jam Rooms at ";
            String fmsg = msg+location;
            Toast.makeText(User_Select_Jam_Studio.this,fmsg,Toast.LENGTH_LONG).show();
            Toast.makeText(User_Select_Jam_Studio.this,fmsg,Toast.LENGTH_LONG).show();
            fmbase = FirebaseDatabase.getInstance().getReference();
            Query mbase = fmbase.child("Owners").orderByChild("location").equalTo(location);
            Log.d("Querries", String.valueOf(mbase));

            recyclerView = findViewById(R.id.recycler1);

            // To display the Recycler view linearly
            recyclerView.setLayoutManager(
                    new LinearLayoutManager(this));

            // It is a class provide by the FirebaseUI to make a
            // query in the database to fetch appropriate data
            FirebaseRecyclerOptions<FetchData> options = new FirebaseRecyclerOptions.Builder<FetchData>()
                    .setQuery(mbase, FetchData.class)
                    .build();
            // Connecting object of required Adapter class to
            // the Adapter class itself
            adapter = new HelperAdapter(options);
            // Connecting Adapter class with the Recycler view*/
            recyclerView.setAdapter(adapter);
        }

        // Function to tell the app to start getting
        // data from database on starting of the activity
        @Override protected void onStart()
        {
            super.onStart();
            adapter.startListening();
        }

        // Function to tell the app to stop getting
        // data from database on stoping of the activity
        @Override protected void onStop()
        {
            super.onStop();
            adapter.stopListening();
        }
    }
