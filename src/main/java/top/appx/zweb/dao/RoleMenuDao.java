package top.appx.zweb.dao;

import top.appx.zutil.StringUtil;
import top.appx.zutil.easysql.BaseDatabase;
import top.appx.zweb.entity.RoleMenuEntity;

import java.sql.SQLException;

/**
 * Created by mrz on 2017/3/24.
 */
public class RoleMenuDao extends BaseDao {
    public Object queryByRoleId(int roleId) throws SQLException{
        return dh.queryDataTable("select * from roleMenu where roleId=?",roleId);
    }

    public void save(int roleId, String menuIds) throws SQLException {
        try(BaseDatabase db = dh.createDatabase()){
            db.beginTransaction();
            db.execute("delete from roleMenu where roleId=?",roleId);
            if(!StringUtil.isNullOrEmpty(menuIds)){
                for (String s : menuIds.split(",")) {
                    RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
                    roleMenuEntity.setRoleId(roleId);
                    roleMenuEntity.setMenuId(Integer.parseInt(s));
                    db.save(roleMenuEntity);
                }
            }
            db.commitTranscation();
        }
    }
}
