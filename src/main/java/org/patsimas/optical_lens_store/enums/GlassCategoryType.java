package org.patsimas.optical_lens_store.enums;

import lombok.Getter;

@Getter
public enum  GlassCategoryType {

    SMALL(((short) 0)),
    MEDIUM(((short) 1)),
    LARGE(((short) 2));

    private final short code;

    GlassCategoryType(short v) {
        code = v;
    }

    public short code() {
        return code;
    }

    public static GlassCategoryType fromValue(short v) {
        for (GlassCategoryType c: GlassCategoryType.values()) {
            if (c.code == v) {
                return c;
            }
        }
        throw new IllegalArgumentException(String.valueOf(v));
    }
}
