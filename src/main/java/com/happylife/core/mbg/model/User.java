package com.happylife.core.mbg.model;

import java.util.Date;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user.user_id
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    private Object userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user.name
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user.password
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user.sex
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    private String sex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user.addr
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    private String addr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user.profile
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    private String profile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user.role
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    private String role;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user.create_date
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.user.update_date
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    private Date updateDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user.user_id
     *
     * @return the value of public.user.user_id
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public Object getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user.user_id
     *
     * @param userId the value for public.user.user_id
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public void setUserId(Object userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user.name
     *
     * @return the value of public.user.name
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user.name
     *
     * @param name the value for public.user.name
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user.password
     *
     * @return the value of public.user.password
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user.password
     *
     * @param password the value for public.user.password
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user.sex
     *
     * @return the value of public.user.sex
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user.sex
     *
     * @param sex the value for public.user.sex
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user.addr
     *
     * @return the value of public.user.addr
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public String getAddr() {
        return addr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user.addr
     *
     * @param addr the value for public.user.addr
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user.profile
     *
     * @return the value of public.user.profile
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public String getProfile() {
        return profile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user.profile
     *
     * @param profile the value for public.user.profile
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user.role
     *
     * @return the value of public.user.role
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user.role
     *
     * @param role the value for public.user.role
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user.create_date
     *
     * @return the value of public.user.create_date
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user.create_date
     *
     * @param createDate the value for public.user.create_date
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.user.update_date
     *
     * @return the value of public.user.update_date
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.user.update_date
     *
     * @param updateDate the value for public.user.update_date
     *
     * @mbg.generated Wed Dec 30 16:15:34 CST 2020
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}