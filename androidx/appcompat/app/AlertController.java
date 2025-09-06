package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AbsListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.id;
import androidx.appcompat.R.styleable;
import androidx.appcompat.widget.LinearLayoutCompat.LayoutParams;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView.OnScrollChangeListener;
import androidx.core.widget.NestedScrollView;
import java.lang.ref.WeakReference;

class AlertController {
    public static class AlertParams {
        public interface OnPrepareListViewListener {
            void onPrepareListView(ListView arg1);
        }

        public ListAdapter mAdapter;
        public boolean mCancelable;
        public int mCheckedItem;
        public boolean[] mCheckedItems;
        public final Context mContext;
        public Cursor mCursor;
        public View mCustomTitleView;
        public boolean mForceInverseBackground;
        public Drawable mIcon;
        public int mIconAttrId;
        public int mIconId;
        public final LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence[] mItems;
        public String mLabelColumn;
        public CharSequence mMessage;
        public Drawable mNegativeButtonIcon;
        public DialogInterface.OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public Drawable mNeutralButtonIcon;
        public DialogInterface.OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
        public DialogInterface.OnClickListener mOnClickListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public AdapterView.OnItemSelectedListener mOnItemSelectedListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public OnPrepareListViewListener mOnPrepareListViewListener;
        public Drawable mPositiveButtonIcon;
        public DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public boolean mRecycleOnMeasure;
        public CharSequence mTitle;
        public View mView;
        public int mViewLayoutResId;
        public int mViewSpacingBottom;
        public int mViewSpacingLeft;
        public int mViewSpacingRight;
        public boolean mViewSpacingSpecified;
        public int mViewSpacingTop;

        public AlertParams(Context context0) {
            this.mIconId = 0;
            this.mIconAttrId = 0;
            this.mViewSpacingSpecified = false;
            this.mCheckedItem = -1;
            this.mRecycleOnMeasure = true;
            this.mContext = context0;
            this.mCancelable = true;
            this.mInflater = (LayoutInflater)context0.getSystemService("layout_inflater");
        }

        public void apply(AlertController alertController0) {
            View view0 = this.mCustomTitleView;
            if(view0 == null) {
                CharSequence charSequence0 = this.mTitle;
                if(charSequence0 != null) {
                    alertController0.setTitle(charSequence0);
                }
                Drawable drawable0 = this.mIcon;
                if(drawable0 != null) {
                    alertController0.setIcon(drawable0);
                }
                int v = this.mIconId;
                if(v != 0) {
                    alertController0.setIcon(v);
                }
                int v1 = this.mIconAttrId;
                if(v1 != 0) {
                    alertController0.setIcon(alertController0.getIconAttributeResId(v1));
                }
            }
            else {
                alertController0.setCustomTitle(view0);
            }
            CharSequence charSequence1 = this.mMessage;
            if(charSequence1 != null) {
                alertController0.setMessage(charSequence1);
            }
            CharSequence charSequence2 = this.mPositiveButtonText;
            if(charSequence2 != null || this.mPositiveButtonIcon != null) {
                alertController0.setButton(-1, charSequence2, this.mPositiveButtonListener, null, this.mPositiveButtonIcon);
            }
            CharSequence charSequence3 = this.mNegativeButtonText;
            if(charSequence3 != null || this.mNegativeButtonIcon != null) {
                alertController0.setButton(-2, charSequence3, this.mNegativeButtonListener, null, this.mNegativeButtonIcon);
            }
            CharSequence charSequence4 = this.mNeutralButtonText;
            if(charSequence4 != null || this.mNeutralButtonIcon != null) {
                alertController0.setButton(-3, charSequence4, this.mNeutralButtonListener, null, this.mNeutralButtonIcon);
            }
            if(this.mItems != null || this.mCursor != null || this.mAdapter != null) {
                this.createListView(alertController0);
            }
            View view1 = this.mView;
            if(view1 != null) {
                if(this.mViewSpacingSpecified) {
                    alertController0.setView(view1, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
                    return;
                }
                alertController0.setView(view1);
                return;
            }
            int v2 = this.mViewLayoutResId;
            if(v2 != 0) {
                alertController0.setView(v2);
            }
        }

        private void createListView(AlertController alertController0) {
            ListAdapter listAdapter0;
            View view0 = this.mInflater.inflate(alertController0.mListLayout, null);
            if(!this.mIsMultiChoice) {
                int v = this.mIsSingleChoice ? alertController0.mSingleChoiceItemLayout : alertController0.mListItemLayout;
                if(this.mCursor == null) {
                    listAdapter0 = this.mAdapter;
                    if(listAdapter0 == null) {
                        listAdapter0 = new CheckedItemAdapter(this.mContext, v, 0x1020014, this.mItems);
                    }
                }
                else {
                    listAdapter0 = new SimpleCursorAdapter(this.mContext, v, this.mCursor, new String[]{this.mLabelColumn}, new int[]{0x1020014});
                }
            }
            else if(this.mCursor == null) {
                listAdapter0 = new ArrayAdapter(this.mContext, alertController0.mMultiChoiceItemLayout, 0x1020014, this.mItems) {
                    @Override  // android.widget.ArrayAdapter
                    public View getView(int v, View view0, ViewGroup viewGroup0) {
                        View view1 = super.getView(v, view0, viewGroup0);
                        if(AlertParams.this.mCheckedItems != null && AlertParams.this.mCheckedItems[v]) {
                            ((RecycleListView)view0).setItemChecked(v, true);
                        }
                        return view1;
                    }
                };
            }
            else {
                listAdapter0 = new CursorAdapter(this.mContext, this.mCursor, false) {
                    private final int mIsCheckedIndex;
                    private final int mLabelIndex;

                    {
                        Context context0 = this.mContext;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                        Cursor cursor0 = this.mCursor;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                        boolean z = false;  // 捕获的参数
                        RecycleListView alertController$RecycleListView0 = (RecycleListView)view0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                        AlertController alertController0 = alertController0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                        Cursor cursor1 = this.getCursor();
                        this.mLabelIndex = cursor1.getColumnIndexOrThrow(alertController$AlertParams0.mLabelColumn);
                        this.mIsCheckedIndex = cursor1.getColumnIndexOrThrow(alertController$AlertParams0.mIsCheckedColumn);
                    }

                    @Override  // android.widget.CursorAdapter
                    public void bindView(View view0, Context context0, Cursor cursor0) {
                        ((CheckedTextView)view0.findViewById(0x1020014)).setText(cursor0.getString(this.mLabelIndex));
                        int v = cursor0.getPosition();
                        boolean z = cursor0.getInt(this.mIsCheckedIndex) == 1;
                        ((RecycleListView)view0).setItemChecked(v, z);
                    }

                    @Override  // android.widget.CursorAdapter
                    public View newView(Context context0, Cursor cursor0, ViewGroup viewGroup0) {
                        return AlertParams.this.mInflater.inflate(alertController0.mMultiChoiceItemLayout, viewGroup0, false);
                    }
                };
            }
            OnPrepareListViewListener alertController$AlertParams$OnPrepareListViewListener0 = this.mOnPrepareListViewListener;
            if(alertController$AlertParams$OnPrepareListViewListener0 != null) {
                alertController$AlertParams$OnPrepareListViewListener0.onPrepareListView(((RecycleListView)view0));
            }
            alertController0.mAdapter = listAdapter0;
            alertController0.mCheckedItem = this.mCheckedItem;
            if(this.mOnClickListener != null) {
                ((RecycleListView)view0).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override  // android.widget.AdapterView$OnItemClickListener
                    public void onItemClick(AdapterView adapterView0, View view0, int v, long v1) {
                        AlertParams.this.mOnClickListener.onClick(alertController0.mDialog, v);
                        if(!AlertParams.this.mIsSingleChoice) {
                            alertController0.mDialog.dismiss();
                        }
                    }
                });
            }
            else if(this.mOnCheckboxClickListener != null) {
                ((RecycleListView)view0).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override  // android.widget.AdapterView$OnItemClickListener
                    public void onItemClick(AdapterView adapterView0, View view0, int v, long v1) {
                        if(AlertParams.this.mCheckedItems != null) {
                            boolean[] arr_z = AlertParams.this.mCheckedItems;
                            arr_z[v] = ((RecycleListView)view0).isItemChecked(v);
                        }
                        DialogInterface.OnMultiChoiceClickListener dialogInterface$OnMultiChoiceClickListener0 = AlertParams.this.mOnCheckboxClickListener;
                        boolean z = ((RecycleListView)view0).isItemChecked(v);
                        dialogInterface$OnMultiChoiceClickListener0.onClick(alertController0.mDialog, v, z);
                    }
                });
            }
            AdapterView.OnItemSelectedListener adapterView$OnItemSelectedListener0 = this.mOnItemSelectedListener;
            if(adapterView$OnItemSelectedListener0 != null) {
                ((RecycleListView)view0).setOnItemSelectedListener(adapterView$OnItemSelectedListener0);
            }
            if(this.mIsSingleChoice) {
                ((RecycleListView)view0).setChoiceMode(1);
            }
            else if(this.mIsMultiChoice) {
                ((RecycleListView)view0).setChoiceMode(2);
            }
            alertController0.mListView = (RecycleListView)view0;
        }
    }

    static final class ButtonHandler extends Handler {
        private static final int MSG_DISMISS_DIALOG = 1;
        private WeakReference mDialog;

        public ButtonHandler(DialogInterface dialogInterface0) {
            this.mDialog = new WeakReference(dialogInterface0);
        }

        @Override  // android.os.Handler
        public void handleMessage(Message message0) {
            switch(message0.what) {
                case -3: 
                case -2: 
                case -1: {
                    ((DialogInterface.OnClickListener)message0.obj).onClick(((DialogInterface)this.mDialog.get()), message0.what);
                    return;
                }
                case 1: {
                    ((DialogInterface)message0.obj).dismiss();
                }
            }
        }
    }

    static class CheckedItemAdapter extends ArrayAdapter {
        public CheckedItemAdapter(Context context0, int v, int v1, CharSequence[] arr_charSequence) {
            super(context0, v, v1, arr_charSequence);
        }

        @Override  // android.widget.ArrayAdapter
        public long getItemId(int v) {
            return (long)v;
        }

        @Override  // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public static class RecycleListView extends ListView {
        private final int mPaddingBottomNoButtons;
        private final int mPaddingTopNoTitle;

        public RecycleListView(Context context0) {
            this(context0, null);
        }

        public RecycleListView(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.RecycleListView);
            this.mPaddingBottomNoButtons = typedArray0.getDimensionPixelOffset(styleable.RecycleListView_paddingBottomNoButtons, -1);
            this.mPaddingTopNoTitle = typedArray0.getDimensionPixelOffset(styleable.RecycleListView_paddingTopNoTitle, -1);
        }

        public void setHasDecor(boolean z, boolean z1) {
            if(z1 && z) {
                return;
            }
            this.setPadding(this.getPaddingLeft(), (z ? this.getPaddingTop() : this.mPaddingTopNoTitle), this.getPaddingRight(), (z1 ? this.getPaddingBottom() : this.mPaddingBottomNoButtons));
        }
    }

    ListAdapter mAdapter;
    private int mAlertDialogLayout;
    private final View.OnClickListener mButtonHandler;
    private final int mButtonIconDimen;
    Button mButtonNegative;
    private Drawable mButtonNegativeIcon;
    Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    Button mButtonNeutral;
    private Drawable mButtonNeutralIcon;
    Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint;
    private int mButtonPanelSideLayout;
    Button mButtonPositive;
    private Drawable mButtonPositiveIcon;
    Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    int mCheckedItem;
    private final Context mContext;
    private View mCustomTitleView;
    final AppCompatDialog mDialog;
    Handler mHandler;
    private Drawable mIcon;
    private int mIconId;
    private ImageView mIconView;
    int mListItemLayout;
    int mListLayout;
    ListView mListView;
    private CharSequence mMessage;
    private TextView mMessageView;
    int mMultiChoiceItemLayout;
    NestedScrollView mScrollView;
    private boolean mShowTitle;
    int mSingleChoiceItemLayout;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private int mViewLayoutResId;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified;
    private int mViewSpacingTop;
    private final Window mWindow;

    public AlertController(Context context0, AppCompatDialog appCompatDialog0, Window window0) {
        this.mViewSpacingSpecified = false;
        this.mIconId = 0;
        this.mCheckedItem = -1;
        this.mButtonPanelLayoutHint = 0;
        this.mButtonHandler = new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                Message message0;
                if(view0 == AlertController.this.mButtonPositive && AlertController.this.mButtonPositiveMessage != null) {
                    message0 = Message.obtain(AlertController.this.mButtonPositiveMessage);
                }
                else if(view0 != AlertController.this.mButtonNegative || AlertController.this.mButtonNegativeMessage == null) {
                    message0 = view0 != AlertController.this.mButtonNeutral || AlertController.this.mButtonNeutralMessage == null ? null : Message.obtain(AlertController.this.mButtonNeutralMessage);
                }
                else {
                    message0 = Message.obtain(AlertController.this.mButtonNegativeMessage);
                }
                if(message0 != null) {
                    message0.sendToTarget();
                }
                AlertController.this.mHandler.obtainMessage(1, AlertController.this.mDialog).sendToTarget();
            }
        };
        this.mContext = context0;
        this.mDialog = appCompatDialog0;
        this.mWindow = window0;
        this.mHandler = new ButtonHandler(appCompatDialog0);
        TypedArray typedArray0 = context0.obtainStyledAttributes(null, styleable.AlertDialog, attr.alertDialogStyle, 0);
        this.mAlertDialogLayout = typedArray0.getResourceId(styleable.AlertDialog_android_layout, 0);
        this.mButtonPanelSideLayout = typedArray0.getResourceId(styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.mListLayout = typedArray0.getResourceId(styleable.AlertDialog_listLayout, 0);
        this.mMultiChoiceItemLayout = typedArray0.getResourceId(styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.mSingleChoiceItemLayout = typedArray0.getResourceId(styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.mListItemLayout = typedArray0.getResourceId(styleable.AlertDialog_listItemLayout, 0);
        this.mShowTitle = typedArray0.getBoolean(styleable.AlertDialog_showTitle, true);
        this.mButtonIconDimen = typedArray0.getDimensionPixelSize(styleable.AlertDialog_buttonIconDimen, 0);
        typedArray0.recycle();
        appCompatDialog0.supportRequestWindowFeature(1);
    }

    static boolean canTextInput(View view0) {
        if(view0.onCheckIsTextEditor()) {
            return true;
        }
        if(!(view0 instanceof ViewGroup)) {
            return false;
        }
        int v = ((ViewGroup)view0).getChildCount();
        while(v > 0) {
            --v;
            if(AlertController.canTextInput(((ViewGroup)view0).getChildAt(v))) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    private void centerButton(Button button0) {
        LinearLayout.LayoutParams linearLayout$LayoutParams0 = (LinearLayout.LayoutParams)button0.getLayoutParams();
        linearLayout$LayoutParams0.gravity = 1;
        linearLayout$LayoutParams0.weight = 0.5f;
        button0.setLayoutParams(linearLayout$LayoutParams0);
    }

    public Button getButton(int v) {
        switch(v) {
            case -3: {
                return this.mButtonNeutral;
            }
            case -2: {
                return this.mButtonNegative;
            }
            case -1: {
                return this.mButtonPositive;
            }
            default: {
                return null;
            }
        }
    }

    public int getIconAttributeResId(int v) {
        TypedValue typedValue0 = new TypedValue();
        this.mContext.getTheme().resolveAttribute(v, typedValue0, true);
        return typedValue0.resourceId;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public void installContent() {
        int v = this.selectContentView();
        this.mDialog.setContentView(v);
        this.setupView();
    }

    // 检测为 Lambda 实现
    static void manageScrollIndicators(View view0, View view1, View view2) [...]

    public boolean onKeyDown(int v, KeyEvent keyEvent0) {
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(keyEvent0);
    }

    public boolean onKeyUp(int v, KeyEvent keyEvent0) {
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(keyEvent0);
    }

    private ViewGroup resolvePanel(View view0, View view1) {
        if(view0 == null) {
            if(view1 instanceof ViewStub) {
                view1 = ((ViewStub)view1).inflate();
            }
            return (ViewGroup)view1;
        }
        if(view1 != null) {
            ViewParent viewParent0 = view1.getParent();
            if(viewParent0 instanceof ViewGroup) {
                ((ViewGroup)viewParent0).removeView(view1);
            }
        }
        if(view0 instanceof ViewStub) {
            view0 = ((ViewStub)view0).inflate();
        }
        return (ViewGroup)view0;
    }

    private int selectContentView() {
        int v = this.mButtonPanelSideLayout;
        if(v == 0) {
            return this.mAlertDialogLayout;
        }
        return this.mButtonPanelLayoutHint == 1 ? v : this.mAlertDialogLayout;
    }

    public void setButton(int v, CharSequence charSequence0, DialogInterface.OnClickListener dialogInterface$OnClickListener0, Message message0, Drawable drawable0) {
        if(message0 == null && dialogInterface$OnClickListener0 != null) {
            message0 = this.mHandler.obtainMessage(v, dialogInterface$OnClickListener0);
        }
        switch(v) {
            case -3: {
                this.mButtonNeutralText = charSequence0;
                this.mButtonNeutralMessage = message0;
                this.mButtonNeutralIcon = drawable0;
                return;
            }
            case -2: {
                this.mButtonNegativeText = charSequence0;
                this.mButtonNegativeMessage = message0;
                this.mButtonNegativeIcon = drawable0;
                return;
            }
            case -1: {
                this.mButtonPositiveText = charSequence0;
                this.mButtonPositiveMessage = message0;
                this.mButtonPositiveIcon = drawable0;
                return;
            }
            default: {
                throw new IllegalArgumentException("Button does not exist");
            }
        }
    }

    public void setButtonPanelLayoutHint(int v) {
        this.mButtonPanelLayoutHint = v;
    }

    public void setCustomTitle(View view0) {
        this.mCustomTitleView = view0;
    }

    public void setIcon(int v) {
        this.mIcon = null;
        this.mIconId = v;
        ImageView imageView0 = this.mIconView;
        if(imageView0 != null) {
            if(v != 0) {
                imageView0.setVisibility(0);
                this.mIconView.setImageResource(this.mIconId);
                return;
            }
            imageView0.setVisibility(8);
        }
    }

    public void setIcon(Drawable drawable0) {
        this.mIcon = drawable0;
        this.mIconId = 0;
        ImageView imageView0 = this.mIconView;
        if(imageView0 != null) {
            if(drawable0 != null) {
                imageView0.setVisibility(0);
                this.mIconView.setImageDrawable(drawable0);
                return;
            }
            imageView0.setVisibility(8);
        }
    }

    public void setMessage(CharSequence charSequence0) {
        this.mMessage = charSequence0;
        TextView textView0 = this.mMessageView;
        if(textView0 != null) {
            textView0.setText(charSequence0);
        }
    }

    private void setScrollIndicators(ViewGroup viewGroup0, View view0, int v, int v1) {
        View view1 = this.mWindow.findViewById(id.scrollIndicatorUp);
        View view2 = this.mWindow.findViewById(id.scrollIndicatorDown);
        if(Build.VERSION.SDK_INT >= 23) {
            ViewCompat.setScrollIndicators(view0, v, v1);
            if(view1 != null) {
                viewGroup0.removeView(view1);
            }
            if(view2 != null) {
                viewGroup0.removeView(view2);
            }
        }
        else {
            if(view1 != null && (v & 1) == 0) {
                viewGroup0.removeView(view1);
                view1 = null;
            }
            if(view2 != null && (v & 2) == 0) {
                viewGroup0.removeView(view2);
                view2 = null;
            }
            if(view1 != null || view2 != null) {
                if(this.mMessage != null) {
                    this.mScrollView.setOnScrollChangeListener((View view0, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */) -> {
                        int v = 0;
                        if(this.val$top != null) {
                            this.val$top.setVisibility((view0.canScrollVertically(-1) ? 0 : 4));
                        }
                        if(this.val$bottom != null) {
                            if(!view0.canScrollVertically(1)) {
                                v = 4;
                            }
                            this.val$bottom.setVisibility(v);
                        }
                    });
                    this.mScrollView.post(() -> {
                        int v = 0;
                        if(this.val$top != null) {
                            this.val$top.setVisibility((AlertController.this.mScrollView.canScrollVertically(-1) ? 0 : 4));
                        }
                        if(this.val$bottom != null) {
                            if(!AlertController.this.mScrollView.canScrollVertically(1)) {
                                v = 4;
                            }
                            this.val$bottom.setVisibility(v);
                        }
                    });
                    return;
                }
                ListView listView0 = this.mListView;
                if(listView0 != null) {
                    listView0.setOnScrollListener(new AbsListView.OnScrollListener() {
                        @Override  // android.widget.AbsListView$OnScrollListener
                        public void onScroll(AbsListView absListView0, int v, int v1, int v2) {
                            AlertController.manageScrollIndicators(absListView0, view1, view2);
                        }

                        @Override  // android.widget.AbsListView$OnScrollListener
                        public void onScrollStateChanged(AbsListView absListView0, int v) {
                        }
                    });
                    this.mListView.post(() -> {
                        int v = 0;
                        if(this.val$top != null) {
                            this.val$top.setVisibility((AlertController.this.mListView.canScrollVertically(-1) ? 0 : 4));
                        }
                        if(this.val$bottom != null) {
                            if(!AlertController.this.mListView.canScrollVertically(1)) {
                                v = 4;
                            }
                            this.val$bottom.setVisibility(v);
                        }
                    });
                    return;
                }
                if(view1 != null) {
                    viewGroup0.removeView(view1);
                }
                if(view2 != null) {
                    viewGroup0.removeView(view2);
                }
            }
        }

        class androidx.appcompat.app.AlertController.2 implements OnScrollChangeListener {
            androidx.appcompat.app.AlertController.2(View view0, View view1) {
            }

            @Override  // androidx.core.widget.NestedScrollView$OnScrollChangeListener
            public void onScrollChange(NestedScrollView nestedScrollView0, int v, int v1, int v2, int v3) {
                AlertController.manageScrollIndicators(nestedScrollView0, this.val$top, this.val$bottom);
            }
        }


        class androidx.appcompat.app.AlertController.3 implements Runnable {
            androidx.appcompat.app.AlertController.3(View view0, View view1) {
            }

            @Override
            public void run() {
                AlertController.manageScrollIndicators(AlertController.this.mScrollView, this.val$top, this.val$bottom);
            }
        }


        class androidx.appcompat.app.AlertController.5 implements Runnable {
            androidx.appcompat.app.AlertController.5(View view0, View view1) {
            }

            @Override
            public void run() {
                AlertController.manageScrollIndicators(AlertController.this.mListView, this.val$top, this.val$bottom);
            }
        }

    }

    public void setTitle(CharSequence charSequence0) {
        this.mTitle = charSequence0;
        TextView textView0 = this.mTitleView;
        if(textView0 != null) {
            textView0.setText(charSequence0);
        }
    }

    public void setView(int v) {
        this.mView = null;
        this.mViewLayoutResId = v;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View view0) {
        this.mView = view0;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View view0, int v, int v1, int v2, int v3) {
        this.mView = view0;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = true;
        this.mViewSpacingLeft = v;
        this.mViewSpacingTop = v1;
        this.mViewSpacingRight = v2;
        this.mViewSpacingBottom = v3;
    }

    private void setupButtons(ViewGroup viewGroup0) {
        int v;
        Button button0 = (Button)viewGroup0.findViewById(0x1020019);
        this.mButtonPositive = button0;
        button0.setOnClickListener(this.mButtonHandler);
        if(!TextUtils.isEmpty(this.mButtonPositiveText) || this.mButtonPositiveIcon != null) {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            Drawable drawable0 = this.mButtonPositiveIcon;
            if(drawable0 != null) {
                drawable0.setBounds(0, 0, this.mButtonIconDimen, this.mButtonIconDimen);
                this.mButtonPositive.setCompoundDrawables(this.mButtonPositiveIcon, null, null, null);
            }
            this.mButtonPositive.setVisibility(0);
            v = 1;
        }
        else {
            this.mButtonPositive.setVisibility(8);
            v = 0;
        }
        Button button1 = (Button)viewGroup0.findViewById(0x102001A);
        this.mButtonNegative = button1;
        button1.setOnClickListener(this.mButtonHandler);
        if(!TextUtils.isEmpty(this.mButtonNegativeText) || this.mButtonNegativeIcon != null) {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            Drawable drawable1 = this.mButtonNegativeIcon;
            if(drawable1 != null) {
                drawable1.setBounds(0, 0, this.mButtonIconDimen, this.mButtonIconDimen);
                this.mButtonNegative.setCompoundDrawables(this.mButtonNegativeIcon, null, null, null);
            }
            this.mButtonNegative.setVisibility(0);
            v |= 2;
        }
        else {
            this.mButtonNegative.setVisibility(8);
        }
        Button button2 = (Button)viewGroup0.findViewById(0x102001B);
        this.mButtonNeutral = button2;
        button2.setOnClickListener(this.mButtonHandler);
        if(!TextUtils.isEmpty(this.mButtonNeutralText) || this.mButtonNeutralIcon != null) {
            this.mButtonNeutral.setText(this.mButtonNeutralText);
            Drawable drawable2 = this.mButtonNeutralIcon;
            if(drawable2 != null) {
                drawable2.setBounds(0, 0, this.mButtonIconDimen, this.mButtonIconDimen);
                this.mButtonNeutral.setCompoundDrawables(this.mButtonNeutralIcon, null, null, null);
            }
            this.mButtonNeutral.setVisibility(0);
            v |= 4;
        }
        else {
            this.mButtonNeutral.setVisibility(8);
        }
        if(AlertController.shouldCenterSingleButton(this.mContext)) {
            if(v == 1) {
                this.centerButton(this.mButtonPositive);
            }
            else {
                switch(v) {
                    case 2: {
                        this.centerButton(this.mButtonNegative);
                        break;
                    }
                    case 4: {
                        this.centerButton(this.mButtonNeutral);
                    }
                }
            }
        }
        if(v != 0) {
            return;
        }
        viewGroup0.setVisibility(8);
    }

    private void setupContent(ViewGroup viewGroup0) {
        NestedScrollView nestedScrollView0 = (NestedScrollView)this.mWindow.findViewById(id.scrollView);
        this.mScrollView = nestedScrollView0;
        nestedScrollView0.setFocusable(false);
        this.mScrollView.setNestedScrollingEnabled(false);
        TextView textView0 = (TextView)viewGroup0.findViewById(0x102000B);
        this.mMessageView = textView0;
        if(textView0 == null) {
            return;
        }
        CharSequence charSequence0 = this.mMessage;
        if(charSequence0 != null) {
            textView0.setText(charSequence0);
            return;
        }
        textView0.setVisibility(8);
        this.mScrollView.removeView(this.mMessageView);
        if(this.mListView != null) {
            ViewGroup viewGroup1 = (ViewGroup)this.mScrollView.getParent();
            int v = viewGroup1.indexOfChild(this.mScrollView);
            viewGroup1.removeViewAt(v);
            viewGroup1.addView(this.mListView, v, new ViewGroup.LayoutParams(-1, -1));
            return;
        }
        viewGroup0.setVisibility(8);
    }

    private void setupCustomContent(ViewGroup viewGroup0) {
        View view0 = this.mView;
        boolean z = false;
        if(view0 == null) {
            view0 = this.mViewLayoutResId == 0 ? null : LayoutInflater.from(this.mContext).inflate(this.mViewLayoutResId, viewGroup0, false);
        }
        if(view0 == null) {
            this.mWindow.setFlags(0x20000, 0x20000);
        }
        else {
            z = true;
            if(!AlertController.canTextInput(view0)) {
                this.mWindow.setFlags(0x20000, 0x20000);
            }
        }
        if(z) {
            FrameLayout frameLayout0 = (FrameLayout)this.mWindow.findViewById(id.custom);
            frameLayout0.addView(view0, new ViewGroup.LayoutParams(-1, -1));
            if(this.mViewSpacingSpecified) {
                frameLayout0.setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            }
            if(this.mListView != null) {
                ((LayoutParams)viewGroup0.getLayoutParams()).weight = 0.0f;
            }
            return;
        }
        viewGroup0.setVisibility(8);
    }

    private void setupTitle(ViewGroup viewGroup0) {
        if(this.mCustomTitleView != null) {
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = new ViewGroup.LayoutParams(-1, -2);
            viewGroup0.addView(this.mCustomTitleView, 0, viewGroup$LayoutParams0);
            this.mWindow.findViewById(id.title_template).setVisibility(8);
            return;
        }
        this.mIconView = (ImageView)this.mWindow.findViewById(0x1020006);
        if(!TextUtils.isEmpty(this.mTitle) && this.mShowTitle) {
            TextView textView0 = (TextView)this.mWindow.findViewById(id.alertTitle);
            this.mTitleView = textView0;
            textView0.setText(this.mTitle);
            int v = this.mIconId;
            if(v != 0) {
                this.mIconView.setImageResource(v);
                return;
            }
            Drawable drawable0 = this.mIcon;
            if(drawable0 != null) {
                this.mIconView.setImageDrawable(drawable0);
                return;
            }
            this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
            this.mIconView.setVisibility(8);
            return;
        }
        this.mWindow.findViewById(id.title_template).setVisibility(8);
        this.mIconView.setVisibility(8);
        viewGroup0.setVisibility(8);
    }

    private void setupView() {
        View view0 = this.mWindow.findViewById(id.parentPanel);
        View view1 = view0.findViewById(id.topPanel);
        View view2 = view0.findViewById(id.contentPanel);
        View view3 = view0.findViewById(id.buttonPanel);
        ViewGroup viewGroup0 = (ViewGroup)view0.findViewById(id.customPanel);
        this.setupCustomContent(viewGroup0);
        View view4 = viewGroup0.findViewById(id.topPanel);
        View view5 = viewGroup0.findViewById(id.contentPanel);
        View view6 = viewGroup0.findViewById(id.buttonPanel);
        ViewGroup viewGroup1 = this.resolvePanel(view4, view1);
        ViewGroup viewGroup2 = this.resolvePanel(view5, view2);
        ViewGroup viewGroup3 = this.resolvePanel(view6, view3);
        this.setupContent(viewGroup2);
        this.setupButtons(viewGroup3);
        this.setupTitle(viewGroup1);
        boolean z = viewGroup0.getVisibility() != 8;
        boolean z1 = viewGroup1 != null && viewGroup1.getVisibility() != 8;
        boolean z2 = viewGroup3 != null && viewGroup3.getVisibility() != 8;
        if(!z2 && viewGroup2 != null) {
            View view7 = viewGroup2.findViewById(id.textSpacerNoButtons);
            if(view7 != null) {
                view7.setVisibility(0);
            }
        }
        if(z1) {
            NestedScrollView nestedScrollView0 = this.mScrollView;
            if(nestedScrollView0 != null) {
                nestedScrollView0.setClipToPadding(true);
            }
            View view8 = this.mMessage != null || this.mListView != null ? viewGroup1.findViewById(id.titleDividerNoCustom) : null;
            if(view8 != null) {
                view8.setVisibility(0);
            }
        }
        else if(viewGroup2 != null) {
            View view9 = viewGroup2.findViewById(id.textSpacerNoTitle);
            if(view9 != null) {
                view9.setVisibility(0);
            }
        }
        ListView listView0 = this.mListView;
        if(listView0 instanceof RecycleListView) {
            ((RecycleListView)listView0).setHasDecor(z1, z2);
        }
        if(!z) {
            ListView listView1 = this.mListView;
            if(listView1 == null) {
                listView1 = this.mScrollView;
            }
            if(listView1 != null) {
                this.setScrollIndicators(viewGroup2, listView1, z1, 3);
            }
        }
        ListView listView2 = this.mListView;
        if(listView2 != null) {
            ListAdapter listAdapter0 = this.mAdapter;
            if(listAdapter0 != null) {
                listView2.setAdapter(listAdapter0);
                int v = this.mCheckedItem;
                if(v > -1) {
                    listView2.setItemChecked(v, true);
                    listView2.setSelection(v);
                }
            }
        }
    }

    private static boolean shouldCenterSingleButton(Context context0) {
        TypedValue typedValue0 = new TypedValue();
        context0.getTheme().resolveAttribute(attr.alertDialogCenterButtons, typedValue0, true);
        return typedValue0.data != 0;
    }
}

