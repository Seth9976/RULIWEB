package com.google.android.material.tabs;

import android.animation.Animator.AnimatorListener;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View.OnLayoutChangeListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Pools.Pool;
import androidx.core.util.Pools.SimplePool;
import androidx.core.util.Pools.SynchronizedPool;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager.DecorView;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.layout;
import com.google.android.material.R.string;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

@DecorView
public class TabLayout extends HorizontalScrollView {
    class AdapterChangeListener implements OnAdapterChangeListener {
        private boolean autoRefresh;

        @Override  // androidx.viewpager.widget.ViewPager$OnAdapterChangeListener
        public void onAdapterChanged(ViewPager viewPager0, PagerAdapter pagerAdapter0, PagerAdapter pagerAdapter1) {
            if(TabLayout.this.viewPager == viewPager0) {
                TabLayout.this.setPagerAdapter(pagerAdapter1, this.autoRefresh);
            }
        }

        void setAutoRefresh(boolean z) {
            this.autoRefresh = z;
        }
    }

    @Deprecated
    public interface BaseOnTabSelectedListener {
        void onTabReselected(Tab arg1);

        void onTabSelected(Tab arg1);

        void onTabUnselected(Tab arg1);
    }

    public @interface LabelVisibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public interface OnTabSelectedListener extends BaseOnTabSelectedListener {
    }

    class PagerAdapterObserver extends DataSetObserver {
        @Override  // android.database.DataSetObserver
        public void onChanged() {
            TabLayout.this.populateFromPagerAdapter();
        }

        @Override  // android.database.DataSetObserver
        public void onInvalidated() {
            TabLayout.this.populateFromPagerAdapter();
        }
    }

    class SlidingTabIndicator extends LinearLayout {
        ValueAnimator indicatorAnimator;
        private int layoutDirection;

        SlidingTabIndicator(Context context0) {
            super(context0);
            this.layoutDirection = -1;
            this.setWillNotDraw(false);
        }

        static void access$100(SlidingTabIndicator tabLayout$SlidingTabIndicator0) {
            tabLayout$SlidingTabIndicator0.jumpIndicatorToSelectedPosition();
        }

        void animateIndicatorToPosition(int v, int v1) {
            if(this.indicatorAnimator != null && this.indicatorAnimator.isRunning() && TabLayout.this.indicatorPosition != v) {
                this.indicatorAnimator.cancel();
            }
            this.updateOrRecreateIndicatorAnimation(true, v, v1);
        }

        boolean childrenNeedLayout() {
            int v = this.getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                if(this.getChildAt(v1).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        @Override  // android.view.View
        public void draw(Canvas canvas0) {
            int v = TabLayout.this.tabSelectedIndicator.getBounds().height();
            if(v < 0) {
                v = TabLayout.this.tabSelectedIndicator.getIntrinsicHeight();
            }
            int v1 = 0;
            int v2 = TabLayout.this.tabIndicatorGravity;
            if(v2 == 0) {
                v1 = this.getHeight() - v;
                v = this.getHeight();
            }
            else {
                switch(v2) {
                    case 1: {
                        v1 = (this.getHeight() - v) / 2;
                        v = (this.getHeight() + v) / 2;
                        break;
                    }
                    case 2: {
                        break;
                    }
                    case 3: {
                        v = this.getHeight();
                        break;
                    }
                    default: {
                        v = 0;
                    }
                }
            }
            if(TabLayout.this.tabSelectedIndicator.getBounds().width() > 0) {
                Rect rect0 = TabLayout.this.tabSelectedIndicator.getBounds();
                TabLayout.this.tabSelectedIndicator.setBounds(rect0.left, v1, rect0.right, v);
                TabLayout.this.tabSelectedIndicator.draw(canvas0);
            }
            super.draw(canvas0);
        }

        private void jumpIndicatorToIndicatorPosition() {
            if(TabLayout.this.indicatorPosition == -1) {
                TabLayout.this.indicatorPosition = TabLayout.this.getSelectedTabPosition();
            }
            this.jumpIndicatorToPosition(TabLayout.this.indicatorPosition);
        }

        private void jumpIndicatorToPosition(int v) {
            if(TabLayout.this.viewPagerScrollState != 0 && (TabLayout.this.getTabSelectedIndicator().getBounds().left != -1 || TabLayout.this.getTabSelectedIndicator().getBounds().right != -1)) {
                return;
            }
            View view0 = this.getChildAt(v);
            TabLayout.this.tabIndicatorInterpolator.setIndicatorBoundsForTab(TabLayout.this, view0, TabLayout.this.tabSelectedIndicator);
            TabLayout.this.indicatorPosition = v;
        }

        private void jumpIndicatorToSelectedPosition() {
            this.jumpIndicatorToPosition(TabLayout.this.getSelectedTabPosition());
        }

        @Override  // android.widget.LinearLayout
        protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
            super.onLayout(z, v, v1, v2, v3);
            if(this.indicatorAnimator != null && this.indicatorAnimator.isRunning()) {
                this.updateOrRecreateIndicatorAnimation(false, TabLayout.this.getSelectedTabPosition(), -1);
                return;
            }
            this.jumpIndicatorToIndicatorPosition();
        }

        @Override  // android.widget.LinearLayout
        protected void onMeasure(int v, int v1) {
            int v2 = 1;
            super.onMeasure(v, v1);
            if(View.MeasureSpec.getMode(v) == 0x40000000 && (TabLayout.this.tabGravity == 1 || TabLayout.this.mode == 2)) {
                int v3 = this.getChildCount();
                int v6 = 0;
                for(int v5 = 0; v5 < v3; ++v5) {
                    View view0 = this.getChildAt(v5);
                    if(view0.getVisibility() == 0) {
                        v6 = Math.max(v6, view0.getMeasuredWidth());
                    }
                }
                if(v6 > 0) {
                    int v7 = (int)ViewUtils.dpToPx(this.getContext(), 16);
                    if(v6 * v3 <= this.getMeasuredWidth() - v7 * 2) {
                        int v8 = 0;
                        for(int v4 = 0; v4 < v3; ++v4) {
                            LinearLayout.LayoutParams linearLayout$LayoutParams0 = (LinearLayout.LayoutParams)this.getChildAt(v4).getLayoutParams();
                            if(linearLayout$LayoutParams0.width != v6 || linearLayout$LayoutParams0.weight != 0.0f) {
                                linearLayout$LayoutParams0.width = v6;
                                linearLayout$LayoutParams0.weight = 0.0f;
                                v8 = 1;
                            }
                        }
                        v2 = v8;
                    }
                    else {
                        TabLayout.this.tabGravity = 0;
                        TabLayout.this.updateTabViews(false);
                    }
                    if(v2 != 0) {
                        super.onMeasure(v, v1);
                    }
                }
            }
        }

        @Override  // android.widget.LinearLayout
        public void onRtlPropertiesChanged(int v) {
            super.onRtlPropertiesChanged(v);
            if(Build.VERSION.SDK_INT < 23 && this.layoutDirection != v) {
                this.requestLayout();
                this.layoutDirection = v;
            }
        }

        void setIndicatorPositionFromTabPosition(int v, float f) {
            TabLayout.this.indicatorPosition = Math.round(((float)v) + f);
            if(this.indicatorAnimator != null && this.indicatorAnimator.isRunning()) {
                this.indicatorAnimator.cancel();
            }
            this.tweenIndicatorPosition(this.getChildAt(v), this.getChildAt(v + 1), f);
        }

        void setSelectedIndicatorHeight(int v) {
            Rect rect0 = TabLayout.this.tabSelectedIndicator.getBounds();
            TabLayout.this.tabSelectedIndicator.setBounds(rect0.left, 0, rect0.right, v);
            this.requestLayout();
        }

        private void tweenIndicatorPosition(View view0, View view1, float f) {
            if(view0 == null || view0.getWidth() <= 0) {
                TabLayout.this.tabSelectedIndicator.setBounds(-1, TabLayout.this.tabSelectedIndicator.getBounds().top, -1, TabLayout.this.tabSelectedIndicator.getBounds().bottom);
            }
            else {
                TabLayout.this.tabIndicatorInterpolator.updateIndicatorForOffset(TabLayout.this, view0, view1, f, TabLayout.this.tabSelectedIndicator);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }

        private void updateOrRecreateIndicatorAnimation(boolean z, int v, int v1) {
            if(TabLayout.this.indicatorPosition == v) {
                return;
            }
            View view0 = this.getChildAt(TabLayout.this.getSelectedTabPosition());
            View view1 = this.getChildAt(v);
            if(view1 == null) {
                this.jumpIndicatorToSelectedPosition();
                return;
            }
            TabLayout.this.indicatorPosition = v;
            com.google.android.material.tabs.TabLayout.SlidingTabIndicator.1 tabLayout$SlidingTabIndicator$10 = new ValueAnimator.AnimatorUpdateListener() {
                @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                    float f = valueAnimator0.getAnimatedFraction();
                    SlidingTabIndicator.this.tweenIndicatorPosition(view0, view1, f);
                }
            };
            if(z) {
                ValueAnimator valueAnimator0 = new ValueAnimator();
                this.indicatorAnimator = valueAnimator0;
                valueAnimator0.setInterpolator(TabLayout.this.tabIndicatorTimeInterpolator);
                valueAnimator0.setDuration(((long)v1));
                valueAnimator0.setFloatValues(new float[]{0.0f, 1.0f});
                valueAnimator0.addUpdateListener(tabLayout$SlidingTabIndicator$10);
                valueAnimator0.start();
                return;
            }
            this.indicatorAnimator.removeAllUpdateListeners();
            this.indicatorAnimator.addUpdateListener(tabLayout$SlidingTabIndicator$10);
        }
    }

    public static class Tab {
        public static final int INVALID_POSITION = -1;
        private CharSequence contentDesc;
        private View customView;
        private Drawable icon;
        private int id;
        private int labelVisibilityMode;
        public TabLayout parent;
        private int position;
        private Object tag;
        private CharSequence text;
        public TabView view;

        public Tab() {
            this.position = -1;
            this.labelVisibilityMode = 1;
            this.id = -1;
        }

        static int access$000(Tab tabLayout$Tab0) {
            return tabLayout$Tab0.id;
        }

        static CharSequence access$400(Tab tabLayout$Tab0) {
            return tabLayout$Tab0.text;
        }

        public BadgeDrawable getBadge() {
            return TabView.access$1000(this.view);
        }

        public CharSequence getContentDescription() {
            return this.view == null ? null : this.view.getContentDescription();
        }

        public View getCustomView() {
            return this.customView;
        }

        public Drawable getIcon() {
            return this.icon;
        }

        public int getId() {
            return this.id;
        }

        public BadgeDrawable getOrCreateBadge() {
            return TabView.access$800(this.view);
        }

        public int getPosition() {
            return this.position;
        }

        public int getTabLabelVisibility() {
            return this.labelVisibilityMode;
        }

        public Object getTag() {
            return this.tag;
        }

        public CharSequence getText() {
            return this.text;
        }

        public boolean isSelected() {
            TabLayout tabLayout0 = this.parent;
            if(tabLayout0 == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            int v = tabLayout0.getSelectedTabPosition();
            return v != -1 && v == this.position;
        }

        public void removeBadge() {
            TabView.access$900(this.view);
        }

        void reset() {
            this.parent = null;
            this.view = null;
            this.tag = null;
            this.icon = null;
            this.id = -1;
            this.text = null;
            this.contentDesc = null;
            this.position = -1;
            this.customView = null;
        }

        public void select() {
            TabLayout tabLayout0 = this.parent;
            if(tabLayout0 == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout0.selectTab(this);
        }

        public Tab setContentDescription(int v) {
            TabLayout tabLayout0 = this.parent;
            if(tabLayout0 == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return this.setContentDescription(tabLayout0.getResources().getText(v));
        }

        public Tab setContentDescription(CharSequence charSequence0) {
            this.contentDesc = charSequence0;
            this.updateView();
            return this;
        }

        public Tab setCustomView(int v) {
            return this.setCustomView(LayoutInflater.from(this.view.getContext()).inflate(v, this.view, false));
        }

        public Tab setCustomView(View view0) {
            this.customView = view0;
            this.updateView();
            return this;
        }

        public Tab setIcon(int v) {
            TabLayout tabLayout0 = this.parent;
            if(tabLayout0 == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return this.setIcon(AppCompatResources.getDrawable(tabLayout0.getContext(), v));
        }

        public Tab setIcon(Drawable drawable0) {
            this.icon = drawable0;
            if(this.parent.tabGravity == 1 || this.parent.mode == 2) {
                this.parent.updateTabViews(true);
            }
            this.updateView();
            if(BadgeUtils.USE_COMPAT_PARENT && TabView.access$600(this.view) && TabView.access$700(this.view).isVisible()) {
                this.view.invalidate();
            }
            return this;
        }

        public Tab setId(int v) {
            this.id = v;
            TabView tabLayout$TabView0 = this.view;
            if(tabLayout$TabView0 != null) {
                tabLayout$TabView0.setId(v);
            }
            return this;
        }

        void setPosition(int v) {
            this.position = v;
        }

        public Tab setTabLabelVisibility(int v) {
            this.labelVisibilityMode = v;
            if(this.parent.tabGravity == 1 || this.parent.mode == 2) {
                this.parent.updateTabViews(true);
            }
            this.updateView();
            if(BadgeUtils.USE_COMPAT_PARENT && TabView.access$600(this.view) && TabView.access$700(this.view).isVisible()) {
                this.view.invalidate();
            }
            return this;
        }

        public Tab setTag(Object object0) {
            this.tag = object0;
            return this;
        }

        public Tab setText(int v) {
            TabLayout tabLayout0 = this.parent;
            if(tabLayout0 == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return this.setText(tabLayout0.getResources().getText(v));
        }

        public Tab setText(CharSequence charSequence0) {
            if(TextUtils.isEmpty(this.contentDesc) && !TextUtils.isEmpty(charSequence0)) {
                this.view.setContentDescription(charSequence0);
            }
            this.text = charSequence0;
            this.updateView();
            return this;
        }

        void updateView() {
            TabView tabLayout$TabView0 = this.view;
            if(tabLayout$TabView0 != null) {
                tabLayout$TabView0.update();
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabGravity {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabIndicatorAnimationMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabIndicatorGravity {
    }

    public static class TabLayoutOnPageChangeListener implements OnPageChangeListener {
        private int previousScrollState;
        private int scrollState;
        private final WeakReference tabLayoutRef;

        public TabLayoutOnPageChangeListener(TabLayout tabLayout0) {
            this.tabLayoutRef = new WeakReference(tabLayout0);
        }

        @Override  // androidx.viewpager.widget.ViewPager$OnPageChangeListener
        public void onPageScrollStateChanged(int v) {
            this.previousScrollState = this.scrollState;
            this.scrollState = v;
            TabLayout tabLayout0 = (TabLayout)this.tabLayoutRef.get();
            if(tabLayout0 != null) {
                tabLayout0.updateViewPagerScrollState(this.scrollState);
            }
        }

        @Override  // androidx.viewpager.widget.ViewPager$OnPageChangeListener
        public void onPageScrolled(int v, float f, int v1) {
            Object object0 = this.tabLayoutRef.get();
            if(((TabLayout)object0) != null) {
                ((TabLayout)object0).setScrollPosition(v, f, this.scrollState != 2 || this.previousScrollState == 1, this.scrollState != 2 || this.previousScrollState != 0, false);
            }
        }

        @Override  // androidx.viewpager.widget.ViewPager$OnPageChangeListener
        public void onPageSelected(int v) {
            TabLayout tabLayout0 = (TabLayout)this.tabLayoutRef.get();
            if(tabLayout0 != null && tabLayout0.getSelectedTabPosition() != v && v < tabLayout0.getTabCount()) {
                boolean z = this.scrollState == 0 || this.scrollState == 2 && this.previousScrollState == 0;
                tabLayout0.selectTab(tabLayout0.getTabAt(v), z);
            }
        }

        void reset() {
            this.scrollState = 0;
            this.previousScrollState = 0;
        }
    }

    public final class TabView extends LinearLayout {
        private View badgeAnchorView;
        private BadgeDrawable badgeDrawable;
        private Drawable baseBackgroundDrawable;
        private ImageView customIconView;
        private TextView customTextView;
        private View customView;
        private int defaultMaxLines;
        private ImageView iconView;
        private Tab tab;
        private TextView textView;

        public TabView(Context context0) {
            super(context0);
            this.defaultMaxLines = 2;
            this.updateBackgroundDrawable(context0);
            ViewCompat.setPaddingRelative(this, tabLayout0.tabPaddingStart, tabLayout0.tabPaddingTop, tabLayout0.tabPaddingEnd, tabLayout0.tabPaddingBottom);
            this.setGravity(17);
            this.setOrientation(!tabLayout0.inlineLabel);
            this.setClickable(true);
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(this.getContext(), 1002));
        }

        static BadgeDrawable access$1000(TabView tabLayout$TabView0) {
            return tabLayout$TabView0.getBadge();
        }

        static void access$200(TabView tabLayout$TabView0, Context context0) {
            tabLayout$TabView0.updateBackgroundDrawable(context0);
        }

        static void access$500(TabView tabLayout$TabView0, Canvas canvas0) {
            tabLayout$TabView0.drawBackground(canvas0);
        }

        static boolean access$600(TabView tabLayout$TabView0) {
            return tabLayout$TabView0.hasBadgeDrawable();
        }

        static BadgeDrawable access$700(TabView tabLayout$TabView0) {
            return tabLayout$TabView0.badgeDrawable;
        }

        static BadgeDrawable access$800(TabView tabLayout$TabView0) {
            return tabLayout$TabView0.getOrCreateBadge();
        }

        static void access$900(TabView tabLayout$TabView0) {
            tabLayout$TabView0.removeBadge();
        }

        private void addOnLayoutChangeListener(View view0) {
            if(view0 == null) {
                return;
            }
            view0.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override  // android.view.View$OnLayoutChangeListener
                public void onLayoutChange(View view0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
                    if(view0.getVisibility() == 0) {
                        TabView.this.tryUpdateBadgeDrawableBounds(view0);
                    }
                }
            });
        }

        private float approximateLineWidth(Layout layout0, int v, float f) {
            return layout0.getLineWidth(v) * (f / layout0.getPaint().getTextSize());
        }

        private void clipViewToPaddingForBadge(boolean z) {
            this.setClipChildren(z);
            this.setClipToPadding(z);
            ViewGroup viewGroup0 = (ViewGroup)this.getParent();
            if(viewGroup0 != null) {
                viewGroup0.setClipChildren(z);
                viewGroup0.setClipToPadding(z);
            }
        }

        private FrameLayout createPreApi18BadgeAnchorRoot() {
            FrameLayout frameLayout0 = new FrameLayout(this.getContext());
            frameLayout0.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            return frameLayout0;
        }

        private void drawBackground(Canvas canvas0) {
            Drawable drawable0 = this.baseBackgroundDrawable;
            if(drawable0 != null) {
                drawable0.setBounds(this.getLeft(), this.getTop(), this.getRight(), this.getBottom());
                this.baseBackgroundDrawable.draw(canvas0);
            }
        }

        @Override  // android.view.ViewGroup
        protected void drawableStateChanged() {
            super.drawableStateChanged();
            int[] arr_v = this.getDrawableState();
            if((this.baseBackgroundDrawable == null || !this.baseBackgroundDrawable.isStateful() ? false : this.baseBackgroundDrawable.setState(arr_v))) {
                this.invalidate();
                TabLayout.this.invalidate();
            }
        }

        private BadgeDrawable getBadge() {
            return this.badgeDrawable;
        }

        int getContentHeight() {
            View[] arr_view = {this.textView, this.iconView, this.customView};
            int v = 0;
            int v1 = 0;
            boolean z = false;
            for(int v2 = 0; v2 < 3; ++v2) {
                View view0 = arr_view[v2];
                if(view0 != null && view0.getVisibility() == 0) {
                    v1 = z ? Math.min(v1, view0.getTop()) : view0.getTop();
                    v = z ? Math.max(v, view0.getBottom()) : view0.getBottom();
                    z = true;
                }
            }
            return v - v1;
        }

        int getContentWidth() {
            View[] arr_view = {this.textView, this.iconView, this.customView};
            int v = 0;
            int v1 = 0;
            boolean z = false;
            for(int v2 = 0; v2 < 3; ++v2) {
                View view0 = arr_view[v2];
                if(view0 != null && view0.getVisibility() == 0) {
                    v1 = z ? Math.min(v1, view0.getLeft()) : view0.getLeft();
                    v = z ? Math.max(v, view0.getRight()) : view0.getRight();
                    z = true;
                }
            }
            return v - v1;
        }

        private FrameLayout getCustomParentForBadge(View view0) {
            if(view0 != this.iconView && view0 != this.textView) {
                return null;
            }
            return BadgeUtils.USE_COMPAT_PARENT ? ((FrameLayout)view0.getParent()) : null;
        }

        private BadgeDrawable getOrCreateBadge() {
            if(this.badgeDrawable == null) {
                this.badgeDrawable = BadgeDrawable.create(this.getContext());
            }
            this.tryUpdateBadgeAnchor();
            BadgeDrawable badgeDrawable0 = this.badgeDrawable;
            if(badgeDrawable0 == null) {
                throw new IllegalStateException("Unable to create badge");
            }
            return badgeDrawable0;
        }

        public Tab getTab() {
            return this.tab;
        }

        private boolean hasBadgeDrawable() {
            return this.badgeDrawable != null;
        }

        private void inflateAndAddDefaultIconView() {
            FrameLayout frameLayout0;
            if(BadgeUtils.USE_COMPAT_PARENT) {
                frameLayout0 = this.createPreApi18BadgeAnchorRoot();
                this.addView(frameLayout0, 0);
            }
            else {
                frameLayout0 = this;
            }
            ImageView imageView0 = (ImageView)LayoutInflater.from(this.getContext()).inflate(layout.design_layout_tab_icon, frameLayout0, false);
            this.iconView = imageView0;
            frameLayout0.addView(imageView0, 0);
        }

        private void inflateAndAddDefaultTextView() {
            FrameLayout frameLayout0;
            if(BadgeUtils.USE_COMPAT_PARENT) {
                frameLayout0 = this.createPreApi18BadgeAnchorRoot();
                this.addView(frameLayout0);
            }
            else {
                frameLayout0 = this;
            }
            TextView textView0 = (TextView)LayoutInflater.from(this.getContext()).inflate(layout.design_layout_tab_text, frameLayout0, false);
            this.textView = textView0;
            frameLayout0.addView(textView0);
        }

        @Override  // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0);
            if(this.badgeDrawable != null && this.badgeDrawable.isVisible()) {
                accessibilityNodeInfoCompat0.setContentDescription(this.badgeDrawable.getContentDescription());
            }
            accessibilityNodeInfoCompat0.setCollectionItemInfo(CollectionItemInfoCompat.obtain(0, 1, this.tab.getPosition(), 1, false, this.isSelected()));
            if(this.isSelected()) {
                accessibilityNodeInfoCompat0.setClickable(false);
                accessibilityNodeInfoCompat0.removeAction(AccessibilityActionCompat.ACTION_CLICK);
            }
            accessibilityNodeInfoCompat0.setRoleDescription(this.getResources().getString(string.item_view_role_description));
        }

        @Override  // android.widget.LinearLayout
        public void onMeasure(int v, int v1) {
            int v2 = View.MeasureSpec.getSize(v);
            int v3 = View.MeasureSpec.getMode(v);
            int v4 = TabLayout.this.getTabMaxWidth();
            if(v4 > 0 && (v3 == 0 || v2 > v4)) {
                v = View.MeasureSpec.makeMeasureSpec(TabLayout.this.tabMaxWidth, 0x80000000);
            }
            super.onMeasure(v, v1);
            if(this.textView != null) {
                float f = TabLayout.this.tabTextSize;
                int v5 = this.defaultMaxLines;
                if(this.iconView != null && this.iconView.getVisibility() == 0) {
                    v5 = 1;
                }
                else if(this.textView != null && this.textView.getLineCount() > 1) {
                    f = TabLayout.this.tabTextMultiLineSize;
                }
                float f1 = this.textView.getTextSize();
                int v6 = this.textView.getLineCount();
                int v7 = TextViewCompat.getMaxLines(this.textView);
                int v8 = Float.compare(f, f1);
                if(v8 != 0 || v7 >= 0 && v5 != v7) {
                    if(TabLayout.this.mode == 1 && v8 > 0 && v6 == 1) {
                        Layout layout0 = this.textView.getLayout();
                        if(layout0 == null || this.approximateLineWidth(layout0, 0, f) > ((float)(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight()))) {
                            return;
                        }
                    }
                    this.textView.setTextSize(0, f);
                    this.textView.setMaxLines(v5);
                    super.onMeasure(v, v1);
                }
            }
        }

        @Override  // android.view.View
        public boolean performClick() {
            boolean z = super.performClick();
            if(this.tab != null) {
                if(!z) {
                    this.playSoundEffect(0);
                }
                this.tab.select();
                return true;
            }
            return z;
        }

        private void removeBadge() {
            if(this.badgeAnchorView != null) {
                this.tryRemoveBadgeFromAnchor();
            }
            this.badgeDrawable = null;
        }

        void reset() {
            this.setTab(null);
            this.setSelected(false);
        }

        @Override  // android.view.View
        public void setSelected(boolean z) {
            this.isSelected();
            super.setSelected(z);
            TextView textView0 = this.textView;
            if(textView0 != null) {
                textView0.setSelected(z);
            }
            ImageView imageView0 = this.iconView;
            if(imageView0 != null) {
                imageView0.setSelected(z);
            }
            View view0 = this.customView;
            if(view0 != null) {
                view0.setSelected(z);
            }
        }

        void setTab(Tab tabLayout$Tab0) {
            if(tabLayout$Tab0 != this.tab) {
                this.tab = tabLayout$Tab0;
                this.update();
            }
        }

        private void tryAttachBadgeToAnchor(View view0) {
            if(this.hasBadgeDrawable() && view0 != null) {
                this.clipViewToPaddingForBadge(false);
                BadgeUtils.attachBadgeDrawable(this.badgeDrawable, view0, this.getCustomParentForBadge(view0));
                this.badgeAnchorView = view0;
            }
        }

        private void tryRemoveBadgeFromAnchor() {
            if(this.hasBadgeDrawable()) {
                this.clipViewToPaddingForBadge(true);
                View view0 = this.badgeAnchorView;
                if(view0 != null) {
                    BadgeUtils.detachBadgeDrawable(this.badgeDrawable, view0);
                    this.badgeAnchorView = null;
                }
            }
        }

        private void tryUpdateBadgeAnchor() {
            if(!this.hasBadgeDrawable()) {
                return;
            }
            if(this.customView != null) {
                this.tryRemoveBadgeFromAnchor();
                return;
            }
            if(this.iconView != null && (this.tab != null && this.tab.getIcon() != null)) {
                ImageView imageView0 = this.iconView;
                if(this.badgeAnchorView != imageView0) {
                    this.tryRemoveBadgeFromAnchor();
                    this.tryAttachBadgeToAnchor(this.iconView);
                    return;
                }
                this.tryUpdateBadgeDrawableBounds(imageView0);
                return;
            }
            if(this.textView != null && (this.tab != null && this.tab.getTabLabelVisibility() == 1)) {
                TextView textView0 = this.textView;
                if(this.badgeAnchorView != textView0) {
                    this.tryRemoveBadgeFromAnchor();
                    this.tryAttachBadgeToAnchor(this.textView);
                    return;
                }
                this.tryUpdateBadgeDrawableBounds(textView0);
                return;
            }
            this.tryRemoveBadgeFromAnchor();
        }

        private void tryUpdateBadgeDrawableBounds(View view0) {
            if(this.hasBadgeDrawable() && view0 == this.badgeAnchorView) {
                BadgeUtils.setBadgeDrawableBounds(this.badgeDrawable, view0, this.getCustomParentForBadge(view0));
            }
        }

        final void update() {
            this.updateTab();
            this.setSelected(this.tab != null && this.tab.isSelected());
        }

        private void updateBackgroundDrawable(Context context0) {
            GradientDrawable gradientDrawable0 = null;
            if(TabLayout.this.tabBackgroundResId == 0) {
                this.baseBackgroundDrawable = null;
            }
            else {
                Drawable drawable0 = AppCompatResources.getDrawable(context0, TabLayout.this.tabBackgroundResId);
                this.baseBackgroundDrawable = drawable0;
                if(drawable0 != null && drawable0.isStateful()) {
                    this.baseBackgroundDrawable.setState(this.getDrawableState());
                }
            }
            GradientDrawable gradientDrawable1 = new GradientDrawable();
            gradientDrawable1.setColor(0);
            if(TabLayout.this.tabRippleColorStateList != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(0.00001f);
                gradientDrawable2.setColor(-1);
                ColorStateList colorStateList0 = RippleUtils.convertToRippleDrawableColor(TabLayout.this.tabRippleColorStateList);
                if(TabLayout.this.unboundedRipple) {
                    gradientDrawable1 = null;
                }
                if(!TabLayout.this.unboundedRipple) {
                    gradientDrawable0 = gradientDrawable2;
                }
                gradientDrawable1 = new RippleDrawable(colorStateList0, gradientDrawable1, gradientDrawable0);
            }
            ViewCompat.setBackground(this, gradientDrawable1);
            TabLayout.this.invalidate();
        }

        final void updateOrientation() {
            this.setOrientation(!TabLayout.this.inlineLabel);
            TextView textView0 = this.customTextView;
            if(textView0 == null && this.customIconView == null) {
                this.updateTextAndIcon(this.textView, this.iconView, true);
                return;
            }
            this.updateTextAndIcon(textView0, this.customIconView, false);
        }

        final void updateTab() {
            Tab tabLayout$Tab0 = this.tab;
            View view0 = tabLayout$Tab0 == null ? null : tabLayout$Tab0.getCustomView();
            if(view0 == null) {
                View view2 = this.customView;
                if(view2 != null) {
                    this.removeView(view2);
                    this.customView = null;
                }
                this.customTextView = null;
                this.customIconView = null;
            }
            else {
                ViewParent viewParent0 = view0.getParent();
                if(viewParent0 != this) {
                    if(viewParent0 != null) {
                        ((ViewGroup)viewParent0).removeView(view0);
                    }
                    View view1 = this.customView;
                    if(view1 != null) {
                        ViewParent viewParent1 = view1.getParent();
                        if(viewParent1 != null) {
                            ((ViewGroup)viewParent1).removeView(this.customView);
                        }
                    }
                    this.addView(view0);
                }
                this.customView = view0;
                TextView textView0 = this.textView;
                if(textView0 != null) {
                    textView0.setVisibility(8);
                }
                ImageView imageView0 = this.iconView;
                if(imageView0 != null) {
                    imageView0.setVisibility(8);
                    this.iconView.setImageDrawable(null);
                }
                TextView textView1 = (TextView)view0.findViewById(0x1020014);
                this.customTextView = textView1;
                if(textView1 != null) {
                    this.defaultMaxLines = TextViewCompat.getMaxLines(textView1);
                }
                this.customIconView = (ImageView)view0.findViewById(0x1020006);
            }
            if(this.customView == null) {
                if(this.iconView == null) {
                    this.inflateAndAddDefaultIconView();
                }
                if(this.textView == null) {
                    this.inflateAndAddDefaultTextView();
                    this.defaultMaxLines = TextViewCompat.getMaxLines(this.textView);
                }
                TextViewCompat.setTextAppearance(this.textView, TabLayout.this.defaultTabTextAppearance);
                if(!this.isSelected() || TabLayout.this.selectedTabTextAppearance == -1) {
                    TextViewCompat.setTextAppearance(this.textView, TabLayout.this.tabTextAppearance);
                }
                else {
                    TextViewCompat.setTextAppearance(this.textView, TabLayout.this.selectedTabTextAppearance);
                }
                if(TabLayout.this.tabTextColors != null) {
                    this.textView.setTextColor(TabLayout.this.tabTextColors);
                }
                this.updateTextAndIcon(this.textView, this.iconView, true);
                this.tryUpdateBadgeAnchor();
                this.addOnLayoutChangeListener(this.iconView);
                this.addOnLayoutChangeListener(this.textView);
            }
            else {
                TextView textView2 = this.customTextView;
                if(textView2 != null || this.customIconView != null) {
                    this.updateTextAndIcon(textView2, this.customIconView, false);
                }
            }
            if(tabLayout$Tab0 != null && !TextUtils.isEmpty(tabLayout$Tab0.contentDesc)) {
                this.setContentDescription(tabLayout$Tab0.contentDesc);
            }
        }

        private void updateTextAndIcon(TextView textView0, ImageView imageView0, boolean z) {
            CharSequence charSequence0 = null;
            Drawable drawable0 = this.tab == null || this.tab.getIcon() == null ? null : DrawableCompat.wrap(this.tab.getIcon()).mutate();
            if(drawable0 != null) {
                DrawableCompat.setTintList(drawable0, TabLayout.this.tabIconTint);
                if(TabLayout.this.tabIconTintMode != null) {
                    DrawableCompat.setTintMode(drawable0, TabLayout.this.tabIconTintMode);
                }
            }
            CharSequence charSequence1 = this.tab == null ? null : this.tab.getText();
            if(imageView0 != null) {
                if(drawable0 == null) {
                    imageView0.setVisibility(8);
                    imageView0.setImageDrawable(null);
                }
                else {
                    imageView0.setImageDrawable(drawable0);
                    imageView0.setVisibility(0);
                    this.setVisibility(0);
                }
            }
            boolean z1 = true;
            boolean z2 = TextUtils.isEmpty(charSequence1);
            if(textView0 == null) {
                z1 = false;
            }
            else {
                if(z2 || this.tab.labelVisibilityMode != 1) {
                    z1 = false;
                }
                textView0.setText((z2 ? null : charSequence1));
                textView0.setVisibility((z1 ? 0 : 8));
                if(!z2) {
                    this.setVisibility(0);
                }
            }
            if(z && imageView0 != null) {
                ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)imageView0.getLayoutParams();
                int v = !z1 || imageView0.getVisibility() != 0 ? 0 : ((int)ViewUtils.dpToPx(this.getContext(), 8));
                if(!TabLayout.this.inlineLabel) {
                    if(v != viewGroup$MarginLayoutParams0.bottomMargin) {
                        viewGroup$MarginLayoutParams0.bottomMargin = v;
                        MarginLayoutParamsCompat.setMarginEnd(viewGroup$MarginLayoutParams0, 0);
                        imageView0.setLayoutParams(viewGroup$MarginLayoutParams0);
                        imageView0.requestLayout();
                    }
                }
                else if(v != MarginLayoutParamsCompat.getMarginEnd(viewGroup$MarginLayoutParams0)) {
                    MarginLayoutParamsCompat.setMarginEnd(viewGroup$MarginLayoutParams0, v);
                    viewGroup$MarginLayoutParams0.bottomMargin = 0;
                    imageView0.setLayoutParams(viewGroup$MarginLayoutParams0);
                    imageView0.requestLayout();
                }
            }
            Tab tabLayout$Tab0 = this.tab;
            if(tabLayout$Tab0 != null) {
                charSequence0 = tabLayout$Tab0.contentDesc;
            }
            if(Build.VERSION.SDK_INT > 23) {
                if(z2) {
                    charSequence1 = charSequence0;
                }
                TooltipCompat.setTooltipText(this, charSequence1);
            }
        }
    }

    public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private final ViewPager viewPager;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager0) {
            this.viewPager = viewPager0;
        }

        @Override  // com.google.android.material.tabs.TabLayout$BaseOnTabSelectedListener
        public void onTabReselected(Tab tabLayout$Tab0) {
        }

        @Override  // com.google.android.material.tabs.TabLayout$BaseOnTabSelectedListener
        public void onTabSelected(Tab tabLayout$Tab0) {
            this.viewPager.setCurrentItem(tabLayout$Tab0.getPosition());
        }

        @Override  // com.google.android.material.tabs.TabLayout$BaseOnTabSelectedListener
        public void onTabUnselected(Tab tabLayout$Tab0) {
        }
    }

    private static final int ANIMATION_DURATION = 300;
    static final int DEFAULT_GAP_TEXT_ICON = 8;
    private static final int DEFAULT_HEIGHT = 0x30;
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
    private static final int DEF_STYLE_RES = 0;
    static final int FIXED_WRAP_GUTTER_MIN = 16;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    public static final int GRAVITY_START = 2;
    public static final int INDICATOR_ANIMATION_MODE_ELASTIC = 1;
    public static final int INDICATOR_ANIMATION_MODE_FADE = 2;
    public static final int INDICATOR_ANIMATION_MODE_LINEAR = 0;
    public static final int INDICATOR_GRAVITY_BOTTOM = 0;
    public static final int INDICATOR_GRAVITY_CENTER = 1;
    public static final int INDICATOR_GRAVITY_STRETCH = 3;
    public static final int INDICATOR_GRAVITY_TOP = 2;
    private static final int INVALID_WIDTH = -1;
    private static final String LOG_TAG = "TabLayout";
    public static final int MODE_AUTO = 2;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    private static final int SELECTED_INDICATOR_HEIGHT_DEFAULT = -1;
    public static final int TAB_LABEL_VISIBILITY_LABELED = 1;
    public static final int TAB_LABEL_VISIBILITY_UNLABELED = 0;
    private static final int TAB_MIN_WIDTH_MARGIN = 56;
    private AdapterChangeListener adapterChangeListener;
    private int contentInsetStart;
    private BaseOnTabSelectedListener currentVpSelectedListener;
    private final int defaultTabTextAppearance;
    int indicatorPosition;
    boolean inlineLabel;
    int mode;
    private TabLayoutOnPageChangeListener pageChangeListener;
    private PagerAdapter pagerAdapter;
    private DataSetObserver pagerAdapterObserver;
    private final int requestedTabMaxWidth;
    private final int requestedTabMinWidth;
    private ValueAnimator scrollAnimator;
    private final int scrollableTabMinWidth;
    private BaseOnTabSelectedListener selectedListener;
    private final ArrayList selectedListeners;
    private Tab selectedTab;
    private int selectedTabTextAppearance;
    float selectedTabTextSize;
    private boolean setupViewPagerImplicitly;
    final SlidingTabIndicator slidingTabIndicator;
    final int tabBackgroundResId;
    int tabGravity;
    ColorStateList tabIconTint;
    PorterDuff.Mode tabIconTintMode;
    int tabIndicatorAnimationDuration;
    int tabIndicatorAnimationMode;
    boolean tabIndicatorFullWidth;
    int tabIndicatorGravity;
    int tabIndicatorHeight;
    private TabIndicatorInterpolator tabIndicatorInterpolator;
    private final TimeInterpolator tabIndicatorTimeInterpolator;
    int tabMaxWidth;
    int tabPaddingBottom;
    int tabPaddingEnd;
    int tabPaddingStart;
    int tabPaddingTop;
    private static final Pool tabPool;
    ColorStateList tabRippleColorStateList;
    Drawable tabSelectedIndicator;
    private int tabSelectedIndicatorColor;
    private final int tabTextAppearance;
    ColorStateList tabTextColors;
    float tabTextMultiLineSize;
    float tabTextSize;
    private final Pool tabViewPool;
    private final ArrayList tabs;
    boolean unboundedRipple;
    ViewPager viewPager;
    private int viewPagerScrollState;

    static {
        TabLayout.DEF_STYLE_RES = style.Widget_Design_TabLayout;
        TabLayout.tabPool = new SynchronizedPool(16);
    }

    public TabLayout(Context context0) {
        this(context0, null);
    }

    public TabLayout(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.tabStyle);
    }

    public TabLayout(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, TabLayout.DEF_STYLE_RES), attributeSet0, v);
        this.indicatorPosition = -1;
        this.tabs = new ArrayList();
        this.selectedTabTextAppearance = -1;
        this.tabSelectedIndicatorColor = 0;
        this.tabMaxWidth = 0x7FFFFFFF;
        this.tabIndicatorHeight = -1;
        this.selectedListeners = new ArrayList();
        this.tabViewPool = new SimplePool(12);
        Context context1 = this.getContext();
        this.setHorizontalScrollBarEnabled(false);
        SlidingTabIndicator tabLayout$SlidingTabIndicator0 = new SlidingTabIndicator(this, context1);
        this.slidingTabIndicator = tabLayout$SlidingTabIndicator0;
        super.addView(tabLayout$SlidingTabIndicator0, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.TabLayout, v, TabLayout.DEF_STYLE_RES, new int[]{styleable.TabLayout_tabTextAppearance});
        ColorStateList colorStateList0 = DrawableUtils.getColorStateListOrNull(this.getBackground());
        if(colorStateList0 != null) {
            MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable();
            materialShapeDrawable0.setFillColor(colorStateList0);
            materialShapeDrawable0.initializeElevationOverlay(context1);
            materialShapeDrawable0.setElevation(ViewCompat.getElevation(this));
            ViewCompat.setBackground(this, materialShapeDrawable0);
        }
        this.setSelectedTabIndicator(MaterialResources.getDrawable(context1, typedArray0, styleable.TabLayout_tabIndicator));
        this.setSelectedTabIndicatorColor(typedArray0.getColor(styleable.TabLayout_tabIndicatorColor, 0));
        tabLayout$SlidingTabIndicator0.setSelectedIndicatorHeight(typedArray0.getDimensionPixelSize(styleable.TabLayout_tabIndicatorHeight, -1));
        this.setSelectedTabIndicatorGravity(typedArray0.getInt(styleable.TabLayout_tabIndicatorGravity, 0));
        this.setTabIndicatorAnimationMode(typedArray0.getInt(styleable.TabLayout_tabIndicatorAnimationMode, 0));
        this.setTabIndicatorFullWidth(typedArray0.getBoolean(styleable.TabLayout_tabIndicatorFullWidth, true));
        int v1 = typedArray0.getDimensionPixelSize(styleable.TabLayout_tabPadding, 0);
        this.tabPaddingBottom = v1;
        this.tabPaddingEnd = v1;
        this.tabPaddingTop = v1;
        this.tabPaddingStart = typedArray0.getDimensionPixelSize(styleable.TabLayout_tabPaddingStart, this.tabPaddingStart);
        this.tabPaddingTop = typedArray0.getDimensionPixelSize(styleable.TabLayout_tabPaddingTop, this.tabPaddingTop);
        this.tabPaddingEnd = typedArray0.getDimensionPixelSize(styleable.TabLayout_tabPaddingEnd, this.tabPaddingEnd);
        this.tabPaddingBottom = typedArray0.getDimensionPixelSize(styleable.TabLayout_tabPaddingBottom, this.tabPaddingBottom);
        this.defaultTabTextAppearance = ThemeEnforcement.isMaterial3Theme(context1) ? attr.textAppearanceTitleSmall : attr.textAppearanceButton;
        int v2 = typedArray0.getResourceId(styleable.TabLayout_tabTextAppearance, style.TextAppearance_Design_Tab);
        this.tabTextAppearance = v2;
        TypedArray typedArray1 = context1.obtainStyledAttributes(v2, androidx.appcompat.R.styleable.TextAppearance);
        try {
            this.tabTextSize = (float)typedArray1.getDimensionPixelSize(androidx.appcompat.R.styleable.TextAppearance_android_textSize, 0);
            this.tabTextColors = MaterialResources.getColorStateList(context1, typedArray1, androidx.appcompat.R.styleable.TextAppearance_android_textColor);
        }
        finally {
            typedArray1.recycle();
        }
        if(typedArray0.hasValue(styleable.TabLayout_tabSelectedTextAppearance)) {
            this.selectedTabTextAppearance = typedArray0.getResourceId(styleable.TabLayout_tabSelectedTextAppearance, v2);
        }
        int v4 = this.selectedTabTextAppearance;
        if(v4 != -1) {
            TypedArray typedArray2 = context1.obtainStyledAttributes(v4, androidx.appcompat.R.styleable.TextAppearance);
            try {
                this.selectedTabTextSize = (float)typedArray2.getDimensionPixelSize(androidx.appcompat.R.styleable.TextAppearance_android_textSize, ((int)this.tabTextSize));
                ColorStateList colorStateList1 = MaterialResources.getColorStateList(context1, typedArray2, androidx.appcompat.R.styleable.TextAppearance_android_textColor);
                if(colorStateList1 != null) {
                    int v6 = this.tabTextColors.getDefaultColor();
                    int v7 = colorStateList1.getDefaultColor();
                    this.tabTextColors = TabLayout.createColorStateList(v6, colorStateList1.getColorForState(new int[]{0x10100A1}, v7));
                }
            }
            finally {
                typedArray2.recycle();
            }
        }
        if(typedArray0.hasValue(styleable.TabLayout_tabTextColor)) {
            this.tabTextColors = MaterialResources.getColorStateList(context1, typedArray0, styleable.TabLayout_tabTextColor);
        }
        if(typedArray0.hasValue(styleable.TabLayout_tabSelectedTextColor)) {
            int v8 = typedArray0.getColor(styleable.TabLayout_tabSelectedTextColor, 0);
            this.tabTextColors = TabLayout.createColorStateList(this.tabTextColors.getDefaultColor(), v8);
        }
        this.tabIconTint = MaterialResources.getColorStateList(context1, typedArray0, styleable.TabLayout_tabIconTint);
        this.tabIconTintMode = ViewUtils.parseTintMode(typedArray0.getInt(styleable.TabLayout_tabIconTintMode, -1), null);
        this.tabRippleColorStateList = MaterialResources.getColorStateList(context1, typedArray0, styleable.TabLayout_tabRippleColor);
        this.tabIndicatorAnimationDuration = typedArray0.getInt(styleable.TabLayout_tabIndicatorAnimationDuration, 300);
        this.tabIndicatorTimeInterpolator = MotionUtils.resolveThemeInterpolator(context1, attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        this.requestedTabMinWidth = typedArray0.getDimensionPixelSize(styleable.TabLayout_tabMinWidth, -1);
        this.requestedTabMaxWidth = typedArray0.getDimensionPixelSize(styleable.TabLayout_tabMaxWidth, -1);
        this.tabBackgroundResId = typedArray0.getResourceId(styleable.TabLayout_tabBackground, 0);
        this.contentInsetStart = typedArray0.getDimensionPixelSize(styleable.TabLayout_tabContentStart, 0);
        this.mode = typedArray0.getInt(styleable.TabLayout_tabMode, 1);
        this.tabGravity = typedArray0.getInt(styleable.TabLayout_tabGravity, 0);
        this.inlineLabel = typedArray0.getBoolean(styleable.TabLayout_tabInlineLabel, false);
        this.unboundedRipple = typedArray0.getBoolean(styleable.TabLayout_tabUnboundedRipple, false);
        typedArray0.recycle();
        Resources resources0 = this.getResources();
        this.tabTextMultiLineSize = (float)resources0.getDimensionPixelSize(dimen.design_tab_text_size_2line);
        this.scrollableTabMinWidth = resources0.getDimensionPixelSize(dimen.design_tab_scrollable_min_width);
        this.applyModeAndGravity();
    }

    @Deprecated
    public void addOnTabSelectedListener(BaseOnTabSelectedListener tabLayout$BaseOnTabSelectedListener0) {
        if(!this.selectedListeners.contains(tabLayout$BaseOnTabSelectedListener0)) {
            this.selectedListeners.add(tabLayout$BaseOnTabSelectedListener0);
        }
    }

    public void addOnTabSelectedListener(OnTabSelectedListener tabLayout$OnTabSelectedListener0) {
        this.addOnTabSelectedListener(tabLayout$OnTabSelectedListener0);
    }

    public void addTab(Tab tabLayout$Tab0) {
        this.addTab(tabLayout$Tab0, this.tabs.isEmpty());
    }

    public void addTab(Tab tabLayout$Tab0, int v) {
        this.addTab(tabLayout$Tab0, v, this.tabs.isEmpty());
    }

    public void addTab(Tab tabLayout$Tab0, int v, boolean z) {
        if(tabLayout$Tab0.parent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        this.configureTab(tabLayout$Tab0, v);
        this.addTabView(tabLayout$Tab0);
        if(z) {
            tabLayout$Tab0.select();
        }
    }

    public void addTab(Tab tabLayout$Tab0, boolean z) {
        this.addTab(tabLayout$Tab0, this.tabs.size(), z);
    }

    private void addTabFromItemView(TabItem tabItem0) {
        Tab tabLayout$Tab0 = this.newTab();
        if(tabItem0.text != null) {
            tabLayout$Tab0.setText(tabItem0.text);
        }
        if(tabItem0.icon != null) {
            tabLayout$Tab0.setIcon(tabItem0.icon);
        }
        if(tabItem0.customLayout != 0) {
            tabLayout$Tab0.setCustomView(tabItem0.customLayout);
        }
        if(!TextUtils.isEmpty(tabItem0.getContentDescription())) {
            tabLayout$Tab0.setContentDescription(tabItem0.getContentDescription());
        }
        this.addTab(tabLayout$Tab0);
    }

    private void addTabView(Tab tabLayout$Tab0) {
        TabView tabLayout$TabView0 = tabLayout$Tab0.view;
        tabLayout$TabView0.setSelected(false);
        tabLayout$TabView0.setActivated(false);
        LinearLayout.LayoutParams linearLayout$LayoutParams0 = this.createLayoutParamsForTabs();
        this.slidingTabIndicator.addView(tabLayout$TabView0, tabLayout$Tab0.getPosition(), linearLayout$LayoutParams0);
    }

    @Override  // android.widget.HorizontalScrollView
    public void addView(View view0) {
        this.addViewInternal(view0);
    }

    @Override  // android.widget.HorizontalScrollView
    public void addView(View view0, int v) {
        this.addViewInternal(view0);
    }

    @Override  // android.widget.HorizontalScrollView
    public void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        this.addViewInternal(view0);
    }

    @Override  // android.widget.HorizontalScrollView
    public void addView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        this.addViewInternal(view0);
    }

    private void addViewInternal(View view0) {
        if(!(view0 instanceof TabItem)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        this.addTabFromItemView(((TabItem)view0));
    }

    private void animateToTab(int v) {
        if(v == -1) {
            return;
        }
        if(this.getWindowToken() != null && ViewCompat.isLaidOut(this) && !this.slidingTabIndicator.childrenNeedLayout()) {
            int v1 = this.getScrollX();
            int v2 = this.calculateScrollXForTab(v, 0.0f);
            if(v1 != v2) {
                this.ensureScrollAnimator();
                this.scrollAnimator.setIntValues(new int[]{v1, v2});
                this.scrollAnimator.start();
            }
            this.slidingTabIndicator.animateIndicatorToPosition(v, this.tabIndicatorAnimationDuration);
            return;
        }
        this.setScrollPosition(v, 0.0f, true);
    }

    private void applyGravityForModeScrollable(int v) {
        switch(v) {
            case 0: {
                Log.w("TabLayout", "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
                break;
            }
            case 1: {
                this.slidingTabIndicator.setGravity(1);
                return;
            }
            case 2: {
                break;
            }
            default: {
                return;
            }
        }
        this.slidingTabIndicator.setGravity(0x800003);
    }

    private void applyModeAndGravity() {
        ViewCompat.setPaddingRelative(this.slidingTabIndicator, (this.mode == 0 || this.mode == 2 ? Math.max(0, this.contentInsetStart - this.tabPaddingStart) : 0), 0, 0, 0);
        switch(this.mode) {
            case 0: {
                this.applyGravityForModeScrollable(this.tabGravity);
                break;
            }
            case 1: 
            case 2: {
                if(this.tabGravity == 2) {
                    Log.w("TabLayout", "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
                }
                this.slidingTabIndicator.setGravity(1);
            }
        }
        this.updateTabViews(true);
    }

    private int calculateScrollXForTab(int v, float f) {
        int v1 = 0;
        if(this.mode != 0 && this.mode != 2) {
            return 0;
        }
        View view0 = this.slidingTabIndicator.getChildAt(v);
        if(view0 == null) {
            return 0;
        }
        View view1 = v + 1 >= this.slidingTabIndicator.getChildCount() ? null : this.slidingTabIndicator.getChildAt(v + 1);
        int v2 = view0.getWidth();
        if(view1 != null) {
            v1 = view1.getWidth();
        }
        int v3 = view0.getLeft() + v2 / 2 - this.getWidth() / 2;
        int v4 = (int)(((float)(v2 + v1)) * 0.5f * f);
        return ViewCompat.getLayoutDirection(this) == 0 ? v3 + v4 : v3 - v4;
    }

    public void clearOnTabSelectedListeners() {
        this.selectedListeners.clear();
    }

    private void configureTab(Tab tabLayout$Tab0, int v) {
        tabLayout$Tab0.setPosition(v);
        this.tabs.add(v, tabLayout$Tab0);
        int v1 = this.tabs.size();
        int v2 = v + 1;
        int v3 = -1;
        while(v2 < v1) {
            if(((Tab)this.tabs.get(v2)).getPosition() == this.indicatorPosition) {
                v3 = v2;
            }
            ((Tab)this.tabs.get(v2)).setPosition(v2);
            ++v2;
        }
        this.indicatorPosition = v3;
    }

    private static ColorStateList createColorStateList(int v, int v1) {
        int[][] arr2_v = new int[2][];
        int[] arr_v = new int[2];
        arr2_v[0] = TabLayout.SELECTED_STATE_SET;
        arr_v[0] = v1;
        arr2_v[1] = TabLayout.EMPTY_STATE_SET;
        arr_v[1] = v;
        return new ColorStateList(arr2_v, arr_v);
    }

    private LinearLayout.LayoutParams createLayoutParamsForTabs() {
        LinearLayout.LayoutParams linearLayout$LayoutParams0 = new LinearLayout.LayoutParams(-2, -1);
        this.updateTabViewLayoutParams(linearLayout$LayoutParams0);
        return linearLayout$LayoutParams0;
    }

    protected Tab createTabFromPool() {
        Tab tabLayout$Tab0 = (Tab)TabLayout.tabPool.acquire();
        return tabLayout$Tab0 == null ? new Tab() : tabLayout$Tab0;
    }

    private TabView createTabView(Tab tabLayout$Tab0) {
        TabView tabLayout$TabView0 = this.tabViewPool == null ? null : ((TabView)this.tabViewPool.acquire());
        if(tabLayout$TabView0 == null) {
            tabLayout$TabView0 = new TabView(this, this.getContext());
        }
        tabLayout$TabView0.setTab(tabLayout$Tab0);
        tabLayout$TabView0.setFocusable(true);
        tabLayout$TabView0.setMinimumWidth(this.getTabMinWidth());
        if(TextUtils.isEmpty(Tab.access$300(tabLayout$Tab0))) {
            tabLayout$TabView0.setContentDescription(Tab.access$400(tabLayout$Tab0));
            return tabLayout$TabView0;
        }
        tabLayout$TabView0.setContentDescription(Tab.access$300(tabLayout$Tab0));
        return tabLayout$TabView0;
    }

    private void dispatchTabReselected(Tab tabLayout$Tab0) {
        for(int v = this.selectedListeners.size() - 1; v >= 0; --v) {
            ((BaseOnTabSelectedListener)this.selectedListeners.get(v)).onTabReselected(tabLayout$Tab0);
        }
    }

    private void dispatchTabSelected(Tab tabLayout$Tab0) {
        for(int v = this.selectedListeners.size() - 1; v >= 0; --v) {
            ((BaseOnTabSelectedListener)this.selectedListeners.get(v)).onTabSelected(tabLayout$Tab0);
        }
    }

    private void dispatchTabUnselected(Tab tabLayout$Tab0) {
        for(int v = this.selectedListeners.size() - 1; v >= 0; --v) {
            ((BaseOnTabSelectedListener)this.selectedListeners.get(v)).onTabUnselected(tabLayout$Tab0);
        }
    }

    private void ensureScrollAnimator() {
        if(this.scrollAnimator == null) {
            ValueAnimator valueAnimator0 = new ValueAnimator();
            this.scrollAnimator = valueAnimator0;
            valueAnimator0.setInterpolator(this.tabIndicatorTimeInterpolator);
            this.scrollAnimator.setDuration(((long)this.tabIndicatorAnimationDuration));
            this.scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                    int v = (int)(((Integer)valueAnimator0.getAnimatedValue()));
                    TabLayout.this.scrollTo(v, 0);
                }
            });
        }
    }

    @Override  // android.widget.FrameLayout
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return this.generateLayoutParams(attributeSet0);
    }

    @Override  // android.widget.FrameLayout
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return this.generateDefaultLayoutParams();
    }

    private int getDefaultHeight() {
        int v = this.tabs.size();
        for(int v1 = 0; v1 < v; ++v1) {
            Tab tabLayout$Tab0 = (Tab)this.tabs.get(v1);
            if(tabLayout$Tab0 != null && tabLayout$Tab0.getIcon() != null && !TextUtils.isEmpty(tabLayout$Tab0.getText())) {
                return this.inlineLabel ? 0x30 : 72;
            }
        }
        return 0x30;
    }

    public int getSelectedTabPosition() {
        return this.selectedTab == null ? -1 : this.selectedTab.getPosition();
    }

    public Tab getTabAt(int v) {
        return v < 0 || v >= this.getTabCount() ? null : ((Tab)this.tabs.get(v));
    }

    public int getTabCount() {
        return this.tabs.size();
    }

    public int getTabGravity() {
        return this.tabGravity;
    }

    public ColorStateList getTabIconTint() {
        return this.tabIconTint;
    }

    public int getTabIndicatorAnimationMode() {
        return this.tabIndicatorAnimationMode;
    }

    public int getTabIndicatorGravity() {
        return this.tabIndicatorGravity;
    }

    int getTabMaxWidth() {
        return this.tabMaxWidth;
    }

    private int getTabMinWidth() {
        int v = this.requestedTabMinWidth;
        if(v != -1) {
            return v;
        }
        return this.mode == 0 || this.mode == 2 ? this.scrollableTabMinWidth : 0;
    }

    public int getTabMode() {
        return this.mode;
    }

    public ColorStateList getTabRippleColor() {
        return this.tabRippleColorStateList;
    }

    private int getTabScrollRange() {
        return Math.max(0, this.slidingTabIndicator.getWidth() - this.getWidth() - this.getPaddingLeft() - this.getPaddingRight());
    }

    public Drawable getTabSelectedIndicator() {
        return this.tabSelectedIndicator;
    }

    public ColorStateList getTabTextColors() {
        return this.tabTextColors;
    }

    public boolean hasUnboundedRipple() {
        return this.unboundedRipple;
    }

    public boolean isInlineLabel() {
        return this.inlineLabel;
    }

    private boolean isScrollingEnabled() {
        return this.getTabMode() == 0 || this.getTabMode() == 2;
    }

    public boolean isTabIndicatorFullWidth() {
        return this.tabIndicatorFullWidth;
    }

    public Tab newTab() {
        Tab tabLayout$Tab0 = this.createTabFromPool();
        tabLayout$Tab0.parent = this;
        tabLayout$Tab0.view = this.createTabView(tabLayout$Tab0);
        if(Tab.access$000(tabLayout$Tab0) != -1) {
            tabLayout$Tab0.view.setId(Tab.access$000(tabLayout$Tab0));
        }
        return tabLayout$Tab0;
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
        if(this.viewPager == null) {
            ViewParent viewParent0 = this.getParent();
            if(viewParent0 instanceof ViewPager) {
                this.setupWithViewPager(((ViewPager)viewParent0), true, true);
            }
        }
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.setupViewPagerImplicitly) {
            this.setupWithViewPager(null);
            this.setupViewPagerImplicitly = false;
        }
    }

    @Override  // android.view.View
    protected void onDraw(Canvas canvas0) {
        for(int v = 0; v < this.slidingTabIndicator.getChildCount(); ++v) {
            View view0 = this.slidingTabIndicator.getChildAt(v);
            if(view0 instanceof TabView) {
                TabView.access$500(((TabView)view0), canvas0);
            }
        }
        super.onDraw(canvas0);
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0).setCollectionInfo(CollectionInfoCompat.obtain(1, this.getTabCount(), false, 1));
    }

    // 去混淆评级： 低(20)
    @Override  // android.widget.HorizontalScrollView
    public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        return this.isScrollingEnabled() && super.onInterceptTouchEvent(motionEvent0);
    }

    @Override  // android.widget.HorizontalScrollView
    protected void onMeasure(int v, int v1) {
        int v5;
        int v2 = Math.round(ViewUtils.dpToPx(this.getContext(), this.getDefaultHeight()));
        switch(View.MeasureSpec.getMode(v1)) {
            case 0x80000000: {
                if(this.getChildCount() == 1 && View.MeasureSpec.getSize(v1) >= v2) {
                    this.getChildAt(0).setMinimumHeight(v2);
                }
                break;
            }
            case 0: {
                v1 = View.MeasureSpec.makeMeasureSpec(v2 + this.getPaddingTop() + this.getPaddingBottom(), 0x40000000);
            }
        }
        int v3 = View.MeasureSpec.getSize(v);
        if(View.MeasureSpec.getMode(v) != 0) {
            this.tabMaxWidth = this.requestedTabMaxWidth > 0 ? this.requestedTabMaxWidth : ((int)(((float)v3) - ViewUtils.dpToPx(this.getContext(), 56)));
        }
        super.onMeasure(v, v1);
        if(this.getChildCount() == 1) {
            View view0 = this.getChildAt(0);
            int v4 = this.mode;
            switch(v4) {
                case 0: {
                label_17:
                    if(view0.getMeasuredWidth() < this.getMeasuredWidth()) {
                        v5 = TabLayout.getChildMeasureSpec(v1, this.getPaddingTop() + this.getPaddingBottom(), view0.getLayoutParams().height);
                        view0.measure(View.MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0x40000000), v5);
                        return;
                    }
                    break;
                }
                case 1: {
                    if(view0.getMeasuredWidth() == this.getMeasuredWidth()) {
                        return;
                    }
                    v5 = TabLayout.getChildMeasureSpec(v1, this.getPaddingTop() + this.getPaddingBottom(), view0.getLayoutParams().height);
                    view0.measure(View.MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0x40000000), v5);
                    break;
                }
                default: {
                    if(v4 != 2) {
                        return;
                    }
                    goto label_17;
                }
            }
        }
    }

    @Override  // android.widget.HorizontalScrollView
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        return motionEvent0.getActionMasked() != 8 || this.isScrollingEnabled() ? super.onTouchEvent(motionEvent0) : false;
    }

    void populateFromPagerAdapter() {
        this.removeAllTabs();
        PagerAdapter pagerAdapter0 = this.pagerAdapter;
        if(pagerAdapter0 != null) {
            int v = pagerAdapter0.getCount();
            for(int v1 = 0; v1 < v; ++v1) {
                this.addTab(this.newTab().setText(null), false);
            }
            ViewPager viewPager0 = this.viewPager;
            if(viewPager0 != null && v > 0) {
                int v2 = viewPager0.getCurrentItem();
                if(v2 != this.getSelectedTabPosition() && v2 < this.getTabCount()) {
                    this.selectTab(this.getTabAt(v2));
                }
            }
        }
    }

    protected boolean releaseFromTabPool(Tab tabLayout$Tab0) {
        return TabLayout.tabPool.release(tabLayout$Tab0);
    }

    public void removeAllTabs() {
        for(int v = this.slidingTabIndicator.getChildCount() - 1; v >= 0; --v) {
            this.removeTabViewAt(v);
        }
        Iterator iterator0 = this.tabs.iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            iterator0.remove();
            ((Tab)object0).reset();
            this.releaseFromTabPool(((Tab)object0));
        }
        this.selectedTab = null;
    }

    @Deprecated
    public void removeOnTabSelectedListener(BaseOnTabSelectedListener tabLayout$BaseOnTabSelectedListener0) {
        this.selectedListeners.remove(tabLayout$BaseOnTabSelectedListener0);
    }

    public void removeOnTabSelectedListener(OnTabSelectedListener tabLayout$OnTabSelectedListener0) {
        this.removeOnTabSelectedListener(tabLayout$OnTabSelectedListener0);
    }

    public void removeTab(Tab tabLayout$Tab0) {
        if(tabLayout$Tab0.parent != this) {
            throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
        }
        this.removeTabAt(tabLayout$Tab0.getPosition());
    }

    public void removeTabAt(int v) {
        int v1 = this.selectedTab == null ? 0 : this.selectedTab.getPosition();
        this.removeTabViewAt(v);
        Tab tabLayout$Tab0 = (Tab)this.tabs.remove(v);
        if(tabLayout$Tab0 != null) {
            tabLayout$Tab0.reset();
            this.releaseFromTabPool(tabLayout$Tab0);
        }
        int v2 = this.tabs.size();
        int v3 = -1;
        for(int v4 = v; v4 < v2; ++v4) {
            if(((Tab)this.tabs.get(v4)).getPosition() == this.indicatorPosition) {
                v3 = v4;
            }
            ((Tab)this.tabs.get(v4)).setPosition(v4);
        }
        this.indicatorPosition = v3;
        if(v1 == v) {
            this.selectTab((this.tabs.isEmpty() ? null : ((Tab)this.tabs.get(Math.max(0, v - 1)))));
        }
    }

    private void removeTabViewAt(int v) {
        TabView tabLayout$TabView0 = (TabView)this.slidingTabIndicator.getChildAt(v);
        this.slidingTabIndicator.removeViewAt(v);
        if(tabLayout$TabView0 != null) {
            tabLayout$TabView0.reset();
            this.tabViewPool.release(tabLayout$TabView0);
        }
        this.requestLayout();
    }

    public void selectTab(Tab tabLayout$Tab0) {
        this.selectTab(tabLayout$Tab0, true);
    }

    public void selectTab(Tab tabLayout$Tab0, boolean z) {
        Tab tabLayout$Tab1 = this.selectedTab;
        if(tabLayout$Tab1 != tabLayout$Tab0) {
            int v = tabLayout$Tab0 == null ? -1 : tabLayout$Tab0.getPosition();
            if(z) {
                if(tabLayout$Tab1 != null && tabLayout$Tab1.getPosition() != -1 || v == -1) {
                    this.animateToTab(v);
                }
                else {
                    this.setScrollPosition(v, 0.0f, true);
                }
                if(v != -1) {
                    this.setSelectedTabView(v);
                }
            }
            this.selectedTab = tabLayout$Tab0;
            if(tabLayout$Tab1 != null && tabLayout$Tab1.parent != null) {
                this.dispatchTabUnselected(tabLayout$Tab1);
            }
            if(tabLayout$Tab0 != null) {
                this.dispatchTabSelected(tabLayout$Tab0);
            }
        }
        else if(tabLayout$Tab1 != null) {
            this.dispatchTabReselected(tabLayout$Tab0);
            this.animateToTab(tabLayout$Tab0.getPosition());
        }
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        MaterialShapeUtils.setElevation(this, f);
    }

    public void setInlineLabel(boolean z) {
        if(this.inlineLabel != z) {
            this.inlineLabel = z;
            for(int v = 0; v < this.slidingTabIndicator.getChildCount(); ++v) {
                View view0 = this.slidingTabIndicator.getChildAt(v);
                if(view0 instanceof TabView) {
                    ((TabView)view0).updateOrientation();
                }
            }
            this.applyModeAndGravity();
        }
    }

    public void setInlineLabelResource(int v) {
        this.setInlineLabel(this.getResources().getBoolean(v));
    }

    @Deprecated
    public void setOnTabSelectedListener(BaseOnTabSelectedListener tabLayout$BaseOnTabSelectedListener0) {
        BaseOnTabSelectedListener tabLayout$BaseOnTabSelectedListener1 = this.selectedListener;
        if(tabLayout$BaseOnTabSelectedListener1 != null) {
            this.removeOnTabSelectedListener(tabLayout$BaseOnTabSelectedListener1);
        }
        this.selectedListener = tabLayout$BaseOnTabSelectedListener0;
        if(tabLayout$BaseOnTabSelectedListener0 != null) {
            this.addOnTabSelectedListener(tabLayout$BaseOnTabSelectedListener0);
        }
    }

    @Deprecated
    public void setOnTabSelectedListener(OnTabSelectedListener tabLayout$OnTabSelectedListener0) {
        this.setOnTabSelectedListener(tabLayout$OnTabSelectedListener0);
    }

    void setPagerAdapter(PagerAdapter pagerAdapter0, boolean z) {
        PagerAdapter pagerAdapter1 = this.pagerAdapter;
        if(pagerAdapter1 != null) {
            DataSetObserver dataSetObserver0 = this.pagerAdapterObserver;
            if(dataSetObserver0 != null) {
                pagerAdapter1.unregisterDataSetObserver(dataSetObserver0);
            }
        }
        this.pagerAdapter = pagerAdapter0;
        if(z && pagerAdapter0 != null) {
            if(this.pagerAdapterObserver == null) {
                this.pagerAdapterObserver = new PagerAdapterObserver(this);
            }
            pagerAdapter0.registerDataSetObserver(this.pagerAdapterObserver);
        }
        this.populateFromPagerAdapter();
    }

    void setScrollAnimatorListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.ensureScrollAnimator();
        this.scrollAnimator.addListener(animator$AnimatorListener0);
    }

    public void setScrollPosition(int v, float f, boolean z) {
        this.setScrollPosition(v, f, z, true);
    }

    public void setScrollPosition(int v, float f, boolean z, boolean z1) {
        this.setScrollPosition(v, f, z, z1, true);
    }

    void setScrollPosition(int v, float f, boolean z, boolean z1, boolean z2) {
        int v1 = Math.round(((float)v) + f);
        if(v1 >= 0 && v1 < this.slidingTabIndicator.getChildCount()) {
            if(z1) {
                this.slidingTabIndicator.setIndicatorPositionFromTabPosition(v, f);
            }
            if(this.scrollAnimator != null && this.scrollAnimator.isRunning()) {
                this.scrollAnimator.cancel();
            }
            int v2 = this.calculateScrollXForTab(v, f);
            int v3 = this.getScrollX();
            boolean z3 = v < this.getSelectedTabPosition() && v2 >= v3 || v > this.getSelectedTabPosition() && v2 <= v3 || v == this.getSelectedTabPosition();
            if(ViewCompat.getLayoutDirection(this) == 1) {
                z3 = v < this.getSelectedTabPosition() && v2 <= v3 || v > this.getSelectedTabPosition() && v2 >= v3 || v == this.getSelectedTabPosition();
            }
            if(z3 || this.viewPagerScrollState == 1 || z2) {
                if(v < 0) {
                    v2 = 0;
                }
                this.scrollTo(v2, 0);
            }
            if(z) {
                this.setSelectedTabView(v1);
            }
        }
    }

    public void setSelectedTabIndicator(int v) {
        if(v != 0) {
            this.setSelectedTabIndicator(AppCompatResources.getDrawable(this.getContext(), v));
            return;
        }
        this.setSelectedTabIndicator(null);
    }

    public void setSelectedTabIndicator(Drawable drawable0) {
        if(drawable0 == null) {
            drawable0 = new GradientDrawable();
        }
        Drawable drawable1 = DrawableCompat.wrap(drawable0).mutate();
        this.tabSelectedIndicator = drawable1;
        DrawableUtils.setTint(drawable1, this.tabSelectedIndicatorColor);
        int v = this.tabIndicatorHeight == -1 ? this.tabSelectedIndicator.getIntrinsicHeight() : this.tabIndicatorHeight;
        this.slidingTabIndicator.setSelectedIndicatorHeight(v);
    }

    public void setSelectedTabIndicatorColor(int v) {
        this.tabSelectedIndicatorColor = v;
        DrawableUtils.setTint(this.tabSelectedIndicator, v);
        this.updateTabViews(false);
    }

    public void setSelectedTabIndicatorGravity(int v) {
        if(this.tabIndicatorGravity != v) {
            this.tabIndicatorGravity = v;
            ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int v) {
        this.tabIndicatorHeight = v;
        this.slidingTabIndicator.setSelectedIndicatorHeight(v);
    }

    private void setSelectedTabView(int v) {
        int v1 = this.slidingTabIndicator.getChildCount();
        if(v < v1) {
            for(int v2 = 0; v2 < v1; ++v2) {
                View view0 = this.slidingTabIndicator.getChildAt(v2);
                boolean z = true;
                if((v2 != v || view0.isSelected()) && (v2 == v || !view0.isSelected())) {
                    view0.setSelected(v2 == v);
                    if(v2 != v) {
                        z = false;
                    }
                    view0.setActivated(z);
                }
                else {
                    view0.setSelected(v2 == v);
                    if(v2 != v) {
                        z = false;
                    }
                    view0.setActivated(z);
                    if(view0 instanceof TabView) {
                        ((TabView)view0).updateTab();
                    }
                }
            }
        }
    }

    public void setTabGravity(int v) {
        if(this.tabGravity != v) {
            this.tabGravity = v;
            this.applyModeAndGravity();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList0) {
        if(this.tabIconTint != colorStateList0) {
            this.tabIconTint = colorStateList0;
            this.updateAllTabs();
        }
    }

    public void setTabIconTintResource(int v) {
        this.setTabIconTint(AppCompatResources.getColorStateList(this.getContext(), v));
    }

    public void setTabIndicatorAnimationMode(int v) {
        this.tabIndicatorAnimationMode = v;
        switch(v) {
            case 0: {
                this.tabIndicatorInterpolator = new TabIndicatorInterpolator();
                return;
            }
            case 1: {
                this.tabIndicatorInterpolator = new ElasticTabIndicatorInterpolator();
                return;
            }
            case 2: {
                this.tabIndicatorInterpolator = new FadeTabIndicatorInterpolator();
                return;
            }
            default: {
                throw new IllegalArgumentException(v + " is not a valid TabIndicatorAnimationMode");
            }
        }
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.tabIndicatorFullWidth = z;
        SlidingTabIndicator.access$100(this.slidingTabIndicator);
        ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
    }

    public void setTabMode(int v) {
        if(v != this.mode) {
            this.mode = v;
            this.applyModeAndGravity();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList0) {
        if(this.tabRippleColorStateList != colorStateList0) {
            this.tabRippleColorStateList = colorStateList0;
            for(int v = 0; v < this.slidingTabIndicator.getChildCount(); ++v) {
                View view0 = this.slidingTabIndicator.getChildAt(v);
                if(view0 instanceof TabView) {
                    TabView.access$200(((TabView)view0), this.getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(int v) {
        this.setTabRippleColor(AppCompatResources.getColorStateList(this.getContext(), v));
    }

    public void setTabTextColors(int v, int v1) {
        this.setTabTextColors(TabLayout.createColorStateList(v, v1));
    }

    public void setTabTextColors(ColorStateList colorStateList0) {
        if(this.tabTextColors != colorStateList0) {
            this.tabTextColors = colorStateList0;
            this.updateAllTabs();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(PagerAdapter pagerAdapter0) {
        this.setPagerAdapter(pagerAdapter0, false);
    }

    public void setUnboundedRipple(boolean z) {
        if(this.unboundedRipple != z) {
            this.unboundedRipple = z;
            for(int v = 0; v < this.slidingTabIndicator.getChildCount(); ++v) {
                View view0 = this.slidingTabIndicator.getChildAt(v);
                if(view0 instanceof TabView) {
                    TabView.access$200(((TabView)view0), this.getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(int v) {
        this.setUnboundedRipple(this.getResources().getBoolean(v));
    }

    private void setupWithViewPager(ViewPager viewPager0, boolean z, boolean z1) {
        ViewPager viewPager1 = this.viewPager;
        if(viewPager1 != null) {
            TabLayoutOnPageChangeListener tabLayout$TabLayoutOnPageChangeListener0 = this.pageChangeListener;
            if(tabLayout$TabLayoutOnPageChangeListener0 != null) {
                viewPager1.removeOnPageChangeListener(tabLayout$TabLayoutOnPageChangeListener0);
            }
            AdapterChangeListener tabLayout$AdapterChangeListener0 = this.adapterChangeListener;
            if(tabLayout$AdapterChangeListener0 != null) {
                this.viewPager.removeOnAdapterChangeListener(tabLayout$AdapterChangeListener0);
            }
        }
        BaseOnTabSelectedListener tabLayout$BaseOnTabSelectedListener0 = this.currentVpSelectedListener;
        if(tabLayout$BaseOnTabSelectedListener0 != null) {
            this.removeOnTabSelectedListener(tabLayout$BaseOnTabSelectedListener0);
            this.currentVpSelectedListener = null;
        }
        if(viewPager0 == null) {
            this.viewPager = null;
            this.setPagerAdapter(null, false);
        }
        else {
            this.viewPager = viewPager0;
            if(this.pageChangeListener == null) {
                this.pageChangeListener = new TabLayoutOnPageChangeListener(this);
            }
            this.pageChangeListener.reset();
            viewPager0.addOnPageChangeListener(this.pageChangeListener);
            ViewPagerOnTabSelectedListener tabLayout$ViewPagerOnTabSelectedListener0 = new ViewPagerOnTabSelectedListener(viewPager0);
            this.currentVpSelectedListener = tabLayout$ViewPagerOnTabSelectedListener0;
            this.addOnTabSelectedListener(tabLayout$ViewPagerOnTabSelectedListener0);
            PagerAdapter pagerAdapter0 = viewPager0.getAdapter();
            if(pagerAdapter0 != null) {
                this.setPagerAdapter(pagerAdapter0, z);
            }
            if(this.adapterChangeListener == null) {
                this.adapterChangeListener = new AdapterChangeListener(this);
            }
            this.adapterChangeListener.setAutoRefresh(z);
            viewPager0.addOnAdapterChangeListener(this.adapterChangeListener);
            this.setScrollPosition(viewPager0.getCurrentItem(), 0.0f, true);
        }
        this.setupViewPagerImplicitly = z1;
    }

    public void setupWithViewPager(ViewPager viewPager0) {
        this.setupWithViewPager(viewPager0, true);
    }

    public void setupWithViewPager(ViewPager viewPager0, boolean z) {
        this.setupWithViewPager(viewPager0, z, false);
    }

    @Override  // android.widget.HorizontalScrollView
    public boolean shouldDelayChildPressedState() {
        return this.getTabScrollRange() > 0;
    }

    private void updateAllTabs() {
        int v = this.tabs.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((Tab)this.tabs.get(v1)).updateView();
        }
    }

    private void updateTabViewLayoutParams(LinearLayout.LayoutParams linearLayout$LayoutParams0) {
        if(this.mode == 1 && this.tabGravity == 0) {
            linearLayout$LayoutParams0.width = 0;
            linearLayout$LayoutParams0.weight = 1.0f;
            return;
        }
        linearLayout$LayoutParams0.width = -2;
        linearLayout$LayoutParams0.weight = 0.0f;
    }

    void updateTabViews(boolean z) {
        for(int v = 0; v < this.slidingTabIndicator.getChildCount(); ++v) {
            View view0 = this.slidingTabIndicator.getChildAt(v);
            view0.setMinimumWidth(this.getTabMinWidth());
            this.updateTabViewLayoutParams(((LinearLayout.LayoutParams)view0.getLayoutParams()));
            if(z) {
                view0.requestLayout();
            }
        }
    }

    void updateViewPagerScrollState(int v) {
        this.viewPagerScrollState = v;
    }
}

