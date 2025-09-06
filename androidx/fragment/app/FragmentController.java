package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Preconditions;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FragmentController {
    private final FragmentHostCallback mHost;

    private FragmentController(FragmentHostCallback fragmentHostCallback0) {
        this.mHost = fragmentHostCallback0;
    }

    public void attachHost(Fragment fragment0) {
        this.mHost.mFragmentManager.attachController(this.mHost, this.mHost, fragment0);
    }

    public static FragmentController createController(FragmentHostCallback fragmentHostCallback0) {
        return new FragmentController(((FragmentHostCallback)Preconditions.checkNotNull(fragmentHostCallback0, "callbacks == null")));
    }

    public void dispatchActivityCreated() {
        this.mHost.mFragmentManager.dispatchActivityCreated();
    }

    @Deprecated
    public void dispatchConfigurationChanged(Configuration configuration0) {
        this.mHost.mFragmentManager.dispatchConfigurationChanged(configuration0, true);
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem0) {
        return this.mHost.mFragmentManager.dispatchContextItemSelected(menuItem0);
    }

    public void dispatchCreate() {
        this.mHost.mFragmentManager.dispatchCreate();
    }

    @Deprecated
    public boolean dispatchCreateOptionsMenu(Menu menu0, MenuInflater menuInflater0) {
        return this.mHost.mFragmentManager.dispatchCreateOptionsMenu(menu0, menuInflater0);
    }

    public void dispatchDestroy() {
        this.mHost.mFragmentManager.dispatchDestroy();
    }

    public void dispatchDestroyView() {
        this.mHost.mFragmentManager.dispatchDestroyView();
    }

    @Deprecated
    public void dispatchLowMemory() {
        this.mHost.mFragmentManager.dispatchLowMemory(true);
    }

    @Deprecated
    public void dispatchMultiWindowModeChanged(boolean z) {
        this.mHost.mFragmentManager.dispatchMultiWindowModeChanged(z, true);
    }

    @Deprecated
    public boolean dispatchOptionsItemSelected(MenuItem menuItem0) {
        return this.mHost.mFragmentManager.dispatchOptionsItemSelected(menuItem0);
    }

    @Deprecated
    public void dispatchOptionsMenuClosed(Menu menu0) {
        this.mHost.mFragmentManager.dispatchOptionsMenuClosed(menu0);
    }

    public void dispatchPause() {
        this.mHost.mFragmentManager.dispatchPause();
    }

    @Deprecated
    public void dispatchPictureInPictureModeChanged(boolean z) {
        this.mHost.mFragmentManager.dispatchPictureInPictureModeChanged(z, true);
    }

    @Deprecated
    public boolean dispatchPrepareOptionsMenu(Menu menu0) {
        return this.mHost.mFragmentManager.dispatchPrepareOptionsMenu(menu0);
    }

    @Deprecated
    public void dispatchReallyStop() {
    }

    public void dispatchResume() {
        this.mHost.mFragmentManager.dispatchResume();
    }

    public void dispatchStart() {
        this.mHost.mFragmentManager.dispatchStart();
    }

    public void dispatchStop() {
        this.mHost.mFragmentManager.dispatchStop();
    }

    @Deprecated
    public void doLoaderDestroy() {
    }

    @Deprecated
    public void doLoaderRetain() {
    }

    @Deprecated
    public void doLoaderStart() {
    }

    @Deprecated
    public void doLoaderStop(boolean z) {
    }

    @Deprecated
    public void dumpLoaders(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
    }

    public boolean execPendingActions() {
        return this.mHost.mFragmentManager.execPendingActions(true);
    }

    public Fragment findFragmentByWho(String s) {
        return this.mHost.mFragmentManager.findFragmentByWho(s);
    }

    public List getActiveFragments(List list0) {
        return this.mHost.mFragmentManager.getActiveFragments();
    }

    public int getActiveFragmentsCount() {
        return this.mHost.mFragmentManager.getActiveFragmentCount();
    }

    public FragmentManager getSupportFragmentManager() {
        return this.mHost.mFragmentManager;
    }

    @Deprecated
    public LoaderManager getSupportLoaderManager() {
        throw new UnsupportedOperationException("Loaders are managed separately from FragmentController, use LoaderManager.getInstance() to obtain a LoaderManager.");
    }

    public void noteStateNotSaved() {
        this.mHost.mFragmentManager.noteStateNotSaved();
    }

    public View onCreateView(View view0, String s, Context context0, AttributeSet attributeSet0) {
        return this.mHost.mFragmentManager.getLayoutInflaterFactory().onCreateView(view0, s, context0, attributeSet0);
    }

    @Deprecated
    public void reportLoaderStart() {
    }

    @Deprecated
    public void restoreAllState(Parcelable parcelable0, FragmentManagerNonConfig fragmentManagerNonConfig0) {
        this.mHost.mFragmentManager.restoreAllState(parcelable0, fragmentManagerNonConfig0);
    }

    @Deprecated
    public void restoreAllState(Parcelable parcelable0, List list0) {
        FragmentManagerNonConfig fragmentManagerNonConfig0 = new FragmentManagerNonConfig(list0, null, null);
        this.mHost.mFragmentManager.restoreAllState(parcelable0, fragmentManagerNonConfig0);
    }

    @Deprecated
    public void restoreLoaderNonConfig(SimpleArrayMap simpleArrayMap0) {
    }

    @Deprecated
    public void restoreSaveState(Parcelable parcelable0) {
        FragmentHostCallback fragmentHostCallback0 = this.mHost;
        if(!(fragmentHostCallback0 instanceof ViewModelStoreOwner)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you\'re still using retainNestedNonConfig().");
        }
        fragmentHostCallback0.mFragmentManager.restoreSaveState(parcelable0);
    }

    @Deprecated
    public SimpleArrayMap retainLoaderNonConfig() {
        return null;
    }

    @Deprecated
    public FragmentManagerNonConfig retainNestedNonConfig() {
        return this.mHost.mFragmentManager.retainNonConfig();
    }

    @Deprecated
    public List retainNonConfig() {
        FragmentManagerNonConfig fragmentManagerNonConfig0 = this.mHost.mFragmentManager.retainNonConfig();
        return fragmentManagerNonConfig0 != null && fragmentManagerNonConfig0.getFragments() != null ? new ArrayList(fragmentManagerNonConfig0.getFragments()) : null;
    }

    @Deprecated
    public Parcelable saveAllState() {
        return this.mHost.mFragmentManager.saveAllState();
    }
}

