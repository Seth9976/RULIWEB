package androidx.lifecycle;

@Deprecated
class ReflectiveGenericLifecycleObserver implements LifecycleEventObserver {
    private final CallbackInfo mInfo;
    private final Object mWrapped;

    ReflectiveGenericLifecycleObserver(Object object0) {
        this.mWrapped = object0;
        this.mInfo = ClassesInfoCache.sInstance.getInfo(object0.getClass());
    }

    @Override  // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        this.mInfo.invokeCallbacks(lifecycleOwner0, lifecycle$Event0, this.mWrapped);
    }
}

