package androidx.profileinstaller;

class WritableFileSection {
    final byte[] mContents;
    final int mExpectedInflateSize;
    final boolean mNeedsCompression;
    final FileSectionType mType;

    WritableFileSection(FileSectionType fileSectionType0, int v, byte[] arr_b, boolean z) {
        this.mType = fileSectionType0;
        this.mExpectedInflateSize = v;
        this.mContents = arr_b;
        this.mNeedsCompression = z;
    }
}

