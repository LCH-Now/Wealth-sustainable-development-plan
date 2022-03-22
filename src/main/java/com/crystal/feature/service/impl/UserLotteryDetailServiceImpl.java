package com.crystal.feature.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crystal.feature.common.constant.CommonCodeConstant;
import com.crystal.feature.common.constant.CommonMessageConstant;
import com.crystal.feature.common.constant.CommonReturnConstant;
import com.crystal.feature.mapper.HappyEightMapper;
import com.crystal.feature.mapper.LotteryBuyDetailMapper;
import com.crystal.feature.mapper.LotteryRulesMapper;
import com.crystal.feature.model.dto.HappyEightBuyDetailDto;
import com.crystal.feature.model.dto.PageDto;
import com.crystal.feature.model.entity.HappyEightEntity;
import com.crystal.feature.model.entity.LotteryBuyDetailEntity;
import com.crystal.feature.model.entity.LotteryRulesEntity;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.service.UserLotteryDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author CHUNHAO LIU
 * 用户彩票购买服务实现类
 */
@Repository
public class UserLotteryDetailServiceImpl implements UserLotteryDetailService {

    @Autowired
    private HappyEightMapper happyEightMapper;

    @Autowired
    private LotteryBuyDetailMapper lotteryBuyDetailMapper;

    @Autowired
    private LotteryRulesMapper lotteryRulesMapper;

    @Override
    public ResultVo<String> saveBuyDetail(HappyEightBuyDetailDto dto) {

        //获取彩票号码,拼接成字符串
        String[] numberArr = dto.getNumberArr();
        StringBuffer numberBuffer = new StringBuffer();

        for (int i = 0; i < numberArr.length; i++) {
            numberBuffer.append(numberArr[i] + ",");
        }

        LotteryBuyDetailEntity happyEightBuyDetailEntity = new LotteryBuyDetailEntity();
        BeanUtils.copyProperties(dto, happyEightBuyDetailEntity);
        String number = numberBuffer.toString();
        //获取玩法规则,以获取彩票单价
        String playType = dto.getPlayType();
        //本期购买注数
        Integer quantity = dto.getQuantity();

        List<LotteryRulesEntity> rulesEntityList = lotteryRulesMapper.selectList(new QueryWrapper<LotteryRulesEntity>().eq("lottery_type", CommonCodeConstant.LOTTERY_TYPE_HAPPY_EIGHT).eq("play_type", playType));
        if ((rulesEntityList.size() == 0) || null == rulesEntityList) {
            //如果查询不出来规则，则直接返回提示规则未维护。请联系管理员维护规则。
            return new ResultVo<String>().sucess(CommonMessageConstant.LOTTERY_RULES_TO_BE_MAINTAIN);
        }
        Float price = rulesEntityList.get(0).getPrice();
        //输入保存购买号码
        happyEightBuyDetailEntity.setNumber(number);
        happyEightBuyDetailEntity.setPurchaseAmount(quantity * price);
        happyEightBuyDetailEntity.setPrice(price);

        //如果有传主键id,表示更新,没传表示新增。
        String id = dto.getId();

        if (null == id || "".equals(id)) {
            id = UuidUtils.generateUuid();
            happyEightBuyDetailEntity.setId(id);
            //新增入库
            lotteryBuyDetailMapper.insert(happyEightBuyDetailEntity);

            return new ResultVo<String>().sucess(CommonMessageConstant.SAVE_SUCCESS);
        }
        //修改数据
        lotteryBuyDetailMapper.updateById(happyEightBuyDetailEntity);

        return new ResultVo<String>().sucess(CommonMessageConstant.SAVE_SUCCESS);
    }

    @Override
    public ResultVo<IPage<LotteryBuyDetailEntity>> queryBuyDetailList(PageDto dto) {
        //查询数据库列表的购买数据信息
        IPage<LotteryBuyDetailEntity> happyEightQueryBuyDetailEntityList = lotteryBuyDetailMapper.selectPage(new Page<>(dto.getPage(), dto.getSize()), new QueryWrapper<LotteryBuyDetailEntity>().orderByDesc("batch_number"));

        return new ResultVo<IPage<LotteryBuyDetailEntity>>().sucess(happyEightQueryBuyDetailEntityList);
    }

    @Override
    public ResultVo<LotteryBuyDetailEntity> queryLotteryInfo(String id) {

        //依据购买编号查询出来详情
        LotteryBuyDetailEntity happyEightBuyDetailEntity = lotteryBuyDetailMapper.selectById(id);

        if (null == happyEightBuyDetailEntity) {
            //表示查无数据
            return new ResultVo(CommonReturnConstant.SUCCESS_BUT_NOT_DATA_CODE,CommonReturnConstant.SUCCESS_BUT_NOT_DATA_MSG);
        }

        String open = happyEightBuyDetailEntity.getOpen();
        if (CommonCodeConstant.IS_OPEN_LOTTERY_TIME.equals(open)) {
            //如果已经开奖则直接返回数据,
            return new ResultVo<LotteryBuyDetailEntity>().sucess(happyEightBuyDetailEntity);
        }

        //购买彩票号码
        List<String> numberList=new ArrayList(Arrays.asList(happyEightBuyDetailEntity.getNumber().split(",")));

        //获取当期的彩票数据
        String batchNumber = happyEightBuyDetailEntity.getBatchNumber();
        HappyEightEntity happyEightEntity = happyEightMapper.selectOne(new QueryWrapper<HappyEightEntity>().eq("batch_number", batchNumber));

        if (null == happyEightEntity) {
            //表示查无数据
            return new ResultVo(CommonReturnConstant.SUCCESS_BUT_NOT_DATA_CODE,CommonReturnConstant.SUCCESS_BUT_NOT_DATA_MSG);
        }
        //当期中奖号码
        String winningNumber = happyEightEntity.getNumber();
        //判断中奖号码的个数
        for (int i = 0; i < numberList.size(); i++) {
            if (!winningNumber.contains(numberList.get(i))) {
                numberList.remove(i);
                i--;
            }
        }

        //依据玩法规则判断奖金以及中奖
        happyEightBuyDetailEntity = checkLotteryInfo(happyEightBuyDetailEntity, numberList);
        happyEightBuyDetailEntity.setUpdateTime(new Date());
        lotteryBuyDetailMapper.updateById(happyEightBuyDetailEntity);

        return new ResultVo<LotteryBuyDetailEntity>().sucess(happyEightBuyDetailEntity);
    }


    /**
     * 依据规则查询中奖信息。
     */
    public LotteryBuyDetailEntity checkLotteryInfo(LotteryBuyDetailEntity happyEightBuyDetailEntity, List<String> numberList) {

        //本次中奖个数
        Integer winNumber = numberList.size();
        //首先依据玩法查询出游戏规则
        String playType = happyEightBuyDetailEntity.getPlayType();
        List<LotteryRulesEntity> rulesEntityList = lotteryRulesMapper.selectList(new QueryWrapper<LotteryRulesEntity>().eq("lottery_type", CommonCodeConstant.LOTTERY_TYPE_HAPPY_EIGHT).eq("play_type", playType));
        Map<Integer, LotteryRulesEntity> rulesEntityMap = new HashMap<>();

        //依据中奖个数进行判断计算金额等
        for (int i = 0; i < rulesEntityList.size(); i++) {
            LotteryRulesEntity lotteryRulesEntity = rulesEntityList.get(i);
            //中奖个数
            Integer numberOfWinner = lotteryRulesEntity.getNumberOfWinners();

            rulesEntityMap.put(numberOfWinner, lotteryRulesEntity);
        }

        //获取本次购买的注数
        Integer quantity = happyEightBuyDetailEntity.getQuantity();
        Float cost = happyEightBuyDetailEntity.getPrice();
        Float bonus = 0.0f;
        Float winningAmount = 0.0f;
        String isWinning = CommonCodeConstant.IS_NOT_WINNING;
        //购买金额
        Float purchaseAmount = cost * quantity;
        if (rulesEntityMap.containsKey(winNumber)) {
            LotteryRulesEntity lotteryRulesEntity = rulesEntityMap.get(winNumber);
            //单注奖金
            bonus = lotteryRulesEntity.getBonus();
            //计算中奖金额 数量*金额
            winningAmount = quantity * bonus;
            //表示中奖
            isWinning = CommonCodeConstant.IS_WINNING;
        }
        //计算利润 中奖金额-成本
        Float netProfit = winningAmount - purchaseAmount;

        happyEightBuyDetailEntity.setBonus(winningAmount);
        happyEightBuyDetailEntity.setNetProfit(netProfit);
        happyEightBuyDetailEntity.setOpen(CommonCodeConstant.IS_OPEN_LOTTERY_TIME);
        happyEightBuyDetailEntity.setIsWinning(isWinning);
        return happyEightBuyDetailEntity;
    }

}
