package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import androidx.appcompat.R.attr;

public final class SwitchCompat.InspectionCompanion implements InspectionCompanion {
    private boolean mPropertiesMapped;
    private int mShowTextId;
    private int mSplitTrackId;
    private int mSwitchMinWidthId;
    private int mSwitchPaddingId;
    private int mTextOffId;
    private int mTextOnId;
    private int mThumbId;
    private int mThumbTextPaddingId;
    private int mThumbTintId;
    private int mThumbTintModeId;
    private int mTrackId;
    private int mTrackTintId;
    private int mTrackTintModeId;

    public SwitchCompat.InspectionCompanion() {
        this.mPropertiesMapped = false;
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void mapProperties(PropertyMapper propertyMapper0) {
        this.mTextOffId = propertyMapper0.mapObject("textOff", 0x1010125);
        this.mTextOnId = propertyMapper0.mapObject("textOn", 0x1010124);
        this.mThumbId = propertyMapper0.mapObject("thumb", 0x1010142);
        this.mShowTextId = propertyMapper0.mapBoolean("showText", attr.showText);
        this.mSplitTrackId = propertyMapper0.mapBoolean("splitTrack", attr.splitTrack);
        this.mSwitchMinWidthId = propertyMapper0.mapInt("switchMinWidth", attr.switchMinWidth);
        this.mSwitchPaddingId = propertyMapper0.mapInt("switchPadding", attr.switchPadding);
        this.mThumbTextPaddingId = propertyMapper0.mapInt("thumbTextPadding", attr.thumbTextPadding);
        this.mThumbTintId = propertyMapper0.mapObject("thumbTint", attr.thumbTint);
        this.mThumbTintModeId = propertyMapper0.mapObject("thumbTintMode", attr.thumbTintMode);
        this.mTrackId = propertyMapper0.mapObject("track", attr.track);
        this.mTrackTintId = propertyMapper0.mapObject("trackTint", attr.trackTint);
        this.mTrackTintModeId = propertyMapper0.mapObject("trackTintMode", attr.trackTintMode);
        this.mPropertiesMapped = true;
    }

    public void readProperties(SwitchCompat switchCompat0, PropertyReader propertyReader0) {
        if(!this.mPropertiesMapped) {
            throw ComponentDialog..ExternalSyntheticApiModelOutline0.m();
        }
        propertyReader0.readObject(this.mTextOffId, switchCompat0.getTextOff());
        propertyReader0.readObject(this.mTextOnId, switchCompat0.getTextOn());
        propertyReader0.readObject(this.mThumbId, switchCompat0.getThumbDrawable());
        propertyReader0.readBoolean(this.mShowTextId, switchCompat0.getShowText());
        propertyReader0.readBoolean(this.mSplitTrackId, switchCompat0.getSplitTrack());
        propertyReader0.readInt(this.mSwitchMinWidthId, switchCompat0.getSwitchMinWidth());
        propertyReader0.readInt(this.mSwitchPaddingId, switchCompat0.getSwitchPadding());
        propertyReader0.readInt(this.mThumbTextPaddingId, switchCompat0.getThumbTextPadding());
        propertyReader0.readObject(this.mThumbTintId, switchCompat0.getThumbTintList());
        propertyReader0.readObject(this.mThumbTintModeId, switchCompat0.getThumbTintMode());
        propertyReader0.readObject(this.mTrackId, switchCompat0.getTrackDrawable());
        propertyReader0.readObject(this.mTrackTintId, switchCompat0.getTrackTintList());
        propertyReader0.readObject(this.mTrackTintModeId, switchCompat0.getTrackTintMode());
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void readProperties(Object object0, PropertyReader propertyReader0) {
        this.readProperties(((SwitchCompat)object0), propertyReader0);
    }
}

