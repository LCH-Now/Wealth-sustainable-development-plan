package com.crystal.feature.controller;

import com.crystal.feature.model.vo.HappyEightNumberNoAppearsVo;
import com.crystal.feature.model.vo.HappyEightNumberFrequencyVo;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.service.HappyEightService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * 查询出最近4场 8场等出现的概率，理论上出现的概率为0.25;
     * @param time
     * @return
     */
    @RequestMapping(value = "queryNumberFrequency",method = RequestMethod.GET)
    public ResultVo<List<HappyEightNumberFrequencyVo>> queryNumberFrequency(@RequestParam(value = "time" ,required = false) String time) {

        ResultVo<List<HappyEightNumberFrequencyVo>> vo=new ResultVo<>();
        try{
            //vo=happyEightService.queryNumberNoAppearsListByStage(stage);
        }catch (Exception e){
            e.printStackTrace();
        }

        return vo;
    }





    /**
     * 倒序查出最近几期没有出现的号码
     * @param stage 场次
     * @return
     */
    @RequestMapping(value = "queryNumberNoAppearsListByStage",method = RequestMethod.GET)
    public ResultVo<HappyEightNumberNoAppearsVo> queryNumberNoAppearsListByStage(@RequestParam(value = "stage" ,required = false)  int stage) {

        ResultVo<HappyEightNumberNoAppearsVo> vo=new ResultVo<>();
        try{
            vo=happyEightService.queryNumberNoAppearsListByStage(stage);
        }catch (Exception e){
            e.printStackTrace();
        }

        return vo;
    }

}
