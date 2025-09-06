package androidx.security.crypto;

import android.content.Context;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.integration.android.AndroidKeysetManager;
import com.google.crypto.tink.streamingaead.StreamingAeadConfig;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public final class EncryptedFile {
    public static final class Builder {
        final Context mContext;
        File mFile;
        final FileEncryptionScheme mFileEncryptionScheme;
        String mKeysetAlias;
        String mKeysetPrefName;
        final String mMasterKeyAlias;
        private static final Object sLock;

        static {
            Builder.sLock = new Object();
        }

        public Builder(Context context0, File file0, MasterKey masterKey0, FileEncryptionScheme encryptedFile$FileEncryptionScheme0) {
            this.mKeysetPrefName = "__androidx_security_crypto_encrypted_file_pref__";
            this.mKeysetAlias = "__androidx_security_crypto_encrypted_file_keyset__";
            this.mFile = file0;
            this.mFileEncryptionScheme = encryptedFile$FileEncryptionScheme0;
            this.mContext = context0.getApplicationContext();
            this.mMasterKeyAlias = masterKey0.getKeyAlias();
        }

        @Deprecated
        public Builder(File file0, Context context0, String s, FileEncryptionScheme encryptedFile$FileEncryptionScheme0) {
            this.mKeysetPrefName = "__androidx_security_crypto_encrypted_file_pref__";
            this.mKeysetAlias = "__androidx_security_crypto_encrypted_file_keyset__";
            this.mFile = file0;
            this.mFileEncryptionScheme = encryptedFile$FileEncryptionScheme0;
            this.mContext = context0.getApplicationContext();
            this.mMasterKeyAlias = s;
        }

        public EncryptedFile build() throws GeneralSecurityException, IOException {
            AndroidKeysetManager androidKeysetManager0;
            StreamingAeadConfig.register();
            com.google.crypto.tink.integration.android.AndroidKeysetManager.Builder androidKeysetManager$Builder0 = new com.google.crypto.tink.integration.android.AndroidKeysetManager.Builder().withKeyTemplate(this.mFileEncryptionScheme.getKeyTemplate()).withSharedPref(this.mContext, this.mKeysetAlias, this.mKeysetPrefName).withMasterKeyUri("android-keystore://" + this.mMasterKeyAlias);
            synchronized(Builder.sLock) {
                androidKeysetManager0 = androidKeysetManager$Builder0.build();
            }
            StreamingAead streamingAead0 = (StreamingAead)androidKeysetManager0.getKeysetHandle().getPrimitive(StreamingAead.class);
            return new EncryptedFile(this.mFile, this.mKeysetAlias, streamingAead0, this.mContext);
        }

        public Builder setKeysetAlias(String s) {
            this.mKeysetAlias = s;
            return this;
        }

        public Builder setKeysetPrefName(String s) {
            this.mKeysetPrefName = s;
            return this;
        }
    }

    static final class EncryptedFileInputStream extends FileInputStream implements AutoCloseable {
        private final InputStream mEncryptedInputStream;
        private final Object mLock;

        EncryptedFileInputStream(FileDescriptor fileDescriptor0, InputStream inputStream0) {
            super(fileDescriptor0);
            this.mLock = new Object();
            this.mEncryptedInputStream = inputStream0;
        }

        @Override
        public int available() throws IOException {
            return this.mEncryptedInputStream.available();
        }

        @Override
        public void close() throws IOException {
            this.mEncryptedInputStream.close();
        }

        @Override
        public FileChannel getChannel() {
            throw new UnsupportedOperationException("For encrypted files, please open the relevant FileInput/FileOutputStream.");
        }

        @Override
        public void mark(int v) {
            synchronized(this.mLock) {
                this.mEncryptedInputStream.mark(v);
            }
        }

        @Override
        public boolean markSupported() {
            return this.mEncryptedInputStream.markSupported();
        }

        @Override
        public int read() throws IOException {
            return this.mEncryptedInputStream.read();
        }

        @Override
        public int read(byte[] arr_b) throws IOException {
            return this.mEncryptedInputStream.read(arr_b);
        }

        @Override
        public int read(byte[] arr_b, int v, int v1) throws IOException {
            return this.mEncryptedInputStream.read(arr_b, v, v1);
        }

        @Override
        public void reset() throws IOException {
            synchronized(this.mLock) {
                this.mEncryptedInputStream.reset();
            }
        }

        @Override
        public long skip(long v) throws IOException {
            return this.mEncryptedInputStream.skip(v);
        }
    }

    static final class EncryptedFileOutputStream extends FileOutputStream implements AutoCloseable {
        private final OutputStream mEncryptedOutputStream;

        EncryptedFileOutputStream(FileDescriptor fileDescriptor0, OutputStream outputStream0) {
            super(fileDescriptor0);
            this.mEncryptedOutputStream = outputStream0;
        }

        @Override
        public void close() throws IOException {
            this.mEncryptedOutputStream.close();
        }

        @Override
        public void flush() throws IOException {
            this.mEncryptedOutputStream.flush();
        }

        @Override
        public FileChannel getChannel() {
            throw new UnsupportedOperationException("For encrypted files, please open the relevant FileInput/FileOutputStream.");
        }

        @Override
        public void write(int v) throws IOException {
            this.mEncryptedOutputStream.write(v);
        }

        @Override
        public void write(byte[] arr_b) throws IOException {
            this.mEncryptedOutputStream.write(arr_b);
        }

        @Override
        public void write(byte[] arr_b, int v, int v1) throws IOException {
            this.mEncryptedOutputStream.write(arr_b, v, v1);
        }
    }

    public static enum FileEncryptionScheme {
        AES256_GCM_HKDF_4KB("AES256_GCM_HKDF_4KB");

        private final String mKeyTemplateName;

        private static FileEncryptionScheme[] $values() [...] // Inlined contents

        private FileEncryptionScheme(String s1) {
            this.mKeyTemplateName = s1;
        }

        KeyTemplate getKeyTemplate() throws GeneralSecurityException {
            return KeyTemplates.get(this.mKeyTemplateName);
        }
    }

    private static final String KEYSET_ALIAS = "__androidx_security_crypto_encrypted_file_keyset__";
    private static final String KEYSET_PREF_NAME = "__androidx_security_crypto_encrypted_file_pref__";
    final Context mContext;
    final File mFile;
    final String mMasterKeyAlias;
    final StreamingAead mStreamingAead;

    EncryptedFile(File file0, String s, StreamingAead streamingAead0, Context context0) {
        this.mFile = file0;
        this.mContext = context0;
        this.mMasterKeyAlias = s;
        this.mStreamingAead = streamingAead0;
    }

    public FileInputStream openFileInput() throws GeneralSecurityException, IOException, FileNotFoundException {
        if(!this.mFile.exists()) {
            throw new FileNotFoundException("file doesn\'t exist: " + this.mFile.getName());
        }
        FileInputStream fileInputStream0 = new FileInputStream(this.mFile);
        byte[] arr_b = this.mFile.getName().getBytes(StandardCharsets.UTF_8);
        InputStream inputStream0 = this.mStreamingAead.newDecryptingStream(fileInputStream0, arr_b);
        return new EncryptedFileInputStream(fileInputStream0.getFD(), inputStream0);
    }

    public FileOutputStream openFileOutput() throws GeneralSecurityException, IOException {
        if(this.mFile.exists()) {
            throw new IOException("output file already exists, please use a new file: " + this.mFile.getName());
        }
        FileOutputStream fileOutputStream0 = new FileOutputStream(this.mFile);
        byte[] arr_b = this.mFile.getName().getBytes(StandardCharsets.UTF_8);
        OutputStream outputStream0 = this.mStreamingAead.newEncryptingStream(fileOutputStream0, arr_b);
        return new EncryptedFileOutputStream(fileOutputStream0.getFD(), outputStream0);
    }
}

