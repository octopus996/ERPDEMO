package com.zyd.bus.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyd.bus.Vo.GoodsTypeVo;
import com.zyd.bus.entity.GoodsType;
import com.zyd.bus.service.GoodsTypeService;
import com.zyd.common.util.DataGridViewResult;
import com.zyd.common.util.JSONResult;
import com.zyd.common.util.SystemConstant;
import com.zyd.common.util.TreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 加载商品类型左侧的树节点
     * @return
     */
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

    /**
     * 加载商品列表
     * @param goodsTypeVo
     * @return
     */
    @RequestMapping("goodstypelist")
    public DataGridViewResult goodstypelist(GoodsTypeVo goodsTypeVo){
        //创建分页构造器
        IPage<GoodsType> page=new Page<>(goodsTypeVo.getPage(),goodsTypeVo.getLimit());
        //创建条件构造器
        QueryWrapper<GoodsType> queryWrapper=new QueryWrapper<>();
        //模糊查询商品名称
        if (goodsTypeVo.getTitle() != null && goodsTypeVo.getTitle().length()>0){
            queryWrapper.like(StringUtils.isNotEmpty(goodsTypeVo.getTitle()),"title",goodsTypeVo.getTitle());
        }
        //通过点击商品左侧商品类型树节点查询商品类型
        queryWrapper.eq((goodsTypeVo.getId()!=null),"id",goodsTypeVo.getId()).or().
                eq((goodsTypeVo.getId()!=null),"pid",goodsTypeVo.getId());
        //按照id升序排列
        queryWrapper.orderByAsc("id");
        //调用分页构造器
        goodsTypeService.page(page,queryWrapper);

        return new DataGridViewResult(page.getTotal(),page.getRecords());
    }

    /**
     * 加载商品类型树节点到添加窗口
     * @return
     */
    @RequestMapping("/loadgoodstypeTreeLeft")
    public DataGridViewResult loadgoodstypeTreeLeft(){
        //创建条件构造器
        QueryWrapper<GoodsType> queryWrapper=new QueryWrapper<>();
        //查询商品类型所有的数据
        List<GoodsType> list = goodsTypeService.list(queryWrapper);
        //创建一个list集合保存树节点
        List<TreeNode> treeNodes=new ArrayList<>();
        for (GoodsType goodsType : list) {
            //创建树节点
            TreeNode treeNode=new TreeNode();
            boolean spread=SystemConstant.SPREAD==goodsType.getOpen()?true:false;
            treeNode.setId(goodsType.getId());
            treeNode.setTitle(goodsType.getTitle());;
            treeNode.setIcon(goodsType.getIcon());;
            treeNode.setPid(goodsType.getPid());
            treeNode.setSpread(spread);
            treeNode.setRemark(goodsType.getRemark());
            treeNodes.add(treeNode);
        }
        return new DataGridViewResult(treeNodes);
    }
    @RequestMapping("addgoodstype")
    public JSONResult addgoodstype(GoodsType  goodsType){
        if (goodsTypeService.save(goodsType)){
            return SystemConstant.ADD_SUCCESS;
        }else {
            return SystemConstant.ADD_ERROR;
        }
    }

    /**
     * 检查节点下有无子节点
     * @param id
     * @return
     */
    @RequestMapping("/checkgoodstypeHasChildren")
    public String checkgoodstypeHasChildren(int id){
        //创建一个map集合存放值
        Map<String,Object> map =new HashMap<>();
        //创建条件构造器
        QueryWrapper<GoodsType> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("pid",id);
        if (goodsTypeService.count(queryWrapper)>0){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"存在子节点，无法删除！");
        }else {
            map.put(SystemConstant.EXIST,false);
        }

        return JSON.toJSONString(map);
    }

    /**
     * 通过id删除节点
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public JSONResult deleteById(int id){
        if (goodsTypeService.removeById(id)){
            return SystemConstant.DELETE_SUCCESS;
        }
        return SystemConstant.DELETE_ERROR;
    }

    /**
     * 修改商品类型
     * @param goodsType
     * @return
     */
    @RequestMapping("/updategoodstype")
    public JSONResult updategoodstype(GoodsType goodsType){
        if (goodsTypeService.updateById(goodsType)){
            return SystemConstant.UPDATE_SUCCESS;
        }
        return SystemConstant.UPDATE_ERROR;
    }
}

