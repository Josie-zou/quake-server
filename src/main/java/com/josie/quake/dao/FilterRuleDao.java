package com.josie.quake.dao;

import com.josie.quake.dao.annotation.DataSourceQuake;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Josie on 16/5/21.
 */
@DataSourceQuake
public interface FilterRuleDao {
    static final String TABLE = "filter_rules";
    static final String COL_ALL = "id, rule, operator, status, create_time";


    @Insert(""
            + "insert into "
            + TABLE
            + " set rule = #{rule}, "
            + " operator = #{id}, "
            + " create_time = now()")
    public void addRule(
            @Param("rule") String rule,
            @Param("id") int id);

    @Update(""
            + " update "
            + TABLE
            + " set status = #{status}, "
            + " operator = #{operator} "
            + " where id = #{id} ")
    public void delete(
            @Param("id") int id,
            @Param("operator") int operator,
            @Param("status") int status);
}
