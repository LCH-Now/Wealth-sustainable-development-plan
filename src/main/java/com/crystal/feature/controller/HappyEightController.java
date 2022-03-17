package com.crystal.feature.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crystal.feature.model.dto.HappyEightBuyDetailDto;
import com.crystal.feature.model.dto.HappyEightInsertDto;
import com.crystal.feature.model.dto.PageDto;
import com.crystal.feature.model.entity.HappyEightBuyDetailEntity;
import com.crystal.feature.model.vo.*;
import com.crystal.feature.service.HappyEightService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author CHUNHAO LIU
 * 快乐8彩票控制层
 */
@RestController
@RequestMapping("/happyEight")
public class HappyEightController {


    @Autowired
    private HappyEightService happyEightService;


    /**
     * 查询历史彩票数据
     *
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public ResultVo<IPage<Map<String, HappyEightQueryVo>>> query(@RequestBody PageDto dto) {

        ResultVo<IPage<Map<String, HappyEightQueryVo>>> vo = new ResultVo<>();
        try {

            vo = happyEightService.query(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return vo.fail();
        }

        return vo;
    }


    /**
     * 保存或者修改彩票中奖号码数据。
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "saveWinNumber", method = RequestMethod.POST)
    public ResultVo<String> save(@Valid @RequestBody HappyEightInsertDto dto) {

        ResultVo<String> vo = new ResultVo<>();
        try {
            vo = happyEightService.save(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return vo.fail();
        }

        return vo;
    }

    /**
     * 查询出最近4场 8场等出现的概率，理论上出现的概率为0.25;
     *
     * @param time
     * @return
     */
    @RequestMapping(value = "queryNumberFrequency", method = RequestMethod.GET)
    public ResultVo<List<HappyEightNumberFrequencyVo>> queryNumberFrequency(@RequestParam(value = "time", required = false) String time) {

        ResultVo<List<HappyEightNumberFrequencyVo>> vo = new ResultVo<>();
        try {
            vo = happyEightService.queryNumberFrequency(time);
        } catch (Exception e) {
            e.printStackTrace();
            return vo.fail();
        }

        return vo;
    }


    /**
     * 倒序查出最近几期没有出现的号码
     *
     * @param stage 场次
     * @return
     */
    @RequestMapping(value = "queryNumberNoAppearsListByStage", method = RequestMethod.GET)
    public ResultVo<HappyEightNumberNoAppearsVo> queryNumberNoAppearsListByStage(@RequestParam(value = "stage", required = false) int stage) {

        ResultVo<HappyEightNumberNoAppearsVo> vo = new ResultVo<>();
        try {
            vo = happyEightService.queryNumberNoAppearsListByStage(stage);
        } catch (Exception e) {
            e.printStackTrace();
            return vo.fail();
        }

        return vo;
    }

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
            vo = happyEightService.saveBuyDetail(dto);
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
    public ResultVo<IPage<HappyEightBuyDetailEntity>> queryBuyDetailList(@RequestBody PageDto dto) {

        ResultVo<IPage<HappyEightBuyDetailEntity>> vo = new ResultVo<>();
        try {
            vo = happyEightService.queryBuyDetailList(dto);
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
    public ResultVo<HappyEightBuyDetailEntity> queryLotteryInfo(@Param("id") String id) {

        ResultVo<HappyEightBuyDetailEntity> vo = new ResultVo<>();
        try {
            vo = happyEightService.queryLotteryInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            return vo.fail();
        }

        return vo;
    }

}
