package com.example.demo.data.mapper;

import com.example.demo.data.domain.Tool;
import com.example.demo.data.domain.ToolExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface ToolMapper {
    @SelectProvider(type=ToolSqlProvider.class, method="countByExample")
    long countByExample(ToolExample example);

    @DeleteProvider(type=ToolSqlProvider.class, method="deleteByExample")
    int deleteByExample(ToolExample example);

    @Delete({
        "delete from tool",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tool (id, name, ",
        "description, imageKey, ",
        "aiKey)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR}, #{imagekey,jdbcType=VARCHAR}, ",
        "#{aikey,jdbcType=VARCHAR})"
    })
    int insert(Tool record);

    @InsertProvider(type=ToolSqlProvider.class, method="insertSelective")
    int insertSelective(Tool record);

    @SelectProvider(type=ToolSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="imageKey", property="imagekey", jdbcType=JdbcType.VARCHAR),
        @Result(column="aiKey", property="aikey", jdbcType=JdbcType.VARCHAR)
    })
    List<Tool> selectByExampleWithRowbounds(ToolExample example, RowBounds rowBounds);

    @SelectProvider(type=ToolSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="imageKey", property="imagekey", jdbcType=JdbcType.VARCHAR),
        @Result(column="aiKey", property="aikey", jdbcType=JdbcType.VARCHAR)
    })
    List<Tool> selectByExample(ToolExample example);

    @Select({
        "select",
        "id, name, description, imageKey, aiKey",
        "from tool",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="imageKey", property="imagekey", jdbcType=JdbcType.VARCHAR),
        @Result(column="aiKey", property="aikey", jdbcType=JdbcType.VARCHAR)
    })
    Tool selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ToolSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Tool record, @Param("example") ToolExample example);

    @UpdateProvider(type=ToolSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Tool record, @Param("example") ToolExample example);

    @UpdateProvider(type=ToolSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Tool record);

    @Update({
        "update tool",
        "set name = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "imageKey = #{imagekey,jdbcType=VARCHAR},",
          "aiKey = #{aikey,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Tool record);
}