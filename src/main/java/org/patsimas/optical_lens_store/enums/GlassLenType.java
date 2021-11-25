package org.patsimas.optical_lens_store.enums;

import lombok.Getter;

@Getter
public enum GlassLenType {

    LEFT(((short) 0)),
    RIGHT(((short) 1));

    private final short code;

    GlassLenType(short v) {
        code = v;
    }

    public short code() {
        return code;
    }

    public static GlassLenType fromValue(short v) {
        for (GlassLenType c: GlassLenType.values()) {
            if (c.code == v) {
                return c;
            }
        }
        throw new IllegalArgumentException(String.valueOf(v));
    }
}
