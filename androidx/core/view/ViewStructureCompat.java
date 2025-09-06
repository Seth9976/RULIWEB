package androidx.core.view;

import android.os.Build.VERSION;
import android.view.ViewStructure;
import androidx.core.util.HalfKt..ExternalSyntheticApiModelOutline0;

public class ViewStructureCompat {
    static class Api23Impl {
        static void setClassName(ViewStructure viewStructure0, String s) {
            viewStructure0.setClassName(s);
        }

        static void setContentDescription(ViewStructure viewStructure0, CharSequence charSequence0) {
            viewStructure0.setContentDescription(charSequence0);
        }

        static void setDimens(ViewStructure viewStructure0, int v, int v1, int v2, int v3, int v4, int v5) {
            viewStructure0.setDimens(v, v1, v2, v3, v4, v5);
        }

        static void setText(ViewStructure viewStructure0, CharSequence charSequence0) {
            viewStructure0.setText(charSequence0);
        }
    }

    private final Object mWrappedObj;

    private ViewStructureCompat(ViewStructure viewStructure0) {
        this.mWrappedObj = viewStructure0;
    }

    public void setClassName(String s) {
        if(Build.VERSION.SDK_INT >= 23) {
            Api23Impl.setClassName(((ViewStructure)this.mWrappedObj), s);
        }
    }

    public void setContentDescription(CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 23) {
            Api23Impl.setContentDescription(HalfKt..ExternalSyntheticApiModelOutline0.m(this.mWrappedObj), charSequence0);
        }
    }

    public void setDimens(int v, int v1, int v2, int v3, int v4, int v5) {
        if(Build.VERSION.SDK_INT >= 23) {
            Api23Impl.setDimens(((ViewStructure)this.mWrappedObj), v, v1, v2, v3, v4, v5);
        }
    }

    public void setText(CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 23) {
            Api23Impl.setText(((ViewStructure)this.mWrappedObj), charSequence0);
        }
    }

    public ViewStructure toViewStructure() {
        return (ViewStructure)this.mWrappedObj;
    }

    public static ViewStructureCompat toViewStructureCompat(ViewStructure viewStructure0) {
        return new ViewStructureCompat(viewStructure0);
    }
}

