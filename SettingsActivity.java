package com.example.controler;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        EditText ipField = findViewById(R.id.editText_ip);
        EditText portField = findViewById(R.id.editText_port);
        Button saveButton = findViewById(R.id.btn_save);

        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        ipField.setText(preferences.getString("ip", ""));
        portField.setText(preferences.getString("port", ""));

        saveButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("ip", ipField.getText().toString());
            editor.putString("port", portField.getText().toString());
            editor.apply();
        });
    }
}
