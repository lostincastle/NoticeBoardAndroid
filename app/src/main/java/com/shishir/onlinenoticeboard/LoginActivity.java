package com.shishir.onlinenoticeboard;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shishir.onlinenoticeboard.StrictMode.StrictModeC;
import com.shishir.onlinenoticeboard.api.BLL;
import com.shishir.onlinenoticeboard.model.UserModel;
import com.shishir.onlinenoticeboard.response.LoginRegisterResponse;


public class LoginActivity extends AppCompatActivity {
    private EditText Username, Password;
    private Button login, link;
    public static String Token = "";
    Context context;
    boolean isSuccess = false;
    LoginRegisterResponse model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Gyro();
        Username = findViewById(R.id.etusername);
        Password = findViewById(R.id.etpassword);
        Username.setText("jeevan");
        Password.setText("jeevan");
        login = findViewById(R.id.btn_login);
        link = findViewById(R.id.btnLink);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

    }

    private void check() {
        String user = Username.getText().toString();
        String pass = Password.getText().toString();

        if (TextUtils.isEmpty(Username.getText())) {
            Username.setError("Enter username");
            return;

        } else if (TextUtils.isEmpty(Password.getText())) {
            Password.setError("Enter password");
            return;
        }

        UserModel userModel = new UserModel(user, pass);

        StrictModeC.StrictMode();
        BLL bll = new BLL();
        boolean loggedIn = bll.LoginBLL(userModel);
        Toast.makeText(this, loggedIn + "", Toast.LENGTH_SHORT).show();
        if (loggedIn) {
            Intent intent = new Intent(LoginActivity.this, MyDashboardActivity.class);
            startActivity(intent);
            finish();
        } else {
//            Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);//if username and password is wrong then real device vibrates
            vibrator.vibrate(5000);
            Toast.makeText(this, "Invalid login", Toast.LENGTH_SHORT).show();
        }
    }

        private void Gyro() {
            SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

            SensorEventListener gyroEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    if (event.values[2] > 0.5f) {        // anticlockwise
                        Log.d("gyro", "tilted left");
                        Toast.makeText(LoginActivity.this, "tilted left", Toast.LENGTH_SHORT).show();
                    } else if (event.values[2] < -0.5f) {     // clockwise
                        Toast.makeText(LoginActivity.this, "right tilted", Toast.LENGTH_SHORT).show();
                        check();
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };
//        register listener
            sensorManager.registerListener(gyroEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        }
    }






