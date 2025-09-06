package com.google.android.material.datepicker;

import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.R.layout;
import com.google.android.material.R.string;
import java.util.Calendar;
import java.util.Locale;

class DaysOfWeekAdapter extends BaseAdapter {
    private static final int CALENDAR_DAY_STYLE = 0;
    private static final int NARROW_FORMAT = 4;
    private final Calendar calendar;
    private final int daysInWeek;
    private final int firstDayOfWeek;

    static {
        DaysOfWeekAdapter.CALENDAR_DAY_STYLE = Build.VERSION.SDK_INT < 26 ? 1 : 4;
    }

    public DaysOfWeekAdapter() {
        Calendar calendar0 = UtcDates.getUtcCalendar();
        this.calendar = calendar0;
        this.daysInWeek = calendar0.getMaximum(7);
        this.firstDayOfWeek = calendar0.getFirstDayOfWeek();
    }

    public DaysOfWeekAdapter(int v) {
        Calendar calendar0 = UtcDates.getUtcCalendar();
        this.calendar = calendar0;
        this.daysInWeek = calendar0.getMaximum(7);
        this.firstDayOfWeek = v;
    }

    @Override  // android.widget.Adapter
    public int getCount() {
        return this.daysInWeek;
    }

    public Integer getItem(int v) {
        return v < this.daysInWeek ? this.positionToDayOfWeek(v) : null;
    }

    @Override  // android.widget.Adapter
    public Object getItem(int v) {
        return this.getItem(v);
    }

    @Override  // android.widget.Adapter
    public long getItemId(int v) {
        return 0L;
    }

    @Override  // android.widget.Adapter
    public View getView(int v, View view0, ViewGroup viewGroup0) {
        View view1 = (TextView)view0;
        if(view0 == null) {
            view1 = (TextView)LayoutInflater.from(viewGroup0.getContext()).inflate(layout.mtrl_calendar_day_of_week, viewGroup0, false);
        }
        int v1 = this.positionToDayOfWeek(v);
        this.calendar.set(7, v1);
        Configuration configuration0 = ((TextView)view1).getResources().getConfiguration();
        ((TextView)view1).setText(this.calendar.getDisplayName(7, DaysOfWeekAdapter.CALENDAR_DAY_STYLE, configuration0.locale));
        String s = viewGroup0.getContext().getString(string.mtrl_picker_day_of_week_column_header);
        Locale locale0 = Locale.getDefault();
        ((TextView)view1).setContentDescription(String.format(s, this.calendar.getDisplayName(7, 2, locale0)));
        return view1;
    }

    private int positionToDayOfWeek(int v) {
        int v1 = v + this.firstDayOfWeek;
        return v1 <= this.daysInWeek ? v1 : v1 - this.daysInWeek;
    }
}

