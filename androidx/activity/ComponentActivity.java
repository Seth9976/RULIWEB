package androidx.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnDrawListener;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.contextaware.ContextAware;
import androidx.activity.contextaware.ContextAwareHelper;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract.SynchronousResult;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.app.OnNewIntentProvider;
import androidx.core.app.OnPictureInPictureModeChangedProvider;
import androidx.core.app.OnUserLeaveHintProvider;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuHostHelper;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import androidx.tracing.Trace;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u00E2\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 \u00BC\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n2\u00020\u000B2\u00020\f2\u00020\r2\u00020\u000E2\u00020\u000F2\u00020\u00102\u00020\u0011:\n\u00BB\u0001\u00BC\u0001\u00BD\u0001\u00BE\u0001\u00BF\u0001B\u0011\b\u0017\u0012\b\b\u0001\u0010\u0012\u001A\u00020\u0013\u00A2\u0006\u0002\u0010\u0014B\u0005\u00A2\u0006\u0002\u0010\u0015J\u001C\u0010[\u001A\u00020\\2\b\u0010]\u001A\u0004\u0018\u00010^2\b\u0010_\u001A\u0004\u0018\u00010`H\u0016J\u0010\u0010a\u001A\u00020\\2\u0006\u0010b\u001A\u00020cH\u0016J\u0018\u0010a\u001A\u00020\\2\u0006\u0010b\u001A\u00020c2\u0006\u0010d\u001A\u00020\u0003H\u0016J \u0010a\u001A\u00020\\2\u0006\u0010b\u001A\u00020c2\u0006\u0010d\u001A\u00020\u00032\u0006\u0010e\u001A\u00020fH\u0017J\u0010\u0010g\u001A\u00020\\2\u0006\u0010h\u001A\u00020=H\u0003J\u0014\u0010i\u001A\u00020\\2\f\u0010j\u001A\b\u0012\u0004\u0012\u00020E0DJ\u000E\u0010k\u001A\u00020\\2\u0006\u0010j\u001A\u00020lJ\u0014\u0010m\u001A\u00020\\2\f\u0010j\u001A\b\u0012\u0004\u0012\u00020G0DJ\u0014\u0010n\u001A\u00020\\2\f\u0010j\u001A\b\u0012\u0004\u0012\u00020I0DJ\u0014\u0010o\u001A\u00020\\2\f\u0010j\u001A\b\u0012\u0004\u0012\u00020K0DJ\u0014\u0010p\u001A\u00020\\2\f\u0010j\u001A\b\u0012\u0004\u0012\u00020\u00130DJ\u000E\u0010q\u001A\u00020\\2\u0006\u0010j\u001A\u00020NJ\b\u0010r\u001A\u00020PH\u0002J\b\u0010s\u001A\u00020\\H\u0002J\b\u0010t\u001A\u00020\\H\u0017J\b\u0010u\u001A\u00020\\H\u0016J\"\u0010v\u001A\u00020\\2\u0006\u0010w\u001A\u00020\u00132\u0006\u0010x\u001A\u00020\u00132\b\u0010y\u001A\u0004\u0018\u00010IH\u0015J\b\u0010z\u001A\u00020\\H\u0017J\u0010\u0010{\u001A\u00020\\2\u0006\u0010|\u001A\u00020EH\u0017J\u0012\u0010}\u001A\u00020\\2\b\u0010~\u001A\u0004\u0018\u00010\u007FH\u0014J\u001C\u0010\u0080\u0001\u001A\u00020)2\u0007\u0010\u0081\u0001\u001A\u00020\u00132\b\u0010\u0082\u0001\u001A\u00030\u0083\u0001H\u0016J\u001C\u0010\u0084\u0001\u001A\u00020)2\u0007\u0010\u0081\u0001\u001A\u00020\u00132\b\u0010\u0085\u0001\u001A\u00030\u0086\u0001H\u0016J\u0012\u0010\u0087\u0001\u001A\u00020\\2\u0007\u0010\u0088\u0001\u001A\u00020)H\u0017J\u001A\u0010\u0087\u0001\u001A\u00020\\2\u0007\u0010\u0088\u0001\u001A\u00020)2\u0006\u0010|\u001A\u00020EH\u0017J\u0012\u0010\u0089\u0001\u001A\u00020\\2\u0007\u0010\u008A\u0001\u001A\u00020IH\u0015J\u001C\u0010\u008B\u0001\u001A\u00020\\2\u0007\u0010\u0081\u0001\u001A\u00020\u00132\b\u0010\u0082\u0001\u001A\u00030\u0083\u0001H\u0016J\u0012\u0010\u008C\u0001\u001A\u00020\\2\u0007\u0010\u008D\u0001\u001A\u00020)H\u0017J\u001A\u0010\u008C\u0001\u001A\u00020\\2\u0007\u0010\u008D\u0001\u001A\u00020)2\u0006\u0010|\u001A\u00020EH\u0017J&\u0010\u008E\u0001\u001A\u00020)2\u0007\u0010\u0081\u0001\u001A\u00020\u00132\b\u0010]\u001A\u0004\u0018\u00010^2\b\u0010\u0082\u0001\u001A\u00030\u0083\u0001H\u0016J2\u0010\u008F\u0001\u001A\u00020\\2\u0006\u0010w\u001A\u00020\u00132\u000F\u0010\u0090\u0001\u001A\n\u0012\u0005\u0012\u00030\u0092\u00010\u0091\u00012\b\u0010\u0093\u0001\u001A\u00030\u0094\u0001H\u0017\u00A2\u0006\u0003\u0010\u0095\u0001J\u000B\u0010\u0096\u0001\u001A\u0004\u0018\u000101H\u0017J\t\u0010\u0097\u0001\u001A\u0004\u0018\u000101J\u0012\u0010\u0098\u0001\u001A\u00020\\2\u0007\u0010\u0099\u0001\u001A\u00020\u007FH\u0015J\u0012\u0010\u009A\u0001\u001A\u00020\\2\u0007\u0010\u009B\u0001\u001A\u00020\u0013H\u0017J\t\u0010\u009C\u0001\u001A\u00020\\H\u0015J\f\u0010\u009D\u0001\u001A\u0005\u0018\u00010\u009E\u0001H\u0016JF\u0010\u009F\u0001\u001A\n\u0012\u0005\u0012\u0003H\u00A1\u00010\u00A0\u0001\"\u0005\b\u0000\u0010\u00A1\u0001\"\u0005\b\u0001\u0010\u00A2\u00012\u0016\u0010\u00A3\u0001\u001A\u0011\u0012\u0005\u0012\u0003H\u00A1\u0001\u0012\u0005\u0012\u0003H\u00A2\u00010\u00A4\u00012\u000F\u0010\u00A5\u0001\u001A\n\u0012\u0005\u0012\u0003H\u00A2\u00010\u00A6\u0001JO\u0010\u009F\u0001\u001A\n\u0012\u0005\u0012\u0003H\u00A1\u00010\u00A0\u0001\"\u0005\b\u0000\u0010\u00A1\u0001\"\u0005\b\u0001\u0010\u00A2\u00012\u0016\u0010\u00A3\u0001\u001A\u0011\u0012\u0005\u0012\u0003H\u00A1\u0001\u0012\u0005\u0012\u0003H\u00A2\u00010\u00A4\u00012\u0007\u0010\u00A7\u0001\u001A\u00020\u00192\u000F\u0010\u00A5\u0001\u001A\n\u0012\u0005\u0012\u0003H\u00A2\u00010\u00A6\u0001J\u0011\u0010\u00A8\u0001\u001A\u00020\\2\u0006\u0010b\u001A\u00020cH\u0016J\u0015\u0010\u00A9\u0001\u001A\u00020\\2\f\u0010j\u001A\b\u0012\u0004\u0012\u00020E0DJ\u000F\u0010\u00AA\u0001\u001A\u00020\\2\u0006\u0010j\u001A\u00020lJ\u0015\u0010\u00AB\u0001\u001A\u00020\\2\f\u0010j\u001A\b\u0012\u0004\u0012\u00020G0DJ\u0015\u0010\u00AC\u0001\u001A\u00020\\2\f\u0010j\u001A\b\u0012\u0004\u0012\u00020I0DJ\u0015\u0010\u00AD\u0001\u001A\u00020\\2\f\u0010j\u001A\b\u0012\u0004\u0012\u00020K0DJ\u0015\u0010\u00AE\u0001\u001A\u00020\\2\f\u0010j\u001A\b\u0012\u0004\u0012\u00020\u00130DJ\u000F\u0010\u00AF\u0001\u001A\u00020\\2\u0006\u0010j\u001A\u00020NJ\t\u0010\u00B0\u0001\u001A\u00020\\H\u0016J\u0013\u0010\u00B1\u0001\u001A\u00020\\2\b\u0010]\u001A\u0004\u0018\u00010^H\u0016J\u001D\u0010\u00B1\u0001\u001A\u00020\\2\b\u0010]\u001A\u0004\u0018\u00010^2\b\u0010_\u001A\u0004\u0018\u00010`H\u0016J\u0014\u0010\u00B1\u0001\u001A\u00020\\2\t\b\u0001\u0010\u00B2\u0001\u001A\u00020\u0013H\u0016J\u001A\u0010\u00B3\u0001\u001A\u00020\\2\u0007\u0010\u008A\u0001\u001A\u00020I2\u0006\u0010w\u001A\u00020\u0013H\u0017J%\u0010\u00B3\u0001\u001A\u00020\\2\u0007\u0010\u008A\u0001\u001A\u00020I2\u0006\u0010w\u001A\u00020\u00132\t\u0010\u00B4\u0001\u001A\u0004\u0018\u00010\u007FH\u0017JA\u0010\u00B5\u0001\u001A\u00020\\2\b\u0010\u008A\u0001\u001A\u00030\u00B6\u00012\u0006\u0010w\u001A\u00020\u00132\t\u0010\u00B7\u0001\u001A\u0004\u0018\u00010I2\u0007\u0010\u00B8\u0001\u001A\u00020\u00132\u0007\u0010\u00B9\u0001\u001A\u00020\u00132\u0007\u0010\u00BA\u0001\u001A\u00020\u0013H\u0017JL\u0010\u00B5\u0001\u001A\u00020\\2\b\u0010\u008A\u0001\u001A\u00030\u00B6\u00012\u0006\u0010w\u001A\u00020\u00132\t\u0010\u00B7\u0001\u001A\u0004\u0018\u00010I2\u0007\u0010\u00B8\u0001\u001A\u00020\u00132\u0007\u0010\u00B9\u0001\u001A\u00020\u00132\u0007\u0010\u00BA\u0001\u001A\u00020\u00132\t\u0010\u00B4\u0001\u001A\u0004\u0018\u00010\u007FH\u0017R\u0010\u0010\u0016\u001A\u0004\u0018\u00010\u0017X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001A\u00020\u0019\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001A\u0010\u001BR\u0012\u0010\u0012\u001A\u00020\u00138\u0002@\u0002X\u0083\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001C\u001A\u00020\u001DX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u001E\u001A\u00020\u001F8WX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b \u0010!R\u001B\u0010\"\u001A\u00020#8VX\u0096\u0084\u0002\u00A2\u0006\f\n\u0004\b&\u0010\'\u001A\u0004\b$\u0010%R\u000E\u0010(\u001A\u00020)X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010*\u001A\u00020)X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001B\u0010+\u001A\u00020,8VX\u0096\u0084\u0002\u00A2\u0006\f\n\u0004\b/\u0010\'\u001A\u0004\b-\u0010.R\u0016\u00100\u001A\u0004\u0018\u0001018WX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b2\u00103R\u0014\u00104\u001A\u0002058VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b6\u00107R\u000E\u00108\u001A\u000209X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010:\u001A\u00020;X\u0082\u0004\u00A2\u0006\u0002\n\u0000R!\u0010<\u001A\u00020=8FX\u0086\u0084\u0002\u00A2\u0006\u0012\n\u0004\bA\u0010\'\u0012\u0004\b>\u0010\u0015\u001A\u0004\b?\u0010@R\u001A\u0010B\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020E0D0CX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010F\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020G0D0CX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010H\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0D0CX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010J\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020K0D0CX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010L\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130D0CX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010M\u001A\b\u0012\u0004\u0012\u00020N0CX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010O\u001A\u00020PX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010Q\u001A\u00020R8F\u00A2\u0006\u0006\u001A\u0004\bS\u0010TR\u0014\u0010U\u001A\u00020VX\u0082\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\bW\u0010\u0015R\u0014\u0010X\u001A\u00020\u00178VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\bY\u0010Z\u00A8\u0006\u00C0\u0001"}, d2 = {"Landroidx/activity/ComponentActivity;", "Landroidx/core/app/ComponentActivity;", "Landroidx/activity/contextaware/ContextAware;", "Landroidx/lifecycle/LifecycleOwner;", "Landroidx/lifecycle/ViewModelStoreOwner;", "Landroidx/lifecycle/HasDefaultViewModelProviderFactory;", "Landroidx/savedstate/SavedStateRegistryOwner;", "Landroidx/activity/OnBackPressedDispatcherOwner;", "Landroidx/activity/result/ActivityResultRegistryOwner;", "Landroidx/activity/result/ActivityResultCaller;", "Landroidx/core/content/OnConfigurationChangedProvider;", "Landroidx/core/content/OnTrimMemoryProvider;", "Landroidx/core/app/OnNewIntentProvider;", "Landroidx/core/app/OnMultiWindowModeChangedProvider;", "Landroidx/core/app/OnPictureInPictureModeChangedProvider;", "Landroidx/core/app/OnUserLeaveHintProvider;", "Landroidx/core/view/MenuHost;", "Landroidx/activity/FullyDrawnReporterOwner;", "contentLayoutId", "", "(I)V", "()V", "_viewModelStore", "Landroidx/lifecycle/ViewModelStore;", "activityResultRegistry", "Landroidx/activity/result/ActivityResultRegistry;", "getActivityResultRegistry", "()Landroidx/activity/result/ActivityResultRegistry;", "contextAwareHelper", "Landroidx/activity/contextaware/ContextAwareHelper;", "defaultViewModelCreationExtras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "getDefaultViewModelCreationExtras", "()Landroidx/lifecycle/viewmodel/CreationExtras;", "defaultViewModelProviderFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getDefaultViewModelProviderFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "defaultViewModelProviderFactory$delegate", "Lkotlin/Lazy;", "dispatchingOnMultiWindowModeChanged", "", "dispatchingOnPictureInPictureModeChanged", "fullyDrawnReporter", "Landroidx/activity/FullyDrawnReporter;", "getFullyDrawnReporter", "()Landroidx/activity/FullyDrawnReporter;", "fullyDrawnReporter$delegate", "lastCustomNonConfigurationInstance", "", "getLastCustomNonConfigurationInstance", "()Ljava/lang/Object;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "menuHostHelper", "Landroidx/core/view/MenuHostHelper;", "nextLocalRequestCode", "Ljava/util/concurrent/atomic/AtomicInteger;", "onBackPressedDispatcher", "Landroidx/activity/OnBackPressedDispatcher;", "getOnBackPressedDispatcher$annotations", "getOnBackPressedDispatcher", "()Landroidx/activity/OnBackPressedDispatcher;", "onBackPressedDispatcher$delegate", "onConfigurationChangedListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/core/util/Consumer;", "Landroid/content/res/Configuration;", "onMultiWindowModeChangedListeners", "Landroidx/core/app/MultiWindowModeChangedInfo;", "onNewIntentListeners", "Landroid/content/Intent;", "onPictureInPictureModeChangedListeners", "Landroidx/core/app/PictureInPictureModeChangedInfo;", "onTrimMemoryListeners", "onUserLeaveHintListeners", "Ljava/lang/Runnable;", "reportFullyDrawnExecutor", "Landroidx/activity/ComponentActivity$ReportFullyDrawnExecutor;", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "getSavedStateRegistry", "()Landroidx/savedstate/SavedStateRegistry;", "savedStateRegistryController", "Landroidx/savedstate/SavedStateRegistryController;", "getSavedStateRegistryController$annotations", "viewModelStore", "getViewModelStore", "()Landroidx/lifecycle/ViewModelStore;", "addContentView", "", "view", "Landroid/view/View;", "params", "Landroid/view/ViewGroup$LayoutParams;", "addMenuProvider", "provider", "Landroidx/core/view/MenuProvider;", "owner", "state", "Landroidx/lifecycle/Lifecycle$State;", "addObserverForBackInvoker", "dispatcher", "addOnConfigurationChangedListener", "listener", "addOnContextAvailableListener", "Landroidx/activity/contextaware/OnContextAvailableListener;", "addOnMultiWindowModeChangedListener", "addOnNewIntentListener", "addOnPictureInPictureModeChangedListener", "addOnTrimMemoryListener", "addOnUserLeaveHintListener", "createFullyDrawnExecutor", "ensureViewModelStore", "initializeViewTreeOwners", "invalidateMenu", "onActivityResult", "requestCode", "resultCode", "data", "onBackPressed", "onConfigurationChanged", "newConfig", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreatePanelMenu", "featureId", "menu", "Landroid/view/Menu;", "onMenuItemSelected", "item", "Landroid/view/MenuItem;", "onMultiWindowModeChanged", "isInMultiWindowMode", "onNewIntent", "intent", "onPanelClosed", "onPictureInPictureModeChanged", "isInPictureInPictureMode", "onPreparePanel", "onRequestPermissionsResult", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onRetainCustomNonConfigurationInstance", "onRetainNonConfigurationInstance", "onSaveInstanceState", "outState", "onTrimMemory", "level", "onUserLeaveHint", "peekAvailableContext", "Landroid/content/Context;", "registerForActivityResult", "Landroidx/activity/result/ActivityResultLauncher;", "I", "O", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "callback", "Landroidx/activity/result/ActivityResultCallback;", "registry", "removeMenuProvider", "removeOnConfigurationChangedListener", "removeOnContextAvailableListener", "removeOnMultiWindowModeChangedListener", "removeOnNewIntentListener", "removeOnPictureInPictureModeChangedListener", "removeOnTrimMemoryListener", "removeOnUserLeaveHintListener", "reportFullyDrawn", "setContentView", "layoutResID", "startActivityForResult", "options", "startIntentSenderForResult", "Landroid/content/IntentSender;", "fillInIntent", "flagsMask", "flagsValues", "extraFlags", "Api33Impl", "Companion", "NonConfigurationInstances", "ReportFullyDrawnExecutor", "ReportFullyDrawnExecutorImpl", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class ComponentActivity extends androidx.core.app.ComponentActivity implements FullyDrawnReporterOwner, OnBackPressedDispatcherOwner, ContextAware, ActivityResultCaller, ActivityResultRegistryOwner, OnMultiWindowModeChangedProvider, OnNewIntentProvider, OnPictureInPictureModeChangedProvider, OnUserLeaveHintProvider, OnConfigurationChangedProvider, OnTrimMemoryProvider, MenuHost, HasDefaultViewModelProviderFactory, LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006¨\u0006\u0007"}, d2 = {"Landroidx/activity/ComponentActivity$Api33Impl;", "", "()V", "getOnBackInvokedDispatcher", "Landroid/window/OnBackInvokedDispatcher;", "activity", "Landroid/app/Activity;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class Api33Impl {
        public static final Api33Impl INSTANCE;

        static {
            Api33Impl.INSTANCE = new Api33Impl();
        }

        public final OnBackInvokedDispatcher getOnBackInvokedDispatcher(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            OnBackInvokedDispatcher onBackInvokedDispatcher0 = activity0.getOnBackInvokedDispatcher();
            Intrinsics.checkNotNullExpressionValue(onBackInvokedDispatcher0, "activity.getOnBackInvokedDispatcher()");
            return onBackInvokedDispatcher0;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/activity/ComponentActivity$Companion;", "", "()V", "ACTIVITY_RESULT_TAG", "", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001C\u0010\u0003\u001A\u0004\u0018\u00010\u0001X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001C\u0010\b\u001A\u0004\u0018\u00010\tX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\n\u0010\u000B\"\u0004\b\f\u0010\r¨\u0006\u000E"}, d2 = {"Landroidx/activity/ComponentActivity$NonConfigurationInstances;", "", "()V", "custom", "getCustom", "()Ljava/lang/Object;", "setCustom", "(Ljava/lang/Object;)V", "viewModelStore", "Landroidx/lifecycle/ViewModelStore;", "getViewModelStore", "()Landroidx/lifecycle/ViewModelStore;", "setViewModelStore", "(Landroidx/lifecycle/ViewModelStore;)V", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class NonConfigurationInstances {
        private Object custom;
        private ViewModelStore viewModelStore;

        public final Object getCustom() {
            return this.custom;
        }

        public final ViewModelStore getViewModelStore() {
            return this.viewModelStore;
        }

        public final void setCustom(Object object0) {
            this.custom = object0;
        }

        public final void setViewModelStore(ViewModelStore viewModelStore0) {
            this.viewModelStore = viewModelStore0;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bb\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001A\u00020\u0003H&J\u0010\u0010\u0004\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u0006H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/activity/ComponentActivity$ReportFullyDrawnExecutor;", "Ljava/util/concurrent/Executor;", "activityDestroyed", "", "viewCreated", "view", "Landroid/view/View;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    interface ReportFullyDrawnExecutor extends Executor {
        void activityDestroyed();

        void viewCreated(View arg1);
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001A\u00020\u0015H\u0016J\u0010\u0010\u0016\u001A\u00020\u00152\u0006\u0010\u0017\u001A\u00020\u0003H\u0016J\b\u0010\u0018\u001A\u00020\u0015H\u0016J\b\u0010\u0019\u001A\u00020\u0015H\u0016J\u0010\u0010\u001A\u001A\u00020\u00152\u0006\u0010\u001B\u001A\u00020\u001CH\u0016R\u001C\u0010\u0005\u001A\u0004\u0018\u00010\u0003X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\n\u001A\u00020\u000B¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\rR\u001A\u0010\u000E\u001A\u00020\u000FX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001D"}, d2 = {"Landroidx/activity/ComponentActivity$ReportFullyDrawnExecutorImpl;", "Landroidx/activity/ComponentActivity$ReportFullyDrawnExecutor;", "Landroid/view/ViewTreeObserver$OnDrawListener;", "Ljava/lang/Runnable;", "(Landroidx/activity/ComponentActivity;)V", "currentRunnable", "getCurrentRunnable", "()Ljava/lang/Runnable;", "setCurrentRunnable", "(Ljava/lang/Runnable;)V", "endWatchTimeMillis", "", "getEndWatchTimeMillis", "()J", "onDrawScheduled", "", "getOnDrawScheduled", "()Z", "setOnDrawScheduled", "(Z)V", "activityDestroyed", "", "execute", "runnable", "onDraw", "run", "viewCreated", "view", "Landroid/view/View;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class ReportFullyDrawnExecutorImpl implements ViewTreeObserver.OnDrawListener, ReportFullyDrawnExecutor, Runnable {
        private Runnable currentRunnable;
        private final long endWatchTimeMillis;
        private boolean onDrawScheduled;

        public ReportFullyDrawnExecutorImpl() {
            this.endWatchTimeMillis = SystemClock.uptimeMillis() + 10000L;
        }

        @Override  // androidx.activity.ComponentActivity$ReportFullyDrawnExecutor
        public void activityDestroyed() {
            ComponentActivity.this.getWindow().getDecorView().removeCallbacks(this);
            ComponentActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }

        @Override
        public void execute(Runnable runnable0) {
            Intrinsics.checkNotNullParameter(runnable0, "runnable");
            this.currentRunnable = runnable0;
            View view0 = ComponentActivity.this.getWindow().getDecorView();
            Intrinsics.checkNotNullExpressionValue(view0, "window.decorView");
            if(this.onDrawScheduled) {
                if(Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                    view0.invalidate();
                    return;
                }
                view0.postInvalidate();
                return;
            }
            view0.postOnAnimation(() -> {
                Runnable runnable0 = this.currentRunnable;
                if(runnable0 != null) {
                    Intrinsics.checkNotNull(runnable0);
                    runnable0.run();
                    this.currentRunnable = null;
                }
            });
        }

        // 检测为 Lambda 实现
        private static final void execute$lambda$0(ReportFullyDrawnExecutorImpl componentActivity$ReportFullyDrawnExecutorImpl0) [...]

        public final Runnable getCurrentRunnable() {
            return this.currentRunnable;
        }

        public final long getEndWatchTimeMillis() {
            return this.endWatchTimeMillis;
        }

        public final boolean getOnDrawScheduled() {
            return this.onDrawScheduled;
        }

        @Override  // android.view.ViewTreeObserver$OnDrawListener
        public void onDraw() {
            Runnable runnable0 = this.currentRunnable;
            if(runnable0 != null) {
                runnable0.run();
                this.currentRunnable = null;
                if(ComponentActivity.this.getFullyDrawnReporter().isFullyDrawnReported()) {
                    this.onDrawScheduled = false;
                    ComponentActivity.this.getWindow().getDecorView().post(this);
                }
            }
            else if(SystemClock.uptimeMillis() > this.endWatchTimeMillis) {
                this.onDrawScheduled = false;
                ComponentActivity.this.getWindow().getDecorView().post(this);
            }
        }

        @Override
        public void run() {
            ComponentActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }

        public final void setCurrentRunnable(Runnable runnable0) {
            this.currentRunnable = runnable0;
        }

        public final void setOnDrawScheduled(boolean z) {
            this.onDrawScheduled = z;
        }

        @Override  // androidx.activity.ComponentActivity$ReportFullyDrawnExecutor
        public void viewCreated(View view0) {
            Intrinsics.checkNotNullParameter(view0, "view");
            if(!this.onDrawScheduled) {
                this.onDrawScheduled = true;
                view0.getViewTreeObserver().addOnDrawListener(this);
            }
        }
    }

    private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";
    private static final Companion Companion;
    private ViewModelStore _viewModelStore;
    private final ActivityResultRegistry activityResultRegistry;
    private int contentLayoutId;
    private final ContextAwareHelper contextAwareHelper;
    private final Lazy defaultViewModelProviderFactory$delegate;
    private boolean dispatchingOnMultiWindowModeChanged;
    private boolean dispatchingOnPictureInPictureModeChanged;
    private final Lazy fullyDrawnReporter$delegate;
    private final MenuHostHelper menuHostHelper;
    private final AtomicInteger nextLocalRequestCode;
    private final Lazy onBackPressedDispatcher$delegate;
    private final CopyOnWriteArrayList onConfigurationChangedListeners;
    private final CopyOnWriteArrayList onMultiWindowModeChangedListeners;
    private final CopyOnWriteArrayList onNewIntentListeners;
    private final CopyOnWriteArrayList onPictureInPictureModeChangedListeners;
    private final CopyOnWriteArrayList onTrimMemoryListeners;
    private final CopyOnWriteArrayList onUserLeaveHintListeners;
    private final ReportFullyDrawnExecutor reportFullyDrawnExecutor;
    private final SavedStateRegistryController savedStateRegistryController;

    static {
        ComponentActivity.Companion = new Companion(null);
    }

    public ComponentActivity() {
        this.contextAwareHelper = new ContextAwareHelper();
        this.menuHostHelper = new MenuHostHelper(() -> this.invalidateMenu());
        SavedStateRegistryController savedStateRegistryController0 = SavedStateRegistryController.Companion.create(this);
        this.savedStateRegistryController = savedStateRegistryController0;
        this.reportFullyDrawnExecutor = this.createFullyDrawnExecutor();
        this.fullyDrawnReporter$delegate = LazyKt.lazy(new Function0() {
            {
                ComponentActivity.this = componentActivity0;
                super(0);
            }

            public final FullyDrawnReporter invoke() {
                return new FullyDrawnReporter(ComponentActivity.this.reportFullyDrawnExecutor, new Function0() {
                    {
                        ComponentActivity.this = componentActivity0;
                        super(0);
                    }

                    @Override  // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        this.invoke();
                        return Unit.INSTANCE;
                    }

                    public final void invoke() {
                        ComponentActivity.this.reportFullyDrawn();
                    }
                });
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
        this.nextLocalRequestCode = new AtomicInteger();
        this.activityResultRegistry = new ActivityResultRegistry() {
            @Override  // androidx.activity.result.ActivityResultRegistry
            public void onLaunch(int v, ActivityResultContract activityResultContract0, Object object0, ActivityOptionsCompat activityOptionsCompat0) {
                Bundle bundle1;
                Intrinsics.checkNotNullParameter(activityResultContract0, "contract");
                ComponentActivity componentActivity0 = ComponentActivity.this;
                SynchronousResult activityResultContract$SynchronousResult0 = activityResultContract0.getSynchronousResult(componentActivity0, object0);
                if(activityResultContract$SynchronousResult0 != null) {
                    new Handler(Looper.getMainLooper()).post(() -> this.dispatchResult(v, activityResultContract$SynchronousResult0.getValue()));
                    return;
                }
                Intent intent0 = activityResultContract0.createIntent(componentActivity0, object0);
                if(intent0.getExtras() != null) {
                    Bundle bundle0 = intent0.getExtras();
                    Intrinsics.checkNotNull(bundle0);
                    if(bundle0.getClassLoader() == null) {
                        intent0.setExtrasClassLoader(componentActivity0.getClassLoader());
                    }
                }
                if(intent0.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
                    bundle1 = intent0.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                    intent0.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                }
                else {
                    bundle1 = activityOptionsCompat0 == null ? null : activityOptionsCompat0.toBundle();
                }
                if(Intrinsics.areEqual("androidx.activity.result.contract.action.REQUEST_PERMISSIONS", intent0.getAction())) {
                    String[] arr_s = intent0.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                    if(arr_s == null) {
                        arr_s = new String[0];
                    }
                    ActivityCompat.requestPermissions(componentActivity0, arr_s, v);
                    return;
                }
                if(Intrinsics.areEqual("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST", intent0.getAction())) {
                    IntentSenderRequest intentSenderRequest0 = (IntentSenderRequest)intent0.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
                    try {
                        Intrinsics.checkNotNull(intentSenderRequest0);
                        ActivityCompat.startIntentSenderForResult(componentActivity0, intentSenderRequest0.getIntentSender(), v, intentSenderRequest0.getFillInIntent(), intentSenderRequest0.getFlagsMask(), intentSenderRequest0.getFlagsValues(), 0, bundle1);
                    }
                    catch(IntentSender.SendIntentException intentSender$SendIntentException0) {
                        new Handler(Looper.getMainLooper()).post(() -> this.dispatchResult(v, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", intentSender$SendIntentException0)));
                    }
                    return;
                }
                ActivityCompat.startActivityForResult(componentActivity0, intent0, v, bundle1);
            }

            // 检测为 Lambda 实现
            private static final void onLaunch$lambda$0(androidx.activity.ComponentActivity.activityResultRegistry.1 componentActivity$activityResultRegistry$10, int v, SynchronousResult activityResultContract$SynchronousResult0) [...]

            // 检测为 Lambda 实现
            private static final void onLaunch$lambda$1(androidx.activity.ComponentActivity.activityResultRegistry.1 componentActivity$activityResultRegistry$10, int v, IntentSender.SendIntentException intentSender$SendIntentException0) [...]
        };
        this.onConfigurationChangedListeners = new CopyOnWriteArrayList();
        this.onTrimMemoryListeners = new CopyOnWriteArrayList();
        this.onNewIntentListeners = new CopyOnWriteArrayList();
        this.onMultiWindowModeChangedListeners = new CopyOnWriteArrayList();
        this.onPictureInPictureModeChangedListeners = new CopyOnWriteArrayList();
        this.onUserLeaveHintListeners = new CopyOnWriteArrayList();
        if(this.getLifecycle() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity\'s constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        this.getLifecycle().addObserver((LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) -> {
            Intrinsics.checkNotNullParameter(lifecycleOwner0, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
            if(lifecycle$Event0 == Event.ON_STOP) {
                Window window0 = this.getWindow();
                if(window0 != null) {
                    View view0 = window0.peekDecorView();
                    if(view0 != null) {
                        view0.cancelPendingInputEvents();
                    }
                }
            }
        });
        this.getLifecycle().addObserver((LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) -> {
            Intrinsics.checkNotNullParameter(lifecycleOwner0, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
            if(lifecycle$Event0 == Event.ON_DESTROY) {
                this.contextAwareHelper.clearAvailableContext();
                if(!this.isChangingConfigurations()) {
                    this.getViewModelStore().clear();
                }
                this.reportFullyDrawnExecutor.activityDestroyed();
            }
        });
        this.getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override  // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
                Intrinsics.checkNotNullParameter(lifecycleOwner0, "source");
                Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
                ComponentActivity.this.ensureViewModelStore();
                ComponentActivity.this.getLifecycle().removeObserver(this);
            }
        });
        savedStateRegistryController0.performAttach();
        SavedStateHandleSupport.enableSavedStateHandles(this);
        if(Build.VERSION.SDK_INT <= 23) {
            this.getLifecycle().addObserver(new ImmLeaksCleaner(this));
        }
        this.getSavedStateRegistry().registerSavedStateProvider("android:support:activity-result", () -> {
            Bundle bundle0 = new Bundle();
            this.activityResultRegistry.onSaveInstanceState(bundle0);
            return bundle0;
        });
        this.addOnContextAvailableListener((Context context0) -> {
            Intrinsics.checkNotNullParameter(context0, "it");
            Bundle bundle0 = this.getSavedStateRegistry().consumeRestoredStateForKey("android:support:activity-result");
            if(bundle0 != null) {
                this.activityResultRegistry.onRestoreInstanceState(bundle0);
            }
        });
        this.defaultViewModelProviderFactory$delegate = LazyKt.lazy(new Function0() {
            {
                ComponentActivity.this = componentActivity0;
                super(0);
            }

            public final SavedStateViewModelFactory invoke() {
                Application application0 = ComponentActivity.this.getApplication();
                return ComponentActivity.this.getIntent() == null ? new SavedStateViewModelFactory(application0, ComponentActivity.this, null) : new SavedStateViewModelFactory(application0, ComponentActivity.this, ComponentActivity.this.getIntent().getExtras());
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
        this.onBackPressedDispatcher$delegate = LazyKt.lazy(new Function0() {
            {
                ComponentActivity.this = componentActivity0;
                super(0);
            }

            public final OnBackPressedDispatcher invoke() {
                OnBackPressedDispatcher onBackPressedDispatcher0 = new OnBackPressedDispatcher(() -> try {
                    ComponentActivity.this.super.onBackPressed();
                }
                catch(IllegalStateException illegalStateException0) {
                    if(!Intrinsics.areEqual(illegalStateException0.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                        throw illegalStateException0;
                    }
                }
                catch(NullPointerException nullPointerException0) {
                    if(!Intrinsics.areEqual(nullPointerException0.getMessage(), "Attempt to invoke virtual method \'android.os.Handler android.app.FragmentHostCallback.getHandler()\' on a null object reference")) {
                        throw nullPointerException0;
                    }
                });
                ComponentActivity componentActivity0 = ComponentActivity.this;
                if(Build.VERSION.SDK_INT >= 33) {
                    if(!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                        new Handler(Looper.getMainLooper()).post(() -> componentActivity0.addObserverForBackInvoker(onBackPressedDispatcher0));
                        return onBackPressedDispatcher0;
                    }
                    componentActivity0.addObserverForBackInvoker(onBackPressedDispatcher0);
                }
                return onBackPressedDispatcher0;
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            // 检测为 Lambda 实现
            private static final void invoke$lambda$0(ComponentActivity componentActivity0) [...]

            // 检测为 Lambda 实现
            private static final void invoke$lambda$2$lambda$1(ComponentActivity componentActivity0, OnBackPressedDispatcher onBackPressedDispatcher0) [...]
        });
    }

    public ComponentActivity(int v) {
        this.contentLayoutId = v;
    }

    // 检测为 Lambda 实现
    private static final void _init_$lambda$2(ComponentActivity componentActivity0, LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) [...]

    // 检测为 Lambda 实现
    private static final void _init_$lambda$3(ComponentActivity componentActivity0, LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) [...]

    // 检测为 Lambda 实现
    private static final Bundle _init_$lambda$4(ComponentActivity componentActivity0) [...]

    // 检测为 Lambda 实现
    private static final void _init_$lambda$5(ComponentActivity componentActivity0, Context context0) [...]

    @Override  // android.app.Activity
    public void addContentView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        this.initializeViewTreeOwners();
        View view1 = this.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(view1, "window.decorView");
        this.reportFullyDrawnExecutor.viewCreated(view1);
        super.addContentView(view0, viewGroup$LayoutParams0);
    }

    @Override  // androidx.core.view.MenuHost
    public void addMenuProvider(MenuProvider menuProvider0) {
        Intrinsics.checkNotNullParameter(menuProvider0, "provider");
        this.menuHostHelper.addMenuProvider(menuProvider0);
    }

    @Override  // androidx.core.view.MenuHost
    public void addMenuProvider(MenuProvider menuProvider0, LifecycleOwner lifecycleOwner0) {
        Intrinsics.checkNotNullParameter(menuProvider0, "provider");
        Intrinsics.checkNotNullParameter(lifecycleOwner0, "owner");
        this.menuHostHelper.addMenuProvider(menuProvider0, lifecycleOwner0);
    }

    @Override  // androidx.core.view.MenuHost
    public void addMenuProvider(MenuProvider menuProvider0, LifecycleOwner lifecycleOwner0, State lifecycle$State0) {
        Intrinsics.checkNotNullParameter(menuProvider0, "provider");
        Intrinsics.checkNotNullParameter(lifecycleOwner0, "owner");
        Intrinsics.checkNotNullParameter(lifecycle$State0, "state");
        this.menuHostHelper.addMenuProvider(menuProvider0, lifecycleOwner0, lifecycle$State0);
    }

    private final void addObserverForBackInvoker(OnBackPressedDispatcher onBackPressedDispatcher0) {
        this.getLifecycle().addObserver((LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) -> {
            Intrinsics.checkNotNullParameter(lifecycleOwner0, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
            if(lifecycle$Event0 == Event.ON_CREATE) {
                onBackPressedDispatcher0.setOnBackInvokedDispatcher(Api33Impl.INSTANCE.getOnBackInvokedDispatcher(this));
            }
        });
    }

    // 检测为 Lambda 实现
    private static final void addObserverForBackInvoker$lambda$7(OnBackPressedDispatcher onBackPressedDispatcher0, ComponentActivity componentActivity0, LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) [...]

    @Override  // androidx.core.content.OnConfigurationChangedProvider
    public final void addOnConfigurationChangedListener(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "listener");
        this.onConfigurationChangedListeners.add(consumer0);
    }

    @Override  // androidx.activity.contextaware.ContextAware
    public final void addOnContextAvailableListener(OnContextAvailableListener onContextAvailableListener0) {
        Intrinsics.checkNotNullParameter(onContextAvailableListener0, "listener");
        this.contextAwareHelper.addOnContextAvailableListener(onContextAvailableListener0);
    }

    @Override  // androidx.core.app.OnMultiWindowModeChangedProvider
    public final void addOnMultiWindowModeChangedListener(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "listener");
        this.onMultiWindowModeChangedListeners.add(consumer0);
    }

    @Override  // androidx.core.app.OnNewIntentProvider
    public final void addOnNewIntentListener(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "listener");
        this.onNewIntentListeners.add(consumer0);
    }

    @Override  // androidx.core.app.OnPictureInPictureModeChangedProvider
    public final void addOnPictureInPictureModeChangedListener(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "listener");
        this.onPictureInPictureModeChangedListeners.add(consumer0);
    }

    @Override  // androidx.core.content.OnTrimMemoryProvider
    public final void addOnTrimMemoryListener(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "listener");
        this.onTrimMemoryListeners.add(consumer0);
    }

    @Override  // androidx.core.app.OnUserLeaveHintProvider
    public final void addOnUserLeaveHintListener(Runnable runnable0) {
        Intrinsics.checkNotNullParameter(runnable0, "listener");
        this.onUserLeaveHintListeners.add(runnable0);
    }

    private final ReportFullyDrawnExecutor createFullyDrawnExecutor() {
        return new ReportFullyDrawnExecutorImpl(this);
    }

    private final void ensureViewModelStore() {
        if(this._viewModelStore == null) {
            NonConfigurationInstances componentActivity$NonConfigurationInstances0 = (NonConfigurationInstances)this.getLastNonConfigurationInstance();
            if(componentActivity$NonConfigurationInstances0 != null) {
                this._viewModelStore = componentActivity$NonConfigurationInstances0.getViewModelStore();
            }
            if(this._viewModelStore == null) {
                this._viewModelStore = new ViewModelStore();
            }
        }
    }

    @Override  // androidx.activity.result.ActivityResultRegistryOwner
    public final ActivityResultRegistry getActivityResultRegistry() {
        return this.activityResultRegistry;
    }

    @Override  // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public CreationExtras getDefaultViewModelCreationExtras() {
        Bundle bundle0 = null;
        MutableCreationExtras mutableCreationExtras0 = new MutableCreationExtras(null, 1, null);
        if(this.getApplication() != null) {
            Application application0 = this.getApplication();
            Intrinsics.checkNotNullExpressionValue(application0, "application");
            mutableCreationExtras0.set(AndroidViewModelFactory.APPLICATION_KEY, application0);
        }
        mutableCreationExtras0.set(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY, this);
        mutableCreationExtras0.set(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY, this);
        Intent intent0 = this.getIntent();
        if(intent0 != null) {
            bundle0 = intent0.getExtras();
        }
        if(bundle0 != null) {
            mutableCreationExtras0.set(SavedStateHandleSupport.DEFAULT_ARGS_KEY, bundle0);
        }
        return mutableCreationExtras0;
    }

    @Override  // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public Factory getDefaultViewModelProviderFactory() {
        return (Factory)this.defaultViewModelProviderFactory$delegate.getValue();
    }

    @Override  // androidx.activity.FullyDrawnReporterOwner
    public FullyDrawnReporter getFullyDrawnReporter() {
        return (FullyDrawnReporter)this.fullyDrawnReporter$delegate.getValue();
    }

    @Deprecated(message = "Use a {@link androidx.lifecycle.ViewModel} to store non config state.")
    public Object getLastCustomNonConfigurationInstance() {
        NonConfigurationInstances componentActivity$NonConfigurationInstances0 = (NonConfigurationInstances)this.getLastNonConfigurationInstance();
        return componentActivity$NonConfigurationInstances0 == null ? null : componentActivity$NonConfigurationInstances0.getCustom();
    }

    @Override  // androidx.core.app.ComponentActivity, androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override  // androidx.activity.OnBackPressedDispatcherOwner
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return (OnBackPressedDispatcher)this.onBackPressedDispatcher$delegate.getValue();
    }

    public static void getOnBackPressedDispatcher$annotations() {
    }

    @Override  // androidx.savedstate.SavedStateRegistryOwner
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.savedStateRegistryController.getSavedStateRegistry();
    }

    private static void getSavedStateRegistryController$annotations() {
    }

    @Override  // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        if(this.getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can\'t request ViewModel before onCreate call.");
        }
        this.ensureViewModelStore();
        ViewModelStore viewModelStore0 = this._viewModelStore;
        Intrinsics.checkNotNull(viewModelStore0);
        return viewModelStore0;
    }

    public void initializeViewTreeOwners() {
        View view0 = this.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(view0, "window.decorView");
        ViewTreeLifecycleOwner.set(view0, this);
        View view1 = this.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(view1, "window.decorView");
        ViewTreeViewModelStoreOwner.set(view1, this);
        View view2 = this.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(view2, "window.decorView");
        ViewTreeSavedStateRegistryOwner.set(view2, this);
        View view3 = this.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(view3, "window.decorView");
        ViewTreeOnBackPressedDispatcherOwner.set(view3, this);
        View view4 = this.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(view4, "window.decorView");
        ViewTreeFullyDrawnReporterOwner.set(view4, this);
    }

    @Override  // androidx.core.view.MenuHost
    public void invalidateMenu() {
        this.invalidateOptionsMenu();
    }

    // 检测为 Lambda 实现
    private static final void menuHostHelper$lambda$0(ComponentActivity componentActivity0) [...]

    @Override  // android.app.Activity
    @Deprecated(message = "This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    protected void onActivityResult(int v, int v1, Intent intent0) {
        if(!this.activityResultRegistry.dispatchResult(v, v1, intent0)) {
            super.onActivityResult(v, v1, intent0);
        }
    }

    @Override  // android.app.Activity
    @Deprecated(message = "This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    public void onBackPressed() {
        this.getOnBackPressedDispatcher().onBackPressed();
    }

    @Override  // android.app.Activity
    public void onConfigurationChanged(Configuration configuration0) {
        Intrinsics.checkNotNullParameter(configuration0, "newConfig");
        super.onConfigurationChanged(configuration0);
        for(Object object0: this.onConfigurationChangedListeners) {
            ((Consumer)object0).accept(configuration0);
        }
    }

    @Override  // androidx.core.app.ComponentActivity
    protected void onCreate(Bundle bundle0) {
        this.savedStateRegistryController.performRestore(bundle0);
        this.contextAwareHelper.dispatchOnContextAvailable(this);
        super.onCreate(bundle0);
        ReportFragment.Companion.injectIfNeededIn(this);
        int v = this.contentLayoutId;
        if(v != 0) {
            this.setContentView(v);
        }
    }

    @Override  // android.app.Activity
    public boolean onCreatePanelMenu(int v, Menu menu0) {
        Intrinsics.checkNotNullParameter(menu0, "menu");
        if(v == 0) {
            super.onCreatePanelMenu(0, menu0);
            MenuInflater menuInflater0 = this.getMenuInflater();
            this.menuHostHelper.onCreateMenu(menu0, menuInflater0);
        }
        return true;
    }

    @Override  // android.app.Activity
    public boolean onMenuItemSelected(int v, MenuItem menuItem0) {
        Intrinsics.checkNotNullParameter(menuItem0, "item");
        if(super.onMenuItemSelected(v, menuItem0)) {
            return true;
        }
        return v == 0 ? this.menuHostHelper.onMenuItemSelected(menuItem0) : false;
    }

    @Override  // android.app.Activity
    @Deprecated(message = "Deprecated in android.app.Activity")
    public void onMultiWindowModeChanged(boolean z) {
        if(!this.dispatchingOnMultiWindowModeChanged) {
            for(Object object0: this.onMultiWindowModeChangedListeners) {
                ((Consumer)object0).accept(new MultiWindowModeChangedInfo(z));
            }
        }
    }

    @Override  // android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration0) {
        Intrinsics.checkNotNullParameter(configuration0, "newConfig");
        this.dispatchingOnMultiWindowModeChanged = true;
        try {
            super.onMultiWindowModeChanged(z, configuration0);
        }
        finally {
            this.dispatchingOnMultiWindowModeChanged = false;
        }
        for(Object object0: this.onMultiWindowModeChangedListeners) {
            ((Consumer)object0).accept(new MultiWindowModeChangedInfo(z, configuration0));
        }
    }

    @Override  // android.app.Activity
    protected void onNewIntent(Intent intent0) {
        Intrinsics.checkNotNullParameter(intent0, "intent");
        super.onNewIntent(intent0);
        for(Object object0: this.onNewIntentListeners) {
            ((Consumer)object0).accept(intent0);
        }
    }

    @Override  // android.app.Activity
    public void onPanelClosed(int v, Menu menu0) {
        Intrinsics.checkNotNullParameter(menu0, "menu");
        this.menuHostHelper.onMenuClosed(menu0);
        super.onPanelClosed(v, menu0);
    }

    @Override  // android.app.Activity
    @Deprecated(message = "Deprecated in android.app.Activity")
    public void onPictureInPictureModeChanged(boolean z) {
        if(!this.dispatchingOnPictureInPictureModeChanged) {
            for(Object object0: this.onPictureInPictureModeChangedListeners) {
                ((Consumer)object0).accept(new PictureInPictureModeChangedInfo(z));
            }
        }
    }

    @Override  // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z, Configuration configuration0) {
        Intrinsics.checkNotNullParameter(configuration0, "newConfig");
        this.dispatchingOnPictureInPictureModeChanged = true;
        try {
            super.onPictureInPictureModeChanged(z, configuration0);
        }
        finally {
            this.dispatchingOnPictureInPictureModeChanged = false;
        }
        for(Object object0: this.onPictureInPictureModeChangedListeners) {
            ((Consumer)object0).accept(new PictureInPictureModeChangedInfo(z, configuration0));
        }
    }

    @Override  // android.app.Activity
    public boolean onPreparePanel(int v, View view0, Menu menu0) {
        Intrinsics.checkNotNullParameter(menu0, "menu");
        if(v == 0) {
            super.onPreparePanel(0, view0, menu0);
            this.menuHostHelper.onPrepareMenu(menu0);
        }
        return true;
    }

    @Override  // android.app.Activity
    @Deprecated(message = "This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)} passing\n      in a {@link RequestMultiplePermissions} object for the {@link ActivityResultContract} and\n      handling the result in the {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    public void onRequestPermissionsResult(int v, String[] arr_s, int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_s, "permissions");
        Intrinsics.checkNotNullParameter(arr_v, "grantResults");
        Intent intent0 = new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", arr_s).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", arr_v);
        if(!this.activityResultRegistry.dispatchResult(v, -1, intent0) && Build.VERSION.SDK_INT >= 23) {
            super.onRequestPermissionsResult(v, arr_s, arr_v);
        }
    }

    @Deprecated(message = "Use a {@link androidx.lifecycle.ViewModel} to store non config state.")
    public Object onRetainCustomNonConfigurationInstance() [...] // Inlined contents

    @Override  // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        ViewModelStore viewModelStore0 = this._viewModelStore;
        if(viewModelStore0 == null) {
            NonConfigurationInstances componentActivity$NonConfigurationInstances0 = (NonConfigurationInstances)this.getLastNonConfigurationInstance();
            if(componentActivity$NonConfigurationInstances0 != null) {
                viewModelStore0 = componentActivity$NonConfigurationInstances0.getViewModelStore();
            }
        }
        if(viewModelStore0 == null) {
            return null;
        }
        NonConfigurationInstances componentActivity$NonConfigurationInstances1 = new NonConfigurationInstances();
        componentActivity$NonConfigurationInstances1.setCustom(null);
        componentActivity$NonConfigurationInstances1.setViewModelStore(viewModelStore0);
        return componentActivity$NonConfigurationInstances1;
    }

    @Override  // androidx.core.app.ComponentActivity
    protected void onSaveInstanceState(Bundle bundle0) {
        Intrinsics.checkNotNullParameter(bundle0, "outState");
        if(this.getLifecycle() instanceof LifecycleRegistry) {
            Lifecycle lifecycle0 = this.getLifecycle();
            Intrinsics.checkNotNull(lifecycle0, "null cannot be cast to non-null type androidx.lifecycle.LifecycleRegistry");
            ((LifecycleRegistry)lifecycle0).setCurrentState(State.CREATED);
        }
        super.onSaveInstanceState(bundle0);
        this.savedStateRegistryController.performSave(bundle0);
    }

    @Override  // android.app.Activity
    public void onTrimMemory(int v) {
        super.onTrimMemory(v);
        for(Object object0: this.onTrimMemoryListeners) {
            ((Consumer)object0).accept(v);
        }
    }

    @Override  // android.app.Activity
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        for(Object object0: this.onUserLeaveHintListeners) {
            ((Runnable)object0).run();
        }
    }

    @Override  // androidx.activity.contextaware.ContextAware
    public Context peekAvailableContext() {
        return this.contextAwareHelper.peekAvailableContext();
    }

    @Override  // androidx.activity.result.ActivityResultCaller
    public final ActivityResultLauncher registerForActivityResult(ActivityResultContract activityResultContract0, ActivityResultCallback activityResultCallback0) {
        Intrinsics.checkNotNullParameter(activityResultContract0, "contract");
        Intrinsics.checkNotNullParameter(activityResultCallback0, "callback");
        return this.registerForActivityResult(activityResultContract0, this.activityResultRegistry, activityResultCallback0);
    }

    @Override  // androidx.activity.result.ActivityResultCaller
    public final ActivityResultLauncher registerForActivityResult(ActivityResultContract activityResultContract0, ActivityResultRegistry activityResultRegistry0, ActivityResultCallback activityResultCallback0) {
        Intrinsics.checkNotNullParameter(activityResultContract0, "contract");
        Intrinsics.checkNotNullParameter(activityResultRegistry0, "registry");
        Intrinsics.checkNotNullParameter(activityResultCallback0, "callback");
        return activityResultRegistry0.register("activity_rq#" + this.nextLocalRequestCode.getAndIncrement(), this, activityResultContract0, activityResultCallback0);
    }

    @Override  // androidx.core.view.MenuHost
    public void removeMenuProvider(MenuProvider menuProvider0) {
        Intrinsics.checkNotNullParameter(menuProvider0, "provider");
        this.menuHostHelper.removeMenuProvider(menuProvider0);
    }

    @Override  // androidx.core.content.OnConfigurationChangedProvider
    public final void removeOnConfigurationChangedListener(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "listener");
        this.onConfigurationChangedListeners.remove(consumer0);
    }

    @Override  // androidx.activity.contextaware.ContextAware
    public final void removeOnContextAvailableListener(OnContextAvailableListener onContextAvailableListener0) {
        Intrinsics.checkNotNullParameter(onContextAvailableListener0, "listener");
        this.contextAwareHelper.removeOnContextAvailableListener(onContextAvailableListener0);
    }

    @Override  // androidx.core.app.OnMultiWindowModeChangedProvider
    public final void removeOnMultiWindowModeChangedListener(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "listener");
        this.onMultiWindowModeChangedListeners.remove(consumer0);
    }

    @Override  // androidx.core.app.OnNewIntentProvider
    public final void removeOnNewIntentListener(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "listener");
        this.onNewIntentListeners.remove(consumer0);
    }

    @Override  // androidx.core.app.OnPictureInPictureModeChangedProvider
    public final void removeOnPictureInPictureModeChangedListener(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "listener");
        this.onPictureInPictureModeChangedListeners.remove(consumer0);
    }

    @Override  // androidx.core.content.OnTrimMemoryProvider
    public final void removeOnTrimMemoryListener(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "listener");
        this.onTrimMemoryListeners.remove(consumer0);
    }

    @Override  // androidx.core.app.OnUserLeaveHintProvider
    public final void removeOnUserLeaveHintListener(Runnable runnable0) {
        Intrinsics.checkNotNullParameter(runnable0, "listener");
        this.onUserLeaveHintListeners.remove(runnable0);
    }

    @Override  // android.app.Activity
    public void reportFullyDrawn() {
        try {
            if(Trace.isEnabled()) {
                Trace.beginSection("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            this.getFullyDrawnReporter().fullyDrawnReported();
        }
        finally {
            Trace.endSection();
        }
    }

    @Override  // android.app.Activity
    public void setContentView(int v) {
        this.initializeViewTreeOwners();
        View view0 = this.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(view0, "window.decorView");
        this.reportFullyDrawnExecutor.viewCreated(view0);
        super.setContentView(v);
    }

    @Override  // android.app.Activity
    public void setContentView(View view0) {
        this.initializeViewTreeOwners();
        View view1 = this.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(view1, "window.decorView");
        this.reportFullyDrawnExecutor.viewCreated(view1);
        super.setContentView(view0);
    }

    @Override  // android.app.Activity
    public void setContentView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        this.initializeViewTreeOwners();
        View view1 = this.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(view1, "window.decorView");
        this.reportFullyDrawnExecutor.viewCreated(view1);
        super.setContentView(view0, viewGroup$LayoutParams0);
    }

    @Override  // android.app.Activity
    @Deprecated(message = "This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      passing in a {@link StartActivityForResult} object for the {@link ActivityResultContract}.")
    public void startActivityForResult(Intent intent0, int v) {
        Intrinsics.checkNotNullParameter(intent0, "intent");
        super.startActivityForResult(intent0, v);
    }

    @Override  // android.app.Activity
    @Deprecated(message = "This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      passing in a {@link StartActivityForResult} object for the {@link ActivityResultContract}.")
    public void startActivityForResult(Intent intent0, int v, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(intent0, "intent");
        super.startActivityForResult(intent0, v, bundle0);
    }

    @Override  // android.app.Activity
    @Deprecated(message = "This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      passing in a {@link StartIntentSenderForResult} object for the\n      {@link ActivityResultContract}.")
    public void startIntentSenderForResult(IntentSender intentSender0, int v, Intent intent0, int v1, int v2, int v3) throws IntentSender.SendIntentException {
        Intrinsics.checkNotNullParameter(intentSender0, "intent");
        super.startIntentSenderForResult(intentSender0, v, intent0, v1, v2, v3);
    }

    @Override  // android.app.Activity
    @Deprecated(message = "This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      passing in a {@link StartIntentSenderForResult} object for the\n      {@link ActivityResultContract}.")
    public void startIntentSenderForResult(IntentSender intentSender0, int v, Intent intent0, int v1, int v2, int v3, Bundle bundle0) throws IntentSender.SendIntentException {
        Intrinsics.checkNotNullParameter(intentSender0, "intent");
        super.startIntentSenderForResult(intentSender0, v, intent0, v1, v2, v3, bundle0);
    }
}

