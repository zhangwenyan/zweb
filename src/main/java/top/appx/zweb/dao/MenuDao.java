package top.appx.zweb.dao;

import top.appx.zutil.easysql.Restrain;
import top.appx.zweb.entity.MenuEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mrz on 2017/3/23.
 */
public class MenuDao extends BaseDao {

    public List<MenuEntity> queryMenuByUserId(int userid) throws SQLException {
        String sql = "select * from menu where id in ( "
                +" select menuId from roleMenu where roleId in ("
                +"select roleId from userRole where userId=?))";
        return dh.queryBySql(sql,MenuEntity.class,userid);
    }

    public Object tree() throws SQLException {
        return dh.queryByEntity(new MenuEntity(), Restrain.orderDesc("sequence"));
    }

}
