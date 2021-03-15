package com.example.jamsecure;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class HelperAdapterSlotOwner extends FirebaseRecyclerAdapter<FetchSlot, HelperAdapterSlotOwner.jamslotsViewholder> {
    Context context;
    public HelperAdapterSlotOwner(
            @NonNull FirebaseRecyclerOptions<FetchSlot> options)
    {
        super(options);
    }
    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void onBindViewHolder(@NonNull final jamslotsViewholder holder, int position, @NonNull FetchSlot model)
    {
        final String em, d,da,ti;
        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.JamMail.setText(model.getUser_Email());
        //Log.d("NAM",model.getSname());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.Dur.setText(model.getDuration());

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.JamDate.setText(model.getJam_Date());
        holder.JamTime.setText(model.getJam_Time());
        em = model.getUser_Email();
        d = model.getDuration();
        da = model.getJam_Date();
        ti = model.getJam_Time();
        Log.d("HERECHECK","HERE");
        Log.d("DUIRIATION",d);


        Toast.makeText(context, "OnBind view holder helper adapter slot owner : " + em, Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "OnBind view holder helper adapter slot owner : " + d, Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "OnBind view holder helper adapter slot owner : " + position, Toast.LENGTH_SHORT).show();
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public jamslotsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jamslots, parent, false);
        return new HelperAdapterSlotOwner.jamslotsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class jamslotsViewholder extends RecyclerView.ViewHolder {
        TextView JamMail,Dur, JamDate, JamTime;
        public jamslotsViewholder(@NonNull View itemView)
        {
            super(itemView);

            JamMail = itemView.findViewById(R.id.jrmail);
            Dur = itemView.findViewById(R.id.durhrs);
            JamDate = itemView.findViewById(R.id.jdate);
            JamTime = itemView.findViewById(R.id.jtime);
        }
    }
}
