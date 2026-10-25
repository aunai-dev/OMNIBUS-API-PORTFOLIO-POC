package umonaz.omnibus.utils;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class HangulGenerator {

    /**
     * 표시용 한글 날짜.
     *
     * <p>존을 포맷터가 직접 들고 있다. 예전에는 타임존을 지정하지 않은 {@code SimpleDateFormat} 이라 JVM 기본값으로 적었고,
     * 컨테이너가 UTC 로 뜬 환경에서 KST 9월 1일 01시에 쓴 이야기가 8월 31일로 내려갔다. 기준은
     * {@link KstTime#ZONE} 하나다.
     */
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초").withZone(KstTime.ZONE);

    public static String getHangulDate(final Timestamp timestamp) {
        return FORMAT.format(timestamp.toInstant());
    }
}
