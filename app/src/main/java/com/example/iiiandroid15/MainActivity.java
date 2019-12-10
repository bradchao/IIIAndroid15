package com.example.iiiandroid15;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager smgr;
    private Sensor sensor;
    private MyListener myListener;
    private TextView x, y, z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);

        smgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

//        List<Sensor> sensors = smgr.getSensorList(Sensor.TYPE_ALL);
//        for (Sensor sensor : sensors){
//            Log.v("brad", sensor.getName() + ":" + sensor.getStringType());
//        }

        sensor = smgr.getDefaultSensor(Sensor.TYPE_PRESSURE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        myListener = new MyListener();
        smgr.registerListener(myListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        smgr.unregisterListener(myListener);
    }

    private class MyListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] data = event.values;
            x.setText("x = " + (int)(data[0]*10)/10f );
//            y.setText("y = " + (int)(data[1]*10)/10f );
//            z.setText("z = " + (int)(data[2]*10)/10f );
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

}
