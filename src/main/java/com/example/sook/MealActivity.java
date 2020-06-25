package com.example.sook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        findViewById(R.id.meal_pref).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        Intent intent = new Intent(MealActivity.this, MealPref.class);
                        startActivity(intent);
                    }
                });
        findViewById(R.id.showrecipe).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        Intent intent = new Intent(MealActivity.this, Recipe2.class);
                        startActivity(intent);
                    }
                });
    }
}
