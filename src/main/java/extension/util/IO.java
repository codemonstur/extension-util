package extension.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.nio.file.Files.newOutputStream;

public enum IO {;

    public static String loadResource(final String resource, final Charset charset) throws IOException {
        return new String(loadResource(resource), charset);
    }

    public static byte[] loadResource(final String resource) throws IOException {
        try (final var in = IO.class.getResourceAsStream(resource)) {
            if (in == null) throw new FileNotFoundException("Resource " + resource + " does not exist.");
            return in.readAllBytes();
        }
    }

    public static byte[] writeReturningSha256(final InputStream in, final Path file) throws IOException, NoSuchAlgorithmException {
        return writeReturningHash(in, file, MessageDigest.getInstance("SHA-256"));
    }
    public static byte[] writeReturningHash(final InputStream in, final Path file, final MessageDigest digest) throws IOException {
        try (final var out = newOutputStream(file)) {
            final var buffer = new byte[2048];
            for (int read; (read = in.read(buffer)) != -1;) {
                out.write(buffer, 0, read);
                digest.update(buffer, 0, read);
            }
        }
        return digest.digest();
    }

    public static void writeZipEntry(final String fileName, final byte[] bytes, final ZipOutputStream out) throws IOException {
        out.putNextEntry(new ZipEntry(fileName));
        out.write(bytes);
        out.closeEntry();
    }

}
