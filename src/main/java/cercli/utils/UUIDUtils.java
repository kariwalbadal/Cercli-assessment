package cercli.utils;

import java.util.UUID;

public class UUIDUtils {

    private static final String DEFAULT_SERVICE_UUID = "75303e0b-b6ef-44d1-bcbe-5b33b21aa90a";

    /**
     * Utility to create a new UUID. Separated so that this logic can be customised without updating any client
     * @return newly generated unique UID
     */
    public static UUID getNewUUID() {
        return UUID.randomUUID();
    }

    /**
     * Mock agent UUID for creating/modifying records
     * Will be useful to identify agent who performed operation on DB in case there are multiple clients capable
     * of updating the data - like Backend Client service, BackOffice service for Customer support, etc..
     * @return UUID for this agent
     */
    public static UUID getServiceUserUUID() {
        return UUID.fromString(DEFAULT_SERVICE_UUID);
    }
}
