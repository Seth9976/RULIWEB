package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import androidx.appcompat.R.attr;

public final class AppCompatImageButton.InspectionCompanion implements InspectionCompanion {
    private int mBackgroundTintId;
    private int mBackgroundTintModeId;
    private boolean mPropertiesMapped;
    private int mTintId;
    private int mTintModeId;

    public AppCompatImageButton.InspectionCompanion() {
        this.mPropertiesMapped = false;
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void mapProperties(PropertyMapper propertyMapper0) {
        this.mBackgroundTintId = propertyMapper0.mapObject("backgroundTint", attr.backgroundTint);
        this.mBackgroundTintModeId = propertyMapper0.mapObject("backgroundTintMode", attr.backgroundTintMode);
        this.mTintId = propertyMapper0.mapObject("tint", attr.tint);
        this.mTintModeId = propertyMapper0.mapObject("tintMode", attr.tintMode);
        this.mPropertiesMapped = true;
    }

    public void readProperties(AppCompatImageButton appCompatImageButton0, PropertyReader propertyReader0) {
        if(!this.mPropertiesMapped) {
            throw ComponentDialog..ExternalSyntheticApiModelOutline0.m();
        }
        propertyReader0.readObject(this.mBackgroundTintId, appCompatImageButton0.getBackgroundTintList());
        propertyReader0.readObject(this.mBackgroundTintModeId, appCompatImageButton0.getBackgroundTintMode());
        propertyReader0.readObject(this.mTintId, appCompatImageButton0.getImageTintList());
        propertyReader0.readObject(this.mTintModeId, appCompatImageButton0.getImageTintMode());
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void readProperties(Object object0, PropertyReader propertyReader0) {
        this.readProperties(((AppCompatImageButton)object0), propertyReader0);
    }
}

