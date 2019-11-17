package me.zqt.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zqt.wx.mapper.BaiduNetdiskMapper;
import me.zqt.wx.model.entity.BaiduNetdisk;
import me.zqt.wx.service.BaiduNetdiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaiduNetdiskServiceImpl extends ServiceImpl<BaiduNetdiskMapper, BaiduNetdisk>
        implements BaiduNetdiskService{

    @Autowired
    BaiduNetdiskMapper baiduNetdiskMapper;

    @Override
    public BaiduNetdisk getByName(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        List<BaiduNetdisk> baiduNetdisks = baiduNetdiskMapper.selectByMap(map);
        return baiduNetdisks.get(0);
    }
}
