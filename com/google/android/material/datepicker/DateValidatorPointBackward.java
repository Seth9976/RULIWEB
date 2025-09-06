package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public class DateValidatorPointBackward implements DateValidator {
    public static final Parcelable.Creator CREATOR;
    private final long point;

    static {
        DateValidatorPointBackward.CREATOR = new Parcelable.Creator() {
            public DateValidatorPointBackward createFromParcel(Parcel parcel0) {
                return new DateValidatorPointBackward(parcel0.readLong(), null);
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public DateValidatorPointBackward[] newArray(int v) {
                return new DateValidatorPointBackward[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    private DateValidatorPointBackward(long v) {
        this.point = v;
    }

    DateValidatorPointBackward(long v, com.google.android.material.datepicker.DateValidatorPointBackward.1 dateValidatorPointBackward$10) {
        this(v);
    }

    public static DateValidatorPointBackward before(long v) {
        return new DateValidatorPointBackward(v);
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
        return object0 instanceof DateValidatorPointBackward ? this.point == ((DateValidatorPointBackward)object0).point : false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.point});
    }

    @Override  // com.google.android.material.datepicker.CalendarConstraints$DateValidator
    public boolean isValid(long v) {
        return v <= this.point;
    }

    public static DateValidatorPointBackward now() {
        return DateValidatorPointBackward.before(UtcDates.getTodayCalendar().getTimeInMillis());
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeLong(this.point);
    }
}

