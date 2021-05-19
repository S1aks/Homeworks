package com.hw.homework05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initClickListener();
    }

    private void initClickListener() {
        findViewById(R.id.button).setOnClickListener(v -> {
            Uri uri = Uri.parse("example://intent");
            Intent runIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(runIntent);
        });
    }
}