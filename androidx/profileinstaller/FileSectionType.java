package androidx.profileinstaller;

enum FileSectionType {
    DEX_FILES(0L),
    EXTRA_DESCRIPTORS(1L),
    CLASSES(2L),
    METHODS(3L),
    AGGREGATION_COUNT(4L);

    private final long mValue;

    private static FileSectionType[] $values() [...] // Inlined contents

    private FileSectionType(long v1) {
        this.mValue = v1;
    }

    static FileSectionType fromValue(long v) {
        FileSectionType[] arr_fileSectionType = FileSectionType.values();
        for(int v1 = 0; v1 < arr_fileSectionType.length; ++v1) {
            if(arr_fileSectionType[v1].getValue() == v) {
                return arr_fileSectionType[v1];
            }
        }
        throw new IllegalArgumentException("Unsupported FileSection Type " + v);
    }

    public long getValue() {
        return this.mValue;
    }
}

