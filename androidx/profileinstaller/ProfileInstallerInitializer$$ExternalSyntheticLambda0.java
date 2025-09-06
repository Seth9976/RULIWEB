package androidx.profileinstaller;

import android.content.Context;
import android.view.Choreographer.FrameCallback;

public final class ProfileInstallerInitializer..ExternalSyntheticLambda0 implements Choreographer.FrameCallback {
    public final ProfileInstallerInitializer f$0;
    public final Context f$1;

    public ProfileInstallerInitializer..ExternalSyntheticLambda0(ProfileInstallerInitializer profileInstallerInitializer0, Context context0) {
        this.f$0 = profileInstallerInitializer0;
        this.f$1 = context0;
    }

    @Override  // android.view.Choreographer$FrameCallback
    public final void doFrame(long v) {
        this.f$0.lambda$create$0$androidx-profileinstaller-ProfileInstallerInitializer(this.f$1, v);
    }
}

