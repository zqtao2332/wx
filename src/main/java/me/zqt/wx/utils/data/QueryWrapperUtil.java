package me.zqt.wx.utils.data;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @auther: zqtao
 * @description: QueryWrapper<T>查询封装
 * @version: 1.0
 */
public class QueryWrapperUtil<T> {

    public QueryWrapper<T> eqInstance(String column, Object val){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        return queryWrapper.eq(column, val);
    }

}
