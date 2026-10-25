package umonaz.omnibus.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("KstTime — 응답 시각의 기준")
class KstTimeTest {

    /** KST 2026-09-01 01:23:51 에 해당하는 순간. UTC 로는 하루 전인 08-31 16:23:51 이다. */
    private static final Timestamp WRITTEN_AT = Timestamp.from(Instant.parse("2026-08-31T16:23:51Z"));

    private TimeZone 원래_타임존;

    @BeforeEach
    void setUp() {
        원래_타임존 = TimeZone.getDefault();
        // 컨테이너가 UTC 로 뜬 상태. 시각 필드가 하루 밀려 나가던 환경이다.
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @AfterEach
    void tearDown() {
        TimeZone.setDefault(원래_타임존);
    }

    @Test
    @DisplayName("JVM 타임존이 UTC 여도 KST 로 적는다")
    void iso_JVM_타임존과_무관하게_KST_로_적는다() {
        assertThat(KstTime.iso(WRITTEN_AT)).isEqualTo("2026-09-01T01:23:51+09:00");
    }

    @Test
    @DisplayName("한글 날짜도 같은 기준으로 적는다")
    void 한글_날짜도_KST_로_적는다() {
        // JVM 기본 타임존을 쓰던 시절엔 "2026년 08월 31일 16시 23분 51초" 로 내려가 날짜가 하루 밀렸다.
        assertThat(HangulGenerator.getHangulDate(WRITTEN_AT)).isEqualTo("2026년 09월 01일 01시 23분 51초");
    }

    @Test
    @DisplayName("값이 없으면 null 을 돌려준다")
    void iso_값이_없으면_null() {
        assertThat(KstTime.iso((Timestamp) null)).isNull();
        assertThat(KstTime.iso((LocalDateTime) null)).isNull();
    }

    @Test
    @DisplayName("도메인이 KST 벽시계로 적어 둔 시각도 같은 문자열이 된다")
    void iso_LocalDateTime_도_같은_형식() {
        assertThat(KstTime.iso(LocalDateTime.of(2026, 9, 1, 1, 23, 51))).isEqualTo("2026-09-01T01:23:51+09:00");
        assertThat(KstTime.iso(LocalDateTime.of(2026, 9, 1, 1, 23, 51))).isEqualTo(KstTime.iso(WRITTEN_AT));
    }
}
