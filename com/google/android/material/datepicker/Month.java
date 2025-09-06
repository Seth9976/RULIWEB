package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

final class Month implements Parcelable, Comparable {
    public static final Parcelable.Creator CREATOR;
    final int daysInMonth;
    final int daysInWeek;
    private final Calendar firstOfMonth;
    private String longName;
    final int month;
    final long timeInMillis;
    final int year;

    static {
        Month.CREATOR = new Parcelable.Creator() {
            public Month createFromParcel(Parcel parcel0) {
                return Month.create(parcel0.readInt(), parcel0.readInt());
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public Month[] newArray(int v) {
                return new Month[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    private Month(Calendar calendar0) {
        calendar0.set(5, 1);
        Calendar calendar1 = UtcDates.getDayCopy(calendar0);
        this.firstOfMonth = calendar1;
        this.month = calendar1.get(2);
        this.year = calendar1.get(1);
        this.daysInWeek = calendar1.getMaximum(7);
        this.daysInMonth = calendar1.getActualMaximum(5);
        this.timeInMillis = calendar1.getTimeInMillis();
    }

    public int compareTo(Month month0) {
        return this.firstOfMonth.compareTo(month0.firstOfMonth);
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Month)object0));
    }

    static Month create(int v, int v1) {
        Calendar calendar0 = UtcDates.getUtcCalendar();
        calendar0.set(1, v);
        calendar0.set(2, v1);
        return new Month(calendar0);
    }

    static Month create(long v) {
        Calendar calendar0 = UtcDates.getUtcCalendar();
        calendar0.setTimeInMillis(v);
        return new Month(calendar0);
    }

    static Month current() {
        return new Month(UtcDates.getTodayCalendar());
    }

    int daysFromStartOfWeekToFirstOfMonth(int v) {
        int v1 = this.firstOfMonth.get(7);
        if(v <= 0) {
            v = this.firstOfMonth.getFirstDayOfWeek();
        }
        int v2 = v1 - v;
        return v2 >= 0 ? v2 : v2 + this.daysInWeek;
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof Month ? this.month == ((Month)object0).month && this.year == ((Month)object0).year : false;
    }

    long getDay(int v) {
        Calendar calendar0 = UtcDates.getDayCopy(this.firstOfMonth);
        calendar0.set(5, v);
        return calendar0.getTimeInMillis();
    }

    int getDayOfMonth(long v) {
        Calendar calendar0 = UtcDates.getDayCopy(this.firstOfMonth);
        calendar0.setTimeInMillis(v);
        return calendar0.get(5);
    }

    String getLongName() {
        if(this.longName == null) {
            this.longName = DateStrings.getYearMonth(this.firstOfMonth.getTimeInMillis());
        }
        return this.longName;
    }

    long getStableId() {
        return this.firstOfMonth.getTimeInMillis();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.month, this.year});
    }

    Month monthsLater(int v) {
        Calendar calendar0 = UtcDates.getDayCopy(this.firstOfMonth);
        calendar0.add(2, v);
        return new Month(calendar0);
    }

    int monthsUntil(Month month0) {
        if(!(this.firstOfMonth instanceof GregorianCalendar)) {
            throw new IllegalArgumentException("Only Gregorian calendars are supported.");
        }
        return (month0.year - this.year) * 12 + (month0.month - this.month);
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeInt(this.year);
        parcel0.writeInt(this.month);
    }
}

