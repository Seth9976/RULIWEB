package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import androidx.appcompat.R.attr;

public final class AppCompatSpinner.InspectionCompanion implements InspectionCompanion {
    private int mBackgroundTintId;
    private int mBackgroundTintModeId;
    private boolean mPropertiesMapped;

    public AppCompatSpinner.InspectionCompanion() {
        this.mPropertiesMapped = false;
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void mapProperties(PropertyMapper propertyMapper0) {
        this.mBackgroundTintId = propertyMapper0.mapObject("backgroundTint", attr.backgroundTint);
        this.mBackgroundTintModeId = propertyMapper0.mapObject("backgroundTintMode", attr.backgroundTintMode);
        this.mPropertiesMapped = true;
    }

    public void readProperties(AppCompatSpinner appCompatSpinner0, PropertyReader propertyReader0) {
        if(!this.mPropertiesMapped) {
            throw ComponentDialog..ExternalSyntheticApiModelOutline0.m();
        }
        propertyReader0.readObject(this.mBackgroundTintId, appCompatSpinner0.getBackgroundTintList());
        propertyReader0.readObject(this.mBackgroundTintModeId, appCompatSpinner0.getBackgroundTintMode());
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void readProperties(Object object0, PropertyReader propertyReader0) {
        this.readProperties(((AppCompatSpinner)object0), propertyReader0);
    }
}

