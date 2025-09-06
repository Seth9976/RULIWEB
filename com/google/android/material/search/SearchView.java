package com.google.android.material.search;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.BackEventCompat;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar.OnMenuItemClickListener;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.internal.FadeThroughDrawable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.internal.ViewUtils.RelativePadding;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.motion.MaterialMainContainerBackHelper;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public class SearchView extends FrameLayout implements AttachedBehavior, MaterialBackHandler {
    public static class Behavior extends androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior {
        public Behavior() {
        }

        public Behavior(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
        }

        @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout0, View view0, View view1) {
            return this.onDependentViewChanged(coordinatorLayout0, ((SearchView)view0), view1);
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout0, SearchView searchView0, View view0) {
            if(!searchView0.isSetupWithSearchBar() && view0 instanceof SearchBar) {
                searchView0.setupWithSearchBar(((SearchBar)view0));
            }
            return false;
        }
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        String text;
        int visibility;

        static {
            SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0);
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

        public SavedState(Parcel parcel0) {
            this(parcel0, null);
        }

        public SavedState(Parcel parcel0, ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            this.text = parcel0.readString();
            this.visibility = parcel0.readInt();
        }

        public SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeString(this.text);
            parcel0.writeInt(this.visibility);
        }
    }

    public interface TransitionListener {
        void onStateChanged(SearchView arg1, TransitionState arg2, TransitionState arg3);
    }

    public static enum TransitionState {
        HIDING,
        HIDDEN,
        SHOWING,
        SHOWN;

    }

    private static final int DEF_STYLE_RES = 0;
    private static final long TALKBACK_FOCUS_CHANGE_DELAY_MS = 100L;
    private boolean animatedMenuItems;
    private boolean animatedNavigationIcon;
    private boolean autoShowKeyboard;
    private final boolean backHandlingEnabled;
    private final MaterialBackOrchestrator backOrchestrator;
    private final int backgroundColor;
    final View backgroundView;
    private Map childImportantForAccessibilityMap;
    final ImageButton clearButton;
    final TouchObserverFrameLayout contentContainer;
    private TransitionState currentTransitionState;
    final View divider;
    final Toolbar dummyToolbar;
    final EditText editText;
    private final ElevationOverlayProvider elevationOverlayProvider;
    final FrameLayout headerContainer;
    private final boolean layoutInflated;
    final ClippableRoundedCornerLayout rootView;
    final View scrim;
    private SearchBar searchBar;
    final TextView searchPrefix;
    private final SearchViewAnimationHelper searchViewAnimationHelper;
    private int softInputMode;
    final View statusBarSpacer;
    private boolean statusBarSpacerEnabledOverride;
    final MaterialToolbar toolbar;
    final FrameLayout toolbarContainer;
    private final Set transitionListeners;
    private boolean useWindowInsetsController;

    static {
        SearchView.DEF_STYLE_RES = style.Widget_Material3_SearchView;
    }

    public SearchView(Context context0) {
        this(context0, null);
    }

    public SearchView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.materialSearchViewStyle);
    }

    public SearchView(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, SearchView.DEF_STYLE_RES), attributeSet0, v);
        this.backOrchestrator = new MaterialBackOrchestrator(this);
        this.transitionListeners = new LinkedHashSet();
        this.softInputMode = 16;
        this.currentTransitionState = TransitionState.HIDDEN;
        Context context1 = this.getContext();
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.SearchView, v, SearchView.DEF_STYLE_RES, new int[0]);
        this.backgroundColor = typedArray0.getColor(styleable.SearchView_backgroundTint, 0);
        int v1 = typedArray0.getResourceId(styleable.SearchView_headerLayout, -1);
        int v2 = typedArray0.getResourceId(styleable.SearchView_android_textAppearance, -1);
        String s = typedArray0.getString(styleable.SearchView_android_text);
        String s1 = typedArray0.getString(styleable.SearchView_android_hint);
        String s2 = typedArray0.getString(styleable.SearchView_searchPrefixText);
        boolean z = typedArray0.getBoolean(styleable.SearchView_useDrawerArrowDrawable, false);
        this.animatedNavigationIcon = typedArray0.getBoolean(styleable.SearchView_animateNavigationIcon, true);
        this.animatedMenuItems = typedArray0.getBoolean(styleable.SearchView_animateMenuItems, true);
        boolean z1 = typedArray0.getBoolean(styleable.SearchView_hideNavigationIcon, false);
        this.autoShowKeyboard = typedArray0.getBoolean(styleable.SearchView_autoShowKeyboard, true);
        this.backHandlingEnabled = typedArray0.getBoolean(styleable.SearchView_backHandlingEnabled, true);
        typedArray0.recycle();
        LayoutInflater.from(context1).inflate(layout.mtrl_search_view, this);
        this.layoutInflated = true;
        this.scrim = this.findViewById(id.open_search_view_scrim);
        this.rootView = (ClippableRoundedCornerLayout)this.findViewById(id.open_search_view_root);
        this.backgroundView = this.findViewById(id.open_search_view_background);
        this.statusBarSpacer = this.findViewById(id.open_search_view_status_bar_spacer);
        this.headerContainer = (FrameLayout)this.findViewById(id.open_search_view_header_container);
        this.toolbarContainer = (FrameLayout)this.findViewById(id.open_search_view_toolbar_container);
        this.toolbar = (MaterialToolbar)this.findViewById(id.open_search_view_toolbar);
        this.dummyToolbar = (Toolbar)this.findViewById(id.open_search_view_dummy_toolbar);
        this.searchPrefix = (TextView)this.findViewById(id.open_search_view_search_prefix);
        this.editText = (EditText)this.findViewById(id.open_search_view_edit_text);
        this.clearButton = (ImageButton)this.findViewById(id.open_search_view_clear_button);
        this.divider = this.findViewById(id.open_search_view_divider);
        this.contentContainer = (TouchObserverFrameLayout)this.findViewById(id.open_search_view_content_container);
        this.searchViewAnimationHelper = new SearchViewAnimationHelper(this);
        this.elevationOverlayProvider = new ElevationOverlayProvider(context1);
        this.setUpRootView();
        this.setUpBackgroundViewElevationOverlay();
        this.setUpHeaderLayout(v1);
        this.setSearchPrefixText(s2);
        this.setUpEditText(v2, s, s1);
        this.setUpBackButton(z, z1);
        this.setUpClearButton();
        this.setUpContentOnTouchListener();
        this.setUpInsetListeners();
    }

    public void addHeaderView(View view0) {
        this.headerContainer.addView(view0);
        this.headerContainer.setVisibility(0);
    }

    public void addTransitionListener(TransitionListener searchView$TransitionListener0) {
        this.transitionListeners.add(searchView$TransitionListener0);
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(this.layoutInflated) {
            this.contentContainer.addView(view0, v, viewGroup$LayoutParams0);
            return;
        }
        super.addView(view0, v, viewGroup$LayoutParams0);
    }

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void cancelBackProgress() {
        if(!this.isHiddenOrHiding() && this.searchBar != null && Build.VERSION.SDK_INT >= 34) {
            this.searchViewAnimationHelper.cancelBackProgress();
        }
    }

    public void clearFocusAndHideKeyboard() {
        SearchView..ExternalSyntheticLambda3 searchView$$ExternalSyntheticLambda30 = () -> {
            this.editText.clearFocus();
            SearchBar searchBar0 = this.searchBar;
            if(searchBar0 != null) {
                searchBar0.requestFocus();
            }
            ViewUtils.hideKeyboard(this.editText, this.useWindowInsetsController);
        };
        this.editText.post(searchView$$ExternalSyntheticLambda30);
    }

    public void clearText() {
        this.editText.setText("");
    }

    private Window getActivityWindow() {
        Activity activity0 = ContextUtils.getActivity(this.getContext());
        return activity0 == null ? null : activity0.getWindow();
    }

    MaterialMainContainerBackHelper getBackHelper() {
        return this.searchViewAnimationHelper.getBackHelper();
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$AttachedBehavior
    public androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior getBehavior() {
        return new Behavior();
    }

    public TransitionState getCurrentTransitionState() {
        return this.currentTransitionState;
    }

    protected int getDefaultNavigationIconResource() [...] // 潜在的解密器

    public EditText getEditText() {
        return this.editText;
    }

    public CharSequence getHint() {
        return this.editText.getHint();
    }

    private float getOverlayElevation() {
        SearchBar searchBar0 = this.searchBar;
        return searchBar0 == null ? this.getResources().getDimension(dimen.m3_searchview_elevation) : searchBar0.getCompatElevation();
    }

    public TextView getSearchPrefix() {
        return this.searchPrefix;
    }

    public CharSequence getSearchPrefixText() {
        return this.searchPrefix.getText();
    }

    public int getSoftInputMode() {
        return this.softInputMode;
    }

    private int getStatusBarHeight() {
        int v = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return v <= 0 ? 0 : this.getResources().getDimensionPixelSize(v);
    }

    public Editable getText() {
        return this.editText.getText();
    }

    public Toolbar getToolbar() {
        return this.toolbar;
    }

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void handleBackInvoked() {
        if(this.isHiddenOrHiding()) {
            return;
        }
        BackEventCompat backEventCompat0 = this.searchViewAnimationHelper.onHandleBackInvoked();
        if(Build.VERSION.SDK_INT >= 34 && this.searchBar != null && backEventCompat0 != null) {
            this.searchViewAnimationHelper.finishBackProgress();
            return;
        }
        this.hide();
    }

    public void hide() {
        if(!this.currentTransitionState.equals(TransitionState.HIDDEN) && !this.currentTransitionState.equals(TransitionState.HIDING)) {
            this.searchViewAnimationHelper.hide();
        }
    }

    public void inflateMenu(int v) {
        this.toolbar.inflateMenu(v);
    }

    boolean isAdjustNothingSoftInputMode() {
        return this.softInputMode == 0x30;
    }

    public boolean isAnimatedNavigationIcon() {
        return this.animatedNavigationIcon;
    }

    public boolean isAutoShowKeyboard() {
        return this.autoShowKeyboard;
    }

    // 去混淆评级： 低(20)
    private boolean isHiddenOrHiding() {
        return this.currentTransitionState.equals(TransitionState.HIDDEN) || this.currentTransitionState.equals(TransitionState.HIDING);
    }

    public boolean isMenuItemsAnimated() {
        return this.animatedMenuItems;
    }

    private boolean isNavigationIconDrawerArrowDrawable(Toolbar toolbar0) {
        return DrawableCompat.unwrap(toolbar0.getNavigationIcon()) instanceof DrawerArrowDrawable;
    }

    public boolean isSetupWithSearchBar() {
        return this.searchBar != null;
    }

    // 去混淆评级： 低(20)
    public boolean isShowing() {
        return this.currentTransitionState.equals(TransitionState.SHOWN) || this.currentTransitionState.equals(TransitionState.SHOWING);
    }

    public boolean isUseWindowInsetsController() {
        return this.useWindowInsetsController;
    }

    // 检测为 Lambda 实现
    void lambda$clearFocusAndHideKeyboard$9$com-google-android-material-search-SearchView() [...]

    // 检测为 Lambda 实现
    void lambda$requestFocusAndShowKeyboard$8$com-google-android-material-search-SearchView() [...]

    // 检测为 Lambda 实现
    void lambda$setUpBackButton$1$com-google-android-material-search-SearchView(View view0) [...]

    // 检测为 Lambda 实现
    void lambda$setUpClearButton$2$com-google-android-material-search-SearchView(View view0) [...]

    // 检测为 Lambda 实现
    boolean lambda$setUpContentOnTouchListener$3$com-google-android-material-search-SearchView(View view0, MotionEvent motionEvent0) [...]

    // 检测为 Lambda 实现
    static WindowInsetsCompat lambda$setUpDividerInsetListener$6(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0, int v, int v1, View view0, WindowInsetsCompat windowInsetsCompat0) [...]

    static boolean lambda$setUpRootView$0(View view0, MotionEvent motionEvent0) [...] // Inlined contents

    // 检测为 Lambda 实现
    WindowInsetsCompat lambda$setUpStatusBarSpacerInsetListener$5$com-google-android-material-search-SearchView(View view0, WindowInsetsCompat windowInsetsCompat0) [...]

    // 检测为 Lambda 实现
    WindowInsetsCompat lambda$setUpToolbarInsetListener$4$com-google-android-material-search-SearchView(View view0, WindowInsetsCompat windowInsetsCompat0, RelativePadding viewUtils$RelativePadding0) [...]

    // 检测为 Lambda 实现
    void lambda$setupWithSearchBar$7$com-google-android-material-search-SearchView(View view0) [...]

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    @Override  // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.updateSoftInputMode();
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.setText(((SavedState)parcelable0).text);
        this.setVisible(((SavedState)parcelable0).visibility == 0);
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        Editable editable0 = this.getText();
        parcelable0.text = editable0 == null ? null : editable0.toString();
        parcelable0.visibility = this.rootView.getVisibility();
        return parcelable0;
    }

    public void removeAllHeaderViews() {
        this.headerContainer.removeAllViews();
        this.headerContainer.setVisibility(8);
    }

    public void removeHeaderView(View view0) {
        this.headerContainer.removeView(view0);
        if(this.headerContainer.getChildCount() == 0) {
            this.headerContainer.setVisibility(8);
        }
    }

    public void removeTransitionListener(TransitionListener searchView$TransitionListener0) {
        this.transitionListeners.remove(searchView$TransitionListener0);
    }

    public void requestFocusAndShowKeyboard() {
        SearchView..ExternalSyntheticLambda6 searchView$$ExternalSyntheticLambda60 = () -> {
            if(this.editText.requestFocus()) {
                this.editText.sendAccessibilityEvent(8);
            }
            ViewUtils.showKeyboard(this.editText, this.useWindowInsetsController);
        };
        this.editText.postDelayed(searchView$$ExternalSyntheticLambda60, 100L);
    }

    void requestFocusAndShowKeyboardIfNeeded() {
        if(this.autoShowKeyboard) {
            this.requestFocusAndShowKeyboard();
        }
    }

    public void setAnimatedNavigationIcon(boolean z) {
        this.animatedNavigationIcon = z;
    }

    public void setAutoShowKeyboard(boolean z) {
        this.autoShowKeyboard = z;
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        this.setUpBackgroundViewElevationOverlay(f);
    }

    public void setHint(int v) {
        this.editText.setHint(v);
    }

    public void setHint(CharSequence charSequence0) {
        this.editText.setHint(charSequence0);
    }

    public void setMenuItemsAnimated(boolean z) {
        this.animatedMenuItems = z;
    }

    public void setModalForAccessibility(boolean z) {
        ViewGroup viewGroup0 = (ViewGroup)this.getRootView();
        if(z) {
            this.childImportantForAccessibilityMap = new HashMap(viewGroup0.getChildCount());
        }
        this.updateChildImportantForAccessibility(viewGroup0, z);
        if(!z) {
            this.childImportantForAccessibilityMap = null;
        }
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener toolbar$OnMenuItemClickListener0) {
        this.toolbar.setOnMenuItemClickListener(toolbar$OnMenuItemClickListener0);
    }

    public void setSearchPrefixText(CharSequence charSequence0) {
        this.searchPrefix.setText(charSequence0);
        int v = TextUtils.isEmpty(charSequence0) ? 8 : 0;
        this.searchPrefix.setVisibility(v);
    }

    public void setStatusBarSpacerEnabled(boolean z) {
        this.statusBarSpacerEnabledOverride = true;
        this.setStatusBarSpacerEnabledInternal(z);
    }

    private void setStatusBarSpacerEnabledInternal(boolean z) {
        this.statusBarSpacer.setVisibility((z ? 0 : 8));
    }

    public void setText(int v) {
        this.editText.setText(v);
    }

    public void setText(CharSequence charSequence0) {
        this.editText.setText(charSequence0);
    }

    public void setToolbarTouchscreenBlocksFocus(boolean z) {
        this.toolbar.setTouchscreenBlocksFocus(z);
    }

    private void setTransitionState(TransitionState searchView$TransitionState0, boolean z) {
        if(this.currentTransitionState.equals(searchView$TransitionState0)) {
            return;
        }
        if(z) {
            if(searchView$TransitionState0 == TransitionState.SHOWN) {
                this.setModalForAccessibility(true);
            }
            else if(searchView$TransitionState0 == TransitionState.HIDDEN) {
                this.setModalForAccessibility(false);
            }
        }
        TransitionState searchView$TransitionState1 = this.currentTransitionState;
        this.currentTransitionState = searchView$TransitionState0;
        for(Object object0: new LinkedHashSet(this.transitionListeners)) {
            ((TransitionListener)object0).onStateChanged(this, searchView$TransitionState1, searchView$TransitionState0);
        }
        this.updateListeningForBackCallbacks(searchView$TransitionState0);
    }

    void setTransitionState(TransitionState searchView$TransitionState0) {
        this.setTransitionState(searchView$TransitionState0, true);
    }

    private void setUpBackButton(boolean z, boolean z1) {
        if(z1) {
            this.toolbar.setNavigationIcon(null);
            return;
        }
        SearchView..ExternalSyntheticLambda2 searchView$$ExternalSyntheticLambda20 = (View view0) -> this.hide();
        this.toolbar.setNavigationOnClickListener(searchView$$ExternalSyntheticLambda20);
        if(z) {
            DrawerArrowDrawable drawerArrowDrawable0 = new DrawerArrowDrawable(this.getContext());
            drawerArrowDrawable0.setColor(MaterialColors.getColor(this, attr.colorOnSurface));
            this.toolbar.setNavigationIcon(drawerArrowDrawable0);
        }
    }

    private void setUpBackgroundViewElevationOverlay() {
        this.setUpBackgroundViewElevationOverlay(this.getOverlayElevation());
    }

    private void setUpBackgroundViewElevationOverlay(float f) {
        ElevationOverlayProvider elevationOverlayProvider0 = this.elevationOverlayProvider;
        if(elevationOverlayProvider0 != null && this.backgroundView != null) {
            int v = elevationOverlayProvider0.compositeOverlayIfNeeded(this.backgroundColor, f);
            this.backgroundView.setBackgroundColor(v);
        }
    }

    private void setUpClearButton() {
        SearchView..ExternalSyntheticLambda4 searchView$$ExternalSyntheticLambda40 = (View view0) -> {
            this.clearText();
            this.requestFocusAndShowKeyboardIfNeeded();
        };
        this.clearButton.setOnClickListener(searchView$$ExternalSyntheticLambda40);
        com.google.android.material.search.SearchView.1 searchView$10 = new TextWatcher() {
            @Override  // android.text.TextWatcher
            public void afterTextChanged(Editable editable0) {
            }

            @Override  // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
            }

            @Override  // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
                int v3 = charSequence0.length() <= 0 ? 8 : 0;
                SearchView.this.clearButton.setVisibility(v3);
            }
        };
        this.editText.addTextChangedListener(searchView$10);
    }

    private void setUpContentOnTouchListener() {
        SearchView..ExternalSyntheticLambda12 searchView$$ExternalSyntheticLambda120 = (View view0, MotionEvent motionEvent0) -> {
            if(this.isAdjustNothingSoftInputMode()) {
                this.clearFocusAndHideKeyboard();
            }
            return false;
        };
        this.contentContainer.setOnTouchListener(searchView$$ExternalSyntheticLambda120);
    }

    private void setUpDividerInsetListener() {
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)this.divider.getLayoutParams();
        SearchView..ExternalSyntheticLambda5 searchView$$ExternalSyntheticLambda50 = (View view0, WindowInsetsCompat windowInsetsCompat0) -> {
            viewGroup$MarginLayoutParams0.leftMargin = viewGroup$MarginLayoutParams0.leftMargin + windowInsetsCompat0.getSystemWindowInsetLeft();
            viewGroup$MarginLayoutParams0.rightMargin = viewGroup$MarginLayoutParams0.rightMargin + windowInsetsCompat0.getSystemWindowInsetRight();
            return windowInsetsCompat0;
        };
        ViewCompat.setOnApplyWindowInsetsListener(this.divider, searchView$$ExternalSyntheticLambda50);
    }

    private void setUpEditText(int v, String s, String s1) {
        if(v != -1) {
            TextViewCompat.setTextAppearance(this.editText, v);
        }
        this.editText.setText(s);
        this.editText.setHint(s1);
    }

    private void setUpHeaderLayout(int v) {
        if(v != -1) {
            this.addHeaderView(LayoutInflater.from(this.getContext()).inflate(v, this.headerContainer, false));
        }
    }

    private void setUpInsetListeners() {
        this.setUpToolbarInsetListener();
        this.setUpDividerInsetListener();
        this.setUpStatusBarSpacerInsetListener();
    }

    private void setUpRootView() {
        SearchView..ExternalSyntheticLambda11 searchView$$ExternalSyntheticLambda110 = new SearchView..ExternalSyntheticLambda11();
        this.rootView.setOnTouchListener(searchView$$ExternalSyntheticLambda110);
    }

    private void setUpStatusBarSpacer(int v) {
        if(this.statusBarSpacer.getLayoutParams().height != v) {
            this.statusBarSpacer.getLayoutParams().height = v;
            this.statusBarSpacer.requestLayout();
        }
    }

    private void setUpStatusBarSpacerInsetListener() {
        this.setUpStatusBarSpacer(this.getStatusBarHeight());
        SearchView..ExternalSyntheticLambda8 searchView$$ExternalSyntheticLambda80 = (View view0, WindowInsetsCompat windowInsetsCompat0) -> {
            int v = windowInsetsCompat0.getSystemWindowInsetTop();
            this.setUpStatusBarSpacer(v);
            if(!this.statusBarSpacerEnabledOverride) {
                this.setStatusBarSpacerEnabledInternal(v > 0);
            }
            return windowInsetsCompat0;
        };
        ViewCompat.setOnApplyWindowInsetsListener(this.statusBarSpacer, searchView$$ExternalSyntheticLambda80);
    }

    private void setUpToolbarInsetListener() {
        SearchView..ExternalSyntheticLambda7 searchView$$ExternalSyntheticLambda70 = (View view0, WindowInsetsCompat windowInsetsCompat0, RelativePadding viewUtils$RelativePadding0) -> {
            boolean z = ViewUtils.isLayoutRtl(this.toolbar);
            int v = z ? viewUtils$RelativePadding0.end : viewUtils$RelativePadding0.start;
            int v1 = z ? viewUtils$RelativePadding0.start : viewUtils$RelativePadding0.end;
            int v2 = windowInsetsCompat0.getSystemWindowInsetLeft();
            int v3 = viewUtils$RelativePadding0.top;
            int v4 = windowInsetsCompat0.getSystemWindowInsetRight();
            this.toolbar.setPadding(v + v2, v3, v1 + v4, viewUtils$RelativePadding0.bottom);
            return windowInsetsCompat0;
        };
        ViewUtils.doOnApplyWindowInsets(this.toolbar, searchView$$ExternalSyntheticLambda70);
    }

    public void setUseWindowInsetsController(boolean z) {
        this.useWindowInsetsController = z;
    }

    public void setVisible(boolean z) {
        boolean z1 = true;
        boolean z2 = this.rootView.getVisibility() == 0;
        this.rootView.setVisibility((z ? 0 : 8));
        this.updateNavigationIconProgressIfNeeded();
        TransitionState searchView$TransitionState0 = z ? TransitionState.SHOWN : TransitionState.HIDDEN;
        if(z2 == z) {
            z1 = false;
        }
        this.setTransitionState(searchView$TransitionState0, z1);
    }

    public void setupWithSearchBar(SearchBar searchBar0) {
        this.searchBar = searchBar0;
        this.searchViewAnimationHelper.setSearchBar(searchBar0);
        if(searchBar0 != null) {
            searchBar0.setOnClickListener((View view0) -> this.show());
            if(Build.VERSION.SDK_INT >= 34) {
                try {
                    LinkFollowing..ExternalSyntheticApiModelOutline0.m(searchBar0, () -> if(!this.currentTransitionState.equals(TransitionState.SHOWN) && !this.currentTransitionState.equals(TransitionState.SHOWING)) {
                        this.searchViewAnimationHelper.show();
                    });
                    LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.editText, true);
                }
                catch(LinkageError unused_ex) {
                }
            }
        }
        this.updateNavigationIconIfNeeded();
        this.setUpBackgroundViewElevationOverlay();
        this.updateListeningForBackCallbacks(this.getCurrentTransitionState());
    }

    // 检测为 Lambda 实现
    public void show() [...]

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void startBackProgress(BackEventCompat backEventCompat0) {
        if(!this.isHiddenOrHiding() && this.searchBar != null) {
            this.searchViewAnimationHelper.startBackProgress(backEventCompat0);
        }
    }

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void updateBackProgress(BackEventCompat backEventCompat0) {
        if(!this.isHiddenOrHiding() && this.searchBar != null && Build.VERSION.SDK_INT >= 34) {
            this.searchViewAnimationHelper.updateBackProgress(backEventCompat0);
        }
    }

    private void updateChildImportantForAccessibility(ViewGroup viewGroup0, boolean z) {
        for(int v = 0; v < viewGroup0.getChildCount(); ++v) {
            View view0 = viewGroup0.getChildAt(v);
            if(view0 != this) {
                if(view0.findViewById(this.rootView.getId()) != null) {
                    this.updateChildImportantForAccessibility(((ViewGroup)view0), z);
                }
                else if(z) {
                    this.childImportantForAccessibilityMap.put(view0, view0.getImportantForAccessibility());
                    ViewCompat.setImportantForAccessibility(view0, 4);
                }
                else if(this.childImportantForAccessibilityMap != null && this.childImportantForAccessibilityMap.containsKey(view0)) {
                    ViewCompat.setImportantForAccessibility(view0, ((int)(((Integer)this.childImportantForAccessibilityMap.get(view0)))));
                }
            }
        }
    }

    private void updateListeningForBackCallbacks(TransitionState searchView$TransitionState0) {
        if(this.searchBar != null && this.backHandlingEnabled) {
            if(searchView$TransitionState0.equals(TransitionState.SHOWN)) {
                this.backOrchestrator.startListeningForBackCallbacks();
                return;
            }
            if(searchView$TransitionState0.equals(TransitionState.HIDDEN)) {
                this.backOrchestrator.stopListeningForBackCallbacks();
            }
        }
    }

    private void updateNavigationIconIfNeeded() {
        if(this.toolbar == null || this.isNavigationIconDrawerArrowDrawable(this.toolbar)) {
            return;
        }
        if(this.searchBar == null) {
            this.toolbar.setNavigationIcon(0x7F0800AE);  // drawable:ic_arrow_back_black_24
            return;
        }
        Drawable drawable0 = DrawableCompat.wrap(AppCompatResources.getDrawable(this.getContext(), 0x7F0800AE).mutate());  // drawable:ic_arrow_back_black_24
        if(this.toolbar.getNavigationIconTint() != null) {
            DrawableCompat.setTint(drawable0, ((int)this.toolbar.getNavigationIconTint()));
        }
        FadeThroughDrawable fadeThroughDrawable0 = new FadeThroughDrawable(this.searchBar.getNavigationIcon(), drawable0);
        this.toolbar.setNavigationIcon(fadeThroughDrawable0);
        this.updateNavigationIconProgressIfNeeded();
    }

    private void updateNavigationIconProgressIfNeeded() {
        ImageButton imageButton0 = ToolbarUtils.getNavigationIconButton(this.toolbar);
        if(imageButton0 != null) {
            int v = this.rootView.getVisibility() == 0 ? 1 : 0;
            Drawable drawable0 = DrawableCompat.unwrap(imageButton0.getDrawable());
            if(drawable0 instanceof DrawerArrowDrawable) {
                ((DrawerArrowDrawable)drawable0).setProgress(((float)v));
            }
            if(drawable0 instanceof FadeThroughDrawable) {
                ((FadeThroughDrawable)drawable0).setProgress(((float)v));
            }
        }
    }

    public void updateSoftInputMode() {
        Window window0 = this.getActivityWindow();
        if(window0 != null) {
            this.softInputMode = window0.getAttributes().softInputMode;
        }
    }
}

