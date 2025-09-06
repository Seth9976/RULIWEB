package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.List;

public final class CompositeDateValidator implements DateValidator {
    interface Operator {
        int getId();

        boolean isValid(List arg1, long arg2);
    }

    private static final Operator ALL_OPERATOR = null;
    private static final Operator ANY_OPERATOR = null;
    private static final int COMPARATOR_ALL_ID = 2;
    private static final int COMPARATOR_ANY_ID = 1;
    public static final Parcelable.Creator CREATOR;
    private final Operator operator;
    private final List validators;

    static {
        CompositeDateValidator.ANY_OPERATOR = new Operator() {
            @Override  // com.google.android.material.datepicker.CompositeDateValidator$Operator
            public int getId() {
                return 1;
            }

            @Override  // com.google.android.material.datepicker.CompositeDateValidator$Operator
            public boolean isValid(List list0, long v) {
                for(Object object0: list0) {
                    if(((DateValidator)object0) != null && ((DateValidator)object0).isValid(v)) {
                        return true;
                    }
                    if(false) {
                        break;
                    }
                }
                return false;
            }
        };
        CompositeDateValidator.ALL_OPERATOR = new Operator() {
            @Override  // com.google.android.material.datepicker.CompositeDateValidator$Operator
            public int getId() {
                return 2;
            }

            @Override  // com.google.android.material.datepicker.CompositeDateValidator$Operator
            public boolean isValid(List list0, long v) {
                for(Object object0: list0) {
                    if(((DateValidator)object0) != null && !((DateValidator)object0).isValid(v)) {
                        return false;
                    }
                    if(false) {
                        break;
                    }
                }
                return true;
            }
        };
        CompositeDateValidator.CREATOR = new Parcelable.Creator() {
            public CompositeDateValidator createFromParcel(Parcel parcel0) {
                ArrayList arrayList0 = parcel0.readArrayList(DateValidator.class.getClassLoader());
                int v = parcel0.readInt();
                if(v == 2) {
                    return new CompositeDateValidator(((List)Preconditions.checkNotNull(arrayList0)), CompositeDateValidator.ALL_OPERATOR, null);
                }
                return v == 1 ? new CompositeDateValidator(((List)Preconditions.checkNotNull(arrayList0)), CompositeDateValidator.ANY_OPERATOR, null) : new CompositeDateValidator(((List)Preconditions.checkNotNull(arrayList0)), CompositeDateValidator.ALL_OPERATOR, null);
            }

            @Override  // android.os.Parcelable$Creator
            public Object createFromParcel(Parcel parcel0) {
                return this.createFromParcel(parcel0);
            }

            public CompositeDateValidator[] newArray(int v) {
                return new CompositeDateValidator[v];
            }

            @Override  // android.os.Parcelable$Creator
            public Object[] newArray(int v) {
                return this.newArray(v);
            }
        };
    }

    private CompositeDateValidator(List list0, Operator compositeDateValidator$Operator0) {
        this.validators = list0;
        this.operator = compositeDateValidator$Operator0;
    }

    CompositeDateValidator(List list0, Operator compositeDateValidator$Operator0, com.google.android.material.datepicker.CompositeDateValidator.1 compositeDateValidator$10) {
        this(list0, compositeDateValidator$Operator0);
    }

    public static DateValidator allOf(List list0) {
        return new CompositeDateValidator(list0, CompositeDateValidator.ALL_OPERATOR);
    }

    public static DateValidator anyOf(List list0) {
        return new CompositeDateValidator(list0, CompositeDateValidator.ANY_OPERATOR);
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
        return object0 instanceof CompositeDateValidator ? this.validators.equals(((CompositeDateValidator)object0).validators) && this.operator.getId() == ((CompositeDateValidator)object0).operator.getId() : false;
    }

    @Override
    public int hashCode() {
        return this.validators.hashCode();
    }

    @Override  // com.google.android.material.datepicker.CalendarConstraints$DateValidator
    public boolean isValid(long v) {
        return this.operator.isValid(this.validators, v);
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeList(this.validators);
        parcel0.writeInt(this.operator.getId());
    }
}

