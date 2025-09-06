package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import androidx.appcompat.R.attr;
import java.util.function.IntFunction;

public final class AppCompatButton.InspectionCompanion implements InspectionCompanion {
    private int mAutoSizeMaxTextSizeId;
    private int mAutoSizeMinTextSizeId;
    private int mAutoSizeStepGranularityId;
    private int mAutoSizeTextTypeId;
    private int mBackgroundTintId;
    private int mBackgroundTintModeId;
    private int mDrawableTintId;
    private int mDrawableTintModeId;
    private boolean mPropertiesMapped;

    public AppCompatButton.InspectionCompanion() {
        this.mPropertiesMapped = false;
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void mapProperties(PropertyMapper propertyMapper0) {
        this.mAutoSizeMaxTextSizeId = propertyMapper0.mapInt("autoSizeMaxTextSize", attr.autoSizeMaxTextSize);
        this.mAutoSizeMinTextSizeId = propertyMapper0.mapInt("autoSizeMinTextSize", attr.autoSizeMinTextSize);
        this.mAutoSizeStepGranularityId = propertyMapper0.mapInt("autoSizeStepGranularity", attr.autoSizeStepGranularity);
        androidx.appcompat.widget.AppCompatButton.InspectionCompanion.1 appCompatButton$InspectionCompanion$10 = new IntFunction() {
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
        this.mAutoSizeTextTypeId = propertyMapper0.mapIntEnum("autoSizeTextType", attr.autoSizeTextType, appCompatButton$InspectionCompanion$10);
        this.mBackgroundTintId = propertyMapper0.mapObject("backgroundTint", attr.backgroundTint);
        this.mBackgroundTintModeId = propertyMapper0.mapObject("backgroundTintMode", attr.backgroundTintMode);
        this.mDrawableTintId = propertyMapper0.mapObject("drawableTint", attr.drawableTint);
        this.mDrawableTintModeId = propertyMapper0.mapObject("drawableTintMode", attr.drawableTintMode);
        this.mPropertiesMapped = true;
    }

    public void readProperties(AppCompatButton appCompatButton0, PropertyReader propertyReader0) {
        if(!this.mPropertiesMapped) {
            throw ComponentDialog..ExternalSyntheticApiModelOutline0.m();
        }
        propertyReader0.readInt(this.mAutoSizeMaxTextSizeId, appCompatButton0.getAutoSizeMaxTextSize());
        propertyReader0.readInt(this.mAutoSizeMinTextSizeId, appCompatButton0.getAutoSizeMinTextSize());
        propertyReader0.readInt(this.mAutoSizeStepGranularityId, appCompatButton0.getAutoSizeStepGranularity());
        propertyReader0.readIntEnum(this.mAutoSizeTextTypeId, appCompatButton0.getAutoSizeTextType());
        propertyReader0.readObject(this.mBackgroundTintId, appCompatButton0.getBackgroundTintList());
        propertyReader0.readObject(this.mBackgroundTintModeId, appCompatButton0.getBackgroundTintMode());
        propertyReader0.readObject(this.mDrawableTintId, appCompatButton0.getCompoundDrawableTintList());
        propertyReader0.readObject(this.mDrawableTintModeId, appCompatButton0.getCompoundDrawableTintMode());
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void readProperties(Object object0, PropertyReader propertyReader0) {
        this.readProperties(((AppCompatButton)object0), propertyReader0);
    }
}

