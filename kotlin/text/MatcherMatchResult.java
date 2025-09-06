package kotlin.text;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.CollectionsKt;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\n\u0010\u001C\u001A\u0004\u0018\u00010\u0001H\u0016R\u001A\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\b8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u0016\u0010\f\u001A\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001A\u00020\u000EX\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001A\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\u0013\u0010\u0014R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001A\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001A\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u001A\u0010\u001B¨\u0006\u001D"}, d2 = {"Lkotlin/text/MatcherMatchResult;", "Lkotlin/text/MatchResult;", "matcher", "Ljava/util/regex/Matcher;", "input", "", "(Ljava/util/regex/Matcher;Ljava/lang/CharSequence;)V", "groupValues", "", "", "getGroupValues", "()Ljava/util/List;", "groupValues_", "groups", "Lkotlin/text/MatchGroupCollection;", "getGroups", "()Lkotlin/text/MatchGroupCollection;", "matchResult", "Ljava/util/regex/MatchResult;", "getMatchResult", "()Ljava/util/regex/MatchResult;", "range", "Lkotlin/ranges/IntRange;", "getRange", "()Lkotlin/ranges/IntRange;", "value", "getValue", "()Ljava/lang/String;", "next", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class MatcherMatchResult implements MatchResult {
    private List groupValues_;
    private final MatchGroupCollection groups;
    private final CharSequence input;
    private final Matcher matcher;

    public MatcherMatchResult(Matcher matcher0, CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(matcher0, "matcher");
        Intrinsics.checkNotNullParameter(charSequence0, "input");
        super();
        this.matcher = matcher0;
        this.input = charSequence0;
        this.groups = new MatchNamedGroupCollection() {
            // 去混淆评级： 低(20)
            @Override  // kotlin.collections.AbstractCollection
            public final boolean contains(Object object0) {
                return (object0 == null ? true : object0 instanceof MatchGroup) ? this.contains(((MatchGroup)object0)) : false;
            }

            public boolean contains(MatchGroup matchGroup0) {
                return super.contains(matchGroup0);
            }

            @Override  // kotlin.text.MatchGroupCollection
            public MatchGroup get(int v) {
                IntRange intRange0 = RegexKt.access$range(MatcherMatchResult.access$getMatchResult(MatcherMatchResult.this), v);
                if(((int)intRange0.getStart()) >= 0) {
                    String s = MatcherMatchResult.access$getMatchResult(MatcherMatchResult.this).group(v);
                    Intrinsics.checkNotNullExpressionValue(s, "matchResult.group(index)");
                    return new MatchGroup(s, intRange0);
                }
                return null;
            }

            @Override  // kotlin.text.MatchNamedGroupCollection
            public MatchGroup get(String s) {
                Intrinsics.checkNotNullParameter(s, "name");
                java.util.regex.MatchResult matchResult0 = MatcherMatchResult.access$getMatchResult(MatcherMatchResult.this);
                return PlatformImplementationsKt.IMPLEMENTATIONS.getMatchResultNamedGroup(matchResult0, s);
            }

            @Override  // kotlin.collections.AbstractCollection
            public int getSize() {
                return MatcherMatchResult.access$getMatchResult(MatcherMatchResult.this).groupCount() + 1;
            }

            @Override  // kotlin.collections.AbstractCollection
            public boolean isEmpty() {
                return false;
            }

            @Override  // kotlin.collections.AbstractCollection
            public Iterator iterator() {
                return SequencesKt.map(CollectionsKt.asSequence(CollectionsKt.getIndices(this)), new Function1() {
                    {
                        kotlin.text.MatcherMatchResult.groups.1.this = matcherMatchResult$groups$10;
                        super(1);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        return this.invoke(((Number)object0).intValue());
                    }

                    public final MatchGroup invoke(int v) {
                        return kotlin.text.MatcherMatchResult.groups.1.this.get(v);
                    }
                }).iterator();
            }
        };
    }

    @Override  // kotlin.text.MatchResult
    public Destructured getDestructured() {
        return DefaultImpls.getDestructured(this);
    }

    @Override  // kotlin.text.MatchResult
    public List getGroupValues() {
        if(this.groupValues_ == null) {
            this.groupValues_ = new AbstractList() {
                @Override  // kotlin.collections.AbstractCollection
                public final boolean contains(Object object0) {
                    return object0 instanceof String ? this.contains(((String)object0)) : false;
                }

                public boolean contains(String s) {
                    return super.contains(s);
                }

                @Override  // kotlin.collections.AbstractList
                public Object get(int v) {
                    return this.get(v);
                }

                public String get(int v) {
                    String s = MatcherMatchResult.this.getMatchResult().group(v);
                    return s == null ? "" : s;
                }

                @Override  // kotlin.collections.AbstractList
                public int getSize() {
                    return MatcherMatchResult.this.getMatchResult().groupCount() + 1;
                }

                @Override  // kotlin.collections.AbstractList
                public final int indexOf(Object object0) {
                    return object0 instanceof String ? this.indexOf(((String)object0)) : -1;
                }

                public int indexOf(String s) {
                    return super.indexOf(s);
                }

                @Override  // kotlin.collections.AbstractList
                public final int lastIndexOf(Object object0) {
                    return object0 instanceof String ? this.lastIndexOf(((String)object0)) : -1;
                }

                public int lastIndexOf(String s) {
                    return super.lastIndexOf(s);
                }
            };
        }
        List list0 = this.groupValues_;
        Intrinsics.checkNotNull(list0);
        return list0;
    }

    @Override  // kotlin.text.MatchResult
    public MatchGroupCollection getGroups() {
        return this.groups;
    }

    private final java.util.regex.MatchResult getMatchResult() {
        return this.matcher;
    }

    @Override  // kotlin.text.MatchResult
    public IntRange getRange() {
        return RegexKt.range(this.getMatchResult());
    }

    @Override  // kotlin.text.MatchResult
    public String getValue() {
        String s = this.getMatchResult().group();
        Intrinsics.checkNotNullExpressionValue(s, "matchResult.group()");
        return s;
    }

    @Override  // kotlin.text.MatchResult
    public MatchResult next() {
        int v = this.getMatchResult().end() + (this.getMatchResult().end() == this.getMatchResult().start() ? 1 : 0);
        if(v <= this.input.length()) {
            Matcher matcher0 = this.matcher.pattern().matcher(this.input);
            Intrinsics.checkNotNullExpressionValue(matcher0, "matcher.pattern().matcher(input)");
            return RegexKt.findNext(matcher0, v, this.input);
        }
        return null;
    }
}

