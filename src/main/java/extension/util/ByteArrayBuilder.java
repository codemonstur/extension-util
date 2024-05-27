package extension.util;

import java.security.SecureRandom;
import java.util.Random;

import static extension.util.Encode.*;

public final class ByteArrayBuilder {

    public static ByteArrayBuilder newByteArray() {
        return new ByteArrayBuilder();
    }

    private Random random = new SecureRandom();
    private int length = 16;
    private int fill = 0;

    public ByteArrayBuilder length(final int length) {
        this.length = length;
        return this;
    }
    public ByteArrayBuilder prng(final Random random) {
        this.random = random;
        return this;
    }
    public ByteArrayBuilder fillWithZero() {
        this.fill = 0;
        return this;
    }
    public ByteArrayBuilder fillRandom() {
        this.fill = 1;
        return this;
    }

    public byte[] build() {
        final var bytes = new byte[length];
        if (fill == 1) random.nextBytes(bytes);
        return bytes;
    }
    public String hex() {
        return encodeHex(build());
    }
    public String base64() {
        return encodeBase64(build());
    }
    public String base64Url() {
        return encodeBase64Url(build());
    }

}
