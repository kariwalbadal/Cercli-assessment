package cercli.utils;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class OffsetDateTimeUtils {

    private static final ZoneId SERVER_TIMEZONE = ZoneId.of("Asia/Dubai");

    public static OffsetDateTime getServerTime() {
        return OffsetDateTime.now();
    }

    public static OffsetDateTime convertToServerTime(OffsetDateTime timeToConvert) {
        ZoneOffset serverOffset = SERVER_TIMEZONE.getRules().getOffset(timeToConvert.toInstant());

        return timeToConvert.withOffsetSameInstant(serverOffset);
    }
}
