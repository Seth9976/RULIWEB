package androidx.core.view;

import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnLayoutChangeListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001A2\u0010 \u001A\u00020!*\u00020\u00022#\b\u0004\u0010\"\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020!0#H\u0086\b\u001A2\u0010\'\u001A\u00020!*\u00020\u00022#\b\u0004\u0010\"\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020!0#H\u0086\b\u001A2\u0010(\u001A\u00020!*\u00020\u00022#\b\u0004\u0010\"\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020!0#H\u0086\b\u001A2\u0010)\u001A\u00020!*\u00020\u00022#\b\u0004\u0010\"\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020!0#H\u0086\b\u001A2\u0010*\u001A\u00020+*\u00020\u00022#\b\u0004\u0010\"\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020!0#H\u0086\b\u001A\u0014\u0010,\u001A\u00020-*\u00020\u00022\b\b\u0002\u0010.\u001A\u00020/\u001A%\u00100\u001A\u000201*\u00020\u00022\u0006\u00102\u001A\u0002032\u000E\b\u0004\u0010\"\u001A\b\u0012\u0004\u0012\u00020!04H\u0086\b\u001A \u00105\u001A\u000201*\u00020\u00022\u0006\u00102\u001A\u0002032\f\u0010\"\u001A\b\u0012\u0004\u0012\u00020!04\u001A\u0017\u00106\u001A\u00020!*\u00020\u00022\b\b\u0001\u00107\u001A\u00020\u0013H\u0086\b\u001A7\u00108\u001A\u00020!\"\n\b\u0000\u00109\u0018\u0001*\u00020:*\u00020\u00022\u0017\u0010;\u001A\u0013\u0012\u0004\u0012\u0002H9\u0012\u0004\u0012\u00020!0#\u00A2\u0006\u0002\b<H\u0087\b\u00A2\u0006\u0002\b=\u001A&\u00108\u001A\u00020!*\u00020\u00022\u0017\u0010;\u001A\u0013\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020!0#\u00A2\u0006\u0002\b<H\u0086\b\u001A5\u0010>\u001A\u00020!*\u00020\u00022\b\b\u0003\u0010?\u001A\u00020\u00132\b\b\u0003\u0010@\u001A\u00020\u00132\b\b\u0003\u0010A\u001A\u00020\u00132\b\b\u0003\u0010B\u001A\u00020\u0013H\u0086\b\u001A5\u0010C\u001A\u00020!*\u00020\u00022\b\b\u0003\u0010D\u001A\u00020\u00132\b\b\u0003\u0010@\u001A\u00020\u00132\b\b\u0003\u0010E\u001A\u00020\u00132\b\b\u0003\u0010B\u001A\u00020\u0013H\u0086\b\"\u001B\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00028F\u00A2\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004\"\u001B\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00060\u0001*\u00020\u00028F\u00A2\u0006\u0006\u001A\u0004\b\u0007\u0010\u0004\"*\u0010\n\u001A\u00020\t*\u00020\u00022\u0006\u0010\b\u001A\u00020\t8\u00C6\u0002@\u00C6\u0002X\u0086\u000E\u00A2\u0006\f\u001A\u0004\b\n\u0010\u000B\"\u0004\b\f\u0010\r\"*\u0010\u000E\u001A\u00020\t*\u00020\u00022\u0006\u0010\b\u001A\u00020\t8\u00C6\u0002@\u00C6\u0002X\u0086\u000E\u00A2\u0006\f\u001A\u0004\b\u000E\u0010\u000B\"\u0004\b\u000F\u0010\r\"*\u0010\u0010\u001A\u00020\t*\u00020\u00022\u0006\u0010\b\u001A\u00020\t8\u00C6\u0002@\u00C6\u0002X\u0086\u000E\u00A2\u0006\f\u001A\u0004\b\u0010\u0010\u000B\"\u0004\b\u0011\u0010\r\"\u0016\u0010\u0012\u001A\u00020\u0013*\u00020\u00028\u00C6\u0002\u00A2\u0006\u0006\u001A\u0004\b\u0014\u0010\u0015\"\u0016\u0010\u0016\u001A\u00020\u0013*\u00020\u00028\u00C6\u0002\u00A2\u0006\u0006\u001A\u0004\b\u0017\u0010\u0015\"\u0016\u0010\u0018\u001A\u00020\u0013*\u00020\u00028\u00C6\u0002\u00A2\u0006\u0006\u001A\u0004\b\u0019\u0010\u0015\"\u0016\u0010\u001A\u001A\u00020\u0013*\u00020\u00028\u00C6\u0002\u00A2\u0006\u0006\u001A\u0004\b\u001B\u0010\u0015\"\u0016\u0010\u001C\u001A\u00020\u0013*\u00020\u00028\u00C6\u0002\u00A2\u0006\u0006\u001A\u0004\b\u001D\u0010\u0015\"\u0016\u0010\u001E\u001A\u00020\u0013*\u00020\u00028\u00C6\u0002\u00A2\u0006\u0006\u001A\u0004\b\u001F\u0010\u0015\u00A8\u0006F"}, d2 = {"allViews", "Lkotlin/sequences/Sequence;", "Landroid/view/View;", "getAllViews", "(Landroid/view/View;)Lkotlin/sequences/Sequence;", "ancestors", "Landroid/view/ViewParent;", "getAncestors", "value", "", "isGone", "(Landroid/view/View;)Z", "setGone", "(Landroid/view/View;Z)V", "isInvisible", "setInvisible", "isVisible", "setVisible", "marginBottom", "", "getMarginBottom", "(Landroid/view/View;)I", "marginEnd", "getMarginEnd", "marginLeft", "getMarginLeft", "marginRight", "getMarginRight", "marginStart", "getMarginStart", "marginTop", "getMarginTop", "doOnAttach", "", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "view", "doOnDetach", "doOnLayout", "doOnNextLayout", "doOnPreDraw", "Landroidx/core/view/OneShotPreDrawListener;", "drawToBitmap", "Landroid/graphics/Bitmap;", "config", "Landroid/graphics/Bitmap$Config;", "postDelayed", "Ljava/lang/Runnable;", "delayInMillis", "", "Lkotlin/Function0;", "postOnAnimationDelayed", "setPadding", "size", "updateLayoutParams", "T", "Landroid/view/ViewGroup$LayoutParams;", "block", "Lkotlin/ExtensionFunctionType;", "updateLayoutParamsTyped", "updatePadding", "left", "top", "right", "bottom", "updatePaddingRelative", "start", "end", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ViewKt {
    public static final void doOnAttach(View view0, Function1 function10) {
        if(view0.isAttachedToWindow()) {
            function10.invoke(view0);
            return;
        }
        view0.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override  // android.view.View$OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view0) {
                view0.removeOnAttachStateChangeListener(this);
                function10.invoke(view0);
            }

            @Override  // android.view.View$OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view0) {
            }
        });
    }

    public static final void doOnDetach(View view0, Function1 function10) {
        if(!view0.isAttachedToWindow()) {
            function10.invoke(view0);
            return;
        }
        view0.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override  // android.view.View$OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view0) {
            }

            @Override  // android.view.View$OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view0) {
                view0.removeOnAttachStateChangeListener(this);
                function10.invoke(view0);
            }
        });
    }

    public static final void doOnLayout(View view0, Function1 function10) {
        if(view0.isLaidOut() && !view0.isLayoutRequested()) {
            function10.invoke(view0);
            return;
        }
        view0.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override  // android.view.View$OnLayoutChangeListener
            public void onLayoutChange(View view0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
                view0.removeOnLayoutChangeListener(this);
                function10.invoke(view0);
            }
        });
    }

    public static final void doOnNextLayout(View view0, Function1 function10) {
        view0.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override  // android.view.View$OnLayoutChangeListener
            public void onLayoutChange(View view0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
                view0.removeOnLayoutChangeListener(this);
                function10.invoke(view0);
            }
        });
    }

    public static final OneShotPreDrawListener doOnPreDraw(View view0, Function1 function10) {
        return OneShotPreDrawListener.add(view0, new Runnable() {
            @Override
            public final void run() {
                function10.invoke(view0);
            }
        });
    }

    public static final Bitmap drawToBitmap(View view0, Bitmap.Config bitmap$Config0) {
        if(!view0.isLaidOut()) {
            throw new IllegalStateException("View needs to be laid out before calling drawToBitmap()");
        }
        Bitmap bitmap0 = Bitmap.createBitmap(view0.getWidth(), view0.getHeight(), bitmap$Config0);
        Canvas canvas0 = new Canvas(bitmap0);
        canvas0.translate(-((float)view0.getScrollX()), -((float)view0.getScrollY()));
        view0.draw(canvas0);
        return bitmap0;
    }

    public static Bitmap drawToBitmap$default(View view0, Bitmap.Config bitmap$Config0, int v, Object object0) {
        if((v & 1) != 0) {
            bitmap$Config0 = Bitmap.Config.ARGB_8888;
        }
        return ViewKt.drawToBitmap(view0, bitmap$Config0);
    }

    public static final Sequence getAllViews(View view0) {
        return SequencesKt.sequence(new Function2(view0, null) {
            final View $this_allViews;
            private Object L$0;
            int label;

            {
                this.$this_allViews = view0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                androidx.core.view.ViewKt.allViews.1 viewKt$allViews$10 = new androidx.core.view.ViewKt.allViews.1(this.$this_allViews, continuation0);
                viewKt$allViews$10.L$0 = object0;
                return viewKt$allViews$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((SequenceScope)object0), ((Continuation)object1));
            }

            public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                return ((androidx.core.view.ViewKt.allViews.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                SequenceScope sequenceScope0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        sequenceScope0 = (SequenceScope)this.L$0;
                        this.L$0 = sequenceScope0;
                        this.label = 1;
                        if(sequenceScope0.yield(this.$this_allViews, this) == object1) {
                            return object1;
                        }
                        break;
                    }
                    case 1: {
                        sequenceScope0 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    case 2: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                View view0 = this.$this_allViews;
                if(view0 instanceof ViewGroup) {
                    Sequence sequence0 = ViewGroupKt.getDescendants(((ViewGroup)view0));
                    this.L$0 = null;
                    this.label = 2;
                    if(sequenceScope0.yieldAll(sequence0, this) == object1) {
                        return object1;
                    }
                }
                return Unit.INSTANCE;
            }
        });
    }

    public static final Sequence getAncestors(View view0) {
        return SequencesKt.generateSequence(view0.getParent(), androidx.core.view.ViewKt.ancestors.1.INSTANCE);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class androidx.core.view.ViewKt.ancestors.1 extends FunctionReferenceImpl implements Function1 {
            public static final androidx.core.view.ViewKt.ancestors.1 INSTANCE;

            static {
                androidx.core.view.ViewKt.ancestors.1.INSTANCE = new androidx.core.view.ViewKt.ancestors.1();
            }

            androidx.core.view.ViewKt.ancestors.1() {
                super(1, ViewParent.class, "getParent", "getParent()Landroid/view/ViewParent;", 0);
            }

            public final ViewParent invoke(ViewParent viewParent0) {
                return viewParent0.getParent();
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ViewParent)object0));
            }
        }

    }

    public static final int getMarginBottom(View view0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0) : null;
        return viewGroup$MarginLayoutParams0 == null ? 0 : viewGroup$MarginLayoutParams0.bottomMargin;
    }

    public static final int getMarginEnd(View view0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        return viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).getMarginEnd() : 0;
    }

    public static final int getMarginLeft(View view0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0) : null;
        return viewGroup$MarginLayoutParams0 == null ? 0 : viewGroup$MarginLayoutParams0.leftMargin;
    }

    public static final int getMarginRight(View view0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0) : null;
        return viewGroup$MarginLayoutParams0 == null ? 0 : viewGroup$MarginLayoutParams0.rightMargin;
    }

    public static final int getMarginStart(View view0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        return viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).getMarginStart() : 0;
    }

    public static final int getMarginTop(View view0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0) : null;
        return viewGroup$MarginLayoutParams0 == null ? 0 : viewGroup$MarginLayoutParams0.topMargin;
    }

    public static final boolean isGone(View view0) {
        return view0.getVisibility() == 8;
    }

    public static final boolean isInvisible(View view0) {
        return view0.getVisibility() == 4;
    }

    public static final boolean isVisible(View view0) {
        return view0.getVisibility() == 0;
    }

    public static final Runnable postDelayed(View view0, long v, Function0 function00) {
        Runnable runnable0 = new Runnable() {
            @Override
            public final void run() {
                function00.invoke();
            }
        };
        view0.postDelayed(runnable0, v);
        return runnable0;
    }

    public static final Runnable postOnAnimationDelayed(View view0, long v, Function0 function00) {
        Runnable runnable0 = () -> function00.invoke();
        view0.postOnAnimationDelayed(runnable0, v);
        return runnable0;
    }

    // 检测为 Lambda 实现
    private static final void postOnAnimationDelayed$lambda$1(Function0 function00) [...]

    public static final void setGone(View view0, boolean z) {
        view0.setVisibility((z ? 8 : 0));
    }

    public static final void setInvisible(View view0, boolean z) {
        view0.setVisibility((z ? 4 : 0));
    }

    public static final void setPadding(View view0, int v) {
        view0.setPadding(v, v, v, v);
    }

    public static final void setVisible(View view0, boolean z) {
        view0.setVisibility((z ? 0 : 8));
    }

    public static final void updateLayoutParams(View view0, Function1 function10) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        if(viewGroup$LayoutParams0 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
        }
        function10.invoke(viewGroup$LayoutParams0);
        view0.setLayoutParams(viewGroup$LayoutParams0);
    }

    public static final void updateLayoutParamsTyped(View view0, Function1 function10) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        Intrinsics.reifiedOperationMarker(1, "T");
        function10.invoke(viewGroup$LayoutParams0);
        view0.setLayoutParams(viewGroup$LayoutParams0);
    }

    public static final void updatePadding(View view0, int v, int v1, int v2, int v3) {
        view0.setPadding(v, v1, v2, v3);
    }

    public static void updatePadding$default(View view0, int v, int v1, int v2, int v3, int v4, Object object0) {
        if((v4 & 1) != 0) {
            v = view0.getPaddingLeft();
        }
        if((v4 & 2) != 0) {
            v1 = view0.getPaddingTop();
        }
        if((v4 & 4) != 0) {
            v2 = view0.getPaddingRight();
        }
        if((v4 & 8) != 0) {
            v3 = view0.getPaddingBottom();
        }
        view0.setPadding(v, v1, v2, v3);
    }

    public static final void updatePaddingRelative(View view0, int v, int v1, int v2, int v3) {
        view0.setPaddingRelative(v, v1, v2, v3);
    }

    public static void updatePaddingRelative$default(View view0, int v, int v1, int v2, int v3, int v4, Object object0) {
        if((v4 & 1) != 0) {
            v = view0.getPaddingStart();
        }
        if((v4 & 2) != 0) {
            v1 = view0.getPaddingTop();
        }
        if((v4 & 4) != 0) {
            v2 = view0.getPaddingEnd();
        }
        if((v4 & 8) != 0) {
            v3 = view0.getPaddingBottom();
        }
        view0.setPaddingRelative(v, v1, v2, v3);
    }
}

