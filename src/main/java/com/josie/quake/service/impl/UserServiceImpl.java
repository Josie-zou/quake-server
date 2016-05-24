package com.josie.quake.service.impl;

import com.josie.quake.dao.UserDao;
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
            String qq,
            String phoneNumber,
            String positon,
            String workPlace) {
        userDao.update(id, username, mailAdress, qq, phoneNumber, positon, workPlace);
    }

    @Override
    public User getByAccount(String account) {
        if (RegexUtils.isPhoneNumber(account)) {
            return userDao.getByMobile(account);
        } else if (RegexUtils.isEmail(account)) {
            return userDao.getByEmail(account);
        }
        return null;
    }

    @Override
    public void deleteUser(int id) {
        userDao.delete(id);
    }

    @Override
    public List<User> getall() {
        return userDao.getall();
    }

    @Override
    public List<User> getAllLowerPrivilege(int start, int count, int privilege) {
        return userDao.getAllLowerPrivilege(start, count, privilege);
    }

}
