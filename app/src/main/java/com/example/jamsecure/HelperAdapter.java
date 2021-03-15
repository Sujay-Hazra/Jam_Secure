package com.example.jamsecure;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


    // FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
    public class HelperAdapter extends FirebaseRecyclerAdapter<FetchData, HelperAdapter.jamroomsViewholder> {
        Context context;
        public HelperAdapter(
                @NonNull FirebaseRecyclerOptions<FetchData> options)
        {
            super(options);
        }
        // Function to bind the view in Card view(here
        // "person.xml") iwth data in
        // model class(here "person.class")
        @Override
        protected void onBindViewHolder(@NonNull final jamroomsViewholder holder, int position, @NonNull FetchData model)
        {
            final String em, nam, rate, loc;
            // Add firstname from model class (here
            // "person.class")to appropriate view in Card
            // view (here "person.xml")
            holder.JamName.setText(model.getSname());
            Log.d("NAM",model.getSname());

            // Add lastname from model class (here
            // "person.class")to appropriate view in Card
            // view (here "person.xml")
            holder.Phone.setText(model.getPhone());

            // Add age from model class (here
            // "person.class")to appropriate view in Card
            // view (here "person.xml")

            holder.JamRate.setText(model.getJam_rate());
            em=model.getSemail();
            nam=model.getSname();
            rate=model.getJam_rate();
            loc=model.getLocation();
          holder.itemView.setOnClickListener(new View.OnClickListener(){
              @Override
              public void onClick(View view) {
                    context = view.getContext();
                  Intent intent = new Intent(context, NewJam.class);
                  intent.putExtra("name",nam);
                  intent.putExtra("email",em);
                  intent.putExtra("rate",rate);
                  intent.putExtra("loc",loc);
                  Log.d("Murker",em);
                  context.startActivity(intent);
              }
          });

        }

        // Function to tell the class about the Card view (here
        // "person.xml")in
        // which the data will be shown
        @NonNull
        @Override
        public jamroomsViewholder
        onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view
                    = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.jamrooms, parent, false);
            return new HelperAdapter.jamroomsViewholder(view);
        }

        // Sub Class to create references of the views in Crad
        // view (here "person.xml")
        class jamroomsViewholder extends RecyclerView.ViewHolder {
            TextView JamName, Phone, JamRate;
            public jamroomsViewholder(@NonNull View itemView)
            {
                super(itemView);

                JamName = itemView.findViewById(R.id.jrmail);
                Phone= itemView.findViewById(R.id.durhrs);
                JamRate = itemView.findViewById(R.id.jdate);
            }
        }
    }
