package extension.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ofPattern;

public enum Time {;

    public static final DateTimeFormatter YYYY_MM_DD = ofPattern("yyyy-MM-dd")
            .withLocale(Locale.getDefault())
            .withZone(ZoneId.systemDefault());
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS = ofPattern("yyyy-MM-dd HH:mm:ss")
            .withLocale(Locale.getDefault())
            .withZone(ZoneId.systemDefault());
    public static String now(final DateTimeFormatter format) {
        return format.format(Instant.now());
    }

}
