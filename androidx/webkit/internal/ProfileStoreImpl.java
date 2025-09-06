package androidx.webkit.internal;

import androidx.webkit.Profile;
import androidx.webkit.ProfileStore;
import java.lang.reflect.InvocationHandler;
import java.util.List;
import org.chromium.support_lib_boundary.ProfileBoundaryInterface;
import org.chromium.support_lib_boundary.ProfileStoreBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class ProfileStoreImpl implements ProfileStore {
    private final ProfileStoreBoundaryInterface mProfileStoreImpl;
    private static ProfileStore sInstance;

    private ProfileStoreImpl() {
        this.mProfileStoreImpl = null;
    }

    private ProfileStoreImpl(ProfileStoreBoundaryInterface profileStoreBoundaryInterface0) {
        this.mProfileStoreImpl = profileStoreBoundaryInterface0;
    }

    @Override  // androidx.webkit.ProfileStore
    public boolean deleteProfile(String s) throws IllegalStateException {
        if(!WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return this.mProfileStoreImpl.deleteProfile(s);
    }

    @Override  // androidx.webkit.ProfileStore
    public List getAllProfileNames() {
        if(!WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return this.mProfileStoreImpl.getAllProfileNames();
    }

    public static ProfileStore getInstance() {
        if(ProfileStoreImpl.sInstance == null) {
            ProfileStoreImpl.sInstance = new ProfileStoreImpl(WebViewGlueCommunicator.getFactory().getProfileStore());
        }
        return ProfileStoreImpl.sInstance;
    }

    @Override  // androidx.webkit.ProfileStore
    public Profile getOrCreateProfile(String s) {
        if(!WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        InvocationHandler invocationHandler0 = this.mProfileStoreImpl.getOrCreateProfile(s);
        return new ProfileImpl(((ProfileBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(ProfileBoundaryInterface.class, invocationHandler0)));
    }

    @Override  // androidx.webkit.ProfileStore
    public Profile getProfile(String s) {
        if(!WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        InvocationHandler invocationHandler0 = this.mProfileStoreImpl.getProfile(s);
        return invocationHandler0 != null ? new ProfileImpl(((ProfileBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(ProfileBoundaryInterface.class, invocationHandler0))) : null;
    }
}

