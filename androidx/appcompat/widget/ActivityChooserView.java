package androidx.appcompat.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.AccessibilityDelegate;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import androidx.appcompat.R.dimen;
import androidx.appcompat.R.id;
import androidx.appcompat.R.layout;
import androidx.appcompat.R.string;
import androidx.appcompat.R.styleable;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.view.ActionProvider;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

public class ActivityChooserView extends ViewGroup implements ActivityChooserModelClient {
    class ActivityChooserViewAdapter extends BaseAdapter {
        private static final int ITEM_VIEW_TYPE_ACTIVITY = 0;
        private static final int ITEM_VIEW_TYPE_COUNT = 3;
        private static final int ITEM_VIEW_TYPE_FOOTER = 1;
        public static final int MAX_ACTIVITY_COUNT_DEFAULT = 4;
        public static final int MAX_ACTIVITY_COUNT_UNLIMITED = 0x7FFFFFFF;
        private ActivityChooserModel mDataModel;
        private boolean mHighlightDefaultActivity;
        private int mMaxActivityCount;
        private boolean mShowDefaultActivity;
        private boolean mShowFooterView;

        ActivityChooserViewAdapter() {
            this.mMaxActivityCount = 4;
        }

        public int getActivityCount() {
            return this.mDataModel.getActivityCount();
        }

        @Override  // android.widget.Adapter
        public int getCount() {
            int v = this.mDataModel.getActivityCount();
            if(!this.mShowDefaultActivity && this.mDataModel.getDefaultActivity() != null) {
                --v;
            }
            int v1 = Math.min(v, this.mMaxActivityCount);
            return this.mShowFooterView ? v1 + 1 : v1;
        }

        public ActivityChooserModel getDataModel() {
            return this.mDataModel;
        }

        public ResolveInfo getDefaultActivity() {
            return this.mDataModel.getDefaultActivity();
        }

        public int getHistorySize() {
            return this.mDataModel.getHistorySize();
        }

        @Override  // android.widget.Adapter
        public Object getItem(int v) {
            switch(this.getItemViewType(v)) {
                case 0: {
                    if(!this.mShowDefaultActivity && this.mDataModel.getDefaultActivity() != null) {
                        ++v;
                    }
                    return this.mDataModel.getActivity(v);
                }
                case 1: {
                    return null;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }

        @Override  // android.widget.Adapter
        public long getItemId(int v) {
            return (long)v;
        }

        @Override  // android.widget.BaseAdapter
        public int getItemViewType(int v) {
            return !this.mShowFooterView || v != this.getCount() - 1 ? 0 : 1;
        }

        public boolean getShowDefaultActivity() {
            return this.mShowDefaultActivity;
        }

        @Override  // android.widget.Adapter
        public View getView(int v, View view0, ViewGroup viewGroup0) {
            switch(this.getItemViewType(v)) {
                case 0: {
                    if(view0 == null || view0.getId() != id.list_item) {
                        view0 = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(layout.abc_activity_chooser_view_list_item, viewGroup0, false);
                    }
                    PackageManager packageManager0 = ActivityChooserView.this.getContext().getPackageManager();
                    ImageView imageView0 = (ImageView)view0.findViewById(id.icon);
                    ResolveInfo resolveInfo0 = (ResolveInfo)this.getItem(v);
                    imageView0.setImageDrawable(resolveInfo0.loadIcon(packageManager0));
                    ((TextView)view0.findViewById(id.title)).setText(resolveInfo0.loadLabel(packageManager0));
                    if(this.mShowDefaultActivity && v == 0 && this.mHighlightDefaultActivity) {
                        view0.setActivated(true);
                        return view0;
                    }
                    view0.setActivated(false);
                    return view0;
                }
                case 1: {
                    if(view0 != null && view0.getId() == 1) {
                        return view0;
                    }
                    View view1 = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(layout.abc_activity_chooser_view_list_item, viewGroup0, false);
                    view1.setId(1);
                    ((TextView)view1.findViewById(id.title)).setText(ActivityChooserView.this.getContext().getString(string.abc_activity_chooser_view_see_all));
                    return view1;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }

        @Override  // android.widget.BaseAdapter
        public int getViewTypeCount() {
            return 3;
        }

        public int measureContentWidth() {
            int v = this.mMaxActivityCount;
            this.mMaxActivityCount = 0x7FFFFFFF;
            int v2 = this.getCount();
            View view0 = null;
            int v3 = 0;
            for(int v1 = 0; v1 < v2; ++v1) {
                view0 = this.getView(v1, view0, null);
                view0.measure(0, 0);
                v3 = Math.max(v3, view0.getMeasuredWidth());
            }
            this.mMaxActivityCount = v;
            return v3;
        }

        public void setDataModel(ActivityChooserModel activityChooserModel0) {
            ActivityChooserModel activityChooserModel1 = ActivityChooserView.this.mAdapter.getDataModel();
            if(activityChooserModel1 != null && ActivityChooserView.this.isShown()) {
                activityChooserModel1.unregisterObserver(ActivityChooserView.this.mModelDataSetObserver);
            }
            this.mDataModel = activityChooserModel0;
            if(activityChooserModel0 != null && ActivityChooserView.this.isShown()) {
                activityChooserModel0.registerObserver(ActivityChooserView.this.mModelDataSetObserver);
            }
            this.notifyDataSetChanged();
        }

        public void setMaxActivityCount(int v) {
            if(this.mMaxActivityCount != v) {
                this.mMaxActivityCount = v;
                this.notifyDataSetChanged();
            }
        }

        public void setShowDefaultActivity(boolean z, boolean z1) {
            if(this.mShowDefaultActivity == z && this.mHighlightDefaultActivity == z1) {
                return;
            }
            this.mShowDefaultActivity = z;
            this.mHighlightDefaultActivity = z1;
            this.notifyDataSetChanged();
        }

        public void setShowFooterView(boolean z) {
            if(this.mShowFooterView != z) {
                this.mShowFooterView = z;
                this.notifyDataSetChanged();
            }
        }
    }

    class Callbacks implements View.OnClickListener, View.OnLongClickListener, AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {
        private void notifyOnDismissListener() {
            if(ActivityChooserView.this.mOnDismissListener != null) {
                ActivityChooserView.this.mOnDismissListener.onDismiss();
            }
        }

        @Override  // android.view.View$OnClickListener
        public void onClick(View view0) {
            if(view0 == ActivityChooserView.this.mDefaultActivityButton) {
                ActivityChooserView.this.dismissPopup();
                ResolveInfo resolveInfo0 = ActivityChooserView.this.mAdapter.getDefaultActivity();
                int v = ActivityChooserView.this.mAdapter.getDataModel().getActivityIndex(resolveInfo0);
                Intent intent0 = ActivityChooserView.this.mAdapter.getDataModel().chooseActivity(v);
                if(intent0 != null) {
                    intent0.addFlags(0x80000);
                    ActivityChooserView.this.getContext().startActivity(intent0);
                }
                return;
            }
            if(view0 != ActivityChooserView.this.mExpandActivityOverflowButton) {
                throw new IllegalArgumentException();
            }
            ActivityChooserView.this.mIsSelectingDefaultActivity = false;
            ActivityChooserView.this.showPopupUnchecked(ActivityChooserView.this.mInitialActivityCount);
        }

        @Override  // android.widget.PopupWindow$OnDismissListener
        public void onDismiss() {
            this.notifyOnDismissListener();
            if(ActivityChooserView.this.mProvider != null) {
                ActivityChooserView.this.mProvider.subUiVisibilityChanged(false);
            }
        }

        @Override  // android.widget.AdapterView$OnItemClickListener
        public void onItemClick(AdapterView adapterView0, View view0, int v, long v1) {
            switch(((ActivityChooserViewAdapter)adapterView0.getAdapter()).getItemViewType(v)) {
                case 0: {
                    ActivityChooserView.this.dismissPopup();
                    if(!ActivityChooserView.this.mIsSelectingDefaultActivity) {
                        if(!ActivityChooserView.this.mAdapter.getShowDefaultActivity()) {
                            ++v;
                        }
                        Intent intent0 = ActivityChooserView.this.mAdapter.getDataModel().chooseActivity(v);
                        if(intent0 != null) {
                            intent0.addFlags(0x80000);
                            ActivityChooserView.this.getContext().startActivity(intent0);
                        }
                    }
                    else if(v > 0) {
                        ActivityChooserView.this.mAdapter.getDataModel().setDefaultActivity(v);
                        return;
                    }
                    return;
                }
                case 1: {
                    ActivityChooserView.this.showPopupUnchecked(0x7FFFFFFF);
                    return;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }

        @Override  // android.view.View$OnLongClickListener
        public boolean onLongClick(View view0) {
            if(view0 != ActivityChooserView.this.mDefaultActivityButton) {
                throw new IllegalArgumentException();
            }
            if(ActivityChooserView.this.mAdapter.getCount() > 0) {
                ActivityChooserView.this.mIsSelectingDefaultActivity = true;
                ActivityChooserView.this.showPopupUnchecked(ActivityChooserView.this.mInitialActivityCount);
            }
            return true;
        }
    }

    public static class InnerLayout extends LinearLayout {
        private static final int[] TINT_ATTRS;

        static {
            InnerLayout.TINT_ATTRS = new int[]{0x10100D4};
        }

        public InnerLayout(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, attributeSet0, InnerLayout.TINT_ATTRS);
            this.setBackgroundDrawable(tintTypedArray0.getDrawable(0));
            tintTypedArray0.recycle();
        }
    }

    private final View mActivityChooserContent;
    private final Drawable mActivityChooserContentBackground;
    final ActivityChooserViewAdapter mAdapter;
    private final Callbacks mCallbacks;
    private int mDefaultActionButtonContentDescription;
    final FrameLayout mDefaultActivityButton;
    private final ImageView mDefaultActivityButtonImage;
    final FrameLayout mExpandActivityOverflowButton;
    private final ImageView mExpandActivityOverflowButtonImage;
    int mInitialActivityCount;
    private boolean mIsAttachedToWindow;
    boolean mIsSelectingDefaultActivity;
    private final int mListPopupMaxWidth;
    private ListPopupWindow mListPopupWindow;
    final DataSetObserver mModelDataSetObserver;
    PopupWindow.OnDismissListener mOnDismissListener;
    private final ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
    ActionProvider mProvider;

    public ActivityChooserView(Context context0) {
        this(context0, null);
    }

    public ActivityChooserView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public ActivityChooserView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mModelDataSetObserver = new DataSetObserver() {
            @Override  // android.database.DataSetObserver
            public void onChanged() {
                super.onChanged();
                ActivityChooserView.this.mAdapter.notifyDataSetChanged();
            }

            @Override  // android.database.DataSetObserver
            public void onInvalidated() {
                super.onInvalidated();
                ActivityChooserView.this.mAdapter.notifyDataSetInvalidated();
            }
        };
        this.mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
            public void onGlobalLayout() {
                if(ActivityChooserView.this.isShowingPopup()) {
                    if(!ActivityChooserView.this.isShown()) {
                        ActivityChooserView.this.getListPopupWindow().dismiss();
                        return;
                    }
                    ActivityChooserView.this.getListPopupWindow().show();
                    if(ActivityChooserView.this.mProvider != null) {
                        ActivityChooserView.this.mProvider.subUiVisibilityChanged(true);
                    }
                }
            }
        };
        this.mInitialActivityCount = 4;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ActivityChooserView, v, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context0, styleable.ActivityChooserView, attributeSet0, typedArray0, v, 0);
        this.mInitialActivityCount = typedArray0.getInt(styleable.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable0 = typedArray0.getDrawable(styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
        typedArray0.recycle();
        LayoutInflater.from(this.getContext()).inflate(layout.abc_activity_chooser_view, this, true);
        Callbacks activityChooserView$Callbacks0 = new Callbacks(this);
        this.mCallbacks = activityChooserView$Callbacks0;
        View view0 = this.findViewById(id.activity_chooser_view_content);
        this.mActivityChooserContent = view0;
        this.mActivityChooserContentBackground = view0.getBackground();
        FrameLayout frameLayout0 = (FrameLayout)this.findViewById(id.default_activity_button);
        this.mDefaultActivityButton = frameLayout0;
        frameLayout0.setOnClickListener(activityChooserView$Callbacks0);
        frameLayout0.setOnLongClickListener(activityChooserView$Callbacks0);
        this.mDefaultActivityButtonImage = (ImageView)frameLayout0.findViewById(id.image);
        FrameLayout frameLayout1 = (FrameLayout)this.findViewById(id.expand_activities_button);
        frameLayout1.setOnClickListener(activityChooserView$Callbacks0);
        frameLayout1.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            @Override  // android.view.View$AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfo accessibilityNodeInfo0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfo0);
                AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0).setCanOpenPopup(true);
            }
        });
        frameLayout1.setOnTouchListener(new ForwardingListener(frameLayout1) {
            @Override  // androidx.appcompat.widget.ForwardingListener
            public ShowableListMenu getPopup() {
                return ActivityChooserView.this.getListPopupWindow();
            }

            @Override  // androidx.appcompat.widget.ForwardingListener
            protected boolean onForwardingStarted() {
                ActivityChooserView.this.showPopup();
                return true;
            }

            @Override  // androidx.appcompat.widget.ForwardingListener
            protected boolean onForwardingStopped() {
                ActivityChooserView.this.dismissPopup();
                return true;
            }
        });
        this.mExpandActivityOverflowButton = frameLayout1;
        ImageView imageView0 = (ImageView)frameLayout1.findViewById(id.image);
        this.mExpandActivityOverflowButtonImage = imageView0;
        imageView0.setImageDrawable(drawable0);
        ActivityChooserViewAdapter activityChooserView$ActivityChooserViewAdapter0 = new ActivityChooserViewAdapter(this);
        this.mAdapter = activityChooserView$ActivityChooserViewAdapter0;
        activityChooserView$ActivityChooserViewAdapter0.registerDataSetObserver(new DataSetObserver() {
            @Override  // android.database.DataSetObserver
            public void onChanged() {
                super.onChanged();
                ActivityChooserView.this.updateAppearance();
            }
        });
        Resources resources0 = context0.getResources();
        this.mListPopupMaxWidth = Math.max(resources0.getDisplayMetrics().widthPixels / 2, resources0.getDimensionPixelSize(dimen.abc_config_prefDialogWidth));
    }

    public boolean dismissPopup() {
        if(this.isShowingPopup()) {
            this.getListPopupWindow().dismiss();
            ViewTreeObserver viewTreeObserver0 = this.getViewTreeObserver();
            if(viewTreeObserver0.isAlive()) {
                viewTreeObserver0.removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
            }
        }
        return true;
    }

    public ActivityChooserModel getDataModel() {
        return this.mAdapter.getDataModel();
    }

    ListPopupWindow getListPopupWindow() {
        if(this.mListPopupWindow == null) {
            ListPopupWindow listPopupWindow0 = new ListPopupWindow(this.getContext());
            this.mListPopupWindow = listPopupWindow0;
            listPopupWindow0.setAdapter(this.mAdapter);
            this.mListPopupWindow.setAnchorView(this);
            this.mListPopupWindow.setModal(true);
            this.mListPopupWindow.setOnItemClickListener(this.mCallbacks);
            this.mListPopupWindow.setOnDismissListener(this.mCallbacks);
        }
        return this.mListPopupWindow;
    }

    public boolean isShowingPopup() {
        return this.getListPopupWindow().isShowing();
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ActivityChooserModel activityChooserModel0 = this.mAdapter.getDataModel();
        if(activityChooserModel0 != null) {
            activityChooserModel0.registerObserver(this.mModelDataSetObserver);
        }
        this.mIsAttachedToWindow = true;
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActivityChooserModel activityChooserModel0 = this.mAdapter.getDataModel();
        if(activityChooserModel0 != null) {
            activityChooserModel0.unregisterObserver(this.mModelDataSetObserver);
        }
        ViewTreeObserver viewTreeObserver0 = this.getViewTreeObserver();
        if(viewTreeObserver0.isAlive()) {
            viewTreeObserver0.removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
        }
        if(this.isShowingPopup()) {
            this.dismissPopup();
        }
        this.mIsAttachedToWindow = false;
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        this.mActivityChooserContent.layout(0, 0, v2 - v, v3 - v1);
        if(!this.isShowingPopup()) {
            this.dismissPopup();
        }
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        View view0 = this.mActivityChooserContent;
        if(this.mDefaultActivityButton.getVisibility() != 0) {
            v1 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(v1), 0x40000000);
        }
        this.measureChild(view0, v, v1);
        this.setMeasuredDimension(view0.getMeasuredWidth(), view0.getMeasuredHeight());
    }

    @Override  // androidx.appcompat.widget.ActivityChooserModel$ActivityChooserModelClient
    public void setActivityChooserModel(ActivityChooserModel activityChooserModel0) {
        this.mAdapter.setDataModel(activityChooserModel0);
        if(this.isShowingPopup()) {
            this.dismissPopup();
            this.showPopup();
        }
    }

    public void setDefaultActionButtonContentDescription(int v) {
        this.mDefaultActionButtonContentDescription = v;
    }

    public void setExpandActivityOverflowButtonContentDescription(int v) {
        String s = this.getContext().getString(v);
        this.mExpandActivityOverflowButtonImage.setContentDescription(s);
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable0) {
        this.mExpandActivityOverflowButtonImage.setImageDrawable(drawable0);
    }

    public void setInitialActivityCount(int v) {
        this.mInitialActivityCount = v;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener popupWindow$OnDismissListener0) {
        this.mOnDismissListener = popupWindow$OnDismissListener0;
    }

    public void setProvider(ActionProvider actionProvider0) {
        this.mProvider = actionProvider0;
    }

    public boolean showPopup() {
        if(!this.isShowingPopup() && this.mIsAttachedToWindow) {
            this.mIsSelectingDefaultActivity = false;
            this.showPopupUnchecked(this.mInitialActivityCount);
            return true;
        }
        return false;
    }

    void showPopupUnchecked(int v) {
        if(this.mAdapter.getDataModel() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        this.getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
        int v1 = this.mDefaultActivityButton.getVisibility() == 0 ? 1 : 0;
        if(v == 0x7FFFFFFF || this.mAdapter.getActivityCount() <= v + v1) {
            this.mAdapter.setShowFooterView(false);
            this.mAdapter.setMaxActivityCount(v);
        }
        else {
            this.mAdapter.setShowFooterView(true);
            this.mAdapter.setMaxActivityCount(v - 1);
        }
        ListPopupWindow listPopupWindow0 = this.getListPopupWindow();
        if(!listPopupWindow0.isShowing()) {
            if(this.mIsSelectingDefaultActivity || v1 == 0) {
                this.mAdapter.setShowDefaultActivity(true, ((boolean)v1));
            }
            else {
                this.mAdapter.setShowDefaultActivity(false, false);
            }
            listPopupWindow0.setContentWidth(Math.min(this.mAdapter.measureContentWidth(), this.mListPopupMaxWidth));
            listPopupWindow0.show();
            ActionProvider actionProvider0 = this.mProvider;
            if(actionProvider0 != null) {
                actionProvider0.subUiVisibilityChanged(true);
            }
            listPopupWindow0.getListView().setContentDescription(this.getContext().getString(string.abc_activitychooserview_choose_application));
            listPopupWindow0.getListView().setSelector(new ColorDrawable(0));
        }
    }

    void updateAppearance() {
        if(this.mAdapter.getCount() > 0) {
            this.mExpandActivityOverflowButton.setEnabled(true);
        }
        else {
            this.mExpandActivityOverflowButton.setEnabled(false);
        }
        int v = this.mAdapter.getActivityCount();
        if(v == 1 || v > 1 && this.mAdapter.getHistorySize() > 0) {
            this.mDefaultActivityButton.setVisibility(0);
            ResolveInfo resolveInfo0 = this.mAdapter.getDefaultActivity();
            PackageManager packageManager0 = this.getContext().getPackageManager();
            Drawable drawable0 = resolveInfo0.loadIcon(packageManager0);
            this.mDefaultActivityButtonImage.setImageDrawable(drawable0);
            if(this.mDefaultActionButtonContentDescription != 0) {
                CharSequence charSequence0 = resolveInfo0.loadLabel(packageManager0);
                String s = this.getContext().getString(this.mDefaultActionButtonContentDescription, new Object[]{charSequence0});
                this.mDefaultActivityButton.setContentDescription(s);
            }
        }
        else {
            this.mDefaultActivityButton.setVisibility(8);
        }
        if(this.mDefaultActivityButton.getVisibility() == 0) {
            this.mActivityChooserContent.setBackgroundDrawable(this.mActivityChooserContentBackground);
            return;
        }
        this.mActivityChooserContent.setBackgroundDrawable(null);
    }
}

