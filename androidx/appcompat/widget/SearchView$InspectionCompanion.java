package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import androidx.appcompat.R.attr;

public final class SearchView.InspectionCompanion implements InspectionCompanion {
    private int mIconifiedByDefaultId;
    private int mImeOptionsId;
    private int mMaxWidthId;
    private boolean mPropertiesMapped;
    private int mQueryHintId;

    public SearchView.InspectionCompanion() {
        this.mPropertiesMapped = false;
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void mapProperties(PropertyMapper propertyMapper0) {
        this.mImeOptionsId = propertyMapper0.mapInt("imeOptions", 0x1010264);
        this.mMaxWidthId = propertyMapper0.mapInt("maxWidth", 0x101011F);
        this.mIconifiedByDefaultId = propertyMapper0.mapBoolean("iconifiedByDefault", attr.iconifiedByDefault);
        this.mQueryHintId = propertyMapper0.mapObject("queryHint", attr.queryHint);
        this.mPropertiesMapped = true;
    }

    public void readProperties(SearchView searchView0, PropertyReader propertyReader0) {
        if(!this.mPropertiesMapped) {
            throw ComponentDialog..ExternalSyntheticApiModelOutline0.m();
        }
        propertyReader0.readInt(this.mImeOptionsId, searchView0.getImeOptions());
        propertyReader0.readInt(this.mMaxWidthId, searchView0.getMaxWidth());
        propertyReader0.readBoolean(this.mIconifiedByDefaultId, searchView0.isIconfiedByDefault());
        propertyReader0.readObject(this.mQueryHintId, searchView0.getQueryHint());
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void readProperties(Object object0, PropertyReader propertyReader0) {
        this.readProperties(((SearchView)object0), propertyReader0);
    }
}

