package androidx.webkit.internal;

import java.net.URLConnection;

class MimeUtil {
    public static String getMimeFromFileName(String s) {
        if(s == null) {
            return null;
        }
        String s1 = URLConnection.guessContentTypeFromName(s);
        return s1 == null ? MimeUtil.guessHardcodedMime(s) : s1;
    }

    private static String guessHardcodedMime(String s) {
        int v = s.lastIndexOf(46);
        if(v == -1) {
            return null;
        }
        String s1 = s.substring(v + 1).toLowerCase();
        s1.hashCode();
        switch(s1) {
            case "apng": {
                return "image/apng";
            }
            case "bmp": {
                return "image/bmp";
            }
            case "css": {
                return "text/css";
            }
            case "ehtml": {
                return "text/html";
            }
            case "flac": {
                return "audio/flac";
            }
            case "gif": {
                return "image/gif";
            }
            case "gz": {
                return "application/gzip";
            }
            case "htm": {
                return "text/html";
            }
            case "html": {
                return "text/html";
            }
            case "ico": {
                return "image/x-icon";
            }
            case "jfif": {
                return "image/jpeg";
            }
            case "jpeg": {
                return "image/jpeg";
            }
            case "jpg": {
                return "image/jpeg";
            }
            case "js": {
                return "text/javascript";
            }
            case "json": {
                return "application/json";
            }
            case "m4a": {
                return "audio/x-m4a";
            }
            case "m4v": {
                return "video/mp4";
            }
            case "mht": {
                return "multipart/related";
            }
            case "mhtml": {
                return "multipart/related";
            }
            case "mjs": {
                return "text/javascript";
            }
            case "mp3": {
                return "audio/mpeg";
            }
            case "mp4": {
                return "video/mp4";
            }
            case "mpeg": {
                return "video/mpeg";
            }
            case "mpg": {
                return "video/mpeg";
            }
            case "oga": {
                return "audio/ogg";
            }
            case "ogg": {
                return "audio/ogg";
            }
            case "ogm": {
                return "video/ogg";
            }
            case "ogv": {
                return "video/ogg";
            }
            case "opus": {
                return "audio/ogg";
            }
            case "pdf": {
                return "application/pdf";
            }
            case "pjp": {
                return "image/jpeg";
            }
            case "pjpeg": {
                return "image/jpeg";
            }
            case "png": {
                return "image/png";
            }
            case "shtm": {
                return "text/html";
            }
            case "shtml": {
                return "text/html";
            }
            case "svg": {
                return "image/svg+xml";
            }
            case "svgz": {
                return "image/svg+xml";
            }
            case "tgz": {
                return "application/gzip";
            }
            case "tif": {
                return "image/tiff";
            }
            case "tiff": {
                return "image/tiff";
            }
            case "wasm": {
                return "application/wasm";
            }
            case "wav": {
                return "audio/wav";
            }
            case "webm": {
                return "video/webm";
            }
            case "webp": {
                return "image/webp";
            }
            case "woff": {
                return "application/font-woff";
            }
            case "xht": {
                return "application/xhtml+xml";
            }
            case "xhtm": {
                return "application/xhtml+xml";
            }
            case "xhtml": {
                return "application/xhtml+xml";
            }
            case "xml": {
                return "text/xml";
            }
            case "zip": {
                return "application/zip";
            }
            default: {
                return null;
            }
        }
    }
}

