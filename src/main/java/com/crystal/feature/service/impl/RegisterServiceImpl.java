package com.crystal.feature.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crystal.feature.common.constant.CommonMessageConstant;
import com.crystal.feature.common.util.IpUtil;
import com.crystal.feature.common.util.Md5Util;
import com.crystal.feature.common.config.RedisUtil;
import com.crystal.feature.mapper.UserMapper;
import com.crystal.feature.model.dto.UserRegisterDto;
import com.crystal.feature.model.entity.UserEntity;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.service.RegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author CHUNHAO LIU
 * 注册接口实现类
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultVo<String> userRegister(HttpServletRequest request, UserRegisterDto dto) throws Exception {
        //创建返回参数
        ResultVo<String> vo=new ResultVo<String>();
        //判断该用户是否已经存在与数据库之中,若存在则抛出异常。
        String IDCard = dto.getIdCard();

        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>().eq("id_card", IDCard);
        List<UserEntity> userList = userMapper.selectList(queryWrapper);
        //若集合大于0,表示库中已经存在与该身份证相同的人员。
        if (userList.size() > 0) {
            return vo.fail(CommonMessageConstant.USER_ALREADY_EXIST);
        }
        UserEntity user = new UserEntity();
        //将数据新增到数据库之中
        BeanUtils.copyProperties(dto, user);
        user.setID(UuidUtils.generateUuid());
        //默认登录名为身份证号
        user.setUserName(IDCard);
        userMapper.insert(user);

        //注册成功后,获取登入者的IP
        String ipAddress=IpUtil.getIpAddr(request);
        //将IP与密码拼接后进行加密。
        StringBuffer strToBeEncrypted=new StringBuffer().append(dto.getPassword()).append(ipAddress);
        //生成token返回给请求方。
        String token=Md5Util.string2MD5(strToBeEncrypted.toString());
        //将token写如到缓存之中并设定过期时间为2个小时。
        RedisUtil.set(token,ipAddress,7200);
        return vo.sucess(token);
    }
}
