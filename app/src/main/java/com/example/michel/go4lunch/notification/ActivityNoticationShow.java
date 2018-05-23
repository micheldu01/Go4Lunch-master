package com.example.michel.go4lunch.notification;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.michel.go4lunch.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityNoticationShow extends AppCompatActivity {


    // DECLARE IMAGE NOTIFICATION
    @BindView(R.id.image_notification_show)ImageView imageView;
    // DECLARE SWITCH
    @BindView(R.id.switch_notification)Switch aSwitch;
    // value for de hour of the notification
    private int hour_of_day = 11;
    private int minute = 51;
    private int second = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notication_show);

        // BUTTER KNIFE
        ButterKnife.bind(this);

        // CALL SWITCH
        implementSwitch();

    }


    // IMPLEMENT SWITCH
    public void implementSwitch() {

        // START ON CHECKED
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // IF TRUE START ALARM MANAGER
                if (b) {
                    // START ALARM MANAGER
                    methodAlarmManager();

                    //get a Toast for ask to choice a check box
                    Toast.makeText(ActivityNoticationShow.this, "Notification is activate", Toast.LENGTH_LONG).show();

                    // TEST LOG
                    Log.e("----switch--------","-----ok---------");
                }
            }
        });
    }


    // METHOD ALARM MANAGER
    // method for get the time and the design of the notification
    public void methodAlarmManager() {
        //alarmManager
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        //use calendar for define the hour
        Calendar calendar = Calendar.getInstance();
        //time for show the notification
        calendar.set(Calendar.HOUR_OF_DAY, hour_of_day); // For 1 PM or 2 PM
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        // PendingIntent for AlarmReceiver
        PendingIntent pi = PendingIntent.getBroadcast(this, 0,
                new Intent(this, AlarmRestaurant.class), PendingIntent.FLAG_UPDATE_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                //repeat start notification one a day
                AlarmManager.INTERVAL_DAY, pi);
    }


}