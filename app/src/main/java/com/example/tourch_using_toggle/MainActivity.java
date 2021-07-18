package com.example.tourch_using_toggle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton toggleButton;
    CameraManager cameraManager;
    String getCameraId;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton=(ToggleButton)findViewById(R.id.toggleButton);
        cameraManager=(CameraManager)getSystemService(CAMERA_SERVICE);
        try {
            getCameraId=cameraManager.getCameraIdList()[0];

        } catch (Exception e) {
            e.printStackTrace();
        }
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(toggleButton.isChecked())
                {
                    try {
                       cameraManager.setTorchMode(getCameraId,true);
                        Toast.makeText(MainActivity.this, "Flash Light On", Toast.LENGTH_SHORT).show();
                    }
                  catch (CameraAccessException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    try {
                        cameraManager.setTorchMode(getCameraId,false);
                        Toast.makeText(MainActivity.this, "Flash Light off", Toast.LENGTH_SHORT).show();
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }

                }

            }
        });


    }
}