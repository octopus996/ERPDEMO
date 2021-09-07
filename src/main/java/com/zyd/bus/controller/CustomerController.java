package com.zyd.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.bus.Vo.CustomerVo;
import com.zyd.bus.entity.Customer;
import com.zyd.bus.service.CustomerService;
import com.zyd.common.util.DataGridViewResult;
import com.zyd.common.util.JSONResult;
import com.zyd.common.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyd
 * @since 2021-09-06
 */
@RestController
@RequestMapping("/bus/customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @RequestMapping("/customerlist")
    public DataGridViewResult customerlist(CustomerVo customerVo){
        //创建分页构造起
        IPage<Customer> page=new Page<>(customerVo.getPage(),customerVo.getLimit());
        //创建条件构造器
        QueryWrapper<Customer> queryWrapper=new QueryWrapper<>();
        //模糊查询客户姓名
        queryWrapper.like(StringUtils.isNotEmpty(customerVo.getCustomername()),"customername",customerVo.getCustomername());
        //模糊查询客户电话
        queryWrapper.like(StringUtils.isNotEmpty(customerVo.getTelephone()),"telephone",customerVo.getTelephone());
        //模糊查询客户姓名
        queryWrapper.like(StringUtils.isNotEmpty(customerVo.getLinkman()),"linkman",customerVo.getLinkman());
        //调用分页
         customerService.page(page,queryWrapper);
         //返回数据
        return  new DataGridViewResult(page.getTotal(),page.getRecords());
    }

    /**
     * 增加客户
     * @param customer
     * @return
     */
    @RequestMapping("/addCustomer")
    public JSONResult addCustomer(Customer customer){

        if (customerService.save(customer)){
            return SystemConstant.ADD_SUCCESS;
        }else{
            return SystemConstant.ADD_ERROR;
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/batchDelete")
    public JSONResult batchDelete(String ids){
        String[] idStr=ids.split(",");
        if (customerService.removeByIds(Arrays.asList(idStr))){
            return SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;

    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping("deleteById")
    public JSONResult deleteById(Integer id){
        if (customerService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;
    }

    /**
     * 更新客户信息
     * @param customer
     * @return
     */
    @RequestMapping("updateCustomer")
    public JSONResult updateCustomer(Customer  customer){
        if (customerService.updateById(customer)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }

}

