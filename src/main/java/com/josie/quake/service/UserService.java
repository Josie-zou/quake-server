package com.josie.quake.service;

import com.josie.quake.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Josie on 16/5/12.
 */
@Component
public interface UserService {

    public User getUserByAccountAndPassword(
            String account,
            String password);

    public User getById(
            int id);

    public void addPrivilege(
            int userId);

    public List<User> getAll(int start, int count);

    public void addUser(User user);

    public void updateUser(User user);

    public void updateUser(Integer id, String username, String mailAdress, String qq, String phoneNumber, String positon, String workPlace);

    public User getByAccount(String account);

    public void deleteUser(int id);

    public List<User> getall();

    public List<User> getAllLowerPrivilege(int start, int count, int privilege);

    public void changePassword(int id, String newPassword);
}
