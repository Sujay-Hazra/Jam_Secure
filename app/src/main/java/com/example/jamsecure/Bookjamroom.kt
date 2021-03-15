package com.example.jamsecure

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
class Bookjamroom : AppCompatActivity() {
    private lateinit var bfa: FloatingActionButton
    private lateinit var bnj: FloatingActionButton
    private lateinit var bp : FloatingActionButton
    private lateinit var bhb : FloatingActionButton
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom) }
    private var clicked = false
    lateinit var recyc : RecyclerView
    lateinit var adapter : AdapterForSlot
    var fmbase: DatabaseReference? = null
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_jam_room)
        bfa = findViewById<FloatingActionButton>(R.id.bfa)
        bnj = findViewById<FloatingActionButton>(R.id.bnj)
        bp = findViewById<FloatingActionButton>(R.id.bp)
        bhb = findViewById<FloatingActionButton>(R.id.bhb)

        bfa?.setOnClickListener {
            onAddButtonClicked()
        }
        bnj?.setOnClickListener {
            startActivity(Intent(this, User_Select_Location::class.java))

        }
        bp?.setOnClickListener {
            Toast.makeText(this, "2button is clicked", Toast.LENGTH_SHORT).show()
            val user = FirebaseAuth.getInstance().currentUser

                // Name, email address, and profile photo Url
                val email = user!!.email
            Toast.makeText(this, "User is $email", Toast.LENGTH_SHORT).show()
            fmbase = FirebaseDatabase.getInstance().reference
            val mbase: Query = fmbase!!.child("Slots").orderByChild("User_Email").equalTo(email)
            //Log.d("Querries", mbase.toString())
            //fmbase!!.child("Slots").
            recyc = findViewById<RecyclerView>(R.id.rc1)

            // To display the Recycler view linearly

            // To display the Recycler view linearly
            recyc.setLayoutManager(
                    LinearLayoutManager(this))

            // It is a class provide by the FirebaseUI to make a
            // query in the database to fetch appropriate data

            // It is a class provide by the FirebaseUI to make a
            // query in the database to fetch appropriate data
            val options = FirebaseRecyclerOptions.Builder<FetchSlot>()
                    .setQuery(mbase, FetchSlot::class.java)
                    .build()
            // Connecting object of required Adapter class to
            // the Adapter class itself
            // Connecting object of required Adapter class to
            // the Adapter class itself
            adapter = AdapterForSlot(this, options)
            // Connecting Adapter class with the Recycler view*/
            // Connecting Adapter class with the Recycler view*/
            recyc.setAdapter(adapter)

        }
        bhb?.setOnClickListener {
            val url = "https://web-chat.global.assistant.watson.cloud.ibm.com/preview.html?region=eu-gb&integrationID=117b6864-801a-4669-b502-070a64091220&serviceInstanceID=16d64f6a-0b0e-4253-b1bd-0bb0e6c38514"
            val i1 = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i1)

        }

    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            bnj.visibility = View.VISIBLE
            bp.visibility = View.VISIBLE
            bhb.visibility = View.VISIBLE
        } else {
            bnj.visibility = View.INVISIBLE
            bp.visibility = View.INVISIBLE
            bhb.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            bnj.startAnimation(fromBottom)
            bp.startAnimation(fromBottom)
            bhb.startAnimation(fromBottom)
            bfa.startAnimation(rotateOpen)
        } else {
            bnj.startAnimation(toBottom)
            bp.startAnimation(toBottom)
            bhb.startAnimation(toBottom)
            bfa.startAnimation(rotateClose)
        }
    }
    private fun setClickable(clicked: Boolean){
        if(!clicked) {
            bnj.isClickable = true
            bp.isClickable = true
            bhb.isClickable = true
        }
        else{
            bnj.isClickable=false
            bp.isClickable=false
            bhb.isClickable=false
        }
    }

    /*override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }*/
}