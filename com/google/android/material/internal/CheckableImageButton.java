package com.google.android.material.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import androidx.appcompat.R.attr;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;

public class CheckableImageButton extends AppCompatImageButton implements Checkable {
    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        boolean checked;

        static {
            SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0, null);
                }

                public SavedState createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return new SavedState(parcel0, classLoader0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                @Override  // android.os.Parcelable$ClassLoaderCreator
                public Object createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return this.createFromParcel(parcel0, classLoader0);
                }

                public SavedState[] newArray(int v) {
                    return new SavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        public SavedState(Parcel parcel0, ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            this.readFromParcel(parcel0);
        }

        public SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        private void readFromParcel(Parcel parcel0) {
            this.checked = parcel0.readInt() == 1;
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(((int)this.checked));
        }
    }

    private static final int[] DRAWABLE_STATE_CHECKED;
    private boolean checkable;
    private boolean checked;
    private boolean pressable;

    static {
        CheckableImageButton.DRAWABLE_STATE_CHECKED = new int[]{0x10100A0};
    }

    public CheckableImageButton(Context context0) {
        this(context0, null);
    }

    public CheckableImageButton(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.imageButtonStyle);
    }

    public CheckableImageButton(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.checkable = true;
        this.pressable = true;
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
                super.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
                accessibilityEvent0.setChecked(CheckableImageButton.this.isChecked());
            }

            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                accessibilityNodeInfoCompat0.setCheckable(CheckableImageButton.this.isCheckable());
                accessibilityNodeInfoCompat0.setChecked(CheckableImageButton.this.isChecked());
            }
        });
    }

    public boolean isCheckable() {
        return this.checkable;
    }

    @Override  // android.widget.Checkable
    public boolean isChecked() {
        return this.checked;
    }

    public boolean isPressable() {
        return this.pressable;
    }

    // 去混淆评级： 低(20)
    @Override  // android.widget.ImageView
    public int[] onCreateDrawableState(int v) {
        return this.checked ? CheckableImageButton.mergeDrawableStates(super.onCreateDrawableState(v + CheckableImageButton.DRAWABLE_STATE_CHECKED.length), CheckableImageButton.DRAWABLE_STATE_CHECKED) : super.onCreateDrawableState(v);
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.setChecked(((SavedState)parcelable0).checked);
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.checked = this.checked;
        return parcelable0;
    }

    public void setCheckable(boolean z) {
        if(this.checkable != z) {
            this.checkable = z;
            this.sendAccessibilityEvent(0);
        }
    }

    @Override  // android.widget.Checkable
    public void setChecked(boolean z) {
        if(this.checkable && this.checked != z) {
            this.checked = z;
            this.refreshDrawableState();
            this.sendAccessibilityEvent(0x800);
        }
    }

    public void setPressable(boolean z) {
        this.pressable = z;
    }

    @Override  // android.view.View
    public void setPressed(boolean z) {
        if(this.pressable) {
            super.setPressed(z);
        }
    }

    @Override  // android.widget.Checkable
    public void toggle() {
        this.setChecked(!this.checked);
    }
}

