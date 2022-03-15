package com.crystal.feature.model.dto;

import lombok.Data;

/**
 * @author CHUNHAO LIU
 * 提供分页组件的Dto
 */
@Data
public class PageDto{

    /**
     * 页数
     */
    private Integer page=1;

    /**
     * 条数
     */
    private Integer size=10;

}
