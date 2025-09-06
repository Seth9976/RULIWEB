package androidx.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater.Factory2;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.reflect.Field;

public final class LayoutInflaterCompat {
    static class Factory2Wrapper implements LayoutInflater.Factory2 {
        final LayoutInflaterFactory mDelegateFactory;

        Factory2Wrapper(LayoutInflaterFactory layoutInflaterFactory0) {
            this.mDelegateFactory = layoutInflaterFactory0;
        }

        @Override  // android.view.LayoutInflater$Factory2
        public View onCreateView(View view0, String s, Context context0, AttributeSet attributeSet0) {
            return this.mDelegateFactory.onCreateView(view0, s, context0, attributeSet0);
        }

        @Override  // android.view.LayoutInflater$Factory
        public View onCreateView(String s, Context context0, AttributeSet attributeSet0) {
            return this.mDelegateFactory.onCreateView(null, s, context0, attributeSet0);
        }

        @Override
        public String toString() {
            return this.getClass().getName() + "{" + this.mDelegateFactory + "}";
        }
    }

    private static final String TAG = "LayoutInflaterCompatHC";
    private static boolean sCheckedField;
    private static Field sLayoutInflaterFactory2Field;

    private static void forceSetFactory2(LayoutInflater layoutInflater0, LayoutInflater.Factory2 layoutInflater$Factory20) {
        if(!LayoutInflaterCompat.sCheckedField) {
            try {
                Field field0 = LayoutInflater.class.getDeclaredField("mFactory2");
                LayoutInflaterCompat.sLayoutInflaterFactory2Field = field0;
                field0.setAccessible(true);
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 Could not find field \'mFactory2\' on class android.view.LayoutInflater; inflation may have unexpected results.", noSuchFieldException0);
            }
            LayoutInflaterCompat.sCheckedField = true;
        }
        Field field1 = LayoutInflaterCompat.sLayoutInflaterFactory2Field;
        if(field1 != null) {
            try {
                field1.set(layoutInflater0, layoutInflater$Factory20);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 could not set the Factory2 on LayoutInflater " + layoutInflater0 + "; inflation may have unexpected results.", illegalAccessException0);
            }
        }
    }

    @Deprecated
    public static LayoutInflaterFactory getFactory(LayoutInflater layoutInflater0) {
        LayoutInflater.Factory layoutInflater$Factory0 = layoutInflater0.getFactory();
        return layoutInflater$Factory0 instanceof Factory2Wrapper ? ((Factory2Wrapper)layoutInflater$Factory0).mDelegateFactory : null;
    }

    @Deprecated
    public static void setFactory(LayoutInflater layoutInflater0, LayoutInflaterFactory layoutInflaterFactory0) {
        layoutInflater0.setFactory2(new Factory2Wrapper(layoutInflaterFactory0));
    }

    public static void setFactory2(LayoutInflater layoutInflater0, LayoutInflater.Factory2 layoutInflater$Factory20) {
        layoutInflater0.setFactory2(layoutInflater$Factory20);
    }
}

