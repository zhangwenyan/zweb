package top.appx.zweb.controller;

import top.appx.zutil.eweb.Param;
import top.appx.zweb.dao.RoleMenuDao;

import java.sql.SQLException;

/**
 * Created by mrz on 2017/3/24.
 */
public class RoleMenuController {

    private RoleMenuDao roleMenuDao = new RoleMenuDao();
    public Object queryByRoleId(@Param("roleId") int roleId) throws SQLException {
        return roleMenuDao.queryByRoleId(roleId);
    }
    public void save(@Param("roleId") int roleId,@Param("menuIds") String menuIds)throws SQLException{
        roleMenuDao.save(roleId,menuIds);
    }
}
