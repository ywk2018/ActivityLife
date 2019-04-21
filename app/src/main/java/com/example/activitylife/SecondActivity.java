package com.example.activitylife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String data = intent.getExtras().getString("data_bundle");
        Toast.makeText(this, "我是通过bundle传递来的:  " + data, Toast.LENGTH_LONG).show();
    }
}
