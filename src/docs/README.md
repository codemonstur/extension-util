# extension-util

General-purpose utilities: encoding, IO, JSON helpers, random byte generation,
string splitting, and shared date-time formatters.

Companion to [extension-lang](extension-lang/src/docs/README.md); overlapping
concerns (encoding, IO) live in both, while `ByteArrayBuilder` was moved out of
`extension-lang` into this library. No external dependencies.

## Packages

### `Encode`

URL / Base64 / hex / date encoding **and** decoding helpers — a superset of
`extension.lang.Encoding`, including the corresponding decode operations.

### `IO`

- `loadResource(...)` — read classpath resources.
- File writing helpers.
- Zip archive creation.
- `MessageDigest` hashing helpers.

### `JSON`

- `jsonId(...)` helpers for building JSON object identifiers.
- `escapeJson(String)` — correct JSON string escaping.
- `JsonSerializer` — a Gson-style serializer interface injected by the caller, so
  the library stays dependency-free.

### Random and strings

- `ByteArrayBuilder` — build random byte arrays using `SecureRandom`, with
  configurable length and PRNG, hex-friendly output.
- `Chance.randomCharFrom(...)` / `Chance.randomItemFrom(...)` — random selection.
- `Strings.split(String, char)` — an allocation-light string split.

### Time

- `Time.YYYY_MM_DD`, `Time.YYYY_MM_DD_HH_MM_SS` — shared `DateTimeFormatter`
  constants.
- `Time.now(format)` — current timestamp formatted with a supplied pattern.

## Dependency

```xml
<dependency>
  <groupId>com.github.codemonstur</groupId>
  <artifactId>extension-util</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Building

```sh
make build
```

## License

MIT
