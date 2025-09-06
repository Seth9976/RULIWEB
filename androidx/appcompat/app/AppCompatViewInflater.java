package androidx.appcompat.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View.OnClickListener;
import android.view.View;
import androidx.appcompat.R.styleable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.appcompat.widget.TintContextWrapper;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AppCompatViewInflater {
    static class DeclaredOnClickListener implements View.OnClickListener {
        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;

        public DeclaredOnClickListener(View view0, String s) {
            this.mHostView = view0;
            this.mMethodName = s;
        }

        @Override  // android.view.View$OnClickListener
        public void onClick(View view0) {
            if(this.mResolvedMethod == null) {
                this.resolveMethod(this.mHostView.getContext());
            }
            try {
                this.mResolvedMethod.invoke(this.mResolvedContext, view0);
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", illegalAccessException0);
            }
            catch(InvocationTargetException invocationTargetException0) {
                throw new IllegalStateException("Could not execute method for android:onClick", invocationTargetException0);
            }
        }

        private void resolveMethod(Context context0) {
            while(context0 != null) {
                try {
                    if(!context0.isRestricted()) {
                        Method method0 = context0.getClass().getMethod(this.mMethodName, View.class);
                        if(method0 != null) {
                            this.mResolvedMethod = method0;
                            this.mResolvedContext = context0;
                            return;
                        }
                    }
                }
                catch(NoSuchMethodException unused_ex) {
                }
                context0 = context0 instanceof ContextWrapper ? ((ContextWrapper)context0).getBaseContext() : null;
            }
            int v = this.mHostView.getId();
            String s = v == -1 ? "" : " with id \'" + this.mHostView.getContext().getResources().getResourceEntryName(v) + "\'";
            throw new IllegalStateException("Could not find method " + this.mMethodName + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.mHostView.getClass() + s);
        }
    }

    private static final String LOG_TAG = "AppCompatViewInflater";
    private final Object[] mConstructorArgs;
    private static final int[] sAccessibilityHeading;
    private static final int[] sAccessibilityPaneTitle;
    private static final String[] sClassPrefixList;
    private static final SimpleArrayMap sConstructorMap;
    private static final Class[] sConstructorSignature;
    private static final int[] sOnClickAttrs;
    private static final int[] sScreenReaderFocusable;

    static {
        AppCompatViewInflater.sConstructorSignature = new Class[]{Context.class, AttributeSet.class};
        AppCompatViewInflater.sOnClickAttrs = new int[]{0x101026F};
        AppCompatViewInflater.sAccessibilityHeading = new int[]{0x1010580};
        AppCompatViewInflater.sAccessibilityPaneTitle = new int[]{0x101057C};
        AppCompatViewInflater.sScreenReaderFocusable = new int[]{0x1010574};
        AppCompatViewInflater.sClassPrefixList = new String[]{"android.widget.", "android.view.", "android.webkit."};
        AppCompatViewInflater.sConstructorMap = new SimpleArrayMap();
    }

    public AppCompatViewInflater() {
        this.mConstructorArgs = new Object[2];
    }

    private void backportAccessibilityAttributes(Context context0, View view0, AttributeSet attributeSet0) {
        if(Build.VERSION.SDK_INT > 28) {
            return;
        }
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, AppCompatViewInflater.sAccessibilityHeading);
        if(typedArray0.hasValue(0)) {
            ViewCompat.setAccessibilityHeading(view0, typedArray0.getBoolean(0, false));
        }
        typedArray0.recycle();
        TypedArray typedArray1 = context0.obtainStyledAttributes(attributeSet0, AppCompatViewInflater.sAccessibilityPaneTitle);
        if(typedArray1.hasValue(0)) {
            ViewCompat.setAccessibilityPaneTitle(view0, typedArray1.getString(0));
        }
        typedArray1.recycle();
        TypedArray typedArray2 = context0.obtainStyledAttributes(attributeSet0, AppCompatViewInflater.sScreenReaderFocusable);
        if(typedArray2.hasValue(0)) {
            ViewCompat.setScreenReaderFocusable(view0, typedArray2.getBoolean(0, false));
        }
        typedArray2.recycle();
    }

    private void checkOnClickListener(View view0, AttributeSet attributeSet0) {
        Context context0 = view0.getContext();
        if(context0 instanceof ContextWrapper && view0.hasOnClickListeners()) {
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, AppCompatViewInflater.sOnClickAttrs);
            String s = typedArray0.getString(0);
            if(s != null) {
                view0.setOnClickListener(new DeclaredOnClickListener(view0, s));
            }
            typedArray0.recycle();
        }
    }

    protected AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context0, AttributeSet attributeSet0) {
        return new AppCompatAutoCompleteTextView(context0, attributeSet0);
    }

    protected AppCompatButton createButton(Context context0, AttributeSet attributeSet0) {
        return new AppCompatButton(context0, attributeSet0);
    }

    protected AppCompatCheckBox createCheckBox(Context context0, AttributeSet attributeSet0) {
        return new AppCompatCheckBox(context0, attributeSet0);
    }

    protected AppCompatCheckedTextView createCheckedTextView(Context context0, AttributeSet attributeSet0) {
        return new AppCompatCheckedTextView(context0, attributeSet0);
    }

    protected AppCompatEditText createEditText(Context context0, AttributeSet attributeSet0) {
        return new AppCompatEditText(context0, attributeSet0);
    }

    protected AppCompatImageButton createImageButton(Context context0, AttributeSet attributeSet0) {
        return new AppCompatImageButton(context0, attributeSet0);
    }

    protected AppCompatImageView createImageView(Context context0, AttributeSet attributeSet0) {
        return new AppCompatImageView(context0, attributeSet0);
    }

    protected AppCompatMultiAutoCompleteTextView createMultiAutoCompleteTextView(Context context0, AttributeSet attributeSet0) {
        return new AppCompatMultiAutoCompleteTextView(context0, attributeSet0);
    }

    protected AppCompatRadioButton createRadioButton(Context context0, AttributeSet attributeSet0) {
        return new AppCompatRadioButton(context0, attributeSet0);
    }

    protected AppCompatRatingBar createRatingBar(Context context0, AttributeSet attributeSet0) {
        return new AppCompatRatingBar(context0, attributeSet0);
    }

    protected AppCompatSeekBar createSeekBar(Context context0, AttributeSet attributeSet0) {
        return new AppCompatSeekBar(context0, attributeSet0);
    }

    protected AppCompatSpinner createSpinner(Context context0, AttributeSet attributeSet0) {
        return new AppCompatSpinner(context0, attributeSet0);
    }

    protected AppCompatTextView createTextView(Context context0, AttributeSet attributeSet0) {
        return new AppCompatTextView(context0, attributeSet0);
    }

    protected AppCompatToggleButton createToggleButton(Context context0, AttributeSet attributeSet0) {
        return new AppCompatToggleButton(context0, attributeSet0);
    }

    protected View createView(Context context0, String s, AttributeSet attributeSet0) [...] // Inlined contents

    public final View createView(View view0, String s, Context context0, AttributeSet attributeSet0, boolean z, boolean z1, boolean z2, boolean z3) {
        View view1;
        Context context1 = !z || view0 == null ? context0 : view0.getContext();
        if(z1 || z2) {
            context1 = AppCompatViewInflater.themifyContext(context1, attributeSet0, z1, z2);
        }
        if(z3) {
            context1 = TintContextWrapper.wrap(context1);
        }
        s.hashCode();
        switch(s) {
            case "AutoCompleteTextView": {
                view1 = this.createAutoCompleteTextView(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            case "Button": {
                view1 = this.createButton(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            case "CheckBox": {
                view1 = this.createCheckBox(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            case "CheckedTextView": {
                view1 = this.createCheckedTextView(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            case "EditText": {
                view1 = this.createEditText(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            case "ImageButton": {
                view1 = this.createImageButton(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            case "ImageView": {
                view1 = this.createImageView(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            case "MultiAutoCompleteTextView": {
                view1 = this.createMultiAutoCompleteTextView(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            case "RadioButton": {
                view1 = this.createRadioButton(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            case "RatingBar": {
                view1 = this.createRatingBar(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            case "SeekBar": {
                view1 = this.createSeekBar(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            case "Spinner": {
                view1 = this.createSpinner(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            case "TextView": {
                view1 = this.createTextView(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            case "ToggleButton": {
                view1 = this.createToggleButton(context1, attributeSet0);
                this.verifyNotNull(view1, s);
                break;
            }
            default: {
                view1 = null;
            }
        }
        if(view1 == null && context0 != context1) {
            view1 = this.createViewFromTag(context1, s, attributeSet0);
        }
        if(view1 != null) {
            this.checkOnClickListener(view1, attributeSet0);
            this.backportAccessibilityAttributes(context1, view1, attributeSet0);
        }
        return view1;
    }

    private View createViewByPrefix(Context context0, String s, String s1) throws ClassNotFoundException, InflateException {
        SimpleArrayMap simpleArrayMap0 = AppCompatViewInflater.sConstructorMap;
        Constructor constructor0 = (Constructor)simpleArrayMap0.get(s);
        try {
            if(constructor0 == null) {
                constructor0 = Class.forName((s1 == null ? s : s1 + s), false, context0.getClassLoader()).asSubclass(View.class).getConstructor(AppCompatViewInflater.sConstructorSignature);
                simpleArrayMap0.put(s, constructor0);
            }
            constructor0.setAccessible(true);
            return (View)constructor0.newInstance(this.mConstructorArgs);
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    private View createViewFromTag(Context context0, String s, AttributeSet attributeSet0) {
        View view0;
        if(s.equals("view")) {
            s = attributeSet0.getAttributeValue(null, "class");
        }
        try {
            this.mConstructorArgs[0] = context0;
            this.mConstructorArgs[1] = attributeSet0;
            if(-1 == s.indexOf(46)) {
                for(int v1 = 0; true; ++v1) {
                    String[] arr_s = AppCompatViewInflater.sClassPrefixList;
                    if(v1 >= arr_s.length) {
                        return null;
                    }
                    view0 = this.createViewByPrefix(context0, s, arr_s[v1]);
                    if(view0 != null) {
                        break;
                    }
                }
                return view0;
            }
            return this.createViewByPrefix(context0, s, null);
        }
        catch(Exception unused_ex) {
            return null;
        }
        finally {
            this.mConstructorArgs[0] = null;
            this.mConstructorArgs[1] = null;
        }
    }

    private static Context themifyContext(Context context0, AttributeSet attributeSet0, boolean z, boolean z1) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.View, 0, 0);
        int v = z ? typedArray0.getResourceId(styleable.View_android_theme, 0) : 0;
        if(z1 && v == 0) {
            v = typedArray0.getResourceId(styleable.View_theme, 0);
            if(v != 0) {
                Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
            }
        }
        typedArray0.recycle();
        return v != 0 && (!(context0 instanceof ContextThemeWrapper) || ((ContextThemeWrapper)context0).getThemeResId() != v) ? new ContextThemeWrapper(context0, v) : context0;
    }

    private void verifyNotNull(View view0, String s) {
        if(view0 == null) {
            throw new IllegalStateException(this.getClass().getName() + " asked to inflate view for <" + s + ">, but returned null");
        }
    }
}

