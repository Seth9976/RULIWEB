package com.google.android.material.internal;

import android.widget.Checkable;

public interface MaterialCheckable extends Checkable {
    public interface OnCheckedChangeListener {
        void onCheckedChanged(Object arg1, boolean arg2);
    }

    int getId();

    void setInternalOnCheckedChangeListener(OnCheckedChangeListener arg1);
}

