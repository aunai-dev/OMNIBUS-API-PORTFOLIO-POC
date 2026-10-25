# Case Study: Consistent API Time Output

## Problem

API responses depended on the JVM default timezone. A container running in UTC
could serialize a KST timestamp as the previous calendar day, causing incorrect
relative-time displays and inconsistent response formats.

## Engineering decision

The implementation introduced one explicit `Asia/Seoul` timezone source and a
fixed ISO 8601 format with an offset. Both SQL `Timestamp` values and domain
`LocalDateTime` values use the same output contract. Korean display dates share
the same timezone source.

## Regression strategy

The tests change the JVM default timezone to UTC and verify that:

- timestamps are still emitted as KST;
- Korean display dates do not shift to the previous day;
- null values remain null;
- `Timestamp` and KST wall-clock `LocalDateTime` values serialize consistently.

## Public evidence

- [KstTime implementation](../../core/common-core/src/main/java/umonaz/omnibus/utils/KstTime.java)
- [HangulGenerator implementation](../../core/common-core/src/main/java/umonaz/omnibus/utils/HangulGenerator.java)
- [Regression tests](../../core/common-core/src/test/java/umonaz/omnibus/utils/KstTimeTest.java)
- [Reconstructed public PR](https://github.com/aunai-dev/OMNIBUS-API-PORTFOLIO-POC/pull/1)

The extracted project builds on Java 21, and all four included tests pass with
`./gradlew test`.
