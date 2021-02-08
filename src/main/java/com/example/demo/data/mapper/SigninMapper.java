package com.example.demo.data.mapper;

import com.example.demo.data.domain.Signin;
import com.example.demo.data.domain.SigninExample;
import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface SigninMapper {
    @SelectProvider(type=SigninSqlProvider.class, method="countByExample")
    long countByExample(SigninExample example);

    @DeleteProvider(type=SigninSqlProvider.class, method="deleteByExample")
    int deleteByExample(SigninExample example);

    @Insert({
        "insert into signin (name, number, ",
        "longitude, latitude, ",
        "date)",
        "values (#{name,jdbcType=VARCHAR}, #{number,jdbcType=VARCHAR}, ",
        "#{longitude,jdbcType=DECIMAL}, #{latitude,jdbcType=DECIMAL}, ",
        "#{date,jdbcType=TIMESTAMP})"
    })
    int insert(Signin record);

    @InsertProvider(type=SigninSqlProvider.class, method="insertSelective")
    int insertSelective(Signin record);

    @SelectProvider(type=SigninSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
        @Result(column="longitude", property="longitude", jdbcType=JdbcType.DECIMAL),
        @Result(column="latitude", property="latitude", jdbcType=JdbcType.DECIMAL),
        @Result(column="date", property="date", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Signin> selectByExampleWithRowbounds(SigninExample example, RowBounds rowBounds);

    @SelectProvider(type=SigninSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="number", property="number", jdbcType=JdbcType.VARCHAR),
        @Result(column="longitude", property="longitude", jdbcType=JdbcType.DECIMAL),
        @Result(column="latitude", property="latitude", jdbcType=JdbcType.DECIMAL),
        @Result(column="date", property="date", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Signin> selectByExample(SigninExample example);

    @UpdateProvider(type=SigninSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Signin record, @Param("example") SigninExample example);

    @UpdateProvider(type=SigninSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Signin record, @Param("example") SigninExample example);
}