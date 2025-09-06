package com.google.crypto.tink.integration.android;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.google.crypto.tink.KeysetWriter;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.subtle.Hex;
import java.io.IOException;

public final class SharedPrefKeysetWriter implements KeysetWriter {
    private final SharedPreferences.Editor editor;
    private final String keysetName;

    public SharedPrefKeysetWriter(Context context0, String s, String s1) {
        if(s == null) {
            throw new IllegalArgumentException("keysetName cannot be null");
        }
        this.keysetName = s;
        Context context1 = context0.getApplicationContext();
        if(s1 == null) {
            this.editor = PreferenceManager.getDefaultSharedPreferences(context1).edit();
            return;
        }
        this.editor = context1.getSharedPreferences(s1, 0).edit();
    }

    @Override  // com.google.crypto.tink.KeysetWriter
    public void write(EncryptedKeyset encryptedKeyset0) throws IOException {
        String s = Hex.encode(encryptedKeyset0.toByteArray());
        if(!this.editor.putString(this.keysetName, s).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }

    @Override  // com.google.crypto.tink.KeysetWriter
    public void write(Keyset keyset0) throws IOException {
        String s = Hex.encode(keyset0.toByteArray());
        if(!this.editor.putString(this.keysetName, s).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }
}

