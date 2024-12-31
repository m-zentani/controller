package com.example.controler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_mouse).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MouseActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_keyboard).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, KeyboardActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_settings).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}
