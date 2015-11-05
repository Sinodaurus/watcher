package org.singular.util;

import java.util.Base64;

public class Utils {
    public static String encodeB64(String valueToEncode) {
        return Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    public static String decodeB64(String valueToDecode) {
        return new String(Base64.getDecoder().decode(valueToDecode));
    }
}
