package cercli.utils;

import java.util.UUID;

public class UUIDUtils {

    private static final String DEFAULT_SERVICE_UUID = "75303e0b-b6ef-44d1-bcbe-5b33b21aa90a";

    public static UUID getNewUUID() {
        return UUID.randomUUID();
    }

    public static UUID getServiceUserUUID() {
        return UUID.fromString(DEFAULT_SERVICE_UUID);
    }
}
