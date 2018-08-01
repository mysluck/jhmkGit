package com.jhmk.earlywaring.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/7/23 10:56
 */

@Entity
@Table(name = "user_info", schema = "yunemr")
public class UserInfo {
    private int uid;
    private String name;
    private String password;
    private String salt;
    private byte state;
    private String username;

    @Id
    @Column(name = "uid", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salt", nullable = true, length = 255)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "state", nullable = false)
    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return uid == userInfo.uid &&
                state == userInfo.state &&
                Objects.equals(name, userInfo.name) &&
                Objects.equals(password, userInfo.password) &&
                Objects.equals(salt, userInfo.salt) &&
                Objects.equals(username, userInfo.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid, name, password, salt, state, username);
    }
}
