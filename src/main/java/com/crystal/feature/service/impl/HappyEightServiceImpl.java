package com.crystal.feature.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crystal.feature.common.constant.CommonCodeConstant;
import com.crystal.feature.common.constant.CommonMessageConstant;
import com.crystal.feature.mapper.HappyEightMapper;
import com.crystal.feature.model.dto.HappyEightInsertDto;
import com.crystal.feature.model.dto.PageDto;
import com.crystal.feature.model.entity.HappyEightEntity;
import com.crystal.feature.model.vo.HappyEightNumberFrequencyVo;
import com.crystal.feature.model.vo.HappyEightNumberNoAppearsVo;
import com.crystal.feature.model.vo.HappyEightQueryVo;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.service.HappyEightService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author CHUNHAO LIU
 * 快乐8实现类
 */
@Service
public class HappyEightServiceImpl implements HappyEightService {

    @Autowired
    private HappyEightMapper happyEightMapper;

    @Override
    public ResultVo<IPage<Map<String, HappyEightQueryVo>>> query(PageDto dto) {


        IPage<HappyEightEntity> happyEightEntity = happyEightMapper.selectPage(new Page<>(dto.getPage(), dto.getSize()), new QueryWrapper<HappyEightEntity>().orderByDesc("stage"));

        //对查询出来的数据进行处理
        List<HappyEightEntity> happyEightEntityList = happyEightEntity.getRecords();

        if (null == happyEightEntityList || happyEightEntityList.size() == 0) {
            //如果集合为空则直接跳出
            return new ResultVo<IPage<Map<String, HappyEightQueryVo>>>().sucess();
        }

        //将开奖日期与中奖号码进行KEY-VALUE排列。
        List<Map<String, HappyEightQueryVo>> happyEightQueryList = new ArrayList<>();
        Map<String, HappyEightQueryVo> happyEightQueryMap = new HashMap<>();
        //如果不为空则对集合进行处理，将字段合并成一个输出
        for (HappyEightEntity entity : happyEightEntityList) {

            HappyEightQueryVo queryVo = new HappyEightQueryVo().fillAttribute(entity.getNumber());
            happyEightQueryMap.put(entity.getBatchNumber(), queryVo);

        }
        Page<Map<String, HappyEightQueryVo>> pageVo = new Page<>();
        BeanUtils.copyProperties(happyEightEntityList, pageVo);
        happyEightQueryList.add(happyEightQueryMap);
        pageVo.setRecords(happyEightQueryList);
        return new ResultVo<IPage<Map<String, HappyEightQueryVo>>>().sucess(pageVo);
    }

    @Override
    public ResultVo<String> save(@Valid HappyEightInsertDto dto) {

        //先查询一下当前的数据库中是否有对应的记录如果有则表示修改
        Date lotteryDate = dto.getLotteryDate();
        String batchNumber =dto.getBatchNumber();
        HappyEightEntity happyEightEntity = happyEightMapper.selectOne(new QueryWrapper<HappyEightEntity>().eq("batch_number", batchNumber));
        if (null == happyEightEntity) {
            happyEightEntity = new HappyEightEntity();
            //若为空表示新增
            happyEightEntity.setLotteryDate(lotteryDate);
            happyEightEntity.setId(UuidUtils.generateUuid());
            happyEightEntity.setBatchNumber(dto.getBatchNumber());
            happyEightEntity.setNumber(dto.toString());
            happyEightMapper.insert(happyEightEntity);
            return new ResultVo<String>().sucess(CommonMessageConstant.SAVE_SUCCESS);
        }
        //表示修改
        happyEightEntity.setNumber(dto.toString());
        happyEightMapper.updateById(happyEightEntity);
        return new ResultVo<String>().sucess(CommonMessageConstant.SAVE_SUCCESS);
    }

    @Override
    public ResultVo<HappyEightNumberNoAppearsVo> queryNumberNoAppearsListByStage(int stage) {
        ResultVo<HappyEightNumberNoAppearsVo> vo = new ResultVo<>();
        //倒序查询出当前几期的中奖号码
        QueryWrapper<HappyEightEntity> queryWrapper = new QueryWrapper<HappyEightEntity>().orderByDesc("batch_number").last("limit " + stage);
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
        //记录变量
        String str="";
        //进行数据比对筛选出中奖的号码
        for (int j = 0; j < numberList.size(); j++) {
            str=numberList.get(j);
            System.out.println("j:"+j+" str:"+str);
            //若中将号码存在则从初始化集合中移除
            if (WinNumStr.contains(str)) {
                numberList.remove(j);
                j--;
            }
        }

        return vo.sucess(new HappyEightNumberNoAppearsVo(stage, numberList));
    }

    @Override
    public ResultVo<List<HappyEightNumberFrequencyVo>> queryNumberFrequency(String time) {

        QueryWrapper<HappyEightEntity> queryWrapper = new QueryWrapper<HappyEightEntity>().orderByDesc("batch_number");

        //判断参数合法性,若time不为空表示具体想查看固定的场次概率
        if (null != time) {
            queryWrapper.last("limit " + time);
        }

        List<HappyEightEntity> happyEightEntityList = happyEightMapper.selectList(queryWrapper);
        //如果查询出来的集合为空的情况下直接返回空数据
        if (null == happyEightEntityList) {
            return new ResultVo<List<HappyEightNumberFrequencyVo>>().sucess();
        }
        //获奖号码出现的概率
        Map<String, HappyEightNumberFrequencyVo> winNumTimeMap = new HashMap<>(80);
        //临时变量,用来记录值
        String str = "";
        int inter = 0;

        for (int i = 0; i < happyEightEntityList.size(); i++) {
            String[] winNumArr = happyEightEntityList.get(i).getNumber().split(",");

            //逐一比较号码是否出现在Map中 如果不存在则创建并赋初始值1，若存在则+1
            for (int j = 0; j < winNumArr.length; j++) {
                str = winNumArr[j];
                //若号码存在则数值+1，不存在则创建啊
                if (winNumTimeMap.containsKey(str)) {
                    HappyEightNumberFrequencyVo temporary = winNumTimeMap.get(str);
                    temporary.setTotalTime(temporary.getTotalTime() + 1);
                    winNumTimeMap.replace(str, temporary);
                    continue;
                }
                winNumTimeMap.put(str, new HappyEightNumberFrequencyVo(str,1));
            }

            //判断第4 8 12 16次的时候计算
            if (i == 4 || i == 8 || i == 12 || i == 16 || i == happyEightEntityList.size()-1) {
                winNumTimeMap = this.WinNumProcess(winNumTimeMap, String.valueOf(i));
            }

        }

        //将HashMap的Value转化成List
        Collection<HappyEightNumberFrequencyVo> valueCollection = winNumTimeMap.values();
        List<HappyEightNumberFrequencyVo> happyEightNumberFrequencyVo = new ArrayList<>(valueCollection);

        return new ResultVo<List<HappyEightNumberFrequencyVo>>().sucess(happyEightNumberFrequencyVo);
    }


    /**
     * 对传入的集合进行数据处理,
     */
    public Map<String, HappyEightNumberFrequencyVo> WinNumProcess(Map<String, HappyEightNumberFrequencyVo> winNumTimeMap, String time) {
        //格式化小数
        DecimalFormat df = new DecimalFormat("0.00");
        for (String s : winNumTimeMap.keySet()) {
            //获取总次数
            Integer totalTime = winNumTimeMap.get(s).getTotalTime();

            //便利winNumTimeMap集合,计算概率
            if (time.equals(CommonCodeConstant.LAST_FOUR)) {
                winNumTimeMap.get(s).setLastFour(totalTime);
                //最近四场次的概率
                winNumTimeMap.get(s).setLastFourFrequency(df.format((float)totalTime / Integer.valueOf(CommonCodeConstant.LAST_FOUR)*100)+"%");
            } else if (time.equals(CommonCodeConstant.LAST_EIGHT)) {
                //最近8场次的概率
                winNumTimeMap.get(s).setLastEight(totalTime);
                winNumTimeMap.get(s).setLastEightFrequency(df.format((float)totalTime / Integer.valueOf(CommonCodeConstant.LAST_EIGHT)*100)+"%");
            } else if (time.equals(CommonCodeConstant.LAST_TWELVE)) {
                //最近12场次的概率
                winNumTimeMap.get(s).setLastTwelve(totalTime);
                winNumTimeMap.get(s).setLastTwelveFrequency(df.format((float)totalTime / Integer.valueOf(CommonCodeConstant.LAST_TWELVE)*100)+"%");
            } else if (time.equals(CommonCodeConstant.LAST_SIXTEEN)) {
                //最近16场次的概率
                winNumTimeMap.get(s).setLastSixteen(totalTime);
                winNumTimeMap.get(s).setLastSixteenFrequency(df.format((float)totalTime / Integer.valueOf(CommonCodeConstant.LAST_SIXTEEN)*100)+"%");
            } else {
                //所有场次的概率
                winNumTimeMap.get(s).setLastSixteenFrequency(df.format((float)totalTime / Integer.valueOf(time)*100)+"%");
            }
        }
        return winNumTimeMap;
    }

}
