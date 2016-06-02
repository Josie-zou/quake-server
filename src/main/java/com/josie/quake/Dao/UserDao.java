package com.josie.quake.dao;

import com.josie.quake.dao.annotation.DataSourceQuake;
import com.josie.quake.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Josie on 16/5/12.
 */
@DataSourceQuake
public interface UserDao {

    static final String COL_ALL = "id, username, nick_name, privilege, password, desciption, phone_number, mail_adress, icon_url, work_place, positon, qq, create_time, last_update_time";
    static final String TABLE = "user";

    @Select(""
            + " select "
            + COL_ALL
            + " from "
            + TABLE
            + " where phone_number = #{phoneNumber} "
            + " and password = #{password}")
    public User getByMobileAndPassword(
            @Param("phoneNumber") String phoneNumber,
            @Param("password") String password);

    @Select(""
            + " select "
            + COL_ALL
            + " from "
            + TABLE
            + " where mail_adress = #{email} "
            + " and password = #{password}")
    public User getByEmailAndPassword(
            @Param("email") String email,
            @Param("password") String password);

    @Select(""
            + " select "
            + COL_ALL
            + " from "
            + TABLE
            + " where id = #{id}")
    public User getById(
            @Param("id") int id);

    @Update(""
            + " update "
            + TABLE
            + " set privilege = #{privilege} "
            + " where id = #{id}")
    void addPrivilege(
            @Param("id") int userId,
            @Param("privilege") int privilege);

    @Select(""
            + " select "
            + COL_ALL
            + " from "
            + TABLE
            + " limit #{start}, #{count}")
    public List<User> getAll(
            @Param("start") int start,
            @Param("count") int count);

    @Insert(""
            + " insert into "
            + TABLE
            + " set "
            + " username = #{username}, "
            + " password = #{password}, "
            + " phone_number = #{phoneNumber}, "
            + " mail_adress = #{mailAdress}, "
            + " work_place = #{workPlace}, "
            + " positon = #{positon}, "
            + " qq = #{qq}, "
            + " create_time = now(), "
            + " last_update_time = now()")
    public void addUser(
            User user);

    @Update(""
            + " update "
            + TABLE
            + " set "
            + " username = #{username}, "
            + " password = #{password}, "
            + " phone_number = #{phoneNumber}, "
            + " mail_adress = #{mailAdress}, "
            + " work_place = #{workPlace}, "
            + " positon = #{positon}, "
            + " qq = #{qq}, "
            + " privilege = #{privilege}, "
            + " last_update_time = now()"
            + " where id = #{id}")
    public void updateUser(
            User user);

    @Update(""
            + " update "
            + TABLE
            + " set "
            + " username = #{username}, "
            + " phone_number = #{phoneNumber}, "
            + " mail_adress = #{mailAdress}, "
            + " qq = #{qq}, "
            + " work_place = #{workPlace}, "
            + " positon = #{positon}, "
            + " last_update_time = now()"
            + " where id = #{id}")
    void update(
            @Param("id") Integer id,
            @Param("username") String username,
            @Param("mailAdress") String mailAdress,
            @Param("qq") String qq,
            @Param("phoneNumber") String phoneNumber,
            @Param("positon") String positon,
            @Param("workPlace") String workPlace);


    @Select(""
            + " select "
            + COL_ALL
            + " from "
            + TABLE
            + " where phone_number = #{phoneNumber} ")
    public User getByMobile(
            @Param("phoneNumber") String account);

    @Select(""
            + " select "
            + COL_ALL
            + " from "
            + TABLE
            + " where mail_adress = #{email} ")
    public User getByEmail(
            @Param("email") String account);

    @Delete(
            "delete from "
                    + TABLE
                    + " where id = #{id}"
    )
    public void delete(@Param("id") int id);

    @Select(
            "select "
                    + COL_ALL
                    + " from "
                    + TABLE
    )
    public List<User> getall();

    @Select(
            "select "
                    + COL_ALL
                    + " from "
                    + TABLE
                    + " where privilege < #{privilege}"
    )
    public List<User> getAllLowerPrivilege(
            @Param("start") int start,
            @Param("count") int count,
            @Param("privilege") int privilege);

    @Update(""
            + " update "
            + TABLE
            + " set "
            + " password = #{password} "
            + " where "
            + " id = #{id}")
    public void changePassword(
            @Param("id") int id,
            @Param("password") String newPassword);

}
