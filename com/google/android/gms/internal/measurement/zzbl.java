package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Map;

public enum zzbl {
    ADD(0),
    AND(1),
    APPLY(2),
    ASSIGN(3),
    BITWISE_AND(56),
    BITWISE_LEFT_SHIFT(57),
    BITWISE_NOT(58),
    BITWISE_OR(59),
    BITWISE_RIGHT_SHIFT(60),
    BITWISE_UNSIGNED_RIGHT_SHIFT(61),
    BITWISE_XOR(62),
    BLOCK(53),
    BREAK(4),
    CASE(5),
    CONST(52),
    CONTINUE(6),
    CONTROL(49),
    CREATE_ARRAY(7),
    CREATE_OBJECT(8),
    DEFAULT(9),
    DEFINE_FUNCTION(50),
    DIVIDE(10),
    DO(11),
    EQUALS(12),
    EXPRESSION_LIST(13),
    FN(51),
    FOR_IN(0x2F),
    FOR_IN_CONST(54),
    FOR_IN_LET(55),
    FOR_LET(0x3F),
    FOR_OF(0x40),
    FOR_OF_CONST(65),
    FOR_OF_LET(66),
    GET(15),
    GET_CONTAINER_VARIABLE(0x30),
    GET_INDEX(16),
    GET_PROPERTY(17),
    GREATER_THAN(18),
    GREATER_THAN_EQUALS(19),
    IDENTITY_EQUALS(20),
    IDENTITY_NOT_EQUALS(21),
    IF(22),
    LESS_THAN(23),
    LESS_THAN_EQUALS(24),
    MODULUS(25),
    MULTIPLY(26),
    NEGATE(27),
    NOT(28),
    NOT_EQUALS(29),
    NULL(45),
    OR(30),
    PLUS_EQUALS(0x1F),
    POST_DECREMENT(0x20),
    POST_INCREMENT(33),
    QUOTE(46),
    PRE_DECREMENT(34),
    PRE_INCREMENT(35),
    RETURN(36),
    SET_PROPERTY(43),
    SUBTRACT(37),
    SWITCH(38),
    TERNARY(39),
    TYPEOF(40),
    UNDEFINED(44),
    VAR(41),
    WHILE(42);

    private static final Map zzao;
    private final int zzaq;

    static {
        zzbl.zzao = new HashMap();
        zzbl[] arr_zzbl = (zzbl[])zzbl.zzap.clone();
        for(int v = 0; v < arr_zzbl.length; ++v) {
            zzbl zzbl0 = arr_zzbl[v];
            zzbl.zzao.put(zzbl0.zzaq, zzbl0);
        }
    }

    private zzbl(int v1) {
        this.zzaq = v1;
    }

    @Override
    public final String toString() {
        return this.zzaq.toString();
    }

    public static zzbl zza(int v) {
        return (zzbl)zzbl.zzao.get(v);
    }

    public final Integer zzb() {
        return this.zzaq;
    }
}

