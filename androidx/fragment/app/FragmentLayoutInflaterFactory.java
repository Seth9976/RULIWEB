package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater.Factory2;
import android.view.View.OnAttachStateChangeListener;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.R.styleable;
import androidx.fragment.app.strictmode.FragmentStrictMode;

class FragmentLayoutInflaterFactory implements LayoutInflater.Factory2 {
    private static final String TAG = "FragmentManager";
    final FragmentManager mFragmentManager;

    FragmentLayoutInflaterFactory(FragmentManager fragmentManager0) {
        this.mFragmentManager = fragmentManager0;
    }

    @Override  // android.view.LayoutInflater$Factory2
    public View onCreateView(View view0, String s, Context context0, AttributeSet attributeSet0) {
        FragmentStateManager fragmentStateManager0;
        Fragment fragment0 = null;
        if("androidx.fragment.app.FragmentContainerView".equals(s)) {
            return new FragmentContainerView(context0, attributeSet0, this.mFragmentManager);
        }
        if(!"fragment".equals(s)) {
            return null;
        }
        String s1 = attributeSet0.getAttributeValue(null, "class");
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.Fragment);
        if(s1 == null) {
            s1 = typedArray0.getString(styleable.Fragment_android_name);
        }
        int v = typedArray0.getResourceId(styleable.Fragment_android_id, -1);
        String s2 = typedArray0.getString(styleable.Fragment_android_tag);
        typedArray0.recycle();
        if(s1 != null && FragmentFactory.isFragmentClass(context0.getClassLoader(), s1)) {
            int v1 = view0 == null ? 0 : view0.getId();
            if(v1 == -1 && v == -1 && s2 == null) {
                throw new IllegalArgumentException(attributeSet0.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + s1);
            }
            if(v != -1) {
                fragment0 = this.mFragmentManager.findFragmentById(v);
            }
            if(fragment0 == null && s2 != null) {
                fragment0 = this.mFragmentManager.findFragmentByTag(s2);
            }
            if(fragment0 == null && v1 != -1) {
                fragment0 = this.mFragmentManager.findFragmentById(v1);
            }
            if(fragment0 == null) {
                fragment0 = this.mFragmentManager.getFragmentFactory().instantiate(context0.getClassLoader(), s1);
                fragment0.mFromLayout = true;
                fragment0.mFragmentId = v == 0 ? v1 : v;
                fragment0.mContainerId = v1;
                fragment0.mTag = s2;
                fragment0.mInLayout = true;
                fragment0.mFragmentManager = this.mFragmentManager;
                fragment0.mHost = this.mFragmentManager.getHost();
                fragment0.onInflate(this.mFragmentManager.getHost().getContext(), attributeSet0, fragment0.mSavedFragmentState);
                fragmentStateManager0 = this.mFragmentManager.addFragment(fragment0);
                if(!FragmentManager.isLoggingEnabled(2)) {
                    goto label_44;
                }
                Log.v("FragmentManager", "Fragment " + fragment0 + " has been inflated via the <fragment> tag: id=0x" + Integer.toHexString(v));
            }
            else if(!fragment0.mInLayout) {
                fragment0.mInLayout = true;
                fragment0.mFragmentManager = this.mFragmentManager;
                fragment0.mHost = this.mFragmentManager.getHost();
                fragment0.onInflate(this.mFragmentManager.getHost().getContext(), attributeSet0, fragment0.mSavedFragmentState);
                fragmentStateManager0 = this.mFragmentManager.createOrGetFragmentStateManager(fragment0);
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Retained Fragment " + fragment0 + " has been re-attached via the <fragment> tag: id=0x" + Integer.toHexString(v));
                }
            }
            else {
                throw new IllegalArgumentException(attributeSet0.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(v) + ", tag " + s2 + ", or parent id 0x" + Integer.toHexString(v1) + " with another fragment for " + s1);
            }
        label_44:
            FragmentStrictMode.onFragmentTagUsage(fragment0, ((ViewGroup)view0));
            fragment0.mContainer = (ViewGroup)view0;
            fragmentStateManager0.moveToExpectedState();
            fragmentStateManager0.ensureInflatedView();
            if(fragment0.mView == null) {
                throw new IllegalStateException("Fragment " + s1 + " did not create a view.");
            }
            if(v != 0) {
                fragment0.mView.setId(v);
            }
            if(fragment0.mView.getTag() == null) {
                fragment0.mView.setTag(s2);
            }
            fragment0.mView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override  // android.view.View$OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view0) {
                    fragmentStateManager0.moveToExpectedState();
                    SpecialEffectsController.getOrCreateController(((ViewGroup)fragmentStateManager0.getFragment().mView.getParent()), FragmentLayoutInflaterFactory.this.mFragmentManager).forceCompleteAllOperations();
                }

                @Override  // android.view.View$OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view0) {
                }
            });
            return fragment0.mView;
        }
        return null;
    }

    @Override  // android.view.LayoutInflater$Factory
    public View onCreateView(String s, Context context0, AttributeSet attributeSet0) {
        return this.onCreateView(null, s, context0, attributeSet0);
    }
}

