package com.example.controler;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.json.JSONObject;

public class MouseActivity extends AppCompatActivity {

    private WebSocket webSocket;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);

        connectToServer();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];

                try {
                    JSONObject data = new JSONObject();
                    data.put("type", "move");
                    data.put("x", x);
                    data.put("y", y);
                    webSocket.send(data.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        }, gyroscope, SensorManager.SENSOR_DELAY_UI);
    }

    private void connectToServer() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://<SERVER_IP>:5000").build();
        webSocket = client.newWebSocket(request, new WebSocketListener() {});
    }
}
