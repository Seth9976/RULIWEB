package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.os.BundleCompat;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.random.Random;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000 72\u00020\u0001:\u0003678B\u0005\u00A2\u0006\u0002\u0010\u0002J\u0018\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\n2\u0006\u0010\u0014\u001A\u00020\u0005H\u0002J#\u0010\u0015\u001A\u00020\u0016\"\u0004\b\u0000\u0010\u00172\u0006\u0010\u0018\u001A\u00020\n2\u0006\u0010\u0019\u001A\u0002H\u0017H\u0007\u00A2\u0006\u0002\u0010\u001AJ\"\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0018\u001A\u00020\n2\u0006\u0010\u001B\u001A\u00020\n2\b\u0010\u001C\u001A\u0004\u0018\u00010\u001DH\u0007J8\u0010\u001E\u001A\u00020\u0012\"\u0004\b\u0000\u0010\u00172\u0006\u0010\u0014\u001A\u00020\u00052\u0006\u0010\u001B\u001A\u00020\n2\b\u0010\u001C\u001A\u0004\u0018\u00010\u001D2\u000E\u0010\u001F\u001A\n\u0012\u0004\u0012\u0002H\u0017\u0018\u00010\u0006H\u0002J\b\u0010 \u001A\u00020\nH\u0002JG\u0010!\u001A\u00020\u0012\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u00172\u0006\u0010\u0018\u001A\u00020\n2\u0012\u0010#\u001A\u000E\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H\u00170$2\u0006\u0010%\u001A\u0002H\"2\b\u0010&\u001A\u0004\u0018\u00010\'H\'\u00A2\u0006\u0002\u0010(J\u0010\u0010)\u001A\u00020\u00122\b\u0010*\u001A\u0004\u0018\u00010\u000FJ\u000E\u0010+\u001A\u00020\u00122\u0006\u0010,\u001A\u00020\u000FJB\u0010-\u001A\b\u0012\u0004\u0012\u0002H\"0.\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u00172\u0006\u0010\u0014\u001A\u00020\u00052\u0012\u0010#\u001A\u000E\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H\u00170$2\f\u0010/\u001A\b\u0012\u0004\u0012\u0002H\u001700JJ\u0010-\u001A\b\u0012\u0004\u0012\u0002H\"0.\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u00172\u0006\u0010\u0014\u001A\u00020\u00052\u0006\u00101\u001A\u0002022\u0012\u0010#\u001A\u000E\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H\u00170$2\f\u0010/\u001A\b\u0012\u0004\u0012\u0002H\u001700J\u0010\u00103\u001A\u00020\u00122\u0006\u0010\u0014\u001A\u00020\u0005H\u0002J\u0015\u00104\u001A\u00020\u00122\u0006\u0010\u0014\u001A\u00020\u0005H\u0001\u00A2\u0006\u0002\b5R\u001E\u0010\u0003\u001A\u0012\u0012\u0004\u0012\u00020\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u0007\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\t\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\u00050\fX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001C\u0010\r\u001A\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u000FX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u0010\u001A\u000E\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u00069"}, d2 = {"Landroidx/activity/result/ActivityResultRegistry;", "", "()V", "keyToCallback", "", "", "Landroidx/activity/result/ActivityResultRegistry$CallbackAndContract;", "keyToLifecycleContainers", "Landroidx/activity/result/ActivityResultRegistry$LifecycleContainer;", "keyToRc", "", "launchedKeys", "", "parsedPendingResults", "pendingResults", "Landroid/os/Bundle;", "rcToKey", "bindRcKey", "", "rc", "key", "dispatchResult", "", "O", "requestCode", "result", "(ILjava/lang/Object;)Z", "resultCode", "data", "Landroid/content/Intent;", "doDispatch", "callbackAndContract", "generateRandomNumber", "onLaunch", "I", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "input", "options", "Landroidx/core/app/ActivityOptionsCompat;", "(ILandroidx/activity/result/contract/ActivityResultContract;Ljava/lang/Object;Landroidx/core/app/ActivityOptionsCompat;)V", "onRestoreInstanceState", "savedInstanceState", "onSaveInstanceState", "outState", "register", "Landroidx/activity/result/ActivityResultLauncher;", "callback", "Landroidx/activity/result/ActivityResultCallback;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "registerKey", "unregister", "unregister$activity_release", "CallbackAndContract", "Companion", "LifecycleContainer", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class ActivityResultRegistry {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B%\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0010\u0010\u0005\u001A\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u001B\u0010\u0005\u001A\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\f"}, d2 = {"Landroidx/activity/result/ActivityResultRegistry$CallbackAndContract;", "O", "", "callback", "Landroidx/activity/result/ActivityResultCallback;", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "(Landroidx/activity/result/ActivityResultCallback;Landroidx/activity/result/contract/ActivityResultContract;)V", "getCallback", "()Landroidx/activity/result/ActivityResultCallback;", "getContract", "()Landroidx/activity/result/contract/ActivityResultContract;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class CallbackAndContract {
        private final ActivityResultCallback callback;
        private final ActivityResultContract contract;

        public CallbackAndContract(ActivityResultCallback activityResultCallback0, ActivityResultContract activityResultContract0) {
            Intrinsics.checkNotNullParameter(activityResultCallback0, "callback");
            Intrinsics.checkNotNullParameter(activityResultContract0, "contract");
            super();
            this.callback = activityResultCallback0;
            this.contract = activityResultContract0;
        }

        public final ActivityResultCallback getCallback() {
            return this.callback;
        }

        public final ActivityResultContract getContract() {
            return this.contract;
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Landroidx/activity/result/ActivityResultRegistry$Companion;", "", "()V", "INITIAL_REQUEST_CODE_VALUE", "", "KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", "", "KEY_COMPONENT_ACTIVITY_PENDING_RESULTS", "KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", "KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", "LOG_TAG", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000E\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\tJ\u0006\u0010\r\u001A\u00020\u000BR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Landroidx/activity/result/ActivityResultRegistry$LifecycleContainer;", "", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "(Landroidx/lifecycle/Lifecycle;)V", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "observers", "", "Landroidx/lifecycle/LifecycleEventObserver;", "addObserver", "", "observer", "clearObservers", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class LifecycleContainer {
        private final Lifecycle lifecycle;
        private final List observers;

        public LifecycleContainer(Lifecycle lifecycle0) {
            Intrinsics.checkNotNullParameter(lifecycle0, "lifecycle");
            super();
            this.lifecycle = lifecycle0;
            this.observers = new ArrayList();
        }

        public final void addObserver(LifecycleEventObserver lifecycleEventObserver0) {
            Intrinsics.checkNotNullParameter(lifecycleEventObserver0, "observer");
            this.lifecycle.addObserver(lifecycleEventObserver0);
            this.observers.add(lifecycleEventObserver0);
        }

        public final void clearObservers() {
            for(Object object0: this.observers) {
                this.lifecycle.removeObserver(((LifecycleEventObserver)object0));
            }
            this.observers.clear();
        }

        public final Lifecycle getLifecycle() {
            return this.lifecycle;
        }
    }

    private static final Companion Companion = null;
    private static final int INITIAL_REQUEST_CODE_VALUE = 0x10000;
    private static final String KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS = "KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS";
    private static final String KEY_COMPONENT_ACTIVITY_PENDING_RESULTS = "KEY_COMPONENT_ACTIVITY_PENDING_RESULT";
    private static final String KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS = "KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS";
    private static final String KEY_COMPONENT_ACTIVITY_REGISTERED_RCS = "KEY_COMPONENT_ACTIVITY_REGISTERED_RCS";
    private static final String LOG_TAG = "ActivityResultRegistry";
    private final transient Map keyToCallback;
    private final Map keyToLifecycleContainers;
    private final Map keyToRc;
    private final List launchedKeys;
    private final Map parsedPendingResults;
    private final Bundle pendingResults;
    private final Map rcToKey;

    static {
        ActivityResultRegistry.Companion = new Companion(null);
    }

    public ActivityResultRegistry() {
        this.rcToKey = new LinkedHashMap();
        this.keyToRc = new LinkedHashMap();
        this.keyToLifecycleContainers = new LinkedHashMap();
        this.launchedKeys = new ArrayList();
        this.keyToCallback = new LinkedHashMap();
        this.parsedPendingResults = new LinkedHashMap();
        this.pendingResults = new Bundle();
    }

    private final void bindRcKey(int v, String s) {
        this.rcToKey.put(v, s);
        this.keyToRc.put(s, v);
    }

    public final boolean dispatchResult(int v, int v1, Intent intent0) {
        String s = (String)this.rcToKey.get(v);
        if(s == null) {
            return false;
        }
        this.doDispatch(s, v1, intent0, ((CallbackAndContract)this.keyToCallback.get(s)));
        return true;
    }

    public final boolean dispatchResult(int v, Object object0) {
        String s = (String)this.rcToKey.get(v);
        if(s == null) {
            return false;
        }
        CallbackAndContract activityResultRegistry$CallbackAndContract0 = (CallbackAndContract)this.keyToCallback.get(s);
        if((activityResultRegistry$CallbackAndContract0 == null ? null : activityResultRegistry$CallbackAndContract0.getCallback()) == null) {
            this.pendingResults.remove(s);
            this.parsedPendingResults.put(s, object0);
            return true;
        }
        ActivityResultCallback activityResultCallback0 = activityResultRegistry$CallbackAndContract0.getCallback();
        Intrinsics.checkNotNull(activityResultCallback0, "null cannot be cast to non-null type androidx.activity.result.ActivityResultCallback<O of androidx.activity.result.ActivityResultRegistry.dispatchResult>");
        if(this.launchedKeys.remove(s)) {
            activityResultCallback0.onActivityResult(object0);
        }
        return true;
    }

    private final void doDispatch(String s, int v, Intent intent0, CallbackAndContract activityResultRegistry$CallbackAndContract0) {
        if((activityResultRegistry$CallbackAndContract0 == null ? null : activityResultRegistry$CallbackAndContract0.getCallback()) != null && this.launchedKeys.contains(s)) {
            activityResultRegistry$CallbackAndContract0.getCallback().onActivityResult(activityResultRegistry$CallbackAndContract0.getContract().parseResult(v, intent0));
            this.launchedKeys.remove(s);
            return;
        }
        this.parsedPendingResults.remove(s);
        Parcelable parcelable0 = new ActivityResult(v, intent0);
        this.pendingResults.putParcelable(s, parcelable0);
    }

    private final int generateRandomNumber() {
        for(Object object0: SequencesKt.generateSequence(androidx.activity.result.ActivityResultRegistry.generateRandomNumber.1.INSTANCE)) {
            Number number0 = (Number)object0;
            if(!this.rcToKey.containsKey(number0.intValue())) {
                return number0.intValue();
            }
            if(false) {
                break;
            }
        }
        throw new NoSuchElementException("Sequence contains no element matching the predicate.");

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001A\u0004\u0018\u00010\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class androidx.activity.result.ActivityResultRegistry.generateRandomNumber.1 extends Lambda implements Function0 {
            public static final androidx.activity.result.ActivityResultRegistry.generateRandomNumber.1 INSTANCE;

            static {
                androidx.activity.result.ActivityResultRegistry.generateRandomNumber.1.INSTANCE = new androidx.activity.result.ActivityResultRegistry.generateRandomNumber.1();
            }

            androidx.activity.result.ActivityResultRegistry.generateRandomNumber.1() {
                super(0);
            }

            public final Integer invoke() {
                return (int)(Random.Default.nextInt(0x7FFF0000) + 0x10000);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        }

    }

    public abstract void onLaunch(int arg1, ActivityResultContract arg2, Object arg3, ActivityOptionsCompat arg4);

    public final void onRestoreInstanceState(Bundle bundle0) {
        if(bundle0 != null) {
            ArrayList arrayList0 = bundle0.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
            ArrayList arrayList1 = bundle0.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
            if(arrayList1 != null && arrayList0 != null) {
                ArrayList arrayList2 = bundle0.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
                if(arrayList2 != null) {
                    this.launchedKeys.addAll(arrayList2);
                }
                Bundle bundle1 = bundle0.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
                if(bundle1 != null) {
                    this.pendingResults.putAll(bundle1);
                }
                int v = arrayList1.size();
                for(int v1 = 0; v1 < v; ++v1) {
                    String s = (String)arrayList1.get(v1);
                    if(this.keyToRc.containsKey(s)) {
                        Integer integer0 = (Integer)this.keyToRc.remove(s);
                        if(!this.pendingResults.containsKey(s)) {
                            TypeIntrinsics.asMutableMap(this.rcToKey).remove(integer0);
                        }
                    }
                    Object object0 = arrayList0.get(v1);
                    Intrinsics.checkNotNullExpressionValue(object0, "rcs[i]");
                    Object object1 = arrayList1.get(v1);
                    Intrinsics.checkNotNullExpressionValue(object1, "keys[i]");
                    this.bindRcKey(((Number)object0).intValue(), ((String)object1));
                }
            }
        }
    }

    public final void onSaveInstanceState(Bundle bundle0) {
        Intrinsics.checkNotNullParameter(bundle0, "outState");
        bundle0.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList(this.keyToRc.values()));
        bundle0.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList(this.keyToRc.keySet()));
        bundle0.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList(this.launchedKeys));
        bundle0.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", new Bundle(this.pendingResults));
    }

    public final ActivityResultLauncher register(String s, ActivityResultContract activityResultContract0, ActivityResultCallback activityResultCallback0) {
        Intrinsics.checkNotNullParameter(s, "key");
        Intrinsics.checkNotNullParameter(activityResultContract0, "contract");
        Intrinsics.checkNotNullParameter(activityResultCallback0, "callback");
        this.registerKey(s);
        CallbackAndContract activityResultRegistry$CallbackAndContract0 = new CallbackAndContract(activityResultCallback0, activityResultContract0);
        this.keyToCallback.put(s, activityResultRegistry$CallbackAndContract0);
        if(this.parsedPendingResults.containsKey(s)) {
            Object object0 = this.parsedPendingResults.get(s);
            this.parsedPendingResults.remove(s);
            activityResultCallback0.onActivityResult(object0);
        }
        ActivityResult activityResult0 = (ActivityResult)BundleCompat.getParcelable(this.pendingResults, s, ActivityResult.class);
        if(activityResult0 != null) {
            this.pendingResults.remove(s);
            activityResultCallback0.onActivityResult(activityResultContract0.parseResult(activityResult0.getResultCode(), activityResult0.getData()));
        }
        return new ActivityResultLauncher() {
            @Override  // androidx.activity.result.ActivityResultLauncher
            public ActivityResultContract getContract() {
                return this.$contract;
            }

            @Override  // androidx.activity.result.ActivityResultLauncher
            public void launch(Object object0, ActivityOptionsCompat activityOptionsCompat0) {
                Object object1 = s.keyToRc.get(activityResultContract0);
                ActivityResultContract activityResultContract0 = this.$contract;
                if(object1 != null) {
                    int v = ((Number)object1).intValue();
                    s.launchedKeys.add(activityResultContract0);
                    try {
                        s.onLaunch(v, this.$contract, object0, activityOptionsCompat0);
                        return;
                    }
                    catch(Exception exception0) {
                        s.launchedKeys.remove(activityResultContract0);
                        throw exception0;
                    }
                }
                throw new IllegalStateException(("Attempting to launch an unregistered ActivityResultLauncher with contract " + activityResultContract0 + " and input " + object0 + ". You must ensure the ActivityResultLauncher is registered before calling launch().").toString());
            }

            @Override  // androidx.activity.result.ActivityResultLauncher
            public void unregister() {
                s.unregister$activity_release(activityResultContract0);
            }
        };
    }

    public final ActivityResultLauncher register(String s, LifecycleOwner lifecycleOwner0, ActivityResultContract activityResultContract0, ActivityResultCallback activityResultCallback0) {
        Intrinsics.checkNotNullParameter(s, "key");
        Intrinsics.checkNotNullParameter(lifecycleOwner0, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(activityResultContract0, "contract");
        Intrinsics.checkNotNullParameter(activityResultCallback0, "callback");
        Lifecycle lifecycle0 = lifecycleOwner0.getLifecycle();
        if(lifecycle0.getCurrentState().isAtLeast(State.STARTED)) {
            throw new IllegalStateException(("LifecycleOwner " + lifecycleOwner0 + " is attempting to register while current state is " + lifecycle0.getCurrentState() + ". LifecycleOwners must call register before they are STARTED.").toString());
        }
        this.registerKey(s);
        LifecycleContainer activityResultRegistry$LifecycleContainer0 = (LifecycleContainer)this.keyToLifecycleContainers.get(s);
        if(activityResultRegistry$LifecycleContainer0 == null) {
            activityResultRegistry$LifecycleContainer0 = new LifecycleContainer(lifecycle0);
        }
        activityResultRegistry$LifecycleContainer0.addObserver((LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) -> {
            Intrinsics.checkNotNullParameter(lifecycleOwner0, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
            if(Event.ON_START == lifecycle$Event0) {
                CallbackAndContract activityResultRegistry$CallbackAndContract0 = new CallbackAndContract(activityResultCallback0, activityResultContract0);
                this.keyToCallback.put(s, activityResultRegistry$CallbackAndContract0);
                if(this.parsedPendingResults.containsKey(s)) {
                    Object object0 = this.parsedPendingResults.get(s);
                    this.parsedPendingResults.remove(s);
                    activityResultCallback0.onActivityResult(object0);
                }
                ActivityResult activityResult0 = (ActivityResult)BundleCompat.getParcelable(this.pendingResults, s, ActivityResult.class);
                if(activityResult0 != null) {
                    this.pendingResults.remove(s);
                    activityResultCallback0.onActivityResult(activityResultContract0.parseResult(activityResult0.getResultCode(), activityResult0.getData()));
                }
            }
            else {
                if(Event.ON_STOP == lifecycle$Event0) {
                    this.keyToCallback.remove(s);
                    return;
                }
                if(Event.ON_DESTROY == lifecycle$Event0) {
                    this.unregister$activity_release(s);
                }
            }
        });
        this.keyToLifecycleContainers.put(s, activityResultRegistry$LifecycleContainer0);
        return new ActivityResultLauncher() {
            @Override  // androidx.activity.result.ActivityResultLauncher
            public ActivityResultContract getContract() {
                return this.$contract;
            }

            @Override  // androidx.activity.result.ActivityResultLauncher
            public void launch(Object object0, ActivityOptionsCompat activityOptionsCompat0) {
                Object object1 = s.keyToRc.get(activityResultContract0);
                ActivityResultContract activityResultContract0 = this.$contract;
                if(object1 != null) {
                    int v = ((Number)object1).intValue();
                    s.launchedKeys.add(activityResultContract0);
                    try {
                        s.onLaunch(v, this.$contract, object0, activityOptionsCompat0);
                        return;
                    }
                    catch(Exception exception0) {
                        s.launchedKeys.remove(activityResultContract0);
                        throw exception0;
                    }
                }
                throw new IllegalStateException(("Attempting to launch an unregistered ActivityResultLauncher with contract " + activityResultContract0 + " and input " + object0 + ". You must ensure the ActivityResultLauncher is registered before calling launch().").toString());
            }

            @Override  // androidx.activity.result.ActivityResultLauncher
            public void unregister() {
                s.unregister$activity_release(activityResultContract0);
            }
        };
    }

    // 检测为 Lambda 实现
    private static final void register$lambda$1(ActivityResultRegistry activityResultRegistry0, String s, ActivityResultCallback activityResultCallback0, ActivityResultContract activityResultContract0, LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) [...]

    private final void registerKey(String s) {
        if(((Integer)this.keyToRc.get(s)) != null) {
            return;
        }
        this.bindRcKey(this.generateRandomNumber(), s);
    }

    public final void unregister$activity_release(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        if(!this.launchedKeys.contains(s)) {
            Integer integer0 = (Integer)this.keyToRc.remove(s);
            if(integer0 != null) {
                this.rcToKey.remove(integer0);
            }
        }
        this.keyToCallback.remove(s);
        if(this.parsedPendingResults.containsKey(s)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + s + ": " + this.parsedPendingResults.get(s));
            this.parsedPendingResults.remove(s);
        }
        if(this.pendingResults.containsKey(s)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + s + ": " + ((ActivityResult)BundleCompat.getParcelable(this.pendingResults, s, ActivityResult.class)));
            this.pendingResults.remove(s);
        }
        LifecycleContainer activityResultRegistry$LifecycleContainer0 = (LifecycleContainer)this.keyToLifecycleContainers.get(s);
        if(activityResultRegistry$LifecycleContainer0 != null) {
            activityResultRegistry$LifecycleContainer0.clearObservers();
            this.keyToLifecycleContainers.remove(s);
        }
    }
}

