package com.crystal.feature.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crystal.feature.common.constant.CommonMessageConstant;
import com.crystal.feature.mapper.UserMapper;
import com.crystal.feature.model.dto.UserRegisterDto;
import com.crystal.feature.model.entity.UserEntity;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.service.RegisterService;
import com.skq.core.jwt.service.TokenManager;
import com.skq.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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

    @Autowired
    private TokenManager tokenManager;

    @Override
    public ResultVo<String> userRegister(HttpServletRequest request, UserRegisterDto dto) throws Exception {
        //创建返回参数
        ResultVo<String> vo = new ResultVo<String>();
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
        user.setID(UuidUtil.getUuid());
        //默认登录名为身份证号
        user.setUserName(IDCard);
        //构建一个盐值对象
        String salt = UuidUtil.getUuid();
        //生成的密码经过加盐加密
        String newPassword = DigestUtils.md5DigestAsHex((salt + user.getPassword()).getBytes());
        user.setPassword(newPassword);
        userMapper.insert(user);

        //注册成功生成Token
        String token = tokenManager.createToken(IDCard);

        return vo.sucess(token);
    }
}
