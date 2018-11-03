package com.ras.teleskabot.telegram.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "userId")
    private long userId;

    @Column(name = "chatId")
    private long chatId;

    @Column(name = "access")
    private boolean access;

    public UserEntity() {
    }

    public UserEntity(long userId, long chatId, boolean access) {
        this.userId = userId;
        this.chatId = chatId;
        this.access = access;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", chatId=" + chatId +
                ", access=" + access +
                '}';
    }

}