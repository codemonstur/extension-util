package extension.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HexFormat;

import static java.lang.Character.digit;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.format.DateTimeFormatter.ofPattern;

public enum Encode {;

    public static String encodeUrl(final String value) {
        return value != null ? URLEncoder.encode(value, UTF_8) : "";
    }
    public static String decodeUrl(final String value) {
        return value != null ? URLDecoder.decode(value, UTF_8) : "";
    }

    public static String encodeBase64(final String value, final Charset charset) {
        return encodeBase64(value.getBytes(charset));
    }
    public static String encodeBase64(final byte[] value) {
        return Base64.getEncoder().encodeToString(value);
    }
    public static String decodeBase64(final String value, final Charset charset) {
        return new String(decodeBase64(value), charset);
    }
    public static byte[] decodeBase64(final String value) {
        return Base64.getDecoder().decode(value);
    }

    public static String encodeBase64Url(final byte[] value) {
        return Base64.getUrlEncoder().encodeToString(value);
    }
    public static byte[] decodeBase64Url(final String text) {
        return Base64.getUrlDecoder().decode(text);
    }

    public static String encodeHex(final byte[] bytes) {
        return HexFormat.of().formatHex(bytes);
    }
    public static byte[] decodeHex(final String hex) {
        return HexFormat.of().parseHex(hex);
    }
    public static byte[] decodeHex(final char[] hex) {
        return HexFormat.of().parseHex(hex, 0, hex.length);
    }

}