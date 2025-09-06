package androidx.transition;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class GhostViewPlatform implements GhostView {
    private static final String TAG = "GhostViewApi21";
    private final View mGhostView;
    private static Method sAddGhostMethod;
    private static boolean sAddGhostMethodFetched;
    private static Class sGhostViewClass;
    private static boolean sGhostViewClassFetched;
    private static Method sRemoveGhostMethod;
    private static boolean sRemoveGhostMethodFetched;

    private GhostViewPlatform(View view0) {
        this.mGhostView = view0;
    }

    static GhostView addGhost(View view0, ViewGroup viewGroup0, Matrix matrix0) {
        GhostViewPlatform.fetchAddGhostMethod();
        Method method0 = GhostViewPlatform.sAddGhostMethod;
        if(method0 != null) {
            try {
                return new GhostViewPlatform(((View)method0.invoke(null, view0, viewGroup0, matrix0)));
            }
            catch(IllegalAccessException invocationTargetException0) {
            }
            catch(InvocationTargetException unused_ex) {
                return null;
            }
            throw new RuntimeException(invocationTargetException0.getCause());
        }
        return null;
    }

    private static void fetchAddGhostMethod() {
        if(!GhostViewPlatform.sAddGhostMethodFetched) {
            try {
                GhostViewPlatform.fetchGhostViewClass();
                Method method0 = GhostViewPlatform.sGhostViewClass.getDeclaredMethod("addGhost", View.class, ViewGroup.class, Matrix.class);
                GhostViewPlatform.sAddGhostMethod = method0;
                method0.setAccessible(true);
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                Log.i("GhostViewApi21", "Failed to retrieve addGhost method", noSuchMethodException0);
            }
            GhostViewPlatform.sAddGhostMethodFetched = true;
        }
    }

    private static void fetchGhostViewClass() {
        if(!GhostViewPlatform.sGhostViewClassFetched) {
            try {
                GhostViewPlatform.sGhostViewClass = Class.forName("android.view.GhostView");
            }
            catch(ClassNotFoundException classNotFoundException0) {
                Log.i("GhostViewApi21", "Failed to retrieve GhostView class", classNotFoundException0);
            }
            GhostViewPlatform.sGhostViewClassFetched = true;
        }
    }

    private static void fetchRemoveGhostMethod() {
        if(!GhostViewPlatform.sRemoveGhostMethodFetched) {
            try {
                GhostViewPlatform.fetchGhostViewClass();
                Method method0 = GhostViewPlatform.sGhostViewClass.getDeclaredMethod("removeGhost", View.class);
                GhostViewPlatform.sRemoveGhostMethod = method0;
                method0.setAccessible(true);
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                Log.i("GhostViewApi21", "Failed to retrieve removeGhost method", noSuchMethodException0);
            }
            GhostViewPlatform.sRemoveGhostMethodFetched = true;
        }
    }

    static void removeGhost(View view0) {
        GhostViewPlatform.fetchRemoveGhostMethod();
        Method method0 = GhostViewPlatform.sRemoveGhostMethod;
        if(method0 != null) {
            try {
                method0.invoke(null, view0);
                return;
            }
            catch(IllegalAccessException invocationTargetException0) {
            }
            catch(InvocationTargetException unused_ex) {
                return;
            }
            throw new RuntimeException(invocationTargetException0.getCause());
        }
    }

    @Override  // androidx.transition.GhostView
    public void reserveEndViewTransition(ViewGroup viewGroup0, View view0) {
    }

    @Override  // androidx.transition.GhostView
    public void setVisibility(int v) {
        this.mGhostView.setVisibility(v);
    }
}

