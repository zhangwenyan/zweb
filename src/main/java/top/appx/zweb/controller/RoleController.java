package top.appx.zweb.controller;

import top.appx.zweb.dao.RoleDao;

import java.sql.SQLException;

/**
 * Created by mrz on 2017/3/24.
 */
public class RoleController {
    private RoleDao roleDao = new RoleDao();
    public Object tree_user() throws SQLException {
        return roleDao.tree_user();
    }
    public Object tree() throws SQLException{
        return roleDao.tree();
    }



}
