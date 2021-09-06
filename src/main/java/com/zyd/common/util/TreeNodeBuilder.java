package com.zyd.sys.util;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {

    /**
     * 构建菜单树节点的层级关系
     * @param treeNodes 菜单集合
     * @param rootId    根节点1
     * @return
     */
    public static List<TreeNode> build(List<TreeNode> treeNodes,int rootId){
        List<TreeNode> node = new ArrayList<TreeNode>();
        for (TreeNode n1 : treeNodes) {
            if (rootId== n1.getPid()){
                node.add(n1);
            }
            for (TreeNode n2 : treeNodes) {
                if (n1.getId()==n2.getPid()){
                    n1.getChildren().add(n2);
                }
            }
        }
        return node;
    }
}
