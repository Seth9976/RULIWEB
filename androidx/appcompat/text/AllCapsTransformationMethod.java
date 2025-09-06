package androidx.appcompat.text;

import android.content.Context;
import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;
import java.util.Locale;

public class AllCapsTransformationMethod implements TransformationMethod {
    private Locale mLocale;

    public AllCapsTransformationMethod(Context context0) {
        this.mLocale = context0.getResources().getConfiguration().locale;
    }

    @Override  // android.text.method.TransformationMethod
    public CharSequence getTransformation(CharSequence charSequence0, View view0) {
        return charSequence0 != null ? charSequence0.toString().toUpperCase(this.mLocale) : null;
    }

    @Override  // android.text.method.TransformationMethod
    public void onFocusChanged(View view0, CharSequence charSequence0, boolean z, int v, Rect rect0) {
    }
}

