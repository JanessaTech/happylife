package com.happylife.core.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Generator UUID
 * @author Juan Zhao
 */
public class UUIDGenerator {
    /**
     * Generator one random UUID
     * @return
     */
    public static UUID getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid;
    }

    /**
     * @param number How many UUIDs to be generated
     * @return
     */
    public static List<UUID> getUUID(int number){
        List uuids = new ArrayList<UUID>();
        while(number > 0){
            uuids.add(UUID.randomUUID());
            number--;
        }
        return uuids;
    }
}
