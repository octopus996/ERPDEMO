package com.zyd.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyd.bus.entity.GoodsType;
import com.zyd.bus.service.GoodsTypeService;
import com.zyd.common.util.DataGridViewResult;
import com.zyd.common.util.SystemConstant;
import com.zyd.common.util.TreeNode;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyd
 * @since 2021-09-07
 */
@RestController
@RequestMapping("/bus/goodstype")
public class GoodsTypeController {

    @Resource
    private GoodsTypeService goodsTypeService;
    @RequestMapping("/loadGoodsTypeLeft")
    public DataGridViewResult loadGoodsTypeLeft(){

        //创建条件构造器
        QueryWrapper<GoodsType> queryWrapper=new QueryWrapper<>();
        //接受goodstype的左右数据
        List<GoodsType> list = goodsTypeService.list(queryWrapper);
        List<TreeNode> treeNodes=new ArrayList<>();
        //遍历goodstype
        for (GoodsType goodsType : list) {
            boolean spread= SystemConstant.SPREAD==goodsType.getOpen()?true:false;
            TreeNode treeNode=new TreeNode();
            treeNode.setId(goodsType.getId());
            treeNode.setPid(goodsType.getPid());
            treeNode.setSpread(spread);
            treeNode.setIcon(goodsType.getIcon());
            treeNode.setTitle(goodsType.getTitle());
            treeNodes.add(treeNode);
        }
        return  new DataGridViewResult(treeNodes);
    }
}

