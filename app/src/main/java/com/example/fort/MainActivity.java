package com.example.fort;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton imb11 = findViewById(R.id.list);
        ImageButton imb12 = findViewById(R.id.addList);
        ImageButton imb21 = findViewById(R.id.addNote);
        ImageButton imb22 = findViewById(R.id.settings);

        imb11.setOnClickListener(this);
        imb12.setOnClickListener(this);
        imb21.setOnClickListener(this);
        imb22.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list: {
                Intent intent11 = new Intent(MainActivity.this, ListsActivity.class);
                startActivity(intent11);
            }

            case R.id.addList: {
                Intent intent12 = new Intent(MainActivity.this, ListsActivity.class);
                startActivity(intent12);
            }

            case R.id.addNote:{
                Intent intent21 = new Intent(MainActivity.this, NotesActivity.class);
                startActivity(intent21);
            }

            case R.id.settings:{
                Intent intent22 = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent22);
            }
        }
    }
}