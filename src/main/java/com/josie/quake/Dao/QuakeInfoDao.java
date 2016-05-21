package com.josie.quake.dao;

import com.josie.quake.dao.annotation.DataSourceQuake;
import com.josie.quake.model.QuakeInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
}
