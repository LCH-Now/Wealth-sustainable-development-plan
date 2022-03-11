package com.crystal.feature.controller;

import com.crystal.feature.model.vo.HappyEightNumberNoAppearsVo;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.service.HappyEightService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * 倒序查出最近几期没有出现的号码
     * @param stage
     * @return
     */
    @RequestMapping(value = "queryNumberNoAppearsListByStage",method = RequestMethod.GET)
    public ResultVo<HappyEightNumberNoAppearsVo> queryNumberNoAppearsListByStage(@Param("stage") int stage) {

        ResultVo<HappyEightNumberNoAppearsVo> vo=new ResultVo<>();
        try{
            vo=happyEightService.queryNumberNoAppearsListByStage(stage);
        }catch (Exception e){
            e.printStackTrace();
        }

        return vo;
    }

}
