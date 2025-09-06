package com.google.android.material.timepicker;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import com.google.android.material.R.string;
import java.util.Arrays;

class TimeModel implements Parcelable {
    public static final Parcelable.Creator CREATOR = null;
    public static final String NUMBER_FORMAT = "%d";
    public static final String ZERO_LEADING_NUMBER_FORMAT = "%02d";
    final int format;
    int hour;
    private final MaxInputValidator hourInputValidator;
    int minute;
    private final MaxInputValidator minuteInputValidator;
    int period;
    int selection;

    static {
        TimeModel.CREATOR = new Parcelable.Creator() {
            public TimeModel createFromParcel(Parcel parcel0) {
                return new TimeModel(parcel0);
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public TimeModel[] newArray(int v) {
                return new TimeModel[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    public TimeModel() {
        this(0);
    }

    public TimeModel(int v) {
        this(0, 0, 10, v);
    }

    public TimeModel(int v, int v1, int v2, int v3) {
        this.hour = v;
        this.minute = v1;
        this.selection = v2;
        this.format = v3;
        this.period = TimeModel.getPeriod(v);
        this.minuteInputValidator = new MaxInputValidator(59);
        this.hourInputValidator = new MaxInputValidator((v3 == 1 ? 23 : 12));
    }

    protected TimeModel(Parcel parcel0) {
        this(parcel0.readInt(), parcel0.readInt(), parcel0.readInt(), parcel0.readInt());
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
        return object0 instanceof TimeModel ? this.hour == ((TimeModel)object0).hour && this.minute == ((TimeModel)object0).minute && this.format == ((TimeModel)object0).format && this.selection == ((TimeModel)object0).selection : false;
    }

    public static String formatText(Resources resources0, CharSequence charSequence0) {
        return TimeModel.formatText(resources0, charSequence0, "%02d");
    }

    public static String formatText(Resources resources0, CharSequence charSequence0, String s) {
        try {
            return String.format(resources0.getConfiguration().locale, s, Integer.parseInt(String.valueOf(charSequence0)));
        }
        catch(NumberFormatException unused_ex) {
            return null;
        }
    }

    public int getHourContentDescriptionResId() {
        return this.format == 1 ? string.material_hour_24h_suffix : string.material_hour_suffix;
    }

    public int getHourForDisplay() {
        if(this.format == 1) {
            return this.hour % 24;
        }
        int v = this.hour;
        if(v % 12 == 0) {
            return 12;
        }
        return this.period == 1 ? v - 12 : v;
    }

    public MaxInputValidator getHourInputValidator() {
        return this.hourInputValidator;
    }

    public MaxInputValidator getMinuteInputValidator() {
        return this.minuteInputValidator;
    }

    private static int getPeriod(int v) {
        return v < 12 ? 0 : 1;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.format, this.hour, this.minute, this.selection});
    }

    public void setHour(int v) {
        int v1 = 12;
        if(this.format == 1) {
            this.hour = v;
            return;
        }
        if(this.period != 1) {
            v1 = 0;
        }
        this.hour = v % 12 + v1;
    }

    public void setHourOfDay(int v) {
        this.period = TimeModel.getPeriod(v);
        this.hour = v;
    }

    public void setMinute(int v) {
        this.minute = v % 60;
    }

    public void setPeriod(int v) {
        if(v != this.period) {
            this.period = v;
            int v1 = this.hour;
            if(v1 < 12 && v == 1) {
                this.hour = v1 + 12;
                return;
            }
            if(v1 >= 12 && v == 0) {
                this.hour = v1 - 12;
            }
        }
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeInt(this.hour);
        parcel0.writeInt(this.minute);
        parcel0.writeInt(this.selection);
        parcel0.writeInt(this.format);
    }
}

