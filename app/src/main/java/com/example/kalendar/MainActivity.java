package com.example.kalendar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText shss;


    TextView textView2;


    String rez2;

    String dat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = (Button) findViewById(R.id.prod);
        shss = (EditText) findViewById(R.id.SHSS);

        textView2 = (TextView) findViewById(R.id.textView3);

        SharedPreferences sPref =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sPref.edit();



        rez2 = sPref.getString("zacladka2", " ");


        textView2.setText(rez2);



        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                int mYear = year;
                int mMonth = month;
                int mDay = dayOfMonth;

                String selectedDate = new StringBuilder().append(mMonth + 1)
                        .append("-").append(mDay).append("-").append(mYear)
                        .append(" ").toString();
                dat = selectedDate;

                Toast.makeText(getApplicationContext(), "Выбрана следующая дата -  " + selectedDate, Toast.LENGTH_LONG).show();

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onProdClick(View v) {

        if(dat != "" && shss.getText().length() != 0){

            SharedPreferences sPref =
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor=myPreferences.edit();

            rez2 = sPref.getString("zacladka2", " ");


            editor.putString("zacladka2","Последняя закладка: "+shss.getText().toString()+" | Дата - " + dat);
            editor.commit();

            textView2.setText(rez2);

        }
        else{
            Toast.makeText(getApplicationContext(), "Вы не заполнили поле или не выбрали дату!", Toast.LENGTH_SHORT).show();
        }
    }
}