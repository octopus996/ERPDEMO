package com.zyd.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bus")
public class BusinessController {

    /**
     * 去到客户管理页面
     * @return
     */
    @RequestMapping("/toCustomerManager")
    public String toCustomerManager(){
        return "business/customer/customerManager";
    }

    /**
     * 去到商品类型管理页面
     * @return
     */
    @RequestMapping("/toGoodsTypeManager")
    public String toGoodsTypeManager(){
        return "business/goodstype/goodstypeManager";
    }

    @RequestMapping("/toGoodsTypeLeft")
    public String toGoodsTypeLeft(){
        return "business/goodstype/left";
    }

    @RequestMapping("/toGoodsTypeRight")
    public String toGoodsTypeRight(){
        return "business/goodstype/right";
    }

    /**
     * 去到商品管理页面
     * @return
     */
    @RequestMapping("/toGoodsManager")
    public String toGoodsManager(){
        return "business/goods/goodsManager";
    }

    @RequestMapping("/toGoodsLeft")
    public String toGoodsLeft(){
        return "business/goods/left";

    }
    @RequestMapping("/toGoodsRight")
    public String toGoodsRight(){
        return "business/goods/right";
    }
}
