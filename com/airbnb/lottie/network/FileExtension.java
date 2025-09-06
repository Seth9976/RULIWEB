package com.airbnb.lottie.network;

import com.airbnb.lottie.utils.Logger;

public enum FileExtension {
    JSON(".json"),
    ZIP(".zip");

    public final String extension;

    private FileExtension(String s1) {
        this.extension = s1;
    }

    public static FileExtension forFile(String s) {
        FileExtension[] arr_fileExtension = FileExtension.values();
        for(int v = 0; v < arr_fileExtension.length; ++v) {
            FileExtension fileExtension0 = arr_fileExtension[v];
            if(s.endsWith(fileExtension0.extension)) {
                return fileExtension0;
            }
        }
        Logger.warning(("Unable to find correct extension for " + s));
        return FileExtension.JSON;
    }

    public String tempExtension() {
        return ".temp" + this.extension;
    }

    @Override
    public String toString() {
        return this.extension;
    }
}

