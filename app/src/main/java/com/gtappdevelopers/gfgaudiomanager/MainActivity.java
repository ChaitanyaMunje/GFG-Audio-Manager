package com.gtappdevelopers.gfgaudiomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Textview to display the current ringer mode
    TextView currentStateTV;
    //image buttons to switch ringer mode.
    ImageButton silentIB, vibrateIB, ringtoneIB;
    // object class variable for audio manager class.
    private AudioManager audioManager;
    // current mode to store integer value of ringer mode.
    int currentmode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentStateTV = findViewById(R.id.idTVCurrentMode);
        silentIB = findViewById(R.id.idIBSilentMode);
        vibrateIB = findViewById(R.id.idIBVibrateMode);
        ringtoneIB = findViewById(R.id.idIBRingtoneMode);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //current mode will store current mode of ringer of users device..
        currentmode = audioManager.getRingerMode();

        switch (currentmode) {
            case AudioManager.RINGER_MODE_NORMAL:
                currentStateTV.setText("Ringer Mode");
                break;
            case AudioManager.RINGER_MODE_SILENT:
                currentStateTV.setText("Silent Mode");
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                currentStateTV.setText("Vibrate Mode");
            default:
                currentStateTV.setText("Fail to get mode");
        }


        silentIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // the below code is to check the permission that the access notification policy settings from users device..
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && !notificationManager.isNotificationPolicyAccessGranted()) {

                    Intent intent = new Intent(
                            android.provider.Settings
                                    .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

                    startActivity(intent);
                }
                // set ringer mode here will sets your ringer mode to silent mode
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(MainActivity.this, "Silent Mode Activated..", Toast.LENGTH_SHORT).show();

                currentStateTV.setText("Silent Mode Activated..");
            }
        });

        vibrateIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set ringer mode here will sets your ringer mode to vibrate mode
                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(MainActivity.this, "Vibrate Mode Activated..", Toast.LENGTH_SHORT).show();
                currentStateTV.setText("Vibrate Mode Activated..");

            }
        });

        ringtoneIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set ringer mode here will sets your ringer mode to normal mode
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(MainActivity.this, "Ringtone Mode Activated..", Toast.LENGTH_SHORT).show();
                currentStateTV.setText("Ringtone Mode Activated..");

            }
        });

    }
}