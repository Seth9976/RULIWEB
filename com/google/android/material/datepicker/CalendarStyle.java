package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import com.google.android.material.R.attr;
import com.google.android.material.R.styleable;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;

final class CalendarStyle {
    final CalendarItemStyle day;
    final CalendarItemStyle invalidDay;
    final Paint rangeFill;
    final CalendarItemStyle selectedDay;
    final CalendarItemStyle selectedYear;
    final CalendarItemStyle todayDay;
    final CalendarItemStyle todayYear;
    final CalendarItemStyle year;

    CalendarStyle(Context context0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(MaterialAttributes.resolveOrThrow(context0, attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), styleable.MaterialCalendar);
        this.day = CalendarItemStyle.create(context0, typedArray0.getResourceId(styleable.MaterialCalendar_dayStyle, 0));
        this.invalidDay = CalendarItemStyle.create(context0, typedArray0.getResourceId(styleable.MaterialCalendar_dayInvalidStyle, 0));
        this.selectedDay = CalendarItemStyle.create(context0, typedArray0.getResourceId(styleable.MaterialCalendar_daySelectedStyle, 0));
        this.todayDay = CalendarItemStyle.create(context0, typedArray0.getResourceId(styleable.MaterialCalendar_dayTodayStyle, 0));
        ColorStateList colorStateList0 = MaterialResources.getColorStateList(context0, typedArray0, styleable.MaterialCalendar_rangeFillColor);
        this.year = CalendarItemStyle.create(context0, typedArray0.getResourceId(styleable.MaterialCalendar_yearStyle, 0));
        this.selectedYear = CalendarItemStyle.create(context0, typedArray0.getResourceId(styleable.MaterialCalendar_yearSelectedStyle, 0));
        this.todayYear = CalendarItemStyle.create(context0, typedArray0.getResourceId(styleable.MaterialCalendar_yearTodayStyle, 0));
        Paint paint0 = new Paint();
        this.rangeFill = paint0;
        paint0.setColor(colorStateList0.getDefaultColor());
        typedArray0.recycle();
    }
}

