package androidx.appcompat.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils.TruncateAt;
import android.text.TextUtils;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.R.attr;
import androidx.appcompat.app.ActionBar.Tab;
import androidx.appcompat.view.ActionBarPolicy;

public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {
    class TabAdapter extends BaseAdapter {
        @Override  // android.widget.Adapter
        public int getCount() {
            return ScrollingTabContainerView.this.mTabLayout.getChildCount();
        }

        @Override  // android.widget.Adapter
        public Object getItem(int v) {
            return ((TabView)ScrollingTabContainerView.this.mTabLayout.getChildAt(v)).getTab();
        }

        @Override  // android.widget.Adapter
        public long getItemId(int v) {
            return (long)v;
        }

        @Override  // android.widget.Adapter
        public View getView(int v, View view0, ViewGroup viewGroup0) {
            if(view0 == null) {
                Tab actionBar$Tab0 = (Tab)this.getItem(v);
                return ScrollingTabContainerView.this.createTabView(actionBar$Tab0, true);
            }
            ((TabView)view0).bindTab(((Tab)this.getItem(v)));
            return view0;
        }
    }

    class TabClickListener implements View.OnClickListener {
        @Override  // android.view.View$OnClickListener
        public void onClick(View view0) {
            ((TabView)view0).getTab().select();
            int v = ScrollingTabContainerView.this.mTabLayout.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                View view1 = ScrollingTabContainerView.this.mTabLayout.getChildAt(v1);
                view1.setSelected(view1 == view0);
            }
        }
    }

    class TabView extends LinearLayout {
        private static final String ACCESSIBILITY_CLASS_NAME = "androidx.appcompat.app.ActionBar$Tab";
        private final int[] BG_ATTRS;
        private View mCustomView;
        private ImageView mIconView;
        private Tab mTab;
        private TextView mTextView;

        public TabView(Context context0, Tab actionBar$Tab0, boolean z) {
            super(context0, null, attr.actionBarTabStyle);
            int[] arr_v = {0x10100D4};
            this.BG_ATTRS = arr_v;
            this.mTab = actionBar$Tab0;
            TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, null, arr_v, attr.actionBarTabStyle, 0);
            if(tintTypedArray0.hasValue(0)) {
                this.setBackgroundDrawable(tintTypedArray0.getDrawable(0));
            }
            tintTypedArray0.recycle();
            if(z) {
                this.setGravity(0x800013);
            }
            this.update();
        }

        public void bindTab(Tab actionBar$Tab0) {
            this.mTab = actionBar$Tab0;
            this.update();
        }

        public Tab getTab() {
            return this.mTab;
        }

        @Override  // android.view.View
        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
            super.onInitializeAccessibilityEvent(accessibilityEvent0);
            accessibilityEvent0.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override  // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
            accessibilityNodeInfo0.setClassName("androidx.appcompat.app.ActionBar$Tab");
        }

        @Override  // android.widget.LinearLayout
        public void onMeasure(int v, int v1) {
            super.onMeasure(v, v1);
            if(ScrollingTabContainerView.this.mMaxTabWidth > 0 && this.getMeasuredWidth() > ScrollingTabContainerView.this.mMaxTabWidth) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(ScrollingTabContainerView.this.mMaxTabWidth, 0x40000000), v1);
            }
        }

        @Override  // android.view.View
        public void setSelected(boolean z) {
            boolean z1 = this.isSelected() != z;
            super.setSelected(z);
            if(z1 && z) {
                this.sendAccessibilityEvent(4);
            }
        }

        public void update() {
            Tab actionBar$Tab0 = this.mTab;
            View view0 = actionBar$Tab0.getCustomView();
            CharSequence charSequence0 = null;
            if(view0 != null) {
                ViewParent viewParent0 = view0.getParent();
                if(viewParent0 != this) {
                    if(viewParent0 != null) {
                        ((ViewGroup)viewParent0).removeView(view0);
                    }
                    this.addView(view0);
                }
                this.mCustomView = view0;
                TextView textView0 = this.mTextView;
                if(textView0 != null) {
                    textView0.setVisibility(8);
                }
                ImageView imageView0 = this.mIconView;
                if(imageView0 != null) {
                    imageView0.setVisibility(8);
                    this.mIconView.setImageDrawable(null);
                }
                return;
            }
            View view1 = this.mCustomView;
            if(view1 != null) {
                this.removeView(view1);
                this.mCustomView = null;
            }
            Drawable drawable0 = actionBar$Tab0.getIcon();
            CharSequence charSequence1 = actionBar$Tab0.getText();
            if(drawable0 == null) {
                ImageView imageView1 = this.mIconView;
                if(imageView1 != null) {
                    imageView1.setVisibility(8);
                    this.mIconView.setImageDrawable(null);
                }
            }
            else {
                if(this.mIconView == null) {
                    AppCompatImageView appCompatImageView0 = new AppCompatImageView(this.getContext());
                    LinearLayout.LayoutParams linearLayout$LayoutParams0 = new LinearLayout.LayoutParams(-2, -2);
                    linearLayout$LayoutParams0.gravity = 16;
                    appCompatImageView0.setLayoutParams(linearLayout$LayoutParams0);
                    this.addView(appCompatImageView0, 0);
                    this.mIconView = appCompatImageView0;
                }
                this.mIconView.setImageDrawable(drawable0);
                this.mIconView.setVisibility(0);
            }
            boolean z = TextUtils.isEmpty(charSequence1);
            if(z) {
                TextView textView1 = this.mTextView;
                if(textView1 != null) {
                    textView1.setVisibility(8);
                    this.mTextView.setText(null);
                }
            }
            else {
                if(this.mTextView == null) {
                    AppCompatTextView appCompatTextView0 = new AppCompatTextView(this.getContext(), null, attr.actionBarTabTextStyle);
                    appCompatTextView0.setEllipsize(TextUtils.TruncateAt.END);
                    LinearLayout.LayoutParams linearLayout$LayoutParams1 = new LinearLayout.LayoutParams(-2, -2);
                    linearLayout$LayoutParams1.gravity = 16;
                    appCompatTextView0.setLayoutParams(linearLayout$LayoutParams1);
                    this.addView(appCompatTextView0);
                    this.mTextView = appCompatTextView0;
                }
                this.mTextView.setText(charSequence1);
                this.mTextView.setVisibility(0);
            }
            ImageView imageView2 = this.mIconView;
            if(imageView2 != null) {
                imageView2.setContentDescription(actionBar$Tab0.getContentDescription());
            }
            if(z) {
                charSequence0 = actionBar$Tab0.getContentDescription();
            }
            TooltipCompat.setTooltipText(this, charSequence0);
        }
    }

    public class VisibilityAnimListener extends AnimatorListenerAdapter {
        private boolean mCanceled;
        private int mFinalVisibility;

        protected VisibilityAnimListener() {
            this.mCanceled = false;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationCancel(Animator animator0) {
            this.mCanceled = true;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationEnd(Animator animator0) {
            if(this.mCanceled) {
                return;
            }
            ScrollingTabContainerView.this.mVisibilityAnim = null;
            ScrollingTabContainerView.this.setVisibility(this.mFinalVisibility);
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationStart(Animator animator0) {
            ScrollingTabContainerView.this.setVisibility(0);
            this.mCanceled = false;
        }

        public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimator viewPropertyAnimator0, int v) {
            this.mFinalVisibility = v;
            ScrollingTabContainerView.this.mVisibilityAnim = viewPropertyAnimator0;
            return this;
        }
    }

    private static final int FADE_DURATION = 200;
    private static final String TAG = "ScrollingTabContainerView";
    private boolean mAllowCollapse;
    private int mContentHeight;
    int mMaxTabWidth;
    private int mSelectedTabIndex;
    int mStackedTabMaxWidth;
    private TabClickListener mTabClickListener;
    LinearLayoutCompat mTabLayout;
    Runnable mTabSelector;
    private Spinner mTabSpinner;
    protected final VisibilityAnimListener mVisAnimListener;
    protected ViewPropertyAnimator mVisibilityAnim;
    private static final Interpolator sAlphaInterpolator;

    static {
        ScrollingTabContainerView.sAlphaInterpolator = new DecelerateInterpolator();
    }

    public ScrollingTabContainerView(Context context0) {
        super(context0);
        this.mVisAnimListener = new VisibilityAnimListener(this);
        this.setHorizontalScrollBarEnabled(false);
        ActionBarPolicy actionBarPolicy0 = ActionBarPolicy.get(context0);
        this.setContentHeight(actionBarPolicy0.getTabContainerHeight());
        this.mStackedTabMaxWidth = actionBarPolicy0.getStackedTabMaxWidth();
        LinearLayoutCompat linearLayoutCompat0 = this.createTabLayout();
        this.mTabLayout = linearLayoutCompat0;
        this.addView(linearLayoutCompat0, new ViewGroup.LayoutParams(-2, -1));
    }

    public void addTab(Tab actionBar$Tab0, int v, boolean z) {
        TabView scrollingTabContainerView$TabView0 = this.createTabView(actionBar$Tab0, false);
        this.mTabLayout.addView(scrollingTabContainerView$TabView0, v, new LayoutParams(0, -1, 1.0f));
        Spinner spinner0 = this.mTabSpinner;
        if(spinner0 != null) {
            ((TabAdapter)spinner0.getAdapter()).notifyDataSetChanged();
        }
        if(z) {
            scrollingTabContainerView$TabView0.setSelected(true);
        }
        if(this.mAllowCollapse) {
            this.requestLayout();
        }
    }

    public void addTab(Tab actionBar$Tab0, boolean z) {
        TabView scrollingTabContainerView$TabView0 = this.createTabView(actionBar$Tab0, false);
        this.mTabLayout.addView(scrollingTabContainerView$TabView0, new LayoutParams(0, -1, 1.0f));
        Spinner spinner0 = this.mTabSpinner;
        if(spinner0 != null) {
            ((TabAdapter)spinner0.getAdapter()).notifyDataSetChanged();
        }
        if(z) {
            scrollingTabContainerView$TabView0.setSelected(true);
        }
        if(this.mAllowCollapse) {
            this.requestLayout();
        }
    }

    public void animateToTab(int v) {
        View view0 = this.mTabLayout.getChildAt(v);
        Runnable runnable0 = this.mTabSelector;
        if(runnable0 != null) {
            this.removeCallbacks(runnable0);
        }
        androidx.appcompat.widget.ScrollingTabContainerView.1 scrollingTabContainerView$10 = new Runnable() {
            @Override
            public void run() {
                int v = view0.getLeft();
                int v1 = ScrollingTabContainerView.this.getWidth();
                int v2 = view0.getWidth();
                ScrollingTabContainerView.this.smoothScrollTo(v - (v1 - v2) / 2, 0);
                ScrollingTabContainerView.this.mTabSelector = null;
            }
        };
        this.mTabSelector = scrollingTabContainerView$10;
        this.post(scrollingTabContainerView$10);
    }

    public void animateToVisibility(int v) {
        ViewPropertyAnimator viewPropertyAnimator0 = this.mVisibilityAnim;
        if(viewPropertyAnimator0 != null) {
            viewPropertyAnimator0.cancel();
        }
        if(v == 0) {
            if(this.getVisibility() != 0) {
                this.setAlpha(0.0f);
            }
            ViewPropertyAnimator viewPropertyAnimator1 = this.animate().alpha(1.0f);
            viewPropertyAnimator1.setDuration(200L);
            viewPropertyAnimator1.setInterpolator(ScrollingTabContainerView.sAlphaInterpolator);
            viewPropertyAnimator1.setListener(this.mVisAnimListener.withFinalVisibility(viewPropertyAnimator1, 0));
            viewPropertyAnimator1.start();
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator2 = this.animate().alpha(0.0f);
        viewPropertyAnimator2.setDuration(200L);
        viewPropertyAnimator2.setInterpolator(ScrollingTabContainerView.sAlphaInterpolator);
        viewPropertyAnimator2.setListener(this.mVisAnimListener.withFinalVisibility(viewPropertyAnimator2, v));
        viewPropertyAnimator2.start();
    }

    private Spinner createSpinner() {
        Spinner spinner0 = new AppCompatSpinner(this.getContext(), null, attr.actionDropDownStyle);
        spinner0.setLayoutParams(new LayoutParams(-2, -1));
        spinner0.setOnItemSelectedListener(this);
        return spinner0;
    }

    private LinearLayoutCompat createTabLayout() {
        LinearLayoutCompat linearLayoutCompat0 = new LinearLayoutCompat(this.getContext(), null, attr.actionBarTabBarStyle);
        linearLayoutCompat0.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat0.setGravity(17);
        linearLayoutCompat0.setLayoutParams(new LayoutParams(-2, -1));
        return linearLayoutCompat0;
    }

    TabView createTabView(Tab actionBar$Tab0, boolean z) {
        TabView scrollingTabContainerView$TabView0 = new TabView(this, this.getContext(), actionBar$Tab0, z);
        if(z) {
            scrollingTabContainerView$TabView0.setBackgroundDrawable(null);
            scrollingTabContainerView$TabView0.setLayoutParams(new AbsListView.LayoutParams(-1, this.mContentHeight));
            return scrollingTabContainerView$TabView0;
        }
        scrollingTabContainerView$TabView0.setFocusable(true);
        if(this.mTabClickListener == null) {
            this.mTabClickListener = new TabClickListener(this);
        }
        scrollingTabContainerView$TabView0.setOnClickListener(this.mTabClickListener);
        return scrollingTabContainerView$TabView0;
    }

    private boolean isCollapsed() {
        return this.mTabSpinner != null && this.mTabSpinner.getParent() == this;
    }

    @Override  // android.view.ViewGroup
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable0 = this.mTabSelector;
        if(runnable0 != null) {
            this.post(runnable0);
        }
    }

    @Override  // android.view.View
    protected void onConfigurationChanged(Configuration configuration0) {
        super.onConfigurationChanged(configuration0);
        ActionBarPolicy actionBarPolicy0 = ActionBarPolicy.get(this.getContext());
        this.setContentHeight(actionBarPolicy0.getTabContainerHeight());
        this.mStackedTabMaxWidth = actionBarPolicy0.getStackedTabMaxWidth();
    }

    @Override  // android.view.ViewGroup
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable0 = this.mTabSelector;
        if(runnable0 != null) {
            this.removeCallbacks(runnable0);
        }
    }

    @Override  // android.widget.AdapterView$OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView0, View view0, int v, long v1) {
        ((TabView)view0).getTab().select();
    }

    @Override  // android.widget.HorizontalScrollView
    public void onMeasure(int v, int v1) {
        int v2 = View.MeasureSpec.getMode(v);
        this.setFillViewport(v2 == 0x40000000);
        int v3 = this.mTabLayout.getChildCount();
        if(v3 <= 1 || v2 != 0x80000000 && v2 != 0x40000000) {
            this.mMaxTabWidth = -1;
        }
        else {
            this.mMaxTabWidth = v3 > 2 ? ((int)(((float)View.MeasureSpec.getSize(v)) * 0.4f)) : View.MeasureSpec.getSize(v) / 2;
            this.mMaxTabWidth = Math.min(this.mMaxTabWidth, this.mStackedTabMaxWidth);
        }
        int v4 = View.MeasureSpec.makeMeasureSpec(this.mContentHeight, 0x40000000);
        if(v2 == 0x40000000 || !this.mAllowCollapse) {
            this.performExpand();
        }
        else {
            this.mTabLayout.measure(0, v4);
            if(this.mTabLayout.getMeasuredWidth() > View.MeasureSpec.getSize(v)) {
                this.performCollapse();
            }
            else {
                this.performExpand();
            }
        }
        int v5 = this.getMeasuredWidth();
        super.onMeasure(v, v4);
        if(v2 == 0x40000000 && v5 != this.getMeasuredWidth()) {
            this.setTabSelected(this.mSelectedTabIndex);
        }
    }

    @Override  // android.widget.AdapterView$OnItemSelectedListener
    public void onNothingSelected(AdapterView adapterView0) {
    }

    private void performCollapse() {
        if(this.isCollapsed()) {
            return;
        }
        if(this.mTabSpinner == null) {
            this.mTabSpinner = this.createSpinner();
        }
        this.removeView(this.mTabLayout);
        this.addView(this.mTabSpinner, new ViewGroup.LayoutParams(-2, -1));
        if(this.mTabSpinner.getAdapter() == null) {
            this.mTabSpinner.setAdapter(new TabAdapter(this));
        }
        Runnable runnable0 = this.mTabSelector;
        if(runnable0 != null) {
            this.removeCallbacks(runnable0);
            this.mTabSelector = null;
        }
        this.mTabSpinner.setSelection(this.mSelectedTabIndex);
    }

    private boolean performExpand() {
        if(!this.isCollapsed()) {
            return false;
        }
        this.removeView(this.mTabSpinner);
        this.addView(this.mTabLayout, new ViewGroup.LayoutParams(-2, -1));
        this.setTabSelected(this.mTabSpinner.getSelectedItemPosition());
        return false;
    }

    public void removeAllTabs() {
        this.mTabLayout.removeAllViews();
        Spinner spinner0 = this.mTabSpinner;
        if(spinner0 != null) {
            ((TabAdapter)spinner0.getAdapter()).notifyDataSetChanged();
        }
        if(this.mAllowCollapse) {
            this.requestLayout();
        }
    }

    public void removeTabAt(int v) {
        this.mTabLayout.removeViewAt(v);
        Spinner spinner0 = this.mTabSpinner;
        if(spinner0 != null) {
            ((TabAdapter)spinner0.getAdapter()).notifyDataSetChanged();
        }
        if(this.mAllowCollapse) {
            this.requestLayout();
        }
    }

    public void setAllowCollapse(boolean z) {
        this.mAllowCollapse = z;
    }

    public void setContentHeight(int v) {
        this.mContentHeight = v;
        this.requestLayout();
    }

    public void setTabSelected(int v) {
        this.mSelectedTabIndex = v;
        int v1 = this.mTabLayout.getChildCount();
        for(int v2 = 0; v2 < v1; ++v2) {
            boolean z = v2 == v;
            this.mTabLayout.getChildAt(v2).setSelected(z);
            if(z) {
                this.animateToTab(v);
            }
        }
        Spinner spinner0 = this.mTabSpinner;
        if(spinner0 != null && v >= 0) {
            spinner0.setSelection(v);
        }
    }

    public void updateTab(int v) {
        ((TabView)this.mTabLayout.getChildAt(v)).update();
        Spinner spinner0 = this.mTabSpinner;
        if(spinner0 != null) {
            ((TabAdapter)spinner0.getAdapter()).notifyDataSetChanged();
        }
        if(this.mAllowCollapse) {
            this.requestLayout();
        }
    }
}

