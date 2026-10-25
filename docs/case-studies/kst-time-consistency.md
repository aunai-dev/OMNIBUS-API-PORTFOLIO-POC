# 사례 연구: API 시간 출력 일관성

## 문제

API 응답이 JVM 기본 시간대에 의존하고 있었습니다. UTC로 실행되는 컨테이너에서는
KST 시각이 전날로 직렬화될 수 있었고, 이 때문에 상대 시간 표시가 틀리거나 응답
형식이 필드마다 달라지는 문제가 생겼습니다.

## 기술적 결정

구현에서는 `Asia/Seoul`을 명시적인 단일 시간대 기준으로 정하고, 오프셋을 포함한
고정 ISO 8601 형식을 사용했습니다. SQL `Timestamp`와 도메인 `LocalDateTime`이
동일한 출력 계약을 사용하며, 한글 표시 날짜도 같은 시간대 기준을 공유합니다.

## 회귀 검증 전략

테스트에서는 JVM 기본 시간대를 UTC로 바꾼 뒤 다음을 확인합니다.

- 타임스탬프가 여전히 KST로 출력되는지
- 한글 표시 날짜가 전날로 밀리지 않는지
- 값이 없을 때 `null`이 유지되는지
- `Timestamp`와 KST 벽시계 `LocalDateTime`이 동일한 형식으로 직렬화되는지

## 공개 근거

- [KstTime 구현](../../core/common-core/src/main/java/umonaz/omnibus/utils/KstTime.java)
- [HangulGenerator 구현](../../core/common-core/src/main/java/umonaz/omnibus/utils/HangulGenerator.java)
- [회귀 테스트](../../core/common-core/src/test/java/umonaz/omnibus/utils/KstTimeTest.java)
- [재구성 공개 PR](https://github.com/aunai-dev/OMNIBUS-API-PORTFOLIO-POC/pull/1)

추출된 프로젝트는 Java 21에서 빌드되며, `./gradlew test`로 포함된 회귀 테스트
4개가 모두 통과합니다.
