package com.ras.teleskabot.telegram.database.service;

import com.ras.teleskabot.telegram.database.dao.GenericDAOImpl;
import com.ras.teleskabot.telegram.database.entity.UserEntity;

import java.util.List;

public class UserService {

    private static volatile UserService instance;

    private static volatile GenericDAOImpl <UserEntity, Long> userDao = new GenericDAOImpl<>(UserEntity.class);

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new UserService();
                }
            }
        }

        return instance;
    }

    public long create(UserEntity entity) {
        userDao.openCurrentSessionWithTransaction();
        long id = userDao.create(entity);
        userDao.closeCurrentSessionWithTransaction();

        return id;
    }

    public UserEntity read(long userId) {
        userDao.openCurrentSessionWithTransaction();
        UserEntity user = userDao.read(userId);
        userDao.closeCurrentSessionWithTransaction();

        return user;
    }

    public List<UserEntity> readAll() {
        userDao.openCurrentSessionWithTransaction();
        List<UserEntity> userList = userDao.readAll();
        userDao.closeCurrentSessionWithTransaction();

        return userList;
    }

}