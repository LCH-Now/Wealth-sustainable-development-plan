package com.crystal.feature.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crystal.feature.common.constant.CommonCodeConstant;
import com.crystal.feature.common.constant.CommonMessageConstant;
import com.crystal.feature.mapper.HappyEightBuyDetailMapper;
import com.crystal.feature.mapper.HappyEightMapper;
import com.crystal.feature.mapper.LotteryRulesMapper;
import com.crystal.feature.model.dto.HappyEightBuyDetailDto;
import com.crystal.feature.model.dto.HappyEightInsertDto;
import com.crystal.feature.model.dto.PageDto;
import com.crystal.feature.model.entity.HappyEightBuyDetailEntity;
import com.crystal.feature.model.entity.HappyEightEntity;
import com.crystal.feature.model.entity.LotteryRulesEntity;
import com.crystal.feature.model.vo.*;
import com.crystal.feature.service.HappyEightService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private HappyEightBuyDetailMapper happyEightBuyDetailMapper;

    @Autowired
    private LotteryRulesMapper lotteryRulesMapper;

    @Override
    public ResultVo<IPage<Map<String, HappyEightQueryVo>>> query(PageDto dto) {


        IPage<HappyEightEntity> happyEightEntity = happyEightMapper.selectPage(new Page<>(dto.getPage(), dto.getSize()), new QueryWrapper<HappyEightEntity>().orderByDesc("batch_number"));

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
        String batchNumber = dto.getBatchNumber();
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
        StringBuffer winNumStrBuffer = new StringBuffer();

        for (HappyEightEntity entity : happyEightEntityList) {
            //直接拼接所有的中将号码.
            winNumStrBuffer.append(entity.getNumber());
        }

        String winNumStr = winNumStrBuffer.toString();
        //记录变量
        String str = "";
        //进行数据比对筛选出中奖的号码
        for (int j = 0; j < numberList.size(); j++) {
            str = numberList.get(j);
            System.out.println("j:" + j + " str:" + str);
            //若中将号码存在则从初始化集合中移除
            if (winNumStr.contains(str)) {
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

            inter = i + 1;
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
                winNumTimeMap.put(str, new HappyEightNumberFrequencyVo(str, 1));
            }

            //判断第4 8 12 16次的时候计算
            if (inter == 4 || inter == 8 || inter == 12 || inter == 16 || inter == happyEightEntityList.size()) {
                winNumTimeMap = this.winNumProcess(winNumTimeMap, String.valueOf(inter));
            }

        }

        //将HashMap的Value转化成List
        Collection<HappyEightNumberFrequencyVo> valueCollection = winNumTimeMap.values();
        List<HappyEightNumberFrequencyVo> happyEightNumberFrequencyVo = new ArrayList<>(valueCollection);

        return new ResultVo<List<HappyEightNumberFrequencyVo>>().sucess(happyEightNumberFrequencyVo);
    }

    @Override
    public ResultVo<String> saveBuyDetail(HappyEightBuyDetailDto dto) {

        //获取彩票号码,拼接成字符串
        String[] numberArr = dto.getNumberArr();
        StringBuffer number = new StringBuffer();

        for (int i = 0; i < numberArr.length; i++) {
            number.append(numberArr[i] + ",");
        }

        HappyEightBuyDetailEntity happyEightBuyDetailEntity = new HappyEightBuyDetailEntity();
        BeanUtils.copyProperties(dto, happyEightBuyDetailEntity);
        //如果有传主键id,表示更新,没传表示新增。
        String id = dto.getId();

        if (null == id || id.equals(id)) {
            id = UuidUtils.generateUuid();
            happyEightBuyDetailEntity.setId(id);
            //新增入库
            happyEightBuyDetailMapper.insert(happyEightBuyDetailEntity);

            return new ResultVo<String>().sucess(CommonMessageConstant.SAVE_SUCCESS);
        }
        //修改数据
        happyEightBuyDetailMapper.updateById(happyEightBuyDetailEntity);

        return new ResultVo<String>().sucess(CommonMessageConstant.SAVE_SUCCESS);
    }

    @Override
    public ResultVo<IPage<HappyEightBuyDetailEntity>> queryBuyDetailList(PageDto dto) {
        //查询数据库列表的购买数据信息
        IPage<HappyEightBuyDetailEntity> happyEightQueryBuyDetailEntityList = happyEightBuyDetailMapper.selectPage(new Page<>(dto.getPage(), dto.getSize()), new QueryWrapper<HappyEightBuyDetailEntity>().orderByDesc("batch_number"));

        return new ResultVo<IPage<HappyEightBuyDetailEntity>>().sucess(happyEightQueryBuyDetailEntityList);
    }

    @Override
    public ResultVo<HappyEightBuyDetailEntity> queryLotteryInfo(String id) {

        //依据购买编号查询出来详情
        HappyEightBuyDetailEntity happyEightBuyDetailEntity = happyEightBuyDetailMapper.selectById(id);

        String open = happyEightBuyDetailEntity.getOpen();
        if (CommonCodeConstant.IS_OPEN_LOTTERY_TIME.equals(open)) {
            //如果已经开奖则直接返回数据,
            return new ResultVo<HappyEightBuyDetailEntity>().sucess(happyEightBuyDetailEntity);
        }

        //购买彩票号码
        List<String> numberList = Arrays.asList(happyEightBuyDetailEntity.getNumber().split(","));

        //获取当期的彩票数据
        String batchNumber = happyEightBuyDetailEntity.getBatchNumber();
        HappyEightEntity happyEightEntity = happyEightMapper.selectOne(new QueryWrapper<HappyEightEntity>().eq("batch_number", batchNumber));

        //判断中奖号码的个数
        for (int i = 0; i < numberList.size(); i++) {
            if (!batchNumber.contains(numberList.get(i))) {
                numberList.remove(i);
                i--;
            }
        }

        //依据玩法规则判断奖金以及中奖


        return null;
    }


    /**
     * 对传入的集合进行数据处理,
     */
    public Map<String, HappyEightNumberFrequencyVo> winNumProcess(Map<String, HappyEightNumberFrequencyVo> winNumTimeMap, String time) {
        //格式化小数
        DecimalFormat df = new DecimalFormat("0.00");
        for (String s : winNumTimeMap.keySet()) {
            //获取总次数
            Integer totalTime = winNumTimeMap.get(s).getTotalTime();

            //便利winNumTimeMap集合,计算概率
            if (time.equals(CommonCodeConstant.HAPPY_EIGHT_LAST_FOUR)) {
                winNumTimeMap.get(s).setLastFour(totalTime);
                //最近四场次的概率
                winNumTimeMap.get(s).setLastFourFrequency(df.format((float) totalTime / Integer.valueOf(CommonCodeConstant.HAPPY_EIGHT_LAST_FOUR) * 100) + "%");
            } else if (time.equals(CommonCodeConstant.HAPPY_EIGHT_LAST_EIGHT)) {
                //最近8场次的概率
                winNumTimeMap.get(s).setLastEight(totalTime);
                winNumTimeMap.get(s).setLastEightFrequency(df.format((float) totalTime / Integer.valueOf(CommonCodeConstant.HAPPY_EIGHT_LAST_EIGHT) * 100) + "%");
            } else if (time.equals(CommonCodeConstant.HAPPY_EIGHT_LAST_TWELVE)) {
                //最近12场次的概率
                winNumTimeMap.get(s).setLastTwelve(totalTime);
                winNumTimeMap.get(s).setLastTwelveFrequency(df.format((float) totalTime / Integer.valueOf(CommonCodeConstant.HAPPY_EIGHT_LAST_TWELVE) * 100) + "%");
            } else if (time.equals(CommonCodeConstant.HAPPY_EIGHT_LAST_SIXTEEN)) {
                //最近16场次的概率
                winNumTimeMap.get(s).setLastSixteen(totalTime);
                winNumTimeMap.get(s).setLastSixteenFrequency(df.format((float) totalTime / Integer.valueOf(CommonCodeConstant.HAPPY_EIGHT_LAST_SIXTEEN) * 100) + "%");

            } else {
                //所有场次的概率
                winNumTimeMap.get(s).setTotalTimeFrequency(df.format((float) totalTime / Integer.valueOf(time) * 100) + "%");
            }
        }
        return winNumTimeMap;
    }


    /**
     * 依据规则查询中奖信息。
     */
    public HappyEightBuyDetailEntity checkLotteryInfo(HappyEightBuyDetailEntity happyEightBuyDetailEntity, List<String> numberList) {


        //首先查询出游戏规则
        String playType = happyEightBuyDetailEntity.getPlayType();
        List<LotteryRulesEntity> rulesEntityList = lotteryRulesMapper.selectList(new QueryWrapper<LotteryRulesEntity>().eq("lottery_type", CommonCodeConstant.LOTTERY_TYPE_HAPPY_EIGHT).eq("play_type", playType));
        Map<String, LotteryRulesEntity> rulesEntityMap = new HashMap<>();
        for (int i = 0; i < rulesEntityList.size(); i++) {
            LotteryRulesEntity lotteryRulesEntity = rulesEntityList.get(i);
            //中奖个数
            String numberOfWinner = lotteryRulesEntity.getNumberOfWinners();

            rulesEntityMap.put(numberOfWinner, lotteryRulesEntity);
        }

        if (CommonCodeConstant.HAPPY_EIGHT_CHOOSE_ONE.equals(playType)) {
            //选一玩法

        } else if (CommonCodeConstant.HAPPY_EIGHT_CHOOSE_TWO.equals(playType)) {
            //选二玩法

        } else if (CommonCodeConstant.HAPPY_EIGHT_CHOOSE_THREE.equals(playType)) {
            //选三玩法

        } else if (CommonCodeConstant.HAPPY_EIGHT_CHOOSE_FOUR.equals(playType)) {
            //选四玩法

        } else if (CommonCodeConstant.HAPPY_EIGHT_CHOOSE_FIVE.equals(playType)) {
            //选五玩法

        } else if (CommonCodeConstant.HAPPY_EIGHT_CHOOSE_SIX.equals(playType)) {
            //选六玩法

        } else if (CommonCodeConstant.HAPPY_EIGHT_CHOOSE_SEVEN.equals(playType)) {
            //选七玩法

        } else if (CommonCodeConstant.HAPPY_EIGHT_CHOOSE_EIGHT.equals(playType)) {
            //选八玩法

        } else if (CommonCodeConstant.HAPPY_EIGHT_CHOOSE_NINE.equals(playType)) {
            //选九玩法

        } else if (CommonCodeConstant.HAPPY_EIGHT_CHOOSE_TEN.equals(playType)) {
            //选十玩法

        }

        return happyEightBuyDetailEntity;
    }

}
