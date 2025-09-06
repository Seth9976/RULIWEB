package androidx.core.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001A\u0080\u0002\u0010\u0000\u001A\u00020\u0001*\u00020\u00022d\b\u0006\u0010\u0003\u001A^\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000B\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u00042d\b\u0006\u0010\u000E\u001A^\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000F\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000B\u0012\u0004\u0012\u00020\r0\u00042%\b\u0006\u0010\u0010\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010\u0012\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\r0\u0011H\u0086\b\u001A4\u0010\u0013\u001A\u00020\u0001*\u00020\u00022%\b\u0004\u0010\u0014\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010\u0012\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\r0\u0011H\u0086\b\u001As\u0010\u0015\u001A\u00020\u0001*\u00020\u00022d\b\u0004\u0010\u0014\u001A^\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000B\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\u0004H\u0086\b\u001As\u0010\u0016\u001A\u00020\u0001*\u00020\u00022d\b\u0004\u0010\u0014\u001A^\u0012\u0015\u0012\u0013\u0018\u00010\u0005\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000F\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000B\u0012\u0004\u0012\u00020\r0\u0004H\u0086\b\u00A8\u0006\u0017"}, d2 = {"addTextChangedListener", "Landroid/text/TextWatcher;", "Landroid/widget/TextView;", "beforeTextChanged", "Lkotlin/Function4;", "", "Lkotlin/ParameterName;", "name", "text", "", "start", "count", "after", "", "onTextChanged", "before", "afterTextChanged", "Lkotlin/Function1;", "Landroid/text/Editable;", "doAfterTextChanged", "action", "doBeforeTextChanged", "doOnTextChanged", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class TextViewKt {
    public static final TextWatcher addTextChangedListener(TextView textView0, Function4 function40, Function4 function41, Function1 function10) {
        TextWatcher textWatcher0 = new TextWatcher() {
            @Override  // android.text.TextWatcher
            public void afterTextChanged(Editable editable0) {
                function10.invoke(editable0);
            }

            @Override  // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
                function40.invoke(charSequence0, v, v1, v2);
            }

            @Override  // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
                function41.invoke(charSequence0, v, v1, v2);
            }
        };
        textView0.addTextChangedListener(textWatcher0);
        return textWatcher0;
    }

    public static TextWatcher addTextChangedListener$default(TextView textView0, Function4 function40, Function4 function41, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            function40 = androidx.core.widget.TextViewKt.addTextChangedListener.1.INSTANCE;
        }
        if((v & 2) != 0) {
            function41 = androidx.core.widget.TextViewKt.addTextChangedListener.2.INSTANCE;
        }
        if((v & 4) != 0) {
            function10 = androidx.core.widget.TextViewKt.addTextChangedListener.3.INSTANCE;
        }
        TextWatcher textWatcher0 = new androidx.core.widget.TextViewKt.addTextChangedListener.textWatcher.1(function10, function40, function41);
        textView0.addTextChangedListener(textWatcher0);
        return textWatcher0;

        @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001A\u00020\u00012\b\u0010\u0002\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00052\u0006\u0010\u0007\u001A\u00020\u0005H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "<anonymous parameter 1>", "", "<anonymous parameter 2>", "<anonymous parameter 3>", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0xB0)
        public final class androidx.core.widget.TextViewKt.addTextChangedListener.1 extends Lambda implements Function4 {
            public static final androidx.core.widget.TextViewKt.addTextChangedListener.1 INSTANCE;

            static {
                androidx.core.widget.TextViewKt.addTextChangedListener.1.INSTANCE = new androidx.core.widget.TextViewKt.addTextChangedListener.1();
            }

            public androidx.core.widget.TextViewKt.addTextChangedListener.1() {
                super(4);
            }

            @Override  // kotlin.jvm.functions.Function4
            public Object invoke(Object object0, Object object1, Object object2, Object object3) {
                return Unit.INSTANCE;
            }

            public final void invoke(CharSequence charSequence0, int v, int v1, int v2) {
            }
        }


        @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001A\u00020\u00012\b\u0010\u0002\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00052\u0006\u0010\u0007\u001A\u00020\u0005H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "<anonymous parameter 1>", "", "<anonymous parameter 2>", "<anonymous parameter 3>", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0xB0)
        public final class androidx.core.widget.TextViewKt.addTextChangedListener.2 extends Lambda implements Function4 {
            public static final androidx.core.widget.TextViewKt.addTextChangedListener.2 INSTANCE;

            static {
                androidx.core.widget.TextViewKt.addTextChangedListener.2.INSTANCE = new androidx.core.widget.TextViewKt.addTextChangedListener.2();
            }

            public androidx.core.widget.TextViewKt.addTextChangedListener.2() {
                super(4);
            }

            @Override  // kotlin.jvm.functions.Function4
            public Object invoke(Object object0, Object object1, Object object2, Object object3) {
                return Unit.INSTANCE;
            }

            public final void invoke(CharSequence charSequence0, int v, int v1, int v2) {
            }
        }


        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/text/Editable;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0xB0)
        public final class androidx.core.widget.TextViewKt.addTextChangedListener.3 extends Lambda implements Function1 {
            public static final androidx.core.widget.TextViewKt.addTextChangedListener.3 INSTANCE;

            static {
                androidx.core.widget.TextViewKt.addTextChangedListener.3.INSTANCE = new androidx.core.widget.TextViewKt.addTextChangedListener.3();
            }

            public androidx.core.widget.TextViewKt.addTextChangedListener.3() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return Unit.INSTANCE;
            }

            public final void invoke(Editable editable0) {
            }
        }

    }

    public static final TextWatcher doAfterTextChanged(TextView textView0, Function1 function10) {
        TextWatcher textWatcher0 = new TextWatcher() {
            @Override  // android.text.TextWatcher
            public void afterTextChanged(Editable editable0) {
                function10.invoke(editable0);
            }

            @Override  // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
            }

            @Override  // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
            }
        };
        textView0.addTextChangedListener(textWatcher0);
        return textWatcher0;
    }

    public static final TextWatcher doBeforeTextChanged(TextView textView0, Function4 function40) {
        TextWatcher textWatcher0 = new TextWatcher() {
            @Override  // android.text.TextWatcher
            public void afterTextChanged(Editable editable0) {
            }

            @Override  // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
                function40.invoke(charSequence0, v, v1, v2);
            }

            @Override  // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
            }
        };
        textView0.addTextChangedListener(textWatcher0);
        return textWatcher0;
    }

    public static final TextWatcher doOnTextChanged(TextView textView0, Function4 function40) {
        TextWatcher textWatcher0 = new TextWatcher() {
            @Override  // android.text.TextWatcher
            public void afterTextChanged(Editable editable0) {
            }

            @Override  // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
            }

            @Override  // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
                function40.invoke(charSequence0, v, v1, v2);
            }
        };
        textView0.addTextChangedListener(textWatcher0);
        return textWatcher0;
    }
}

