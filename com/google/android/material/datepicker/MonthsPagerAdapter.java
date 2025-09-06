package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;

class MonthsPagerAdapter extends Adapter {
    public static class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        final MaterialCalendarGridView monthGrid;
        final TextView monthTitle;

        ViewHolder(LinearLayout linearLayout0, boolean z) {
            super(linearLayout0);
            TextView textView0 = (TextView)linearLayout0.findViewById(id.month_title);
            this.monthTitle = textView0;
            ViewCompat.setAccessibilityHeading(textView0, true);
            this.monthGrid = (MaterialCalendarGridView)linearLayout0.findViewById(id.month_grid);
            if(!z) {
                textView0.setVisibility(8);
            }
        }
    }

    private final CalendarConstraints calendarConstraints;
    private final DateSelector dateSelector;
    private final DayViewDecorator dayViewDecorator;
    private final int itemHeight;
    private final OnDayClickListener onDayClickListener;

    MonthsPagerAdapter(Context context0, DateSelector dateSelector0, CalendarConstraints calendarConstraints0, DayViewDecorator dayViewDecorator0, OnDayClickListener materialCalendar$OnDayClickListener0) {
        Month month0 = calendarConstraints0.getEnd();
        Month month1 = calendarConstraints0.getOpenAt();
        if(calendarConstraints0.getStart().compareTo(month1) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if(month1.compareTo(month0) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        int v = MaterialCalendar.getDayHeight(context0);
        int v1 = MaterialDatePicker.isFullscreen(context0) ? MaterialCalendar.getDayHeight(context0) : 0;
        this.itemHeight = MonthAdapter.MAXIMUM_WEEKS * v + v1;
        this.calendarConstraints = calendarConstraints0;
        this.dateSelector = dateSelector0;
        this.dayViewDecorator = dayViewDecorator0;
        this.onDayClickListener = materialCalendar$OnDayClickListener0;
        this.setHasStableIds(true);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public int getItemCount() {
        return this.calendarConstraints.getMonthSpan();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public long getItemId(int v) {
        return this.calendarConstraints.getStart().monthsLater(v).getStableId();
    }

    Month getPageMonth(int v) {
        return this.calendarConstraints.getStart().monthsLater(v);
    }

    CharSequence getPageTitle(int v) {
        return this.getPageMonth(v).getLongName();
    }

    int getPosition(Month month0) {
        return this.calendarConstraints.getStart().monthsUntil(month0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onBindViewHolder(androidx.recyclerview.widget.RecyclerView.ViewHolder recyclerView$ViewHolder0, int v) {
        this.onBindViewHolder(((ViewHolder)recyclerView$ViewHolder0), v);
    }

    public void onBindViewHolder(ViewHolder monthsPagerAdapter$ViewHolder0, int v) {
        Month month0 = this.calendarConstraints.getStart().monthsLater(v);
        String s = month0.getLongName();
        monthsPagerAdapter$ViewHolder0.monthTitle.setText(s);
        MaterialCalendarGridView materialCalendarGridView0 = (MaterialCalendarGridView)monthsPagerAdapter$ViewHolder0.monthGrid.findViewById(id.month_grid);
        if(materialCalendarGridView0.getAdapter() == null || !month0.equals(materialCalendarGridView0.getAdapter().month)) {
            MonthAdapter monthAdapter0 = new MonthAdapter(month0, this.dateSelector, this.calendarConstraints, this.dayViewDecorator);
            materialCalendarGridView0.setNumColumns(month0.daysInWeek);
            materialCalendarGridView0.setAdapter(monthAdapter0);
        }
        else {
            materialCalendarGridView0.invalidate();
            materialCalendarGridView0.getAdapter().updateSelectedStates(materialCalendarGridView0);
        }
        materialCalendarGridView0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override  // android.widget.AdapterView$OnItemClickListener
            public void onItemClick(AdapterView adapterView0, View view0, int v, long v1) {
                if(materialCalendarGridView0.getAdapter().withinMonth(v)) {
                    MonthsPagerAdapter.this.onDayClickListener.onDayClick(((long)materialCalendarGridView0.getAdapter().getItem(v)));
                }
            }
        });
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup0, int v) {
        return this.onCreateViewHolder(viewGroup0, v);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup0, int v) {
        LinearLayout linearLayout0 = (LinearLayout)LayoutInflater.from(viewGroup0.getContext()).inflate(layout.mtrl_calendar_month_labeled, viewGroup0, false);
        if(MaterialDatePicker.isFullscreen(viewGroup0.getContext())) {
            linearLayout0.setLayoutParams(new LayoutParams(-1, this.itemHeight));
            return new ViewHolder(linearLayout0, true);
        }
        return new ViewHolder(linearLayout0, false);
    }
}

