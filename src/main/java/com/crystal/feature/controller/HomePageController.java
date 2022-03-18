package com.crystal.feature.controller;

import com.crystal.feature.model.vo.HomePageMainVo;
import com.crystal.feature.model.vo.ResultVo;
import com.crystal.feature.service.HomePageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHUNHAO LIU
 * 首页展示报表数据形成条形图或者树状图等
 */
@RestController
@RequestMapping("/homepage")
public class HomePageController {

    @Autowired
    private HomePageService homePageService;

    /**
     * 展示数据 中奖总金额,总成本,总利润。中奖概率
     */
    @RequestMapping(value = "/baseInfo", method = RequestMethod.GET)
    public ResultVo<HomePageMainVo> baseInfo(@Param("lotteryType") String lotteryType) {

        ResultVo<HomePageMainVo> vo = new ResultVo<>();
        try {
            //vo = homePageService
        } catch (Exception e) {
            e.printStackTrace();
            return vo.fail();
        }

        return vo;
    }
}
