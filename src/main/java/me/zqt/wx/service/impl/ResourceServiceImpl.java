package me.zqt.wx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zqt.wx.mapper.ResourceMapper;
import me.zqt.wx.model.entity.Resource;
import me.zqt.wx.service.ResourceService;
import me.zqt.wx.utils.data.QueryWrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource>
        implements ResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public Resource getByName(String name) {
        return resourceMapper.selectOne(new QueryWrapperUtil<Resource>().eqInstance("name", name));
    }

    @Override
    public List<Resource> getListByName(String name) {
        return resourceMapper.selectList(new QueryWrapperUtil<Resource>().eqInstance("name", name));
    }

    @Override
    public int removeByName(String name) {
        return resourceMapper.delete(new QueryWrapperUtil<Resource>().eqInstance("name", name));
    }

    @Override
    public int modifiedResource(Resource resource) {
        return resourceMapper.update(resource,
                new QueryWrapperUtil<Resource>().eqInstance("name", resource.getName()));
    }

    public int add(Resource resource){
        Resource byName = getByName(resource.getName());
        return byName == null ? resourceMapper.insert(resource) : 0;
    }

    /**
     * 资源操作分配方法
     * 操作模板：资源#增#资源名称#资源URL
     *
     * @param properties 请求操作
     * @return content 操作结果
     */
    @Override
    public String deal(String[] properties) {

        String content = "";

        if (properties[1].equals("增")) {
            Resource resource = getResourceInstance(properties);
            content = "" + (add(resource) == 1 ? "新增资源成功！" : "新增资源失败！");
        } else if (properties[1].equals("删")) {
            int res = removeByName(properties[2]);
            content = "" + (res == 0 ? "资源不存在！" : "删除资源成功！");
        } else if (properties[1].equals("改")) {
            Resource resource = getByName(properties[2]);
            int res = modifiedResource(resource);
            content = "" + (res == 1 ? "修改资源成功！" : "修改资源失败！");
        } else if (properties[1].equals("查")) {
            List<Resource> resourceList = getListByName(properties[2]);
            if (resourceList.isEmpty()) {
                content = "无相关资源!";
            } else {
                StringBuilder sb = new StringBuilder();
                for (Resource r : resourceList)
                    sb.append(r.getName() + "  " + r.getUrl() + "\n");
                content = sb.toString();
            }
        } else {
            content = "请检查输入！资源操作模板：资源#增#资源名称#资源URL";
        }
        return content;
    }

    private Resource getResourceInstance(String[] properties) {
        Resource resource = new Resource();
        resource.setName(properties[2]);
        resource.setUrl(properties[3]);
        resource.setCreateTime(LocalDateTime.now());
        return resource;
    }
}
