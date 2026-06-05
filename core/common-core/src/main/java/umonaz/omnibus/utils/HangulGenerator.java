package umonaz.omnibus.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class HangulGenerator {
    private static final String formatPattern = "yyyy년 MM월 dd일 HH시 mm분 ss초";

    public static String getHangulDate(final Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);

        return dateFormat.format(timestamp);
    }
}
