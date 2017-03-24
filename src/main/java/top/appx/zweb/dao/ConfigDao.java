package top.appx.zweb.dao;

import top.appx.zutil.easysql.BaseDatabase;
import top.appx.zutil.easysql.Restrain;
import top.appx.zutil.eweb.MsgException;
import top.appx.zutil.eweb.PageInfo;
import top.appx.zweb.entity.ConfigEntity;

import java.sql.SQLException;

/**
 * Created by mrz on 2017/3/24.
 */
public class ConfigDao extends BaseDao {
    public Object queryPage(PageInfo pageInfo, Restrain... restrains) throws SQLException {
        return dh.queryPage(pageInfo,restrains);
    }
    public void save(ConfigEntity entity)throws SQLException{
        try(BaseDatabase db = dh.createDatabase()) {
            db.beginTransaction();
            db.save(entity);
            int c = db.total("select count(*) from config where name=?", entity.getName());
            if (c > 1) {
                throw new MsgException("已有相同名称配置");
            }

            db.commitTranscation();
        }
    }
    public void update(ConfigEntity entity) throws SQLException{
        try(BaseDatabase db = dh.createDatabase()){
            db.beginTransaction();
            db.update(entity);
            int c = db.total("select count(*) from config where name=?", entity.getName());
            if (c > 1) {
                throw new MsgException("配置名称不能重复");
            }
            db.commitTranscation();;
        }
    }
}
