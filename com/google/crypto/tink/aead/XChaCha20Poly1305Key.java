package com.google.crypto.tink.aead;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBytes;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Objects;
import javax.annotation.Nullable;

@Immutable
public final class XChaCha20Poly1305Key extends AeadKey {
    @Nullable
    private final Integer idRequirement;
    private final SecretBytes keyBytes;
    private final Bytes outputPrefix;
    private final XChaCha20Poly1305Parameters parameters;

    private XChaCha20Poly1305Key(XChaCha20Poly1305Parameters xChaCha20Poly1305Parameters0, SecretBytes secretBytes0, Bytes bytes0, @Nullable Integer integer0) {
        this.parameters = xChaCha20Poly1305Parameters0;
        this.keyBytes = secretBytes0;
        this.outputPrefix = bytes0;
        this.idRequirement = integer0;
    }

    public static XChaCha20Poly1305Key create(Variant xChaCha20Poly1305Parameters$Variant0, SecretBytes secretBytes0, @Nullable Integer integer0) throws GeneralSecurityException {
        if(xChaCha20Poly1305Parameters$Variant0 != Variant.NO_PREFIX && integer0 == null) {
            throw new GeneralSecurityException("For given Variant " + xChaCha20Poly1305Parameters$Variant0 + " the value of idRequirement must be non-null");
        }
        if(xChaCha20Poly1305Parameters$Variant0 == Variant.NO_PREFIX && integer0 != null) {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        }
        if(secretBytes0.size() != 0x20) {
            throw new GeneralSecurityException("XChaCha20Poly1305 key must be constructed with key of length 32 bytes, not " + secretBytes0.size());
        }
        XChaCha20Poly1305Parameters xChaCha20Poly1305Parameters0 = XChaCha20Poly1305Parameters.create(xChaCha20Poly1305Parameters$Variant0);
        return new XChaCha20Poly1305Key(xChaCha20Poly1305Parameters0, secretBytes0, XChaCha20Poly1305Key.getOutputPrefix(xChaCha20Poly1305Parameters0, integer0), integer0);
    }

    public static XChaCha20Poly1305Key create(SecretBytes secretBytes0) throws GeneralSecurityException {
        return XChaCha20Poly1305Key.create(Variant.NO_PREFIX, secretBytes0, null);
    }

    // 去混淆评级： 低(40)
    @Override  // com.google.crypto.tink.Key
    public boolean equalsKey(Key key0) {
        return key0 instanceof XChaCha20Poly1305Key ? ((XChaCha20Poly1305Key)key0).parameters.equals(this.parameters) && ((XChaCha20Poly1305Key)key0).keyBytes.equalsSecretBytes(this.keyBytes) && Objects.equals(((XChaCha20Poly1305Key)key0).idRequirement, this.idRequirement) : false;
    }

    @Override  // com.google.crypto.tink.Key
    @Nullable
    public Integer getIdRequirementOrNull() {
        return this.idRequirement;
    }

    public SecretBytes getKeyBytes() {
        return this.keyBytes;
    }

    private static Bytes getOutputPrefix(XChaCha20Poly1305Parameters xChaCha20Poly1305Parameters0, @Nullable Integer integer0) {
        if(xChaCha20Poly1305Parameters0.getVariant() == Variant.NO_PREFIX) {
            return Bytes.copyFrom(new byte[0]);
        }
        if(xChaCha20Poly1305Parameters0.getVariant() == Variant.CRUNCHY) {
            return Bytes.copyFrom(ByteBuffer.allocate(5).put(0).putInt(((int)integer0)).array());
        }
        if(xChaCha20Poly1305Parameters0.getVariant() != Variant.TINK) {
            throw new IllegalStateException("Unknown Variant: " + xChaCha20Poly1305Parameters0.getVariant());
        }
        return Bytes.copyFrom(ByteBuffer.allocate(5).put(1).putInt(((int)integer0)).array());
    }

    @Override  // com.google.crypto.tink.aead.AeadKey
    public Bytes getOutputPrefix() {
        return this.outputPrefix;
    }

    @Override  // com.google.crypto.tink.aead.AeadKey
    public Parameters getParameters() {
        return this.getParameters();
    }

    @Override  // com.google.crypto.tink.aead.AeadKey
    public AeadParameters getParameters() {
        return this.getParameters();
    }

    public XChaCha20Poly1305Parameters getParameters() {
        return this.parameters;
    }
}

