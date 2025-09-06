package okhttp3;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal._HostnamesCommonKt;
import okhttp3.internal._HostnamesJvmKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import okio.Buffer;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u000E\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 J2\u00020\u0001:\u0002IJBa\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0003\u0012\u0006\u0010\u0006\u001A\u00020\u0003\u0012\u0006\u0010\u0007\u001A\u00020\b\u0012\f\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0010\u0010\u000B\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\n\u0012\b\u0010\f\u001A\u0004\u0018\u00010\u0003\u0012\u0006\u0010\r\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u000EJ\u000F\u0010\u000F\u001A\u0004\u0018\u00010\u0003H\u0007\u00A2\u0006\u0002\b!J\r\u0010\u0011\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b\"J\r\u0010\u0012\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b#J\u0013\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00030\nH\u0007\u00A2\u0006\u0002\b$J\u000F\u0010\u0015\u001A\u0004\u0018\u00010\u0003H\u0007\u00A2\u0006\u0002\b%J\r\u0010\u0016\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b&J\u0013\u0010\'\u001A\u00020\u00182\b\u0010(\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\u000F\u0010\f\u001A\u0004\u0018\u00010\u0003H\u0007\u00A2\u0006\u0002\b)J\b\u0010*\u001A\u00020\bH\u0016J\r\u0010\u0006\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b+J\u0006\u0010,\u001A\u00020-J\u0010\u0010,\u001A\u0004\u0018\u00010-2\u0006\u0010.\u001A\u00020\u0003J\r\u0010\u0005\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b/J\u0013\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u00030\nH\u0007\u00A2\u0006\u0002\b0J\r\u0010\u001A\u001A\u00020\bH\u0007\u00A2\u0006\u0002\b1J\r\u0010\u0007\u001A\u00020\bH\u0007\u00A2\u0006\u0002\b2J\u000F\u0010\u001C\u001A\u0004\u0018\u00010\u0003H\u0007\u00A2\u0006\u0002\b3J\u0010\u00104\u001A\u0004\u0018\u00010\u00032\u0006\u00105\u001A\u00020\u0003J\u000E\u00106\u001A\u00020\u00032\u0006\u00107\u001A\u00020\bJ\u0013\u0010\u001D\u001A\b\u0012\u0004\u0012\u00020\u00030\u001EH\u0007\u00A2\u0006\u0002\b8J\u0010\u00109\u001A\u0004\u0018\u00010\u00032\u0006\u00107\u001A\u00020\bJ\u0016\u0010:\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\n2\u0006\u00105\u001A\u00020\u0003J\r\u0010 \u001A\u00020\bH\u0007\u00A2\u0006\u0002\b;J\u0006\u0010<\u001A\u00020\u0003J\u0010\u0010=\u001A\u0004\u0018\u00010\u00002\u0006\u0010.\u001A\u00020\u0003J\r\u0010\u0002\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b>J\b\u0010?\u001A\u00020\u0003H\u0016J\r\u0010@\u001A\u00020AH\u0007\u00A2\u0006\u0002\bBJ\r\u0010C\u001A\u00020DH\u0007\u00A2\u0006\u0002\b\rJ\b\u0010E\u001A\u0004\u0018\u00010\u0003J\r\u0010B\u001A\u00020AH\u0007\u00A2\u0006\u0002\bFJ\r\u0010\r\u001A\u00020DH\u0007\u00A2\u0006\u0002\bGJ\r\u0010\u0004\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\bHR\u0013\u0010\u000F\u001A\u0004\u0018\u00010\u00038G\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\u0011\u001A\u00020\u00038G\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0012\u001A\u00020\u00038G\u00A2\u0006\u0006\u001A\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00030\n8G\u00A2\u0006\u0006\u001A\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001A\u0004\u0018\u00010\u00038G\u00A2\u0006\u0006\u001A\u0004\b\u0015\u0010\u0010R\u0011\u0010\u0016\u001A\u00020\u00038G\u00A2\u0006\u0006\u001A\u0004\b\u0016\u0010\u0010R\u0015\u0010\f\u001A\u0004\u0018\u00010\u00038\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\f\u0010\u0010R\u0013\u0010\u0006\u001A\u00020\u00038\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0010R\u0011\u0010\u0017\u001A\u00020\u0018\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0019R\u0013\u0010\u0005\u001A\u00020\u00038\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0010R\u0019\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u00030\n8\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\t\u0010\u0014R\u0011\u0010\u001A\u001A\u00020\b8G\u00A2\u0006\u0006\u001A\u0004\b\u001A\u0010\u001BR\u0013\u0010\u0007\u001A\u00020\b8\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\u001BR\u0013\u0010\u001C\u001A\u0004\u0018\u00010\u00038G\u00A2\u0006\u0006\u001A\u0004\b\u001C\u0010\u0010R\u0018\u0010\u000B\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\nX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0017\u0010\u001D\u001A\b\u0012\u0004\u0012\u00020\u00030\u001E8G\u00A2\u0006\u0006\u001A\u0004\b\u001D\u0010\u001FR\u0011\u0010 \u001A\u00020\b8G\u00A2\u0006\u0006\u001A\u0004\b \u0010\u001BR\u0013\u0010\u0002\u001A\u00020\u00038\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0010R\u000E\u0010\r\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001A\u00020\u00038\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u0010\u00A8\u0006K"}, d2 = {"Lokhttp3/HttpUrl;", "", "scheme", "", "username", "password", "host", "port", "", "pathSegments", "", "queryNamesAndValues", "fragment", "url", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "encodedFragment", "()Ljava/lang/String;", "encodedPassword", "encodedPath", "encodedPathSegments", "()Ljava/util/List;", "encodedQuery", "encodedUsername", "isHttps", "", "()Z", "pathSize", "()I", "query", "queryParameterNames", "", "()Ljava/util/Set;", "querySize", "-deprecated_encodedFragment", "-deprecated_encodedPassword", "-deprecated_encodedPath", "-deprecated_encodedPathSegments", "-deprecated_encodedQuery", "-deprecated_encodedUsername", "equals", "other", "-deprecated_fragment", "hashCode", "-deprecated_host", "newBuilder", "Lokhttp3/HttpUrl$Builder;", "link", "-deprecated_password", "-deprecated_pathSegments", "-deprecated_pathSize", "-deprecated_port", "-deprecated_query", "queryParameter", "name", "queryParameterName", "index", "-deprecated_queryParameterNames", "queryParameterValue", "queryParameterValues", "-deprecated_querySize", "redact", "resolve", "-deprecated_scheme", "toString", "toUri", "Ljava/net/URI;", "uri", "toUrl", "Ljava/net/URL;", "topPrivateDomain", "-deprecated_uri", "-deprecated_url", "-deprecated_username", "Builder", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class HttpUrl {
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0017\u0018\u0000 V2\u00020\u0001:\u0001VB\u0005\u00A2\u0006\u0002\u0010\u0002J\u000E\u0010#\u001A\u00020\u00002\u0006\u0010$\u001A\u00020\u0004J\u000E\u0010%\u001A\u00020\u00002\u0006\u0010\f\u001A\u00020\u0004J\u0018\u0010&\u001A\u00020\u00002\u0006\u0010\'\u001A\u00020\u00042\b\u0010(\u001A\u0004\u0018\u00010\u0004J\u000E\u0010)\u001A\u00020\u00002\u0006\u0010*\u001A\u00020\u0004J\u000E\u0010+\u001A\u00020\u00002\u0006\u0010,\u001A\u00020\u0004J\u0018\u0010+\u001A\u00020\u00002\u0006\u0010,\u001A\u00020\u00042\u0006\u0010-\u001A\u00020.H\u0002J\u0018\u0010/\u001A\u00020\u00002\u0006\u00100\u001A\u00020\u00042\b\u00101\u001A\u0004\u0018\u00010\u0004J\u0006\u00102\u001A\u000203J\b\u00104\u001A\u00020\u001BH\u0002J\u0010\u0010\u0003\u001A\u00020\u00002\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004J\u000E\u0010\t\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0004J\u000E\u00105\u001A\u00020\u00002\u0006\u00105\u001A\u00020\u0004J\u0010\u00106\u001A\u00020\u00002\b\u00106\u001A\u0004\u0018\u00010\u0004J\u000E\u0010\u0014\u001A\u00020\u00002\u0006\u0010\u0014\u001A\u00020\u0004J\u0010\u00107\u001A\u00020\u00002\b\u00107\u001A\u0004\u0018\u00010\u0004J\u000E\u0010\u0017\u001A\u00020\u00002\u0006\u0010\u0017\u001A\u00020\u0004J\u0010\u00108\u001A\u00020.2\u0006\u00109\u001A\u00020\u0004H\u0002J\u0010\u0010:\u001A\u00020.2\u0006\u00109\u001A\u00020\u0004H\u0002J\u001F\u0010;\u001A\u00020\u00002\b\u0010<\u001A\u0004\u0018\u0001032\u0006\u00109\u001A\u00020\u0004H\u0000\u00A2\u0006\u0002\b=J\u000E\u0010>\u001A\u00020\u00002\u0006\u0010>\u001A\u00020\u0004J\b\u0010?\u001A\u00020@H\u0002J\u000E\u0010\u001A\u001A\u00020\u00002\u0006\u0010\u001A\u001A\u00020\u001BJ0\u0010A\u001A\u00020@2\u0006\u00109\u001A\u00020\u00042\u0006\u0010B\u001A\u00020\u001B2\u0006\u0010C\u001A\u00020\u001B2\u0006\u0010D\u001A\u00020.2\u0006\u0010-\u001A\u00020.H\u0002J\u0010\u0010E\u001A\u00020\u00002\b\u0010E\u001A\u0004\u0018\u00010\u0004J\r\u0010F\u001A\u00020\u0000H\u0000\u00A2\u0006\u0002\bGJ\u0010\u0010H\u001A\u00020@2\u0006\u0010I\u001A\u00020\u0004H\u0002J\u000E\u0010J\u001A\u00020\u00002\u0006\u0010\'\u001A\u00020\u0004J\u000E\u0010K\u001A\u00020\u00002\u0006\u00100\u001A\u00020\u0004J\u000E\u0010L\u001A\u00020\u00002\u0006\u0010M\u001A\u00020\u001BJ \u0010N\u001A\u00020@2\u0006\u00109\u001A\u00020\u00042\u0006\u0010O\u001A\u00020\u001B2\u0006\u0010C\u001A\u00020\u001BH\u0002J\u000E\u0010 \u001A\u00020\u00002\u0006\u0010 \u001A\u00020\u0004J\u0016\u0010P\u001A\u00020\u00002\u0006\u0010M\u001A\u00020\u001B2\u0006\u0010$\u001A\u00020\u0004J\u0018\u0010Q\u001A\u00020\u00002\u0006\u0010\'\u001A\u00020\u00042\b\u0010(\u001A\u0004\u0018\u00010\u0004J\u0016\u0010R\u001A\u00020\u00002\u0006\u0010M\u001A\u00020\u001B2\u0006\u0010*\u001A\u00020\u0004J\u0018\u0010S\u001A\u00020\u00002\u0006\u00100\u001A\u00020\u00042\b\u00101\u001A\u0004\u0018\u00010\u0004J\b\u0010T\u001A\u00020\u0004H\u0016J\u000E\u0010U\u001A\u00020\u00002\u0006\u0010U\u001A\u00020\u0004R\u001C\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001A\u0010\t\u001A\u00020\u0004X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\n\u0010\u0006\"\u0004\b\u000B\u0010\bR\u001A\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u00040\rX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR$\u0010\u0010\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\rX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0011\u0010\u000F\"\u0004\b\u0012\u0010\u0013R\u001A\u0010\u0014\u001A\u00020\u0004X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\bR\u001C\u0010\u0017\u001A\u0004\u0018\u00010\u0004X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0018\u0010\u0006\"\u0004\b\u0019\u0010\bR\u001A\u0010\u001A\u001A\u00020\u001BX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001C\u0010\u001D\"\u0004\b\u001E\u0010\u001FR\u001C\u0010 \u001A\u0004\u0018\u00010\u0004X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\b\u00A8\u0006W"}, d2 = {"Lokhttp3/HttpUrl$Builder;", "", "()V", "encodedFragment", "", "getEncodedFragment$okhttp", "()Ljava/lang/String;", "setEncodedFragment$okhttp", "(Ljava/lang/String;)V", "encodedPassword", "getEncodedPassword$okhttp", "setEncodedPassword$okhttp", "encodedPathSegments", "", "getEncodedPathSegments$okhttp", "()Ljava/util/List;", "encodedQueryNamesAndValues", "getEncodedQueryNamesAndValues$okhttp", "setEncodedQueryNamesAndValues$okhttp", "(Ljava/util/List;)V", "encodedUsername", "getEncodedUsername$okhttp", "setEncodedUsername$okhttp", "host", "getHost$okhttp", "setHost$okhttp", "port", "", "getPort$okhttp", "()I", "setPort$okhttp", "(I)V", "scheme", "getScheme$okhttp", "setScheme$okhttp", "addEncodedPathSegment", "encodedPathSegment", "addEncodedPathSegments", "addEncodedQueryParameter", "encodedName", "encodedValue", "addPathSegment", "pathSegment", "addPathSegments", "pathSegments", "alreadyEncoded", "", "addQueryParameter", "name", "value", "build", "Lokhttp3/HttpUrl;", "effectivePort", "encodedPath", "encodedQuery", "fragment", "isDot", "input", "isDotDot", "parse", "base", "parse$okhttp", "password", "pop", "", "push", "pos", "limit", "addTrailingSlash", "query", "reencodeForUri", "reencodeForUri$okhttp", "removeAllCanonicalQueryParameters", "canonicalName", "removeAllEncodedQueryParameters", "removeAllQueryParameters", "removePathSegment", "index", "resolvePath", "startPos", "setEncodedPathSegment", "setEncodedQueryParameter", "setPathSegment", "setQueryParameter", "toString", "username", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Builder {
        @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\u00062\u0006\u0010\t\u001A\u00020\u0006H\u0002J \u0010\n\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\u00062\u0006\u0010\t\u001A\u00020\u0006H\u0002J \u0010\u000B\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\u00062\u0006\u0010\t\u001A\u00020\u0006H\u0002J\u001C\u0010\f\u001A\u00020\u0006*\u00020\u00042\u0006\u0010\b\u001A\u00020\u00062\u0006\u0010\t\u001A\u00020\u0006H\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/HttpUrl$Builder$Companion;", "", "()V", "INVALID_HOST", "", "parsePort", "", "input", "pos", "limit", "portColonOffset", "schemeDelimiterOffset", "slashCount", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
        public static final class Companion {
            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            private final int parsePort(String s, int v, int v1) {
                try {
                    int v2 = Integer.parseInt(okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, v, v1, "", false, false, false, false, null, 0xF8, null));
                    if(1 <= v2 && v2 < 0x10000) {
                        return v2;
                    }
                }
                catch(NumberFormatException unused_ex) {
                }
                return -1;
            }

            private final int portColonOffset(String s, int v, int v1) {
                while(v < v1) {
                    int v2 = s.charAt(v);
                    if(v2 == 91) {
                        do {
                            ++v;
                        }
                        while(v < v1 && s.charAt(v) != 93);
                    }
                    else if(v2 == 58) {
                        return v;
                    }
                    ++v;
                }
                return v1;
            }

            private final int schemeDelimiterOffset(String s, int v, int v1) {
                if(v1 - v < 2) {
                    return -1;
                }
                int v2 = s.charAt(v);
                if(Intrinsics.compare(v2, 97) >= 0 && Intrinsics.compare(v2, 0x7A) <= 0 || Intrinsics.compare(v2, 65) >= 0 && Intrinsics.compare(v2, 90) <= 0) {
                    while(true) {
                        ++v;
                        if(v >= v1) {
                            break;
                        }
                        int v3 = s.charAt(v);
                        if((97 > v3 || v3 >= 0x7B) && (65 > v3 || v3 >= 91) && (0x30 > v3 || v3 >= 58)) {
                            switch(v3) {
                                case 43: 
                                case 45: 
                                case 46: {
                                    break;
                                }
                                case 58: {
                                    return v;
                                }
                                default: {
                                    return -1;
                                }
                            }
                        }
                    }
                }
                return -1;
            }

            private final int slashCount(String s, int v, int v1) {
                int v2 = 0;
                while(v < v1) {
                    switch(s.charAt(v)) {
                        case 0x2F: 
                        case 92: {
                            ++v2;
                            ++v;
                            continue;
                        }
                        default: {
                            return v2;
                        }
                    }
                }
                return v2;
            }
        }

        public static final Companion Companion = null;
        public static final String INVALID_HOST = "Invalid URL host";
        private String encodedFragment;
        private String encodedPassword;
        private final List encodedPathSegments;
        private List encodedQueryNamesAndValues;
        private String encodedUsername;
        private String host;
        private int port;
        private String scheme;

        static {
            Builder.Companion = new Companion(null);
        }

        public Builder() {
            this.encodedUsername = "";
            this.encodedPassword = "";
            this.port = -1;
            List list0 = new ArrayList();
            this.encodedPathSegments = list0;
            list0.add("");
        }

        public final Builder addEncodedPathSegment(String s) {
            Intrinsics.checkNotNullParameter(s, "encodedPathSegment");
            this.push(s, 0, s.length(), false, true);
            return this;
        }

        public final Builder addEncodedPathSegments(String s) {
            Intrinsics.checkNotNullParameter(s, "encodedPathSegments");
            return this.addPathSegments(s, true);
        }

        public final Builder addEncodedQueryParameter(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "encodedName");
            if(this.encodedQueryNamesAndValues == null) {
                this.encodedQueryNamesAndValues = new ArrayList();
            }
            List list0 = this.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list0);
            list0.add(okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " \"\'<>#&=", true, false, true, false, null, 0xD3, null));
            List list1 = this.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list1);
            list1.add((s1 == null ? null : okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s1, 0, 0, " \"\'<>#&=", true, false, true, false, null, 0xD3, null)));
            return this;
        }

        public final Builder addPathSegment(String s) {
            Intrinsics.checkNotNullParameter(s, "pathSegment");
            this.push(s, 0, s.length(), false, false);
            return this;
        }

        private final Builder addPathSegments(String s, boolean z) {
            int v = 0;
            do {
                int v1 = _UtilCommonKt.delimiterOffset(s, "/\\", v, s.length());
                this.push(s, v, v1, v1 < s.length(), z);
                v = v1 + 1;
            }
            while(v <= s.length());
            return this;
        }

        public final Builder addPathSegments(String s) {
            Intrinsics.checkNotNullParameter(s, "pathSegments");
            return this.addPathSegments(s, false);
        }

        public final Builder addQueryParameter(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            if(this.encodedQueryNamesAndValues == null) {
                this.encodedQueryNamesAndValues = new ArrayList();
            }
            List list0 = this.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list0);
            list0.add(okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " !\"#$&\'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 0xDB, null));
            List list1 = this.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list1);
            list1.add((s1 == null ? null : okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s1, 0, 0, " !\"#$&\'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 0xDB, null)));
            return this;
        }

        public final HttpUrl build() {
            List list1;
            String s = this.scheme;
            if(s == null) {
                throw new IllegalStateException("scheme == null");
            }
            String s1 = okhttp3.HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, this.encodedUsername, 0, 0, false, 7, null);
            String s2 = okhttp3.HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, this.encodedPassword, 0, 0, false, 7, null);
            String s3 = this.host;
            if(s3 == null) {
                throw new IllegalStateException("host == null");
            }
            int v = this.effectivePort();
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.encodedPathSegments, 10));
            for(Object object0: this.encodedPathSegments) {
                arrayList0.add(okhttp3.HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, ((String)object0), 0, 0, false, 7, null));
            }
            List list0 = this.encodedQueryNamesAndValues;
            String s4 = null;
            if(list0 == null) {
                list1 = null;
            }
            else {
                ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                for(Object object1: list0) {
                    String s5 = (String)object1;
                    arrayList1.add((s5 == null ? null : okhttp3.HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, s5, 0, 0, true, 3, null)));
                }
                list1 = arrayList1;
            }
            String s6 = this.encodedFragment;
            if(s6 != null) {
                s4 = okhttp3.HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, s6, 0, 0, false, 7, null);
            }
            return new HttpUrl(s, s1, s2, s3, v, arrayList0, list1, s4, "///");
        }

        private final int effectivePort() {
            int v = this.port;
            if(v != -1) {
                return v;
            }
            String s = this.scheme;
            Intrinsics.checkNotNull(s);
            return HttpUrl.Companion.defaultPort(s);
        }

        public final Builder encodedFragment(String s) {
            this.encodedFragment = s == null ? null : okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, "", true, false, false, true, null, 0xB3, null);
            return this;
        }

        public final Builder encodedPassword(String s) {
            Intrinsics.checkNotNullParameter(s, "encodedPassword");
            this.encodedPassword = okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " \"\':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 0xF3, null);
            return this;
        }

        public final Builder encodedPath(String s) {
            Intrinsics.checkNotNullParameter(s, "encodedPath");
            if(!StringsKt.startsWith$default(s, "/", false, 2, null)) {
                throw new IllegalArgumentException(("unexpected encodedPath: " + s).toString());
            }
            this.resolvePath(s, 0, s.length());
            return this;
        }

        public final Builder encodedQuery(String s) {
            List list0;
            if(s == null) {
                list0 = null;
            }
            else {
                String s1 = okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " \"\'<>#", true, false, true, false, null, 0xD3, null);
                list0 = s1 == null ? null : HttpUrl.Companion.toQueryNamesAndValues$okhttp(s1);
            }
            this.encodedQueryNamesAndValues = list0;
            return this;
        }

        public final Builder encodedUsername(String s) {
            Intrinsics.checkNotNullParameter(s, "encodedUsername");
            this.encodedUsername = okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " \"\':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 0xF3, null);
            return this;
        }

        public final Builder fragment(String s) {
            this.encodedFragment = s == null ? null : okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, "", false, false, false, true, null, 0xBB, null);
            return this;
        }

        public final String getEncodedFragment$okhttp() {
            return this.encodedFragment;
        }

        public final String getEncodedPassword$okhttp() {
            return this.encodedPassword;
        }

        public final List getEncodedPathSegments$okhttp() {
            return this.encodedPathSegments;
        }

        public final List getEncodedQueryNamesAndValues$okhttp() {
            return this.encodedQueryNamesAndValues;
        }

        public final String getEncodedUsername$okhttp() {
            return this.encodedUsername;
        }

        public final String getHost$okhttp() {
            return this.host;
        }

        public final int getPort$okhttp() {
            return this.port;
        }

        public final String getScheme$okhttp() {
            return this.scheme;
        }

        public final Builder host(String s) {
            Intrinsics.checkNotNullParameter(s, "host");
            String s1 = _HostnamesJvmKt.toCanonicalHost(okhttp3.HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, s, 0, 0, false, 7, null));
            if(s1 == null) {
                throw new IllegalArgumentException("unexpected host: " + s);
            }
            this.host = s1;
            return this;
        }

        // 去混淆评级： 低(20)
        private final boolean isDot(String s) {
            return Intrinsics.areEqual(s, ".") || StringsKt.equals(s, "%2e", true);
        }

        // 去混淆评级： 低(40)
        private final boolean isDotDot(String s) {
            return Intrinsics.areEqual(s, "..") || StringsKt.equals(s, "%2e.", true) || StringsKt.equals(s, ".%2e", true) || StringsKt.equals(s, "%2e%2e", true);
        }

        public final Builder parse$okhttp(HttpUrl httpUrl0, String s) {
            int v11;
            int v5;
            Intrinsics.checkNotNullParameter(s, "input");
            int v = _UtilCommonKt.indexOfFirstNonAsciiWhitespace$default(s, 0, 0, 3, null);
            int v1 = _UtilCommonKt.indexOfLastNonAsciiWhitespace$default(s, v, 0, 2, null);
            Companion httpUrl$Builder$Companion0 = Builder.Companion;
            int v2 = httpUrl$Builder$Companion0.schemeDelimiterOffset(s, v, v1);
            if(v2 != -1) {
                if(StringsKt.startsWith(s, "https:", v, true)) {
                    this.scheme = "https";
                    v += 6;
                    goto label_19;
                }
                if(StringsKt.startsWith(s, "http:", v, true)) {
                    this.scheme = "http";
                    v += 5;
                    goto label_19;
                }
                String s1 = s.substring(0, v2);
                Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
                throw new IllegalArgumentException("Expected URL scheme \'http\' or \'https\' but was \'" + s1 + '\'');
            }
            if(httpUrl0 == null) {
                throw new IllegalArgumentException("Expected URL scheme \'http\' or \'https\' but no scheme was found for " + (s.length() <= 6 ? s : StringsKt.take(s, 6) + "..."));
            }
            this.scheme = httpUrl0.scheme();
        label_19:
            int v3 = httpUrl$Builder$Companion0.slashCount(s, v, v1);
            if(v3 < 2 && httpUrl0 != null && Intrinsics.areEqual(httpUrl0.scheme(), this.scheme)) {
                this.encodedUsername = httpUrl0.encodedUsername();
                this.encodedPassword = httpUrl0.encodedPassword();
                this.host = httpUrl0.host();
                this.port = httpUrl0.port();
                this.encodedPathSegments.clear();
                Collection collection0 = httpUrl0.encodedPathSegments();
                this.encodedPathSegments.addAll(collection0);
                if(v != v1 && s.charAt(v) != 35) {
                    goto label_51;
                }
                this.encodedQuery(httpUrl0.encodedQuery());
                goto label_51;
            }
            int v4 = v + v3;
            do {
                v5 = _UtilCommonKt.delimiterOffset(s, "@/\\?#", v4, v1);
                int v6 = v5 == v1 ? -1 : s.charAt(v5);
            }
            while(v6 != -1 && v6 != 35 && v6 != 0x2F && v6 != 0x3F && v6 != 92);
            Companion httpUrl$Builder$Companion1 = Builder.Companion;
            int v7 = httpUrl$Builder$Companion1.portColonOffset(s, v4, v5);
            if(v7 + 1 < v5) {
                this.host = _HostnamesJvmKt.toCanonicalHost(okhttp3.HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, s, v4, v7, false, 4, null));
                int v8 = httpUrl$Builder$Companion1.parsePort(s, v7 + 1, v5);
                this.port = v8;
                if(v8 == -1) {
                    String s2 = s.substring(v7 + 1, v5);
                    Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String…ing(startIndex, endIndex)");
                    throw new IllegalArgumentException(("Invalid URL port: \"" + s2 + '\"').toString());
                }
            }
            else {
                this.host = _HostnamesJvmKt.toCanonicalHost(okhttp3.HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, s, v4, v7, false, 4, null));
                String s3 = this.scheme;
                Intrinsics.checkNotNull(s3);
                this.port = HttpUrl.Companion.defaultPort(s3);
            }
            if(this.host != null) {
                v = v5;
            label_51:
                int v9 = _UtilCommonKt.delimiterOffset(s, "?#", v, v1);
                this.resolvePath(s, v, v9);
                if(v9 >= v1 || s.charAt(v9) != 0x3F) {
                    v11 = v9;
                }
                else {
                    int v10 = _UtilCommonKt.delimiterOffset(s, '#', v9, v1);
                    String s4 = okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, v9 + 1, v10, " \"\'<>#", true, false, true, false, null, 0xD0, null);
                    this.encodedQueryNamesAndValues = HttpUrl.Companion.toQueryNamesAndValues$okhttp(s4);
                    v11 = v10;
                }
                if(v11 < v1 && s.charAt(v11) == 35) {
                    this.encodedFragment = okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, v11 + 1, v1, "", true, false, false, true, null, 0xB0, null);
                }
                return this;
            }
            String s5 = s.substring(v4, v7);
            Intrinsics.checkNotNullExpressionValue(s5, "this as java.lang.String…ing(startIndex, endIndex)");
            throw new IllegalArgumentException(("Invalid URL host: \"" + s5 + '\"').toString());
        }

        public final Builder password(String s) {
            Intrinsics.checkNotNullParameter(s, "password");
            this.encodedPassword = okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " \"\':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 0xFB, null);
            return this;
        }

        private final void pop() {
            if(((String)this.encodedPathSegments.remove(this.encodedPathSegments.size() - 1)).length() == 0 && !this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, "");
                return;
            }
            this.encodedPathSegments.add("");
        }

        public final Builder port(int v) {
            if(1 > v || v >= 0x10000) {
                throw new IllegalArgumentException(("unexpected port: " + v).toString());
            }
            this.port = v;
            return this;
        }

        private final void push(String s, int v, int v1, boolean z, boolean z1) {
            String s1 = okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, v, v1, " \"<>^`{}|/\\?#", z1, false, false, false, null, 0xF0, null);
            if(!this.isDot(s1)) {
                if(this.isDotDot(s1)) {
                    this.pop();
                    return;
                }
                if(((CharSequence)this.encodedPathSegments.get(this.encodedPathSegments.size() - 1)).length() == 0) {
                    this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, s1);
                }
                else {
                    this.encodedPathSegments.add(s1);
                }
                if(z) {
                    this.encodedPathSegments.add("");
                }
            }
        }

        public final Builder query(String s) {
            List list0;
            if(s == null) {
                list0 = null;
            }
            else {
                String s1 = okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " \"\'<>#", false, false, true, false, null, 0xDB, null);
                list0 = s1 == null ? null : HttpUrl.Companion.toQueryNamesAndValues$okhttp(s1);
            }
            this.encodedQueryNamesAndValues = list0;
            return this;
        }

        public final Builder reencodeForUri$okhttp() {
            String s = this.host;
            String s1 = null;
            this.host = s == null ? null : new Regex("[\"<>^`{|}]").replace(s, "");
            int v = this.encodedPathSegments.size();
            for(int v2 = 0; v2 < v; ++v2) {
                String s2 = (String)this.encodedPathSegments.get(v2);
                String s3 = okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s2, 0, 0, "[]", true, true, false, false, null, 0xE3, null);
                this.encodedPathSegments.set(v2, s3);
            }
            List list0 = this.encodedQueryNamesAndValues;
            if(list0 != null) {
                int v3 = list0.size();
                for(int v1 = 0; v1 < v3; ++v1) {
                    String s4 = (String)list0.get(v1);
                    list0.set(v1, (s4 == null ? null : okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s4, 0, 0, "\\^`{|}", true, true, true, false, null, 0xC3, null)));
                }
            }
            String s5 = this.encodedFragment;
            if(s5 != null) {
                s1 = okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s5, 0, 0, " \"#<>\\^`{|}", true, true, false, true, null, 0xA3, null);
            }
            this.encodedFragment = s1;
            return this;
        }

        private final void removeAllCanonicalQueryParameters(String s) {
            List list0 = this.encodedQueryNamesAndValues;
            Intrinsics.checkNotNull(list0);
            int v = list0.size() - 2;
            int v1 = ProgressionUtilKt.getProgressionLastElement(v, 0, -2);
            if(v1 <= v) {
                while(true) {
                    List list1 = this.encodedQueryNamesAndValues;
                    Intrinsics.checkNotNull(list1);
                    if(Intrinsics.areEqual(s, list1.get(v))) {
                        List list2 = this.encodedQueryNamesAndValues;
                        Intrinsics.checkNotNull(list2);
                        list2.remove(v + 1);
                        List list3 = this.encodedQueryNamesAndValues;
                        Intrinsics.checkNotNull(list3);
                        list3.remove(v);
                        List list4 = this.encodedQueryNamesAndValues;
                        Intrinsics.checkNotNull(list4);
                        if(list4.isEmpty()) {
                            this.encodedQueryNamesAndValues = null;
                            return;
                        }
                    }
                    if(v == v1) {
                        break;
                    }
                    v -= 2;
                }
            }
        }

        public final Builder removeAllEncodedQueryParameters(String s) {
            Intrinsics.checkNotNullParameter(s, "encodedName");
            if(this.encodedQueryNamesAndValues == null) {
                return this;
            }
            this.removeAllCanonicalQueryParameters(okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " \"\'<>#&=", true, false, true, false, null, 0xD3, null));
            return this;
        }

        public final Builder removeAllQueryParameters(String s) {
            Intrinsics.checkNotNullParameter(s, "name");
            if(this.encodedQueryNamesAndValues == null) {
                return this;
            }
            this.removeAllCanonicalQueryParameters(okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " !\"#$&\'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 0xDB, null));
            return this;
        }

        public final Builder removePathSegment(int v) {
            this.encodedPathSegments.remove(v);
            if(this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.add("");
            }
            return this;
        }

        private final void resolvePath(String s, int v, int v1) {
            if(v != v1) {
                switch(s.charAt(v)) {
                    case 0x2F: 
                    case 92: {
                        this.encodedPathSegments.clear();
                        this.encodedPathSegments.add("");
                        ++v;
                        break;
                    }
                    default: {
                        this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, "");
                    }
                }
                for(int v2 = v; v2 < v1; v2 = z ? v3 + 1 : v3) {
                    int v3 = _UtilCommonKt.delimiterOffset(s, "/\\", v2, v1);
                    boolean z = v3 < v1;
                    this.push(s, v2, v3, z, true);
                }
            }
        }

        public final Builder scheme(String s) {
            Intrinsics.checkNotNullParameter(s, "scheme");
            if(StringsKt.equals(s, "http", true)) {
                this.scheme = "http";
                return this;
            }
            if(!StringsKt.equals(s, "https", true)) {
                throw new IllegalArgumentException("unexpected scheme: " + s);
            }
            this.scheme = "https";
            return this;
        }

        public final void setEncodedFragment$okhttp(String s) {
            this.encodedFragment = s;
        }

        public final void setEncodedPassword$okhttp(String s) {
            Intrinsics.checkNotNullParameter(s, "<set-?>");
            this.encodedPassword = s;
        }

        public final Builder setEncodedPathSegment(int v, String s) {
            Intrinsics.checkNotNullParameter(s, "encodedPathSegment");
            String s1 = okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " \"<>^`{}|/\\?#", true, false, false, false, null, 0xF3, null);
            this.encodedPathSegments.set(v, s1);
            if(this.isDot(s1) || this.isDotDot(s1)) {
                throw new IllegalArgumentException(("unexpected path segment: " + s).toString());
            }
            return this;
        }

        public final void setEncodedQueryNamesAndValues$okhttp(List list0) {
            this.encodedQueryNamesAndValues = list0;
        }

        public final Builder setEncodedQueryParameter(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "encodedName");
            this.removeAllEncodedQueryParameters(s);
            this.addEncodedQueryParameter(s, s1);
            return this;
        }

        public final void setEncodedUsername$okhttp(String s) {
            Intrinsics.checkNotNullParameter(s, "<set-?>");
            this.encodedUsername = s;
        }

        public final void setHost$okhttp(String s) {
            this.host = s;
        }

        public final Builder setPathSegment(int v, String s) {
            Intrinsics.checkNotNullParameter(s, "pathSegment");
            String s1 = okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " \"<>^`{}|/\\?#", false, false, false, false, null, 0xFB, null);
            if(this.isDot(s1) || this.isDotDot(s1)) {
                throw new IllegalArgumentException(("unexpected path segment: " + s).toString());
            }
            this.encodedPathSegments.set(v, s1);
            return this;
        }

        public final void setPort$okhttp(int v) {
            this.port = v;
        }

        public final Builder setQueryParameter(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            this.removeAllQueryParameters(s);
            this.addQueryParameter(s, s1);
            return this;
        }

        public final void setScheme$okhttp(String s) {
            this.scheme = s;
        }

        @Override
        public String toString() [...] // 潜在的解密器

        public final Builder username(String s) {
            Intrinsics.checkNotNullParameter(s, "username");
            this.encodedUsername = okhttp3.HttpUrl.Companion.canonicalize$okhttp$default(HttpUrl.Companion, s, 0, 0, " \"\':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 0xFB, null);
            return this;
        }
    }

    @Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u0004H\u0007J\u0017\u0010\u0014\u001A\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001A\u00020\u0017H\u0007\u00A2\u0006\u0002\b\u0018J\u0017\u0010\u0014\u001A\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001A\u00020\u001AH\u0007\u00A2\u0006\u0002\b\u0018J\u0015\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0019\u001A\u00020\u0004H\u0007\u00A2\u0006\u0002\b\u0018J\u0017\u0010\u001B\u001A\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001A\u00020\u0004H\u0007\u00A2\u0006\u0002\b\u001CJa\u0010\u001D\u001A\u00020\u0004*\u00020\u00042\b\b\u0002\u0010\u001E\u001A\u00020\u00122\b\b\u0002\u0010\u001F\u001A\u00020\u00122\u0006\u0010 \u001A\u00020\u00042\b\b\u0002\u0010!\u001A\u00020\"2\b\b\u0002\u0010#\u001A\u00020\"2\b\b\u0002\u0010$\u001A\u00020\"2\b\b\u0002\u0010%\u001A\u00020\"2\n\b\u0002\u0010&\u001A\u0004\u0018\u00010\'H\u0000\u00A2\u0006\u0002\b(J\u001C\u0010)\u001A\u00020\"*\u00020\u00042\u0006\u0010\u001E\u001A\u00020\u00122\u0006\u0010\u001F\u001A\u00020\u0012H\u0002J/\u0010*\u001A\u00020\u0004*\u00020\u00042\b\b\u0002\u0010\u001E\u001A\u00020\u00122\b\b\u0002\u0010\u001F\u001A\u00020\u00122\b\b\u0002\u0010$\u001A\u00020\"H\u0000\u00A2\u0006\u0002\b+J\u0011\u0010,\u001A\u00020\u0015*\u00020\u0004H\u0007\u00A2\u0006\u0002\b\u0014J\u0013\u0010-\u001A\u0004\u0018\u00010\u0015*\u00020\u0017H\u0007\u00A2\u0006\u0002\b\u0014J\u0013\u0010-\u001A\u0004\u0018\u00010\u0015*\u00020\u001AH\u0007\u00A2\u0006\u0002\b\u0014J\u0013\u0010-\u001A\u0004\u0018\u00010\u0015*\u00020\u0004H\u0007\u00A2\u0006\u0002\b\u001BJ#\u0010.\u001A\u00020/*\b\u0012\u0004\u0012\u00020\u0004002\n\u00101\u001A\u000602j\u0002`3H\u0000\u00A2\u0006\u0002\b4J\u0019\u00105\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u000406*\u00020\u0004H\u0000\u00A2\u0006\u0002\b7J%\u00108\u001A\u00020/*\n\u0012\u0006\u0012\u0004\u0018\u00010\u0004002\n\u00101\u001A\u000602j\u0002`3H\u0000\u00A2\u0006\u0002\b9JV\u0010:\u001A\u00020/*\u00020;2\u0006\u0010<\u001A\u00020\u00042\u0006\u0010\u001E\u001A\u00020\u00122\u0006\u0010\u001F\u001A\u00020\u00122\u0006\u0010 \u001A\u00020\u00042\u0006\u0010!\u001A\u00020\"2\u0006\u0010#\u001A\u00020\"2\u0006\u0010$\u001A\u00020\"2\u0006\u0010%\u001A\u00020\"2\b\u0010&\u001A\u0004\u0018\u00010\'H\u0002J,\u0010=\u001A\u00020/*\u00020;2\u0006\u0010>\u001A\u00020\u00042\u0006\u0010\u001E\u001A\u00020\u00122\u0006\u0010\u001F\u001A\u00020\u00122\u0006\u0010$\u001A\u00020\"H\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0004X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0004X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0004X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0004X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u0004X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u0004X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0004X\u0080T\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0004X\u0080T\u00A2\u0006\u0002\n\u0000\u00A8\u0006?"}, d2 = {"Lokhttp3/HttpUrl$Companion;", "", "()V", "FORM_ENCODE_SET", "", "FRAGMENT_ENCODE_SET", "FRAGMENT_ENCODE_SET_URI", "HEX_DIGITS", "", "PASSWORD_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET", "PATH_SEGMENT_ENCODE_SET_URI", "QUERY_COMPONENT_ENCODE_SET", "QUERY_COMPONENT_ENCODE_SET_URI", "QUERY_COMPONENT_REENCODE_SET", "QUERY_ENCODE_SET", "USERNAME_ENCODE_SET", "defaultPort", "", "scheme", "get", "Lokhttp3/HttpUrl;", "uri", "Ljava/net/URI;", "-deprecated_get", "url", "Ljava/net/URL;", "parse", "-deprecated_parse", "canonicalize", "pos", "limit", "encodeSet", "alreadyEncoded", "", "strict", "plusIsSpace", "unicodeAllowed", "charset", "Ljava/nio/charset/Charset;", "canonicalize$okhttp", "isPercentEncoded", "percentDecode", "percentDecode$okhttp", "toHttpUrl", "toHttpUrlOrNull", "toPathString", "", "", "out", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "toPathString$okhttp", "toQueryNamesAndValues", "", "toQueryNamesAndValues$okhttp", "toQueryString", "toQueryString$okhttp", "writeCanonicalized", "Lokio/Buffer;", "input", "writePercentDecoded", "encoded", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class okhttp3.HttpUrl.Companion {
        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "url.toHttpUrl()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrl"}))
        public final HttpUrl -deprecated_get(String s) {
            Intrinsics.checkNotNullParameter(s, "url");
            return this.get(s);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "uri.toHttpUrlOrNull()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}))
        public final HttpUrl -deprecated_get(URI uRI0) {
            Intrinsics.checkNotNullParameter(uRI0, "uri");
            return this.get(uRI0);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "url.toHttpUrlOrNull()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}))
        public final HttpUrl -deprecated_get(URL uRL0) {
            Intrinsics.checkNotNullParameter(uRL0, "url");
            return this.get(uRL0);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "url.toHttpUrlOrNull()", imports = {"okhttp3.HttpUrl.Companion.toHttpUrlOrNull"}))
        public final HttpUrl -deprecated_parse(String s) {
            Intrinsics.checkNotNullParameter(s, "url");
            return this.parse(s);
        }

        private okhttp3.HttpUrl.Companion() {
        }

        public okhttp3.HttpUrl.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final String canonicalize$okhttp(String s, int v, int v1, String s1, boolean z, boolean z1, boolean z2, boolean z3, Charset charset0) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            Intrinsics.checkNotNullParameter(s1, "encodeSet");
            int v2 = v;
            while(v2 < v1) {
                int v3 = s.codePointAt(v2);
                if(v3 >= 0x20 && v3 != 0x7F && (v3 < 0x80 || z3) && !StringsKt.contains$default(s1, ((char)v3), false, 2, null) && (v3 != 37 || z && (!z1 || this.isPercentEncoded(s, v2, v1))) && (v3 != 43 || !z2)) {
                    v2 += Character.charCount(v3);
                    continue;
                }
                Buffer buffer0 = new Buffer();
                buffer0.writeUtf8(s, v, v2);
                this.writeCanonicalized(buffer0, s, v2, v1, s1, z, z1, z2, z3, charset0);
                return "";
            }
            String s2 = s.substring(v, v1);
            Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String…ing(startIndex, endIndex)");
            return s2;
        }

        public static String canonicalize$okhttp$default(okhttp3.HttpUrl.Companion httpUrl$Companion0, String s, int v, int v1, String s1, boolean z, boolean z1, boolean z2, boolean z3, Charset charset0, int v2, Object object0) {
            if((v2 & 1) != 0) {
                v = 0;
            }
            if((v2 & 2) != 0) {
                v1 = s.length();
            }
            if((v2 & 8) != 0) {
                z = false;
            }
            if((v2 & 16) != 0) {
                z1 = false;
            }
            if((v2 & 0x20) != 0) {
                z2 = false;
            }
            if((v2 & 0x40) != 0) {
                z3 = false;
            }
            if((v2 & 0x80) != 0) {
                charset0 = null;
            }
            return httpUrl$Companion0.canonicalize$okhttp(s, v, v1, s1, z, z1, z2, z3, charset0);
        }

        @JvmStatic
        public final int defaultPort(String s) {
            Intrinsics.checkNotNullParameter(s, "scheme");
            if(Intrinsics.areEqual(s, "http")) {
                return 80;
            }
            return Intrinsics.areEqual(s, "https") ? 443 : -1;
        }

        @JvmStatic
        public final HttpUrl get(String s) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            return new Builder().parse$okhttp(null, s).build();
        }

        @JvmStatic
        public final HttpUrl get(URI uRI0) {
            Intrinsics.checkNotNullParameter(uRI0, "<this>");
            String s = uRI0.toString();
            Intrinsics.checkNotNullExpressionValue(s, "toString()");
            return this.parse(s);
        }

        @JvmStatic
        public final HttpUrl get(URL uRL0) {
            Intrinsics.checkNotNullParameter(uRL0, "<this>");
            String s = uRL0.toString();
            Intrinsics.checkNotNullExpressionValue(s, "toString()");
            return this.parse(s);
        }

        private final boolean isPercentEncoded(String s, int v, int v1) {
            return v + 2 < v1 && s.charAt(v) == 37 && _UtilCommonKt.parseHexDigit(s.charAt(v + 1)) != -1 && _UtilCommonKt.parseHexDigit(s.charAt(v + 2)) != -1;
        }

        @JvmStatic
        public final HttpUrl parse(String s) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            try {
                return this.get(s);
            }
            catch(IllegalArgumentException unused_ex) {
                return null;
            }
        }

        public final String percentDecode$okhttp(String s, int v, int v1, boolean z) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            int v2 = v;
            while(v2 < v1) {
                int v3 = s.charAt(v2);
                if(v3 != 37 && (v3 != 43 || !z)) {
                    ++v2;
                    continue;
                }
                Buffer buffer0 = new Buffer();
                buffer0.writeUtf8(s, v, v2);
                this.writePercentDecoded(buffer0, s, v2, v1, z);
                return "";
            }
            String s1 = s.substring(v, v1);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
            return s1;
        }

        public static String percentDecode$okhttp$default(okhttp3.HttpUrl.Companion httpUrl$Companion0, String s, int v, int v1, boolean z, int v2, Object object0) {
            if((v2 & 1) != 0) {
                v = 0;
            }
            if((v2 & 2) != 0) {
                v1 = s.length();
            }
            if((v2 & 4) != 0) {
                z = false;
            }
            return httpUrl$Companion0.percentDecode$okhttp(s, v, v1, z);
        }

        public final void toPathString$okhttp(List list0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(list0, "<this>");
            Intrinsics.checkNotNullParameter(stringBuilder0, "out");
            int v = list0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                stringBuilder0.append('/');
                stringBuilder0.append(((String)list0.get(v1)));
            }
        }

        public final List toQueryNamesAndValues$okhttp(String s) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            List list0 = new ArrayList();
            for(int v = 0; v <= s.length(); v = v1 + 1) {
                int v1 = StringsKt.indexOf$default(s, '&', v, false, 4, null);
                if(v1 == -1) {
                    v1 = s.length();
                }
                int v2 = StringsKt.indexOf$default(s, '=', v, false, 4, null);
                if(v2 == -1 || v2 > v1) {
                    String s3 = s.substring(v, v1);
                    Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String…ing(startIndex, endIndex)");
                    list0.add(s3);
                    list0.add(null);
                }
                else {
                    String s1 = s.substring(v, v2);
                    Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
                    list0.add(s1);
                    String s2 = s.substring(v2 + 1, v1);
                    Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String…ing(startIndex, endIndex)");
                    list0.add(s2);
                }
            }
            return list0;
        }

        public final void toQueryString$okhttp(List list0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(list0, "<this>");
            Intrinsics.checkNotNullParameter(stringBuilder0, "out");
            IntProgression intProgression0 = RangesKt.step(RangesKt.until(0, list0.size()), 2);
            int v = intProgression0.getFirst();
            int v1 = intProgression0.getLast();
            int v2 = intProgression0.getStep();
            if(v2 > 0 && v <= v1 || v2 < 0 && v1 <= v) {
                while(true) {
                    String s = (String)list0.get(v);
                    String s1 = (String)list0.get(v + 1);
                    if(v > 0) {
                        stringBuilder0.append('&');
                    }
                    stringBuilder0.append(s);
                    if(s1 != null) {
                        stringBuilder0.append('=');
                        stringBuilder0.append(s1);
                    }
                    if(v == v1) {
                        break;
                    }
                    v += v2;
                }
            }
        }

        private final void writeCanonicalized(Buffer buffer0, String s, int v, int v1, String s1, boolean z, boolean z1, boolean z2, boolean z3, Charset charset0) {
            Buffer buffer1 = null;
            while(v < v1) {
                String s2 = "+";
                int v2 = s.codePointAt(v);
                if(!z || v2 != 9 && v2 != 10 && v2 != 12 && v2 != 13) {
                    if(v2 == 0x20 && s1 == " !\"#$&\'()+,/:;<=>?@[\\]^`{|}~") {
                        buffer0.writeUtf8("+");
                    }
                    else if(v2 == 43 && z2) {
                        if(!z) {
                            s2 = "%2B";
                        }
                        buffer0.writeUtf8(s2);
                    }
                    else if(v2 < 0x20 || v2 == 0x7F || v2 >= 0x80 && !z3 || StringsKt.contains$default(s1, ((char)v2), false, 2, null) || v2 == 37 && (!z || z1 && !this.isPercentEncoded(s, v, v1))) {
                        if(buffer1 == null) {
                            buffer1 = new Buffer();
                        }
                        if(charset0 == null || Intrinsics.areEqual(charset0, Charsets.UTF_8)) {
                            buffer1.writeUtf8CodePoint(v2);
                        }
                        else {
                            buffer1.writeString(s, v, Character.charCount(v2) + v, charset0);
                        }
                        while(!buffer1.exhausted()) {
                            int v3 = buffer1.readByte();
                            buffer0.writeByte(37);
                            buffer0.writeByte(((int)HttpUrl.HEX_DIGITS[(v3 & 0xFF) >> 4 & 15]));
                            buffer0.writeByte(((int)HttpUrl.HEX_DIGITS[v3 & 15]));
                        }
                    }
                    else {
                        buffer0.writeUtf8CodePoint(v2);
                    }
                }
                v += Character.charCount(v2);
            }
        }

        private final void writePercentDecoded(Buffer buffer0, String s, int v, int v1, boolean z) {
            while(v < v1) {
                int v2 = s.codePointAt(v);
                if(v2 == 37 && v + 2 < v1) {
                    int v3 = _UtilCommonKt.parseHexDigit(s.charAt(v + 1));
                    int v4 = _UtilCommonKt.parseHexDigit(s.charAt(v + 2));
                    if(v3 != -1 && v4 != -1) {
                        buffer0.writeByte((v3 << 4) + v4);
                        v += 3;
                        continue;
                    }
                }
                else if(v2 == 43 && z) {
                    buffer0.writeByte(0x20);
                    ++v;
                    continue;
                }
                buffer0.writeUtf8CodePoint(v2);
                v += Character.charCount(v2);
            }
        }
    }

    public static final okhttp3.HttpUrl.Companion Companion = null;
    public static final String FORM_ENCODE_SET = " !\"#$&\'()+,/:;<=>?@[\\]^`{|}~";
    public static final String FRAGMENT_ENCODE_SET = "";
    public static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
    private static final char[] HEX_DIGITS = null;
    public static final String PASSWORD_ENCODE_SET = " \"\':;<=>@[]^`{}|/\\?#";
    public static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
    public static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";
    public static final String QUERY_COMPONENT_ENCODE_SET = " !\"#$&\'(),/:;<=>?@[]\\^`{|}~";
    public static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";
    public static final String QUERY_COMPONENT_REENCODE_SET = " \"\'<>#&=";
    public static final String QUERY_ENCODE_SET = " \"\'<>#";
    public static final String USERNAME_ENCODE_SET = " \"\':;<=>@[]^`{}|/\\?#";
    private final String fragment;
    private final String host;
    private final boolean isHttps;
    private final String password;
    private final List pathSegments;
    private final int port;
    private final List queryNamesAndValues;
    private final String scheme;
    private final String url;
    private final String username;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedFragment", imports = {}))
    public final String -deprecated_encodedFragment() {
        return this.encodedFragment();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedPassword", imports = {}))
    public final String -deprecated_encodedPassword() {
        return this.encodedPassword();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedPath", imports = {}))
    public final String -deprecated_encodedPath() {
        return this.encodedPath();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedPathSegments", imports = {}))
    public final List -deprecated_encodedPathSegments() {
        return this.encodedPathSegments();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedQuery", imports = {}))
    public final String -deprecated_encodedQuery() {
        return this.encodedQuery();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "encodedUsername", imports = {}))
    public final String -deprecated_encodedUsername() {
        return this.encodedUsername();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "fragment", imports = {}))
    public final String -deprecated_fragment() {
        return this.fragment;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "host", imports = {}))
    public final String -deprecated_host() {
        return this.host;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "password", imports = {}))
    public final String -deprecated_password() {
        return this.password;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "pathSegments", imports = {}))
    public final List -deprecated_pathSegments() {
        return this.pathSegments;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "pathSize", imports = {}))
    public final int -deprecated_pathSize() {
        return this.pathSize();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "port", imports = {}))
    public final int -deprecated_port() {
        return this.port;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "query", imports = {}))
    public final String -deprecated_query() {
        return this.query();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "queryParameterNames", imports = {}))
    public final Set -deprecated_queryParameterNames() {
        return this.queryParameterNames();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "querySize", imports = {}))
    public final int -deprecated_querySize() {
        return this.querySize();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "scheme", imports = {}))
    public final String -deprecated_scheme() {
        return this.scheme;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to toUri()", replaceWith = @ReplaceWith(expression = "toUri()", imports = {}))
    public final URI -deprecated_uri() {
        return this.uri();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to toUrl()", replaceWith = @ReplaceWith(expression = "toUrl()", imports = {}))
    public final URL -deprecated_url() {
        return this.url();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "username", imports = {}))
    public final String -deprecated_username() {
        return this.username;
    }

    static {
        HttpUrl.Companion = new okhttp3.HttpUrl.Companion(null);
        HttpUrl.HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public HttpUrl(String s, String s1, String s2, String s3, int v, List list0, List list1, String s4, String s5) {
        Intrinsics.checkNotNullParameter(s, "scheme");
        Intrinsics.checkNotNullParameter(s1, "username");
        Intrinsics.checkNotNullParameter(s2, "password");
        Intrinsics.checkNotNullParameter(s3, "host");
        Intrinsics.checkNotNullParameter(list0, "pathSegments");
        Intrinsics.checkNotNullParameter(s5, "url");
        super();
        this.scheme = s;
        this.username = s1;
        this.password = s2;
        this.host = s3;
        this.port = v;
        this.pathSegments = list0;
        this.queryNamesAndValues = list1;
        this.fragment = s4;
        this.url = s5;
        this.isHttps = Intrinsics.areEqual(s, "https");
    }

    @JvmStatic
    public static final int defaultPort(String s) {
        return HttpUrl.Companion.defaultPort(s);
    }

    public final String encodedFragment() {
        if(this.fragment == null) {
            return null;
        }
        int v = StringsKt.indexOf$default(this.url, '#', 0, false, 6, null);
        String s = this.url.substring(v + 1);
        Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String).substring(startIndex)");
        return s;
    }

    public final String encodedPassword() {
        if(this.password.length() == 0) {
            return "";
        }
        int v = StringsKt.indexOf$default(this.url, ':', this.scheme.length() + 3, false, 4, null);
        int v1 = StringsKt.indexOf$default(this.url, '@', 0, false, 6, null);
        String s = this.url.substring(v + 1, v1);
        Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String…ing(startIndex, endIndex)");
        return s;
    }

    public final String encodedPath() {
        int v = StringsKt.indexOf$default(this.url, '/', this.scheme.length() + 3, false, 4, null);
        int v1 = _UtilCommonKt.delimiterOffset(this.url, "?#", v, this.url.length());
        String s = this.url.substring(v, v1);
        Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String…ing(startIndex, endIndex)");
        return s;
    }

    public final List encodedPathSegments() {
        int v = StringsKt.indexOf$default(this.url, '/', this.scheme.length() + 3, false, 4, null);
        int v1 = _UtilCommonKt.delimiterOffset(this.url, "?#", v, this.url.length());
        List list0 = new ArrayList();
        while(v < v1) {
            int v2 = _UtilCommonKt.delimiterOffset(this.url, '/', v + 1, v1);
            String s = this.url.substring(v + 1, v2);
            Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String…ing(startIndex, endIndex)");
            list0.add(s);
            v = v2;
        }
        return list0;
    }

    public final String encodedQuery() {
        if(this.queryNamesAndValues == null) {
            return null;
        }
        int v = StringsKt.indexOf$default(this.url, '?', 0, false, 6, null);
        int v1 = _UtilCommonKt.delimiterOffset(this.url, '#', v + 1, this.url.length());
        String s = this.url.substring(v + 1, v1);
        Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String…ing(startIndex, endIndex)");
        return s;
    }

    public final String encodedUsername() {
        if(this.username.length() == 0) {
            return "";
        }
        int v = this.scheme.length();
        int v1 = _UtilCommonKt.delimiterOffset(this.url, ":@", v + 3, this.url.length());
        String s = this.url.substring(v + 3, v1);
        Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String…ing(startIndex, endIndex)");
        return s;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof HttpUrl && Intrinsics.areEqual(((HttpUrl)object0).url, this.url);
    }

    public final String fragment() {
        return this.fragment;
    }

    @JvmStatic
    public static final HttpUrl get(String s) {
        return HttpUrl.Companion.get(s);
    }

    @JvmStatic
    public static final HttpUrl get(URI uRI0) {
        return HttpUrl.Companion.get(uRI0);
    }

    @JvmStatic
    public static final HttpUrl get(URL uRL0) {
        return HttpUrl.Companion.get(uRL0);
    }

    @Override
    public int hashCode() {
        return this.url.hashCode();
    }

    public final String host() {
        return this.host;
    }

    public final boolean isHttps() {
        return this.isHttps;
    }

    public final Builder newBuilder() {
        Builder httpUrl$Builder0 = new Builder();
        httpUrl$Builder0.setScheme$okhttp(this.scheme);
        httpUrl$Builder0.setEncodedUsername$okhttp(this.encodedUsername());
        httpUrl$Builder0.setEncodedPassword$okhttp(this.encodedPassword());
        httpUrl$Builder0.setHost$okhttp(this.host);
        int v = HttpUrl.Companion.defaultPort(this.scheme);
        httpUrl$Builder0.setPort$okhttp((this.port == v ? -1 : this.port));
        httpUrl$Builder0.getEncodedPathSegments$okhttp().clear();
        httpUrl$Builder0.getEncodedPathSegments$okhttp().addAll(this.encodedPathSegments());
        httpUrl$Builder0.encodedQuery(this.encodedQuery());
        httpUrl$Builder0.setEncodedFragment$okhttp(this.encodedFragment());
        return httpUrl$Builder0;
    }

    public final Builder newBuilder(String s) {
        Intrinsics.checkNotNullParameter(s, "link");
        try {
            return new Builder().parse$okhttp(this, s);
        }
        catch(IllegalArgumentException unused_ex) {
            return null;
        }
    }

    @JvmStatic
    public static final HttpUrl parse(String s) {
        return HttpUrl.Companion.parse(s);
    }

    public final String password() {
        return this.password;
    }

    public final List pathSegments() {
        return this.pathSegments;
    }

    public final int pathSize() {
        return this.pathSegments.size();
    }

    public final int port() {
        return this.port;
    }

    public final String query() {
        if(this.queryNamesAndValues == null) {
            return null;
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        HttpUrl.Companion.toQueryString$okhttp(this.queryNamesAndValues, stringBuilder0);
        return stringBuilder0.toString();
    }

    public final String queryParameter(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        List list0 = this.queryNamesAndValues;
        if(list0 == null) {
            return null;
        }
        IntProgression intProgression0 = RangesKt.step(RangesKt.until(0, list0.size()), 2);
        int v = intProgression0.getFirst();
        int v1 = intProgression0.getLast();
        int v2 = intProgression0.getStep();
        if(v2 > 0 && v <= v1 || v2 < 0 && v1 <= v) {
            while(true) {
                if(Intrinsics.areEqual(s, this.queryNamesAndValues.get(v))) {
                    return (String)this.queryNamesAndValues.get(v + 1);
                }
                if(v == v1) {
                    break;
                }
                v += v2;
            }
        }
        return null;
    }

    public final String queryParameterName(int v) {
        List list0 = this.queryNamesAndValues;
        if(list0 == null) {
            throw new IndexOutOfBoundsException();
        }
        Object object0 = list0.get(v * 2);
        Intrinsics.checkNotNull(object0);
        return (String)object0;
    }

    public final Set queryParameterNames() {
        if(this.queryNamesAndValues == null) {
            return SetsKt.emptySet();
        }
        LinkedHashSet linkedHashSet0 = new LinkedHashSet();
        IntProgression intProgression0 = RangesKt.step(RangesKt.until(0, this.queryNamesAndValues.size()), 2);
        int v = intProgression0.getFirst();
        int v1 = intProgression0.getLast();
        int v2 = intProgression0.getStep();
        if(v2 > 0 && v <= v1 || v2 < 0 && v1 <= v) {
            while(true) {
                Object object0 = this.queryNamesAndValues.get(v);
                Intrinsics.checkNotNull(object0);
                linkedHashSet0.add(object0);
                if(v == v1) {
                    break;
                }
                v += v2;
            }
        }
        Set set0 = Collections.unmodifiableSet(linkedHashSet0);
        Intrinsics.checkNotNullExpressionValue(set0, "unmodifiableSet(result)");
        return set0;
    }

    public final String queryParameterValue(int v) {
        List list0 = this.queryNamesAndValues;
        if(list0 == null) {
            throw new IndexOutOfBoundsException();
        }
        return (String)list0.get(v * 2 + 1);
    }

    public final List queryParameterValues(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        if(this.queryNamesAndValues == null) {
            return CollectionsKt.emptyList();
        }
        List list0 = new ArrayList();
        IntProgression intProgression0 = RangesKt.step(RangesKt.until(0, this.queryNamesAndValues.size()), 2);
        int v = intProgression0.getFirst();
        int v1 = intProgression0.getLast();
        int v2 = intProgression0.getStep();
        if(v2 > 0 && v <= v1 || v2 < 0 && v1 <= v) {
            while(true) {
                if(Intrinsics.areEqual(s, this.queryNamesAndValues.get(v))) {
                    list0.add(this.queryNamesAndValues.get(v + 1));
                }
                if(v == v1) {
                    break;
                }
                v += v2;
            }
        }
        List list1 = Collections.unmodifiableList(list0);
        Intrinsics.checkNotNullExpressionValue(list1, "unmodifiableList(result)");
        return list1;
    }

    public final int querySize() {
        return this.queryNamesAndValues == null ? 0 : this.queryNamesAndValues.size() / 2;
    }

    public final String redact() {
        Builder httpUrl$Builder0 = this.newBuilder("/...");
        Intrinsics.checkNotNull(httpUrl$Builder0);
        return httpUrl$Builder0.username("").password("").build().toString();
    }

    public final HttpUrl resolve(String s) {
        Intrinsics.checkNotNullParameter(s, "link");
        Builder httpUrl$Builder0 = this.newBuilder(s);
        return httpUrl$Builder0 == null ? null : httpUrl$Builder0.build();
    }

    public final String scheme() {
        return this.scheme;
    }

    @Override
    public String toString() {
        return this.url;
    }

    // 去混淆评级： 低(20)
    public final String topPrivateDomain() {
        return _HostnamesCommonKt.canParseAsIpAddress(this.host) ? null : PublicSuffixDatabase.Companion.get().getEffectiveTldPlusOne(this.host);
    }

    public final URI uri() {
        URI uRI0;
        try {
            return new URI("///");
        }
        catch(URISyntaxException uRISyntaxException0) {
            try {
                uRI0 = URI.create("///");
            }
            catch(Exception unused_ex) {
                throw new RuntimeException(uRISyntaxException0);
            }
            Intrinsics.checkNotNullExpressionValue(uRI0, "{\n      // Unlikely edge…Unexpected!\n      }\n    }");
            return uRI0;
        }
    }

    public final URL url() {
        try {
            return new URL(this.url);
        }
        catch(MalformedURLException malformedURLException0) {
            throw new RuntimeException(malformedURLException0);
        }
    }

    public final String username() {
        return this.username;
    }
}

