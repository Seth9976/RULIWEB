package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent.DispatcherState;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AbsListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.PopupWindow;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.styleable;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.widget.PopupWindowCompat;
import java.lang.reflect.Method;

public class ListPopupWindow implements ShowableListMenu {
    static class Api24Impl {
        static int getMaxAvailableHeight(PopupWindow popupWindow0, View view0, int v, boolean z) {
            return popupWindow0.getMaxAvailableHeight(view0, v, z);
        }
    }

    static class Api29Impl {
        static void setEpicenterBounds(PopupWindow popupWindow0, Rect rect0) {
            popupWindow0.setEpicenterBounds(rect0);
        }

        static void setIsClippedToScreen(PopupWindow popupWindow0, boolean z) {
            popupWindow0.setIsClippedToScreen(z);
        }
    }

    class ListSelectorHider implements Runnable {
        @Override
        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }

    class PopupDataSetObserver extends DataSetObserver {
        @Override  // android.database.DataSetObserver
        public void onChanged() {
            if(ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }

        @Override  // android.database.DataSetObserver
        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    class PopupScrollListener implements AbsListView.OnScrollListener {
        @Override  // android.widget.AbsListView$OnScrollListener
        public void onScroll(AbsListView absListView0, int v, int v1, int v2) {
        }

        @Override  // android.widget.AbsListView$OnScrollListener
        public void onScrollStateChanged(AbsListView absListView0, int v) {
            if(v == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.this.mPopup.getContentView() != null) {
                ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
                ListPopupWindow.this.mResizePopupRunnable.run();
            }
        }
    }

    class PopupTouchInterceptor implements View.OnTouchListener {
        @Override  // android.view.View$OnTouchListener
        public boolean onTouch(View view0, MotionEvent motionEvent0) {
            int v = motionEvent0.getAction();
            int v1 = (int)motionEvent0.getX();
            int v2 = (int)motionEvent0.getY();
            if(v == 0 && ListPopupWindow.this.mPopup != null && ListPopupWindow.this.mPopup.isShowing() && v1 >= 0 && v1 < ListPopupWindow.this.mPopup.getWidth() && v2 >= 0 && v2 < ListPopupWindow.this.mPopup.getHeight()) {
                ListPopupWindow.this.mHandler.postDelayed(ListPopupWindow.this.mResizePopupRunnable, 0xFAL);
                return false;
            }
            if(v == 1) {
                ListPopupWindow.this.mHandler.removeCallbacks(ListPopupWindow.this.mResizePopupRunnable);
            }
            return false;
        }
    }

    class ResizePopupRunnable implements Runnable {
        @Override
        public void run() {
            if(ListPopupWindow.this.mDropDownList != null && ListPopupWindow.this.mDropDownList.isAttachedToWindow() && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount() && ListPopupWindow.this.mDropDownList.getChildCount() <= ListPopupWindow.this.mListItemExpandMaximum) {
                ListPopupWindow.this.mPopup.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }

    private static final boolean DEBUG = false;
    static final int EXPAND_LIST_TIMEOUT = 0xFA;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch;
    final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    private boolean mOverlapAnchor;
    private boolean mOverlapAnchorSet;
    PopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;
    private static Method sGetMaxAvailableHeightMethod;
    private static Method sSetClipToWindowEnabledMethod;
    private static Method sSetEpicenterBoundsMethod;

    static {
        if(Build.VERSION.SDK_INT <= 28) {
            try {
                ListPopupWindow.sSetClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            }
            catch(NoSuchMethodException unused_ex) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                ListPopupWindow.sSetEpicenterBoundsMethod = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            }
            catch(NoSuchMethodException unused_ex) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if(Build.VERSION.SDK_INT <= 23) {
            try {
                ListPopupWindow.sGetMaxAvailableHeightMethod = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            }
            catch(NoSuchMethodException unused_ex) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public ListPopupWindow(Context context0) {
        this(context0, null, attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, 0);
    }

    public ListPopupWindow(Context context0, AttributeSet attributeSet0, int v, int v1) {
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownWindowLayoutType = 1002;
        this.mDropDownGravity = 0;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.mListItemExpandMaximum = 0x7FFFFFFF;
        this.mPromptPosition = 0;
        this.mResizePopupRunnable = new ResizePopupRunnable(this);
        this.mTouchInterceptor = new PopupTouchInterceptor(this);
        this.mScrollListener = new PopupScrollListener(this);
        this.mHideSelector = () -> {
            DropDownListView dropDownListView0 = ListPopupWindow.this.mDropDownList;
            if(dropDownListView0 != null) {
                dropDownListView0.setListSelectionHidden(true);
                dropDownListView0.requestLayout();
            }
        };
        this.mTempRect = new Rect();
        this.mContext = context0;
        this.mHandler = new Handler(context0.getMainLooper());
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ListPopupWindow, v, v1);
        this.mDropDownHorizontalOffset = typedArray0.getDimensionPixelOffset(styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int v2 = typedArray0.getDimensionPixelOffset(styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.mDropDownVerticalOffset = v2;
        if(v2 != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        typedArray0.recycle();
        AppCompatPopupWindow appCompatPopupWindow0 = new AppCompatPopupWindow(context0, attributeSet0, v, v1);
        this.mPopup = appCompatPopupWindow0;
        appCompatPopupWindow0.setInputMethodMode(1);
    }

    private int buildDropDown() {
        int v6;
        int v3;
        int v2;
        int v1;
        boolean z = true;
        if(this.mDropDownList == null) {
            Context context0 = this.mContext;
            this.mShowDropDownRunnable = new Runnable() {
                @Override
                public void run() {
                    View view0 = ListPopupWindow.this.getAnchorView();
                    if(view0 != null && view0.getWindowToken() != null) {
                        ListPopupWindow.this.show();
                    }
                }
            };
            DropDownListView dropDownListView0 = this.createDropDownListView(context0, !this.mModal);
            this.mDropDownList = dropDownListView0;
            Drawable drawable0 = this.mDropDownListHighlight;
            if(drawable0 != null) {
                dropDownListView0.setSelector(drawable0);
            }
            this.mDropDownList.setAdapter(this.mAdapter);
            this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
            this.mDropDownList.setFocusable(true);
            this.mDropDownList.setFocusableInTouchMode(true);
            this.mDropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override  // android.widget.AdapterView$OnItemSelectedListener
                public void onItemSelected(AdapterView adapterView0, View view0, int v, long v1) {
                    if(v != -1) {
                        DropDownListView dropDownListView0 = ListPopupWindow.this.mDropDownList;
                        if(dropDownListView0 != null) {
                            dropDownListView0.setListSelectionHidden(false);
                        }
                    }
                }

                @Override  // android.widget.AdapterView$OnItemSelectedListener
                public void onNothingSelected(AdapterView adapterView0) {
                }
            });
            this.mDropDownList.setOnScrollListener(this.mScrollListener);
            AdapterView.OnItemSelectedListener adapterView$OnItemSelectedListener0 = this.mItemSelectedListener;
            if(adapterView$OnItemSelectedListener0 != null) {
                this.mDropDownList.setOnItemSelectedListener(adapterView$OnItemSelectedListener0);
            }
            DropDownListView dropDownListView1 = this.mDropDownList;
            View view0 = this.mPromptView;
            if(view0 == null) {
                v2 = 0;
            }
            else {
                LinearLayout linearLayout0 = new LinearLayout(context0);
                linearLayout0.setOrientation(1);
                LinearLayout.LayoutParams linearLayout$LayoutParams0 = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                switch(this.mPromptPosition) {
                    case 0: {
                        linearLayout0.addView(view0);
                        linearLayout0.addView(dropDownListView1, linearLayout$LayoutParams0);
                        break;
                    }
                    case 1: {
                        linearLayout0.addView(dropDownListView1, linearLayout$LayoutParams0);
                        linearLayout0.addView(view0);
                        break;
                    }
                    default: {
                        Log.e("ListPopupWindow", "Invalid hint position " + this.mPromptPosition);
                    }
                }
                int v = this.mDropDownWidth;
                if(v >= 0) {
                    v1 = 0x80000000;
                }
                else {
                    v = 0;
                    v1 = 0;
                }
                view0.measure(View.MeasureSpec.makeMeasureSpec(v, v1), 0);
                LinearLayout.LayoutParams linearLayout$LayoutParams1 = (LinearLayout.LayoutParams)view0.getLayoutParams();
                v2 = view0.getMeasuredHeight() + linearLayout$LayoutParams1.topMargin + linearLayout$LayoutParams1.bottomMargin;
                dropDownListView1 = linearLayout0;
            }
            this.mPopup.setContentView(dropDownListView1);
        }
        else {
            ViewGroup viewGroup0 = (ViewGroup)this.mPopup.getContentView();
            View view1 = this.mPromptView;
            if(view1 == null) {
                v2 = 0;
            }
            else {
                LinearLayout.LayoutParams linearLayout$LayoutParams2 = (LinearLayout.LayoutParams)view1.getLayoutParams();
                v2 = view1.getMeasuredHeight() + linearLayout$LayoutParams2.topMargin + linearLayout$LayoutParams2.bottomMargin;
            }
        }
        Drawable drawable1 = this.mPopup.getBackground();
        if(drawable1 == null) {
            this.mTempRect.setEmpty();
            v3 = 0;
        }
        else {
            drawable1.getPadding(this.mTempRect);
            v3 = this.mTempRect.top + this.mTempRect.bottom;
            if(!this.mDropDownVerticalOffsetSet) {
                this.mDropDownVerticalOffset = -this.mTempRect.top;
            }
        }
        if(this.mPopup.getInputMethodMode() != 2) {
            z = false;
        }
        int v4 = this.getMaxAvailableHeight(this.getAnchorView(), this.mDropDownVerticalOffset, z);
        if(!this.mDropDownAlwaysVisible && this.mDropDownHeight != -1) {
            int v5 = this.mDropDownWidth;
            switch(v5) {
                case -2: {
                    v6 = View.MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), 0x80000000);
                    break;
                }
                case -1: {
                    v6 = View.MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), 0x40000000);
                    break;
                }
                default: {
                    v6 = View.MeasureSpec.makeMeasureSpec(v5, 0x40000000);
                }
            }
            int v7 = this.mDropDownList.measureHeightOfChildrenCompat(v6, 0, -1, v4 - v2, -1);
            if(v7 > 0) {
                v2 += v3 + (this.mDropDownList.getPaddingTop() + this.mDropDownList.getPaddingBottom());
            }
            return v7 + v2;
        }
        return v4 + v3;
    }

    // 检测为 Lambda 实现
    public void clearListSelection() [...]

    public View.OnTouchListener createDragToOpenListener(View view0) {
        return new ForwardingListener(view0) {
            @Override  // androidx.appcompat.widget.ForwardingListener
            public ShowableListMenu getPopup() {
                return this.getPopup();
            }

            public ListPopupWindow getPopup() {
                return ListPopupWindow.this;
            }
        };
    }

    DropDownListView createDropDownListView(Context context0, boolean z) {
        return new DropDownListView(context0, z);
    }

    @Override  // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        this.mPopup.dismiss();
        this.removePromptView();
        this.mPopup.setContentView(null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks(this.mResizePopupRunnable);
    }

    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }

    public int getAnimationStyle() {
        return this.mPopup.getAnimationStyle();
    }

    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }

    public Rect getEpicenterBounds() {
        return this.mEpicenterBounds == null ? null : new Rect(this.mEpicenterBounds);
    }

    public int getHeight() {
        return this.mDropDownHeight;
    }

    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }

    public int getInputMethodMode() {
        return this.mPopup.getInputMethodMode();
    }

    @Override  // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        return this.mDropDownList;
    }

    private int getMaxAvailableHeight(View view0, int v, boolean z) {
        if(Build.VERSION.SDK_INT <= 23) {
            Method method0 = ListPopupWindow.sGetMaxAvailableHeightMethod;
            if(method0 != null) {
                try {
                    return (int)(((Integer)method0.invoke(this.mPopup, view0, v, Boolean.valueOf(z))));
                }
                catch(Exception unused_ex) {
                    Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
                }
            }
            return this.mPopup.getMaxAvailableHeight(view0, v);
        }
        return Api24Impl.getMaxAvailableHeight(this.mPopup, view0, v, z);
    }

    public int getPromptPosition() {
        return this.mPromptPosition;
    }

    public Object getSelectedItem() {
        return this.isShowing() ? this.mDropDownList.getSelectedItem() : null;
    }

    public long getSelectedItemId() {
        return this.isShowing() ? this.mDropDownList.getSelectedItemId() : 0x8000000000000000L;
    }

    public int getSelectedItemPosition() {
        return this.isShowing() ? this.mDropDownList.getSelectedItemPosition() : -1;
    }

    public View getSelectedView() {
        return this.isShowing() ? this.mDropDownList.getSelectedView() : null;
    }

    public int getSoftInputMode() {
        return this.mPopup.getSoftInputMode();
    }

    public int getVerticalOffset() {
        return this.mDropDownVerticalOffsetSet ? this.mDropDownVerticalOffset : 0;
    }

    public int getWidth() {
        return this.mDropDownWidth;
    }

    private static boolean isConfirmKey(int v) {
        return v == 23 || v == 66;
    }

    public boolean isDropDownAlwaysVisible() {
        return this.mDropDownAlwaysVisible;
    }

    public boolean isInputMethodNotNeeded() {
        return this.mPopup.getInputMethodMode() == 2;
    }

    public boolean isModal() {
        return this.mModal;
    }

    @Override  // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return this.mPopup.isShowing();
    }

    public boolean onKeyDown(int v, KeyEvent keyEvent0) {
        int v3;
        int v2;
        if(this.isShowing() && v != 62 && (this.mDropDownList.getSelectedItemPosition() >= 0 || !ListPopupWindow.isConfirmKey(v))) {
            int v1 = this.mDropDownList.getSelectedItemPosition();
            boolean z = this.mPopup.isAboveAnchor();
            ListAdapter listAdapter0 = this.mAdapter;
            if(listAdapter0 == null) {
                v2 = 0x7FFFFFFF;
                v3 = 0x80000000;
            }
            else {
                boolean z1 = listAdapter0.areAllItemsEnabled();
                v2 = z1 ? 0 : this.mDropDownList.lookForSelectablePosition(0, true);
                v3 = z1 ? listAdapter0.getCount() - 1 : this.mDropDownList.lookForSelectablePosition(listAdapter0.getCount() - 1, false);
            }
            if(!z && v == 19 && v1 <= v2 || z && v == 20 && v1 >= v3) {
                this.clearListSelection();
                this.mPopup.setInputMethodMode(1);
                this.show();
                return true;
            }
            this.mDropDownList.setListSelectionHidden(false);
            if(this.mDropDownList.onKeyDown(v, keyEvent0)) {
                this.mPopup.setInputMethodMode(2);
                this.mDropDownList.requestFocusFromTouch();
                this.show();
                return v == 19 || v == 20 || (v == 23 || v == 66);
            }
            return z || v != 20 ? z && v == 19 && v1 == v2 : v1 == v3;
        }
        return false;
    }

    public boolean onKeyPreIme(int v, KeyEvent keyEvent0) {
        if(v == 4 && this.isShowing()) {
            View view0 = this.mDropDownAnchorView;
            if(keyEvent0.getAction() == 0 && keyEvent0.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyEvent$DispatcherState0 = view0.getKeyDispatcherState();
                if(keyEvent$DispatcherState0 != null) {
                    keyEvent$DispatcherState0.startTracking(keyEvent0, this);
                }
                return true;
            }
            if(keyEvent0.getAction() == 1) {
                KeyEvent.DispatcherState keyEvent$DispatcherState1 = view0.getKeyDispatcherState();
                if(keyEvent$DispatcherState1 != null) {
                    keyEvent$DispatcherState1.handleUpEvent(keyEvent0);
                }
                if(keyEvent0.isTracking() && !keyEvent0.isCanceled()) {
                    this.dismiss();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onKeyUp(int v, KeyEvent keyEvent0) {
        if(this.isShowing() && this.mDropDownList.getSelectedItemPosition() >= 0) {
            boolean z = this.mDropDownList.onKeyUp(v, keyEvent0);
            if(z && ListPopupWindow.isConfirmKey(v)) {
                this.dismiss();
            }
            return z;
        }
        return false;
    }

    public boolean performItemClick(int v) {
        if(this.isShowing()) {
            if(this.mItemClickListener != null) {
                DropDownListView dropDownListView0 = this.mDropDownList;
                View view0 = dropDownListView0.getChildAt(v - dropDownListView0.getFirstVisiblePosition());
                ListAdapter listAdapter0 = dropDownListView0.getAdapter();
                this.mItemClickListener.onItemClick(dropDownListView0, view0, v, listAdapter0.getItemId(v));
            }
            return true;
        }
        return false;
    }

    public void postShow() {
        this.mHandler.post(this.mShowDropDownRunnable);
    }

    private void removePromptView() {
        View view0 = this.mPromptView;
        if(view0 != null) {
            ViewParent viewParent0 = view0.getParent();
            if(viewParent0 instanceof ViewGroup) {
                ((ViewGroup)viewParent0).removeView(this.mPromptView);
            }
        }
    }

    public void setAdapter(ListAdapter listAdapter0) {
        DataSetObserver dataSetObserver0 = this.mObserver;
        if(dataSetObserver0 == null) {
            this.mObserver = new PopupDataSetObserver(this);
        }
        else {
            ListAdapter listAdapter1 = this.mAdapter;
            if(listAdapter1 != null) {
                listAdapter1.unregisterDataSetObserver(dataSetObserver0);
            }
        }
        this.mAdapter = listAdapter0;
        if(listAdapter0 != null) {
            listAdapter0.registerDataSetObserver(this.mObserver);
        }
        DropDownListView dropDownListView0 = this.mDropDownList;
        if(dropDownListView0 != null) {
            dropDownListView0.setAdapter(this.mAdapter);
        }
    }

    public void setAnchorView(View view0) {
        this.mDropDownAnchorView = view0;
    }

    public void setAnimationStyle(int v) {
        this.mPopup.setAnimationStyle(v);
    }

    public void setBackgroundDrawable(Drawable drawable0) {
        this.mPopup.setBackgroundDrawable(drawable0);
    }

    public void setContentWidth(int v) {
        Drawable drawable0 = this.mPopup.getBackground();
        if(drawable0 != null) {
            drawable0.getPadding(this.mTempRect);
            this.mDropDownWidth = this.mTempRect.left + this.mTempRect.right + v;
            return;
        }
        this.setWidth(v);
    }

    public void setDropDownAlwaysVisible(boolean z) {
        this.mDropDownAlwaysVisible = z;
    }

    public void setDropDownGravity(int v) {
        this.mDropDownGravity = v;
    }

    public void setEpicenterBounds(Rect rect0) {
        this.mEpicenterBounds = rect0 == null ? null : new Rect(rect0);
    }

    public void setForceIgnoreOutsideTouch(boolean z) {
        this.mForceIgnoreOutsideTouch = z;
    }

    public void setHeight(int v) {
        if(v < 0 && -2 != v && -1 != v) {
            throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
        }
        this.mDropDownHeight = v;
    }

    public void setHorizontalOffset(int v) {
        this.mDropDownHorizontalOffset = v;
    }

    public void setInputMethodMode(int v) {
        this.mPopup.setInputMethodMode(v);
    }

    void setListItemExpandMax(int v) {
        this.mListItemExpandMaximum = v;
    }

    public void setListSelector(Drawable drawable0) {
        this.mDropDownListHighlight = drawable0;
    }

    public void setModal(boolean z) {
        this.mModal = z;
        this.mPopup.setFocusable(z);
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener popupWindow$OnDismissListener0) {
        this.mPopup.setOnDismissListener(popupWindow$OnDismissListener0);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener adapterView$OnItemClickListener0) {
        this.mItemClickListener = adapterView$OnItemClickListener0;
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener adapterView$OnItemSelectedListener0) {
        this.mItemSelectedListener = adapterView$OnItemSelectedListener0;
    }

    public void setOverlapAnchor(boolean z) {
        this.mOverlapAnchorSet = true;
        this.mOverlapAnchor = z;
    }

    private void setPopupClipToScreenEnabled(boolean z) {
        if(Build.VERSION.SDK_INT <= 28) {
            Method method0 = ListPopupWindow.sSetClipToWindowEnabledMethod;
            if(method0 != null) {
                try {
                    method0.invoke(this.mPopup, Boolean.valueOf(z));
                    return;
                }
                catch(Exception unused_ex) {
                    Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
                }
            }
            return;
        }
        Api29Impl.setIsClippedToScreen(this.mPopup, z);
    }

    public void setPromptPosition(int v) {
        this.mPromptPosition = v;
    }

    public void setPromptView(View view0) {
        boolean z = this.isShowing();
        if(z) {
            this.removePromptView();
        }
        this.mPromptView = view0;
        if(z) {
            this.show();
        }
    }

    public void setSelection(int v) {
        DropDownListView dropDownListView0 = this.mDropDownList;
        if(this.isShowing() && dropDownListView0 != null) {
            dropDownListView0.setListSelectionHidden(false);
            dropDownListView0.setSelection(v);
            if(dropDownListView0.getChoiceMode() != 0) {
                dropDownListView0.setItemChecked(v, true);
            }
        }
    }

    public void setSoftInputMode(int v) {
        this.mPopup.setSoftInputMode(v);
    }

    public void setVerticalOffset(int v) {
        this.mDropDownVerticalOffset = v;
        this.mDropDownVerticalOffsetSet = true;
    }

    public void setWidth(int v) {
        this.mDropDownWidth = v;
    }

    public void setWindowLayoutType(int v) {
        this.mDropDownWindowLayoutType = v;
    }

    @Override  // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        int v = this.buildDropDown();
        boolean z = this.isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
        boolean z1 = true;
        if(!this.mPopup.isShowing()) {
            int v3 = this.mDropDownWidth;
            if(v3 == -1) {
                v3 = -1;
            }
            else if(v3 == -2) {
                v3 = this.getAnchorView().getWidth();
            }
            int v4 = this.mDropDownHeight;
            if(v4 == -1) {
                v = -1;
            }
            else if(v4 != -2) {
                v = v4;
            }
            this.mPopup.setWidth(v3);
            this.mPopup.setHeight(v);
            this.setPopupClipToScreenEnabled(true);
            this.mPopup.setOutsideTouchable(!this.mForceIgnoreOutsideTouch && !this.mDropDownAlwaysVisible);
            this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
            if(this.mOverlapAnchorSet) {
                PopupWindowCompat.setOverlapAnchor(this.mPopup, this.mOverlapAnchor);
            }
            if(Build.VERSION.SDK_INT <= 28) {
                Method method0 = ListPopupWindow.sSetEpicenterBoundsMethod;
                if(method0 != null) {
                    try {
                        method0.invoke(this.mPopup, this.mEpicenterBounds);
                    }
                    catch(Exception exception0) {
                        Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", exception0);
                    }
                }
            }
            else {
                Api29Impl.setEpicenterBounds(this.mPopup, this.mEpicenterBounds);
            }
            PopupWindowCompat.showAsDropDown(this.mPopup, this.getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
            this.mDropDownList.setSelection(-1);
            if(!this.mModal || this.mDropDownList.isInTouchMode()) {
                this.clearListSelection();
            }
            if(!this.mModal) {
                this.mHandler.post(this.mHideSelector);
            }
        }
        else if(this.getAnchorView().isAttachedToWindow()) {
            int v1 = this.mDropDownWidth;
            if(v1 == -1) {
                v1 = -1;
            }
            else if(v1 == -2) {
                v1 = this.getAnchorView().getWidth();
            }
            int v2 = this.mDropDownHeight;
            if(v2 == -1) {
                if(!z) {
                    v = -1;
                }
                if(z) {
                    this.mPopup.setWidth((this.mDropDownWidth == -1 ? -1 : 0));
                    this.mPopup.setHeight(0);
                }
                else {
                    this.mPopup.setWidth((this.mDropDownWidth == -1 ? -1 : 0));
                    this.mPopup.setHeight(-1);
                }
            }
            else if(v2 != -2) {
                v = v2;
            }
            PopupWindow popupWindow0 = this.mPopup;
            if(this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) {
                z1 = false;
            }
            popupWindow0.setOutsideTouchable(z1);
            this.mPopup.update(this.getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, (v1 >= 0 ? v1 : -1), (v >= 0 ? v : -1));
        }
    }
}

