package com.google.android.material.button;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import com.google.android.material.R.attr;

public final class MaterialButton.InspectionCompanion implements InspectionCompanion {
    private int mIconPaddingId;
    private boolean mPropertiesMapped;

    public MaterialButton.InspectionCompanion() {
        this.mPropertiesMapped = false;
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void mapProperties(PropertyMapper propertyMapper0) {
        this.mIconPaddingId = propertyMapper0.mapInt("iconPadding", attr.iconPadding);
        this.mPropertiesMapped = true;
    }

    public void readProperties(MaterialButton materialButton0, PropertyReader propertyReader0) {
        if(!this.mPropertiesMapped) {
            throw ComponentDialog..ExternalSyntheticApiModelOutline0.m();
        }
        propertyReader0.readInt(this.mIconPaddingId, materialButton0.getIconPadding());
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void readProperties(Object object0, PropertyReader propertyReader0) {
        this.readProperties(((MaterialButton)object0), propertyReader0);
    }
}

