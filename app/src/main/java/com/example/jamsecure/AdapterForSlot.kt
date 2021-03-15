package com.example.jamsecure

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class AdapterForSlot( ctx : Context,
        options: FirebaseRecyclerOptions<FetchSlot?>) : FirebaseRecyclerAdapter<FetchSlot, AdapterForSlot.jamslotsViewholder>(options) {
    var context: Context? = ctx

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    override fun onBindViewHolder(holder: jamslotsViewholder, position: Int, model: FetchSlot) {
        val em: String
        val d: String
        val da: String
        val ti: String
        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.JamMail.text = model.getJamRoom_Email()
        //Log.d("NAM", model.JamRoom_Email)

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.JamDate.text = model.getJam_Date()
        holder.Dur.text = model.getDuration()

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.JamTime.text = model.getJam_Time()
        em = model.getJamRoom_Email()
        d = model.getDuration()
        da = model.getJam_Date()
        ti = model.getJam_Time()
        Log.d("KUR",em);
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): jamslotsViewholder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.jamslots, parent, false)
        return jamslotsViewholder(view)
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    inner class jamslotsViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var JamMail: TextView
        var Dur: TextView
        var JamDate: TextView
        var JamTime : TextView

        init {
            JamMail = itemView.findViewById(R.id.jrmail)
            Dur = itemView.findViewById(R.id.durhrs)
            JamDate = itemView.findViewById(R.id.jdate)
            JamTime = itemView.findViewById(R.id.jtime)
        }
    }
}