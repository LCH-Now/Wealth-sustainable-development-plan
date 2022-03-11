package com.crystal.feature.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crystal.feature.model.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author CHUNHAO LIU
 */
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 依据身份证号码查询人员信息
     * @param IDCard
     * @return 返回对象信息
     */
    @Select("Select * FROM t_user where id_card=#{IDCard}")
    UserEntity selectByIdCard(@Param("IDCard") String IDCard);

}
