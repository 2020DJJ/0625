package com.example.sook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MakeCommuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makecommu);
    }

    public void onClick2 (View view){

        EditText editname = (EditText) findViewById(R.id.input_name);
        String name = editname.getText().toString();
        EditText editnumber = (EditText) findViewById(R.id.input_number);
        String number = editnumber.getText().toString();

        Intent intent = new Intent(this, CommuActivity.class);
        intent.putExtra("입력한 모임명", name);
        intent.putExtra("입력한 모임원 수", number);

        startActivity(intent);
    }
}