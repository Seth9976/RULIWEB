package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.daead.DeterministicAeadConfig;
import com.google.crypto.tink.hybrid.subtle.AeadOrDaead;
import com.google.crypto.tink.proto.AesCtrHmacAeadKey;
import com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormat;
import com.google.crypto.tink.proto.AesCtrKey;
import com.google.crypto.tink.proto.AesGcmKey.Builder;
import com.google.crypto.tink.proto.AesGcmKey;
import com.google.crypto.tink.proto.AesGcmKeyFormat;
import com.google.crypto.tink.proto.AesSivKey;
import com.google.crypto.tink.proto.AesSivKeyFormat;
import com.google.crypto.tink.proto.HmacKey;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EciesAeadHkdfDemHelper;
import java.security.GeneralSecurityException;
import java.util.Arrays;

class RegistryEciesAeadHkdfDemHelper implements EciesAeadHkdfDemHelper {
    private AesCtrHmacAeadKey aesCtrHmacAeadKey;
    private int aesCtrKeySize;
    private AesGcmKey aesGcmKey;
    private AesSivKey aesSivKey;
    private final String demKeyTypeUrl;
    private final int symmetricKeySize;

    RegistryEciesAeadHkdfDemHelper(KeyTemplate keyTemplate0) throws GeneralSecurityException {
        this.demKeyTypeUrl = "";
        if("".equals(AeadConfig.AES_GCM_TYPE_URL)) {
            try {
                AesGcmKeyFormat aesGcmKeyFormat0 = AesGcmKeyFormat.parseFrom(keyTemplate0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                this.aesGcmKey = (AesGcmKey)Registry.newKey(keyTemplate0);
                this.symmetricKeySize = aesGcmKeyFormat0.getKeySize();
                return;
            }
            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", invalidProtocolBufferException0);
            }
        }
        if("".equals(AeadConfig.AES_CTR_HMAC_AEAD_TYPE_URL)) {
            try {
                AesCtrHmacAeadKeyFormat aesCtrHmacAeadKeyFormat0 = AesCtrHmacAeadKeyFormat.parseFrom(keyTemplate0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                this.aesCtrHmacAeadKey = (AesCtrHmacAeadKey)Registry.newKey(keyTemplate0);
                this.aesCtrKeySize = aesCtrHmacAeadKeyFormat0.getAesCtrKeyFormat().getKeySize();
                int v = aesCtrHmacAeadKeyFormat0.getHmacKeyFormat().getKeySize();
                this.symmetricKeySize = this.aesCtrKeySize + v;
                return;
            }
            catch(InvalidProtocolBufferException invalidProtocolBufferException1) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", invalidProtocolBufferException1);
            }
        }
        if("".equals(DeterministicAeadConfig.AES_SIV_TYPE_URL)) {
            try {
                AesSivKeyFormat aesSivKeyFormat0 = AesSivKeyFormat.parseFrom(keyTemplate0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                this.aesSivKey = (AesSivKey)Registry.newKey(keyTemplate0);
                this.symmetricKeySize = aesSivKeyFormat0.getKeySize();
                return;
            }
            catch(InvalidProtocolBufferException invalidProtocolBufferException2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", invalidProtocolBufferException2);
            }
        }
        throw new GeneralSecurityException("unsupported AEAD DEM key type: ");
    }

    @Override  // com.google.crypto.tink.subtle.EciesAeadHkdfDemHelper
    public AeadOrDaead getAeadOrDaead(byte[] arr_b) throws GeneralSecurityException {
        if(arr_b.length != this.getSymmetricKeySizeInBytes()) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        }
        if(this.demKeyTypeUrl.equals(AeadConfig.AES_GCM_TYPE_URL)) {
            AesGcmKey aesGcmKey0 = (AesGcmKey)((Builder)AesGcmKey.newBuilder().mergeFrom(this.aesGcmKey)).setKeyValue(ByteString.copyFrom(arr_b, 0, this.symmetricKeySize)).build();
            return new AeadOrDaead(((Aead)Registry.getPrimitive(this.demKeyTypeUrl, aesGcmKey0, Aead.class)));
        }
        if(this.demKeyTypeUrl.equals(AeadConfig.AES_CTR_HMAC_AEAD_TYPE_URL)) {
            byte[] arr_b1 = Arrays.copyOfRange(arr_b, 0, this.aesCtrKeySize);
            byte[] arr_b2 = Arrays.copyOfRange(arr_b, this.aesCtrKeySize, this.symmetricKeySize);
            AesCtrKey aesCtrKey0 = (AesCtrKey)((com.google.crypto.tink.proto.AesCtrKey.Builder)AesCtrKey.newBuilder().mergeFrom(this.aesCtrHmacAeadKey.getAesCtrKey())).setKeyValue(ByteString.copyFrom(arr_b1)).build();
            HmacKey hmacKey0 = (HmacKey)((com.google.crypto.tink.proto.HmacKey.Builder)HmacKey.newBuilder().mergeFrom(this.aesCtrHmacAeadKey.getHmacKey())).setKeyValue(ByteString.copyFrom(arr_b2)).build();
            AesCtrHmacAeadKey aesCtrHmacAeadKey0 = (AesCtrHmacAeadKey)AesCtrHmacAeadKey.newBuilder().setVersion(this.aesCtrHmacAeadKey.getVersion()).setAesCtrKey(aesCtrKey0).setHmacKey(hmacKey0).build();
            return new AeadOrDaead(((Aead)Registry.getPrimitive(this.demKeyTypeUrl, aesCtrHmacAeadKey0, Aead.class)));
        }
        if(!this.demKeyTypeUrl.equals(DeterministicAeadConfig.AES_SIV_TYPE_URL)) {
            throw new GeneralSecurityException("unknown DEM key type");
        }
        AesSivKey aesSivKey0 = (AesSivKey)((com.google.crypto.tink.proto.AesSivKey.Builder)AesSivKey.newBuilder().mergeFrom(this.aesSivKey)).setKeyValue(ByteString.copyFrom(arr_b, 0, this.symmetricKeySize)).build();
        return new AeadOrDaead(((DeterministicAead)Registry.getPrimitive(this.demKeyTypeUrl, aesSivKey0, DeterministicAead.class)));
    }

    @Override  // com.google.crypto.tink.subtle.EciesAeadHkdfDemHelper
    public int getSymmetricKeySizeInBytes() {
        return this.symmetricKeySize;
    }
}

