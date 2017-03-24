package top.appx.zweb.dao;

import com.alibaba.fastjson.JSONObject;
import top.appx.zutil.easysql.DataRow;
import top.appx.zutil.easysql.DataTable;
import top.appx.zutil.info.ResultMap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrz on 2017/3/24.
 */
public class RoleDao extends BaseDao {

    public Object tree_user() throws SQLException {
        DataTable dt = dh.queryDataTable("select * from role");
        List<Object> result =new ArrayList<>();
        for (DataRow dataRow : dt) {
            result.add(ResultMap.data("text",dataRow.get("name")).p(dataRow));
        }
        return result;
    }

    public Object tree() throws SQLException{
        DataTable dt = dh.queryDataTable("select * from role");
        List<Object> result =new ArrayList<>();
        for (DataRow dataRow : dt) {
            result.add(ResultMap.data("text",dataRow.get("name")).p(dataRow));
        }
        return result;
    }
}
