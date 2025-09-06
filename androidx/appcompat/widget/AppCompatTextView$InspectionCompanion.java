package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import androidx.appcompat.R.attr;
import java.util.function.IntFunction;

public final class AppCompatTextView.InspectionCompanion implements InspectionCompanion {
    private int mAutoSizeMaxTextSizeId;
    private int mAutoSizeMinTextSizeId;
    private int mAutoSizeStepGranularityId;
    private int mAutoSizeTextTypeId;
    private int mBackgroundTintId;
    private int mBackgroundTintModeId;
    private int mDrawableTintId;
    private int mDrawableTintModeId;
    private boolean mPropertiesMapped;

    public AppCompatTextView.InspectionCompanion() {
        this.mPropertiesMapped = false;
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void mapProperties(PropertyMapper propertyMapper0) {
        this.mAutoSizeMaxTextSizeId = propertyMapper0.mapInt("autoSizeMaxTextSize", attr.autoSizeMaxTextSize);
        this.mAutoSizeMinTextSizeId = propertyMapper0.mapInt("autoSizeMinTextSize", attr.autoSizeMinTextSize);
        this.mAutoSizeStepGranularityId = propertyMapper0.mapInt("autoSizeStepGranularity", attr.autoSizeStepGranularity);
        androidx.appcompat.widget.AppCompatTextView.InspectionCompanion.1 appCompatTextView$InspectionCompanion$10 = new IntFunction() {
            @Override
            public Object apply(int v) {
                return this.apply(v);
            }

            public String apply(int v) {
                switch(v) {
                    case 0: {
                        return "none";
                    }
                    case 1: {
                        return "uniform";
                    }
                    default: {
                        return String.valueOf(v);
                    }
                }
            }
        };
        this.mAutoSizeTextTypeId = propertyMapper0.mapIntEnum("autoSizeTextType", attr.autoSizeTextType, appCompatTextView$InspectionCompanion$10);
        this.mBackgroundTintId = propertyMapper0.mapObject("backgroundTint", attr.backgroundTint);
        this.mBackgroundTintModeId = propertyMapper0.mapObject("backgroundTintMode", attr.backgroundTintMode);
        this.mDrawableTintId = propertyMapper0.mapObject("drawableTint", attr.drawableTint);
        this.mDrawableTintModeId = propertyMapper0.mapObject("drawableTintMode", attr.drawableTintMode);
        this.mPropertiesMapped = true;
    }

    public void readProperties(AppCompatTextView appCompatTextView0, PropertyReader propertyReader0) {
        if(!this.mPropertiesMapped) {
            throw ComponentDialog..ExternalSyntheticApiModelOutline0.m();
        }
        propertyReader0.readInt(this.mAutoSizeMaxTextSizeId, appCompatTextView0.getAutoSizeMaxTextSize());
        propertyReader0.readInt(this.mAutoSizeMinTextSizeId, appCompatTextView0.getAutoSizeMinTextSize());
        propertyReader0.readInt(this.mAutoSizeStepGranularityId, appCompatTextView0.getAutoSizeStepGranularity());
        propertyReader0.readIntEnum(this.mAutoSizeTextTypeId, appCompatTextView0.getAutoSizeTextType());
        propertyReader0.readObject(this.mBackgroundTintId, appCompatTextView0.getBackgroundTintList());
        propertyReader0.readObject(this.mBackgroundTintModeId, appCompatTextView0.getBackgroundTintMode());
        propertyReader0.readObject(this.mDrawableTintId, appCompatTextView0.getCompoundDrawableTintList());
        propertyReader0.readObject(this.mDrawableTintModeId, appCompatTextView0.getCompoundDrawableTintMode());
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void readProperties(Object object0, PropertyReader propertyReader0) {
        this.readProperties(((AppCompatTextView)object0), propertyReader0);
    }
}

