package com.google.android.material.badge;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.material.R.dimen;
import com.google.android.material.R.plurals;
import com.google.android.material.R.string;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import java.util.Locale.Category;
import java.util.Locale;

public final class BadgeState {
    public static final class State implements Parcelable {
        private static final int BADGE_NUMBER_NONE = -1;
        public static final Parcelable.Creator CREATOR = null;
        private static final int NOT_SET = -2;
        private Integer additionalHorizontalOffset;
        private Integer additionalVerticalOffset;
        private int alpha;
        private Boolean autoAdjustToWithinGrandparentBounds;
        private Integer backgroundColor;
        private Integer badgeGravity;
        private Integer badgeHorizontalPadding;
        private int badgeResId;
        private Integer badgeShapeAppearanceOverlayResId;
        private Integer badgeShapeAppearanceResId;
        private Integer badgeTextAppearanceResId;
        private Integer badgeTextColor;
        private Integer badgeVerticalPadding;
        private Integer badgeWithTextShapeAppearanceOverlayResId;
        private Integer badgeWithTextShapeAppearanceResId;
        private int contentDescriptionExceedsMaxBadgeNumberRes;
        private CharSequence contentDescriptionForText;
        private CharSequence contentDescriptionNumberless;
        private int contentDescriptionQuantityStrings;
        private Integer horizontalOffsetWithText;
        private Integer horizontalOffsetWithoutText;
        private Boolean isVisible;
        private Integer largeFontVerticalOffsetAdjustment;
        private int maxCharacterCount;
        private int maxNumber;
        private int number;
        private Locale numberLocale;
        private String text;
        private Integer verticalOffsetWithText;
        private Integer verticalOffsetWithoutText;

        static {
            State.CREATOR = new Parcelable.Creator() {
                public State createFromParcel(Parcel parcel0) {
                    return new State(parcel0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                public State[] newArray(int v) {
                    return new State[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        public State() {
            this.alpha = 0xFF;
            this.number = -2;
            this.maxCharacterCount = -2;
            this.maxNumber = -2;
            this.isVisible = Boolean.TRUE;
        }

        State(Parcel parcel0) {
            this.alpha = 0xFF;
            this.number = -2;
            this.maxCharacterCount = -2;
            this.maxNumber = -2;
            this.isVisible = Boolean.TRUE;
            this.badgeResId = parcel0.readInt();
            this.backgroundColor = (Integer)parcel0.readSerializable();
            this.badgeTextColor = (Integer)parcel0.readSerializable();
            this.badgeTextAppearanceResId = (Integer)parcel0.readSerializable();
            this.badgeShapeAppearanceResId = (Integer)parcel0.readSerializable();
            this.badgeShapeAppearanceOverlayResId = (Integer)parcel0.readSerializable();
            this.badgeWithTextShapeAppearanceResId = (Integer)parcel0.readSerializable();
            this.badgeWithTextShapeAppearanceOverlayResId = (Integer)parcel0.readSerializable();
            this.alpha = parcel0.readInt();
            this.text = parcel0.readString();
            this.number = parcel0.readInt();
            this.maxCharacterCount = parcel0.readInt();
            this.maxNumber = parcel0.readInt();
            this.contentDescriptionForText = parcel0.readString();
            this.contentDescriptionNumberless = parcel0.readString();
            this.contentDescriptionQuantityStrings = parcel0.readInt();
            this.badgeGravity = (Integer)parcel0.readSerializable();
            this.badgeHorizontalPadding = (Integer)parcel0.readSerializable();
            this.badgeVerticalPadding = (Integer)parcel0.readSerializable();
            this.horizontalOffsetWithoutText = (Integer)parcel0.readSerializable();
            this.verticalOffsetWithoutText = (Integer)parcel0.readSerializable();
            this.horizontalOffsetWithText = (Integer)parcel0.readSerializable();
            this.verticalOffsetWithText = (Integer)parcel0.readSerializable();
            this.largeFontVerticalOffsetAdjustment = (Integer)parcel0.readSerializable();
            this.additionalHorizontalOffset = (Integer)parcel0.readSerializable();
            this.additionalVerticalOffset = (Integer)parcel0.readSerializable();
            this.isVisible = (Boolean)parcel0.readSerializable();
            this.numberLocale = (Locale)parcel0.readSerializable();
            this.autoAdjustToWithinGrandparentBounds = (Boolean)parcel0.readSerializable();
        }

        static int access$000(State badgeState$State0) {
            return badgeState$State0.badgeResId;
        }

        static int access$002(State badgeState$State0, int v) {
            badgeState$State0.badgeResId = v;
            return v;
        }

        static int access$100(State badgeState$State0) {
            return badgeState$State0.alpha;
        }

        static int access$1000(State badgeState$State0) {
            return badgeState$State0.maxNumber;
        }

        static int access$1002(State badgeState$State0, int v) {
            badgeState$State0.maxNumber = v;
            return v;
        }

        static int access$102(State badgeState$State0, int v) {
            badgeState$State0.alpha = v;
            return v;
        }

        static Integer access$1100(State badgeState$State0) {
            return badgeState$State0.badgeShapeAppearanceResId;
        }

        static Integer access$1102(State badgeState$State0, Integer integer0) {
            badgeState$State0.badgeShapeAppearanceResId = integer0;
            return integer0;
        }

        static Integer access$1200(State badgeState$State0) {
            return badgeState$State0.badgeShapeAppearanceOverlayResId;
        }

        static Integer access$1202(State badgeState$State0, Integer integer0) {
            badgeState$State0.badgeShapeAppearanceOverlayResId = integer0;
            return integer0;
        }

        static Integer access$1300(State badgeState$State0) {
            return badgeState$State0.badgeWithTextShapeAppearanceResId;
        }

        static Integer access$1302(State badgeState$State0, Integer integer0) {
            badgeState$State0.badgeWithTextShapeAppearanceResId = integer0;
            return integer0;
        }

        static Integer access$1400(State badgeState$State0) {
            return badgeState$State0.badgeWithTextShapeAppearanceOverlayResId;
        }

        static Integer access$1402(State badgeState$State0, Integer integer0) {
            badgeState$State0.badgeWithTextShapeAppearanceOverlayResId = integer0;
            return integer0;
        }

        static Integer access$1500(State badgeState$State0) {
            return badgeState$State0.backgroundColor;
        }

        static Integer access$1502(State badgeState$State0, Integer integer0) {
            badgeState$State0.backgroundColor = integer0;
            return integer0;
        }

        static Integer access$1600(State badgeState$State0) {
            return badgeState$State0.badgeTextAppearanceResId;
        }

        static Integer access$1602(State badgeState$State0, Integer integer0) {
            badgeState$State0.badgeTextAppearanceResId = integer0;
            return integer0;
        }

        static Integer access$1700(State badgeState$State0) {
            return badgeState$State0.badgeTextColor;
        }

        static Integer access$1702(State badgeState$State0, Integer integer0) {
            badgeState$State0.badgeTextColor = integer0;
            return integer0;
        }

        static Integer access$1800(State badgeState$State0) {
            return badgeState$State0.badgeGravity;
        }

        static Integer access$1802(State badgeState$State0, Integer integer0) {
            badgeState$State0.badgeGravity = integer0;
            return integer0;
        }

        static Integer access$1900(State badgeState$State0) {
            return badgeState$State0.badgeHorizontalPadding;
        }

        static Integer access$1902(State badgeState$State0, Integer integer0) {
            badgeState$State0.badgeHorizontalPadding = integer0;
            return integer0;
        }

        static int access$200(State badgeState$State0) {
            return badgeState$State0.number;
        }

        static Integer access$2000(State badgeState$State0) {
            return badgeState$State0.badgeVerticalPadding;
        }

        static Integer access$2002(State badgeState$State0, Integer integer0) {
            badgeState$State0.badgeVerticalPadding = integer0;
            return integer0;
        }

        static int access$202(State badgeState$State0, int v) {
            badgeState$State0.number = v;
            return v;
        }

        static Integer access$2100(State badgeState$State0) {
            return badgeState$State0.horizontalOffsetWithoutText;
        }

        static Integer access$2102(State badgeState$State0, Integer integer0) {
            badgeState$State0.horizontalOffsetWithoutText = integer0;
            return integer0;
        }

        static Integer access$2200(State badgeState$State0) {
            return badgeState$State0.verticalOffsetWithoutText;
        }

        static Integer access$2202(State badgeState$State0, Integer integer0) {
            badgeState$State0.verticalOffsetWithoutText = integer0;
            return integer0;
        }

        static Integer access$2300(State badgeState$State0) {
            return badgeState$State0.horizontalOffsetWithText;
        }

        static Integer access$2302(State badgeState$State0, Integer integer0) {
            badgeState$State0.horizontalOffsetWithText = integer0;
            return integer0;
        }

        static Integer access$2400(State badgeState$State0) {
            return badgeState$State0.verticalOffsetWithText;
        }

        static Integer access$2402(State badgeState$State0, Integer integer0) {
            badgeState$State0.verticalOffsetWithText = integer0;
            return integer0;
        }

        static Integer access$2500(State badgeState$State0) {
            return badgeState$State0.largeFontVerticalOffsetAdjustment;
        }

        static Integer access$2502(State badgeState$State0, Integer integer0) {
            badgeState$State0.largeFontVerticalOffsetAdjustment = integer0;
            return integer0;
        }

        static Integer access$2600(State badgeState$State0) {
            return badgeState$State0.additionalHorizontalOffset;
        }

        static Integer access$2602(State badgeState$State0, Integer integer0) {
            badgeState$State0.additionalHorizontalOffset = integer0;
            return integer0;
        }

        static Integer access$2700(State badgeState$State0) {
            return badgeState$State0.additionalVerticalOffset;
        }

        static Integer access$2702(State badgeState$State0, Integer integer0) {
            badgeState$State0.additionalVerticalOffset = integer0;
            return integer0;
        }

        static Boolean access$2800(State badgeState$State0) {
            return badgeState$State0.autoAdjustToWithinGrandparentBounds;
        }

        static Boolean access$2802(State badgeState$State0, Boolean boolean0) {
            badgeState$State0.autoAdjustToWithinGrandparentBounds = boolean0;
            return boolean0;
        }

        static Locale access$2900(State badgeState$State0) {
            return badgeState$State0.numberLocale;
        }

        static Locale access$2902(State badgeState$State0, Locale locale0) {
            badgeState$State0.numberLocale = locale0;
            return locale0;
        }

        static String access$300(State badgeState$State0) {
            return badgeState$State0.text;
        }

        static String access$302(State badgeState$State0, String s) {
            badgeState$State0.text = s;
            return s;
        }

        static CharSequence access$400(State badgeState$State0) {
            return badgeState$State0.contentDescriptionForText;
        }

        static CharSequence access$402(State badgeState$State0, CharSequence charSequence0) {
            badgeState$State0.contentDescriptionForText = charSequence0;
            return charSequence0;
        }

        static CharSequence access$500(State badgeState$State0) {
            return badgeState$State0.contentDescriptionNumberless;
        }

        static CharSequence access$502(State badgeState$State0, CharSequence charSequence0) {
            badgeState$State0.contentDescriptionNumberless = charSequence0;
            return charSequence0;
        }

        static int access$600(State badgeState$State0) {
            return badgeState$State0.contentDescriptionQuantityStrings;
        }

        static int access$602(State badgeState$State0, int v) {
            badgeState$State0.contentDescriptionQuantityStrings = v;
            return v;
        }

        static int access$700(State badgeState$State0) {
            return badgeState$State0.contentDescriptionExceedsMaxBadgeNumberRes;
        }

        static int access$702(State badgeState$State0, int v) {
            badgeState$State0.contentDescriptionExceedsMaxBadgeNumberRes = v;
            return v;
        }

        static Boolean access$800(State badgeState$State0) {
            return badgeState$State0.isVisible;
        }

        static Boolean access$802(State badgeState$State0, Boolean boolean0) {
            badgeState$State0.isVisible = boolean0;
            return boolean0;
        }

        static int access$900(State badgeState$State0) {
            return badgeState$State0.maxCharacterCount;
        }

        static int access$902(State badgeState$State0, int v) {
            badgeState$State0.maxCharacterCount = v;
            return v;
        }

        @Override  // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override  // android.os.Parcelable
        public void writeToParcel(Parcel parcel0, int v) {
            parcel0.writeInt(this.badgeResId);
            parcel0.writeSerializable(this.backgroundColor);
            parcel0.writeSerializable(this.badgeTextColor);
            parcel0.writeSerializable(this.badgeTextAppearanceResId);
            parcel0.writeSerializable(this.badgeShapeAppearanceResId);
            parcel0.writeSerializable(this.badgeShapeAppearanceOverlayResId);
            parcel0.writeSerializable(this.badgeWithTextShapeAppearanceResId);
            parcel0.writeSerializable(this.badgeWithTextShapeAppearanceOverlayResId);
            parcel0.writeInt(this.alpha);
            parcel0.writeString(this.text);
            parcel0.writeInt(this.number);
            parcel0.writeInt(this.maxCharacterCount);
            parcel0.writeInt(this.maxNumber);
            String s = null;
            parcel0.writeString((this.contentDescriptionForText == null ? null : this.contentDescriptionForText.toString()));
            CharSequence charSequence0 = this.contentDescriptionNumberless;
            if(charSequence0 != null) {
                s = charSequence0.toString();
            }
            parcel0.writeString(s);
            parcel0.writeInt(this.contentDescriptionQuantityStrings);
            parcel0.writeSerializable(this.badgeGravity);
            parcel0.writeSerializable(this.badgeHorizontalPadding);
            parcel0.writeSerializable(this.badgeVerticalPadding);
            parcel0.writeSerializable(this.horizontalOffsetWithoutText);
            parcel0.writeSerializable(this.verticalOffsetWithoutText);
            parcel0.writeSerializable(this.horizontalOffsetWithText);
            parcel0.writeSerializable(this.verticalOffsetWithText);
            parcel0.writeSerializable(this.largeFontVerticalOffsetAdjustment);
            parcel0.writeSerializable(this.additionalHorizontalOffset);
            parcel0.writeSerializable(this.additionalVerticalOffset);
            parcel0.writeSerializable(this.isVisible);
            parcel0.writeSerializable(this.numberLocale);
            parcel0.writeSerializable(this.autoAdjustToWithinGrandparentBounds);
        }
    }

    private static final String BADGE_RESOURCE_TAG = "badge";
    final float badgeHeight;
    final float badgeRadius;
    final float badgeWidth;
    final float badgeWithTextHeight;
    final float badgeWithTextRadius;
    final float badgeWithTextWidth;
    private final State currentState;
    final int horizontalInset;
    final int horizontalInsetWithText;
    int offsetAlignmentMode;
    private final State overridingState;

    BadgeState(Context context0, int v, int v1, int v2, State badgeState$State0) {
        int v6;
        int v4;
        State badgeState$State1 = new State();
        this.currentState = badgeState$State1;
        if(badgeState$State0 == null) {
            badgeState$State0 = new State();
        }
        if(v != 0) {
            State.access$002(badgeState$State0, v);
        }
        TypedArray typedArray0 = this.generateTypedArray(context0, State.access$000(badgeState$State0), v1, v2);
        Resources resources0 = context0.getResources();
        this.badgeRadius = (float)typedArray0.getDimensionPixelSize(styleable.Badge_badgeRadius, -1);
        this.horizontalInset = context0.getResources().getDimensionPixelSize(dimen.mtrl_badge_horizontal_edge_offset);
        this.horizontalInsetWithText = context0.getResources().getDimensionPixelSize(dimen.mtrl_badge_text_horizontal_edge_offset);
        this.badgeWithTextRadius = (float)typedArray0.getDimensionPixelSize(styleable.Badge_badgeWithTextRadius, -1);
        float f = resources0.getDimension(dimen.m3_badge_size);
        this.badgeWidth = typedArray0.getDimension(styleable.Badge_badgeWidth, f);
        float f1 = resources0.getDimension(dimen.m3_badge_with_text_size);
        this.badgeWithTextWidth = typedArray0.getDimension(styleable.Badge_badgeWithTextWidth, f1);
        float f2 = resources0.getDimension(dimen.m3_badge_size);
        this.badgeHeight = typedArray0.getDimension(styleable.Badge_badgeHeight, f2);
        float f3 = resources0.getDimension(dimen.m3_badge_with_text_size);
        this.badgeWithTextHeight = typedArray0.getDimension(styleable.Badge_badgeWithTextHeight, f3);
        boolean z = true;
        this.offsetAlignmentMode = typedArray0.getInt(styleable.Badge_offsetAlignmentMode, 1);
        State.access$102(badgeState$State1, (State.access$100(badgeState$State0) == -2 ? 0xFF : State.access$100(badgeState$State0)));
        if(State.access$200(badgeState$State0) != -2) {
            State.access$202(badgeState$State1, State.access$200(badgeState$State0));
        }
        else if(typedArray0.hasValue(styleable.Badge_number)) {
            State.access$202(badgeState$State1, typedArray0.getInt(styleable.Badge_number, 0));
        }
        else {
            State.access$202(badgeState$State1, -1);
        }
        if(State.access$300(badgeState$State0) != null) {
            State.access$302(badgeState$State1, State.access$300(badgeState$State0));
        }
        else if(typedArray0.hasValue(styleable.Badge_badgeText)) {
            State.access$302(badgeState$State1, typedArray0.getString(styleable.Badge_badgeText));
        }
        State.access$402(badgeState$State1, State.access$400(badgeState$State0));
        CharSequence charSequence0 = State.access$500(badgeState$State0) == null ? context0.getString(string.mtrl_badge_numberless_content_description) : State.access$500(badgeState$State0);
        State.access$502(badgeState$State1, charSequence0);
        State.access$602(badgeState$State1, (State.access$600(badgeState$State0) == 0 ? plurals.mtrl_badge_content_description : State.access$600(badgeState$State0)));
        State.access$702(badgeState$State1, (State.access$700(badgeState$State0) == 0 ? string.mtrl_exceed_max_badge_number_content_description : State.access$700(badgeState$State0)));
        if(State.access$800(badgeState$State0) != null && !State.access$800(badgeState$State0).booleanValue()) {
            z = false;
        }
        State.access$802(badgeState$State1, Boolean.valueOf(z));
        State.access$902(badgeState$State1, (State.access$900(badgeState$State0) == -2 ? typedArray0.getInt(styleable.Badge_maxCharacterCount, -2) : State.access$900(badgeState$State0)));
        State.access$1002(badgeState$State1, (State.access$1000(badgeState$State0) == -2 ? typedArray0.getInt(styleable.Badge_maxNumber, -2) : State.access$1000(badgeState$State0)));
        State.access$1102(badgeState$State1, ((int)(State.access$1100(badgeState$State0) == null ? typedArray0.getResourceId(styleable.Badge_badgeShapeAppearance, style.ShapeAppearance_M3_Sys_Shape_Corner_Full) : ((int)State.access$1100(badgeState$State0)))));
        State.access$1202(badgeState$State1, ((int)(State.access$1200(badgeState$State0) == null ? typedArray0.getResourceId(styleable.Badge_badgeShapeAppearanceOverlay, 0) : ((int)State.access$1200(badgeState$State0)))));
        State.access$1302(badgeState$State1, ((int)(State.access$1300(badgeState$State0) == null ? typedArray0.getResourceId(styleable.Badge_badgeWithTextShapeAppearance, style.ShapeAppearance_M3_Sys_Shape_Corner_Full) : ((int)State.access$1300(badgeState$State0)))));
        State.access$1402(badgeState$State1, ((int)(State.access$1400(badgeState$State0) == null ? typedArray0.getResourceId(styleable.Badge_badgeWithTextShapeAppearanceOverlay, 0) : ((int)State.access$1400(badgeState$State0)))));
        State.access$1502(badgeState$State1, ((int)(State.access$1500(badgeState$State0) == null ? BadgeState.readColorFromAttributes(context0, typedArray0, styleable.Badge_backgroundColor) : ((int)State.access$1500(badgeState$State0)))));
        State.access$1602(badgeState$State1, ((int)(State.access$1600(badgeState$State0) == null ? typedArray0.getResourceId(styleable.Badge_badgeTextAppearance, style.TextAppearance_MaterialComponents_Badge) : ((int)State.access$1600(badgeState$State0)))));
        if(State.access$1700(badgeState$State0) != null) {
            State.access$1702(badgeState$State1, State.access$1700(badgeState$State0));
        }
        else if(typedArray0.hasValue(styleable.Badge_badgeTextColor)) {
            State.access$1702(badgeState$State1, BadgeState.readColorFromAttributes(context0, typedArray0, styleable.Badge_badgeTextColor));
        }
        else {
            State.access$1702(badgeState$State1, new TextAppearance(context0, ((int)State.access$1600(badgeState$State1))).getTextColor().getDefaultColor());
        }
        State.access$1802(badgeState$State1, ((int)(State.access$1800(badgeState$State0) == null ? typedArray0.getInt(styleable.Badge_badgeGravity, 0x800035) : ((int)State.access$1800(badgeState$State0)))));
        if(State.access$1900(badgeState$State0) == null) {
            int v3 = resources0.getDimensionPixelSize(dimen.mtrl_badge_long_text_horizontal_padding);
            v4 = typedArray0.getDimensionPixelSize(styleable.Badge_badgeWidePadding, v3);
        }
        else {
            v4 = (int)State.access$1900(badgeState$State0);
        }
        State.access$1902(badgeState$State1, v4);
        if(State.access$2000(badgeState$State0) == null) {
            int v5 = resources0.getDimensionPixelSize(dimen.m3_badge_with_text_vertical_padding);
            v6 = typedArray0.getDimensionPixelSize(styleable.Badge_badgeVerticalPadding, v5);
        }
        else {
            v6 = (int)State.access$2000(badgeState$State0);
        }
        State.access$2002(badgeState$State1, v6);
        State.access$2102(badgeState$State1, ((int)(State.access$2100(badgeState$State0) == null ? typedArray0.getDimensionPixelOffset(styleable.Badge_horizontalOffset, 0) : ((int)State.access$2100(badgeState$State0)))));
        State.access$2202(badgeState$State1, ((int)(State.access$2200(badgeState$State0) == null ? typedArray0.getDimensionPixelOffset(styleable.Badge_verticalOffset, 0) : ((int)State.access$2200(badgeState$State0)))));
        State.access$2302(badgeState$State1, ((int)(State.access$2300(badgeState$State0) == null ? typedArray0.getDimensionPixelOffset(styleable.Badge_horizontalOffsetWithText, ((int)State.access$2100(badgeState$State1))) : ((int)State.access$2300(badgeState$State0)))));
        State.access$2402(badgeState$State1, ((int)(State.access$2400(badgeState$State0) == null ? typedArray0.getDimensionPixelOffset(styleable.Badge_verticalOffsetWithText, ((int)State.access$2200(badgeState$State1))) : ((int)State.access$2400(badgeState$State0)))));
        State.access$2502(badgeState$State1, ((int)(State.access$2500(badgeState$State0) == null ? typedArray0.getDimensionPixelOffset(styleable.Badge_largeFontVerticalOffsetAdjustment, 0) : ((int)State.access$2500(badgeState$State0)))));
        State.access$2602(badgeState$State1, ((int)(State.access$2600(badgeState$State0) == null ? 0 : ((int)State.access$2600(badgeState$State0)))));
        State.access$2702(badgeState$State1, ((int)(State.access$2700(badgeState$State0) == null ? 0 : ((int)State.access$2700(badgeState$State0)))));
        State.access$2802(badgeState$State1, Boolean.valueOf((State.access$2800(badgeState$State0) == null ? typedArray0.getBoolean(styleable.Badge_autoAdjustToWithinGrandparentBounds, false) : State.access$2800(badgeState$State0).booleanValue())));
        typedArray0.recycle();
        if(State.access$2900(badgeState$State0) == null) {
            State.access$2902(badgeState$State1, (Build.VERSION.SDK_INT < 24 ? Locale.getDefault() : Locale.getDefault(Locale.Category.FORMAT)));
        }
        else {
            State.access$2902(badgeState$State1, State.access$2900(badgeState$State0));
        }
        this.overridingState = badgeState$State0;
    }

    void clearNumber() {
        this.setNumber(-1);
    }

    void clearText() {
        this.setText(null);
    }

    private TypedArray generateTypedArray(Context context0, int v, int v1, int v2) {
        if(v != 0) {
            AttributeSet attributeSet0 = DrawableUtils.parseDrawableXml(context0, v, "badge");
            int v3 = attributeSet0.getStyleAttribute();
            return v3 == 0 ? ThemeEnforcement.obtainStyledAttributes(context0, attributeSet0, styleable.Badge, v1, v2, new int[0]) : ThemeEnforcement.obtainStyledAttributes(context0, attributeSet0, styleable.Badge, v1, v3, new int[0]);
        }
        return ThemeEnforcement.obtainStyledAttributes(context0, null, styleable.Badge, v1, v2, new int[0]);
    }

    int getAdditionalHorizontalOffset() {
        return (int)State.access$2600(this.currentState);
    }

    int getAdditionalVerticalOffset() {
        return (int)State.access$2700(this.currentState);
    }

    int getAlpha() {
        return State.access$100(this.currentState);
    }

    int getBackgroundColor() {
        return (int)State.access$1500(this.currentState);
    }

    int getBadgeGravity() {
        return (int)State.access$1800(this.currentState);
    }

    int getBadgeHorizontalPadding() {
        return (int)State.access$1900(this.currentState);
    }

    int getBadgeShapeAppearanceOverlayResId() {
        return (int)State.access$1200(this.currentState);
    }

    int getBadgeShapeAppearanceResId() {
        return (int)State.access$1100(this.currentState);
    }

    int getBadgeTextColor() {
        return (int)State.access$1700(this.currentState);
    }

    int getBadgeVerticalPadding() {
        return (int)State.access$2000(this.currentState);
    }

    int getBadgeWithTextShapeAppearanceOverlayResId() {
        return (int)State.access$1400(this.currentState);
    }

    int getBadgeWithTextShapeAppearanceResId() {
        return (int)State.access$1300(this.currentState);
    }

    int getContentDescriptionExceedsMaxBadgeNumberStringResource() {
        return State.access$700(this.currentState);
    }

    CharSequence getContentDescriptionForText() {
        return State.access$400(this.currentState);
    }

    CharSequence getContentDescriptionNumberless() {
        return State.access$500(this.currentState);
    }

    int getContentDescriptionQuantityStrings() {
        return State.access$600(this.currentState);
    }

    int getHorizontalOffsetWithText() {
        return (int)State.access$2300(this.currentState);
    }

    int getHorizontalOffsetWithoutText() {
        return (int)State.access$2100(this.currentState);
    }

    int getLargeFontVerticalOffsetAdjustment() {
        return (int)State.access$2500(this.currentState);
    }

    int getMaxCharacterCount() {
        return State.access$900(this.currentState);
    }

    int getMaxNumber() {
        return State.access$1000(this.currentState);
    }

    int getNumber() {
        return State.access$200(this.currentState);
    }

    Locale getNumberLocale() {
        return State.access$2900(this.currentState);
    }

    State getOverridingState() {
        return this.overridingState;
    }

    String getText() {
        return State.access$300(this.currentState);
    }

    int getTextAppearanceResId() {
        return (int)State.access$1600(this.currentState);
    }

    int getVerticalOffsetWithText() {
        return (int)State.access$2400(this.currentState);
    }

    int getVerticalOffsetWithoutText() {
        return (int)State.access$2200(this.currentState);
    }

    boolean hasNumber() {
        return State.access$200(this.currentState) != -1;
    }

    boolean hasText() {
        return State.access$300(this.currentState) != null;
    }

    boolean isAutoAdjustedToGrandparentBounds() {
        return State.access$2800(this.currentState).booleanValue();
    }

    boolean isVisible() {
        return State.access$800(this.currentState).booleanValue();
    }

    private static int readColorFromAttributes(Context context0, TypedArray typedArray0, int v) {
        return MaterialResources.getColorStateList(context0, typedArray0, v).getDefaultColor();
    }

    void setAdditionalHorizontalOffset(int v) {
        State.access$2602(this.overridingState, v);
        State.access$2602(this.currentState, v);
    }

    void setAdditionalVerticalOffset(int v) {
        State.access$2702(this.overridingState, v);
        State.access$2702(this.currentState, v);
    }

    void setAlpha(int v) {
        State.access$102(this.overridingState, v);
        State.access$102(this.currentState, v);
    }

    void setAutoAdjustToGrandparentBounds(boolean z) {
        State.access$2802(this.overridingState, Boolean.valueOf(z));
        State.access$2802(this.currentState, Boolean.valueOf(z));
    }

    void setBackgroundColor(int v) {
        State.access$1502(this.overridingState, v);
        State.access$1502(this.currentState, v);
    }

    void setBadgeGravity(int v) {
        State.access$1802(this.overridingState, v);
        State.access$1802(this.currentState, v);
    }

    void setBadgeHorizontalPadding(int v) {
        State.access$1902(this.overridingState, v);
        State.access$1902(this.currentState, v);
    }

    void setBadgeShapeAppearanceOverlayResId(int v) {
        State.access$1202(this.overridingState, v);
        State.access$1202(this.currentState, v);
    }

    void setBadgeShapeAppearanceResId(int v) {
        State.access$1102(this.overridingState, v);
        State.access$1102(this.currentState, v);
    }

    void setBadgeTextColor(int v) {
        State.access$1702(this.overridingState, v);
        State.access$1702(this.currentState, v);
    }

    void setBadgeVerticalPadding(int v) {
        State.access$2002(this.overridingState, v);
        State.access$2002(this.currentState, v);
    }

    void setBadgeWithTextShapeAppearanceOverlayResId(int v) {
        State.access$1402(this.overridingState, v);
        State.access$1402(this.currentState, v);
    }

    void setBadgeWithTextShapeAppearanceResId(int v) {
        State.access$1302(this.overridingState, v);
        State.access$1302(this.currentState, v);
    }

    void setContentDescriptionExceedsMaxBadgeNumberStringResource(int v) {
        State.access$702(this.overridingState, v);
        State.access$702(this.currentState, v);
    }

    void setContentDescriptionForText(CharSequence charSequence0) {
        State.access$402(this.overridingState, charSequence0);
        State.access$402(this.currentState, charSequence0);
    }

    void setContentDescriptionNumberless(CharSequence charSequence0) {
        State.access$502(this.overridingState, charSequence0);
        State.access$502(this.currentState, charSequence0);
    }

    void setContentDescriptionQuantityStringsResource(int v) {
        State.access$602(this.overridingState, v);
        State.access$602(this.currentState, v);
    }

    void setHorizontalOffsetWithText(int v) {
        State.access$2302(this.overridingState, v);
        State.access$2302(this.currentState, v);
    }

    void setHorizontalOffsetWithoutText(int v) {
        State.access$2102(this.overridingState, v);
        State.access$2102(this.currentState, v);
    }

    void setLargeFontVerticalOffsetAdjustment(int v) {
        State.access$2502(this.overridingState, v);
        State.access$2502(this.currentState, v);
    }

    void setMaxCharacterCount(int v) {
        State.access$902(this.overridingState, v);
        State.access$902(this.currentState, v);
    }

    void setMaxNumber(int v) {
        State.access$1002(this.overridingState, v);
        State.access$1002(this.currentState, v);
    }

    void setNumber(int v) {
        State.access$202(this.overridingState, v);
        State.access$202(this.currentState, v);
    }

    void setNumberLocale(Locale locale0) {
        State.access$2902(this.overridingState, locale0);
        State.access$2902(this.currentState, locale0);
    }

    void setText(String s) {
        State.access$302(this.overridingState, s);
        State.access$302(this.currentState, s);
    }

    void setTextAppearanceResId(int v) {
        State.access$1602(this.overridingState, v);
        State.access$1602(this.currentState, v);
    }

    void setVerticalOffsetWithText(int v) {
        State.access$2402(this.overridingState, v);
        State.access$2402(this.currentState, v);
    }

    void setVerticalOffsetWithoutText(int v) {
        State.access$2202(this.overridingState, v);
        State.access$2202(this.currentState, v);
    }

    void setVisible(boolean z) {
        State.access$802(this.overridingState, Boolean.valueOf(z));
        State.access$802(this.currentState, Boolean.valueOf(z));
    }
}

