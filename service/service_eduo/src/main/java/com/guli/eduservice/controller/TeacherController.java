package com.guli.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.commonbase.exceptionhandler.GuLiException;
import com.guli.commonutils.R;
import com.guli.commonutils.ResultCode;
import com.guli.eduservice.entity.Teacher;
import com.guli.eduservice.entity.vo.TeacherQuery;
import com.guli.eduservice.service.TeacherService;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-17
 */

@RestController
    @RequestMapping("/eduservice/teacher")
@Api(description = "讲师管理")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService service;
    @ResponseBody
    @PostMapping("findAll")
    public R list(){
        List<Teacher> list = service.list(null);
        try{
            int i=1/0;
        }catch (Exception e){
            throw new GuLiException(ResultCode.ERROR,"执行自定义异常处理......");
        }
        return R.ok().data("items",list) ;
    }
    //2.逻辑删除除方法
    @DeleteMapping("{id}")
    public R removeTeacher(@PathVariable String id){
        boolean b = service.removeById(id);
        if(b){
            return R.ok();
        }else {
           return R.error();
        }
    }

    /**
     * 用于分页查询
     * @param current 表示当前页
     * @param limit 表示每页显示的记录数
     * @return
     */
    @PostMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(
            @PathVariable long current,
            @PathVariable long limit
    ){
        Page<Teacher> teacherPage = new Page<>(current,limit);
        //调用方法做分页
        service.page(teacherPage,null);
        //先获取总的记录数
        long total = teacherPage.getTotal();
        //获取每页数据的list集合
        List<Teacher> records = teacherPage.getRecords();
        return R.ok().data("toal",total).data("rows",records);
    }
    /**
     * 条件查询带分页查询
     */
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherConditon(
            @PathVariable long current,
            @PathVariable long limit,
           @RequestBody(required = false) TeacherQuery teacherQuery
    ){
        Page<Teacher> pageTeacher  = new Page<>(current,limit);
        //需要一个查询条件的对象
        //构建条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        //多条件组合查询sql
        //动态sql
        //判断条件值是否为空
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_creat",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }
        service.page(pageTeacher,wrapper);
        //先获取总的记录数
        long total = pageTeacher.getTotal();
        //获取每页数据的list集合
        List<Teacher> records = pageTeacher.getRecords();
        return R.ok().data("toal",total).data("rows",records);
    }
    //添加讲师的方法
    @PostMapping("addTeacher")
    @ResponseBody
    public R addTeacher(@RequestBody Teacher teacher){
        boolean save = service.save(teacher);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        Teacher teacher = service.getById(id);
        return R.ok().data("teacher",teacher);
    }
    //讲师修改功能
    @PostMapping("updateTheacher")
    @ResponseBody
    public R updateTeacher(@RequestBody Teacher teacher){
        boolean flag = service.updateById(teacher);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }

    }

}

