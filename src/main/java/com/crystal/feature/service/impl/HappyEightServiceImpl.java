package com.crystal.feature.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crystal.feature.common.constant.CommonCodeConstant;
import com.crystal.feature.mapper.HappyEightMapper;
import com.crystal.feature.model.entity.HappyEightEntity;
import com.crystal.feature.model.vo.HappyEightNumberFrequencyVo;
import com.crystal.feature.model.vo.HappyEightNumberNoAppearsVo;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.service.HappyEightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CHUNHAO LIU
 * 快乐8实现类
 */
@Service
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
            WinNumStrBuffer.append(entity.getNumber() + "-");
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

    @Override
    public ResultVo<List<HappyEightNumberFrequencyVo>> queryNumberFrequency(String time) {
        //判断参数合法性,若time不为空表示具体想查看固定的场次概率
        if (null != time) {
            QueryWrapper<HappyEightEntity> queryWrapper = new QueryWrapper<HappyEightEntity>().orderByDesc("stage").last("limit " + time);
            List<HappyEightEntity> happyEightEntityList = happyEightMapper.selectList(queryWrapper);
            //如果查询出来的集合为空的情况下直接返回空数据
            if (null == happyEightEntityList) {
                return new ResultVo<List<HappyEightNumberFrequencyVo>>().sucess();
            }
            //获奖号码出现的概率
            Map<String, Integer> WinNumTimeMap = new HashMap<>(80);
            //临时变量,用来记录值
            String str = "";
            int inter = 0;
            for (int i = 0; i < happyEightEntityList.size(); i++) {
                String[] winNumArr = happyEightEntityList.get(i).getNumber().split(",");

                //逐一比较号码是否出现在Map中 如果不存在则创建并赋初始值1，若存在则+1
                for (int j = 0; j < winNumArr.length; j++) {
                    str = winNumArr[j];
                    //若号码存在则数值+1，不存在则创建啊
                    if (WinNumTimeMap.containsKey(str)) {
                        inter = WinNumTimeMap.get(str);
                        WinNumTimeMap.replace(str, inter + 1);
                        continue;
                    }
                    WinNumTimeMap.put(str, 1);
                }

                //进行数据处理
                if (i == 4) {

                } else if (i == 8) {

                } else if (i == 12) {

                } else if (i == 16) {

                } else if (i == happyEightEntityList.size()) {

                }
            }

        }
        return null;
    }


    /**
     * 对传入的集合进行数据处理
     */
    public void WinNumProcess(Map<String, Integer> winNumTimeMap, String time) {

        if (time.equals(CommonCodeConstant.LAST_FOUR)) {

        }else if(time.equals(CommonCodeConstant.LAST_EIGHT)){

        }else if(time.equals(CommonCodeConstant.LAST_TWELVE)){

        }else if(time.equals(CommonCodeConstant.LAST_SIXTEEN)){

        }else if(time.equals(CommonCodeConstant.TOTAL_TIME)){

        }


    }

}
