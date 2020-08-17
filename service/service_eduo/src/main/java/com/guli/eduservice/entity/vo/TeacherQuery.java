package com.guli.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用于基本的数据查询
 */
@Data
public class TeacherQuery implements Serializable {
    @ApiModelProperty(value = "教师名称")
    private String name;
    @ApiModelProperty(value = "教师等级 1.高级讲师 2.首席教师")
    private Integer level;
    @ApiModelProperty(value = "教师的开始时间",example = "2019-08-07 11:10:50")
    private String begin;
    @ApiModelProperty(value = "查询时间结束时间",example = "2019-08-07 11:10:50")
    private String end;
}
