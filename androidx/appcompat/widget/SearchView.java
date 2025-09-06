package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent.DispatcherState;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLayoutChangeListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.TextView;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.dimen;
import androidx.appcompat.R.id;
import androidx.appcompat.R.layout;
import androidx.appcompat.R.string;
import androidx.appcompat.R.styleable;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.core.view.ViewCompat;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.customview.view.AbsSavedState;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class SearchView extends LinearLayoutCompat implements CollapsibleActionView {
    static class Api29Impl {
        static void refreshAutoCompleteResults(AutoCompleteTextView autoCompleteTextView0) {
            autoCompleteTextView0.refreshAutoCompleteResults();
        }

        static void setInputMethodMode(SearchAutoComplete searchView$SearchAutoComplete0, int v) {
            searchView$SearchAutoComplete0.setInputMethodMode(v);
        }
    }

    public interface OnCloseListener {
        boolean onClose();
    }

    public interface OnQueryTextListener {
        boolean onQueryTextChange(String arg1);

        boolean onQueryTextSubmit(String arg1);
    }

    public interface OnSuggestionListener {
        boolean onSuggestionClick(int arg1);

        boolean onSuggestionSelect(int arg1);
    }

    static class PreQAutoCompleteTextViewReflector {
        private Method mDoAfterTextChanged;
        private Method mDoBeforeTextChanged;
        private Method mEnsureImeVisible;

        PreQAutoCompleteTextViewReflector() {
            this.mDoBeforeTextChanged = null;
            this.mDoAfterTextChanged = null;
            this.mEnsureImeVisible = null;
            PreQAutoCompleteTextViewReflector.preApi29Check();
            try {
                Method method0 = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", null);
                this.mDoBeforeTextChanged = method0;
                method0.setAccessible(true);
            }
            catch(NoSuchMethodException unused_ex) {
            }
            try {
                Method method1 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", null);
                this.mDoAfterTextChanged = method1;
                method1.setAccessible(true);
            }
            catch(NoSuchMethodException unused_ex) {
            }
            try {
                Method method2 = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.mEnsureImeVisible = method2;
                method2.setAccessible(true);
            }
            catch(NoSuchMethodException unused_ex) {
            }
        }

        void doAfterTextChanged(AutoCompleteTextView autoCompleteTextView0) {
            PreQAutoCompleteTextViewReflector.preApi29Check();
            Method method0 = this.mDoAfterTextChanged;
            if(method0 != null) {
                try {
                    method0.invoke(autoCompleteTextView0, null);
                }
                catch(Exception unused_ex) {
                }
            }
        }

        void doBeforeTextChanged(AutoCompleteTextView autoCompleteTextView0) {
            PreQAutoCompleteTextViewReflector.preApi29Check();
            Method method0 = this.mDoBeforeTextChanged;
            if(method0 != null) {
                try {
                    method0.invoke(autoCompleteTextView0, null);
                }
                catch(Exception unused_ex) {
                }
            }
        }

        void ensureImeVisible(AutoCompleteTextView autoCompleteTextView0) {
            PreQAutoCompleteTextViewReflector.preApi29Check();
            Method method0 = this.mEnsureImeVisible;
            if(method0 != null) {
                try {
                    method0.invoke(autoCompleteTextView0, Boolean.TRUE);
                }
                catch(Exception unused_ex) {
                }
            }
        }

        private static void preApi29Check() {
            if(Build.VERSION.SDK_INT >= 29) {
                throw new UnsupportedClassVersionError("This function can only be used for API Level < 29.");
            }
        }
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        boolean isIconified;

        static {
            SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0, null);
                }

                public SavedState createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return new SavedState(parcel0, classLoader0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                @Override  // android.os.Parcelable$ClassLoaderCreator
                public Object createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return this.createFromParcel(parcel0, classLoader0);
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

        public SavedState(Parcel parcel0, ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            this.isIconified = ((Boolean)parcel0.readValue(null)).booleanValue();
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override
        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.isIconified + "}";
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeValue(Boolean.valueOf(this.isIconified));
        }
    }

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {
        private boolean mHasPendingShowSoftInputRequest;
        final Runnable mRunShowSoftInputIfNecessary;
        private SearchView mSearchView;
        private int mThreshold;

        public SearchAutoComplete(Context context0) {
            this(context0, null);
        }

        public SearchAutoComplete(Context context0, AttributeSet attributeSet0) {
            this(context0, attributeSet0, attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context0, AttributeSet attributeSet0, int v) {
            super(context0, attributeSet0, v);
            this.mRunShowSoftInputIfNecessary = () -> if(SearchAutoComplete.this.mHasPendingShowSoftInputRequest) {
                ((InputMethodManager)SearchAutoComplete.this.getContext().getSystemService("input_method")).showSoftInput(SearchAutoComplete.this, 0);
                SearchAutoComplete.this.mHasPendingShowSoftInputRequest = false;
            };
            this.mThreshold = this.getThreshold();
        }

        @Override  // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.mThreshold <= 0 || super.enoughToFilter();
        }

        void ensureImeVisible() {
            if(Build.VERSION.SDK_INT >= 29) {
                Api29Impl.setInputMethodMode(this, 1);
                if(this.enoughToFilter()) {
                    this.showDropDown();
                }
                return;
            }
            SearchView.PRE_API_29_HIDDEN_METHOD_INVOKER.ensureImeVisible(this);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration0 = this.getResources().getConfiguration();
            int v = configuration0.screenWidthDp;
            int v1 = configuration0.screenHeightDp;
            if(v >= 960 && v1 >= 720 && configuration0.orientation == 2) {
                return 0x100;
            }
            return v >= 600 || v >= 640 && v1 >= 480 ? 0xC0 : 0xA0;
        }

        boolean isEmpty() {
            return TextUtils.getTrimmedLength(this.getText()) == 0;
        }

        @Override  // androidx.appcompat.widget.AppCompatAutoCompleteTextView
        public InputConnection onCreateInputConnection(EditorInfo editorInfo0) {
            InputConnection inputConnection0 = super.onCreateInputConnection(editorInfo0);
            if(this.mHasPendingShowSoftInputRequest) {
                this.removeCallbacks(this.mRunShowSoftInputIfNecessary);
                this.post(this.mRunShowSoftInputIfNecessary);
            }
            return inputConnection0;
        }

        @Override  // android.view.View
        protected void onFinishInflate() {
            super.onFinishInflate();
            DisplayMetrics displayMetrics0 = this.getResources().getDisplayMetrics();
            this.setMinWidth(((int)TypedValue.applyDimension(1, ((float)this.getSearchViewTextMinWidthDp()), displayMetrics0)));
        }

        @Override  // android.widget.AutoCompleteTextView
        protected void onFocusChanged(boolean z, int v, Rect rect0) {
            super.onFocusChanged(z, v, rect0);
            this.mSearchView.onTextFocusChanged();
        }

        @Override  // android.widget.AutoCompleteTextView
        public boolean onKeyPreIme(int v, KeyEvent keyEvent0) {
            if(v == 4) {
                if(keyEvent0.getAction() == 0 && keyEvent0.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyEvent$DispatcherState0 = this.getKeyDispatcherState();
                    if(keyEvent$DispatcherState0 != null) {
                        keyEvent$DispatcherState0.startTracking(keyEvent0, this);
                    }
                    return true;
                }
                if(keyEvent0.getAction() == 1) {
                    KeyEvent.DispatcherState keyEvent$DispatcherState1 = this.getKeyDispatcherState();
                    if(keyEvent$DispatcherState1 != null) {
                        keyEvent$DispatcherState1.handleUpEvent(keyEvent0);
                    }
                    if(keyEvent0.isTracking() && !keyEvent0.isCanceled()) {
                        this.mSearchView.clearFocus();
                        this.setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(v, keyEvent0);
        }

        @Override  // android.widget.AutoCompleteTextView
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if(z && this.mSearchView.hasFocus() && this.getVisibility() == 0) {
                this.mHasPendingShowSoftInputRequest = true;
                if(SearchView.isLandscapeMode(this.getContext())) {
                    this.ensureImeVisible();
                }
            }
        }

        @Override  // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override  // android.widget.AutoCompleteTextView
        protected void replaceText(CharSequence charSequence0) {
        }

        void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager0 = (InputMethodManager)this.getContext().getSystemService("input_method");
            if(!z) {
                this.mHasPendingShowSoftInputRequest = false;
                this.removeCallbacks(this.mRunShowSoftInputIfNecessary);
                inputMethodManager0.hideSoftInputFromWindow(this.getWindowToken(), 0);
                return;
            }
            if(inputMethodManager0.isActive(this)) {
                this.mHasPendingShowSoftInputRequest = false;
                this.removeCallbacks(this.mRunShowSoftInputIfNecessary);
                inputMethodManager0.showSoftInput(this, 0);
                return;
            }
            this.mHasPendingShowSoftInputRequest = true;
        }

        void setSearchView(SearchView searchView0) {
            this.mSearchView = searchView0;
        }

        @Override  // android.widget.AutoCompleteTextView
        public void setThreshold(int v) {
            super.setThreshold(v);
            this.mThreshold = v;
        }

        // 检测为 Lambda 实现
        void showSoftInputIfNecessary() [...]

        class androidx.appcompat.widget.SearchView.SearchAutoComplete.1 implements Runnable {
            @Override
            public void run() {
                SearchAutoComplete.this.showSoftInputIfNecessary();
            }
        }

    }

    static class UpdatableTouchDelegate extends TouchDelegate {
        private final Rect mActualBounds;
        private boolean mDelegateTargeted;
        private final View mDelegateView;
        private final int mSlop;
        private final Rect mSlopBounds;
        private final Rect mTargetBounds;

        public UpdatableTouchDelegate(Rect rect0, Rect rect1, View view0) {
            super(rect0, view0);
            this.mSlop = ViewConfiguration.get(view0.getContext()).getScaledTouchSlop();
            this.mTargetBounds = new Rect();
            this.mSlopBounds = new Rect();
            this.mActualBounds = new Rect();
            this.setBounds(rect0, rect1);
            this.mDelegateView = view0;
        }

        @Override  // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent0) {
            boolean z2;
            boolean z1;
            int v = (int)motionEvent0.getX();
            int v1 = (int)motionEvent0.getY();
            int v2 = motionEvent0.getAction();
            boolean z = true;
            switch(v2) {
                case 0: {
                    if(this.mTargetBounds.contains(v, v1)) {
                        this.mDelegateTargeted = true;
                        z2 = true;
                    }
                    else {
                        z2 = true;
                        z = false;
                    }
                    break;
                }
                case 1: 
                case 2: {
                    z1 = this.mDelegateTargeted;
                    if(!z1 || this.mSlopBounds.contains(v, v1)) {
                        z = z1;
                        z2 = true;
                    }
                    else {
                        z2 = false;
                    }
                    break;
                }
                default: {
                    if(v2 == 3) {
                        z1 = this.mDelegateTargeted;
                        this.mDelegateTargeted = false;
                        z = z1;
                        z2 = true;
                    }
                    else {
                        z2 = true;
                        z = false;
                    }
                }
            }
            if(z) {
                if(z2 && !this.mActualBounds.contains(v, v1)) {
                    motionEvent0.setLocation(((float)(this.mDelegateView.getWidth() / 2)), ((float)(this.mDelegateView.getHeight() / 2)));
                    return this.mDelegateView.dispatchTouchEvent(motionEvent0);
                }
                motionEvent0.setLocation(((float)(v - this.mActualBounds.left)), ((float)(v1 - this.mActualBounds.top)));
                return this.mDelegateView.dispatchTouchEvent(motionEvent0);
            }
            return false;
        }

        public void setBounds(Rect rect0, Rect rect1) {
            this.mTargetBounds.set(rect0);
            this.mSlopBounds.set(rect0);
            this.mSlopBounds.inset(-this.mSlop, -this.mSlop);
            this.mActualBounds.set(rect1);
        }
    }

    static final boolean DBG = false;
    private static final String IME_OPTION_NO_MICROPHONE = "nm";
    static final String LOG_TAG = "SearchView";
    static final PreQAutoCompleteTextViewReflector PRE_API_29_HIDDEN_METHOD_INVOKER;
    private Bundle mAppSearchData;
    private boolean mClearingFocus;
    final ImageView mCloseButton;
    private final ImageView mCollapsedIcon;
    private int mCollapsedImeOptions;
    private final CharSequence mDefaultQueryHint;
    private final View mDropDownAnchor;
    private boolean mExpandedInActionView;
    final ImageView mGoButton;
    private boolean mIconified;
    private boolean mIconifiedByDefault;
    private int mMaxWidth;
    private CharSequence mOldQueryText;
    private final View.OnClickListener mOnClickListener;
    private OnCloseListener mOnCloseListener;
    private final TextView.OnEditorActionListener mOnEditorActionListener;
    private final AdapterView.OnItemClickListener mOnItemClickListener;
    private final AdapterView.OnItemSelectedListener mOnItemSelectedListener;
    private OnQueryTextListener mOnQueryChangeListener;
    View.OnFocusChangeListener mOnQueryTextFocusChangeListener;
    private View.OnClickListener mOnSearchClickListener;
    private OnSuggestionListener mOnSuggestionListener;
    private final WeakHashMap mOutsideDrawablesCache;
    private CharSequence mQueryHint;
    private boolean mQueryRefinement;
    private Runnable mReleaseCursorRunnable;
    final ImageView mSearchButton;
    private final View mSearchEditFrame;
    private final Drawable mSearchHintIcon;
    private final View mSearchPlate;
    final SearchAutoComplete mSearchSrcTextView;
    private Rect mSearchSrcTextViewBounds;
    private Rect mSearchSrtTextViewBoundsExpanded;
    SearchableInfo mSearchable;
    private final View mSubmitArea;
    private boolean mSubmitButtonEnabled;
    private final int mSuggestionCommitIconResId;
    private final int mSuggestionRowLayout;
    CursorAdapter mSuggestionsAdapter;
    private int[] mTemp;
    private int[] mTemp2;
    View.OnKeyListener mTextKeyListener;
    private TextWatcher mTextWatcher;
    private UpdatableTouchDelegate mTouchDelegate;
    private final Runnable mUpdateDrawableStateRunnable;
    private CharSequence mUserQuery;
    private final Intent mVoiceAppSearchIntent;
    final ImageView mVoiceButton;
    private boolean mVoiceButtonEnabled;
    private final Intent mVoiceWebSearchIntent;

    static {
        SearchView.PRE_API_29_HIDDEN_METHOD_INVOKER = Build.VERSION.SDK_INT >= 29 ? null : new PreQAutoCompleteTextViewReflector();
    }

    public SearchView(Context context0) {
        this(context0, null);
    }

    public SearchView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.searchViewStyle);
    }

    public SearchView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mSearchSrcTextViewBounds = new Rect();
        this.mSearchSrtTextViewBoundsExpanded = new Rect();
        this.mTemp = new int[2];
        this.mTemp2 = new int[2];
        this.mUpdateDrawableStateRunnable = () -> {
            int[] arr_v = SearchView.this.mSearchSrcTextView.hasFocus() ? SearchView.FOCUSED_STATE_SET : SearchView.EMPTY_STATE_SET;
            Drawable drawable0 = SearchView.this.mSearchPlate.getBackground();
            if(drawable0 != null) {
                drawable0.setState(arr_v);
            }
            Drawable drawable1 = SearchView.this.mSubmitArea.getBackground();
            if(drawable1 != null) {
                drawable1.setState(arr_v);
            }
            SearchView.this.invalidate();
        };
        this.mReleaseCursorRunnable = new Runnable() {
            @Override
            public void run() {
                if(SearchView.this.mSuggestionsAdapter instanceof SuggestionsAdapter) {
                    SearchView.this.mSuggestionsAdapter.changeCursor(null);
                }
            }
        };
        this.mOutsideDrawablesCache = new WeakHashMap();
        androidx.appcompat.widget.SearchView.5 searchView$50 = new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                if(view0 == SearchView.this.mSearchButton) {
                    SearchView.this.onSearchClicked();
                    return;
                }
                if(view0 == SearchView.this.mCloseButton) {
                    SearchView.this.onCloseClicked();
                    return;
                }
                if(view0 == SearchView.this.mGoButton) {
                    SearchView.this.onSubmitQuery();
                    return;
                }
                if(view0 == SearchView.this.mVoiceButton) {
                    SearchView.this.onVoiceClicked();
                    return;
                }
                if(view0 == SearchView.this.mSearchSrcTextView) {
                    SearchView.this.forceSuggestionQuery();
                }
            }
        };
        this.mOnClickListener = searchView$50;
        this.mTextKeyListener = new View.OnKeyListener() {
            @Override  // android.view.View$OnKeyListener
            public boolean onKey(View view0, int v, KeyEvent keyEvent0) {
                if(SearchView.this.mSearchable == null) {
                    return false;
                }
                if(SearchView.this.mSearchSrcTextView.isPopupShowing() && SearchView.this.mSearchSrcTextView.getListSelection() != -1) {
                    return SearchView.this.onSuggestionsKey(view0, v, keyEvent0);
                }
                if(!SearchView.this.mSearchSrcTextView.isEmpty() && keyEvent0.hasNoModifiers() && keyEvent0.getAction() == 1 && v == 66) {
                    view0.cancelLongPress();
                    String s = SearchView.this.mSearchSrcTextView.getText().toString();
                    SearchView.this.launchQuerySearch(0, null, s);
                    return true;
                }
                return false;
            }
        };
        androidx.appcompat.widget.SearchView.7 searchView$70 = new TextView.OnEditorActionListener() {
            @Override  // android.widget.TextView$OnEditorActionListener
            public boolean onEditorAction(TextView textView0, int v, KeyEvent keyEvent0) {
                SearchView.this.onSubmitQuery();
                return true;
            }
        };
        this.mOnEditorActionListener = searchView$70;
        androidx.appcompat.widget.SearchView.8 searchView$80 = (/* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, int v, /* 缺少LAMBDA参数 */) -> {
            if(SearchView.this.mOnSuggestionListener != null && SearchView.this.mOnSuggestionListener.onSuggestionClick(v)) {
                return false;
            }
            SearchView.this.launchSuggestion(v, 0, null);
            SearchView.this.mSearchSrcTextView.setImeVisibility(false);
            SearchView.this.dismissSuggestions();
            return true;
        };
        this.mOnItemClickListener = searchView$80;
        androidx.appcompat.widget.SearchView.9 searchView$90 = new AdapterView.OnItemSelectedListener() {
            @Override  // android.widget.AdapterView$OnItemSelectedListener
            public void onItemSelected(AdapterView adapterView0, View view0, int v, long v1) {
                SearchView.this.onItemSelected(v);
            }

            @Override  // android.widget.AdapterView$OnItemSelectedListener
            public void onNothingSelected(AdapterView adapterView0) {
            }
        };
        this.mOnItemSelectedListener = searchView$90;
        this.mTextWatcher = new TextWatcher() {
            @Override  // android.text.TextWatcher
            public void afterTextChanged(Editable editable0) {
            }

            @Override  // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
            }

            @Override  // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
                SearchView.this.onTextChanged(charSequence0);
            }
        };
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, attributeSet0, styleable.SearchView, v, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context0, styleable.SearchView, attributeSet0, tintTypedArray0.getWrappedTypeArray(), v, 0);
        LayoutInflater.from(context0).inflate(tintTypedArray0.getResourceId(styleable.SearchView_layout, layout.abc_search_view), this, true);
        SearchAutoComplete searchView$SearchAutoComplete0 = (SearchAutoComplete)this.findViewById(id.search_src_text);
        this.mSearchSrcTextView = searchView$SearchAutoComplete0;
        searchView$SearchAutoComplete0.setSearchView(this);
        this.mSearchEditFrame = this.findViewById(id.search_edit_frame);
        View view0 = this.findViewById(id.search_plate);
        this.mSearchPlate = view0;
        View view1 = this.findViewById(id.submit_area);
        this.mSubmitArea = view1;
        ImageView imageView0 = (ImageView)this.findViewById(id.search_button);
        this.mSearchButton = imageView0;
        ImageView imageView1 = (ImageView)this.findViewById(id.search_go_btn);
        this.mGoButton = imageView1;
        ImageView imageView2 = (ImageView)this.findViewById(id.search_close_btn);
        this.mCloseButton = imageView2;
        ImageView imageView3 = (ImageView)this.findViewById(id.search_voice_btn);
        this.mVoiceButton = imageView3;
        ImageView imageView4 = (ImageView)this.findViewById(id.search_mag_icon);
        this.mCollapsedIcon = imageView4;
        ViewCompat.setBackground(view0, tintTypedArray0.getDrawable(styleable.SearchView_queryBackground));
        ViewCompat.setBackground(view1, tintTypedArray0.getDrawable(styleable.SearchView_submitBackground));
        imageView0.setImageDrawable(tintTypedArray0.getDrawable(styleable.SearchView_searchIcon));
        imageView1.setImageDrawable(tintTypedArray0.getDrawable(styleable.SearchView_goIcon));
        imageView2.setImageDrawable(tintTypedArray0.getDrawable(styleable.SearchView_closeIcon));
        imageView3.setImageDrawable(tintTypedArray0.getDrawable(styleable.SearchView_voiceIcon));
        imageView4.setImageDrawable(tintTypedArray0.getDrawable(styleable.SearchView_searchIcon));
        this.mSearchHintIcon = tintTypedArray0.getDrawable(styleable.SearchView_searchHintIcon);
        TooltipCompat.setTooltipText(imageView0, this.getResources().getString(string.abc_searchview_description_search));
        this.mSuggestionRowLayout = tintTypedArray0.getResourceId(styleable.SearchView_suggestionRowLayout, layout.abc_search_dropdown_item_icons_2line);
        this.mSuggestionCommitIconResId = tintTypedArray0.getResourceId(styleable.SearchView_commitIcon, 0);
        imageView0.setOnClickListener(searchView$50);
        imageView2.setOnClickListener(searchView$50);
        imageView1.setOnClickListener(searchView$50);
        imageView3.setOnClickListener(searchView$50);
        searchView$SearchAutoComplete0.setOnClickListener(searchView$50);
        searchView$SearchAutoComplete0.addTextChangedListener(this.mTextWatcher);
        searchView$SearchAutoComplete0.setOnEditorActionListener(searchView$70);
        searchView$SearchAutoComplete0.setOnItemClickListener(searchView$80);
        searchView$SearchAutoComplete0.setOnItemSelectedListener(searchView$90);
        searchView$SearchAutoComplete0.setOnKeyListener(this.mTextKeyListener);
        searchView$SearchAutoComplete0.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override  // android.view.View$OnFocusChangeListener
            public void onFocusChange(View view0, boolean z) {
                if(SearchView.this.mOnQueryTextFocusChangeListener != null) {
                    SearchView.this.mOnQueryTextFocusChangeListener.onFocusChange(SearchView.this, z);
                }
            }
        });
        this.setIconifiedByDefault(tintTypedArray0.getBoolean(styleable.SearchView_iconifiedByDefault, true));
        int v1 = tintTypedArray0.getDimensionPixelSize(styleable.SearchView_android_maxWidth, -1);
        if(v1 != -1) {
            this.setMaxWidth(v1);
        }
        this.mDefaultQueryHint = tintTypedArray0.getText(styleable.SearchView_defaultQueryHint);
        this.mQueryHint = tintTypedArray0.getText(styleable.SearchView_queryHint);
        int v2 = tintTypedArray0.getInt(styleable.SearchView_android_imeOptions, -1);
        if(v2 != -1) {
            this.setImeOptions(v2);
        }
        int v3 = tintTypedArray0.getInt(styleable.SearchView_android_inputType, -1);
        if(v3 != -1) {
            this.setInputType(v3);
        }
        this.setFocusable(tintTypedArray0.getBoolean(styleable.SearchView_android_focusable, true));
        tintTypedArray0.recycle();
        Intent intent0 = new Intent("android.speech.action.WEB_SEARCH");
        this.mVoiceWebSearchIntent = intent0;
        intent0.addFlags(0x10000000);
        intent0.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent1 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.mVoiceAppSearchIntent = intent1;
        intent1.addFlags(0x10000000);
        View view2 = this.findViewById(searchView$SearchAutoComplete0.getDropDownAnchor());
        this.mDropDownAnchor = view2;
        if(view2 != null) {
            view2.addOnLayoutChangeListener((/* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */) -> if(SearchView.this.mDropDownAnchor.getWidth() > 1) {
                Resources resources0 = SearchView.this.getContext().getResources();
                int v = SearchView.this.mSearchPlate.getPaddingLeft();
                Rect rect0 = new Rect();
                boolean z = ViewUtils.isLayoutRtl(SearchView.this);
                int v1 = SearchView.this.mIconifiedByDefault ? resources0.getDimensionPixelSize(dimen.abc_dropdownitem_icon_width) + resources0.getDimensionPixelSize(dimen.abc_dropdownitem_text_padding_left) : 0;
                SearchView.this.mSearchSrcTextView.getDropDownBackground().getPadding(rect0);
                SearchView.this.mSearchSrcTextView.setDropDownHorizontalOffset((z ? -rect0.left : v - (rect0.left + v1)));
                int v2 = SearchView.this.mDropDownAnchor.getWidth();
                SearchView.this.mSearchSrcTextView.setDropDownWidth(v2 + rect0.left + rect0.right + v1 - v);
            });
        }
        this.updateViewsVisibility(this.mIconifiedByDefault);
        this.updateQueryHint();

        class androidx.appcompat.widget.SearchView.4 implements View.OnLayoutChangeListener {
            @Override  // android.view.View$OnLayoutChangeListener
            public void onLayoutChange(View view0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
                SearchView.this.adjustDropDownSizeAndPosition();
            }
        }

    }

    // 检测为 Lambda 实现
    void adjustDropDownSizeAndPosition() [...]

    @Override  // android.view.ViewGroup
    public void clearFocus() {
        this.mClearingFocus = true;
        super.clearFocus();
        this.mSearchSrcTextView.clearFocus();
        this.mSearchSrcTextView.setImeVisibility(false);
        this.mClearingFocus = false;
    }

    private Intent createIntent(String s, Uri uri0, String s1, String s2, int v, String s3) {
        Intent intent0 = new Intent(s);
        intent0.addFlags(0x10000000);
        if(uri0 != null) {
            intent0.setData(uri0);
        }
        intent0.putExtra("user_query", this.mUserQuery);
        if(s2 != null) {
            intent0.putExtra("query", s2);
        }
        if(s1 != null) {
            intent0.putExtra("intent_extra_data_key", s1);
        }
        Bundle bundle0 = this.mAppSearchData;
        if(bundle0 != null) {
            intent0.putExtra("app_data", bundle0);
        }
        if(v != 0) {
            intent0.putExtra("action_key", v);
            intent0.putExtra("action_msg", s3);
        }
        intent0.setComponent(this.mSearchable.getSearchActivity());
        return intent0;
    }

    private Intent createIntentFromSuggestion(Cursor cursor0, int v, String s) {
        int v1;
        try {
            String s1 = SuggestionsAdapter.getColumnString(cursor0, "suggest_intent_action");
            if(s1 == null) {
                s1 = this.mSearchable.getSuggestIntentAction();
            }
            if(s1 == null) {
                s1 = "android.intent.action.SEARCH";
            }
            String s2 = SuggestionsAdapter.getColumnString(cursor0, "suggest_intent_data");
            if(s2 == null) {
                s2 = this.mSearchable.getSuggestIntentData();
            }
            if(s2 != null) {
                String s3 = SuggestionsAdapter.getColumnString(cursor0, "suggest_intent_data_id");
                if(s3 != null) {
                    s2 = s2 + "/" + Uri.encode(s3);
                }
            }
            Uri uri0 = s2 == null ? null : Uri.parse(s2);
            String s4 = SuggestionsAdapter.getColumnString(cursor0, "suggest_intent_query");
            return this.createIntent(s1, uri0, SuggestionsAdapter.getColumnString(cursor0, "suggest_intent_extra_data"), s4, v, s);
        }
        catch(RuntimeException runtimeException0) {
            try {
                v1 = -1;
                v1 = cursor0.getPosition();
            }
            catch(RuntimeException unused_ex) {
            }
            Log.w("SearchView", "Search suggestions cursor at row " + v1 + " returned exception.", runtimeException0);
            return null;
        }
    }

    private Intent createVoiceAppSearchIntent(Intent intent0, SearchableInfo searchableInfo0) {
        ComponentName componentName0 = searchableInfo0.getSearchActivity();
        Intent intent1 = new Intent("android.intent.action.SEARCH");
        intent1.setComponent(componentName0);
        PendingIntent pendingIntent0 = PendingIntent.getActivity(this.getContext(), 0, intent1, 0x42000000);
        Bundle bundle0 = new Bundle();
        Bundle bundle1 = this.mAppSearchData;
        if(bundle1 != null) {
            bundle0.putParcelable("app_data", bundle1);
        }
        Intent intent2 = new Intent(intent0);
        Resources resources0 = this.getResources();
        String s = searchableInfo0.getVoiceLanguageModeId() == 0 ? "free_form" : resources0.getString(searchableInfo0.getVoiceLanguageModeId());
        String s1 = null;
        String s2 = searchableInfo0.getVoicePromptTextId() == 0 ? null : resources0.getString(searchableInfo0.getVoicePromptTextId());
        String s3 = searchableInfo0.getVoiceLanguageId() == 0 ? null : resources0.getString(searchableInfo0.getVoiceLanguageId());
        int v = searchableInfo0.getVoiceMaxResults() == 0 ? 1 : searchableInfo0.getVoiceMaxResults();
        intent2.putExtra("android.speech.extra.LANGUAGE_MODEL", s);
        intent2.putExtra("android.speech.extra.PROMPT", s2);
        intent2.putExtra("android.speech.extra.LANGUAGE", s3);
        intent2.putExtra("android.speech.extra.MAX_RESULTS", v);
        if(componentName0 != null) {
            s1 = componentName0.flattenToShortString();
        }
        intent2.putExtra("calling_package", s1);
        intent2.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", pendingIntent0);
        intent2.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle0);
        return intent2;
    }

    private Intent createVoiceWebSearchIntent(Intent intent0, SearchableInfo searchableInfo0) {
        Intent intent1 = new Intent(intent0);
        ComponentName componentName0 = searchableInfo0.getSearchActivity();
        intent1.putExtra("calling_package", (componentName0 == null ? null : componentName0.flattenToShortString()));
        return intent1;
    }

    private void dismissSuggestions() {
        this.mSearchSrcTextView.dismissDropDown();
    }

    void forceSuggestionQuery() {
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.refreshAutoCompleteResults(this.mSearchSrcTextView);
            return;
        }
        SearchView.PRE_API_29_HIDDEN_METHOD_INVOKER.doBeforeTextChanged(this.mSearchSrcTextView);
        SearchView.PRE_API_29_HIDDEN_METHOD_INVOKER.doAfterTextChanged(this.mSearchSrcTextView);
    }

    private void getChildBoundsWithinSearchView(View view0, Rect rect0) {
        view0.getLocationInWindow(this.mTemp);
        this.getLocationInWindow(this.mTemp2);
        int[] arr_v = this.mTemp;
        int[] arr_v1 = this.mTemp2;
        int v = arr_v[1] - arr_v1[1];
        int v1 = arr_v[0] - arr_v1[0];
        rect0.set(v1, v, view0.getWidth() + v1, view0.getHeight() + v);
    }

    private CharSequence getDecoratedHint(CharSequence charSequence0) {
        if(this.mIconifiedByDefault && this.mSearchHintIcon != null) {
            int v = (int)(((double)this.mSearchSrcTextView.getTextSize()) * 1.25);
            this.mSearchHintIcon.setBounds(0, 0, v, v);
            CharSequence charSequence1 = new SpannableStringBuilder("   ");
            ((SpannableStringBuilder)charSequence1).setSpan(new ImageSpan(this.mSearchHintIcon), 1, 2, 33);
            ((SpannableStringBuilder)charSequence1).append(charSequence0);
            return charSequence1;
        }
        return charSequence0;
    }

    public int getImeOptions() {
        return this.mSearchSrcTextView.getImeOptions();
    }

    public int getInputType() {
        return this.mSearchSrcTextView.getInputType();
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    private int getPreferredHeight() {
        return this.getContext().getResources().getDimensionPixelSize(dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return this.getContext().getResources().getDimensionPixelSize(dimen.abc_search_view_preferred_width);
    }

    public CharSequence getQuery() {
        return this.mSearchSrcTextView.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence0 = this.mQueryHint;
        if(charSequence0 != null) {
            return charSequence0;
        }
        return this.mSearchable == null || this.mSearchable.getHintId() == 0 ? this.mDefaultQueryHint : this.getContext().getText(this.mSearchable.getHintId());
    }

    int getSuggestionCommitIconResId() {
        return this.mSuggestionCommitIconResId;
    }

    int getSuggestionRowLayout() {
        return this.mSuggestionRowLayout;
    }

    public CursorAdapter getSuggestionsAdapter() {
        return this.mSuggestionsAdapter;
    }

    private boolean hasVoiceSearch() {
        if(this.mSearchable != null && this.mSearchable.getVoiceSearchEnabled()) {
            if(this.mSearchable.getVoiceSearchLaunchWebSearch()) {
                return this.mVoiceWebSearchIntent != null && this.getContext().getPackageManager().resolveActivity(this.mVoiceWebSearchIntent, 0x10000) != null;
            }
            Intent intent0 = this.mSearchable.getVoiceSearchLaunchRecognizer() ? this.mVoiceAppSearchIntent : null;
            return intent0 != null && this.getContext().getPackageManager().resolveActivity(intent0, 0x10000) != null;
        }
        return false;
    }

    public boolean isIconfiedByDefault() {
        return this.mIconifiedByDefault;
    }

    public boolean isIconified() {
        return this.mIconified;
    }

    static boolean isLandscapeMode(Context context0) {
        return context0.getResources().getConfiguration().orientation == 2;
    }

    public boolean isQueryRefinementEnabled() {
        return this.mQueryRefinement;
    }

    // 去混淆评级： 低(30)
    private boolean isSubmitAreaEnabled() {
        return (this.mSubmitButtonEnabled || this.mVoiceButtonEnabled) && !this.isIconified();
    }

    public boolean isSubmitButtonEnabled() {
        return this.mSubmitButtonEnabled;
    }

    private void launchIntent(Intent intent0) {
        if(intent0 != null) {
            try {
                this.getContext().startActivity(intent0);
            }
            catch(RuntimeException runtimeException0) {
                Log.e("SearchView", "Failed launch activity: " + intent0, runtimeException0);
            }
        }
    }

    void launchQuerySearch(int v, String s, String s1) {
        Intent intent0 = this.createIntent("android.intent.action.SEARCH", null, null, s1, v, s);
        this.getContext().startActivity(intent0);
    }

    private boolean launchSuggestion(int v, int v1, String s) {
        Cursor cursor0 = this.mSuggestionsAdapter.getCursor();
        if(cursor0 != null && cursor0.moveToPosition(v)) {
            this.launchIntent(this.createIntentFromSuggestion(cursor0, v1, s));
            return true;
        }
        return false;
    }

    @Override  // androidx.appcompat.view.CollapsibleActionView
    public void onActionViewCollapsed() {
        this.setQuery("", false);
        this.clearFocus();
        this.updateViewsVisibility(true);
        this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions);
        this.mExpandedInActionView = false;
    }

    @Override  // androidx.appcompat.view.CollapsibleActionView
    public void onActionViewExpanded() {
        if(this.mExpandedInActionView) {
            return;
        }
        this.mExpandedInActionView = true;
        int v = this.mSearchSrcTextView.getImeOptions();
        this.mCollapsedImeOptions = v;
        this.mSearchSrcTextView.setImeOptions(v | 0x2000000);
        this.mSearchSrcTextView.setText("");
        this.setIconified(false);
    }

    void onCloseClicked() {
        if(TextUtils.isEmpty(this.mSearchSrcTextView.getText())) {
            if(this.mIconifiedByDefault && (this.mOnCloseListener == null || !this.mOnCloseListener.onClose())) {
                this.clearFocus();
                this.updateViewsVisibility(true);
            }
            return;
        }
        this.mSearchSrcTextView.setText("");
        this.mSearchSrcTextView.requestFocus();
        this.mSearchSrcTextView.setImeVisibility(true);
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        this.removeCallbacks(this.mUpdateDrawableStateRunnable);
        this.post(this.mReleaseCursorRunnable);
        super.onDetachedFromWindow();
    }

    // 检测为 Lambda 实现
    boolean onItemClicked(int v, int v1, String s) [...]

    boolean onItemSelected(int v) {
        if(this.mOnSuggestionListener != null && this.mOnSuggestionListener.onSuggestionSelect(v)) {
            return false;
        }
        this.rewriteQueryFromSuggestion(v);
        return true;
    }

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        if(z) {
            this.getChildBoundsWithinSearchView(this.mSearchSrcTextView, this.mSearchSrcTextViewBounds);
            this.mSearchSrtTextViewBoundsExpanded.set(this.mSearchSrcTextViewBounds.left, 0, this.mSearchSrcTextViewBounds.right, v3 - v1);
            UpdatableTouchDelegate searchView$UpdatableTouchDelegate0 = this.mTouchDelegate;
            if(searchView$UpdatableTouchDelegate0 == null) {
                UpdatableTouchDelegate searchView$UpdatableTouchDelegate1 = new UpdatableTouchDelegate(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds, this.mSearchSrcTextView);
                this.mTouchDelegate = searchView$UpdatableTouchDelegate1;
                this.setTouchDelegate(searchView$UpdatableTouchDelegate1);
                return;
            }
            searchView$UpdatableTouchDelegate0.setBounds(this.mSearchSrtTextViewBoundsExpanded, this.mSearchSrcTextViewBounds);
        }
    }

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    protected void onMeasure(int v, int v1) {
        if(this.isIconified()) {
            super.onMeasure(v, v1);
            return;
        }
        int v2 = View.MeasureSpec.getMode(v);
        int v3 = View.MeasureSpec.getSize(v);
        switch(v2) {
            case 0x80000000: {
                int v4 = this.mMaxWidth;
                v3 = v4 <= 0 ? Math.min(this.getPreferredWidth(), v3) : Math.min(v4, v3);
                break;
            }
            case 0: {
                v3 = this.mMaxWidth;
                if(v3 <= 0) {
                    v3 = this.getPreferredWidth();
                }
                break;
            }
            case 0x40000000: {
                int v5 = this.mMaxWidth;
                if(v5 > 0) {
                    v3 = Math.min(v5, v3);
                }
            }
        }
        int v6 = View.MeasureSpec.getMode(v1);
        int v7 = View.MeasureSpec.getSize(v1);
        switch(v6) {
            case 0x80000000: {
                v7 = Math.min(this.getPreferredHeight(), v7);
                break;
            }
            case 0: {
                v7 = this.getPreferredHeight();
            }
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(v3, 0x40000000), View.MeasureSpec.makeMeasureSpec(v7, 0x40000000));
    }

    protected void onQueryRefine(CharSequence charSequence0) {
        this.setQuery(charSequence0);
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.updateViewsVisibility(((SavedState)parcelable0).isIconified);
        this.requestLayout();
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.isIconified = this.isIconified();
        return parcelable0;
    }

    void onSearchClicked() {
        this.updateViewsVisibility(false);
        this.mSearchSrcTextView.requestFocus();
        this.mSearchSrcTextView.setImeVisibility(true);
        View.OnClickListener view$OnClickListener0 = this.mOnSearchClickListener;
        if(view$OnClickListener0 != null) {
            view$OnClickListener0.onClick(this);
        }
    }

    void onSubmitQuery() {
        Editable editable0 = this.mSearchSrcTextView.getText();
        if(editable0 != null && TextUtils.getTrimmedLength(editable0) > 0 && (this.mOnQueryChangeListener == null || !this.mOnQueryChangeListener.onQueryTextSubmit(editable0.toString()))) {
            if(this.mSearchable != null) {
                this.launchQuerySearch(0, null, editable0.toString());
            }
            this.mSearchSrcTextView.setImeVisibility(false);
            this.dismissSuggestions();
        }
    }

    boolean onSuggestionsKey(View view0, int v, KeyEvent keyEvent0) {
        if(this.mSearchable == null) {
            return false;
        }
        if(this.mSuggestionsAdapter == null) {
            return false;
        }
        if(keyEvent0.getAction() == 0 && keyEvent0.hasNoModifiers()) {
            switch(v) {
                case 19: {
                    this.mSearchSrcTextView.getListSelection();
                    return false;
                }
                case 21: 
                case 22: {
                    int v1 = v == 21 ? 0 : this.mSearchSrcTextView.length();
                    this.mSearchSrcTextView.setSelection(v1);
                    this.mSearchSrcTextView.setListSelection(0);
                    this.mSearchSrcTextView.clearListSelection();
                    this.mSearchSrcTextView.ensureImeVisible();
                    return true;
                }
                case 61: 
                case 66: 
                case 84: {
                    return this.onItemClicked(this.mSearchSrcTextView.getListSelection(), 0, null);
                }
                default: {
                    return false;
                }
            }
        }
        return false;
    }

    void onTextChanged(CharSequence charSequence0) {
        Editable editable0 = this.mSearchSrcTextView.getText();
        this.mUserQuery = editable0;
        boolean z = TextUtils.isEmpty(editable0);
        this.updateSubmitButton(!z);
        this.updateVoiceButton(z);
        this.updateCloseButton();
        this.updateSubmitArea();
        if(this.mOnQueryChangeListener != null && !TextUtils.equals(charSequence0, this.mOldQueryText)) {
            this.mOnQueryChangeListener.onQueryTextChange(charSequence0.toString());
        }
        this.mOldQueryText = charSequence0.toString();
    }

    void onTextFocusChanged() {
        this.updateViewsVisibility(this.isIconified());
        this.postUpdateFocusedState();
        if(this.mSearchSrcTextView.hasFocus()) {
            this.forceSuggestionQuery();
        }
    }

    void onVoiceClicked() {
        SearchableInfo searchableInfo0 = this.mSearchable;
        if(searchableInfo0 != null) {
            try {
                if(searchableInfo0.getVoiceSearchLaunchWebSearch()) {
                    Intent intent0 = this.createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, searchableInfo0);
                    this.getContext().startActivity(intent0);
                    return;
                }
                if(searchableInfo0.getVoiceSearchLaunchRecognizer()) {
                    Intent intent1 = this.createVoiceAppSearchIntent(this.mVoiceAppSearchIntent, searchableInfo0);
                    this.getContext().startActivity(intent1);
                }
            }
            catch(ActivityNotFoundException unused_ex) {
                Log.w("SearchView", "Could not find voice search activity");
            }
        }
    }

    @Override  // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.postUpdateFocusedState();
    }

    private void postUpdateFocusedState() {
        this.post(this.mUpdateDrawableStateRunnable);
    }

    @Override  // android.view.ViewGroup
    public boolean requestFocus(int v, Rect rect0) {
        if(this.mClearingFocus) {
            return false;
        }
        if(!this.isFocusable()) {
            return false;
        }
        if(!this.isIconified()) {
            boolean z = this.mSearchSrcTextView.requestFocus(v, rect0);
            if(z) {
                this.updateViewsVisibility(false);
            }
            return z;
        }
        return super.requestFocus(v, rect0);
    }

    private void rewriteQueryFromSuggestion(int v) {
        Editable editable0 = this.mSearchSrcTextView.getText();
        Cursor cursor0 = this.mSuggestionsAdapter.getCursor();
        if(cursor0 == null) {
            return;
        }
        if(cursor0.moveToPosition(v)) {
            CharSequence charSequence0 = this.mSuggestionsAdapter.convertToString(cursor0);
            if(charSequence0 != null) {
                this.setQuery(charSequence0);
                return;
            }
            this.setQuery(editable0);
            return;
        }
        this.setQuery(editable0);
    }

    public void setAppSearchData(Bundle bundle0) {
        this.mAppSearchData = bundle0;
    }

    public void setIconified(boolean z) {
        if(z) {
            this.onCloseClicked();
            return;
        }
        this.onSearchClicked();
    }

    public void setIconifiedByDefault(boolean z) {
        if(this.mIconifiedByDefault == z) {
            return;
        }
        this.mIconifiedByDefault = z;
        this.updateViewsVisibility(z);
        this.updateQueryHint();
    }

    public void setImeOptions(int v) {
        this.mSearchSrcTextView.setImeOptions(v);
    }

    public void setInputType(int v) {
        this.mSearchSrcTextView.setInputType(v);
    }

    public void setMaxWidth(int v) {
        this.mMaxWidth = v;
        this.requestLayout();
    }

    public void setOnCloseListener(OnCloseListener searchView$OnCloseListener0) {
        this.mOnCloseListener = searchView$OnCloseListener0;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener view$OnFocusChangeListener0) {
        this.mOnQueryTextFocusChangeListener = view$OnFocusChangeListener0;
    }

    public void setOnQueryTextListener(OnQueryTextListener searchView$OnQueryTextListener0) {
        this.mOnQueryChangeListener = searchView$OnQueryTextListener0;
    }

    public void setOnSearchClickListener(View.OnClickListener view$OnClickListener0) {
        this.mOnSearchClickListener = view$OnClickListener0;
    }

    public void setOnSuggestionListener(OnSuggestionListener searchView$OnSuggestionListener0) {
        this.mOnSuggestionListener = searchView$OnSuggestionListener0;
    }

    private void setQuery(CharSequence charSequence0) {
        this.mSearchSrcTextView.setText(charSequence0);
        int v = TextUtils.isEmpty(charSequence0) ? 0 : charSequence0.length();
        this.mSearchSrcTextView.setSelection(v);
    }

    public void setQuery(CharSequence charSequence0, boolean z) {
        this.mSearchSrcTextView.setText(charSequence0);
        if(charSequence0 != null) {
            int v = this.mSearchSrcTextView.length();
            this.mSearchSrcTextView.setSelection(v);
            this.mUserQuery = charSequence0;
        }
        if(z && !TextUtils.isEmpty(charSequence0)) {
            this.onSubmitQuery();
        }
    }

    public void setQueryHint(CharSequence charSequence0) {
        this.mQueryHint = charSequence0;
        this.updateQueryHint();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.mQueryRefinement = z;
        CursorAdapter cursorAdapter0 = this.mSuggestionsAdapter;
        if(cursorAdapter0 instanceof SuggestionsAdapter) {
            ((SuggestionsAdapter)cursorAdapter0).setQueryRefinement((z ? 2 : 1));
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo0) {
        this.mSearchable = searchableInfo0;
        if(searchableInfo0 != null) {
            this.updateSearchAutoComplete();
            this.updateQueryHint();
        }
        boolean z = this.hasVoiceSearch();
        this.mVoiceButtonEnabled = z;
        if(z) {
            this.mSearchSrcTextView.setPrivateImeOptions("nm");
        }
        this.updateViewsVisibility(this.isIconified());
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.mSubmitButtonEnabled = z;
        this.updateViewsVisibility(this.isIconified());
    }

    public void setSuggestionsAdapter(CursorAdapter cursorAdapter0) {
        this.mSuggestionsAdapter = cursorAdapter0;
        this.mSearchSrcTextView.setAdapter(cursorAdapter0);
    }

    private void updateCloseButton() {
        boolean z = TextUtils.isEmpty(this.mSearchSrcTextView.getText());
        int v = 0;
        ImageView imageView0 = this.mCloseButton;
        if(z && (!this.mIconifiedByDefault || this.mExpandedInActionView)) {
            v = 8;
        }
        imageView0.setVisibility(v);
        Drawable drawable0 = this.mCloseButton.getDrawable();
        if(drawable0 != null) {
            drawable0.setState((z ? SearchView.EMPTY_STATE_SET : SearchView.ENABLED_STATE_SET));
        }
    }

    // 检测为 Lambda 实现
    void updateFocusedState() [...]

    private void updateQueryHint() {
        CharSequence charSequence0 = this.getQueryHint();
        SearchAutoComplete searchView$SearchAutoComplete0 = this.mSearchSrcTextView;
        if(charSequence0 == null) {
            charSequence0 = "";
        }
        searchView$SearchAutoComplete0.setHint(this.getDecoratedHint(charSequence0));
    }

    private void updateSearchAutoComplete() {
        int v = this.mSearchable.getSuggestThreshold();
        this.mSearchSrcTextView.setThreshold(v);
        int v1 = this.mSearchable.getImeOptions();
        this.mSearchSrcTextView.setImeOptions(v1);
        int v2 = this.mSearchable.getInputType();
        int v3 = 1;
        if((v2 & 15) == 1) {
            v2 &= 0xFFFEFFFF;
            if(this.mSearchable.getSuggestAuthority() != null) {
                v2 |= 0x90000;
            }
        }
        this.mSearchSrcTextView.setInputType(v2);
        CursorAdapter cursorAdapter0 = this.mSuggestionsAdapter;
        if(cursorAdapter0 != null) {
            cursorAdapter0.changeCursor(null);
        }
        if(this.mSearchable.getSuggestAuthority() != null) {
            SuggestionsAdapter suggestionsAdapter0 = new SuggestionsAdapter(this.getContext(), this, this.mSearchable, this.mOutsideDrawablesCache);
            this.mSuggestionsAdapter = suggestionsAdapter0;
            this.mSearchSrcTextView.setAdapter(suggestionsAdapter0);
            SuggestionsAdapter suggestionsAdapter1 = (SuggestionsAdapter)this.mSuggestionsAdapter;
            if(this.mQueryRefinement) {
                v3 = 2;
            }
            suggestionsAdapter1.setQueryRefinement(v3);
        }
    }

    private void updateSubmitArea() {
        int v = !this.isSubmitAreaEnabled() || this.mGoButton.getVisibility() != 0 && this.mVoiceButton.getVisibility() != 0 ? 8 : 0;
        this.mSubmitArea.setVisibility(v);
    }

    private void updateSubmitButton(boolean z) {
        int v = !this.mSubmitButtonEnabled || !this.isSubmitAreaEnabled() || !this.hasFocus() || !z && this.mVoiceButtonEnabled ? 8 : 0;
        this.mGoButton.setVisibility(v);
    }

    private void updateViewsVisibility(boolean z) {
        this.mIconified = z;
        int v = 0;
        boolean z1 = TextUtils.isEmpty(this.mSearchSrcTextView.getText());
        this.mSearchButton.setVisibility((z ? 0 : 8));
        this.updateSubmitButton(!z1);
        this.mSearchEditFrame.setVisibility((z ? 8 : 0));
        if(this.mCollapsedIcon.getDrawable() == null || this.mIconifiedByDefault) {
            v = 8;
        }
        this.mCollapsedIcon.setVisibility(v);
        this.updateCloseButton();
        this.updateVoiceButton(z1);
        this.updateSubmitArea();
    }

    private void updateVoiceButton(boolean z) {
        int v = 8;
        if(this.mVoiceButtonEnabled && !this.isIconified() && z) {
            this.mGoButton.setVisibility(8);
            v = 0;
        }
        this.mVoiceButton.setVisibility(v);
    }

    class androidx.appcompat.widget.SearchView.1 implements Runnable {
        @Override
        public void run() {
            SearchView.this.updateFocusedState();
        }
    }


    class androidx.appcompat.widget.SearchView.8 implements AdapterView.OnItemClickListener {
        @Override  // android.widget.AdapterView$OnItemClickListener
        public void onItemClick(AdapterView adapterView0, View view0, int v, long v1) {
            SearchView.this.onItemClicked(v, 0, null);
        }
    }

}

