package me.zqt.wx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.zqt.wx.model.entity.Resource;

import java.util.List;

/**
 * @auther: zqtao
 * @description: 普通资源业务层
 * @version: 1.0
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 根据资源名查询资源
     * @param name 资源名称
     * @return 资源集合
     */
    Resource getByName(String name);

    List<Resource> getListByName(String name);

    /**
     * 根据资源名删除资源
     * @param name 资源名称
     * @return 影响条数
     */
    int removeByName(String name);

    /**
     * 修改资源，根据资源名修改资源URL
     * @param resource 资源实体
     * @return 影响条数
     */
    int modifiedResource(Resource resource);

    String deal(String[] properties);
}
