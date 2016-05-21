package com.josie.quake.service.impl;

import com.josie.quake.Dao.UserDao;
import com.josie.quake.commons.utils.RegexUtils;
import com.josie.quake.model.User;
import com.josie.quake.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Josie on 16/5/12.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByAccountAndPassword(
            String account,
            String password) {
        if (RegexUtils.isPhoneNumber(account)) {
            return userDao.getByMobileAndPassword(account, password);
        } else if (RegexUtils.isEmail(account)) {
            return userDao.getByEmailAndPassword(account, password);
        }
        return null;
    }

    @Override
    public User getById(
            int id) {
        return userDao.getById(id);
    }

    @Override
    public void addPrivilege(
            int userId) {
        userDao.addPrivilege(userId, User.Privilege.Admin.toInt());
        return;
    }

    @Override
    public List<User> getAll(
            int start,
            int count) {
        return userDao.getAll(start, count);
    }

    @Override
    public void addUser(
            User user) {
        userDao.addUser(user);
        return;
    }

    @Override
    public void updateUser(
            User user) {
        userDao.updateUser(user);
    }

    @Override
    public void updateUser(
            Integer id,
            String username,
            String mailAdress,
            String phoneNumber,
            String positon,
            String workPlace) {
        userDao.update(id, username, mailAdress, phoneNumber, positon, workPlace);
    }
}
