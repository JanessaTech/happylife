package com.happylife.core.dto.user;

import com.happylife.core.exception.user.UserFilterParameterException;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class UserFilter {
    private String userIds;
    private String name;
    private String password;
    private String sex;
    private String sortby;
    private String order;

    private MessageSource messageSource;

    public UserFilter(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSortby() {
        return sortby;
    }

    public void setSortby(String sortby) {
        this.sortby = sortby;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    private void validateUserIds(){
        if(!this.userIds.equals("")){
            String[] idArr = this.userIds.split(",");
            boolean isValid = true;
            StringBuffer reason = new StringBuffer();
            reason.append("for userIds, invalid ids are:");
            for(String id : idArr){
                try{
                    UUID.fromString(id);
                }catch (IllegalArgumentException ex){
                    isValid = false;
                    if(!invalidFields.contains("userIds")){
                        invalidFields.add("userIds");
                    }
                    reason.append(id);
                    reason.append(",");
                }
            }
            if(!isValid){
                reasons.add(reason.toString());
            }
        }
        return;
    }

    private List<String> invalidFields = new ArrayList<String>();
    private List<String> reasons = new ArrayList<String>();

    private void validateName(){
        return;
    }

    private void validatePassword(){return;}

    private void validateSex(){
        return;
    }

    private void validateSortby(){
        return;
    }

    private void validateOrder(){
        return;
    }

    public void validate() throws UserFilterParameterException {
        validateUserIds();
        validateName();
        validatePassword();
        validateSex();
        validateSortby();
        validateOrder();
        if(invalidFields.size() > 0){
            throw new UserFilterParameterException(this.messageSource.getMessage("user.filter.validation", new Object[]{invalidFields.toString(), reasons.toString()}, Locale.getDefault()));
        }
    }

    @Override
    public String toString() {
        return "UserFilter{" +
                "userIds='" + userIds + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", sortby='" + sortby + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
