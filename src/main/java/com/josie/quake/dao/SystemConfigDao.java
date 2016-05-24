package com.josie.quake.dao;

import com.josie.quake.dao.annotation.DataSourceQuake;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Glacier on 16/5/24.
 */
@DataSourceQuake
public interface SystemConfigDao {
    static final String COL_ALL = "id, type, status, modify_time";
    static final String TABLE = "system_config";

    @Select(
            " select status from "
            + TABLE
            + " where type = #{type}"
    )
    boolean getStatus(@Param("type") int type);

    @Update(
            " update "
            + TABLE
            + " set status = #{status} "
            + " where type = #{type}"
    )
    void setStatus(@Param("type")int type, @Param("status") boolean status);

}
