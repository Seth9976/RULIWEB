package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import androidx.appcompat.R.attr;

public final class AppCompatMultiAutoCompleteTextView.InspectionCompanion implements InspectionCompanion {
    private int mBackgroundTintId;
    private int mBackgroundTintModeId;
    private int mDrawableTintId;
    private int mDrawableTintModeId;
    private boolean mPropertiesMapped;

    public AppCompatMultiAutoCompleteTextView.InspectionCompanion() {
        this.mPropertiesMapped = false;
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void mapProperties(PropertyMapper propertyMapper0) {
        this.mBackgroundTintId = propertyMapper0.mapObject("backgroundTint", attr.backgroundTint);
        this.mBackgroundTintModeId = propertyMapper0.mapObject("backgroundTintMode", attr.backgroundTintMode);
        this.mDrawableTintId = propertyMapper0.mapObject("drawableTint", attr.drawableTint);
        this.mDrawableTintModeId = propertyMapper0.mapObject("drawableTintMode", attr.drawableTintMode);
        this.mPropertiesMapped = true;
    }

    public void readProperties(AppCompatMultiAutoCompleteTextView appCompatMultiAutoCompleteTextView0, PropertyReader propertyReader0) {
        if(!this.mPropertiesMapped) {
            throw ComponentDialog..ExternalSyntheticApiModelOutline0.m();
        }
        propertyReader0.readObject(this.mBackgroundTintId, appCompatMultiAutoCompleteTextView0.getBackgroundTintList());
        propertyReader0.readObject(this.mBackgroundTintModeId, appCompatMultiAutoCompleteTextView0.getBackgroundTintMode());
        propertyReader0.readObject(this.mDrawableTintId, appCompatMultiAutoCompleteTextView0.getCompoundDrawableTintList());
        propertyReader0.readObject(this.mDrawableTintModeId, appCompatMultiAutoCompleteTextView0.getCompoundDrawableTintMode());
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void readProperties(Object object0, PropertyReader propertyReader0) {
        this.readProperties(((AppCompatMultiAutoCompleteTextView)object0), propertyReader0);
    }
}

