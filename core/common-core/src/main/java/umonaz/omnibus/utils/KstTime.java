package umonaz.omnibus.utils;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 응답에 적는 시각의 기준. 한국 사용자만 쓰는 서비스라 모든 API 가 KST 하나로 말한다.
 *
 * <p>기준을 여기 한곳에 둔 것은 JVM 기본 타임존에 기대던 것이 실제로 틀렸기 때문이다. {@code Timestamp#toString} 과
 * 타임존을 지정하지 않은 포맷터는 JVM 기본값으로 시각을 적는데, 컨테이너가 UTC 로 뜨면 KST 9월 1일 01시에 쓴 글이
 * 8월 31일 16시로 내려간다. 날짜가 하루 밀려 보이고, 방금 온 알림이 「9시간 전」으로 표시된다.
 *
 * <p>포맷터가 존을 직접 들고 있으면 실행 환경이 어떻든 같은 문자열이 나간다. 컨테이너 TZ 설정은 거들 뿐이고, 빠뜨려도
 * 응답은 틀리지 않는다.
 */
public final class KstTime {

    public static final ZoneId ZONE = ZoneId.of("Asia/Seoul");

    /**
     * 오프셋까지 적는 ISO 8601 형식(예: {@code 2026-09-01T01:23:51+09:00}).
     *
     * <p>오프셋을 붙이는 것은 클라이언트가 기준을 추측하지 않게 하기 위해서다. 앱은 이 값을 파싱해 「N분 전」을 계산하는데,
     * 오프셋이 없으면 파서가 단말 로컬 시각으로 읽어 시차만큼 어긋난다.
     *
     * <p>초까지만 적어 길이를 고정한다. {@code ISO_OFFSET_DATE_TIME} 은 소수점 이하가 0 이면 지우는데, 같은 API 가 값에
     * 따라 길이가 다른 문자열을 내보내면 클라이언트 파서가 한쪽에서만 깨진다. 표시에 쓰는 값이라 초 아래는 필요 없다.
     */
    private static final DateTimeFormatter ISO_OFFSET =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX").withZone(ZONE);

    private KstTime() {}

    /** 시각을 KST ISO 8601 문자열로. 값이 없으면 {@code null} 이다. */
    public static String iso(final Timestamp timestamp) {
        return Objects.isNull(timestamp) ? null : ISO_OFFSET.format(timestamp.toInstant());
    }
}
