package com.android.volley;

import android.text.TextUtils;

public final class Header {
    private final String mName;
    private final String mValue;

    public Header(String s, String s1) {
        this.mName = s;
        this.mValue = s1;
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && TextUtils.equals(this.mName, ((Header)object0).mName) && TextUtils.equals(this.mValue, ((Header)object0).mValue);
    }

    public final String getName() {
        return this.mName;
    }

    public final String getValue() {
        return this.mValue;
    }

    @Override
    public int hashCode() {
        return this.mName.hashCode() * 0x1F + this.mValue.hashCode();
    }

    @Override
    public String toString() {
        return "Header[name=" + this.mName + ",value=" + this.mValue + "]";
    }
}

