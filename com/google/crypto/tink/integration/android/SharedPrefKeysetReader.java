package com.google.crypto.tink.integration.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.crypto.tink.KeysetReader;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.Hex;
import java.io.CharConversionException;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class SharedPrefKeysetReader implements KeysetReader {
    private final String keysetName;
    private final SharedPreferences sharedPreferences;

    public SharedPrefKeysetReader(Context context0, String s, String s1) throws IOException {
        if(s == null) {
            throw new IllegalArgumentException("keysetName cannot be null");
        }
        this.keysetName = s;
        Context context1 = context0.getApplicationContext();
        if(s1 == null) {
            this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context1);
            return;
        }
        this.sharedPreferences = context1.getSharedPreferences(s1, 0);
    }

    @Override  // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        return Keyset.parseFrom(this.readPref(), ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        return EncryptedKeyset.parseFrom(this.readPref(), ExtensionRegistryLite.getEmptyRegistry());
    }

    private byte[] readPref() throws IOException {
        try {
            String s = this.sharedPreferences.getString(this.keysetName, null);
            if(s == null) {
                throw new FileNotFoundException(String.format("can\'t read keyset; the pref value %s does not exist", this.keysetName));
            }
            return Hex.decode(s);
        }
        catch(ClassCastException | IllegalArgumentException unused_ex) {
            throw new CharConversionException(String.format("can\'t read keyset; the pref value %s is not a valid hex string", this.keysetName));
        }
    }
}

