package com.example.sook;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.listeners.OnMonthChangeListener;
import sun.bob.mcalendarview.vo.DateData;
import sun.bob.mcalendarview.vo.MarkedDates;

public class Calendar2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar2);

        final MCalendarView calendarView = ((MCalendarView) findViewById(R.id.calendar));
        calendarView.travelTo(new DateData(2020, 6, 11));
        calendarView.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                Toast.makeText(Calendar2.this, String.format("%d-%d", date.getMonth(), date.getDay()), Toast.LENGTH_SHORT).show();
            }
        }).setOnMonthChangeListener(new OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                ((TextView) findViewById(R.id.ind)).setText(String.format("%d-%d", year, month));
                Toast.makeText(Calendar2.this, String.format("%d-%d", year, month), Toast.LENGTH_SHORT).show();
                calendarView.markDate(year, month, 5);
                MarkedDates.getInstance().notifyObservers();
            }
        }).setMarkedStyle(MarkStyle.RIGHTSIDEBAR)
                .markDate(2020, 6, 1).markDate(2020, 6, 25)
                .markDate(2020, 6, 4)
                .markDate(new DateData(2020, 6, 1).setMarkStyle(new MarkStyle(MarkStyle.DOT, Color.GREEN)))
                .hasTitle(false);
    }
}



