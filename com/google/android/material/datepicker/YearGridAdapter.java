package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import com.google.android.material.R.layout;
import java.util.Calendar;
import java.util.Locale;

class YearGridAdapter extends Adapter {
    public static class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        final TextView textView;

        ViewHolder(TextView textView0) {
            super(textView0);
            this.textView = textView0;
        }
    }

    private final MaterialCalendar materialCalendar;

    YearGridAdapter(MaterialCalendar materialCalendar0) {
        this.materialCalendar = materialCalendar0;
    }

    private View.OnClickListener createYearClickListener(int v) {
        return new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                Month month0 = YearGridAdapter.this.materialCalendar.getCurrentMonth();
                Month month1 = Month.create(v, month0.month);
                Month month2 = YearGridAdapter.this.materialCalendar.getCalendarConstraints().clamp(month1);
                YearGridAdapter.this.materialCalendar.setCurrentMonth(month2);
                YearGridAdapter.this.materialCalendar.setSelector(CalendarSelector.DAY);
            }
        };
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public int getItemCount() {
        return this.materialCalendar.getCalendarConstraints().getYearSpan();
    }

    int getPositionForYear(int v) {
        return v - this.materialCalendar.getCalendarConstraints().getStart().year;
    }

    int getYearForPosition(int v) {
        return this.materialCalendar.getCalendarConstraints().getStart().year + v;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onBindViewHolder(androidx.recyclerview.widget.RecyclerView.ViewHolder recyclerView$ViewHolder0, int v) {
        this.onBindViewHolder(((ViewHolder)recyclerView$ViewHolder0), v);
    }

    public void onBindViewHolder(ViewHolder yearGridAdapter$ViewHolder0, int v) {
        int v1 = this.getYearForPosition(v);
        Locale locale0 = Locale.getDefault();
        yearGridAdapter$ViewHolder0.textView.setText(String.format(locale0, "%d", v1));
        String s = DateStrings.getYearContentDescription(yearGridAdapter$ViewHolder0.textView.getContext(), v1);
        yearGridAdapter$ViewHolder0.textView.setContentDescription(s);
        CalendarStyle calendarStyle0 = this.materialCalendar.getCalendarStyle();
        Calendar calendar0 = UtcDates.getTodayCalendar();
        CalendarItemStyle calendarItemStyle0 = calendar0.get(1) == v1 ? calendarStyle0.todayYear : calendarStyle0.year;
        for(Object object0: this.materialCalendar.getDateSelector().getSelectedDays()) {
            calendar0.setTimeInMillis(((long)(((Long)object0))));
            if(calendar0.get(1) == v1) {
                calendarItemStyle0 = calendarStyle0.selectedYear;
            }
        }
        calendarItemStyle0.styleItem(yearGridAdapter$ViewHolder0.textView);
        View.OnClickListener view$OnClickListener0 = this.createYearClickListener(v1);
        yearGridAdapter$ViewHolder0.textView.setOnClickListener(view$OnClickListener0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup0, int v) {
        return this.onCreateViewHolder(viewGroup0, v);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup0, int v) {
        return new ViewHolder(((TextView)LayoutInflater.from(viewGroup0.getContext()).inflate(layout.mtrl_calendar_year, viewGroup0, false)));
    }
}

