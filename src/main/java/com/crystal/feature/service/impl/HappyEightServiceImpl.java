package com.crystal.feature.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crystal.feature.mapper.HappyEightMapper;
import com.crystal.feature.model.entity.HappyEightEntity;
import com.crystal.feature.model.vo.HappyEightNumberNoAppearsVo;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.service.HappyEightService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CHUNHAO LIU
 * 快乐8实现类
 */
public class HappyEightServiceImpl implements HappyEightService {

    @Autowired
    private HappyEightMapper happyEightMapper;

    @Override
    public ResultVo<HappyEightNumberNoAppearsVo> queryNumberNoAppearsListByStage(int stage) {
        ResultVo<HappyEightNumberNoAppearsVo> vo = new ResultVo<>();
        //倒序查询出当前几期的中奖号码
        QueryWrapper<HappyEightEntity> queryWrapper = new QueryWrapper<HappyEightEntity>().orderByDesc("stage").last("limit " + stage);
        List<HappyEightEntity> happyEightEntityList = happyEightMapper.selectList(queryWrapper);
        List<String> numberList = new ArrayList<>();
        //给号码集合做一个初始赋值
        int maxNumber = 80;

        for (int i = 1; i <= maxNumber; i++) {
            if (i < 10) {
                numberList.add("0" + i);
            } else {
                numberList.add(i + "");
            }

        }
        //如果集合为空的话直接返回
        if (null == happyEightEntityList) {
            return vo.sucess(new HappyEightNumberNoAppearsVo(stage, numberList));
        }
        //拼接所有中奖号码;
        StringBuffer WinNumStrBuffer = new StringBuffer();

        for (HappyEightEntity entity : happyEightEntityList) {
            //直接拼接所有的中将号码.
            WinNumStrBuffer.append(entity.getNumber());
        }

        String WinNumStr = WinNumStrBuffer.toString();
        //进行数据比对筛选出中奖的号码
        for (int j = 0; j < numberList.size(); j++) {
            //若中将号码存在则从初始化集合中移除
            if (WinNumStr.contains(j + "")) {
                numberList.remove(j);
                j--;
            }
        }

        return vo.sucess(new HappyEightNumberNoAppearsVo(stage, numberList));
    }



}
