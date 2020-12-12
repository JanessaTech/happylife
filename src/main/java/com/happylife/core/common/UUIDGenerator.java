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
     * Generate UUID using input parameter id
     * @param id
     * @return
     */
    public static UUID getUUID(String id){
        return UUID.fromString(id);
    }

    /**
     * Generate a list of UUID using input parameter ids
     * ids consists at least one UUID string using "," as a splitter
     * @param ids
     * @return
     */
    public static List<UUID> getUUIDs(String ids){
        String[] ids_arr = ids.split(",");
        List<UUID> uuids = new ArrayList<UUID>();
        for(String id : ids_arr){
            uuids.add(UUID.fromString(id));
        }
        return uuids;
    }

    /**
     * @param number How many UUIDs to be generated
     * @return
     */
    public static List<UUID> getUUIDs(int number){
        List uuids = new ArrayList<UUID>();
        while(number > 0){
            uuids.add(UUID.randomUUID());
            number--;
        }
        return uuids;
    }
}
