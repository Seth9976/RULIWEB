package com.navercorp.nid.oauth.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.navercorp.nid.oauth.NidOAuthLogin;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000E\u001A\u00020\u0005J\u0006\u0010\u000F\u001A\u00020\u0005J\u0006\u0010\u0010\u001A\u00020\u0005J\u0006\u0010\u0011\u001A\u00020\u0012J\u0006\u0010\u0013\u001A\u00020\u0012J\u000E\u0010\u0014\u001A\u00020\u00122\u0006\u0010\u0015\u001A\u00020\u0005J\u0006\u0010\u0016\u001A\u00020\u0012R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001A\b\u0012\u0004\u0012\u00020\u00050\u000B8F¢\u0006\u0006\u001A\u0004\b\n\u0010\fR\u0017\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u00050\u000B8F¢\u0006\u0006\u001A\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/navercorp/nid/oauth/viewModel/NidOAuthBridgeViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_isShowProgress", "Landroidx/lifecycle/MutableLiveData;", "", "_isSuccessRefreshToken", "isForceDestroyed", "isLoginActivityStarted", "isRotated", "isShowProgress", "Landroidx/lifecycle/LiveData;", "()Landroidx/lifecycle/LiveData;", "isSuccessRefreshToken", "getIsForceDestroyed", "getIsLoginActivityStarted", "getIsRotated", "isNotForcedFinish", "", "refreshToken", "setIsRotated", "value", "startLoginActivity", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidOAuthBridgeViewModel extends ViewModel {
    private MutableLiveData _isShowProgress;
    private final MutableLiveData _isSuccessRefreshToken;
    private boolean isForceDestroyed;
    private boolean isLoginActivityStarted;
    private boolean isRotated;

    public NidOAuthBridgeViewModel() {
        this.isForceDestroyed = true;
        MutableLiveData mutableLiveData0 = new MutableLiveData();
        this._isShowProgress = mutableLiveData0;
        mutableLiveData0.setValue(Boolean.FALSE);
        this._isSuccessRefreshToken = new MutableLiveData();
    }

    public final boolean getIsForceDestroyed() {
        return this.isForceDestroyed;
    }

    public final boolean getIsLoginActivityStarted() {
        return this.isLoginActivityStarted;
    }

    public final boolean getIsRotated() {
        return this.isRotated;
    }

    public final void isNotForcedFinish() {
        this.isForceDestroyed = false;
    }

    public final LiveData isShowProgress() {
        return this._isShowProgress;
    }

    public final LiveData isSuccessRefreshToken() {
        return this._isSuccessRefreshToken;
    }

    public final void refreshToken() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new Function2(null) {
            int label;

            {
                NidOAuthBridgeViewModel.this = nidOAuthBridgeViewModel0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new com.navercorp.nid.oauth.viewModel.NidOAuthBridgeViewModel.refreshToken.1(NidOAuthBridgeViewModel.this, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((com.navercorp.nid.oauth.viewModel.NidOAuthBridgeViewModel.refreshToken.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        NidOAuthBridgeViewModel.this._isShowProgress.setValue(Boxing.boxBoolean(true));
                        NidOAuthLogin nidOAuthLogin0 = new NidOAuthLogin();
                        this.label = 1;
                        object0 = nidOAuthLogin0.refreshToken(this);
                        if(object0 == object1) {
                            return object1;
                        }
                        break;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                NidOAuthBridgeViewModel.this._isShowProgress.setValue(Boxing.boxBoolean(false));
                NidOAuthBridgeViewModel.this._isSuccessRefreshToken.setValue(Boxing.boxBoolean(((Boolean)object0).booleanValue()));
                return Unit.INSTANCE;
            }
        }, 3, null);
    }

    public final void setIsRotated(boolean z) {
        this.isRotated = z;
    }

    public final void startLoginActivity() {
        this.isLoginActivityStarted = true;
    }
}

