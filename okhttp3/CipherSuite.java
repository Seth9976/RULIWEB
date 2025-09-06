package okhttp3;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0006\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u000F\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0002\u001A\u00020\u0003H\u0007¢\u0006\u0002\b\u0006J\b\u0010\u0007\u001A\u00020\u0003H\u0016R\u0013\u0010\u0002\u001A\u00020\u00038\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0005¨\u0006\t"}, d2 = {"Lokhttp3/CipherSuite;", "", "javaName", "", "(Ljava/lang/String;)V", "()Ljava/lang/String;", "-deprecated_javaName", "toString", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class CipherSuite {
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b}\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0002J\u0012\u0010\u0083\u0001\u001A\u00020\u00062\u0007\u0010\u0084\u0001\u001A\u00020\u0005H\u0007J\u001C\u0010\u0085\u0001\u001A\u00020\u00062\u0007\u0010\u0084\u0001\u001A\u00020\u00052\b\u0010\u0086\u0001\u001A\u00030\u0087\u0001H\u0002J\u0012\u0010\u0088\u0001\u001A\u00020\u00052\u0007\u0010\u0084\u0001\u001A\u00020\u0005H\u0002R\u001A\u0010\u0003\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R$\u0010\u0007\u001A\u0012\u0012\u0004\u0012\u00020\u00050\bj\b\u0012\u0004\u0012\u00020\u0005`\tX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0010\u0010\f\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\r\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u000E\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u000F\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u001A\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u001B\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u001C\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u001D\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u001E\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u001F\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010 \u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010!\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\"\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010#\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010$\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010%\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010&\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\'\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010(\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010)\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010*\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010+\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010,\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010-\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010.\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010/\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u00100\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u00101\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u00102\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u00103\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u00104\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u00105\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u00106\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u00107\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u00108\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u00109\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010:\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010;\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010<\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010=\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010>\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010?\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010@\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010A\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010B\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010C\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010D\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010E\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010F\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010G\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010H\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010I\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010J\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010K\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010L\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010M\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010N\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010O\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010P\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010Q\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010R\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010S\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010T\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010U\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010V\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010W\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010X\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010Y\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010Z\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010[\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\\\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010]\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010^\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010_\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010`\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010a\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010b\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010c\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010d\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010e\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010f\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010g\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010h\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010i\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010j\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010k\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010l\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010m\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010n\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010o\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010p\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010q\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010r\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010s\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010t\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010u\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010v\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010w\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010x\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010y\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010z\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010{\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010|\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010}\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010~\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u007F\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0080\u0001\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0081\u0001\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0082\u0001\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u0089\u0001"}, d2 = {"Lokhttp3/CipherSuite$Companion;", "", "()V", "INSTANCES", "", "", "Lokhttp3/CipherSuite;", "ORDER_BY_NAME", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "getORDER_BY_NAME$okhttp", "()Ljava/util/Comparator;", "TLS_AES_128_CCM_8_SHA256", "TLS_AES_128_CCM_SHA256", "TLS_AES_128_GCM_SHA256", "TLS_AES_256_GCM_SHA384", "TLS_CHACHA20_POLY1305_SHA256", "TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", "TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA", "TLS_DHE_DSS_WITH_AES_128_CBC_SHA", "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", "TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", "TLS_DHE_DSS_WITH_AES_256_CBC_SHA", "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", "TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", "TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", "TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", "TLS_DHE_DSS_WITH_DES_CBC_SHA", "TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", "TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA", "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", "TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", "TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", "TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_DHE_RSA_WITH_DES_CBC_SHA", "TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA", "TLS_DH_anon_EXPORT_WITH_RC4_40_MD5", "TLS_DH_anon_WITH_3DES_EDE_CBC_SHA", "TLS_DH_anon_WITH_AES_128_CBC_SHA", "TLS_DH_anon_WITH_AES_128_CBC_SHA256", "TLS_DH_anon_WITH_AES_128_GCM_SHA256", "TLS_DH_anon_WITH_AES_256_CBC_SHA", "TLS_DH_anon_WITH_AES_256_CBC_SHA256", "TLS_DH_anon_WITH_AES_256_GCM_SHA384", "TLS_DH_anon_WITH_DES_CBC_SHA", "TLS_DH_anon_WITH_RC4_128_MD5", "TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_ECDHE_ECDSA_WITH_NULL_SHA", "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", "TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", "TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", "TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256", "TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", "TLS_ECDHE_RSA_WITH_NULL_SHA", "TLS_ECDHE_RSA_WITH_RC4_128_SHA", "TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", "TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", "TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", "TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", "TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", "TLS_ECDH_ECDSA_WITH_NULL_SHA", "TLS_ECDH_ECDSA_WITH_RC4_128_SHA", "TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", "TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", "TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDH_RSA_WITH_NULL_SHA", "TLS_ECDH_RSA_WITH_RC4_128_SHA", "TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", "TLS_ECDH_anon_WITH_AES_128_CBC_SHA", "TLS_ECDH_anon_WITH_AES_256_CBC_SHA", "TLS_ECDH_anon_WITH_NULL_SHA", "TLS_ECDH_anon_WITH_RC4_128_SHA", "TLS_EMPTY_RENEGOTIATION_INFO_SCSV", "TLS_FALLBACK_SCSV", "TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", "TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", "TLS_KRB5_EXPORT_WITH_RC4_40_MD5", "TLS_KRB5_EXPORT_WITH_RC4_40_SHA", "TLS_KRB5_WITH_3DES_EDE_CBC_MD5", "TLS_KRB5_WITH_3DES_EDE_CBC_SHA", "TLS_KRB5_WITH_DES_CBC_MD5", "TLS_KRB5_WITH_DES_CBC_SHA", "TLS_KRB5_WITH_RC4_128_MD5", "TLS_KRB5_WITH_RC4_128_SHA", "TLS_PSK_WITH_3DES_EDE_CBC_SHA", "TLS_PSK_WITH_AES_128_CBC_SHA", "TLS_PSK_WITH_AES_256_CBC_SHA", "TLS_PSK_WITH_RC4_128_SHA", "TLS_RSA_EXPORT_WITH_DES40_CBC_SHA", "TLS_RSA_EXPORT_WITH_RC4_40_MD5", "TLS_RSA_WITH_3DES_EDE_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA256", "TLS_RSA_WITH_AES_128_GCM_SHA256", "TLS_RSA_WITH_AES_256_CBC_SHA", "TLS_RSA_WITH_AES_256_CBC_SHA256", "TLS_RSA_WITH_AES_256_GCM_SHA384", "TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", "TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", "TLS_RSA_WITH_DES_CBC_SHA", "TLS_RSA_WITH_NULL_MD5", "TLS_RSA_WITH_NULL_SHA", "TLS_RSA_WITH_NULL_SHA256", "TLS_RSA_WITH_RC4_128_MD5", "TLS_RSA_WITH_RC4_128_SHA", "TLS_RSA_WITH_SEED_CBC_SHA", "forJavaName", "javaName", "init", "value", "", "secondaryName", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final CipherSuite forJavaName(String s) {
            synchronized(this) {
                Intrinsics.checkNotNullParameter(s, "javaName");
                CipherSuite cipherSuite0 = (CipherSuite)CipherSuite.INSTANCES.get(s);
                if(cipherSuite0 == null) {
                    cipherSuite0 = (CipherSuite)CipherSuite.INSTANCES.get(this.secondaryName(s));
                    if(cipherSuite0 == null) {
                        cipherSuite0 = new CipherSuite(s, null);
                    }
                    CipherSuite.INSTANCES.put(s, cipherSuite0);
                }
                return cipherSuite0;
            }
        }

        public final Comparator getORDER_BY_NAME$okhttp() {
            return CipherSuite.ORDER_BY_NAME;
        }

        private final CipherSuite init(String s, int v) {
            CipherSuite cipherSuite0 = new CipherSuite(s, null);
            CipherSuite.INSTANCES.put(s, cipherSuite0);
            return cipherSuite0;
        }

        private final String secondaryName(String s) {
            if(StringsKt.startsWith$default(s, "TLS_", false, 2, null)) {
                String s1 = s.substring(4);
                Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).substring(startIndex)");
                return "SSL_" + s1;
            }
            if(StringsKt.startsWith$default(s, "SSL_", false, 2, null)) {
                String s2 = s.substring(4);
                Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String).substring(startIndex)");
                return "TLS_" + s2;
            }
            return s;
        }
    }

    public static final Companion Companion;
    private static final Map INSTANCES;
    private static final Comparator ORDER_BY_NAME;
    public static final CipherSuite TLS_AES_128_CCM_8_SHA256;
    public static final CipherSuite TLS_AES_128_CCM_SHA256;
    public static final CipherSuite TLS_AES_128_GCM_SHA256;
    public static final CipherSuite TLS_AES_256_GCM_SHA384;
    public static final CipherSuite TLS_CHACHA20_POLY1305_SHA256;
    public static final CipherSuite TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA;
    public static final CipherSuite TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA;
    public static final CipherSuite TLS_DHE_DSS_WITH_AES_128_CBC_SHA;
    public static final CipherSuite TLS_DHE_DSS_WITH_AES_128_CBC_SHA256;
    public static final CipherSuite TLS_DHE_DSS_WITH_AES_128_GCM_SHA256;
    public static final CipherSuite TLS_DHE_DSS_WITH_AES_256_CBC_SHA;
    public static final CipherSuite TLS_DHE_DSS_WITH_AES_256_CBC_SHA256;
    public static final CipherSuite TLS_DHE_DSS_WITH_AES_256_GCM_SHA384;
    public static final CipherSuite TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA;
    public static final CipherSuite TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA;
    public static final CipherSuite TLS_DHE_DSS_WITH_DES_CBC_SHA;
    public static final CipherSuite TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA;
    public static final CipherSuite TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA;
    public static final CipherSuite TLS_DHE_RSA_WITH_AES_128_CBC_SHA;
    public static final CipherSuite TLS_DHE_RSA_WITH_AES_128_CBC_SHA256;
    public static final CipherSuite TLS_DHE_RSA_WITH_AES_128_GCM_SHA256;
    public static final CipherSuite TLS_DHE_RSA_WITH_AES_256_CBC_SHA;
    public static final CipherSuite TLS_DHE_RSA_WITH_AES_256_CBC_SHA256;
    public static final CipherSuite TLS_DHE_RSA_WITH_AES_256_GCM_SHA384;
    public static final CipherSuite TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA;
    public static final CipherSuite TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA;
    public static final CipherSuite TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256;
    public static final CipherSuite TLS_DHE_RSA_WITH_DES_CBC_SHA;
    public static final CipherSuite TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA;
    public static final CipherSuite TLS_DH_anon_EXPORT_WITH_RC4_40_MD5;
    public static final CipherSuite TLS_DH_anon_WITH_3DES_EDE_CBC_SHA;
    public static final CipherSuite TLS_DH_anon_WITH_AES_128_CBC_SHA;
    public static final CipherSuite TLS_DH_anon_WITH_AES_128_CBC_SHA256;
    public static final CipherSuite TLS_DH_anon_WITH_AES_128_GCM_SHA256;
    public static final CipherSuite TLS_DH_anon_WITH_AES_256_CBC_SHA;
    public static final CipherSuite TLS_DH_anon_WITH_AES_256_CBC_SHA256;
    public static final CipherSuite TLS_DH_anon_WITH_AES_256_GCM_SHA384;
    public static final CipherSuite TLS_DH_anon_WITH_DES_CBC_SHA;
    public static final CipherSuite TLS_DH_anon_WITH_RC4_128_MD5;
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA;
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA;
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256;
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256;
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA;
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384;
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384;
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256;
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_NULL_SHA;
    public static final CipherSuite TLS_ECDHE_ECDSA_WITH_RC4_128_SHA;
    public static final CipherSuite TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA;
    public static final CipherSuite TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA;
    public static final CipherSuite TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256;
    public static final CipherSuite TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA;
    public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA;
    public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256;
    public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256;
    public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA;
    public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384;
    public static final CipherSuite TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384;
    public static final CipherSuite TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256;
    public static final CipherSuite TLS_ECDHE_RSA_WITH_NULL_SHA;
    public static final CipherSuite TLS_ECDHE_RSA_WITH_RC4_128_SHA;
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA;
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA;
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256;
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256;
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA;
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384;
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384;
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_NULL_SHA;
    public static final CipherSuite TLS_ECDH_ECDSA_WITH_RC4_128_SHA;
    public static final CipherSuite TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA;
    public static final CipherSuite TLS_ECDH_RSA_WITH_AES_128_CBC_SHA;
    public static final CipherSuite TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256;
    public static final CipherSuite TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256;
    public static final CipherSuite TLS_ECDH_RSA_WITH_AES_256_CBC_SHA;
    public static final CipherSuite TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384;
    public static final CipherSuite TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384;
    public static final CipherSuite TLS_ECDH_RSA_WITH_NULL_SHA;
    public static final CipherSuite TLS_ECDH_RSA_WITH_RC4_128_SHA;
    public static final CipherSuite TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA;
    public static final CipherSuite TLS_ECDH_anon_WITH_AES_128_CBC_SHA;
    public static final CipherSuite TLS_ECDH_anon_WITH_AES_256_CBC_SHA;
    public static final CipherSuite TLS_ECDH_anon_WITH_NULL_SHA;
    public static final CipherSuite TLS_ECDH_anon_WITH_RC4_128_SHA;
    public static final CipherSuite TLS_EMPTY_RENEGOTIATION_INFO_SCSV;
    public static final CipherSuite TLS_FALLBACK_SCSV;
    public static final CipherSuite TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5;
    public static final CipherSuite TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA;
    public static final CipherSuite TLS_KRB5_EXPORT_WITH_RC4_40_MD5;
    public static final CipherSuite TLS_KRB5_EXPORT_WITH_RC4_40_SHA;
    public static final CipherSuite TLS_KRB5_WITH_3DES_EDE_CBC_MD5;
    public static final CipherSuite TLS_KRB5_WITH_3DES_EDE_CBC_SHA;
    public static final CipherSuite TLS_KRB5_WITH_DES_CBC_MD5;
    public static final CipherSuite TLS_KRB5_WITH_DES_CBC_SHA;
    public static final CipherSuite TLS_KRB5_WITH_RC4_128_MD5;
    public static final CipherSuite TLS_KRB5_WITH_RC4_128_SHA;
    public static final CipherSuite TLS_PSK_WITH_3DES_EDE_CBC_SHA;
    public static final CipherSuite TLS_PSK_WITH_AES_128_CBC_SHA;
    public static final CipherSuite TLS_PSK_WITH_AES_256_CBC_SHA;
    public static final CipherSuite TLS_PSK_WITH_RC4_128_SHA;
    public static final CipherSuite TLS_RSA_EXPORT_WITH_DES40_CBC_SHA;
    public static final CipherSuite TLS_RSA_EXPORT_WITH_RC4_40_MD5;
    public static final CipherSuite TLS_RSA_WITH_3DES_EDE_CBC_SHA;
    public static final CipherSuite TLS_RSA_WITH_AES_128_CBC_SHA;
    public static final CipherSuite TLS_RSA_WITH_AES_128_CBC_SHA256;
    public static final CipherSuite TLS_RSA_WITH_AES_128_GCM_SHA256;
    public static final CipherSuite TLS_RSA_WITH_AES_256_CBC_SHA;
    public static final CipherSuite TLS_RSA_WITH_AES_256_CBC_SHA256;
    public static final CipherSuite TLS_RSA_WITH_AES_256_GCM_SHA384;
    public static final CipherSuite TLS_RSA_WITH_CAMELLIA_128_CBC_SHA;
    public static final CipherSuite TLS_RSA_WITH_CAMELLIA_256_CBC_SHA;
    public static final CipherSuite TLS_RSA_WITH_DES_CBC_SHA;
    public static final CipherSuite TLS_RSA_WITH_NULL_MD5;
    public static final CipherSuite TLS_RSA_WITH_NULL_SHA;
    public static final CipherSuite TLS_RSA_WITH_NULL_SHA256;
    public static final CipherSuite TLS_RSA_WITH_RC4_128_MD5;
    public static final CipherSuite TLS_RSA_WITH_RC4_128_SHA;
    public static final CipherSuite TLS_RSA_WITH_SEED_CBC_SHA;
    private final String javaName;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "javaName", imports = {}))
    public final String -deprecated_javaName() {
        return this.javaName;
    }

    static {
        Companion cipherSuite$Companion0 = new Companion(null);
        CipherSuite.Companion = cipherSuite$Companion0;
        CipherSuite.ORDER_BY_NAME = new CipherSuite.Companion.ORDER_BY_NAME.1();
        CipherSuite.INSTANCES = new LinkedHashMap();
        CipherSuite.TLS_RSA_WITH_NULL_MD5 = cipherSuite$Companion0.init("SSL_RSA_WITH_NULL_MD5", 1);
        CipherSuite.TLS_RSA_WITH_NULL_SHA = cipherSuite$Companion0.init("SSL_RSA_WITH_NULL_SHA", 2);
        CipherSuite.TLS_RSA_EXPORT_WITH_RC4_40_MD5 = cipherSuite$Companion0.init("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);
        CipherSuite.TLS_RSA_WITH_RC4_128_MD5 = cipherSuite$Companion0.init("SSL_RSA_WITH_RC4_128_MD5", 4);
        CipherSuite.TLS_RSA_WITH_RC4_128_SHA = cipherSuite$Companion0.init("SSL_RSA_WITH_RC4_128_SHA", 5);
        CipherSuite.TLS_RSA_EXPORT_WITH_DES40_CBC_SHA = cipherSuite$Companion0.init("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);
        CipherSuite.TLS_RSA_WITH_DES_CBC_SHA = cipherSuite$Companion0.init("SSL_RSA_WITH_DES_CBC_SHA", 9);
        CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA = cipherSuite$Companion0.init("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);
        CipherSuite.TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA = cipherSuite$Companion0.init("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);
        CipherSuite.TLS_DHE_DSS_WITH_DES_CBC_SHA = cipherSuite$Companion0.init("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);
        CipherSuite.TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA = cipherSuite$Companion0.init("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
        CipherSuite.TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA = cipherSuite$Companion0.init("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);
        CipherSuite.TLS_DHE_RSA_WITH_DES_CBC_SHA = cipherSuite$Companion0.init("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);
        CipherSuite.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA = cipherSuite$Companion0.init("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
        CipherSuite.TLS_DH_anon_EXPORT_WITH_RC4_40_MD5 = cipherSuite$Companion0.init("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);
        CipherSuite.TLS_DH_anon_WITH_RC4_128_MD5 = cipherSuite$Companion0.init("SSL_DH_anon_WITH_RC4_128_MD5", 24);
        CipherSuite.TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA = cipherSuite$Companion0.init("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);
        CipherSuite.TLS_DH_anon_WITH_DES_CBC_SHA = cipherSuite$Companion0.init("SSL_DH_anon_WITH_DES_CBC_SHA", 26);
        CipherSuite.TLS_DH_anon_WITH_3DES_EDE_CBC_SHA = cipherSuite$Companion0.init("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);
        CipherSuite.TLS_KRB5_WITH_DES_CBC_SHA = cipherSuite$Companion0.init("TLS_KRB5_WITH_DES_CBC_SHA", 30);
        CipherSuite.TLS_KRB5_WITH_3DES_EDE_CBC_SHA = cipherSuite$Companion0.init("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 0x1F);
        CipherSuite.TLS_KRB5_WITH_RC4_128_SHA = cipherSuite$Companion0.init("TLS_KRB5_WITH_RC4_128_SHA", 0x20);
        CipherSuite.TLS_KRB5_WITH_DES_CBC_MD5 = cipherSuite$Companion0.init("TLS_KRB5_WITH_DES_CBC_MD5", 34);
        CipherSuite.TLS_KRB5_WITH_3DES_EDE_CBC_MD5 = cipherSuite$Companion0.init("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);
        CipherSuite.TLS_KRB5_WITH_RC4_128_MD5 = cipherSuite$Companion0.init("TLS_KRB5_WITH_RC4_128_MD5", 36);
        CipherSuite.TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA = cipherSuite$Companion0.init("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);
        CipherSuite.TLS_KRB5_EXPORT_WITH_RC4_40_SHA = cipherSuite$Companion0.init("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);
        CipherSuite.TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5 = cipherSuite$Companion0.init("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);
        CipherSuite.TLS_KRB5_EXPORT_WITH_RC4_40_MD5 = cipherSuite$Companion0.init("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);
        CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA = cipherSuite$Companion0.init("TLS_RSA_WITH_AES_128_CBC_SHA", 0x2F);
        CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA = cipherSuite$Companion0.init("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
        CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA = cipherSuite$Companion0.init("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
        CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA = cipherSuite$Companion0.init("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);
        CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA = cipherSuite$Companion0.init("TLS_RSA_WITH_AES_256_CBC_SHA", 53);
        CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA = cipherSuite$Companion0.init("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
        CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA = cipherSuite$Companion0.init("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
        CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA = cipherSuite$Companion0.init("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);
        CipherSuite.TLS_RSA_WITH_NULL_SHA256 = cipherSuite$Companion0.init("TLS_RSA_WITH_NULL_SHA256", 59);
        CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA256 = cipherSuite$Companion0.init("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
        CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA256 = cipherSuite$Companion0.init("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
        CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA256 = cipherSuite$Companion0.init("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 0x40);
        CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA = cipherSuite$Companion0.init("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
        CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA = cipherSuite$Companion0.init("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
        CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA = cipherSuite$Companion0.init("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
        CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA256 = cipherSuite$Companion0.init("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
        CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA256 = cipherSuite$Companion0.init("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
        CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256 = cipherSuite$Companion0.init("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
        CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256 = cipherSuite$Companion0.init("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);
        CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA256 = cipherSuite$Companion0.init("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);
        CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA = cipherSuite$Companion0.init("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", 0x84);
        CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA = cipherSuite$Companion0.init("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 0x87);
        CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA = cipherSuite$Companion0.init("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 0x88);
        CipherSuite.TLS_PSK_WITH_RC4_128_SHA = cipherSuite$Companion0.init("TLS_PSK_WITH_RC4_128_SHA", 0x8A);
        CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA = cipherSuite$Companion0.init("TLS_PSK_WITH_3DES_EDE_CBC_SHA", 0x8B);
        CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA = cipherSuite$Companion0.init("TLS_PSK_WITH_AES_128_CBC_SHA", 140);
        CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA = cipherSuite$Companion0.init("TLS_PSK_WITH_AES_256_CBC_SHA", 0x8D);
        CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA = cipherSuite$Companion0.init("TLS_RSA_WITH_SEED_CBC_SHA", 150);
        CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256 = cipherSuite$Companion0.init("TLS_RSA_WITH_AES_128_GCM_SHA256", 0x9C);
        CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384 = cipherSuite$Companion0.init("TLS_RSA_WITH_AES_256_GCM_SHA384", 0x9D);
        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256 = cipherSuite$Companion0.init("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 0x9E);
        CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 = cipherSuite$Companion0.init("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 0x9F);
        CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 = cipherSuite$Companion0.init("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", 0xA2);
        CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 = cipherSuite$Companion0.init("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", 0xA3);
        CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256 = cipherSuite$Companion0.init("TLS_DH_anon_WITH_AES_128_GCM_SHA256", 0xA6);
        CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384 = cipherSuite$Companion0.init("TLS_DH_anon_WITH_AES_256_GCM_SHA384", 0xA7);
        CipherSuite.TLS_EMPTY_RENEGOTIATION_INFO_SCSV = cipherSuite$Companion0.init("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 0xFF);
        CipherSuite.TLS_FALLBACK_SCSV = cipherSuite$Companion0.init("TLS_FALLBACK_SCSV", 0x5600);
        CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA = cipherSuite$Companion0.init("TLS_ECDH_ECDSA_WITH_NULL_SHA", 0xC001);
        CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA = cipherSuite$Companion0.init("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", 0xC002);
        CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", 0xC003);
        CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", 0xC004);
        CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", 0xC005);
        CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA = cipherSuite$Companion0.init("TLS_ECDHE_ECDSA_WITH_NULL_SHA", 0xC006);
        CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA = cipherSuite$Companion0.init("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", 0xC007);
        CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", 0xC008);
        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", 0xC009);
        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", 0xC00A);
        CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA = cipherSuite$Companion0.init("TLS_ECDH_RSA_WITH_NULL_SHA", 0xC00B);
        CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA = cipherSuite$Companion0.init("TLS_ECDH_RSA_WITH_RC4_128_SHA", 0xC00C);
        CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", 0xC00D);
        CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", 0xC00E);
        CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", 0xC00F);
        CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA = cipherSuite$Companion0.init("TLS_ECDHE_RSA_WITH_NULL_SHA", 0xC010);
        CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA = cipherSuite$Companion0.init("TLS_ECDHE_RSA_WITH_RC4_128_SHA", 0xC011);
        CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", 0xC012);
        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", 0xC013);
        CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", 0xC014);
        CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA = cipherSuite$Companion0.init("TLS_ECDH_anon_WITH_NULL_SHA", 0xC015);
        CipherSuite.TLS_ECDH_anon_WITH_RC4_128_SHA = cipherSuite$Companion0.init("TLS_ECDH_anon_WITH_RC4_128_SHA", 0xC016);
        CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", 0xC017);
        CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", 0xC018);
        CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", 0xC019);
        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 = cipherSuite$Companion0.init("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", 0xC023);
        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 = cipherSuite$Companion0.init("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", 0xC024);
        CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 = cipherSuite$Companion0.init("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", 0xC025);
        CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 = cipherSuite$Companion0.init("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", 0xC026);
        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 = cipherSuite$Companion0.init("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", 0xC027);
        CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 = cipherSuite$Companion0.init("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", 0xC028);
        CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 = cipherSuite$Companion0.init("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", 0xC029);
        CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 = cipherSuite$Companion0.init("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", 0xC02A);
        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 = cipherSuite$Companion0.init("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", 0xC02B);
        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 = cipherSuite$Companion0.init("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", 0xC02C);
        CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 = cipherSuite$Companion0.init("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", 0xC02D);
        CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 = cipherSuite$Companion0.init("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", 0xC02E);
        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 = cipherSuite$Companion0.init("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", 0xC02F);
        CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 = cipherSuite$Companion0.init("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", 0xC030);
        CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 = cipherSuite$Companion0.init("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", 0xC031);
        CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 = cipherSuite$Companion0.init("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", 0xC032);
        CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", 0xC035);
        CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA = cipherSuite$Companion0.init("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", 0xC036);
        CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 = cipherSuite$Companion0.init("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 0xCCA8);
        CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 = cipherSuite$Companion0.init("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 0xCCA9);
        CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 = cipherSuite$Companion0.init("TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 0xCCAA);
        CipherSuite.TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256 = cipherSuite$Companion0.init("TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256", 0xCCAC);
        CipherSuite.TLS_AES_128_GCM_SHA256 = cipherSuite$Companion0.init("TLS_AES_128_GCM_SHA256", 0x1301);
        CipherSuite.TLS_AES_256_GCM_SHA384 = cipherSuite$Companion0.init("TLS_AES_256_GCM_SHA384", 0x1302);
        CipherSuite.TLS_CHACHA20_POLY1305_SHA256 = cipherSuite$Companion0.init("TLS_CHACHA20_POLY1305_SHA256", 0x1303);
        CipherSuite.TLS_AES_128_CCM_SHA256 = cipherSuite$Companion0.init("TLS_AES_128_CCM_SHA256", 0x1304);
        CipherSuite.TLS_AES_128_CCM_8_SHA256 = cipherSuite$Companion0.init("TLS_AES_128_CCM_8_SHA256", 0x1305);
    }

    private CipherSuite(String s) {
        this.javaName = s;
    }

    public CipherSuite(String s, DefaultConstructorMarker defaultConstructorMarker0) {
        this(s);
    }

    @JvmStatic
    public static final CipherSuite forJavaName(String s) {
        synchronized(CipherSuite.class) {
            return CipherSuite.Companion.forJavaName(s);
        }
    }

    public final String javaName() {
        return this.javaName;
    }

    @Override
    public String toString() {
        return this.javaName;
    }
}

