package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import androidx.appcompat.R.attr;

public final class AppCompatImageView.InspectionCompanion implements InspectionCompanion {
    private int mBackgroundTintId;
    private int mBackgroundTintModeId;
    private boolean mPropertiesMapped;
    private int mTintId;
    private int mTintModeId;

    public AppCompatImageView.InspectionCompanion() {
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

    public void readProperties(AppCompatImageView appCompatImageView0, PropertyReader propertyReader0) {
        if(!this.mPropertiesMapped) {
            throw ComponentDialog..ExternalSyntheticApiModelOutline0.m();
        }
        propertyReader0.readObject(this.mBackgroundTintId, appCompatImageView0.getBackgroundTintList());
        propertyReader0.readObject(this.mBackgroundTintModeId, appCompatImageView0.getBackgroundTintMode());
        propertyReader0.readObject(this.mTintId, appCompatImageView0.getImageTintList());
        propertyReader0.readObject(this.mTintModeId, appCompatImageView0.getImageTintMode());
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void readProperties(Object object0, PropertyReader propertyReader0) {
        this.readProperties(((AppCompatImageView)object0), propertyReader0);
    }
}

