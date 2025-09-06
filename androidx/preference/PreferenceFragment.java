package androidx.preference;

import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.res.TypedArrayUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.State;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.recyclerview.widget.RecyclerView;

@Deprecated
public abstract class PreferenceFragment extends Fragment implements TargetFragment, OnDisplayPreferenceDialogListener, OnNavigateToScreenListener, OnPreferenceTreeClickListener {
    class DividerDecoration extends ItemDecoration {
        private boolean mAllowDividerAfterLastItem;
        private Drawable mDivider;
        private int mDividerHeight;

        DividerDecoration() {
            this.mAllowDividerAfterLastItem = true;
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$ItemDecoration
        public void getItemOffsets(Rect rect0, View view0, RecyclerView recyclerView0, State recyclerView$State0) {
            if(this.shouldDrawDividerBelow(view0, recyclerView0)) {
                rect0.bottom = this.mDividerHeight;
            }
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$ItemDecoration
        public void onDrawOver(Canvas canvas0, RecyclerView recyclerView0, State recyclerView$State0) {
            if(this.mDivider != null) {
                int v = recyclerView0.getChildCount();
                int v1 = recyclerView0.getWidth();
                for(int v2 = 0; v2 < v; ++v2) {
                    View view0 = recyclerView0.getChildAt(v2);
                    if(this.shouldDrawDividerBelow(view0, recyclerView0)) {
                        int v3 = (int)view0.getY();
                        int v4 = view0.getHeight();
                        this.mDivider.setBounds(0, v3 + v4, v1, this.mDividerHeight + (v3 + v4));
                        this.mDivider.draw(canvas0);
                    }
                }
            }
        }

        public void setAllowDividerAfterLastItem(boolean z) {
            this.mAllowDividerAfterLastItem = z;
        }

        public void setDivider(Drawable drawable0) {
            this.mDividerHeight = drawable0 == null ? 0 : drawable0.getIntrinsicHeight();
            this.mDivider = drawable0;
            PreferenceFragment.this.mList.invalidateItemDecorations();
        }

        public void setDividerHeight(int v) {
            this.mDividerHeight = v;
            PreferenceFragment.this.mList.invalidateItemDecorations();
        }

        private boolean shouldDrawDividerBelow(View view0, RecyclerView recyclerView0) {
            ViewHolder recyclerView$ViewHolder0 = recyclerView0.getChildViewHolder(view0);
            if(recyclerView$ViewHolder0 instanceof PreferenceViewHolder && ((PreferenceViewHolder)recyclerView$ViewHolder0).isDividerAllowedBelow()) {
                boolean z = this.mAllowDividerAfterLastItem;
                int v = recyclerView0.indexOfChild(view0);
                if(v < recyclerView0.getChildCount() - 1) {
                    ViewHolder recyclerView$ViewHolder1 = recyclerView0.getChildViewHolder(recyclerView0.getChildAt(v + 1));
                    return recyclerView$ViewHolder1 instanceof PreferenceViewHolder && ((PreferenceViewHolder)recyclerView$ViewHolder1).isDividerAllowedAbove();
                }
                return z;
            }
            return false;
        }
    }

    public interface OnPreferenceDisplayDialogCallback {
        boolean onPreferenceDisplayDialog(PreferenceFragment arg1, Preference arg2);
    }

    public interface OnPreferenceStartFragmentCallback {
        boolean onPreferenceStartFragment(PreferenceFragment arg1, Preference arg2);
    }

    public interface OnPreferenceStartScreenCallback {
        boolean onPreferenceStartScreen(PreferenceFragment arg1, PreferenceScreen arg2);
    }

    static class ScrollToPreferenceObserver extends AdapterDataObserver {
        private final Adapter mAdapter;
        private final String mKey;
        private final RecyclerView mList;
        private final Preference mPreference;

        ScrollToPreferenceObserver(Adapter recyclerView$Adapter0, RecyclerView recyclerView0, Preference preference0, String s) {
            this.mAdapter = recyclerView$Adapter0;
            this.mList = recyclerView0;
            this.mPreference = preference0;
            this.mKey = s;
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onChanged() {
            this.scrollToPreference();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeChanged(int v, int v1) {
            this.scrollToPreference();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeChanged(int v, int v1, Object object0) {
            this.scrollToPreference();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeInserted(int v, int v1) {
            this.scrollToPreference();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeMoved(int v, int v1, int v2) {
            this.scrollToPreference();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
        public void onItemRangeRemoved(int v, int v1) {
            this.scrollToPreference();
        }

        private void scrollToPreference() {
            this.mAdapter.unregisterAdapterDataObserver(this);
            int v = this.mPreference == null ? ((PreferencePositionCallback)this.mAdapter).getPreferenceAdapterPosition(this.mKey) : ((PreferencePositionCallback)this.mAdapter).getPreferenceAdapterPosition(this.mPreference);
            if(v != -1) {
                this.mList.scrollToPosition(v);
            }
        }
    }

    @Deprecated
    public static final String ARG_PREFERENCE_ROOT = "androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT";
    private static final String DIALOG_FRAGMENT_TAG = "androidx.preference.PreferenceFragment.DIALOG";
    private static final int MSG_BIND_PREFERENCES = 1;
    private static final String PREFERENCES_TAG = "android:preferences";
    private final DividerDecoration mDividerDecoration;
    private final Handler mHandler;
    private boolean mHavePrefs;
    private boolean mInitDone;
    private int mLayoutResId;
    RecyclerView mList;
    private PreferenceManager mPreferenceManager;
    private final Runnable mRequestFocus;
    private Runnable mSelectPreferenceRunnable;
    private Context mStyledContext;

    public PreferenceFragment() {
        this.mDividerDecoration = new DividerDecoration(this);
        this.mLayoutResId = layout.preference_list_fragment;
        this.mHandler = new Handler() {
            @Override  // android.os.Handler
            public void handleMessage(Message message0) {
                if(message0.what != 1) {
                    return;
                }
                PreferenceFragment.this.bindPreferences();
            }
        };
        this.mRequestFocus = new Runnable() {
            @Override
            public void run() {
                PreferenceFragment.this.mList.focusableViewAvailable(PreferenceFragment.this.mList);
            }
        };
    }

    @Deprecated
    public void addPreferencesFromResource(int v) {
        this.requirePreferenceManager();
        this.setPreferenceScreen(this.mPreferenceManager.inflateFromResource(this.mStyledContext, v, this.getPreferenceScreen()));
    }

    void bindPreferences() {
        PreferenceScreen preferenceScreen0 = this.getPreferenceScreen();
        if(preferenceScreen0 != null) {
            this.getListView().setAdapter(this.onCreateAdapter(preferenceScreen0));
            preferenceScreen0.onAttached();
        }
    }

    @Override  // androidx.preference.DialogPreference$TargetFragment
    @Deprecated
    public Preference findPreference(CharSequence charSequence0) {
        return this.mPreferenceManager == null ? null : this.mPreferenceManager.findPreference(charSequence0);
    }

    public Fragment getCallbackFragment() [...] // Inlined contents

    @Deprecated
    public final RecyclerView getListView() {
        return this.mList;
    }

    @Deprecated
    public PreferenceManager getPreferenceManager() {
        return this.mPreferenceManager;
    }

    @Deprecated
    public PreferenceScreen getPreferenceScreen() {
        return this.mPreferenceManager.getPreferenceScreen();
    }

    protected void onBindPreferences() {
    }

    @Override  // android.app.Fragment
    public void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        TypedValue typedValue0 = new TypedValue();
        this.getActivity().getTheme().resolveAttribute(attr.preferenceTheme, typedValue0, true);
        int v = typedValue0.resourceId == 0 ? style.PreferenceThemeOverlay : typedValue0.resourceId;
        this.mStyledContext = new ContextThemeWrapper(this.getActivity(), v);
        PreferenceManager preferenceManager0 = new PreferenceManager(this.mStyledContext);
        this.mPreferenceManager = preferenceManager0;
        preferenceManager0.setOnNavigateToScreenListener(this);
        this.onCreatePreferences(bundle0, (this.getArguments() == null ? null : this.getArguments().getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT")));
    }

    @Deprecated
    protected Adapter onCreateAdapter(PreferenceScreen preferenceScreen0) {
        return new PreferenceGroupAdapter(preferenceScreen0);
    }

    @Deprecated
    public LayoutManager onCreateLayoutManager() {
        return new LinearLayoutManager(this.getActivity());
    }

    @Deprecated
    public abstract void onCreatePreferences(Bundle arg1, String arg2);

    @Deprecated
    public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0) {
        if(this.mStyledContext.getPackageManager().hasSystemFeature("android.hardware.type.automotive")) {
            RecyclerView recyclerView0 = (RecyclerView)viewGroup0.findViewById(id.recycler_view);
            if(recyclerView0 != null) {
                return recyclerView0;
            }
        }
        RecyclerView recyclerView1 = (RecyclerView)layoutInflater0.inflate(layout.preference_recyclerview, viewGroup0, false);
        recyclerView1.setLayoutManager(this.onCreateLayoutManager());
        recyclerView1.setAccessibilityDelegateCompat(new PreferenceRecyclerViewAccessibilityDelegate(recyclerView1));
        return recyclerView1;
    }

    @Override  // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0) {
        Context context0 = this.mStyledContext;
        int v = TypedArrayUtils.getAttr(this.mStyledContext, attr.preferenceFragmentStyle, 0x1010506);
        TypedArray typedArray0 = context0.obtainStyledAttributes(null, styleable.PreferenceFragment, v, 0);
        this.mLayoutResId = typedArray0.getResourceId(styleable.PreferenceFragment_android_layout, this.mLayoutResId);
        Drawable drawable0 = typedArray0.getDrawable(styleable.PreferenceFragment_android_divider);
        int v1 = typedArray0.getDimensionPixelSize(styleable.PreferenceFragment_android_dividerHeight, -1);
        boolean z = typedArray0.getBoolean(styleable.PreferenceFragment_allowDividerAfterLastItem, true);
        typedArray0.recycle();
        LayoutInflater layoutInflater1 = layoutInflater0.cloneInContext(this.mStyledContext);
        View view0 = layoutInflater1.inflate(this.mLayoutResId, viewGroup0, false);
        View view1 = view0.findViewById(0x102003F);
        if(!(view1 instanceof ViewGroup)) {
            throw new RuntimeException("Content has view with id attribute \'android.R.id.list_container\' that is not a ViewGroup class");
        }
        RecyclerView recyclerView0 = this.onCreateRecyclerView(layoutInflater1, ((ViewGroup)view1), bundle0);
        if(recyclerView0 == null) {
            throw new RuntimeException("Could not create RecyclerView");
        }
        this.mList = recyclerView0;
        recyclerView0.addItemDecoration(this.mDividerDecoration);
        this.setDivider(drawable0);
        if(v1 != -1) {
            this.setDividerHeight(v1);
        }
        this.mDividerDecoration.setAllowDividerAfterLastItem(z);
        if(this.mList.getParent() == null) {
            ((ViewGroup)view1).addView(this.mList);
        }
        this.mHandler.post(this.mRequestFocus);
        return view0;
    }

    @Override  // android.app.Fragment
    public void onDestroyView() {
        this.mHandler.removeCallbacks(this.mRequestFocus);
        this.mHandler.removeMessages(1);
        if(this.mHavePrefs) {
            this.unbindPreferences();
        }
        this.mList = null;
        super.onDestroyView();
    }

    @Override  // androidx.preference.PreferenceManager$OnDisplayPreferenceDialogListener
    @Deprecated
    public void onDisplayPreferenceDialog(Preference preference0) {
        EditTextPreferenceDialogFragment editTextPreferenceDialogFragment0;
        boolean z = false;
        if(null instanceof OnPreferenceDisplayDialogCallback) {
            throw new NullPointerException();
        }
        if(this.getActivity() instanceof OnPreferenceDisplayDialogCallback) {
            z = ((OnPreferenceDisplayDialogCallback)this.getActivity()).onPreferenceDisplayDialog(this, preference0);
        }
        if(z || this.getFragmentManager().findFragmentByTag("androidx.preference.PreferenceFragment.DIALOG") != null) {
            return;
        }
        if(preference0 instanceof EditTextPreference) {
            editTextPreferenceDialogFragment0 = EditTextPreferenceDialogFragment.newInstance(preference0.getKey());
        }
        else if(preference0 instanceof ListPreference) {
            editTextPreferenceDialogFragment0 = ListPreferenceDialogFragment.newInstance(preference0.getKey());
        }
        else if(preference0 instanceof MultiSelectListPreference) {
            editTextPreferenceDialogFragment0 = MultiSelectListPreferenceDialogFragment.newInstance(preference0.getKey());
        }
        else {
            throw new IllegalArgumentException("Tried to display dialog for unknown preference type. Did you forget to override onDisplayPreferenceDialog()?");
        }
        editTextPreferenceDialogFragment0.setTargetFragment(this, 0);
        editTextPreferenceDialogFragment0.show(this.getFragmentManager(), "androidx.preference.PreferenceFragment.DIALOG");
    }

    @Override  // androidx.preference.PreferenceManager$OnNavigateToScreenListener
    @Deprecated
    public void onNavigateToScreen(PreferenceScreen preferenceScreen0) {
        if(null instanceof OnPreferenceStartScreenCallback) {
            throw new NullPointerException();
        }
        if(this.getActivity() instanceof OnPreferenceStartScreenCallback) {
            ((OnPreferenceStartScreenCallback)this.getActivity()).onPreferenceStartScreen(this, preferenceScreen0);
        }
    }

    @Override  // androidx.preference.PreferenceManager$OnPreferenceTreeClickListener
    @Deprecated
    public boolean onPreferenceTreeClick(Preference preference0) {
        if(preference0.getFragment() != null) {
            if(null instanceof OnPreferenceStartFragmentCallback) {
                throw new NullPointerException();
            }
            return this.getActivity() instanceof OnPreferenceStartFragmentCallback ? ((OnPreferenceStartFragmentCallback)this.getActivity()).onPreferenceStartFragment(this, preference0) : false;
        }
        return false;
    }

    @Override  // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        PreferenceScreen preferenceScreen0 = this.getPreferenceScreen();
        if(preferenceScreen0 != null) {
            Bundle bundle1 = new Bundle();
            preferenceScreen0.saveHierarchyState(bundle1);
            bundle0.putBundle("android:preferences", bundle1);
        }
    }

    @Override  // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.mPreferenceManager.setOnPreferenceTreeClickListener(this);
        this.mPreferenceManager.setOnDisplayPreferenceDialogListener(this);
    }

    @Override  // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.mPreferenceManager.setOnPreferenceTreeClickListener(null);
        this.mPreferenceManager.setOnDisplayPreferenceDialogListener(null);
    }

    protected void onUnbindPreferences() {
    }

    @Override  // android.app.Fragment
    public void onViewCreated(View view0, Bundle bundle0) {
        super.onViewCreated(view0, bundle0);
        if(bundle0 != null) {
            Bundle bundle1 = bundle0.getBundle("android:preferences");
            if(bundle1 != null) {
                PreferenceScreen preferenceScreen0 = this.getPreferenceScreen();
                if(preferenceScreen0 != null) {
                    preferenceScreen0.restoreHierarchyState(bundle1);
                }
            }
        }
        if(this.mHavePrefs) {
            this.bindPreferences();
            Runnable runnable0 = this.mSelectPreferenceRunnable;
            if(runnable0 != null) {
                runnable0.run();
                this.mSelectPreferenceRunnable = null;
            }
        }
        this.mInitDone = true;
    }

    private void postBindPreferences() {
        if(this.mHandler.hasMessages(1)) {
            return;
        }
        this.mHandler.obtainMessage(1).sendToTarget();
    }

    private void requirePreferenceManager() {
        if(this.mPreferenceManager == null) {
            throw new RuntimeException("This should be called after super.onCreate.");
        }
    }

    @Deprecated
    public void scrollToPreference(Preference preference0) {
        this.scrollToPreferenceInternal(preference0, null);
    }

    @Deprecated
    public void scrollToPreference(String s) {
        this.scrollToPreferenceInternal(null, s);
    }

    private void scrollToPreferenceInternal(Preference preference0, String s) {
        androidx.preference.PreferenceFragment.3 preferenceFragment$30 = new Runnable() {
            @Override
            public void run() {
                Adapter recyclerView$Adapter0 = PreferenceFragment.this.mList.getAdapter();
                if(!(recyclerView$Adapter0 instanceof PreferencePositionCallback)) {
                    if(recyclerView$Adapter0 != null) {
                        throw new IllegalStateException("Adapter must implement PreferencePositionCallback");
                    }
                    return;
                }
                int v = preference0 == null ? ((PreferencePositionCallback)recyclerView$Adapter0).getPreferenceAdapterPosition(s) : ((PreferencePositionCallback)recyclerView$Adapter0).getPreferenceAdapterPosition(preference0);
                if(v != -1) {
                    PreferenceFragment.this.mList.scrollToPosition(v);
                    return;
                }
                recyclerView$Adapter0.registerAdapterDataObserver(new ScrollToPreferenceObserver(recyclerView$Adapter0, PreferenceFragment.this.mList, preference0, s));
            }
        };
        if(this.mList == null) {
            this.mSelectPreferenceRunnable = preferenceFragment$30;
            return;
        }
        preferenceFragment$30.run();
    }

    @Deprecated
    public void setDivider(Drawable drawable0) {
        this.mDividerDecoration.setDivider(drawable0);
    }

    @Deprecated
    public void setDividerHeight(int v) {
        this.mDividerDecoration.setDividerHeight(v);
    }

    @Deprecated
    public void setPreferenceScreen(PreferenceScreen preferenceScreen0) {
        if(this.mPreferenceManager.setPreferences(preferenceScreen0) && preferenceScreen0 != null) {
            this.mHavePrefs = true;
            if(this.mInitDone) {
                this.postBindPreferences();
            }
        }
    }

    @Deprecated
    public void setPreferencesFromResource(int v, String s) {
        this.requirePreferenceManager();
        Preference preference0 = this.mPreferenceManager.inflateFromResource(this.mStyledContext, v, null);
        if(s != null) {
            preference0 = ((PreferenceScreen)preference0).findPreference(s);
            if(!(preference0 instanceof PreferenceScreen)) {
                throw new IllegalArgumentException("Preference object with key " + s + " is not a PreferenceScreen");
            }
        }
        this.setPreferenceScreen(((PreferenceScreen)preference0));
    }

    private void unbindPreferences() {
        PreferenceScreen preferenceScreen0 = this.getPreferenceScreen();
        if(preferenceScreen0 != null) {
            preferenceScreen0.onDetached();
        }
    }
}

