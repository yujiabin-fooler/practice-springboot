package com.jiabin.shardingsphere.encryption.decryption.practice.entity;

/**
 * <p>
 * UserEntity
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/7/15
 */
public class UserEntity {

    private Long id;

    private String email;

    private String nickName;

    private String passWord;

    private String regTime;

    private String userName;

    private String salary;

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public UserEntity setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getPassWord() {
        return passWord;
    }

    public UserEntity setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    public String getRegTime() {
        return regTime;
    }

    public UserEntity setRegTime(String regTime) {
        this.regTime = regTime;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getSalary() {
        return salary;
    }

    public UserEntity setSalary(String salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", regTime='" + regTime + '\'' +
                ", userName='" + userName + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
