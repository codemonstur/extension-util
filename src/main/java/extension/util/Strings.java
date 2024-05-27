package extension.util;

public enum Strings {;

    public static String[] split(final String string, final char delimiter) {
        final var parts = new String[string.length() + 1];

        int index = 0; int lastOccurence = 0; int foundPosition = 0;
        while ((foundPosition = string.indexOf(delimiter, lastOccurence)) > -1) {
            parts[index++] = string.substring(lastOccurence, foundPosition);
            lastOccurence = foundPosition + 1;
        }
        parts[index] = string.substring(lastOccurence);

        final var result = new String[index + 1];
        System.arraycopy(parts, 0, result, 0, result.length);
        return result;
    }

}
