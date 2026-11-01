package extension.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExtensionUtilTest {

    @Test
    void encode_decode_roundTrip() {
        assertEquals("a+b", Encode.encodeUrl("a b"));
        assertEquals("a b", Encode.decodeUrl("a+b"));
        final byte[] data = "hello".getBytes(UTF_8);
        final String b64 = Encode.encodeBase64(data);
        assertArrayEquals(data, Encode.decodeBase64(b64));
        assertEquals("hello", Encode.decodeBase64(b64, UTF_8));
        assertEquals(b64, Encode.encodeBase64("hello", UTF_8));
        final String urlB64 = Encode.encodeBase64Url(data);
        assertArrayEquals(data, Encode.decodeBase64Url(urlB64));
        final String hex = Encode.encodeHex(data);
        assertArrayEquals(data, Encode.decodeHex(hex));
        assertArrayEquals(data, Encode.decodeHex(hex.toCharArray()));
        assertEquals("", Encode.encodeUrl(null));
        assertEquals("", Encode.decodeUrl(null));
    }

    @Test
    void strings_split() {
        assertArrayEquals(new String[]{"a", "b", "c"}, Strings.split("a,b,c", ','));
        assertArrayEquals(new String[]{"a", "b"}, Strings.split("a,b", ','));
        assertArrayEquals(new String[]{"abc"}, Strings.split("abc", ','));
    }

    @Test
    void json_escapeAndId() {
        assertEquals("{\"id\":5}", JSON.jsonId(5));
        assertEquals("{\"id\":\"a\\\"b\"}", JSON.jsonId("a\"b"));
        assertEquals("\\n", JSON.escapeJson("\n"));
        assertEquals("", JSON.escapeJson(null));
    }

    @Test
    void chance_methods() {
        final char[] chars = {'a', 'b'};
        final char c = Chance.randomCharFrom(chars);
        assertTrue(c == 'a' || c == 'b');
        final List<String> list = List.of("x", "y");
        assertTrue(list.contains(Chance.randomItemFrom(list)));
        final String r = Chance.randomItemFrom("first", "second");
        assertTrue(r.equals("first") || r.equals("second"));
    }

    @Test
    void byteArrayBuilder() {
        final byte[] zeros = ByteArrayBuilder.newByteArray().length(4).fillWithZero().build();
        assertEquals(4, zeros.length);
        final byte[] random = ByteArrayBuilder.newByteArray().length(4).prng(new Random(1)).fillRandom().build();
        assertEquals(4, random.length);
        final String hex = ByteArrayBuilder.newByteArray().length(2).hex();
        assertEquals(4, hex.length());
        final String b64 = ByteArrayBuilder.newByteArray().length(2).base64();
        assertTrue(b64.length() > 0);
        final String b64url = ByteArrayBuilder.newByteArray().length(2).base64Url();
        assertTrue(b64url.length() > 0);
    }

    @Test
    void io_loadResource_missingThrows() {
        assertThrows(FileNotFoundException.class, () -> IO.loadResource("/no/such/resource.txt"));
    }

    @Test
    void io_writeReturningHash(@TempDir Path dir) throws Exception {
        final Path file = dir.resolve("out.bin");
        final byte[] data = "hello".getBytes(UTF_8);
        final var in = new ByteArrayInputStream(data);
        final byte[] hash = IO.writeReturningSha256(in, file);
        assertEquals(32, hash.length);
        final var in2 = new ByteArrayInputStream(data);
        final byte[] hash2 = IO.writeReturningHash(in2, dir.resolve("out2.bin"), MessageDigest.getInstance("MD5"));
        assertEquals(16, hash2.length);
    }

    @Test
    void io_writeZipEntry() throws Exception {
        final var baos = new java.io.ByteArrayOutputStream();
        try (final var zos = new java.util.zip.ZipOutputStream(baos)) {
            IO.writeZipEntry("a.txt", "data".getBytes(UTF_8), zos);
        }
        assertTrue(baos.size() > 0);
    }

    @Test
    void time_now() {
        assertTrue(Time.now(Time.YYYY_MM_DD).matches("\\d{4}-\\d{2}-\\d{2}"));
    }
}
