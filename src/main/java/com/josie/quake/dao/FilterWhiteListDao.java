package com.josie.quake.dao;

import com.josie.quake.dao.annotation.DataSourceQuake;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Josie on 16/5/21.
 */
@DataSourceQuake
public interface FilterWhiteListDao {
    static final String TABLE = "filter_whitelist";
    static final String COL_ALL = " id, url, status, operater, create_time";

    @Insert(""
            + " insert into "
            + TABLE
            + " set url = #{url}, "
            + " operater = #{operater}, "
            + " create_time = now()")
    public void add(
            @Param("url") String url,
            @Param("operater") int operater);

    @Update(""
            + " update "
            + TABLE
            + " set status = #{status}, "
            + " operater = #{operater} "
            + " where id = #{id}")
    public void delete(
            @Param("id") int id,
            @Param("operater") int operater,
            @Param("status") int status);
}
