package com.crystal.feature.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crystal.feature.model.dto.HappyEightBuyDetailDto;
import com.crystal.feature.model.dto.PageDto;
import com.crystal.feature.model.entity.LotteryBuyDetailEntity;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.service.UserLotteryDetailService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author CHUNHAO LIU
 * 用户彩票数据管理
 */
@RestController
@RequestMapping("/user/lotteryDetail/")
public class UserLotteryDetailController {

    @Autowired
    private UserLotteryDetailService userLotteryDetailService;

    /**
     * 记录彩票购买明细
     *
     * @param dto 彩票购买明细
     * @return
     */
    @RequestMapping(value = "saveBuyDetail", method = RequestMethod.POST)
    public ResultVo<String> saveBuyDetail(@Valid @RequestBody HappyEightBuyDetailDto dto) {

        ResultVo<String> vo = new ResultVo<>();
        try {
            vo = userLotteryDetailService.saveBuyDetail(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return vo.fail();
        }

        return vo;
    }


    /**
     * 查询彩票购买记录明细列表
     *
     * @return
     */
    @RequestMapping(value = "queryBuyDetailList", method = RequestMethod.POST)
    public ResultVo<IPage<LotteryBuyDetailEntity>> queryBuyDetailList(@RequestBody PageDto dto) {

        ResultVo<IPage<LotteryBuyDetailEntity>> vo = new ResultVo<>();
        try {
            vo = userLotteryDetailService.queryBuyDetailList(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return vo.fail();
        }

        return vo;
    }

    /**
     * 查询是否中奖如果是则返回中奖信息对象,如果没中奖则返回信息提示还没有开奖。
     */
    @RequestMapping(value = "queryLotteryInfo", method = RequestMethod.GET)
    public ResultVo<LotteryBuyDetailEntity> queryLotteryInfo(@Param("id") String id) {

        ResultVo<LotteryBuyDetailEntity> vo = new ResultVo<>();
        try {
            vo = userLotteryDetailService.queryLotteryInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            return vo.fail();
        }

        return vo;
    }

}
