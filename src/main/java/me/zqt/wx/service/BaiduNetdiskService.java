package me.zqt.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.zqt.wx.model.entity.BaiduNetdisk;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public interface BaiduNetdiskService extends IService<BaiduNetdisk> {
    BaiduNetdisk getByName(String name);
}
