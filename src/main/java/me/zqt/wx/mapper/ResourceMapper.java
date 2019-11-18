package me.zqt.wx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.zqt.wx.model.entity.BaiduNetdisk;
import me.zqt.wx.model.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {
}
