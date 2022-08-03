package com.example.todoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CalendarActivity extends FragmentActivity {

    SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendarView, caldroidFragment);
        t.commit();

        HashMap<Date, Drawable> map = new HashMap<>();
        for(Deal d : MainActivity.deals){
            if(d.isDateSet()){
                switch (d.getImportance()){
                    case 0:
                        map.put(d.getDate(), new ColorDrawable(Color.parseColor("#F0FFF0")));
                        break;
                    case 1:
                        map.put(d.getDate(), new ColorDrawable(Color.parseColor("#FFF8DC")));
                        break;
                    case 2:
                        map.put(d.getDate(), new ColorDrawable(Color.parseColor("#FFF0F5")));
                        break;
                }
            }
        }

        caldroidFragment.setBackgroundDrawableForDates(map);
        caldroidFragment.refreshView();

        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                for(Deal d : MainActivity.deals){
                    if(formatter.format(d.getDate()).equals(formatter.format(date))){
                        new AlertDialog.Builder(CalendarActivity.this)
                                .setTitle(formatter.format(date))
                                .setMessage(d.getText())
                                .create()
                                .show();
                    }
                }
            }
        };
        caldroidFragment.setCaldroidListener(listener);

        Button back = (Button) findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
