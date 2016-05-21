package com.josie.quake.Dao;

import com.josie.quake.Dao.annotation.DataSourceQuake;
import com.josie.quake.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.soap.SOAPBinding;
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
            + " last_update_time = now()")
    public void updateUser(
            User user);

    @Update(""
            + " update "
            + TABLE
            + " set "
            + " username = #{username}, "
            + " phone_number = #{phoneNumber}, "
            + " mail_adress = #{mailAdress}, "
            + " work_place = #{workPlace}, "
            + " positon = #{positon}, "
            + " last_update_time = now()"
            + " where id = #{id}")
    void update(
            @Param("id") Integer id,
            @Param("username") String username,
            @Param("mailAdress") String mailAdress,
            @Param("phoneNumber") String phoneNumber,
            @Param("positon") String positon,
            @Param("workPlace") String workPlace);
}
