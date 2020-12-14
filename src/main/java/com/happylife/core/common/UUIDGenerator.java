package com.happylife.core.common;

import com.happylife.core.exception.uuid.UUIDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Generator UUID
 * @author Juan Zhao
 */
@Component
public class UUIDGenerator {
    @Autowired
    private MessageSource messageSource;
    /**
     * Generator one random UUID
     * @return
     */
    public  UUID getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid;
    }

    /**
     * Generate UUID using input parameter id
     * @param id
     * @return
     */
    public  UUID getUUID(String id) throws IllegalArgumentException{
        UUID uuid = null;
        try{
            uuid = UUID.fromString(id);
        }catch(IllegalArgumentException ex){
            throw new IllegalArgumentException(this.messageSource.getMessage("uuid.invalid", new Object[]{id}, Locale.getDefault()));
        }
        return uuid;
    }

    /**
     * Generate a list of UUID using input parameter ids
     * ids consists at least one UUID string using "," as a splitter
     * @param ids
     * @return
     */
    public  List<Object> getUUIDs(String ids) throws UUIDException{
        String[] ids_arr = ids.split(",");
        List<Object> uuids = new ArrayList<Object>();
        for(String id : ids_arr){
            try{
                uuids.add(UUID.fromString(id));
            }catch (IllegalArgumentException ex){
                throw new UUIDException(this.messageSource.getMessage("uuid.multiple.invalid", new Object[]{ids, id}, Locale.getDefault()));
            }
        }
        return uuids;
    }

    /**
     * @param number How many UUIDs to be generated
     * @return
     */
    public  List<UUID> getUUIDs(int number){
        List uuids = new ArrayList<UUID>();
        while(number > 0){
            uuids.add(UUID.randomUUID());
            number--;
        }
        return uuids;
    }

    public void validate(String uuidStr, String field, String module) throws UUIDException {
        if(uuidStr == null || uuidStr.trim().equals("")){
            throw new UUIDException(this.messageSource.getMessage("uuid.empty", new Object[]{field, module}, Locale.getDefault()));
        }
        UUID uuid = null;
        try{
            uuid = getUUID(uuidStr);
        }catch(IllegalArgumentException ex){
            throw new UUIDException(ex.getMessage());
        }
    }
}
