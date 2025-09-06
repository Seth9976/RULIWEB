package androidx.appcompat.widget;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.layout;
import androidx.appcompat.R.styleable;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.TintableBackgroundView;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
    static final class Api23Impl {
        static void setDropDownViewTheme(ThemedSpinnerAdapter themedSpinnerAdapter0, Resources.Theme resources$Theme0) {
            if(!ObjectsCompat.equals(themedSpinnerAdapter0.getDropDownViewTheme(), resources$Theme0)) {
                themedSpinnerAdapter0.setDropDownViewTheme(resources$Theme0);
            }
        }
    }

    class DialogPopup implements DialogInterface.OnClickListener, SpinnerPopup {
        private ListAdapter mListAdapter;
        AlertDialog mPopup;
        private CharSequence mPrompt;

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public void dismiss() {
            AlertDialog alertDialog0 = this.mPopup;
            if(alertDialog0 != null) {
                alertDialog0.dismiss();
                this.mPopup = null;
            }
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public Drawable getBackground() {
            return null;
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public CharSequence getHintText() {
            return this.mPrompt;
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public int getHorizontalOffset() {
            return 0;
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public int getHorizontalOriginalOffset() {
            return 0;
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public int getVerticalOffset() {
            return 0;
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public boolean isShowing() {
            return this.mPopup == null ? false : this.mPopup.isShowing();
        }

        @Override  // android.content.DialogInterface$OnClickListener
        public void onClick(DialogInterface dialogInterface0, int v) {
            AppCompatSpinner.this.setSelection(v);
            if(AppCompatSpinner.this.getOnItemClickListener() != null) {
                long v1 = this.mListAdapter.getItemId(v);
                AppCompatSpinner.this.performItemClick(null, v, v1);
            }
            this.dismiss();
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public void setAdapter(ListAdapter listAdapter0) {
            this.mListAdapter = listAdapter0;
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public void setBackgroundDrawable(Drawable drawable0) {
            Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public void setHorizontalOffset(int v) {
            Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public void setHorizontalOriginalOffset(int v) {
            Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public void setPromptText(CharSequence charSequence0) {
            this.mPrompt = charSequence0;
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public void setVerticalOffset(int v) {
            Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public void show(int v, int v1) {
            if(this.mListAdapter == null) {
                return;
            }
            Builder alertDialog$Builder0 = new Builder(AppCompatSpinner.this.getPopupContext());
            CharSequence charSequence0 = this.mPrompt;
            if(charSequence0 != null) {
                alertDialog$Builder0.setTitle(charSequence0);
            }
            AlertDialog alertDialog0 = alertDialog$Builder0.setSingleChoiceItems(this.mListAdapter, AppCompatSpinner.this.getSelectedItemPosition(), this).create();
            this.mPopup = alertDialog0;
            ListView listView0 = alertDialog0.getListView();
            listView0.setTextDirection(v);
            listView0.setTextAlignment(v1);
            this.mPopup.show();
        }
    }

    static class DropDownAdapter implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public DropDownAdapter(SpinnerAdapter spinnerAdapter0, Resources.Theme resources$Theme0) {
            this.mAdapter = spinnerAdapter0;
            if(spinnerAdapter0 instanceof ListAdapter) {
                this.mListAdapter = (ListAdapter)spinnerAdapter0;
            }
            if(resources$Theme0 != null) {
                if(Build.VERSION.SDK_INT >= 23 && ComponentDialog..ExternalSyntheticApiModelOutline0.m(spinnerAdapter0)) {
                    Api23Impl.setDropDownViewTheme(((ThemedSpinnerAdapter)spinnerAdapter0), resources$Theme0);
                    return;
                }
                if(spinnerAdapter0 instanceof androidx.appcompat.widget.ThemedSpinnerAdapter && ((androidx.appcompat.widget.ThemedSpinnerAdapter)spinnerAdapter0).getDropDownViewTheme() == null) {
                    ((androidx.appcompat.widget.ThemedSpinnerAdapter)spinnerAdapter0).setDropDownViewTheme(resources$Theme0);
                }
            }
        }

        @Override  // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return this.mListAdapter == null ? true : this.mListAdapter.areAllItemsEnabled();
        }

        @Override  // android.widget.Adapter
        public int getCount() {
            return this.mAdapter == null ? 0 : this.mAdapter.getCount();
        }

        @Override  // android.widget.SpinnerAdapter
        public View getDropDownView(int v, View view0, ViewGroup viewGroup0) {
            return this.mAdapter == null ? null : this.mAdapter.getDropDownView(v, view0, viewGroup0);
        }

        @Override  // android.widget.Adapter
        public Object getItem(int v) {
            return this.mAdapter == null ? null : this.mAdapter.getItem(v);
        }

        @Override  // android.widget.Adapter
        public long getItemId(int v) {
            return this.mAdapter == null ? -1L : this.mAdapter.getItemId(v);
        }

        @Override  // android.widget.Adapter
        public int getItemViewType(int v) {
            return 0;
        }

        @Override  // android.widget.Adapter
        public View getView(int v, View view0, ViewGroup viewGroup0) {
            return this.getDropDownView(v, view0, viewGroup0);
        }

        @Override  // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override  // android.widget.Adapter
        public boolean hasStableIds() {
            return this.mAdapter != null && this.mAdapter.hasStableIds();
        }

        @Override  // android.widget.Adapter
        public boolean isEmpty() {
            return this.getCount() == 0;
        }

        @Override  // android.widget.ListAdapter
        public boolean isEnabled(int v) {
            return this.mListAdapter == null ? true : this.mListAdapter.isEnabled(v);
        }

        @Override  // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver0) {
            SpinnerAdapter spinnerAdapter0 = this.mAdapter;
            if(spinnerAdapter0 != null) {
                spinnerAdapter0.registerDataSetObserver(dataSetObserver0);
            }
        }

        @Override  // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver0) {
            SpinnerAdapter spinnerAdapter0 = this.mAdapter;
            if(spinnerAdapter0 != null) {
                spinnerAdapter0.unregisterDataSetObserver(dataSetObserver0);
            }
        }
    }

    class DropdownPopup extends ListPopupWindow implements SpinnerPopup {
        ListAdapter mAdapter;
        private CharSequence mHintText;
        private int mOriginalHorizontalOffset;
        private final Rect mVisibleRect;

        public DropdownPopup(Context context0, AttributeSet attributeSet0, int v) {
            super(context0, attributeSet0, v);
            this.mVisibleRect = new Rect();
            this.setAnchorView(appCompatSpinner0);
            this.setModal(true);
            this.setPromptPosition(0);
            this.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override  // android.widget.AdapterView$OnItemClickListener
                public void onItemClick(AdapterView adapterView0, View view0, int v, long v1) {
                    AppCompatSpinner.this.setSelection(v);
                    if(AppCompatSpinner.this.getOnItemClickListener() != null) {
                        long v2 = DropdownPopup.this.mAdapter.getItemId(v);
                        AppCompatSpinner.this.performItemClick(view0, v, v2);
                    }
                    DropdownPopup.this.dismiss();
                }
            });
        }

        void computeContentWidth() {
            int v;
            Drawable drawable0 = this.getBackground();
            if(drawable0 == null) {
                AppCompatSpinner.this.mTempRect.right = 0;
                AppCompatSpinner.this.mTempRect.left = 0;
                v = 0;
            }
            else {
                drawable0.getPadding(AppCompatSpinner.this.mTempRect);
                v = ViewUtils.isLayoutRtl(AppCompatSpinner.this) ? AppCompatSpinner.this.mTempRect.right : -AppCompatSpinner.this.mTempRect.left;
            }
            int v1 = AppCompatSpinner.this.getPaddingLeft();
            int v2 = AppCompatSpinner.this.getPaddingRight();
            int v3 = AppCompatSpinner.this.getWidth();
            if(AppCompatSpinner.this.mDropDownWidth == -2) {
                SpinnerAdapter spinnerAdapter0 = (SpinnerAdapter)this.mAdapter;
                Drawable drawable1 = this.getBackground();
                int v4 = AppCompatSpinner.this.compatMeasureContentWidth(spinnerAdapter0, drawable1);
                int v5 = AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels - AppCompatSpinner.this.mTempRect.left - AppCompatSpinner.this.mTempRect.right;
                if(v4 > v5) {
                    v4 = v5;
                }
                this.setContentWidth(Math.max(v4, v3 - v1 - v2));
            }
            else if(AppCompatSpinner.this.mDropDownWidth == -1) {
                this.setContentWidth(v3 - v1 - v2);
            }
            else {
                this.setContentWidth(AppCompatSpinner.this.mDropDownWidth);
            }
            this.setHorizontalOffset((ViewUtils.isLayoutRtl(AppCompatSpinner.this) ? v + (v3 - v2 - this.getWidth() - this.getHorizontalOriginalOffset()) : v + (v1 + this.getHorizontalOriginalOffset())));
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public CharSequence getHintText() {
            return this.mHintText;
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public int getHorizontalOriginalOffset() {
            return this.mOriginalHorizontalOffset;
        }

        // 去混淆评级： 低(20)
        boolean isVisibleToUser(View view0) {
            return view0.isAttachedToWindow() && view0.getGlobalVisibleRect(this.mVisibleRect);
        }

        @Override  // androidx.appcompat.widget.ListPopupWindow, androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public void setAdapter(ListAdapter listAdapter0) {
            super.setAdapter(listAdapter0);
            this.mAdapter = listAdapter0;
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public void setHorizontalOriginalOffset(int v) {
            this.mOriginalHorizontalOffset = v;
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public void setPromptText(CharSequence charSequence0) {
            this.mHintText = charSequence0;
        }

        @Override  // androidx.appcompat.widget.AppCompatSpinner$SpinnerPopup
        public void show(int v, int v1) {
            boolean z = this.isShowing();
            this.computeContentWidth();
            this.setInputMethodMode(2);
            super.show();
            ListView listView0 = this.getListView();
            listView0.setChoiceMode(1);
            listView0.setTextDirection(v);
            listView0.setTextAlignment(v1);
            this.setSelection(AppCompatSpinner.this.getSelectedItemPosition());
            if(!z) {
                ViewTreeObserver viewTreeObserver0 = AppCompatSpinner.this.getViewTreeObserver();
                if(viewTreeObserver0 != null) {
                    androidx.appcompat.widget.AppCompatSpinner.DropdownPopup.2 appCompatSpinner$DropdownPopup$20 = new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
                        public void onGlobalLayout() {
                            if(!DropdownPopup.this.isVisibleToUser(AppCompatSpinner.this)) {
                                DropdownPopup.this.dismiss();
                                return;
                            }
                            DropdownPopup.this.computeContentWidth();
                            DropdownPopup.this.super.show();
                        }
                    };
                    viewTreeObserver0.addOnGlobalLayoutListener(appCompatSpinner$DropdownPopup$20);
                    this.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override  // android.widget.PopupWindow$OnDismissListener
                        public void onDismiss() {
                            ViewTreeObserver viewTreeObserver0 = AppCompatSpinner.this.getViewTreeObserver();
                            if(viewTreeObserver0 != null) {
                                viewTreeObserver0.removeGlobalOnLayoutListener(appCompatSpinner$DropdownPopup$20);
                            }
                        }
                    });
                }
            }
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR;
        boolean mShowDropdown;

        static {
            SavedState.CREATOR = new Parcelable.Creator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                public SavedState[] newArray(int v) {
                    return new SavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        SavedState(Parcel parcel0) {
            super(parcel0);
            this.mShowDropdown = parcel0.readByte() != 0;
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // android.view.View$BaseSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeByte(((byte)this.mShowDropdown));
        }
    }

    interface SpinnerPopup {
        void dismiss();

        Drawable getBackground();

        CharSequence getHintText();

        int getHorizontalOffset();

        int getHorizontalOriginalOffset();

        int getVerticalOffset();

        boolean isShowing();

        void setAdapter(ListAdapter arg1);

        void setBackgroundDrawable(Drawable arg1);

        void setHorizontalOffset(int arg1);

        void setHorizontalOriginalOffset(int arg1);

        void setPromptText(CharSequence arg1);

        void setVerticalOffset(int arg1);

        void show(int arg1, int arg2);
    }

    private static final int[] ATTRS_ANDROID_SPINNERMODE = null;
    private static final int MAX_ITEMS_MEASURED = 15;
    private static final int MODE_DIALOG = 0;
    private static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "AppCompatSpinner";
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    int mDropDownWidth;
    private ForwardingListener mForwardingListener;
    private SpinnerPopup mPopup;
    private final Context mPopupContext;
    private final boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    final Rect mTempRect;

    static {
        AppCompatSpinner.ATTRS_ANDROID_SPINNERMODE = new int[]{0x10102F1};
    }

    public AppCompatSpinner(Context context0) {
        this(context0, null);
    }

    public AppCompatSpinner(Context context0, int v) {
        this(context0, null, attr.spinnerStyle, v);
    }

    public AppCompatSpinner(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.spinnerStyle);
    }

    public AppCompatSpinner(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, -1);
    }

    public AppCompatSpinner(Context context0, AttributeSet attributeSet0, int v, int v1) {
        this(context0, attributeSet0, v, v1, null);
    }

    public AppCompatSpinner(Context context0, AttributeSet attributeSet0, int v, int v1, Resources.Theme resources$Theme0) {
        super(context0, attributeSet0, v);
        TypedArray typedArray1;
        this.mTempRect = new Rect();
        ThemeUtils.checkAppCompatTheme(this, this.getContext());
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, attributeSet0, styleable.Spinner, v, 0);
        this.mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        if(resources$Theme0 == null) {
            int v2 = tintTypedArray0.getResourceId(styleable.Spinner_popupTheme, 0);
            this.mPopupContext = v2 == 0 ? context0 : new ContextThemeWrapper(context0, v2);
        }
        else {
            this.mPopupContext = new ContextThemeWrapper(context0, resources$Theme0);
        }
        TypedArray typedArray0 = null;
        if(v1 == -1) {
            try {
                typedArray1 = null;
                typedArray1 = context0.obtainStyledAttributes(attributeSet0, AppCompatSpinner.ATTRS_ANDROID_SPINNERMODE, v, 0);
                goto label_20;
            }
            catch(Exception exception0) {
                goto label_24;
            }
            catch(Throwable throwable0) {
            }
            goto label_29;
            try {
                try {
                label_20:
                    if(typedArray1.hasValue(0)) {
                        v1 = typedArray1.getInt(0, 0);
                        goto label_32;
                    }
                    else {
                        goto label_34;
                    }
                }
                catch(Exception exception0) {
                label_24:
                    Log.i("AppCompatSpinner", "Could not read android:spinnerMode", exception0);
                    if(typedArray1 != null) {
                        goto label_32;
                    }
                }
                goto label_35;
            }
            catch(Throwable throwable0) {
                typedArray0 = typedArray1;
            }
        label_29:
            if(typedArray0 != null) {
                typedArray0.recycle();
            }
            throw throwable0;
        label_32:
            typedArray1.recycle();
            goto label_35;
        label_34:
            typedArray1.recycle();
        }
    label_35:
        switch(v1) {
            case 0: {
                DialogPopup appCompatSpinner$DialogPopup0 = new DialogPopup(this);
                this.mPopup = appCompatSpinner$DialogPopup0;
                appCompatSpinner$DialogPopup0.setPromptText(tintTypedArray0.getString(styleable.Spinner_android_prompt));
                break;
            }
            case 1: {
                DropdownPopup appCompatSpinner$DropdownPopup0 = new DropdownPopup(this, this.mPopupContext, attributeSet0, v);
                TintTypedArray tintTypedArray1 = TintTypedArray.obtainStyledAttributes(this.mPopupContext, attributeSet0, styleable.Spinner, v, 0);
                this.mDropDownWidth = tintTypedArray1.getLayoutDimension(styleable.Spinner_android_dropDownWidth, -2);
                appCompatSpinner$DropdownPopup0.setBackgroundDrawable(tintTypedArray1.getDrawable(styleable.Spinner_android_popupBackground));
                appCompatSpinner$DropdownPopup0.setPromptText(tintTypedArray0.getString(styleable.Spinner_android_prompt));
                tintTypedArray1.recycle();
                this.mPopup = appCompatSpinner$DropdownPopup0;
                this.mForwardingListener = new ForwardingListener(this) {
                    @Override  // androidx.appcompat.widget.ForwardingListener
                    public ShowableListMenu getPopup() {
                        return appCompatSpinner$DropdownPopup0;
                    }

                    @Override  // androidx.appcompat.widget.ForwardingListener
                    public boolean onForwardingStarted() {
                        if(!AppCompatSpinner.this.getInternalPopup().isShowing()) {
                            AppCompatSpinner.this.showPopup();
                        }
                        return true;
                    }
                };
            }
        }
        CharSequence[] arr_charSequence = tintTypedArray0.getTextArray(styleable.Spinner_android_entries);
        if(arr_charSequence != null) {
            ArrayAdapter arrayAdapter0 = new ArrayAdapter(context0, 0x1090008, arr_charSequence);
            arrayAdapter0.setDropDownViewResource(layout.support_simple_spinner_dropdown_item);
            this.setAdapter(arrayAdapter0);
        }
        tintTypedArray0.recycle();
        this.mPopupSet = true;
        SpinnerAdapter spinnerAdapter0 = this.mTempAdapter;
        if(spinnerAdapter0 != null) {
            this.setAdapter(spinnerAdapter0);
            this.mTempAdapter = null;
        }
        this.mBackgroundTintHelper.loadFromAttributes(attributeSet0, v);
    }

    int compatMeasureContentWidth(SpinnerAdapter spinnerAdapter0, Drawable drawable0) {
        int v = 0;
        if(spinnerAdapter0 == null) {
            return 0;
        }
        int v1 = View.MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0);
        int v2 = View.MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 0);
        int v3 = Math.max(0, this.getSelectedItemPosition());
        int v4 = Math.min(spinnerAdapter0.getCount(), v3 + 15);
        int v5 = Math.max(0, v4 - 15);
        View view0 = null;
        int v6 = 0;
        while(v5 < v4) {
            int v7 = spinnerAdapter0.getItemViewType(v5);
            if(v7 != v) {
                view0 = null;
                v = v7;
            }
            view0 = spinnerAdapter0.getView(v5, view0, this);
            if(view0.getLayoutParams() == null) {
                view0.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view0.measure(v1, v2);
            v6 = Math.max(v6, view0.getMeasuredWidth());
            ++v5;
        }
        if(drawable0 != null) {
            drawable0.getPadding(this.mTempRect);
            return v6 + (this.mTempRect.left + this.mTempRect.right);
        }
        return v6;
    }

    @Override  // android.view.ViewGroup
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.applySupportBackgroundTint();
        }
    }

    @Override  // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        SpinnerPopup appCompatSpinner$SpinnerPopup0 = this.mPopup;
        return appCompatSpinner$SpinnerPopup0 == null ? super.getDropDownHorizontalOffset() : appCompatSpinner$SpinnerPopup0.getHorizontalOffset();
    }

    @Override  // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        SpinnerPopup appCompatSpinner$SpinnerPopup0 = this.mPopup;
        return appCompatSpinner$SpinnerPopup0 == null ? super.getDropDownVerticalOffset() : appCompatSpinner$SpinnerPopup0.getVerticalOffset();
    }

    @Override  // android.widget.Spinner
    public int getDropDownWidth() {
        return this.mPopup == null ? super.getDropDownWidth() : this.mDropDownWidth;
    }

    final SpinnerPopup getInternalPopup() {
        return this.mPopup;
    }

    @Override  // android.widget.Spinner
    public Drawable getPopupBackground() {
        SpinnerPopup appCompatSpinner$SpinnerPopup0 = this.mPopup;
        return appCompatSpinner$SpinnerPopup0 == null ? super.getPopupBackground() : appCompatSpinner$SpinnerPopup0.getBackground();
    }

    @Override  // android.widget.Spinner
    public Context getPopupContext() {
        return this.mPopupContext;
    }

    @Override  // android.widget.Spinner
    public CharSequence getPrompt() {
        SpinnerPopup appCompatSpinner$SpinnerPopup0 = this.mPopup;
        return appCompatSpinner$SpinnerPopup0 == null ? super.getPrompt() : appCompatSpinner$SpinnerPopup0.getHintText();
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public ColorStateList getSupportBackgroundTintList() {
        return this.mBackgroundTintHelper == null ? null : this.mBackgroundTintHelper.getSupportBackgroundTintList();
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.mBackgroundTintHelper == null ? null : this.mBackgroundTintHelper.getSupportBackgroundTintMode();
    }

    @Override  // android.widget.Spinner
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.mPopup != null && this.mPopup.isShowing()) {
            this.mPopup.dismiss();
        }
    }

    @Override  // android.widget.Spinner
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
        if(this.mPopup != null && View.MeasureSpec.getMode(v) == 0x80000000) {
            this.setMeasuredDimension(Math.min(Math.max(this.getMeasuredWidth(), this.compatMeasureContentWidth(this.getAdapter(), this.getBackground())), View.MeasureSpec.getSize(v)), this.getMeasuredHeight());
        }
    }

    @Override  // android.widget.Spinner
    public void onRestoreInstanceState(Parcelable parcelable0) {
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        if(((SavedState)parcelable0).mShowDropdown) {
            ViewTreeObserver viewTreeObserver0 = this.getViewTreeObserver();
            if(viewTreeObserver0 != null) {
                viewTreeObserver0.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        if(!AppCompatSpinner.this.getInternalPopup().isShowing()) {
                            AppCompatSpinner.this.showPopup();
                        }
                        ViewTreeObserver viewTreeObserver0 = AppCompatSpinner.this.getViewTreeObserver();
                        if(viewTreeObserver0 != null) {
                            viewTreeObserver0.removeOnGlobalLayoutListener(this);
                        }
                    }
                });
            }
        }
    }

    @Override  // android.widget.Spinner
    public Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.mShowDropdown = this.mPopup != null && this.mPopup.isShowing();
        return parcelable0;
    }

    @Override  // android.widget.Spinner
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        return this.mForwardingListener == null || !this.mForwardingListener.onTouch(this, motionEvent0) ? super.onTouchEvent(motionEvent0) : true;
    }

    @Override  // android.widget.Spinner
    public boolean performClick() {
        SpinnerPopup appCompatSpinner$SpinnerPopup0 = this.mPopup;
        if(appCompatSpinner$SpinnerPopup0 != null) {
            if(!appCompatSpinner$SpinnerPopup0.isShowing()) {
                this.showPopup();
            }
            return true;
        }
        return super.performClick();
    }

    @Override  // android.widget.Spinner
    public void setAdapter(Adapter adapter0) {
        this.setAdapter(((SpinnerAdapter)adapter0));
    }

    @Override  // android.widget.Spinner
    public void setAdapter(SpinnerAdapter spinnerAdapter0) {
        if(!this.mPopupSet) {
            this.mTempAdapter = spinnerAdapter0;
            return;
        }
        super.setAdapter(spinnerAdapter0);
        if(this.mPopup != null) {
            Context context0 = this.mPopupContext == null ? this.getContext() : this.mPopupContext;
            this.mPopup.setAdapter(new DropDownAdapter(spinnerAdapter0, context0.getTheme()));
        }
    }

    @Override  // android.view.View
    public void setBackgroundDrawable(Drawable drawable0) {
        super.setBackgroundDrawable(drawable0);
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.onSetBackgroundDrawable(drawable0);
        }
    }

    @Override  // android.view.View
    public void setBackgroundResource(int v) {
        super.setBackgroundResource(v);
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.onSetBackgroundResource(v);
        }
    }

    @Override  // android.widget.Spinner
    public void setDropDownHorizontalOffset(int v) {
        SpinnerPopup appCompatSpinner$SpinnerPopup0 = this.mPopup;
        if(appCompatSpinner$SpinnerPopup0 != null) {
            appCompatSpinner$SpinnerPopup0.setHorizontalOriginalOffset(v);
            this.mPopup.setHorizontalOffset(v);
            return;
        }
        super.setDropDownHorizontalOffset(v);
    }

    @Override  // android.widget.Spinner
    public void setDropDownVerticalOffset(int v) {
        SpinnerPopup appCompatSpinner$SpinnerPopup0 = this.mPopup;
        if(appCompatSpinner$SpinnerPopup0 != null) {
            appCompatSpinner$SpinnerPopup0.setVerticalOffset(v);
            return;
        }
        super.setDropDownVerticalOffset(v);
    }

    @Override  // android.widget.Spinner
    public void setDropDownWidth(int v) {
        if(this.mPopup != null) {
            this.mDropDownWidth = v;
            return;
        }
        super.setDropDownWidth(v);
    }

    @Override  // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable0) {
        SpinnerPopup appCompatSpinner$SpinnerPopup0 = this.mPopup;
        if(appCompatSpinner$SpinnerPopup0 != null) {
            appCompatSpinner$SpinnerPopup0.setBackgroundDrawable(drawable0);
            return;
        }
        super.setPopupBackgroundDrawable(drawable0);
    }

    @Override  // android.widget.Spinner
    public void setPopupBackgroundResource(int v) {
        this.setPopupBackgroundDrawable(AppCompatResources.getDrawable(this.getPopupContext(), v));
    }

    @Override  // android.widget.Spinner
    public void setPrompt(CharSequence charSequence0) {
        SpinnerPopup appCompatSpinner$SpinnerPopup0 = this.mPopup;
        if(appCompatSpinner$SpinnerPopup0 != null) {
            appCompatSpinner$SpinnerPopup0.setPromptText(charSequence0);
            return;
        }
        super.setPrompt(charSequence0);
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public void setSupportBackgroundTintList(ColorStateList colorStateList0) {
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.setSupportBackgroundTintList(colorStateList0);
        }
    }

    @Override  // androidx.core.view.TintableBackgroundView
    public void setSupportBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
        AppCompatBackgroundHelper appCompatBackgroundHelper0 = this.mBackgroundTintHelper;
        if(appCompatBackgroundHelper0 != null) {
            appCompatBackgroundHelper0.setSupportBackgroundTintMode(porterDuff$Mode0);
        }
    }

    void showPopup() {
        this.mPopup.show(this.getTextDirection(), this.getTextAlignment());
    }
}

