package androidx.preference;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View.OnLayoutChangeListener;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.ViewTreeOnBackPressedDispatcherOwner;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.FragmentManager.BackStackEntry;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.slidingpanelayout.widget.SlidingPaneLayout.LayoutParams;
import androidx.slidingpanelayout.widget.SlidingPaneLayout.PanelSlideListener;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\u00020\u0002:\u0001&B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\n\u001A\u00020\u00072\u0006\u0010\u000B\u001A\u00020\fH\u0002J\u0010\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0010H\u0017J\n\u0010\u0011\u001A\u0004\u0018\u00010\u0001H\u0016J\b\u0010\u0012\u001A\u00020\u0013H&J$\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u000B\u001A\u00020\f2\b\u0010\u0016\u001A\u0004\u0018\u00010\u00172\b\u0010\u0018\u001A\u0004\u0018\u00010\u0019H\u0017J\u0018\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u00132\u0006\u0010\u001D\u001A\u00020\u001EH\u0017J\u001A\u0010\u001F\u001A\u00020\u000E2\u0006\u0010 \u001A\u00020\u00152\b\u0010\u0018\u001A\u0004\u0018\u00010\u0019H\u0017J\u0012\u0010!\u001A\u00020\u000E2\b\u0010\u0018\u001A\u0004\u0018\u00010\u0019H\u0016J\u0012\u0010\"\u001A\u00020\u000E2\b\u0010#\u001A\u0004\u0018\u00010$H\u0002J\u0010\u0010\"\u001A\u00020\u000E2\u0006\u0010%\u001A\u00020\u001EH\u0002R\u0010\u0010\u0004\u001A\u0004\u0018\u00010\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001A\u00020\u00078F¢\u0006\u0006\u001A\u0004\b\b\u0010\t¨\u0006\'"}, d2 = {"Landroidx/preference/PreferenceHeaderFragmentCompat;", "Landroidx/fragment/app/Fragment;", "Landroidx/preference/PreferenceFragmentCompat$OnPreferenceStartFragmentCallback;", "()V", "onBackPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "slidingPaneLayout", "Landroidx/slidingpanelayout/widget/SlidingPaneLayout;", "getSlidingPaneLayout", "()Landroidx/slidingpanelayout/widget/SlidingPaneLayout;", "buildContentView", "inflater", "Landroid/view/LayoutInflater;", "onAttach", "", "context", "Landroid/content/Context;", "onCreateInitialDetailFragment", "onCreatePreferenceHeader", "Landroidx/preference/PreferenceFragmentCompat;", "onCreateView", "Landroid/view/View;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onPreferenceStartFragment", "", "caller", "pref", "Landroidx/preference/Preference;", "onViewCreated", "view", "onViewStateRestored", "openPreferenceHeader", "intent", "Landroid/content/Intent;", "header", "InnerOnBackPressedCallback", "preference_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public abstract class PreferenceHeaderFragmentCompat extends Fragment implements OnPreferenceStartFragmentCallback {
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001A\u00020\u0007H\u0016J\u0010\u0010\b\u001A\u00020\u00072\u0006\u0010\t\u001A\u00020\nH\u0016J\u0010\u0010\u000B\u001A\u00020\u00072\u0006\u0010\t\u001A\u00020\nH\u0016J\u0018\u0010\f\u001A\u00020\u00072\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\r\u001A\u00020\u000EH\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Landroidx/preference/PreferenceHeaderFragmentCompat$InnerOnBackPressedCallback;", "Landroidx/activity/OnBackPressedCallback;", "Landroidx/slidingpanelayout/widget/SlidingPaneLayout$PanelSlideListener;", "caller", "Landroidx/preference/PreferenceHeaderFragmentCompat;", "(Landroidx/preference/PreferenceHeaderFragmentCompat;)V", "handleOnBackPressed", "", "onPanelClosed", "panel", "Landroid/view/View;", "onPanelOpened", "onPanelSlide", "slideOffset", "", "preference_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    static final class InnerOnBackPressedCallback extends OnBackPressedCallback implements PanelSlideListener {
        private final PreferenceHeaderFragmentCompat caller;

        public InnerOnBackPressedCallback(PreferenceHeaderFragmentCompat preferenceHeaderFragmentCompat0) {
            Intrinsics.checkNotNullParameter(preferenceHeaderFragmentCompat0, "caller");
            super(true);
            this.caller = preferenceHeaderFragmentCompat0;
            preferenceHeaderFragmentCompat0.getSlidingPaneLayout().addPanelSlideListener(this);
        }

        @Override  // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            this.caller.getSlidingPaneLayout().closePane();
        }

        @Override  // androidx.slidingpanelayout.widget.SlidingPaneLayout$PanelSlideListener
        public void onPanelClosed(View view0) {
            Intrinsics.checkNotNullParameter(view0, "panel");
            this.setEnabled(false);
        }

        @Override  // androidx.slidingpanelayout.widget.SlidingPaneLayout$PanelSlideListener
        public void onPanelOpened(View view0) {
            Intrinsics.checkNotNullParameter(view0, "panel");
            this.setEnabled(true);
        }

        @Override  // androidx.slidingpanelayout.widget.SlidingPaneLayout$PanelSlideListener
        public void onPanelSlide(View view0, float f) {
            Intrinsics.checkNotNullParameter(view0, "panel");
        }
    }

    private OnBackPressedCallback onBackPressedCallback;

    // 检测为 Lambda 实现
    public static void $r8$lambda$UBzSjdTT_FWTCre_igcL-des9zg(PreferenceHeaderFragmentCompat preferenceHeaderFragmentCompat0) [...]

    private final SlidingPaneLayout buildContentView(LayoutInflater layoutInflater0) {
        SlidingPaneLayout slidingPaneLayout0 = new SlidingPaneLayout(layoutInflater0.getContext());
        slidingPaneLayout0.setId(id.preferences_sliding_pane_layout);
        FragmentContainerView fragmentContainerView0 = new FragmentContainerView(layoutInflater0.getContext());
        fragmentContainerView0.setId(id.preferences_header);
        LayoutParams slidingPaneLayout$LayoutParams0 = new LayoutParams(this.getResources().getDimensionPixelSize(dimen.preferences_header_width), -1);
        slidingPaneLayout$LayoutParams0.weight = (float)this.getResources().getInteger(integer.preferences_header_pane_weight);
        slidingPaneLayout0.addView(fragmentContainerView0, slidingPaneLayout$LayoutParams0);
        FragmentContainerView fragmentContainerView1 = new FragmentContainerView(layoutInflater0.getContext());
        fragmentContainerView1.setId(id.preferences_detail);
        LayoutParams slidingPaneLayout$LayoutParams1 = new LayoutParams(this.getResources().getDimensionPixelSize(dimen.preferences_detail_width), -1);
        slidingPaneLayout$LayoutParams1.weight = (float)this.getResources().getInteger(integer.preferences_detail_pane_weight);
        slidingPaneLayout0.addView(fragmentContainerView1, slidingPaneLayout$LayoutParams1);
        return slidingPaneLayout0;
    }

    public final SlidingPaneLayout getSlidingPaneLayout() {
        return (SlidingPaneLayout)this.requireView();
    }

    @Override  // androidx.fragment.app.Fragment
    public void onAttach(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        super.onAttach(context0);
        FragmentManager fragmentManager0 = this.getParentFragmentManager();
        Intrinsics.checkNotNullExpressionValue(fragmentManager0, "parentFragmentManager");
        FragmentTransaction fragmentTransaction0 = fragmentManager0.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction0, "beginTransaction()");
        fragmentTransaction0.setPrimaryNavigationFragment(this);
        fragmentTransaction0.commit();
    }

    public Fragment onCreateInitialDetailFragment() {
        Fragment fragment0 = null;
        Fragment fragment1 = this.getChildFragmentManager().findFragmentById(id.preferences_header);
        if(fragment1 == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.preference.PreferenceFragmentCompat");
        }
        if(((PreferenceFragmentCompat)fragment1).getPreferenceScreen().getPreferenceCount() <= 0) {
            return null;
        }
        int v = ((PreferenceFragmentCompat)fragment1).getPreferenceScreen().getPreferenceCount();
        int v1 = 0;
        while(v1 < v) {
            Preference preference0 = ((PreferenceFragmentCompat)fragment1).getPreferenceScreen().getPreference(v1);
            Intrinsics.checkNotNullExpressionValue(preference0, "headerFragment.preferenc…reen.getPreference(index)");
            if(preference0.getFragment() == null) {
                ++v1;
            }
            else {
                String s = preference0.getFragment();
                if(s != null) {
                    fragment0 = this.getChildFragmentManager().getFragmentFactory().instantiate(this.requireContext().getClassLoader(), s);
                }
                if(fragment0 == null) {
                    return null;
                }
                fragment0.setArguments(preference0.getExtras());
                if(true) {
                    break;
                }
            }
        }
        return fragment0;
    }

    public abstract PreferenceFragmentCompat onCreatePreferenceHeader();

    @Override  // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(layoutInflater0, "inflater");
        SlidingPaneLayout slidingPaneLayout0 = this.buildContentView(layoutInflater0);
        if(this.getChildFragmentManager().findFragmentById(id.preferences_header) == null) {
            PreferenceFragmentCompat preferenceFragmentCompat0 = this.onCreatePreferenceHeader();
            FragmentManager fragmentManager0 = this.getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(fragmentManager0, "childFragmentManager");
            FragmentTransaction fragmentTransaction0 = fragmentManager0.beginTransaction();
            Intrinsics.checkNotNullExpressionValue(fragmentTransaction0, "beginTransaction()");
            fragmentTransaction0.setReorderingAllowed(true);
            fragmentTransaction0.add(id.preferences_header, preferenceFragmentCompat0);
            fragmentTransaction0.commit();
        }
        slidingPaneLayout0.setLockMode(3);
        return slidingPaneLayout0;
    }

    @Override  // androidx.preference.PreferenceFragmentCompat$OnPreferenceStartFragmentCallback
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat preferenceFragmentCompat0, Preference preference0) {
        Intrinsics.checkNotNullParameter(preferenceFragmentCompat0, "caller");
        Intrinsics.checkNotNullParameter(preference0, "pref");
        if(preferenceFragmentCompat0.getId() == id.preferences_header) {
            this.openPreferenceHeader(preference0);
            return true;
        }
        if(preferenceFragmentCompat0.getId() == id.preferences_detail) {
            FragmentFactory fragmentFactory0 = this.getChildFragmentManager().getFragmentFactory();
            ClassLoader classLoader0 = this.requireContext().getClassLoader();
            String s = preference0.getFragment();
            Intrinsics.checkNotNull(s);
            Fragment fragment0 = fragmentFactory0.instantiate(classLoader0, s);
            Intrinsics.checkNotNullExpressionValue(fragment0, "childFragmentManager.fra….fragment!!\n            )");
            fragment0.setArguments(preference0.getExtras());
            FragmentManager fragmentManager0 = this.getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(fragmentManager0, "childFragmentManager");
            FragmentTransaction fragmentTransaction0 = fragmentManager0.beginTransaction();
            Intrinsics.checkNotNullExpressionValue(fragmentTransaction0, "beginTransaction()");
            fragmentTransaction0.setReorderingAllowed(true);
            fragmentTransaction0.replace(id.preferences_detail, fragment0);
            fragmentTransaction0.setTransition(0x1003);
            fragmentTransaction0.addToBackStack(null);
            fragmentTransaction0.commit();
            return true;
        }
        return false;
    }

    @Override  // androidx.fragment.app.Fragment
    public void onViewCreated(View view0, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(view0, "view");
        super.onViewCreated(view0, bundle0);
        this.onBackPressedCallback = new InnerOnBackPressedCallback(this);
        View view1 = this.getSlidingPaneLayout();
        if(!ViewCompat.isLaidOut(view1) || view1.isLayoutRequested()) {
            view1.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override  // android.view.View$OnLayoutChangeListener
                public void onLayoutChange(View view0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
                    Intrinsics.checkParameterIsNotNull(view0, "view");
                    view0.removeOnLayoutChangeListener(this);
                    OnBackPressedCallback onBackPressedCallback0 = PreferenceHeaderFragmentCompat.this.onBackPressedCallback;
                    Intrinsics.checkNotNull(onBackPressedCallback0);
                    onBackPressedCallback0.setEnabled(PreferenceHeaderFragmentCompat.this.getSlidingPaneLayout().isSlideable() && PreferenceHeaderFragmentCompat.this.getSlidingPaneLayout().isOpen());
                }
            });
        }
        else {
            OnBackPressedCallback onBackPressedCallback0 = this.onBackPressedCallback;
            Intrinsics.checkNotNull(onBackPressedCallback0);
            onBackPressedCallback0.setEnabled(this.getSlidingPaneLayout().isSlideable() && this.getSlidingPaneLayout().isOpen());
        }
        this.getChildFragmentManager().addOnBackStackChangedListener(() -> PreferenceHeaderFragmentCompat.onViewCreated$lambda-10(this));
        OnBackPressedDispatcherOwner onBackPressedDispatcherOwner0 = ViewTreeOnBackPressedDispatcherOwner.get(view0);
        if(onBackPressedDispatcherOwner0 != null) {
            OnBackPressedDispatcher onBackPressedDispatcher0 = onBackPressedDispatcherOwner0.getOnBackPressedDispatcher();
            if(onBackPressedDispatcher0 != null) {
                LifecycleOwner lifecycleOwner0 = this.getViewLifecycleOwner();
                OnBackPressedCallback onBackPressedCallback1 = this.onBackPressedCallback;
                Intrinsics.checkNotNull(onBackPressedCallback1);
                onBackPressedDispatcher0.addCallback(lifecycleOwner0, onBackPressedCallback1);
            }
        }
    }

    private static final void onViewCreated$lambda-10(PreferenceHeaderFragmentCompat preferenceHeaderFragmentCompat0) {
        Intrinsics.checkNotNullParameter(preferenceHeaderFragmentCompat0, "this$0");
        OnBackPressedCallback onBackPressedCallback0 = preferenceHeaderFragmentCompat0.onBackPressedCallback;
        Intrinsics.checkNotNull(onBackPressedCallback0);
        onBackPressedCallback0.setEnabled(preferenceHeaderFragmentCompat0.getChildFragmentManager().getBackStackEntryCount() == 0);
    }

    @Override  // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle0) {
        super.onViewStateRestored(bundle0);
        if(bundle0 == null) {
            Fragment fragment0 = this.onCreateInitialDetailFragment();
            if(fragment0 != null) {
                FragmentManager fragmentManager0 = this.getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(fragmentManager0, "childFragmentManager");
                FragmentTransaction fragmentTransaction0 = fragmentManager0.beginTransaction();
                Intrinsics.checkNotNullExpressionValue(fragmentTransaction0, "beginTransaction()");
                fragmentTransaction0.setReorderingAllowed(true);
                fragmentTransaction0.replace(id.preferences_detail, fragment0);
                fragmentTransaction0.commit();
            }
        }
    }

    private final void openPreferenceHeader(Intent intent0) {
        if(intent0 == null) {
            return;
        }
        this.startActivity(intent0);
    }

    private final void openPreferenceHeader(Preference preference0) {
        if(preference0.getFragment() == null) {
            this.openPreferenceHeader(preference0.getIntent());
            return;
        }
        String s = preference0.getFragment();
        Fragment fragment0 = s == null ? null : this.getChildFragmentManager().getFragmentFactory().instantiate(this.requireContext().getClassLoader(), s);
        if(fragment0 != null) {
            fragment0.setArguments(preference0.getExtras());
        }
        if(this.getChildFragmentManager().getBackStackEntryCount() > 0) {
            BackStackEntry fragmentManager$BackStackEntry0 = this.getChildFragmentManager().getBackStackEntryAt(0);
            Intrinsics.checkNotNullExpressionValue(fragmentManager$BackStackEntry0, "childFragmentManager.getBackStackEntryAt(0)");
            this.getChildFragmentManager().popBackStack(fragmentManager$BackStackEntry0.getId(), 1);
        }
        FragmentManager fragmentManager0 = this.getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(fragmentManager0, "childFragmentManager");
        FragmentTransaction fragmentTransaction0 = fragmentManager0.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction0, "beginTransaction()");
        fragmentTransaction0.setReorderingAllowed(true);
        Intrinsics.checkNotNull(fragment0);
        fragmentTransaction0.replace(id.preferences_detail, fragment0);
        if(this.getSlidingPaneLayout().isOpen()) {
            fragmentTransaction0.setTransition(0x1003);
        }
        this.getSlidingPaneLayout().openPane();
        fragmentTransaction0.commit();
    }
}

