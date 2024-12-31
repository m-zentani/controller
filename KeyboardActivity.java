package com.example.controler;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import org.json.JSONObject;

public class KeyboardActivity extends AppCompatActivity {

    private WebSocket webSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        connectToServer();

        EditText editText = findViewById(R.id.editText);
        Button sendButton = findViewById(R.id.btn_send);

        sendButton.setOnClickListener(v -> {
            String text = editText.getText().toString();

            try {
                JSONObject data = new JSONObject();
                data.put("type", "keyboard_input");
                data.put("text", text);
                webSocket.send(data.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void connectToServer() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://<SERVER_IP>:5000").build();
        webSocket = client.newWebSocket(request, new okhttp3.WebSocketListener() {});
    }
}
