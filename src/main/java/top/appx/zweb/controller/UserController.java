package top.appx.zweb.controller;

import top.appx.zutil.eweb.MsgException;
import top.appx.zutil.eweb.PageInfo;
import top.appx.zutil.eweb.Param;
import top.appx.zutil.info.ResultMap;
import top.appx.zweb.dao.UserDao;
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
public class UserController {
    private UserDao userDao = new UserDao();

    public Object queryPage(PageInfo pageInfo,UserEntity userEntity) throws SQLException {
        pageInfo.setQuery(userEntity);
        return userDao.queryPage(pageInfo);
    }
    public void modify(UserEntity userEntity,@Param("roleIds") String roleIds) throws SQLException {
        userDao.update(userEntity,roleIds);
    }
    public void add(UserEntity userEntity,@Param("roleIds") String roleIds)throws SQLException{
        userDao.save(userEntity,roleIds);
    }

    public Object getLoginUser(HttpSession session){
        return ResultMap.data("nickname",((UserEntity)session.getAttribute("user")).getNickname());
    }

    public void login(UserEntity userEntity,HttpSession session) throws SQLException {
        UserEntity userEntity1 = userDao.login(userEntity);
        session.setAttribute("user",userEntity1);
    }


}
