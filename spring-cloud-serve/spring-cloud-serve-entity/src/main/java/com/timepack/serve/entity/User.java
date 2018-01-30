package com.timepack.serve.entity;

import com.timepack.serve.BaseEntity;

public class User extends BaseEntity {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer age;

    /**
     * 
     */
    private String balance;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return username 
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 
     * @return name 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 
     * @return age 
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 
     * @param age 
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 
     * @return balance 
     */
    public String getBalance() {
        return balance;
    }

    /**
     * 
     * @param balance 
     */
    public void setBalance(String balance) {
        this.balance = balance == null ? null : balance.trim();
    }
}