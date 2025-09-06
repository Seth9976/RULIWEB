package com.google.android.material.datepicker;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import androidx.core.util.ObjectsCompat;
import java.util.Arrays;
import java.util.Objects;

public final class CalendarConstraints implements Parcelable {
    public static final class Builder {
        private static final String DEEP_COPY_VALIDATOR_KEY = "DEEP_COPY_VALIDATOR_KEY";
        static final long DEFAULT_END;
        static final long DEFAULT_START;
        private long end;
        private int firstDayOfWeek;
        private Long openAt;
        private long start;
        private DateValidator validator;

        static {
            Builder.DEFAULT_START = UtcDates.canonicalYearMonthDay(Month.create(1900, 0).timeInMillis);
            Builder.DEFAULT_END = UtcDates.canonicalYearMonthDay(Month.create(2100, 11).timeInMillis);
        }

        public Builder() {
            this.start = Builder.DEFAULT_START;
            this.end = Builder.DEFAULT_END;
            this.validator = DateValidatorPointForward.from(0x8000000000000000L);
        }

        Builder(CalendarConstraints calendarConstraints0) {
            this.start = Builder.DEFAULT_START;
            this.end = Builder.DEFAULT_END;
            this.validator = DateValidatorPointForward.from(0x8000000000000000L);
            this.start = calendarConstraints0.start.timeInMillis;
            this.end = calendarConstraints0.end.timeInMillis;
            this.openAt = calendarConstraints0.openAt.timeInMillis;
            this.firstDayOfWeek = calendarConstraints0.firstDayOfWeek;
            this.validator = calendarConstraints0.validator;
        }

        public CalendarConstraints build() {
            Bundle bundle0 = new Bundle();
            bundle0.putParcelable("DEEP_COPY_VALIDATOR_KEY", this.validator);
            Month month0 = Month.create(this.start);
            Month month1 = Month.create(this.end);
            Parcelable parcelable0 = bundle0.getParcelable("DEEP_COPY_VALIDATOR_KEY");
            return this.openAt == null ? new CalendarConstraints(month0, month1, ((DateValidator)parcelable0), null, this.firstDayOfWeek, null) : new CalendarConstraints(month0, month1, ((DateValidator)parcelable0), Month.create(((long)this.openAt)), this.firstDayOfWeek, null);
        }

        public Builder setEnd(long v) {
            this.end = v;
            return this;
        }

        public Builder setFirstDayOfWeek(int v) {
            this.firstDayOfWeek = v;
            return this;
        }

        public Builder setOpenAt(long v) {
            this.openAt = v;
            return this;
        }

        public Builder setStart(long v) {
            this.start = v;
            return this;
        }

        public Builder setValidator(DateValidator calendarConstraints$DateValidator0) {
            Objects.requireNonNull(calendarConstraints$DateValidator0, "validator cannot be null");
            this.validator = calendarConstraints$DateValidator0;
            return this;
        }
    }

    public interface DateValidator extends Parcelable {
        boolean isValid(long arg1);
    }

    public static final Parcelable.Creator CREATOR;
    private final Month end;
    private final int firstDayOfWeek;
    private final int monthSpan;
    private Month openAt;
    private final Month start;
    private final DateValidator validator;
    private final int yearSpan;

    static {
        CalendarConstraints.CREATOR = new Parcelable.Creator() {
            public CalendarConstraints createFromParcel(Parcel parcel0) {
                Parcelable parcelable0 = parcel0.readParcelable(Month.class.getClassLoader());
                Parcelable parcelable1 = parcel0.readParcelable(Month.class.getClassLoader());
                Parcelable parcelable2 = parcel0.readParcelable(Month.class.getClassLoader());
                return new CalendarConstraints(((Month)parcelable0), ((Month)parcelable1), ((DateValidator)parcel0.readParcelable(DateValidator.class.getClassLoader())), ((Month)parcelable2), parcel0.readInt(), null);
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public CalendarConstraints[] newArray(int v) {
                return new CalendarConstraints[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    private CalendarConstraints(Month month0, Month month1, DateValidator calendarConstraints$DateValidator0, Month month2, int v) {
        Objects.requireNonNull(month0, "start cannot be null");
        Objects.requireNonNull(month1, "end cannot be null");
        Objects.requireNonNull(calendarConstraints$DateValidator0, "validator cannot be null");
        this.start = month0;
        this.end = month1;
        this.openAt = month2;
        this.firstDayOfWeek = v;
        this.validator = calendarConstraints$DateValidator0;
        if(month2 != null && month0.compareTo(month2) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if(month2 != null && month2.compareTo(month1) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        if(v < 0 || v > UtcDates.getUtcCalendar().getMaximum(7)) {
            throw new IllegalArgumentException("firstDayOfWeek is not valid");
        }
        this.monthSpan = month0.monthsUntil(month1) + 1;
        this.yearSpan = month1.year - month0.year + 1;
    }

    CalendarConstraints(Month month0, Month month1, DateValidator calendarConstraints$DateValidator0, Month month2, int v, com.google.android.material.datepicker.CalendarConstraints.1 calendarConstraints$10) {
        this(month0, month1, calendarConstraints$DateValidator0, month2, v);
    }

    Month clamp(Month month0) {
        if(month0.compareTo(this.start) < 0) {
            return this.start;
        }
        return month0.compareTo(this.end) <= 0 ? month0 : this.end;
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
        return object0 instanceof CalendarConstraints ? this.start.equals(((CalendarConstraints)object0).start) && this.end.equals(((CalendarConstraints)object0).end) && ObjectsCompat.equals(this.openAt, ((CalendarConstraints)object0).openAt) && this.firstDayOfWeek == ((CalendarConstraints)object0).firstDayOfWeek && this.validator.equals(((CalendarConstraints)object0).validator) : false;
    }

    public DateValidator getDateValidator() {
        return this.validator;
    }

    Month getEnd() {
        return this.end;
    }

    public long getEndMs() {
        return this.end.timeInMillis;
    }

    int getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    int getMonthSpan() {
        return this.monthSpan;
    }

    Month getOpenAt() {
        return this.openAt;
    }

    public Long getOpenAtMs() {
        return this.openAt == null ? null : this.openAt.timeInMillis;
    }

    Month getStart() {
        return this.start;
    }

    public long getStartMs() {
        return this.start.timeInMillis;
    }

    int getYearSpan() {
        return this.yearSpan;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.start, this.end, this.openAt, this.firstDayOfWeek, this.validator});
    }

    boolean isWithinBounds(long v) {
        return this.start.getDay(1) <= v && v <= this.end.getDay(this.end.daysInMonth);
    }

    void setOpenAt(Month month0) {
        this.openAt = month0;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeParcelable(this.start, 0);
        parcel0.writeParcelable(this.end, 0);
        parcel0.writeParcelable(this.openAt, 0);
        parcel0.writeParcelable(this.validator, 0);
        parcel0.writeInt(this.firstDayOfWeek);
    }
}

