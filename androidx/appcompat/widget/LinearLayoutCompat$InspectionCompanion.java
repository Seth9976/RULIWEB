package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import androidx.appcompat.R.attr;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntFunction;

public final class LinearLayoutCompat.InspectionCompanion implements InspectionCompanion {
    private int mBaselineAlignedChildIndexId;
    private int mBaselineAlignedId;
    private int mDividerId;
    private int mDividerPaddingId;
    private int mGravityId;
    private int mMeasureWithLargestChildId;
    private int mOrientationId;
    private boolean mPropertiesMapped;
    private int mShowDividersId;
    private int mWeightSumId;

    public LinearLayoutCompat.InspectionCompanion() {
        this.mPropertiesMapped = false;
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void mapProperties(PropertyMapper propertyMapper0) {
        this.mBaselineAlignedId = propertyMapper0.mapBoolean("baselineAligned", 0x1010126);
        this.mBaselineAlignedChildIndexId = propertyMapper0.mapInt("baselineAlignedChildIndex", 0x1010127);
        this.mGravityId = propertyMapper0.mapGravity("gravity", 0x10100AF);
        this.mOrientationId = propertyMapper0.mapIntEnum("orientation", 0x10100C4, new IntFunction() {
            @Override
            public Object apply(int v) {
                return this.apply(v);
            }

            public String apply(int v) {
                switch(v) {
                    case 0: {
                        return "horizontal";
                    }
                    case 1: {
                        return "vertical";
                    }
                    default: {
                        return String.valueOf(v);
                    }
                }
            }
        });
        this.mWeightSumId = propertyMapper0.mapFloat("weightSum", 0x1010128);
        this.mDividerId = propertyMapper0.mapObject("divider", attr.divider);
        this.mDividerPaddingId = propertyMapper0.mapInt("dividerPadding", attr.dividerPadding);
        this.mMeasureWithLargestChildId = propertyMapper0.mapBoolean("measureWithLargestChild", attr.measureWithLargestChild);
        androidx.appcompat.widget.LinearLayoutCompat.InspectionCompanion.2 linearLayoutCompat$InspectionCompanion$20 = new IntFunction() {
            @Override
            public Object apply(int v) {
                return this.apply(v);
            }

            public Set apply(int v) {
                Set set0 = new HashSet();
                if(v == 0) {
                    set0.add("none");
                }
                if(v == 1) {
                    set0.add("beginning");
                }
                if(v == 2) {
                    set0.add("middle");
                }
                if(v == 4) {
                    set0.add("end");
                }
                return set0;
            }
        };
        this.mShowDividersId = propertyMapper0.mapIntFlag("showDividers", attr.showDividers, linearLayoutCompat$InspectionCompanion$20);
        this.mPropertiesMapped = true;
    }

    public void readProperties(LinearLayoutCompat linearLayoutCompat0, PropertyReader propertyReader0) {
        if(!this.mPropertiesMapped) {
            throw ComponentDialog..ExternalSyntheticApiModelOutline0.m();
        }
        propertyReader0.readBoolean(this.mBaselineAlignedId, linearLayoutCompat0.isBaselineAligned());
        propertyReader0.readInt(this.mBaselineAlignedChildIndexId, linearLayoutCompat0.getBaselineAlignedChildIndex());
        propertyReader0.readGravity(this.mGravityId, linearLayoutCompat0.getGravity());
        propertyReader0.readIntEnum(this.mOrientationId, linearLayoutCompat0.getOrientation());
        propertyReader0.readFloat(this.mWeightSumId, linearLayoutCompat0.getWeightSum());
        propertyReader0.readObject(this.mDividerId, linearLayoutCompat0.getDividerDrawable());
        propertyReader0.readInt(this.mDividerPaddingId, linearLayoutCompat0.getDividerPadding());
        propertyReader0.readBoolean(this.mMeasureWithLargestChildId, linearLayoutCompat0.isMeasureWithLargestChildEnabled());
        propertyReader0.readIntFlag(this.mShowDividersId, linearLayoutCompat0.getShowDividers());
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void readProperties(Object object0, PropertyReader propertyReader0) {
        this.readProperties(((LinearLayoutCompat)object0), propertyReader0);
    }
}

