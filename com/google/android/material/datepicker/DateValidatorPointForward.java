package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public class DateValidatorPointForward implements DateValidator {
    public static final Parcelable.Creator CREATOR;
    private final long point;

    static {
        DateValidatorPointForward.CREATOR = new Parcelable.Creator() {
            public DateValidatorPointForward createFromParcel(Parcel parcel0) {
                return new DateValidatorPointForward(parcel0.readLong(), null);
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public DateValidatorPointForward[] newArray(int v) {
                return new DateValidatorPointForward[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    private DateValidatorPointForward(long v) {
        this.point = v;
    }

    DateValidatorPointForward(long v, com.google.android.material.datepicker.DateValidatorPointForward.1 dateValidatorPointForward$10) {
        this(v);
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
        return object0 instanceof DateValidatorPointForward ? this.point == ((DateValidatorPointForward)object0).point : false;
    }

    public static DateValidatorPointForward from(long v) {
        return new DateValidatorPointForward(v);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.point});
    }

    @Override  // com.google.android.material.datepicker.CalendarConstraints$DateValidator
    public boolean isValid(long v) {
        return v >= this.point;
    }

    public static DateValidatorPointForward now() {
        return DateValidatorPointForward.from(UtcDates.getTodayCalendar().getTimeInMillis());
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeLong(this.point);
    }
}

