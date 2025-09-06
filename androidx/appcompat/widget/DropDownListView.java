package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R.attr;
import androidx.appcompat.graphics.drawable.DrawableWrapperCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.widget.ListViewAutoScrollHelper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class DropDownListView extends ListView {
    static class Api21Impl {
        static void drawableHotspotChanged(View view0, float f, float f1) {
            view0.drawableHotspotChanged(f, f1);
        }
    }

    static class Api30Impl {
        private static boolean sHasMethods;
        private static Method sPositionSelector;
        private static Method sSetNextSelectedPositionInt;
        private static Method sSetSelectedPositionInt;

        static {
            try {
                Method method0 = AbsListView.class.getDeclaredMethod("positionSelector", Integer.TYPE, View.class, Boolean.TYPE, Float.TYPE, Float.TYPE);
                Api30Impl.sPositionSelector = method0;
                method0.setAccessible(true);
                Method method1 = AdapterView.class.getDeclaredMethod("setSelectedPositionInt", Integer.TYPE);
                Api30Impl.sSetSelectedPositionInt = method1;
                method1.setAccessible(true);
                Method method2 = AdapterView.class.getDeclaredMethod("setNextSelectedPositionInt", Integer.TYPE);
                Api30Impl.sSetNextSelectedPositionInt = method2;
                method2.setAccessible(true);
                Api30Impl.sHasMethods = true;
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                noSuchMethodException0.printStackTrace();
            }
        }

        static boolean canPositionSelectorForHoveredItem() [...] // 潜在的解密器

        static void positionSelectorForHoveredItem(DropDownListView dropDownListView0, int v, View view0) {
            try {
                Api30Impl.sPositionSelector.invoke(dropDownListView0, v, view0, Boolean.FALSE, -1, -1);
                Api30Impl.sSetSelectedPositionInt.invoke(dropDownListView0, v);
                Api30Impl.sSetNextSelectedPositionInt.invoke(dropDownListView0, v);
            }
            catch(IllegalAccessException illegalAccessException0) {
                illegalAccessException0.printStackTrace();
            }
            catch(InvocationTargetException invocationTargetException0) {
                invocationTargetException0.printStackTrace();
            }
        }
    }

    static class Api33Impl {
        static boolean isSelectedChildViewEnabled(AbsListView absListView0) {
            return absListView0.isSelectedChildViewEnabled();
        }

        static void setSelectedChildViewEnabled(AbsListView absListView0, boolean z) {
            absListView0.setSelectedChildViewEnabled(z);
        }
    }

    static class GateKeeperDrawable extends DrawableWrapperCompat {
        private boolean mEnabled;

        GateKeeperDrawable(Drawable drawable0) {
            super(drawable0);
            this.mEnabled = true;
        }

        @Override  // androidx.appcompat.graphics.drawable.DrawableWrapperCompat
        public void draw(Canvas canvas0) {
            if(this.mEnabled) {
                super.draw(canvas0);
            }
        }

        void setEnabled(boolean z) {
            this.mEnabled = z;
        }

        @Override  // androidx.appcompat.graphics.drawable.DrawableWrapperCompat
        public void setHotspot(float f, float f1) {
            if(this.mEnabled) {
                super.setHotspot(f, f1);
            }
        }

        @Override  // androidx.appcompat.graphics.drawable.DrawableWrapperCompat
        public void setHotspotBounds(int v, int v1, int v2, int v3) {
            if(this.mEnabled) {
                super.setHotspotBounds(v, v1, v2, v3);
            }
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.appcompat.graphics.drawable.DrawableWrapperCompat
        public boolean setState(int[] arr_v) {
            return this.mEnabled ? super.setState(arr_v) : false;
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.appcompat.graphics.drawable.DrawableWrapperCompat
        public boolean setVisible(boolean z, boolean z1) {
            return this.mEnabled ? super.setVisible(z, z1) : false;
        }
    }

    static class PreApi33Impl {
        private static final Field sIsChildViewEnabled;

        static {
            Field field0 = null;
            try {
                field0 = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
                field0.setAccessible(true);
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                noSuchFieldException0.printStackTrace();
            }
            PreApi33Impl.sIsChildViewEnabled = field0;
        }

        static boolean isSelectedChildViewEnabled(AbsListView absListView0) {
            Field field0 = PreApi33Impl.sIsChildViewEnabled;
            if(field0 != null) {
                try {
                    return field0.getBoolean(absListView0);
                }
                catch(IllegalAccessException illegalAccessException0) {
                    illegalAccessException0.printStackTrace();
                }
            }
            return false;
        }

        static void setSelectedChildViewEnabled(AbsListView absListView0, boolean z) {
            Field field0 = PreApi33Impl.sIsChildViewEnabled;
            if(field0 != null) {
                try {
                    field0.set(absListView0, Boolean.valueOf(z));
                }
                catch(IllegalAccessException illegalAccessException0) {
                    illegalAccessException0.printStackTrace();
                }
            }
        }
    }

    class ResolveHoverRunnable implements Runnable {
        public void cancel() {
            DropDownListView.this.mResolveHoverRunnable = null;
            DropDownListView.this.removeCallbacks(this);
        }

        public void post() {
            DropDownListView.this.post(this);
        }

        @Override
        public void run() {
            DropDownListView.this.mResolveHoverRunnable = null;
            DropDownListView.this.drawableStateChanged();
        }
    }

    public static final int INVALID_POSITION = -1;
    public static final int NO_POSITION = -1;
    private ViewPropertyAnimatorCompat mClickAnimation;
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private boolean mListSelectionHidden;
    private int mMotionPosition;
    ResolveHoverRunnable mResolveHoverRunnable;
    private ListViewAutoScrollHelper mScrollHelper;
    private int mSelectionBottomPadding;
    private int mSelectionLeftPadding;
    private int mSelectionRightPadding;
    private int mSelectionTopPadding;
    private GateKeeperDrawable mSelector;
    private final Rect mSelectorRect;

    DropDownListView(Context context0, boolean z) {
        super(context0, null, attr.dropDownListViewStyle);
        this.mSelectorRect = new Rect();
        this.mSelectionLeftPadding = 0;
        this.mSelectionTopPadding = 0;
        this.mSelectionRightPadding = 0;
        this.mSelectionBottomPadding = 0;
        this.mHijackFocus = z;
        this.setCacheColorHint(0);
    }

    private void clearPressedItem() {
        this.mDrawsInPressedState = false;
        this.setPressed(false);
        this.drawableStateChanged();
        View view0 = this.getChildAt(this.mMotionPosition - this.getFirstVisiblePosition());
        if(view0 != null) {
            view0.setPressed(false);
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0 = this.mClickAnimation;
        if(viewPropertyAnimatorCompat0 != null) {
            viewPropertyAnimatorCompat0.cancel();
            this.mClickAnimation = null;
        }
    }

    private void clickPressedItem(View view0, int v) {
        this.performItemClick(view0, v, this.getItemIdAtPosition(v));
    }

    @Override  // android.widget.ListView
    protected void dispatchDraw(Canvas canvas0) {
        this.drawSelectorCompat(canvas0);
        super.dispatchDraw(canvas0);
    }

    private void drawSelectorCompat(Canvas canvas0) {
        if(!this.mSelectorRect.isEmpty()) {
            Drawable drawable0 = this.getSelector();
            if(drawable0 != null) {
                drawable0.setBounds(this.mSelectorRect);
                drawable0.draw(canvas0);
            }
        }
    }

    @Override  // android.widget.AbsListView
    protected void drawableStateChanged() {
        if(this.mResolveHoverRunnable != null) {
            return;
        }
        super.drawableStateChanged();
        this.setSelectorEnabled(true);
        this.updateSelectorStateCompat();
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    public boolean hasFocus() {
        return this.mHijackFocus || super.hasFocus();
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.View
    public boolean hasWindowFocus() {
        return this.mHijackFocus || super.hasWindowFocus();
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.View
    public boolean isFocused() {
        return this.mHijackFocus || super.isFocused();
    }

    // 去混淆评级： 低(30)
    @Override  // android.view.View
    public boolean isInTouchMode() {
        return this.mHijackFocus && this.mListSelectionHidden || super.isInTouchMode();
    }

    public int lookForSelectablePosition(int v, boolean z) {
        ListAdapter listAdapter0 = this.getAdapter();
        if(listAdapter0 != null && !this.isInTouchMode()) {
            int v1 = listAdapter0.getCount();
            if(!this.getAdapter().areAllItemsEnabled()) {
                if(z) {
                    int v2;
                    for(v2 = Math.max(0, v); v2 < v1 && !listAdapter0.isEnabled(v2); ++v2) {
                    }
                    return v2 < 0 || v2 >= v1 ? -1 : v2;
                }
                for(v2 = Math.min(v, v1 - 1); v2 >= 0 && !listAdapter0.isEnabled(v2); --v2) {
                }
                return v2 < 0 || v2 >= v1 ? -1 : v2;
            }
            return v < 0 || v >= v1 ? -1 : v;
        }
        return -1;
    }

    public int measureHeightOfChildrenCompat(int v, int v1, int v2, int v3, int v4) {
        int v5 = this.getListPaddingTop();
        int v6 = this.getListPaddingBottom();
        int v7 = this.getDividerHeight();
        Drawable drawable0 = this.getDivider();
        ListAdapter listAdapter0 = this.getAdapter();
        if(listAdapter0 == null) {
            return v5 + v6;
        }
        int v8 = v5 + v6;
        if(v7 <= 0 || drawable0 == null) {
            v7 = 0;
        }
        int v9 = listAdapter0.getCount();
        View view0 = null;
        int v11 = 0;
        int v12 = 0;
        for(int v10 = 0; v10 < v9; ++v10) {
            int v13 = listAdapter0.getItemViewType(v10);
            if(v13 != v11) {
                view0 = null;
                v11 = v13;
            }
            view0 = listAdapter0.getView(v10, view0, this);
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
            if(viewGroup$LayoutParams0 == null) {
                viewGroup$LayoutParams0 = this.generateDefaultLayoutParams();
                view0.setLayoutParams(viewGroup$LayoutParams0);
            }
            view0.measure(v, (viewGroup$LayoutParams0.height <= 0 ? 0 : View.MeasureSpec.makeMeasureSpec(viewGroup$LayoutParams0.height, 0x40000000)));
            view0.forceLayout();
            if(v10 > 0) {
                v8 += v7;
            }
            v8 += view0.getMeasuredHeight();
            if(v8 >= v3) {
                return v4 < 0 || v10 <= v4 || v12 <= 0 || v8 == v3 ? v3 : v12;
            }
            if(v4 >= 0 && v10 >= v4) {
                v12 = v8;
            }
        }
        return v8;
    }

    @Override  // android.widget.ListView
    protected void onDetachedFromWindow() {
        this.mResolveHoverRunnable = null;
        super.onDetachedFromWindow();
    }

    public boolean onForwardedEvent(MotionEvent motionEvent0, int v) {
        boolean z1;
        boolean z;
        int v1 = motionEvent0.getActionMasked();
        switch(v1) {
            case 1: {
                z1 = false;
                goto label_8;
            }
            case 2: {
                z1 = true;
            label_8:
                int v2 = motionEvent0.findPointerIndex(v);
                if(v2 >= 0) {
                    int v3 = (int)motionEvent0.getX(v2);
                    int v4 = (int)motionEvent0.getY(v2);
                    int v5 = this.pointToPosition(v3, v4);
                    if(v5 == -1) {
                        z = true;
                    }
                    else {
                        View view0 = this.getChildAt(v5 - this.getFirstVisiblePosition());
                        this.setPressedItem(view0, v5, ((float)v3), ((float)v4));
                        if(v1 == 1) {
                            this.clickPressedItem(view0, v5);
                        }
                        z = false;
                        z1 = true;
                    }
                }
                else {
                    z = false;
                    z1 = false;
                }
                break;
            }
            case 3: {
                z = false;
                z1 = false;
                break;
            }
            default: {
                z = false;
                z1 = true;
            }
        }
        if(!z1 || z) {
            this.clearPressedItem();
        }
        if(z1) {
            if(this.mScrollHelper == null) {
                this.mScrollHelper = new ListViewAutoScrollHelper(this);
            }
            this.mScrollHelper.setEnabled(true);
            this.mScrollHelper.onTouch(this, motionEvent0);
            return true;
        }
        ListViewAutoScrollHelper listViewAutoScrollHelper0 = this.mScrollHelper;
        if(listViewAutoScrollHelper0 != null) {
            listViewAutoScrollHelper0.setEnabled(false);
        }
        return false;
    }

    @Override  // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent0) {
        if(Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent0);
        }
        int v = motionEvent0.getActionMasked();
        if(v == 10 && this.mResolveHoverRunnable == null) {
            ResolveHoverRunnable dropDownListView$ResolveHoverRunnable0 = new ResolveHoverRunnable(this);
            this.mResolveHoverRunnable = dropDownListView$ResolveHoverRunnable0;
            dropDownListView$ResolveHoverRunnable0.post();
        }
        boolean z = super.onHoverEvent(motionEvent0);
        if(v != 7 && v != 9) {
            this.setSelection(-1);
            return z;
        }
        int v1 = this.pointToPosition(((int)motionEvent0.getX()), ((int)motionEvent0.getY()));
        if(v1 != -1 && v1 != this.getSelectedItemPosition()) {
            View view0 = this.getChildAt(v1 - this.getFirstVisiblePosition());
            if(view0.isEnabled()) {
                this.requestFocus();
                this.setSelectionFromTop(v1, view0.getTop() - this.getTop());
            }
            this.updateSelectorStateCompat();
        }
        return z;
    }

    @Override  // android.widget.AbsListView
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        if(motionEvent0.getAction() == 0) {
            this.mMotionPosition = this.pointToPosition(((int)motionEvent0.getX()), ((int)motionEvent0.getY()));
        }
        ResolveHoverRunnable dropDownListView$ResolveHoverRunnable0 = this.mResolveHoverRunnable;
        if(dropDownListView$ResolveHoverRunnable0 != null) {
            dropDownListView$ResolveHoverRunnable0.cancel();
        }
        return super.onTouchEvent(motionEvent0);
    }

    private void positionSelectorCompat(int v, View view0) {
        int v1 = view0.getLeft();
        int v2 = view0.getTop();
        int v3 = view0.getRight();
        int v4 = view0.getBottom();
        this.mSelectorRect.set(v1, v2, v3, v4);
        this.mSelectorRect.left -= this.mSelectionLeftPadding;
        this.mSelectorRect.top -= this.mSelectionTopPadding;
        this.mSelectorRect.right += this.mSelectionRightPadding;
        this.mSelectorRect.bottom += this.mSelectionBottomPadding;
        boolean z = this.superIsSelectedChildViewEnabled();
        if(view0.isEnabled() != z) {
            this.superSetSelectedChildViewEnabled(!z);
            if(v != -1) {
                this.refreshDrawableState();
            }
        }
    }

    private void positionSelectorLikeFocusCompat(int v, View view0) {
        Drawable drawable0 = this.getSelector();
        boolean z = true;
        boolean z1 = drawable0 != null && v != -1;
        if(z1) {
            drawable0.setVisible(false, false);
        }
        this.positionSelectorCompat(v, view0);
        if(z1) {
            float f = this.mSelectorRect.exactCenterX();
            float f1 = this.mSelectorRect.exactCenterY();
            if(this.getVisibility() != 0) {
                z = false;
            }
            drawable0.setVisible(z, false);
            DrawableCompat.setHotspot(drawable0, f, f1);
        }
    }

    private void positionSelectorLikeTouchCompat(int v, View view0, float f, float f1) {
        this.positionSelectorLikeFocusCompat(v, view0);
        Drawable drawable0 = this.getSelector();
        if(drawable0 != null && v != -1) {
            DrawableCompat.setHotspot(drawable0, f, f1);
        }
    }

    void setListSelectionHidden(boolean z) {
        this.mListSelectionHidden = z;
    }

    private void setPressedItem(View view0, int v, float f, float f1) {
        this.mDrawsInPressedState = true;
        Api21Impl.drawableHotspotChanged(this, f, f1);
        if(!this.isPressed()) {
            this.setPressed(true);
        }
        this.layoutChildren();
        int v1 = this.mMotionPosition;
        if(v1 != -1) {
            View view1 = this.getChildAt(v1 - this.getFirstVisiblePosition());
            if(view1 != null && view1 != view0 && view1.isPressed()) {
                view1.setPressed(false);
            }
        }
        this.mMotionPosition = v;
        Api21Impl.drawableHotspotChanged(view0, f - ((float)view0.getLeft()), f1 - ((float)view0.getTop()));
        if(!view0.isPressed()) {
            view0.setPressed(true);
        }
        this.positionSelectorLikeTouchCompat(v, view0, f, f1);
        this.setSelectorEnabled(false);
        this.refreshDrawableState();
    }

    @Override  // android.widget.AbsListView
    public void setSelector(Drawable drawable0) {
        GateKeeperDrawable dropDownListView$GateKeeperDrawable0 = drawable0 == null ? null : new GateKeeperDrawable(drawable0);
        this.mSelector = dropDownListView$GateKeeperDrawable0;
        super.setSelector(dropDownListView$GateKeeperDrawable0);
        Rect rect0 = new Rect();
        if(drawable0 != null) {
            drawable0.getPadding(rect0);
        }
        this.mSelectionLeftPadding = rect0.left;
        this.mSelectionTopPadding = rect0.top;
        this.mSelectionRightPadding = rect0.right;
        this.mSelectionBottomPadding = rect0.bottom;
    }

    private void setSelectorEnabled(boolean z) {
        GateKeeperDrawable dropDownListView$GateKeeperDrawable0 = this.mSelector;
        if(dropDownListView$GateKeeperDrawable0 != null) {
            dropDownListView$GateKeeperDrawable0.setEnabled(z);
        }
    }

    private boolean superIsSelectedChildViewEnabled() {
        return Build.VERSION.SDK_INT < 33 ? PreApi33Impl.isSelectedChildViewEnabled(this) : Api33Impl.isSelectedChildViewEnabled(this);
    }

    private void superSetSelectedChildViewEnabled(boolean z) {
        if(Build.VERSION.SDK_INT >= 33) {
            Api33Impl.setSelectedChildViewEnabled(this, z);
            return;
        }
        PreApi33Impl.setSelectedChildViewEnabled(this, z);
    }

    private boolean touchModeDrawsInPressedStateCompat() {
        return this.mDrawsInPressedState;
    }

    private void updateSelectorStateCompat() {
        Drawable drawable0 = this.getSelector();
        if(drawable0 != null && this.touchModeDrawsInPressedStateCompat() && this.isPressed()) {
            drawable0.setState(this.getDrawableState());
        }
    }
}

