package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.core.util.Pair;
import com.google.android.material.R.layout;
import java.util.Collection;

class MonthAdapter extends BaseAdapter {
    private static final int MAXIMUM_GRID_CELLS = 0;
    static final int MAXIMUM_WEEKS = 0;
    private static final int NO_DAY_NUMBER = -1;
    final CalendarConstraints calendarConstraints;
    CalendarStyle calendarStyle;
    final DateSelector dateSelector;
    final DayViewDecorator dayViewDecorator;
    final Month month;
    private Collection previouslySelectedDates;

    static {
        MonthAdapter.MAXIMUM_WEEKS = UtcDates.getUtcCalendar().getMaximum(4);
        MonthAdapter.MAXIMUM_GRID_CELLS = UtcDates.getUtcCalendar().getMaximum(5) + UtcDates.getUtcCalendar().getMaximum(7) - 1;
    }

    MonthAdapter(Month month0, DateSelector dateSelector0, CalendarConstraints calendarConstraints0, DayViewDecorator dayViewDecorator0) {
        this.month = month0;
        this.dateSelector = dateSelector0;
        this.calendarConstraints = calendarConstraints0;
        this.dayViewDecorator = dayViewDecorator0;
        this.previouslySelectedDates = dateSelector0.getSelectedDays();
    }

    int dayToPosition(int v) {
        return this.firstPositionInMonth() + (v - 1);
    }

    int firstPositionInMonth() {
        return this.month.daysFromStartOfWeekToFirstOfMonth(this.calendarConstraints.getFirstDayOfWeek());
    }

    @Override  // android.widget.Adapter
    public int getCount() {
        return MonthAdapter.MAXIMUM_GRID_CELLS;
    }

    private String getDayContentDescription(Context context0, long v) {
        return DateStrings.getDayContentDescription(context0, v, this.isToday(v), this.isStartOfRange(v), this.isEndOfRange(v));
    }

    public Long getItem(int v) {
        if(v >= this.firstPositionInMonth() && v <= this.lastPositionInMonth()) {
            int v1 = this.positionToDay(v);
            return this.month.getDay(v1);
        }
        return null;
    }

    @Override  // android.widget.Adapter
    public Object getItem(int v) {
        return this.getItem(v);
    }

    @Override  // android.widget.Adapter
    public long getItemId(int v) {
        return (long)(v / this.month.daysInWeek);
    }

    @Override  // android.widget.Adapter
    public View getView(int v, View view0, ViewGroup viewGroup0) {
        return this.getView(v, view0, viewGroup0);
    }

    public TextView getView(int v, View view0, ViewGroup viewGroup0) {
        int v2;
        this.initializeStyles(viewGroup0.getContext());
        TextView textView0 = (TextView)view0;
        if(view0 == null) {
            textView0 = (TextView)LayoutInflater.from(viewGroup0.getContext()).inflate(layout.mtrl_calendar_day, viewGroup0, false);
        }
        int v1 = v - this.firstPositionInMonth();
        if(v1 < 0 || v1 >= this.month.daysInMonth) {
            textView0.setVisibility(8);
            textView0.setEnabled(false);
            v2 = -1;
        }
        else {
            v2 = v1 + 1;
            textView0.setTag(this.month);
            textView0.setText(String.format(textView0.getResources().getConfiguration().locale, "%d", v2));
            textView0.setVisibility(0);
            textView0.setEnabled(true);
        }
        Long long0 = this.getItem(v);
        if(long0 == null) {
            return textView0;
        }
        this.updateSelectedState(textView0, ((long)long0), v2);
        return textView0;
    }

    @Override  // android.widget.BaseAdapter
    public boolean hasStableIds() {
        return true;
    }

    private void initializeStyles(Context context0) {
        if(this.calendarStyle == null) {
            this.calendarStyle = new CalendarStyle(context0);
        }
    }

    boolean isEndOfRange(long v) {
        for(Object object0: this.dateSelector.getSelectedRanges()) {
            if(((Pair)object0).second != null && ((long)(((Long)((Pair)object0).second))) == v) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    boolean isFirstInRow(int v) {
        return v % this.month.daysInWeek == 0;
    }

    boolean isLastInRow(int v) {
        return (v + 1) % this.month.daysInWeek == 0;
    }

    private boolean isSelected(long v) {
        for(Object object0: this.dateSelector.getSelectedDays()) {
            if(UtcDates.canonicalYearMonthDay(v) == UtcDates.canonicalYearMonthDay(((long)(((Long)object0))))) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    boolean isStartOfRange(long v) {
        for(Object object0: this.dateSelector.getSelectedRanges()) {
            if(((Pair)object0).first != null && ((long)(((Long)((Pair)object0).first))) == v) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    private boolean isToday(long v) {
        return UtcDates.getTodayCalendar().getTimeInMillis() == v;
    }

    int lastPositionInMonth() {
        return this.firstPositionInMonth() + this.month.daysInMonth - 1;
    }

    int positionToDay(int v) {
        return v - this.firstPositionInMonth() + 1;
    }

    private void updateSelectedState(TextView textView0, long v, int v1) {
        CalendarItemStyle calendarItemStyle0;
        if(textView0 == null) {
            return;
        }
        String s = this.getDayContentDescription(textView0.getContext(), v);
        textView0.setContentDescription(s);
        if(this.calendarConstraints.getDateValidator().isValid(v)) {
            textView0.setEnabled(true);
            boolean z = this.isSelected(v);
            textView0.setSelected(z);
            if(z) {
                calendarItemStyle0 = this.calendarStyle.selectedDay;
            }
            else if(this.isToday(v)) {
                calendarItemStyle0 = this.calendarStyle.todayDay;
            }
            else {
                calendarItemStyle0 = this.calendarStyle.day;
            }
        }
        else {
            textView0.setEnabled(false);
            calendarItemStyle0 = this.calendarStyle.invalidDay;
        }
        if(this.dayViewDecorator != null && v1 != -1) {
            calendarItemStyle0.styleItem(textView0, null, null);
            textView0.setCompoundDrawables(null, null, null, null);
            textView0.setContentDescription(s);
            return;
        }
        calendarItemStyle0.styleItem(textView0);
    }

    private void updateSelectedStateForDate(MaterialCalendarGridView materialCalendarGridView0, long v) {
        if(Month.create(v).equals(this.month)) {
            int v1 = this.month.getDayOfMonth(v);
            this.updateSelectedState(((TextView)materialCalendarGridView0.getChildAt(materialCalendarGridView0.getAdapter().dayToPosition(v1) - materialCalendarGridView0.getFirstVisiblePosition())), v, v1);
        }
    }

    public void updateSelectedStates(MaterialCalendarGridView materialCalendarGridView0) {
        for(Object object0: this.previouslySelectedDates) {
            this.updateSelectedStateForDate(materialCalendarGridView0, ((long)(((Long)object0))));
        }
        DateSelector dateSelector0 = this.dateSelector;
        if(dateSelector0 != null) {
            for(Object object1: dateSelector0.getSelectedDays()) {
                this.updateSelectedStateForDate(materialCalendarGridView0, ((long)(((Long)object1))));
            }
            this.previouslySelectedDates = this.dateSelector.getSelectedDays();
        }
    }

    boolean withinMonth(int v) {
        return v >= this.firstPositionInMonth() && v <= this.lastPositionInMonth();
    }
}

