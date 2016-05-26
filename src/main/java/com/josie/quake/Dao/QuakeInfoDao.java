package com.josie.quake.dao;

import com.josie.quake.dao.annotation.DataSourceQuake;
import com.josie.quake.model.QuakeInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Josie on 16/5/12.
 */
@DataSourceQuake
public interface QuakeInfoDao {
    static final String COL_ALL = "id, title, description, type, manager, status, jump_to, create_time, publish_time, verify_time";
    static final String TABLE = "quake_info";

    @Select(""
            + " select "
            + COL_ALL
            + " from "
            + TABLE
            + " where status > 0 "
            + " limit #{start}, #{count}")
    public List<QuakeInfo> getAll(
            @Param("start") int start,
            @Param("count") int count);

    @Select(""
            + " select "
            + COL_ALL
            + " from "
            + TABLE
            + " where status = #{status} "
            + " limit #{start}, #{count}")
    public List<QuakeInfo> getAllByStatus(
            @Param("status") int status,
            @Param("start") int start,
            @Param("count") int count);

    @Select(""
            + " select "
            + " DATE_FORMAT(create_time, '%Y-%m-%d') as date, count(id) as count"
            + " from "
            + TABLE
            + " where DATE_FORMAT(create_time, '%Y-%m-%d') <= DATE_FORMAT(#{endDate}, '%Y-%m-%d') "
            + " and DATE_FORMAT(create_time, '%Y-%m-%d') > DATE_FORMAT(#{startDate}, '%Y-%m-%d') "
            + " group by DATE_FORMAT(create_time, '%Y-%m-%d')"
            + " order by DATE_FORMAT(create_time, '%Y-%m-%d')")
    public List<Map<String, Object>> getAllByDate(
            @Param("startDate") Date startDate,
            @Param("endDate") Date lastDate);

    @Select(""
            + " select "
            + " DATE_FORMAT(create_time, '%Y-%m-%d') as date, count(id) as count"
            + " from "
            + TABLE
            + " where status = #{status} "
            + " and DATE_FORMAT(create_time, '%Y-%m-%d') <= DATE_FORMAT(#{endDate}, '%Y-%m-%d') "
            + " and DATE_FORMAT(create_time, '%Y-%m-%d') > DATE_FORMAT(#{startDate}, '%Y-%m-%d') "
            + " group by DATE_FORMAT(create_time, '%Y-%m-%d')"
            + " order by DATE_FORMAT(create_time, '%Y-%m-%d')")
    public List<Map<String, Object>> getAllByStatusByDate(
            @Param("status") int status,
            @Param("startDate") Date startDate,
            @Param("endDate") Date lastDate);

    @Select(""
            + " select "
            + " count(id) as count, type "
            + " from "
            + TABLE
            + " where status = #{status} "
            + " group by type "
            + " order by rand()"
    )
    public List<Map<String,? extends Object>> getAllByTypeByStatus(
            @Param("status") int status);


    @Select(""
            + " select "
            + " count(id) as count, type "
            + " from "
            + TABLE
            + " group by type "
            + " order by rand()"
    )
    public List<Map<String,? extends Object>> getAllByType();


    @Update(""
            + " update "
            + TABLE
            + " set status = #{status},"
            + " manager = #{uid},"
            + " verify_time = now()"
            + " where id = #{id} "
    )
    public void updateStatus(
            @Param("uid") int uid,
            @Param("id") int id,
            @Param("status") int status);


    @Select(
            "select count(id) as count, description as keywords"
            + " from "
            + TABLE
            + " group by description order by rand()"
    )
    public List<Map<String, Object>> getByKeywords();

    @Select(
            "select count(id) as count, DATE_FORMAT(publish_time, '%Y-%m-%d') as publish_time"
            + " from "
            + TABLE
            + " where create_time != publish_time"
            + " group by DATE_FORMAT(publish_time, '%Y-%m-%d')"
            + " order by rand()"
    )
    public List<Map<String, Object>> getPublishTime();

    @Select(
            "select count(*) as count "
            + " from "
            + TABLE
    )
    public Integer getQuakeInfoCount();

    @Select(
            "select count(*) as count "
            + " from "
            + TABLE
            + " where DATE_FORMAT(create_time, '%Y-%m-%d') = DATE_FORMAT(#{date}, '%Y-%m-%d')"
    )
    public Integer getQuakeInfoCountByDate(@Param("date") Date date);

    @Select(
            "select count(*) as count "
            + " from "
            + TABLE
            + " where status = #{status}"
    )
    public Integer getQuakeInfoCountByStatus(@Param("status") int status);
}
