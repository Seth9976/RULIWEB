package androidx.emoji2.viewsintegration;

import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;
import androidx.emoji2.text.EmojiCompat;

class EmojiTransformationMethod implements TransformationMethod {
    private final TransformationMethod mTransformationMethod;

    EmojiTransformationMethod(TransformationMethod transformationMethod0) {
        this.mTransformationMethod = transformationMethod0;
    }

    public TransformationMethod getOriginalTransformationMethod() {
        return this.mTransformationMethod;
    }

    @Override  // android.text.method.TransformationMethod
    public CharSequence getTransformation(CharSequence charSequence0, View view0) {
        if(view0.isInEditMode()) {
            return charSequence0;
        }
        TransformationMethod transformationMethod0 = this.mTransformationMethod;
        if(transformationMethod0 != null) {
            charSequence0 = transformationMethod0.getTransformation(charSequence0, view0);
        }
        return charSequence0 == null || EmojiCompat.get().getLoadState() != 1 ? charSequence0 : EmojiCompat.get().process(charSequence0);
    }

    @Override  // android.text.method.TransformationMethod
    public void onFocusChanged(View view0, CharSequence charSequence0, boolean z, int v, Rect rect0) {
        TransformationMethod transformationMethod0 = this.mTransformationMethod;
        if(transformationMethod0 != null) {
            transformationMethod0.onFocusChanged(view0, charSequence0, z, v, rect0);
        }
    }
}

