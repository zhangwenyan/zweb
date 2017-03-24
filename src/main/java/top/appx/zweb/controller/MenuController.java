package top.appx.zweb.controller;

import top.appx.zweb.dao.MenuDao;
import top.appx.zweb.entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by mrz on 2017/3/22.
 */
public class MenuController {

    private MenuDao menuDao = new MenuDao();
    public Object myMenuTree(HttpSession session) throws SQLException {
        UserEntity userEntity = (UserEntity)session.getAttribute("user");
        return menuDao.queryMenuByUserId(userEntity.getId());
    }

    public Object tree()throws SQLException{
        return menuDao.tree();
    }
    public Object query()throws SQLException{
        return menuDao.query();
    }
}
