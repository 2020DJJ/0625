package com.example.sook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class DrinkActivity extends AppCompatActivity {

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_drink);
        TextView txttitle = (TextView) findViewById(R.id.txttitle);

        //button calendar
        findViewById(R.id.button_calendar).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        Intent intent = new Intent(DrinkActivity.this, CalendarActivity.class);
                        startActivity(intent);
                    }
                });
        //bottom1~5
        findViewById(R.id.bottom_home).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        Intent intent = new Intent(DrinkActivity.this, PrefActivity.class);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.bottom_drink).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        Intent intent = new Intent(DrinkActivity.this, DrinkActivity.class);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.bottom_meal).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        Intent intent = new Intent(DrinkActivity.this, MealActivity.class);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.bottom_pref).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        Intent intent = new Intent(DrinkActivity.this, PrefActivity.class);
                        startActivity(intent);
                    }
                });
        }
}