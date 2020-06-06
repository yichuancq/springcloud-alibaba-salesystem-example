package com.example.keygen.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.keygen.core.model.LeafAlloc;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @calss name IDAllocMapper
 * @description:IDAllocMapper
 * @author: yichuan
 * @create time: 2020/06/06 16:28
 */
@Mapper
public interface IDAllocMapper extends BaseMapper<LeafAlloc> {

    /***
     *
     * @return
     */
    @Select("SELECT biz_tag, max_id, step, update_time FROM leaf_alloc")
    @Results(value = {
            @Result(column = "biz_tag", property = "key"),
            @Result(column = "max_id", property = "maxId"),
            @Result(column = "step", property = "step"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<LeafAlloc> getAllLeafAllocs();

    /***
     *
     * @param tag
     * @return
     */
    @Select("SELECT biz_tag, max_id, step FROM leaf_alloc WHERE biz_tag = #{tag}")
    @Results(value = {
            @Result(column = "biz_tag", property = "key"),
            @Result(column = "max_id", property = "maxId"),
            @Result(column = "step", property = "step")
    })
    LeafAlloc getLeafAlloc(@Param("tag") String tag);

    /**
     * @param tag
     */
    @Update("UPDATE leaf_alloc SET max_id = max_id + step WHERE biz_tag = #{tag}")
    void updateMaxId(@Param("tag") String tag);

    /**
     * @param leafAlloc
     */
    @Update("UPDATE leaf_alloc SET max_id = max_id + #{step} WHERE biz_tag = #{key}")
    void updateMaxIdByCustomStep(@Param("leafAlloc") LeafAlloc leafAlloc);

    /**
     * @return
     */
    @Select("SELECT biz_tag FROM leaf_alloc")
    List<String> getAllTags();
}
