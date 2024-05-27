package extension.util;

import java.io.Reader;
import java.lang.reflect.Type;
import java.text.StringCharacterIterator;

import static java.lang.String.format;
import static java.text.CharacterIterator.DONE;

public enum JSON {;

    public static String jsonId(final long id) {
        return format("{\"id\":%d}", id);
    }
    public static String jsonId(final int id) {
        return format("{\"id\":%d}", id);
    }
    public static String jsonId(final String id) {
        return format("{\"id\":\"%s\"}", escapeJson(id));
    }

    public interface JsonSerializer {
        String toJson(Object o);
        <T> T fromJson(String json, Class<T> clazz);
        <T> T fromJson(String json, Type type);
        <T> T fromJson(Reader json, Class<T> clazz);

        default String safeToJson(final Object o, final String defaultValue) {
            return o == null ? defaultValue : toJson(o);
        }
        default <T> T safeFromJson(final String json, final Class<T> clazz) {
            return json == null ? null : fromJson(json, clazz);
        }
        default String validateJson(final String json, final Class<?> clazz) {
            return toJson(fromJson(json, clazz));
        }
    }

    public static String escapeJson(final String input) {
        if (input == null) return "";

        final var ret = new StringBuilder();
        final var iterator = new StringCharacterIterator(input);
        for (char c = iterator.current(); c != DONE; c = iterator.next()) {
            ret.append(switch (c) {
                case '\"' -> "\\\"";
                case '\t' -> "\\t";
                case '\f' -> "\\f";
                case '\n' -> "\\n";
                case '\r' -> "\\r";
                case '\\' -> "\\\\";
                case '/' -> "\\/";
                case '\b' -> "\\b";
                default -> c;
            });
        }

        return ret.toString();
    }

}
