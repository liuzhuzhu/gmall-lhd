package com.atguigu.gmall.bean.enums;

public class Java0316 {
    /**
     * 2020 09 15
     * team1 今天的任务已经完成 准备下班  下班之前需要将当天代码提交到team1 发分支上不需要合并
     * 提交流程 git add
     *      *          git commit
     *      *              git push
     */
    public void addCart(){
        System.out.println("测试了一下");
        System.out.println("添加购物车");
    }
    /**
     * 2020 09 16
     * 无论任何时候,只要不是第一次创建分支,就需要执行git pull 操作 保证本地代码与远程仓库的高度一致性
     */
    public void updateCart(){
        System.out.println("添加购物车");
    }
}
