package extension.util;

import java.util.List;

public enum Chance {;

    public static char randomCharFrom(final char[] array) {
        return array[(int)(Math.random()*array.length)];
    }
    public static <T> T randomItemFrom(final List<T> list) {
        return list.get((int)(Math.random()*list.size()));
    }
    @SafeVarargs
    public static <T> T randomItemFrom(final T option, final T... options) {
        final int index = (int)(Math.random()*(options.length+1));
        return index == 0 ? option : options[index-1];
    }

}
