package cercli.utils;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class OffsetDateTimeUtils {

    // Mock server timezone as Asia/Dubai to mimic a situation where data is being accessed in a different timezone
    private static final ZoneId SERVER_TIMEZONE = ZoneId.of("Asia/Dubai");

    public static OffsetDateTime getServerTime() {
        return OffsetDateTime.now();
    }

    /**
     * Convert to mocked server timezone
     * @param timeToConvert Original time as read from the DB
     * @return Converted time in server timezone
     */
    public static OffsetDateTime convertToServerTime(OffsetDateTime timeToConvert) {
        ZoneOffset serverOffset = SERVER_TIMEZONE.getRules().getOffset(timeToConvert.toInstant());

        return timeToConvert.withOffsetSameInstant(serverOffset);
    }
}
