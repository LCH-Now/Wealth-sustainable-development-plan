package com.crystal.feature.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crystal.feature.model.dto.HappyEightInsertDto;
import com.crystal.feature.model.dto.PageDto;
import com.crystal.feature.model.entity.HappyEightEntity;
import com.crystal.feature.model.vo.HappyEightNumberNoAppearsVo;
import com.crystal.feature.model.vo.HappyEightNumberFrequencyVo;
import com.crystal.feature.model.vo.HappyEightQueryVo;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.service.HappyEightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author CHUNHAO LIU
 * 快乐8彩票实体类
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
    @RequestMapping(value = "query", method = RequestMethod.GET)
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
    @RequestMapping(value = "save", method = RequestMethod.POST)
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
}
