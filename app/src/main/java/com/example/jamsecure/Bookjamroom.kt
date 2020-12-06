package com.example.jamsecure

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
            startActivity(Intent(this, User_Select_Jam_Studio::class.java))

        }
        bp?.setOnClickListener {
            Toast.makeText(this, "2button is clicked", Toast.LENGTH_SHORT).show()

        }
        bhb?.setOnClickListener {
            startActivity(Intent(this, HelpBot::class.java))

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
}