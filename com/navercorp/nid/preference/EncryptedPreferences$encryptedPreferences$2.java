package com.navercorp.nid.preference;

import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/content/SharedPreferences;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 0x30)
final class EncryptedPreferences.encryptedPreferences.2 extends Lambda implements Function0 {
    public static final EncryptedPreferences.encryptedPreferences.2 INSTANCE;

    static {
        EncryptedPreferences.encryptedPreferences.2.INSTANCE = new EncryptedPreferences.encryptedPreferences.2();
    }

    EncryptedPreferences.encryptedPreferences.2() {
        super(0);
    }

    public final SharedPreferences invoke() {
        return EncryptedPreferences.access$init(EncryptedPreferences.INSTANCE);
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }
}

