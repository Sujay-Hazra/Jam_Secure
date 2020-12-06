package com.example.jamsecure;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class RoomOwner extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_owner);
    }
}