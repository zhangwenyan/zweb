package top.appx.zweb.dao;

import top.appx.zutil.DateTimeUtil;
import top.appx.zutil.easysql.BaseDatabase;
import top.appx.zutil.easysql.Restrain;
import top.appx.zutil.eweb.MsgException;
import top.appx.zutil.eweb.PageInfo;
import top.appx.zweb.entity.UserEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mrz on 2017/3/23.
 */
public class UserDao extends BaseDao {


    public Object queryPage(PageInfo pageInfo,Restrain... restrains) throws SQLException {
        return dh.queryPage(pageInfo,restrains);
    }

    public void save(UserEntity entity) throws SQLException {
        dh.save(entity);
    }
    public UserEntity login(UserEntity entity) throws SQLException {
        try(BaseDatabase db = dh.createDatabase()){
            List<UserEntity> list = db.queryBySql("select * from user where username=?",UserEntity.class,entity.getUsername());
            if(list.size()==0){
                throw new MsgException("用户名不存在");
            }
            UserEntity userEntity = list.get(0);
            if(userEntity.getStatus()!=1){
                throw new MsgException("用户当前状态不能登录");
            }
            if (userEntity.getPassword() != null && entity.getPassword().equals(entity.getPassword())) {
                //成功
                db.execute("update user set lastLogin=? where id=?", DateTimeUtil.now(), userEntity.getId());
                userEntity.setPassword(null);
                return  userEntity;
            } else {
                throw new MsgException("用户名或密码错误");
            }

        }
    }


}
