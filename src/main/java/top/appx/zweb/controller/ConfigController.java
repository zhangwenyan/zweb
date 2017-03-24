package top.appx.zweb.controller;

import top.appx.zutil.easysql.Restrain;
import top.appx.zutil.eweb.PageInfo;
import top.appx.zweb.dao.ConfigDao;
import top.appx.zweb.entity.ConfigEntity;

import java.sql.SQLException;

/**
 * Created by mrz on 2017/3/24.
 */
public class ConfigController {
    private ConfigDao configDao = new ConfigDao();
    public Object queryPage(PageInfo pageInfo, ConfigEntity configEntity) throws SQLException {
        pageInfo.setQuery(configEntity);
        return configDao.queryPage(pageInfo, Restrain.order("name"));
    }
    public void add(ConfigEntity entity)throws SQLException{
        configDao.save(entity);
    }
    public void modify(ConfigEntity entity)throws SQLException{
        configDao.update(entity);
    }
}
