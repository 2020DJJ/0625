package com.example.sook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CommuActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commu);

    }

    public void onCLick(View view) {
        Intent intent = new Intent(this, MakeCommuActivity.class);
        startActivity(intent);
/*
        Intent intent2 = getIntent();
        String name  = intent2.getStringExtra("입력한 모임명");
        String number = intent2.getStringExtra("입력한 모임원 수");
        if(name.equals(""))
            Toast.makeText(this, "입력한 모임명이 없습니다.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "입력한 모임명은 "+name+"입력한 모임원 수는 "+number, Toast.LENGTH_LONG).show();
*/
    }
}

